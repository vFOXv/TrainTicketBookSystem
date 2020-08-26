package com.company.java.dao;

import com.company.java.dao.GenericDAO;
import com.company.java.model.Locomotive;


import java.io.*;

public class LocomotiveDAO implements GenericDAO<Locomotive> {
    String filePath = "src/com/resources/database_test/locomotive.txt";

    @Override
    public Locomotive getEntityById(Long id) {
        Locomotive locomotive = new Locomotive();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String read = null;
            while ((read = reader.readLine()) != null) {
                String[] splitedFile = read.split("/");
                for (String line : splitedFile) {
                    String[] splitedLine = line.split(",");

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
            System.out.println("Locomotive with such id is already existing");
        } else {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
                StringBuffer existFiles = new StringBuffer();
                String read = null;
                while ((read = bufferedReader.readLine()) != null) {
                    existFiles.append(read);
                }
                String[] fileDataList = existFiles.toString().split("/");


                String locomotiveToString = locomotive.getId() + ","
                        + locomotive.getName() + ","
                        + locomotive.getCapacityLocomotive() + ","
                        + locomotive.getPowerLocomotive() + ","
                        + locomotive.getYearIssueLocomotive() + ","
                        + locomotive.getFuelType() +"/";

                String newFile = "";
                if (existFiles.toString().equals("")) {
                    newFile = existFiles.append(locomotiveToString).toString();
                } else {
                    for (String s : fileDataList) {
                        newFile += s;
                        newFile += "/";
                        newFile += "\n";

                    }
                    newFile += locomotiveToString;
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    writer.write(newFile);
                } catch (IOException e) {
                    e.printStackTrace();

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void updateEntity(Locomotive entity) {

    }

    @Override
    public void removeEntity(Locomotive entity) {

    }
}
