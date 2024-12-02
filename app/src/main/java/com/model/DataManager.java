package com.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager instance;

    private static Gson gson;

    private Data data;
    private boolean isEditAllowed;
    private boolean didGSRChange;
    private boolean didBVPChange;


    private DataManager() {
        data = new Data();
        gson = new Gson();

        isEditAllowed = true;
        didGSRChange = false;
        didBVPChange = false;
    }

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();

        return instance;
    }

    public void setIBI(float value) {
        if (isEditAllowed) {
            data.IBI = value;
        }
    }

    public void setGSR(float value) {
        if (isEditAllowed) {
            data.GSR.add(value);
            didGSRChange = true;
        }
    }

    public void setBVP(float value) {
        if (isEditAllowed) {
            data.BVP.add(value);
            didBVPChange = true;
        }
    }

    public boolean getDidGSRChange() {
        return didGSRChange;
    }

    public boolean getDidBVPChange() {
        return didBVPChange;
    }

    private void resetData() {
        data.GSR.clear();
        data.BVP.clear();

        didGSRChange = false;
        didBVPChange = false;
    }

    public String getDataAsJson() {
        isEditAllowed = false;
        String json = gson.toJson(data);
        resetData();
        isEditAllowed = true;

        return json;
    }
}
