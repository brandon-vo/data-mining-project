/**
 * Specialized to process files from the Journey to Work dataset
 * @author Felix
 */

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
     * Index the categories and city Arraylist and HashMap in dataset
     * @param rawDataset = the raw dataset
     * @param cities     = all the city names in the dataset
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
            
            // Initialize the group with all the cities
            getDataset().get(groupName).add(new Category(categoryName));
            ArrayList<Category> group = getDataset().get(groupName);
            for (String city : cities) {
                group.get(group.size()-1).getCities().put(city, 0.0);
            }
            
        }
        
    }
    
    /**
     * Provide the tools with the valid data groups that they will use
     * @param tools = an array of all the tools
     */
    @Override
    public void assignValidGroupCharts (Tool[] tools) {
        
        tools[BAR_CHART.getValue()].getValidGroups(0).put("Main Mode 15 Yrs Plus", getDataset().get("Main Mode 15 Yrs Plus"));
        
        tools[PIE_CHART.getValue()].getValidGroups(0).put("Main Mode 15 Yrs Plus", getDataset().get("Main Mode 15 Yrs Plus"));
        
        tools[LINE_CHART.getValue()].getValidGroups(0).put("Time Leave 15 Yrs Plus", getDataset().get("Time Leave 15 Yrs Plus"));
        tools[LINE_CHART.getValue()].getValidGroups(0).put("Commute Time 15 Yrs Plus", getDataset().get("Commute Time 15 Yrs Plus"));
        
    }
    
}
