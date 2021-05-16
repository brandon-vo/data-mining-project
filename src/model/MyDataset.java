package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.TreeMap;

public abstract class MyDataset {
    
    // First: city; Second: group, Third: category data; then value
    private HashMap<String, HashMap<String, TreeMap<String, ArrayList<Double>>>> dataset;
    private HashMap<String, Integer> cityCount;
    private HashMap<String, HashSet<DataType>> groupValidCharts;
    
    public MyDataset () {
        dataset = new HashMap<>();
        cityCount = new HashMap<>();
        groupValidCharts = new HashMap<>();
    }
    
    public HashMap<String, HashMap<String, TreeMap<String, ArrayList<Double>>>> getDataset () {
        return dataset;
    }
    
    public HashMap<String, Integer> getCityCount () {
        return cityCount;
    }
    
    public HashMap<String, HashSet<DataType>> getGroupValidCharts () {
        return groupValidCharts;
    }
    
    public abstract void setDataset (ArrayList<ArrayList<String>> dataset);
    
    // Returns index of target if found.
    // Otherwise, returns the index before its place in the list.
    public static int binarySearch (ArrayList<Integer> arr, int target) {
        
        int l, mid, r;
        l = 0;
        r = arr.size()-1;
        while (l<=r) {
            mid = l+(r-l)/2;
            if (arr.get(mid)==target) {
                return mid;
            } else if (arr.get(mid)<target) {
                l = mid+1;
            } else if (target<arr.get(mid)) {
                r = mid-1;
            }
        }
        return l-1;
        
    }
    
}
