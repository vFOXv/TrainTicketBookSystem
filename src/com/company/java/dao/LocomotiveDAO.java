package dao;

import model.Locomotive;
import model.Passenger;

import java.io.*;
import java.util.ArrayList;

public class LocomotiveDAO implements GenericDAO<Locomotive> {
    String filePath = "src/com/resources/database_test/locomotive.txt";

    @Override
    public Locomotive getEntityById(Long id) {
        Locomotive locomotive = new Locomotive();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String read = null;
            while ((read = reader.readLine()) != null) {
                //String[] splitedFile = read.split("/");
                //for (String line : read) {
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
            System.out.println("Locomotive with such id = " + locomotive.getId() + " is already existing");
        } else {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                StringBuffer existFiles = new StringBuffer();
                String read = null;
                while ((read = bufferedReader.readLine()) != null) {
                    existFiles.append(read + "\n");
                }
                //String[] fileDataList = existFiles.toString().split("/");

                String str = existFiles.toString();
                System.out.println(str);
                String[] split = str.split("\n");



                String locomotiveToString = locomotive.getId() + ","
                        + locomotive.getName() + ","
                        + locomotive.getCapacityLocomotive() + ","
                        + locomotive.getPowerLocomotive() + ","
                        + locomotive.getYearIssueLocomotive() + ","
                        + locomotive.getFuelType() + "\n";
//                String newFile = "";
//                if (existFiles.toString().equals("")) {
//                    newFile = existFiles.append(locomotiveToString).toString();
//                } else {
//                    for (String s : fileDataList) {
//                        newFile += s;
//                        newFile += "/";
//                        newFile += "\n";
//
//                    }
//                    newFile += locomotiveToString;
//                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                    writer.write(locomotiveToString);
                } catch (IOException e) {
                    e.printStackTrace();

                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    @Override
    public void updateEntity(Locomotive locomotive) {
        removeEntity(locomotive);
        saveEntity(locomotive);
    }

    @Override
    public void removeEntity(Locomotive locomotive) {
//        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
//            String read = null;
//            while((read = reader.readLine()) != null){
//                String[]splitedLine = read.split(",");
//                Long idLong  = Long.parseLong(splitedLine[0]);
//
//                if(idLong.equals(locomotive.getId())){
//                    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))){
//                        bufferedWriter.write("");
//                    }
//                }
//            }
//        }catch(IOException e){
//            e.printStackTrace();
//        }
        long id = locomotive.getId();
        String[] splitedLine = null;
        //List для хранения списка обьектов пассажиров из файла
        ArrayList<Locomotive> list = new ArrayList<>();
        int index = 0;
        //получение списка пассажиров из файла
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String read = null;
            while ((read = reader.readLine()) != null) {
                splitedLine = read.split(",");
                Locomotive locomotiveToString = new Locomotive(Long.parseLong(splitedLine[0]),
                        splitedLine[1],
                        Integer.parseInt(splitedLine[2]),
                        Integer.parseInt(splitedLine[3]),
                        Integer.parseInt(splitedLine[4]),
                        splitedLine[5]);
                list.add(locomotiveToString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //получение индекса удаляемого объекта - сложности для того,
        // что бы этот метод можно было использовать в updateEntity

        //index = list.indexOf(locomotive.toString());

        for (int i = 0; i < list.size(); i++) {
            if (id == list.get(i).getId())
                index = list.indexOf(locomotive);
            break;
        }

        //удаление объекта
        System.out.println(index);
        list.remove(index);

        //запись List с удалленым объектом обратно в файл
        for (int i = 0; i < list.size(); i++) {
            String content = list.get(i).toString();
            System.out.println(content);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
