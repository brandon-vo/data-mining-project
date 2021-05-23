package model;

import util.Category;
import view.Tool;

import java.util.*;

import static model.DataType.*;

public class ProfileOfHousing extends MyDataset {
    
    public static final String MED_ = "MED_";
    public static final String AVG_ = "AVG_";
    
    public ProfileOfHousing () {
        super();
        getGroupIndicators().add(MED_);
    }
    
    @Override
    public void indexDataset (ArrayList<ArrayList<String>> dataset, HashSet<String> cities) {
        
        // Get and organize the columns
        ArrayList<String> categoryRow = dataset.get(0);
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
     * Lots and lots of hard code
     */
    @Override
    public void assignValidGroupCharts (Tool[] tools) {

        tools[DENSITY_MAP.getValue()].getValidGroups(1).put("Average Dwelling Value", getDataset().get("AVG_DWELL_VALUE"));
        tools[DENSITY_MAP.getValue()].getValidGroups(1).put("Average Renter Monthly Shelter Cost", getDataset().get("AVG_MONTHLY_SHELTER_COST_RENT"));
        tools[DENSITY_MAP.getValue()].getValidGroups(1).put("Average Owner Monthly Shelter Cost", getDataset().get("OWN_AVG_MONTHLY_SHELTER_COST"));
        tools[DENSITY_MAP.getValue()].getValidGroups(1).put("Household Owner Count", getDataset().get("OWNER")); // TODO this doesn't work
        tools[DENSITY_MAP.getValue()].getValidGroups(1).put("Household Renter Count", getDataset().get("RENTER")); // TODO this doesn't work
    
        tools[BAR_CHART.getValue()].getValidGroups(1).put("AVG_MONTHLY_SHELTER_RENT", getDataset().get("AVG_MONTHLY_SHELTER_RENT"));
        
        tools[SCATTER_CHART.getValue()].getValidGroups(1).put("OCC_PVT_DWELL_NO_BED", getDataset().get("OCC_PVT_DWELL_NO_BED"));
        tools[SCATTER_CHART.getValue()].getValidGroups(1).put("OCC_PVT_DWELL_NO_ROOMS", getDataset().get("OCC_PVT_DWELL_NO_ROOMS"));
        tools[SCATTER_CHART.getValue()].getValidGroups(1).put("PVT_HH_AGE_MAINTAINER", getDataset().get("PVT_HH_AGE_MAINTAINER"));
        
        tools[PIE_CHART.getValue()].getValidGroups(1).put("OCC_PVT_DWELL_CONDO", getDataset().get("OCC_PVT_DWELL_CONDO"));
    
    }
    
}
