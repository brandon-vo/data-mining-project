package model;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class MyDataset {
    
    private final HashMap<String, HashMap<String, Double>> data;
    
    public MyDataset () {
        data = new HashMap<>();
    }
    
    public HashMap<String, HashMap<String, Double>> getData () {
        return data;
    }
    
    public abstract void setData (ArrayList<ArrayList<Double>> data);
    
}
