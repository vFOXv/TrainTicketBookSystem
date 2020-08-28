package view;

import controller.PassengerController;
import model.Passenger;
import model.Ticket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PassengerView {

    PassengerController passengerController = new PassengerController();

    public int choice(){
        Scanner scanner = new Scanner(System.in);
        Integer choice = -1;
        if(scanner.hasNextInt()) {
            choice = scanner.nextInt();
            if(choice< 1 || choice >3){
                System.err.println("Menu has only 3 position!");
            }
        } else {
            System.err.println("You enter not integer number!");
        }
        return choice;
    }

    public void showPassengerMenu() {
        while (true) {
            System.out.println();
            System.out.println("Passenger");
            System.out.println("1 - Create Passenger");
            System.out.println("2 - Find a Passenger");
            System.out.println("3 - View all Passenger");
            switch (choice()) {
                case 1:
                    createPassengerMenu();
                    break;
                case 2:
                    findPassenger();
                    break;
                case 3:
                    viewAllPassenger();
                    break;
            }
        }
        }

        private void createPassengerMenu(){
            Passenger passenger = new Passenger();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Crete Passenger");

            System.out.println("Enter passenger's first name ");
            while (passenger.getFirstNamePassenger() == null || passenger.getFirstNamePassenger().isBlank()) {
                passenger.setFirstNamePassenger(scanner.nextLine());
            }

            System.out.println("Enter passenger's second name ");
            while (passenger.getSecondNamePassenger() == null || passenger.getSecondNamePassenger().isBlank()) {
                passenger.setSecondNamePassenger(scanner.nextLine());
            }

//        while (passenger.getIdTicket() == null) {
//            System.out.println("ticket id");
//            Long ticketId = scanner.nextLong();
//            if (true) {//мы должны проверить что билет с таким id есть в базе ticketController.getEntityById(ticketId).getId();
//                System.out.println("Билет с таким id не существует");
//            } else {
//                passenger.setIdTicket(ticketId);
//            }
//        }

            //получение значения id билета из ticket для поля passenger.idTicket
            passenger.setIdTicket(Ticket.getId());

            //сохраняет нового пасажира только если его passenger.idTicket уникальный
            passengerController.save(passenger);
        }

        public void findPassenger () {
            Passenger passenger = null;
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter id ticket");
            try {
                //       if(scanner.hasNextLong()) {
                long idTicket = scan.nextLong();
                passenger = passengerController.getById(idTicket);
                if (passenger.getIdTicket() != null) {
                    System.out.println("This passenger find :" + passenger.toString());
                } else {
                    System.err.println("This passenger isn't be!!!");
                }
            } catch (InputMismatchException e) {
                System.err.println("You enter not long number!");

            }
//        }else{
//            System.err.println("You enter not long number!");
//        }
        }

        public void viewAllPassenger () {
            //получение списка пассажиров из файла
            ArrayList<Passenger> list = new ArrayList<>();
            String filePath = "src/com/resources/database_test/Passenger.txt";

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String read = null;
                while ((read = reader.readLine()) != null) {
                    String[] splitedLine = read.split(",");
                    list.add(new Passenger(splitedLine[0], splitedLine[1], Long.parseLong(splitedLine[2])));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Passenger passenger : list) {
                System.out.println(passenger.toString());
            }
        }

    }
