package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class JourneyToWork extends MyDataset {
    
    public JourneyToWork () {
        super();
    }
    
    /*
        If there is a number and it doesn't have NO in it then the
        data is compatible with a line chart
     */
    
    @Override
    public void setDataset (ArrayList<ArrayList<String>> dataset) {
    
        final int START = 3;
    
        // Index all of the cities
        for (int i = 1; i<dataset.size(); ++i) {
            String city = dataset.get(START).get(i);
            if (!getDataset().containsKey(city)) {
                getDataset().put(city, new HashMap<>(dataset.get(START).size()));
            }
            // Increment the number of times the city was entered
            getCityCount().put(city, getCityCount().getOrDefault(city, 0)+1);
        }
    
        HashSet<String> invalidColumn = new HashSet<>();
        
        // Get the valid columns
        for (int i = START; i<dataset.get(0).size(); ++i) {
            
            if (!dataset.get(0).get(i).contains("TOT")) {
            
            
            
            }
            
        }
    
    }
    
}
