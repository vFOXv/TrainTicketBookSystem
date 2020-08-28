package dao;

import model.Passenger;

import java.io.*;
import java.util.ArrayList;

public class PassengerDAO implements GenericDAO<Passenger> {
    String filePath = "src/com/resources/database_test/Passenger.txt";

    @Override
    public Passenger getEntityById(Long id) {
        Passenger passenger = new Passenger();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String read = null;
            while ((read = reader.readLine()) != null) {
                String[] splitedLine = read.split(",");
                Long firstLong = Long.parseLong(splitedLine[2]);

                if (firstLong.equals(id)) {
                    passenger.setFirstNamePassenger(splitedLine[0]);
                    passenger.setSecondNamePassenger(splitedLine[1]);
                    passenger.setIdTicket(firstLong);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    @Override
    public void saveEntity(Passenger passenger) {
        Passenger existPassenger = getEntityById(passenger.getIdTicket());

        if (passenger.getIdTicket().equals(existPassenger.getIdTicket())) {
            System.out.println("Passenger with such id = " + passenger.getIdTicket() + " is already existing");
        } else {
            String passengerToString = passenger.getFirstNamePassenger() + ","
                    + passenger.getSecondNamePassenger() + ","
                    + passenger.getIdTicket() + "\n";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(passengerToString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    //изменение обекта, при этом id должен остоваться неизменым
    public void updateEntity(Passenger entity) {
        removeEntity(entity);
        saveEntity(entity);
    }

    @Override
    public void removeEntity(Passenger passenger) {

        long id = passenger.getIdTicket();

        String[] splitedLine = null;
        //List для хранения списка обьектов пассажиров из файла
        ArrayList<Passenger> list = new ArrayList<>();
        int index = 0;


        //получение списка пассажиров из файла
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String read = null;
            while ((read = reader.readLine()) != null) {
                splitedLine = read.split(",");
                list.add(new Passenger(splitedLine[0], splitedLine[1], Long.parseLong(splitedLine[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //получение индекса удаляемого объекта - сложности для того,что бы этот метод можно было использовать в updateEntity

        //index = list.indexOf(entity);

        for (int i = 0; i < list.size(); i++) {
            if (id == list.get(i).getIdTicket()){
                index = i;
                break;
            }
        }

        //удаление объекта
        list.remove(index);

        //запись List с удалленым объектом обратно в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Passenger value : list) {
                String passengerToString = value.getFirstNamePassenger() + ","
                        + value.getSecondNamePassenger() + ","
                        + value.getIdTicket() + "\n";
                writer.write(passengerToString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
