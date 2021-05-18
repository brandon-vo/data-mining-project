package util;

import java.util.HashMap;
import java.util.Map;

public class Category {
    
    private final String categoryName;
    private final HashMap<String, Double> cities;
    private double total;
    
    public Category (String categoryName) {
        this.categoryName = categoryName;
        cities = new HashMap<>();
        total = 0;
    }
    
    public String getCategoryName () {
        return categoryName;
    }
    
    public HashMap<String, Double> getCities () {
        return cities;
    }
    
    public void addToCity (String city, double people) {
        cities.put(city, cities.getOrDefault(city, 0.0)+people);
        total += people;
    }
    
    public double getTotal () {
        return total;
    }
    
    @Override
    public String toString () {
        return categoryName+", total="+total;
    }
    
}
