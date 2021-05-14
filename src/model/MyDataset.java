package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

public abstract class MyDataset {
    
    private HashMap<String, HashMap<String, Double>> dataset;
    private HashMap<String, Integer> cityCount;
    private HashMap<String, HashSet<DataType>> columnValidChart;
    
    public MyDataset () {
        dataset = new HashMap<>();
        cityCount = new HashMap<>();
        columnValidChart = new HashMap<>();
    }
    
    public HashMap<String, HashMap<String, Double>> getDataset () {
        return dataset;
    }
    
    public HashMap<String, Integer> getCityCount () {
        return cityCount;
    }
    
    public HashMap<String, HashSet<DataType>> getColumnValidChart () {
        return columnValidChart;
    }
    
    public abstract void setDataset (ArrayList<ArrayList<String>> dataset);
    
    
}
