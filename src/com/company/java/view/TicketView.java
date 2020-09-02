package view;

import controller.TicketController;
import model.Passenger;
import model.PassengerWagoon;
import model.Ticket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketView {

    TicketController ticketController = new TicketController();
    String filePath = "src/com/resources/database_test/Ticket.txt";

    public int choice() {
        Scanner scanner = new Scanner(System.in);
        Integer choice = -1;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            if (choice < 1 || choice > 3) {
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
                    createTicket();
                    break;
                case 2:
                    findTicket();
                    break;
                case 3:
                    viewAllPassenger();
                    break;
            }
        }
    }

    private long createIdTicket() {
        //List для хранения списка обьектов ticket из файла
        ArrayList<Long> list = new ArrayList<>();
        String[] splitedLine = null;

        //получение списка id билетов из файла
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String read = null;

            while ((read = reader.readLine()) != null) {
                splitedLine = read.split(",");
                list.add(Long.parseLong(splitedLine[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long max = list.stream().max(Long::compareTo)
                .get();
        return (max + 1);
    }

    private void createTicket() {
        Ticket ticket = new Ticket();
        Scanner scanner = new Scanner(System.in);

        //задание id билету
        ticket.setId(createIdTicket());

        System.out.println("Crete Ticket");

        //задание типа вагона
        System.out.println("Enter type wagoon: 1 - PLATSKART/ 2 - KYPE/ 3 - SW");
        PassengerWagoon.TypeWagoon typeWagoon = null;
        if (scanner.hasNextInt()) {
            int box = scanner.nextInt();
            if (box > 0 && box < 4) {
                if (box == 1) {
                    ticket.setTypeWagoon(PassengerWagoon.TypeWagoon.PLATSKART);
                } else if (box == 2) {
                    ticket.setTypeWagoon(PassengerWagoon.TypeWagoon.KYPE);
                } else if (box == 3) {
                    ticket.setTypeWagoon(PassengerWagoon.TypeWagoon.CW);
                }
            } else {
                System.err.println("You must enter number 1/2/3!");
            }
        } else {
            System.err.println("You enter not integer number!");
        }

        //задание даты прибытия
        System.out.println("Enter date arrive:  dd/MM/yyyy in H:mm");
        SimpleDateFormat arrive = new SimpleDateFormat("dd/MM/yyyy in H:mm");
        String dateInStringArrive = scanner.nextLine();
        try {
            ticket.setDateArrival(arrive.parse(dateInStringArrive));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //задание даты отправления
        System.out.println("Enter date departure:  dd/MM/yyyy in H:mm");
        SimpleDateFormat departure = new SimpleDateFormat("dd/MM/yyyy in H:mm");
        String dateInStringDeparture = scanner.nextLine();
        try {
            ticket.setDateDeparture(departure.parse(dateInStringDeparture));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //задание постели
        System.out.println("Enter 1 if bed is, and press any key if bed isn't.");
        boolean bed = false;
        if (scanner.hasNextInt()) {
            int box = scanner.nextInt();
            if (box == 1) {
                ticket.setBed(true);
            } else {
                ticket.setBed(false);
            }
        } else {
            ticket.setBed(false);
        }

        //получение напитков
        System.out.println("Enter drink: 1 - NO_DRINKS/2 - TEA/3 - COFFEE");
        Ticket.TeaCoffee drink = null;
        if (scanner.hasNextInt()) {
            int box = scanner.nextInt();
            if (box > 0 && box < 4) {
                switch (box) {
                    case 1:
                        ticket.setDrink(Ticket.TeaCoffee.NO_DRINKS);
                        break;
                    case 2:
                        ticket.setDrink(Ticket.TeaCoffee.TEA);
                        break;
                    case 3:
                        ticket.setDrink(Ticket.TeaCoffee.COFFEE);
                        break;
                }
            }
        }

        //получние багажа
        System.out.println("Enter luggage");
        System.out.println("1 - NO_LUGGAGE(Ручная кладь до 5 кг)");
        System.out.println("2 - LIGHT_LUGGAGE(Легкая кладь от 5 до 10 кг)");
        System.out.println("3 - MEDIUM_LUGGAGE(Средняя кладь от 10 до 20 кг)");
        System.out.println("4 - HEAVY_LUGGAGE(Тяжелая кладь от 20 кг)");

        if (scanner.hasNextInt()) {
            int box = scanner.nextInt();
            if (box > 0 && box < 5) {
                switch (box) {
                    case 1:
                        ticket.setBaggage(Ticket.Luggage.NO_LUGGAGE);
                        break;
                    case 2:
                        ticket.setBaggage(Ticket.Luggage.LIGHT_LUGGAGE);
                        break;
                    case 3:
                        ticket.setBaggage(Ticket.Luggage.MEDIUM_LUGGAGE);
                        break;
                    case 4:
                        ticket.setBaggage(Ticket.Luggage.HEAVY_LUGGAGE);
                        break;
                }
            } else {
                System.err.println("You must enter number 1/2/3/4!");
            }
        } else {
            System.err.println("You enter not integer number!");
        }

        //сохранение нового билета в базу данных
        ticketController.save(ticket);
    }

    public void findTicket () {
        Ticket ticket = null;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter id ticket");
        try {
            long id = scan.nextLong();
            ticket = ticketController.getById(id);
            //if (ticket.getId() != null) {    // не работает, почему???
            if (ticket != null){
                System.out.println("This ticket find :" + ticket.toString());
            } else {
                System.err.println("This ticket isn't be!!!");
            }
        } catch (InputMismatchException e) {
            System.err.println("You enter not long number!");

        }
    }

    public void viewAllPassenger () {
        String[] splitedLine = null;
        //List для хранения списка обьектов ticket из файла
        ArrayList<Ticket> list = new ArrayList<>();

        //получение списка билетов из файла
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String read = null;
            Date arriveBox = null;
            Date departureBox = null;

            while ((read = reader.readLine()) != null) {
                splitedLine = read.split(",");

                //получение даты прибытия
                SimpleDateFormat arrive = new SimpleDateFormat("dd/MM/yyyy in H:mm");
                String dateInStringArrive = splitedLine[2];
                try {
                    arriveBox = arrive.parse(dateInStringArrive);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //получения даты отправления
                SimpleDateFormat departure = new SimpleDateFormat("dd/MM/yyyy in H:mm");
                String dateInStringDeparture = splitedLine[3];
                try {
                    departureBox = departure.parse(dateInStringDeparture);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //получение постели
                boolean bedBox = splitedLine[4].equals("true");

                list.add(new Ticket(Long.parseLong(splitedLine[0]), PassengerWagoon.TypeWagoon.valueOf(splitedLine[1]), arriveBox, departureBox, bedBox, Ticket.TeaCoffee.valueOf(splitedLine[5]), Ticket.Luggage.valueOf(splitedLine[6])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Ticket ticket : list) {
            System.out.println(ticket.toString());
        }
    }
}
