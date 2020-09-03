package dao;

import model.Passenger;
import model.PassengerWagoon;
import model.Ticket;


import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TicketDAO implements GenericDAO<Ticket> {
    String filePath = "src/com/resources/database_test/Ticket.txt";

    @Override
    public Ticket getEntityById(Long id) {
        Ticket ticket = new Ticket();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String read = null;
            while ((read = reader.readLine()) != null) {
                String[] splitedLine = read.split(",");
                Long firstLong = Long.parseLong(splitedLine[0]);

                if (firstLong.equals(id)) {
                    ticket.setId(firstLong);
                    //привод String к enum
                    ticket.setTypeWagoon(PassengerWagoon.TypeWagoon.valueOf(splitedLine[1]));

                    //получение даты прибытия
                 DateFormat arrive = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);
                    String dateInStringArrive = splitedLine[2];
                    try {
                        ticket.setDateArrival(arrive.parse(dateInStringArrive));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //получения даты отправления
                    DateFormat departure = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH);
                    String dateInStringDeparture = splitedLine[3];
                    try {
                        ticket.setDateArrival(departure.parse(dateInStringDeparture));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    //получение постели
                    ticket.setBed(splitedLine[4].equals("true"));

                    //получение напитков
                    ticket.setDrink(Ticket.TeaCoffee.valueOf(splitedLine[5]));

                    //получение багажа
                    ticket.setBaggage(Ticket.Luggage.valueOf(splitedLine[6]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public void saveEntity(Ticket ticket) {
        Ticket existTicket = getEntityById(ticket.getId());

//        if (ticket.getId().equals(existTicket.getId()))

        if (false/*!existTicket.equals(null)*/) {        // не могу заставить работать equals как в PassengerDAO ?????
            System.out.println("Passenger with such id = " + ticket.getId() + " is already existing");
        } else {
            String passengerToString = ticket.getId() + ","
                    + ticket.getTypeWagoon() + ","
                    + ticket.getDateArrival() + ","
                    + ticket.getDateDeparture() + ","
                    + ticket.getBed() + ","
                    + ticket.getDrink() + ","
                    + ticket.getBaggage() + "\n";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(passengerToString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateEntity(Ticket ticket) {
        removeEntity(ticket);
        saveEntity(ticket);
    }

    @Override
    public void removeEntity(Ticket ticket) {
        long id = ticket.getId();

        String[] splitedLine = null;
        //List для хранения списка обьектов ticket из файла
        ArrayList<Ticket> list = new ArrayList<>();
        int index = 0;


        //получение списка билетов из файла
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String read = null;
            Date arriveBox = null;
            Date departureBox = null;

            while ((read = reader.readLine()) != null) {
                splitedLine = read.split(",");

                //получение даты прибытия
                SimpleDateFormat arrive = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                String dateInStringArrive = splitedLine[2];
                try {
                    arriveBox = arrive.parse(dateInStringArrive);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //получения даты отправления
                SimpleDateFormat departure = new SimpleDateFormat("dd.MM.yyyy HH:mm");
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

        //получение индекса удаляемого объекта - сложности для того,что бы этот метод можно было использовать в updateEntity

        //index = list.indexOf(entity);

        for (int i = 0; i < list.size(); i++) {
            if (id == list.get(i).getId()) {
                index = i;
                break;
            }
        }

        //удаление объекта
        list.remove(index);

        //запись List с удалленым объектом обратно в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Ticket ticket1 : list) {
                String passengerToString = ticket1.getId() + ","
                        + ticket1.getTypeWagoon() + ","
                        + ticket1.getDateArrival() + ","
                        + ticket1.getDateDeparture() + ","
                        + ticket1.getBed() + ","
                        + ticket1.getDrink() + ","
                        + ticket1.getBaggage() + "\n";

                writer.write(passengerToString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
