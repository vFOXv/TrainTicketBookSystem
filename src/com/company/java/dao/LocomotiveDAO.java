package dao;

import model.Locomotive;
import model.Passenger;

import java.io.*;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;


//        Реализовать методы из интерфейса GenericDAO. Данные должны хранится
//        в файле который расположен в дирректории "com.resources.database_test"
//        (для каждой сущности создается свой файл прим. locomotive.txt).
//        Пример метода getEntityById смотрите в классе LocomotiveDAO.


public class LocomotiveDAO implements GenericDAO<Locomotive> {

    String filePath = "src/com/resources/database_test/locomotive.txt";

    @Override
    public Locomotive getEntityById(Long id) {
        Locomotive locomotive = new Locomotive();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String read;
            while ((read = reader.readLine()) != null) {
                String[] splitedLine = read.split(",");

                Long firstLong = Long.parseLong(splitedLine[0]);

                if (firstLong.equals(id)) {
                    locomotive.setId(firstLong);
                    locomotive.setName(splitedLine[1]);
                    locomotive.setCapacityLocomotive(Integer.parseInt(splitedLine[2]));
                    locomotive.setPowerLocomotive(Integer.parseInt(splitedLine[3]));
                    locomotive.setYearIssueLocomotive(Integer.parseInt(splitedLine[4]));
                    locomotive.setFuelType(splitedLine[5]);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return locomotive;
    }


    @Override
    public void saveEntity(Locomotive locomotive) {

        Locomotive existLocomotive = getEntityById(locomotive.getId());

        if (locomotive.getId().equals(existLocomotive.getId())) {
            System.out.println("locomotive " + existLocomotive.getId() + " exist");
        } else {

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {


                // форматируем параметры нашего объекта в массив и по элементно записываем в наш файл


                String[] train = new String[]{String.valueOf(locomotive.getId()), locomotive.getName(),
                        String.valueOf(locomotive.getCapacityLocomotive()), String.valueOf(locomotive.getPowerLocomotive()),
                        String.valueOf(locomotive.getYearIssueLocomotive()), String.valueOf(locomotive.getFuelType())};
                for (int i = 0; i < train.length; i++) {
                    if (i != train.length - 1) {
                        bw.write(train[i]);
                        bw.write(",");
                    } else {
                        bw.write(train[i] + "\n");
                    }
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    @Override
    public void updateEntity(Locomotive entity) {

        // строки файла как отдельные объекты записываем в ArrayList


        List<Locomotive> locomotiveList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String read;
            while ((read = br.readLine()) != null) {
                String[] splitedLine = read.split(",");
                Locomotive locomotive = new Locomotive();
                locomotive.setId(Long.parseLong(splitedLine[0]));
                locomotive.setName(splitedLine[1]);
                locomotive.setCapacityLocomotive(Integer.parseInt(splitedLine[2]));
                locomotive.setPowerLocomotive(Integer.parseInt(splitedLine[3]));
                locomotive.setYearIssueLocomotive(Integer.parseInt(splitedLine[4]));
                locomotive.setFuelType(splitedLine[5]);
                locomotiveList.add(locomotive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // находим из списка объектов тот, который соответствует нашему, переданному методу в параметрах

        Locomotive locomotive = locomotiveList.stream()
                .filter(loc -> loc.getId().equals(entity.getId()))
                .collect(Collectors.toList()).get(0);

        // удаляем объект, переданного в качестве параметра методу, из списка объектов из файла

        while (locomotiveList.contains(locomotive)) {
            locomotiveList.remove(locomotive);
        }
        // записываем объект с измененными параметрами в список

        locomotiveList.add(entity);

        // Стираем данные в файле и перезаписываем заново с учетом внесенных изменений в объектах Locomotive

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        locomotiveList.forEach(this::saveEntity);
    }

    @Override
    public void removeEntity(Locomotive entity) {


        // выполняем проверку наличия в нашем файле объекта, идентичного объекту, переданному метоу в параметрах

        List<Locomotive> locomotiveList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String read;
            while ((read = br.readLine()) != null) {
                String[] splitedLine = read.split(",");
                Locomotive locomotive = new Locomotive();
                locomotive.setId(Long.parseLong(splitedLine[0]));
                locomotive.setName(splitedLine[1]);
                locomotive.setCapacityLocomotive(Integer.parseInt(splitedLine[2]));
                locomotive.setPowerLocomotive(Integer.parseInt(splitedLine[3]));
                locomotive.setYearIssueLocomotive(Integer.parseInt(splitedLine[4]));
                locomotive.setFuelType(splitedLine[5]);
                locomotiveList.add(locomotive);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        // находим из списка объектов тот, который соответствует нашему, переданному метоу в параметрах

        Locomotive locomotive = locomotiveList.stream().filter(loc -> loc.getId().equals(entity.getId()))
                .collect(Collectors.toList()).get(0);
        // проводим удаление объекта, идентичного объекту, переданному метоу в параметрах

        locomotiveList.remove(locomotive);

        // Стираем данные в файле и перезаписываем заново с учетом внесенных изменений в объектах Locomotive

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        locomotiveList.forEach(this::saveEntity);

    }
}
// Метод возвращающий объект сканера

//    public Scanner scanner(){
//        return new Scanner(System.in);
//    }

// Метод, который заполняет поле ID поезда, если при создании обхекта поезда он не был задан

//    public void setItemID(Locomotive entity){
//        while (true){
//            Scanner scanner = scanner();
//            System.out.println("Задайте ID поезда :");
//            if(scanner.hasNextLong()){
//                entity.setId(scanner.nextLong());
//                System.out.println("ID поезда = " + entity.getId());
//                break;
//            } else {
//                System.out.println("Не верно. Попробуйте снова");
//            }
//        }
//    }

// Метод, который заполняет поле Name поезда, если при создании объекта поезда оно не было задано

//    public void setItemName(Locomotive entity){
//        while (true){
//            Scanner scanner = scanner();
//            System.out.println("Задайте НАЗВАНИЕ поезда :");
//            if(scanner.hasNextLine()){
//                entity.setName(scanner.nextLine());
//                System.out.println("Название поезда = " + entity.getName());
//                break;
//            } else {
//                System.out.println("Не верно. Попробуйте снова");
//            }
//        }
//    }

// Метод, который проводит проверку записей в файле на идентичность объекту, переданному методу в качестве
// параметра. Если этот объект есть в файле, то он выдает список всех объектов из файла, если нет - возвращает
// null

//    public List<Locomotive> chekID(Locomotive entity){
//        List<Locomotive> locomotiveList= new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
//            String read;
//            while ((read = br.readLine()) != null)  {
//                String[] splitedFile = read.split("/");
//                for (String line : splitedFile) {
//                    String[] splitedLine = line.split(",");
//                    Locomotive locomotive = new Locomotive();
//                    locomotive.setId(Long.parseLong(splitedLine[0]));
//                    locomotive.setName(splitedLine[1]);
//                    locomotive.setCapacityLocomotive(Integer.parseInt(splitedLine[2]));
//                    locomotive.setPowerLocomotive(Integer.parseInt(splitedLine[3]));
//                    locomotive.setYearIssueLocomotive(Integer.parseInt(splitedLine[4]));
//                    locomotive.setFuelType(splitedLine[5]);
//                    locomotiveList.add(locomotive);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if(locomotiveList.stream().anyMatch(loc -> loc.getId().equals(entity.getId()))){
//            return locomotiveList;
//        } else {
//            return null;
//        }
//    }


//class Run {
//    public static void main(String[] args) {
//        LocomotiveDAO locomotiveDAO = new LocomotiveDAO();
//
//        Locomotive locomotive = new Locomotive(1L, "first", 10, 120, 1978, Locomotive.FuelType.DIESEL);
//        //locomotive.setId(1L);
//        //locomotive.setName("train1");
//
//        locomotiveDAO.saveEntity(locomotive);
//
//        Locomotive locomotive1 = new Locomotive(2L, "second", 10, 120, 1978, Locomotive.FuelType.DIESEL);
//        //locomotive1.setId(2L);
//        //locomotive1.setName("train2");
//
//        locomotiveDAO.saveEntity(locomotive1);
//
//        System.out.println(locomotiveDAO.getEntityById(1L).toString());
//        System.out.println(locomotiveDAO.getEntityById(2L).toString());
//
//        //locomotiveDAO.removeEntity(locomotive1);
//
//        //locomotive.setName("train4");
//
//        //System.out.println(locomotive.toString());
//
//        //locomotiveDAO.updateEntity(locomotive);
//
//}
//}