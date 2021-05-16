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
    public void indexDataset (ArrayList<ArrayList<String>> dataset, HashSet<String> cities) {
        
        // Get and organize the columns
        ArrayList<String> categoryRow = dataset.get(0);
        String group = null;
    
        // Index all of groups and categories
        for (int i = CITY_INDEX+1; i<categoryRow.size(); ++i) {
        
            String category = categoryRow.get(i);
        
            // If there is TOT_ then create a new group
            if (category.contains(TOT_)) {
                group = category.replace(TOT_, "");
                getDataset().put(group, new TreeMap<>());
                continue;
                
            } else if (category.contains(SHAPE__) && !group.equals("Shape")) {
                group = "Shape";
                getDataset().put(group, new TreeMap<>());
            }
        
            getDataset().get(group).put(category, new HashMap<>());
            for (String city: cities) {
                getDataset().get(group).get(category).put(city, 0.0);
            }
        
        }
    
    }
    
}
