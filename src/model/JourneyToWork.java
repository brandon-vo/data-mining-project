package model;

import java.util.*;

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
    
        final int CITY_INDEX = 3;
        final String TOT_ = "TOT_";
    
        // Index all of the cities
        for (int i = 1; i<dataset.size(); ++i) {
            
            String city = dataset.get(i).get(CITY_INDEX);
            if (!getDataset().containsKey(city)) {
                getDataset().put(city, new HashMap<>(dataset.get(CITY_INDEX).size()));
            }
            // Increment the number of times the city was entered
            getCityCount().put(city, getCityCount().getOrDefault(city, 0)+1);
            
        }
        
        // Get and organize the columns
        ArrayList<String> categoryRow = dataset.get(0);
        ArrayList<Integer> groups = new ArrayList<>();
        String group = null;
        
        for (int i = CITY_INDEX+1; i<categoryRow.size(); ++i) {
            
            String category = categoryRow.get(i);
            
            // If there is TOT_, then create a new group
            if (category.contains(TOT_)) {
                group = category.replace(TOT_, "");
                for (Map.Entry<String, HashMap<String, TreeMap<String, ArrayList<Double>>>> city: getDataset().entrySet()) {
                    city.getValue().put(group, new TreeMap<>());
                }
                groups.add(i);
            } else {
                for (Map.Entry<String, HashMap<String, TreeMap<String, ArrayList<Double>>>> city: getDataset().entrySet()) {
                    city.getValue().get(group).put(category, new ArrayList<>());
                }
            }
            
        }
    
        // put the data into dataset
        for (int row = 1; row<dataset.size(); ++row) {
            for (int col = CITY_INDEX+1; col<dataset.get(row).size(); ++col) {

                if (dataset.get(0).get(col).contains(TOT_)) continue;
                // Find the group the current category is in with binary search
                try {
                    getDataset().get(dataset.get(row).get(CITY_INDEX))
                            .get(dataset.get(0).get(groups.get(binarySearch(groups, col))).replace(TOT_, ""))
                            .get(dataset.get(0).get(col))
                            .add(Double.parseDouble(dataset.get(row).get(col)));
                } catch (NullPointerException e) {
                    System.err.println("not good");
                }

            }
        }
    
        System.out.println("hi");
    
    }
    
}
