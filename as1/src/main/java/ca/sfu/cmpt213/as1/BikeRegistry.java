package ca.sfu.cmpt213.as1;

import java.io.*;
import java.util.*;
import ca.sfu.cmpt213.as1.Bike;

public class BikeRegistry {

    private static Integer displayMainMenu() {

        Scanner optionIn = new Scanner(System.in);
        String takeIn;

        System.out.println("*************\n* Main Menu *\n*************");
        System.out.print(
                "1. List Bikes\n2. Add a new Bike\n3. Remove a Bike\n4. Change Bike attribute\n5. DEBUG: Dump objects (toString)\n6. Exit\n> ");

        takeIn = optionIn.next();
        while (takeIn.length() != 1 || takeIn.charAt(0) < '1' || takeIn.charAt(0) > '6') {
            System.out.print("Invalid input. Enter a number between 1 and 6!\n> ");
            takeIn = optionIn.next();
        }

        return Integer.parseInt(takeIn);

    }

    private static void displayAllBikes(List<Bike> bikeList) {

        if (bikeList.size() == 0) {
            System.out.println("No bikes registered!\n");
            return;
        }

        Comparator<Bike> compID = Comparator.comparingInt(bike -> bike.getID());
        PriorityQueue<Bike> sortByID = new PriorityQueue<>(compID);
        Bike bike;

        sortByID.addAll(bikeList);

        System.out.println("\n******************\n* List of Bikes: *\n******************");
        System.out.printf("%-3s %-22s %-10s %-11s %-8s %-10s%n", "ID", "Owner,", "Type,", "Serial,", "Brake,",
                "Wheel Size");

        for (int i = 0; i < bikeList.size(); i++) {

            bike = sortByID.poll();

            System.out.printf("%-3d %-22s %-10s %-11s %-8s %-10d%n", bike.getID(), bike.getOwner(), bike.getBikeType(),
                    bike.getSerialNumber(), bike.getBrakeType(), bike.getWheelSize());
        }

        System.out.print("\n");

    }

    private static void addNewBike(List<Bike> bikeList) {

        Scanner optionIn = new Scanner(System.in);
        Bike newBike = new Bike();
        putID(bikeList, newBike);

        System.out.print("Enter Bike owner name: ");
        newBike.setOwner(optionIn.nextLine());

        System.out.print("Enter Bike type: ");
        newBike.setBikeType(optionIn.next());

        System.out.print("Enter Bike's serial number: ");
        newBike.setSerialNumber(optionIn.next());

        System.out.print("Enter Bike's brake type: ");
        newBike.setBrakeType(validBrake());

        System.out.print("Enter Bike's wheel size: ");
        newBike.setWheelSize(validWheelSize());

        bikeList.add(newBike);

        System.out.print("\n");

    }

    private static void deleteBike(List<Bike> bikeList) {

        if (bikeList.size() == 0) {
            System.out.println("No bikes registered!\n");
            return;
        }

        displayAllBikes(bikeList);

        int choose = -1;
        Scanner remIn = new Scanner(System.in);

        System.out.print("Choose a Bike to remove: ");
        while (true) {
            try {
                choose = remIn.nextInt();
                if (choose < 0 || choose > bikeList.size())
                    System.out.print("Invalid input! Choose an existing Bike ID to remove: ");
                else
                    break;
            } catch (InputMismatchException e) {
                choose = -1;
                System.out.print("Invalid input! Choose an existing Bike ID to remove: ");
                remIn.nextLine();
            }
        }

        if (choose == 0)
            return;
        for (int i = 0; i < bikeList.size(); i++) {
            if (choose == bikeList.get(i).getID()) {
                bikeList.remove(i);
                System.out.println("Bike successfully deleted!\n");
                return;
            }
        }

    }

    private static void alterBike(List<Bike> bikeList) {

        if (bikeList.size() == 0) {
            System.out.println("No bikes registered!\n");
            return;
        }

        displayAllBikes(bikeList);

        System.out.print("Choose a Bike by ID to Alter (0 to exit):\n> ");

        Scanner optionIn = new Scanner(System.in);
        int takeInt;

        while (true) {
            try {
                takeInt = optionIn.nextInt();

                if (takeInt < 0 || takeInt > bikeList.size())
                    System.out.print("Invalid input! Choose an existing Bike ID to remove: ");
                else
                    break;

            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Choose an existing Bike ID to remove: ");
                optionIn.nextLine();
            }
        }

        if (takeInt == 0)
            return;

        int index = -1;
        String takeIn = "";
        String[] attTypes = { "Owner", "owner", "Serial", "serial", "Brake", "brake", "Bike", "bike", "Wheel",
                "wheel", "Exit", "exit" };
        ArrayList<String> attCheck = new ArrayList<>(Arrays.asList(attTypes));

        for (int i = 0; i < bikeList.size(); i++) {
            if (bikeList.get(i).getID() == takeInt) {
                index = i;
                break;
            }
        }

        System.out.print("Which Attribute ('Owner', 'Bike', 'Serial', 'Brake', 'Wheel', 'Exit' to cancel):\n> ");

        while (true) {
            takeIn = optionIn.next();
            if (!attCheck.contains(takeIn))
                System.out.print("Invalid input! Type 'Owner', 'Bike', 'Serial', 'Brake', 'Wheel' ('Exit' to exit): ");
            else
                break;
        }

        if (takeIn == "Exit" || takeIn == "exit")
            return;
        if (takeIn == "Owner" || takeIn == "owner") {
            System.out.print("Enter new Bike owner name: ");
            bikeList.get(index).setOwner(optionIn.nextLine());
        } else if (takeIn == "Bike" || takeIn == "bike") {
            System.out.print("Enter new Bike type: ");
            bikeList.get(index).setOwner(optionIn.nextLine());
        } else if (takeIn == "serial" || takeIn == "Serial") {
            System.out.print("Enter new Bike's serial number: ");
            bikeList.get(index).setSerialNumber(optionIn.next());
        } else if (takeIn == "brake" || takeIn == "brake") {
            System.out.print("Enter new Bike's brake type: ");
            bikeList.get(index).setBrakeType(validBrake());
        } else {
            System.out.print("Enter new Bike's wheel size: ");
            bikeList.get(index).setWheelSize(validWheelSize());
        }

        System.out.println("Modified successfully!\n");

    }

    private static void displayDumpObjects(List<Bike> bikeList) {

        if (bikeList.size() == 0) {
            System.out.println("No bikes registered!\n");
            return;
        }

        Comparator<Bike> compID = Comparator.comparingInt(bike -> bike.getID());
        PriorityQueue<Bike> sortByID = new PriorityQueue<>(compID);
        sortByID.addAll(bikeList);

        System.out.println("All Bike Objects:");

        for (int i = 0; i < bikeList.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(sortByID.poll().toString());
        }

        System.out.print("\n");

    }

    public static void main(String[] args) {

        System.out.println(
                "***************************************************\n* Bike Registry by Damir Zharikessov sn 301541028 *\n***************************************************\n");

        int option;
        List<Bike> bikeList = new ArrayList<>();

        while (true) {

            option = displayMainMenu();
            if (option == 6)
                break;
            if (option == 1)
                displayAllBikes(bikeList);
            else if (option == 2)
                addNewBike(bikeList);
            else if (option == 3)
                deleteBike(bikeList);
            else if (option == 4)
                alterBike(bikeList);
            else
                displayDumpObjects(bikeList);
        }

        System.out.println("BYE!");

        return;
    }

    // HELPER FUNCTIONS
    private static void putID(List<Bike> bikeList, Bike newBike) {
        if (newBike.getID() != 0)
            return;

        int id = 1;
        int check;
        Comparator<Bike> compID = Comparator.comparingInt(bike -> bike.getID());
        PriorityQueue<Bike> sortByID = new PriorityQueue<>(compID);
        sortByID.addAll(bikeList);
        for (int i = 0; i < bikeList.size(); i++) {
            check = sortByID.poll().getID();
            if (check != id)
                break;
            id++;
        }
        newBike.setID(id);
    }

    private static String validBrake() {

        String[] brakeTypes = { "Disc", "disc", "Rim", "rim", "Drum", "drum" };
        ArrayList<String> brakeCheck = new ArrayList<>(Arrays.asList(brakeTypes));
        Scanner optionIn = new Scanner(System.in);
        String ans;

        ans = optionIn.next();
        while (!brakeCheck.contains(ans)) {
            System.out.println("Invalid input. Options: Disc, Rim, Drum");
            System.out.print("Enter Bike's brake type: ");
            ans = optionIn.next();
        }

        if (ans.equals("disc"))
            ans = "Disc";
        else if (ans.equals("drum"))
            ans = "Drum";
        else if (ans.equals("rim"))
            ans = "Rim";

        return ans;

    }

    private static int validWheelSize() {

        Scanner optionIn = new Scanner(System.in);
        int ans;

        while (true) {

            try {
                ans = optionIn.nextInt();
                if (ans >= 15 && ans <= 30)
                    return ans;
                else
                    System.out.print("Invalid input. Choose a size between 15 and 50: ");
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Choose a size between 15 and 50: ");
                optionIn.nextLine();
            }

        }

    }
}
