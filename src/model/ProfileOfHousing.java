package model;

import util.Category;
import view.Tool;

import java.util.*;

import static model.DataType.*;

/**
 * Specialized to process files from the Profile of Housing dataset
 * @author Felix
 */
public class ProfileOfHousing extends MyDataset {
    
    public ProfileOfHousing () {
        super();
        getGroupIndicators().add(MED_);
    }
    
    /**
     * {@link MyDataset#indexDataset}
     */
    @Override
    public void indexDataset (ArrayList<ArrayList<String>> rawDataset, HashSet<String> cities) {
        
        // Get and organize the columns
        ArrayList<String> categoryRow = rawDataset.get(0);
        String groupName = null;
        
        // Index all of groups and categories
        for (int i = CITY_INDEX+1; i<categoryRow.size(); ++i) {
            
            String categoryName = categoryRow.get(i);
            
            // If there is TOT_ or MED_, then create a new group and skip it
            if (categoryName.contains(TOT_) || categoryName.contains(MED_)) {
    
                groupName = categoryName.contains(TOT_)
                        ? categoryName.replace(TOT_, "")
                        : categoryRow.get(i+1);
                getDataset().put(groupName, new ArrayList<>());
                continue;
                
            // If there is SHAPE__ , create a new group but do not skip it
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
     * {@link MyDataset#assignValidGroupCharts}
     */
    @Override
    public void assignValidGroupCharts (Tool[] tools) {
        
        tools[DENSITY_MAP.getValue()].getValidGroups(1).put("Average Dwelling Value", getDataset().get("Avg Dwell Value"));
        tools[DENSITY_MAP.getValue()].getValidGroups(1).put("Average Renter Monthly Shelter Cost", getDataset().get("Avg Monthly Shelter Cost Rent"));
        tools[DENSITY_MAP.getValue()].getValidGroups(1).put("Average Owner Monthly Shelter Cost", getDataset().get("Own Avg Monthly Shelter Cost"));
        tools[DENSITY_MAP.getValue()].getValidGroups(1).put("Household Owner Count", getDataset().get("Pvt Hh Tenure"));
        tools[DENSITY_MAP.getValue()].getValidGroups(1).put("Household Renter Count", getDataset().get("Pvt Hh Tenure"));
    
        tools[BAR_CHART.getValue()].getValidGroups(1).put("Avg Monthly Shelter Cost Rent", getDataset().get("Avg Monthly Shelter Cost Rent"));
        
        tools[SCATTER_CHART.getValue()].getValidGroups(1).put("Occ Pvt Dwell No Bed", getDataset().get("Occ Pvt Dwell No Bed"));
        tools[SCATTER_CHART.getValue()].getValidGroups(1).put("Occ Pvt Dwell No Rooms", getDataset().get("Occ Pvt Dwell No Rooms"));
        tools[SCATTER_CHART.getValue()].getValidGroups(1).put("Pvt Hh No Maintainer", getDataset().get("Pvt Hh No Maintainer"));
    
        tools[PIE_CHART.getValue()].getValidGroups(1).put("Occ Pvt Dwell Condo", getDataset().get("Occ Pvt Dwell Condo"));
    
        tools[LINE_CHART.getValue()].getValidGroups(1).put("Pvt Hh Age Maintainer", getDataset().get("Pvt Hh Age Maintainer"));
        tools[LINE_CHART.getValue()].getValidGroups(1).put("Occ Pvt Dwell Const Period", getDataset().get("Occ Pvt Dwell Const Period"));
        
    }
    
}
