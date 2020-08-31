package dao;

import model.PassengerWagoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


/*
Реализовать методы из интерфейса GenericDAO.
Данные должны хранится в файле который расположен в
дирректории "com.resources.database_test"
 */

public class PassengerWagoonDAO implements GenericDAO<PassengerWagoon> {

    // создание объекта и путь к файлу
    String filePath = "src/com/resources/database_test/passenger_wagoon.txt";

// получиние идентификационного номера объекта

    @Override
    public PassengerWagoon getEntityById(Long id) {

        // создание объекта passengerWagoon
        PassengerWagoon passengerWagoon = new PassengerWagoon();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String read;
            while ((read = reader.readLine()) != null) {
                String[] splitedLine = read.split(",");

                Long firstLong = Long.parseLong(splitedLine[0]);

                if (firstLong.equals(id)) {
                    passengerWagoon.setId(firstLong);
                    passengerWagoon.setTypeWagoon(PassengerWagoon.TypeWagoon.valueOf(splitedLine[1]));
                    passengerWagoon.setNumberWagoon(Integer.parseInt(splitedLine[2]));
                    passengerWagoon.setAmountSeats(Integer.parseInt(splitedLine[3]));
                    passengerWagoon.setNumberSeats(Integer.parseInt(splitedLine[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passengerWagoon;
    }

    // сохранение объекта в файл (аналог базы данных)
    @Override
    public void saveEntity(PassengerWagoon passengerWagoon) throws IOException {
        PassengerWagoon existPassengerWagoon = getEntityById(passengerWagoon.getId());

        if (passengerWagoon.getId().equals(existPassengerWagoon.getId())) {
            System.out.println("Passenger Wagoon " + existPassengerWagoon.getId() + " exit");
        } else {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {

                // передаем параметры объекта в массив и записываем по элементно в файл

                String[] train = new String[]{
                        String.valueOf(passengerWagoon.getId()),
                        String.valueOf(passengerWagoon.getTypeWagoon()),
                        String.valueOf(passengerWagoon.getNumberWagoon()),
                        String.valueOf(passengerWagoon.getAmountSeats()),
                        String.valueOf(passengerWagoon.getNumberSeats())
                };

                for (int i = 0; i < train.length; i++) {
                    if (i != train.length - 1) {
                        bufferedWriter.write(train[i]);
                        bufferedWriter.write(",");
                    } else {
                        bufferedWriter.write(train[i] + "\n");
                    }
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    // изменение объекта
    @Override
    public void updateEntity(PassengerWagoon entity) {

        // строки файла записываются в список (ArrayList) как отдельные объекты
        List<PassengerWagoon> passengerWagoonsList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                String[] splitedLine = read.split(",");
                PassengerWagoon passengerWagoon = new PassengerWagoon();
                passengerWagoon.setId(Long.parseLong(splitedLine[0]));
                passengerWagoon.setTypeWagoon(PassengerWagoon.TypeWagoon.valueOf(splitedLine[1]));
                passengerWagoon.setNumberWagoon(Integer.parseInt(splitedLine[2]));
                passengerWagoon.setAmountSeats(Integer.parseInt(splitedLine[3]));
                passengerWagoon.setNumberSeats(Integer.parseInt(splitedLine[4]));
                passengerWagoonsList.add(passengerWagoon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //В списке находим объект, соответствующий объекту переданному в параметрах
        PassengerWagoon passengerWagoon = passengerWagoonsList.stream()
                .filter(passengerWagoon1 -> passengerWagoon1.getId().equals(entity.getId()))
                .collect(Collectors.toList()).get(0);

        // удаление переданного в качестве параметра методу из списка объектов в файле
        while (passengerWagoonsList.contains(passengerWagoon)) {
            passengerWagoonsList.remove(passengerWagoon);
        }

        //запись объекта с измененными параметрами в список
        passengerWagoonsList.add(entity);

        //Перезапись данных в файл с учетом изменений в объектах Passenger Wagoon
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        passengerWagoonsList.forEach(passengerWagoon1 -> {
            try {
                saveEntity(passengerWagoon1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }); //???????????????????????????????//
    }


    // удаление объекта из списка
    @Override
    public void removeEntity(PassengerWagoon entity) {
        List<PassengerWagoon> passengerWagoonList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String read;
            while ((read = bufferedReader.readLine()) != null) {
                String[] splitedLine = read.split(",");
                PassengerWagoon passengerWagoon = new PassengerWagoon();
                passengerWagoon.setId(Long.parseLong(splitedLine[0]));
                passengerWagoon.setTypeWagoon(PassengerWagoon.TypeWagoon.valueOf(splitedLine[1]));
                passengerWagoon.setNumberWagoon(Integer.parseInt(splitedLine[2]));
                passengerWagoon.setAmountSeats(Integer.parseInt(splitedLine[3]));
                passengerWagoon.setNumberSeats(Integer.parseInt(splitedLine[4]));
                passengerWagoonList.add(passengerWagoon);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        PassengerWagoon passengerWagoon = passengerWagoonList
                .stream().filter(passengerWagoon1 -> passengerWagoon1.getId().equals(entity.getId()))
                .collect(Collectors.toList()).get(0);

        //удаление выбранного объекта
        passengerWagoonList.remove(passengerWagoon);

        //перезаписываем данные в файл
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        passengerWagoonList.forEach(passengerWagoon1 -> {
            try {
                saveEntity(passengerWagoon1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
