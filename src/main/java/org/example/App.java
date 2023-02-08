package org.example;

import org.example.enums.Status;
import org.example.model.Address;
import org.example.model.Countries;
import org.example.model.Programmer;
import org.example.model.Project;
import org.example.service.*;
import org.example.util.DataBaseConnection;

import java.time.LocalDate;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final CountryService cr = new CountryServiceImpl();
    private static final AddressService ad = new AddressServiceImpl();
    private static final ProgrammerService pr = new ProgrammerServiceImpl();
    private static final ProjectService pj = new ProjectServiceImpl();
    public static void main( String[] args ) {
        DataBaseConnection.getSession();
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("""
                    ----------MAIN----------
                    1 - Country
                    2 - Address
                    3 - Programmer
                    4 - Project
                    5 - Close
                    """);
            int choose = new Scanner(System.in).nextInt();
            switch (choose) {
                case 1 -> {
                    boolean isCountry = true;
                    while (isCountry) {
                        System.out.println("""
                                ----------COUNTRY----------
                                1 - Save countries
                                2 - Save a lot of countries
                                3 - Show all countries
                                4 - Find by id
                                5 - Delete by id
                                6 - Delete all countries
                                7 - Update countries
                                8 - Get countries by long description
                                9 - Country quantity
                                10 - Exit
                                """);
                        int number1 = new Scanner(System.in).nextInt();
                        switch (number1) {
                            case 1 -> System.out.println(cr.saveCountry(createCountry()));
                            case 2 -> {
                                try {
                                    System.out.println("How many countries do you want to add?");
                                    int count = new Scanner(System.in).nextInt();
                                    List<Countries> countries = new ArrayList<>();
                                    for (int i = 0; i < count; i++) {
                                        System.out.println("-----" + i + " countries-----");
                                        countries.add(createCountry());
                                    }
                                    System.out.println(cr.saveAllCountries(countries));
                                } catch (InputMismatchException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            case 3 -> System.out.println(cr.getAllCountries());
                            case 4 -> {
                                System.out.println("Enter countryId: ");
                                System.out.println(cr.findById(scanId()));
                            }
                            case 5 -> {
                                System.out.println("Enter countryId: ");
                                System.out.println(cr.deleteById(scanId()));
                            }
                            case 6 -> System.out.println(cr.deleteAllCountries());
                            case 7 -> {
                                System.out.println("Enter countryId: ");
                                System.out.println(cr.updateCountry(scanId(), createCountry()));
                            }
                            case 8 -> System.out.println(cr.getCountryByLongDescription());
                            case 9 -> {
                                System.out.println("Enter countries name: ");
                                System.out.println(cr.countryQuantity(new Scanner(System.in).nextLine()));
                            }
                            case 10 -> isCountry = false;
                        }
                    }
                }case 2 -> {
                    boolean isAddress = true;
                    while (isAddress){
                        System.out.println("""
                                ----------ADDRESS----------
                                1 - Save address
                                2 - Save all addresses
                                3 - Show all addresses
                                4 - Find by id
                                5 - Delete by id
                                6 - Delete all addresses
                                7 - Update
                                8 - Exit
                                """);
                        int number2 = new Scanner(System.in).nextInt();
                        switch (number2){
                            case 1 -> {
                                try {
                                    System.out.println("Enter country ID: ");
                                    Long id = scanId();
                                    System.out.println(ad.saveAddress(createAddress(), id));
                                }catch (InputMismatchException e){
                                    System.out.println(e.getMessage());
                                }
                            }
                            case 2 -> {
                                try {
                                    System.out.println("Enter country ID: ");
                                    Long id = scanId();
                                    System.out.println("How many countries do you want to add?");
                                    int count = new Scanner(System.in).nextInt();
                                    List<Address> addresses = new ArrayList<>();
                                    for (int i = 0; i < count; i++) {
                                        System.out.println("-----" + i + " address-----");
                                        addresses.add(createAddress());
                                    }
                                    System.out.println(ad.saveAllAddress(addresses,id));
                                } catch (InputMismatchException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            case 3 -> System.out.println(ad.getAllAddresses());
                            case 4 -> System.out.println(ad.findById(scanId()));
                            case 5 -> System.out.println(ad.findById(scanId()));
                            case 6 -> System.out.println(ad.deleteAllAddress());
                            case 7 -> {
                                System.out.println("Enter old address id: ");
                                System.out.println(ad.updateAddress(scanId(),createAddress()));
                            }
                            case 8 -> isAddress = false;
                        }
                    }
                }case 3 -> {
                        boolean isProgrammer = true;
                        while (isProgrammer) {
                            System.out.println("""
                                    ----------PROGRAMMER----------
                                    1 - Save programmer
                                    2 - Show all programmers
                                    3 - Find by id
                                    4 - Delete by id
                                    5 - Delete all programmers
                                    6 - Update programmer
                                    7 - Find programmer by country name
                                    8 - Get young programmer
                                    9 - Get old programmer
                                    10 - Exit
                                    """);
                            int number3 = new Scanner(System.in).nextInt();
                            switch (number3) {
                                case 1 -> {
                                    System.out.println("Enter addressId: ");
                                    Long id = scanId();
                                    System.out.println(pr.saveProgrammer(createProgrammer(), id));
                                }
                                case 2 -> System.out.println(pr.getAllProgrammer());
                                case 3 -> {
                                    System.out.println("Enter programmer Id: ");
                                    System.out.println(pr.findById(scanId()));
                                }
                                case 4 -> {
                                    System.out.println("Enter programmer Id: ");
                                    System.out.println(pr.deleteById(scanId()));
                                }
                                case 5 -> System.out.println(pr.deleteAllProgrammer());
                                case 6 -> {
                                    System.out.println("Enter old programmer id: ");
                                    System.out.println(pr.updateProgrammer(scanId(),createProgrammer()));
                                }
                                case 7 -> {
                                    try {
                                        System.out.println("Enter country name: ");
                                        System.out.println(pr.findProgrammersByCountry(new Scanner(System.in).nextLine()));
                                    }catch (InputMismatchException e){
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 8 -> System.out.println(pr.getYoungProgrammer());
                                case 9 -> System.out.println(pr.getOldProgrammer());
                                case 10 -> isProgrammer = false;
                            }
                        }
                    }
                    case 4 -> {
                        boolean isProject = true;
                        while (isProject) {
                            System.out.println("""
                                    ----------PROJECT----------
                                    1 - Save project
                                    2 - Save all projects
                                    3 - Get all projects
                                    4 - Find by id
                                    5 - Delete by id
                                    6 - Delete all projects
                                    7 - Update project
                                    8 - Assign programmers to project
                                    9 - Get Expensive Project
                                    10 - Get Project Written In A Short Time
                                    11 - Exit
                                    """);
                            int number4 = new Scanner(System.in).nextInt();
                            switch (number4){
                                case 1 -> System.out.println(pj.saveProject(createProject()));
                                case 2 -> {
                                    try {
                                        System.out.println("How many projects do you want to add?");
                                        int count = new Scanner(System.in).nextInt();
                                        List<Project> projects = new ArrayList<>();
                                        for (int i = 0; i < count; i++) {
                                            System.out.println("-----" + i + " project-----");
                                            projects.add(createProject());
                                        }
                                        System.out.println(pj.saveAllProject(projects));
                                    } catch (InputMismatchException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 3 -> System.out.println(pj.getAllProjects());
                                case 4 -> System.out.println(pj.findProjectById(scanId()));
                                case 5 -> System.out.println(pj.deleteProjectById(scanId()));
                                case 6 -> System.out.println(pj.deleteAllProjects());
                                case 7 -> System.out.println(pj.updateProject(scanId(),createProject()));
                                case 8 -> {
                                    System.out.println("Enter project ID: ");
                                    Long p =scanId();
                                    System.out.println("Enter programmer ID: ");
                                    System.out.println(pj.assignProgrammersToProject(p,scanId()));
                                }
                                case 9 -> System.out.println(pj.getExpensiveProject());
                                case 10 -> System.out.println(pj.getProjectWrittenInAShortTime());
                                case 11 -> isProject = false;
                            }
                        }
                    }
                    case 5 -> isTrue = false;
            }
        }
    }
    public static Countries createCountry(){
        try {
            System.out.println("Enter countries name: ");
            String name = new Scanner(System.in).nextLine();
            System.out.println("Enter description: ");
            String desc = new Scanner(System.in).nextLine();
            return new Countries(name,desc);
        }catch(InputMismatchException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Address createAddress(){
        try {
            System.out.println("Enter region name: ");
            String name = new Scanner(System.in).nextLine();
            System.out.println("Enter street: ");
            String street = new Scanner(System.in).nextLine();
            System.out.println("Enter home number: ");
            int number = new Scanner(System.in).nextInt();
            return new Address(name,street,number);
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Programmer createProgrammer(){
        try {
            System.out.println("Enter full name: ");
            String name = new Scanner(System.in).nextLine();
            System.out.println("Enter email: ");
            String email = new Scanner(System.in).nextLine();
            System.out.println("Enter date of birth(Y/M/D): ");
            LocalDate birth = LocalDate.of(new Scanner(System.in).nextInt(),
                    new Scanner(System.in).nextInt(),
                    new Scanner(System.in).nextInt());
            System.out.println("Select status(1 - OWNER, 2 - COLLABORATOR, 3 - CONTRIBUTOR): ");
            int status = new Scanner(System.in).nextInt();
            Status status1 = Status.COLLABORATOR;
            switch (status){
                case 1 -> status1 = Status.OWNER;
                case 2 -> status1 = Status.COLLABORATOR;
                case 3 -> status1 = Status.CONTRIBUTOR;
                default -> System.out.println("No such number!");
            }
            return new Programmer(name,email,birth,status1);
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Project createProject(){
        try {
            System.out.println("Enter project name: ");
            String name = new Scanner(System.in).nextLine();
            System.out.println("Enter description: ");
            String description = new Scanner(System.in).nextLine();
            System.out.println("Enter dateOfStart(Y/M/D): ");
            LocalDate start = LocalDate.of(new Scanner(System.in).nextInt(),
                    new Scanner(System.in).nextInt(),
                    new Scanner(System.in).nextInt());
            System.out.println("Enter dateOfFinish(Y/M/D): ");
            LocalDate finish = LocalDate.of(new Scanner(System.in).nextInt(),
                    new Scanner(System.in).nextInt(),
                    new Scanner(System.in).nextInt());
            System.out.println("Enter price: ");
            int price = new Scanner(System.in).nextInt();
            return new Project(name,description,start,finish,price);
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static Long scanId(){
        Long id = null;
        try {
            id = new Scanner(System.in).nextLong();
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
        }
        return id;
    }
}
