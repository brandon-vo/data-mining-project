package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public abstract class MyDataset {
    
    private final HashMap<String, HashMap<String, Double>> dataset;
    private final HashMap<String, Integer> cityCount;
    private final HashMap<String, HashSet<DataType>>
    
    public MyDataset () {
        dataset = new HashMap<>();
        cityCount = new HashMap<>();
    }
    
    public HashMap<String, HashMap<String, Double>> getDataset () {
        return dataset;
    }
    
    public HashMap<String, Integer> getCityCount () {
        return cityCount;
    }
    
    public abstract void setDataset (ArrayList<ArrayList<String>> dataset);
    
    
}
