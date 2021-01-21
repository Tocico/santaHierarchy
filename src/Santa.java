import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Toshiko Kuno
 * Date: 2021/01/21
 * Time: 17:57
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */


public class Santa {
    private List<Worker> workerList;
    private List<String> tempChefList = new ArrayList<>();
    private List<String> tempStaffList = new ArrayList<>();


    public Santa() {
        Path path = Paths.get("src/workers.txt");
        workerList = DAO.getAllWorkers(path);
    }

    public List<String> getChef(String name) {
        String chef = workerList.stream().filter(e -> e.getStaff().stream().anyMatch(staff -> staff.equalsIgnoreCase(name)))
                .findFirst().map(Worker::getName).orElse(null);

        if (chef != null) {
            tempChefList.add(chef);
            getChef(chef);
        }
        return tempChefList.stream().distinct().collect(Collectors.toList());
    }

    public List<String> getStaff(String name) {
        List<String> staff = workerList.stream().filter(e -> e.getName().equalsIgnoreCase(name)).findAny().map(Worker::getStaff).orElse(null);

        if (staff != null) {
            staff.forEach(e -> {
                tempStaffList.add(e);
                getStaff(e);
            });
        }
        return tempStaffList.stream().distinct().collect(Collectors.toList());
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("-----------Santa Hierarchy----------");
            System.out.println("Vilka vill du söka?");
            System.out.println("1: Nisses chefer\n2: Nisses underordnade");

            if (in.hasNextLine()) {
                try {
                    String input = in.nextLine().trim();
                    switch (input) {
                        case "1" -> {
                            String nisse = readNisseToGetChefList(in);
                            List<String> staffList = getChef(nisse);
                            if (staffList.size() != 0) {
                                printList(staffList);
                            } else System.out.println("Vi hittar inte nisses chefer.....");
                        }
                        case "2" -> {
                            String nisse = readNisseToGetStaffList(in);
                            List<String> staffList = getStaff(nisse);
                            if (staffList.size() != 0) {
                                printList(staffList);
                            } else System.out.println("Vi hittar inte nisses underordnade.....");
                        }
                        default -> throw new IllegalArgumentException("Vänligen mata in ett nummer, 1 eller 2");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.exit(0);
            }

        }


    }

    public String readNisseToGetChefList(Scanner in) {
        return getString(in);
    }

    public String readNisseToGetStaffList(Scanner in) {
        return getString(in);
    }

    private String getString(Scanner in) {
        while (true) {
            System.out.println("Ange en nisses namn");
            if (in.hasNextLine()) {
                String input = in.nextLine().trim();
                if (input.length() == 0 || input.isEmpty())
                    throw new NoSuchElementException();
                else return input;
            } else System.exit(0);
        }
    }

    public void printList(List<String> list) {
        list.forEach(e -> System.out.println(e));
        System.out.println();
    }


    public static void main(String[] args) {
        Santa santa = new Santa();
        santa.run();
        List<String> a = santa.getChef("Dammråttan");
        System.out.println(santa.getStaff("Butter"));

    }
}


