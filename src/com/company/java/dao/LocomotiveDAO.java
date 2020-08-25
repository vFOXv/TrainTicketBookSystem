package com.company.java.dao;

import com.company.java.dao.GenericDAO;
import com.company.java.model.Locomotive;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
    public void saveEntity(Locomotive entity) {

    }

    @Override
    public void updateEntity(Locomotive entity) {

    }

    @Override
    public void removeEntity(Locomotive entity) {

    }
}
