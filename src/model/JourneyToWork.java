package model;

import util.Category;

import java.util.*;

public class JourneyToWork extends MyDataset {
    
    public JourneyToWork () {
        super();
    }
    
    /**
     * Index the groups and categories in the dataset
     * @param dataset = the dataset
     * @param cities = the city names
     */
    @Override
    public void indexDataset (ArrayList<ArrayList<String>> dataset, HashSet<String> cities) {
        
        // Get and organize the columns
        ArrayList<String> categoryRow = dataset.get(0);
        String groupName = null;
    
        // Index all of groups and categories
        for (int i = CITY_INDEX+1; i<categoryRow.size(); ++i) {
        
            String categoryName = categoryRow.get(i);
        
            // If there is TOT_ then create a new group
            if (categoryName.contains(TOT_)) {
                
                groupName = categoryName.replace(TOT_, "");
                getDataset().put(groupName, new ArrayList<>());
                continue;
                
            } else if (categoryName.contains(SHAPE__) && !groupName.equals("Shape")) {
                groupName = "Shape";
                getDataset().put(groupName, new ArrayList<>());
            }
    
            // Initialize the group with all the cities
            getDataset().get(groupName).add(new Category(categoryName));
            ArrayList<Category> group = getDataset().get(groupName);
            for (String city : cities) {
                group.get(group.size()-1).getCities().put(city, 0.0);
            }
        
        }
    
    }
    
    /**
     * Lots and lots of hard code
     */
    @Override
    public void assignValidGroupCharts () {
        
        getValidGroupCharts().put("MAIN_MODE_15_YRS_PLUS_", new HashSet<>());
        getValidGroupCharts().get("MAIN_MODE_15_YRS_PLUS_").add(DataType.BAR_CHART);
        getValidGroupCharts().get("MAIN_MODE_15_YRS_PLUS_").add(DataType.PIE_CHART);
        
        getValidGroupCharts().put("TIME_LEAVE_15_YRS_PLUS", new HashSet<>());
        getValidGroupCharts().get("TIME_LEAVE_15_YRS_PLUS").add(DataType.LINE_CHART);
        
        getValidGroupCharts().put("COMMUTE_TIME_15_YRS_PLUS", new HashSet<>());
        getValidGroupCharts().get("COMMUTE_TIME_15_YRS_PLUS").add(DataType.LINE_CHART);
        
    }
    
}
