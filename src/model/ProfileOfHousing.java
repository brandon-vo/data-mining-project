package model;

import util.Category;
import view.Tool;

import java.util.*;

import static model.DataType.*;

public class ProfileOfHousing extends MyDataset {
    
    public static final String MED_ = "MED_";
    
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
            
            // If there is TOT_ then create a new group and skip it
            if (categoryName.contains(TOT_)) {
                
                groupName = categoryName.replace(TOT_, "");
                getDataset().put(groupName, new ArrayList<>());
                continue;
                
            // If there is SHAPE__  or MED_, create a new group but do not skip it
            } else if (categoryName.contains(SHAPE__) && !groupName.equals("Shape") || categoryName.contains(MED_)) {
                
                groupName = categoryName.contains(SHAPE__)
                        ? "Shape":categoryName.replace(MED_, "");
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
        
        tools[DENSITY_MAP.getValue()].getValidGroups().add("DWELLING_VALUE");
        tools[DENSITY_MAP.getValue()].getValidGroups().add("MONTHLY_SHELTER_RENT");
        tools[DENSITY_MAP.getValue()].getValidGroups().add("OWN_MONTHLY_SHELTER_COST");
    
        tools[BAR_CHART.getValue()].getValidGroups().add("MONTHLY_SHELTER_RENT");
        
        tools[SCATTER_CHART.getValue()].getValidGroups().add("OCC_PVT_DWELL_NO_BED");
        tools[SCATTER_CHART.getValue()].getValidGroups().add("OCC_PVT_DWELL_NO_ROOMS");
        tools[SCATTER_CHART.getValue()].getValidGroups().add("PVT_HH_AGE_MAINTAINER");
        
        tools[PIE_CHART.getValue()].getValidGroups().add("OCC_PVT_DWELL_CONDO");
    
    }
    
}
