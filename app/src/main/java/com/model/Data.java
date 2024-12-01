package com.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

// singleton
public class Data {
    private static Data instance;

    private static Gson gson;

    private float IBI;
    private List<Float> GSR;
    private List<Float> BVP;
    private boolean isEditAllowed;

    private Data() {
        GSR = new ArrayList<>();
        BVP = new ArrayList<>();

        gson = new Gson();

        isEditAllowed = true;
    }

    public static Data getInstance() {
        if (instance == null)
            instance = new Data();

        return instance;
    }

    public void setIBI(float value) {
        if (isEditAllowed) {
            IBI = value;
        }
    }

    public void setGSR(float value) {
        if (isEditAllowed) {
            GSR.add(value);
        }
    }

    public void setBVP(float value) {
        if (isEditAllowed) {
            BVP.add(value);
        }
    }

    public String getDataAsJson() {
        isEditAllowed = false;

        String json = gson.toJson(instance);

        GSR.clear();
        BVP.clear();

        isEditAllowed = true;

        return json;
    }
}
