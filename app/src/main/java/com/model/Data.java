package com.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public float IBI;
    public List<Float> GSR;
    public List<Float> BVP;

    public Data() {
        GSR = new ArrayList<>();
        BVP = new ArrayList<>();
    }
}
