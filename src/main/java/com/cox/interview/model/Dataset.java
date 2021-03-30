package com.cox.interview.model;

import java.util.List;

public class Dataset {
    private String datasetId;
    private List<Dealer> dealers;


    public Dataset() {
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public List<Dealer> getDealers() {
        return dealers;
    }

    public void setDealers(List<Dealer> dealerSet) {
        this.dealers = dealerSet;
    }

    @Override
    public String toString()
    {
        return "dataset [datasetId="
                + datasetId
                + ", dealers="
                + dealers
                + "]";
    }
}
