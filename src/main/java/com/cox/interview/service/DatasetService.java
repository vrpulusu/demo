package com.cox.interview.service;
import com.cox.interview.model.Dataset;
import com.cox.interview.model.Dealer;
import com.cox.interview.model.Vehicle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DatasetService {

    public String createDataset(String datasetId){
        try {

            File targetFile = new File(datasetId + ".json");
            boolean success = targetFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String datasetJson = "{\"datasetId\":\"" +datasetId+"\"}";
        return datasetJson;
    }

    public boolean postDataset(Dataset dataset, String datasetId){

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(dataset);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(datasetId + ".json", true));
            writer.append(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public String getCheat(String datasetId){

        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(datasetId + ".json"));
            Dataset dataset = new Gson().fromJson(bufferedReader, Dataset.class);
            return gson.toJson(dataset);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getDealer(String datasetId, String dealerId){

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(datasetId + ".json"));
            Dataset dataset = new Gson().fromJson(bufferedReader, Dataset.class);
            List<Dealer> dealers = dataset.getDealers();
            for(int i = 0; i<dealers.size(); i++){
                if(dealers.get(i).getDealerId() == Integer.parseInt(dealerId)){
                    String dealerName = dealers.get(i).getName();
                    String dealerJson = "{\"dealerId\":" +dealerId+ ", \"name\": \"" + dealerName + "\"}";
                    return dealerJson;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "Dealer Not Present with that ID!";
    }

    public String getVehicles(String datasetId){
        List<Integer> vehicleIds = new ArrayList<Integer>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(datasetId + ".json"));
            Dataset dataset = new Gson().fromJson(bufferedReader, Dataset.class);
            List<Dealer> dealers = dataset.getDealers();

            for(int i = 0; i<dealers.size(); i++){
                List<Vehicle> vehicles = dealers.get(i).getVehicles();
                for(int j = 0; j< vehicles.size();j++){
                    vehicleIds.add(vehicles.get(j).getVehicleId());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String vehicleIdJson = "{\n" +
                "  \"vehicleIds\": [";

        for(int k = 0; k<vehicleIds.size(); k++){
            if(k!=vehicleIds.size()-1)
            vehicleIdJson = vehicleIdJson + vehicleIds.get(k) + ",";
            else
                vehicleIdJson = vehicleIdJson + vehicleIds.get(k);
        }

        vehicleIdJson = vehicleIdJson + "]}";

        return vehicleIdJson;
    }

    public String getVehicle(String datasetId, String vehicleId){

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(datasetId + ".json"));
            Dataset dataset = new Gson().fromJson(bufferedReader, Dataset.class);
            List<Dealer> dealers = dataset.getDealers();

            for(int i = 0; i<dealers.size(); i++){
                List<Vehicle> vehicles = dealers.get(i).getVehicles();
                int dealer = dealers.get(i).getDealerId();
                for(int j = 0; j< vehicles.size();j++){
                    if(vehicles.get(j).getVehicleId() == Integer.parseInt(vehicleId)){
                        Vehicle vehicle = vehicles.get(j);
                        Gson gson = new Gson();
                        String vehicleJson = gson.toJson(vehicle);
                        String vehicleJsonTruncated = new StringBuffer(vehicleJson).deleteCharAt(vehicleJson.length()-1).toString();
                        vehicleJsonTruncated = vehicleJsonTruncated + ",\"dealerId\":" + dealer + "}";
                        return vehicleJsonTruncated;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return "Vehicle Not Present with that ID";
    }
}
