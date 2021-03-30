package com.cox.interview.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.cox.interview.model.Dataset;
import com.cox.interview.service.DatasetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {


    @Autowired
    DatasetService datasetService;

    @GetMapping("/{datasetId}")
    public String postDataset(@PathVariable String datasetId) {
        return datasetService.createDataset(datasetId);
    }

    @PostMapping("/{datasetId}/answer")
    public String postAnswer(@RequestBody Dataset dataset, @PathVariable String datasetId) {
        Long startTime = System.currentTimeMillis();
        boolean result = datasetService.postDataset(dataset, datasetId);
        Long endTime = System.currentTimeMillis();
        Long elapsedTime = endTime - startTime;
        String answerJson = "{\"success\":" + result + ",\"message\":Answer is Posted, \"totalMillisecond\":"+elapsedTime+"}";
        return answerJson;
    }

    @GetMapping("/{datasetId}/cheat")
    public String getCheat(@PathVariable String datasetId) {
        return datasetService.getCheat(datasetId);
    }

    @GetMapping("/{datasetId}/dealers/{dealerId}")
    public String getDealers(@PathVariable String datasetId, @PathVariable String dealerId) {
        return datasetService.getDealer(datasetId, dealerId);
    }

    @GetMapping("/{datasetId}/vehicles")
    public String getVehicles(@PathVariable String datasetId) {
        return datasetService.getVehicles(datasetId);
    }

    @GetMapping("/{datasetId}/vehicles/{vehicleId}")
    public String getVehicle(@PathVariable String datasetId, @PathVariable String vehicleId) {
        return datasetService.getVehicle(datasetId, vehicleId);
    }
}