package model;

import util.Category;
import view.Tool;

import java.util.*;

import static model.DataType.*;

public class JourneyToWork extends MyDataset {
    
    public JourneyToWork () {
        super();
    }
    
    /**
     * @see #indexDataset
     */
    @Override
    public void indexDataset (ArrayList<ArrayList<String>> rawDataset, HashSet<String> cities) {
        
        // Get and organize the columns
        ArrayList<String> categoryRow = rawDataset.get(0);
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
            
            try {
                // Initialize the group with all the cities
                getDataset().get(groupName).add(new Category(categoryName));
                ArrayList<Category> group = getDataset().get(groupName);
                for (String city : cities) {
                    group.get(group.size()-1).getCities().put(city, 0.0);
                }
            } catch (NullPointerException e) {
                System.out.println();
            }
            
        }
        
    }
    
    /**
     * Lots and lots of hard code
     */
    @Override
    public void assignValidGroupCharts (Tool[] tools) {
        
        tools[BAR_CHART.getValue()].getValidGroups(0).put("Main Mode 15 Yrs Plus", getDataset().get("Main Mode 15 Yrs Plus"));
        
        tools[PIE_CHART.getValue()].getValidGroups(0).put("Main Mode 15 Yrs Plus", getDataset().get("Main Mode 15 Yrs Plus"));
        
        tools[LINE_CHART.getValue()].getValidGroups(0).put("Time Leave 15 Yrs Plus", getDataset().get("Time Leave 15 Yrs Plus"));
        tools[LINE_CHART.getValue()].getValidGroups(0).put("Commuting 15 Yrs Plus", getDataset().get("Commuting 15 Yrs Plus"));
        
    }
    
}
