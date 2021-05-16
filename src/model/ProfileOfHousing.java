package model;

import java.util.*;

public class ProfileOfHousing extends MyDataset {
    
    /*
        OWN_MED_MONTHLY_SHELTER_COST
        OWN_AVG_MONTHLY_SHELTER_COST
        MED_DWELLING_VALUE
        AVG_DWELLING_VALUE
        MED_MONTHLY_SHELTER_RENT
        AVG_MONTHLY_SHELTER_RENT
     */
    public static final String MED_ = "MED_";
    
    public ProfileOfHousing () {
        super();
        getGroupIndicators().add(MED_);
    }
    
    @Override
    public void indexDataset (ArrayList<ArrayList<String>> dataset, HashSet<String> cities) {
        
        // Get and organize the columns
        ArrayList<String> categoryRow = dataset.get(0);
        String group = null;
        
        // Index all of groups and categories
        for (int i = CITY_INDEX+1; i<categoryRow.size(); ++i) {
            
            String category = categoryRow.get(i);
            
            // If there is TOT_ then create a new group and skip
            if (category.contains(TOT_)) {
                
                group = category.replace(TOT_, "");
                getDataset().put(group, new TreeMap<>());
                continue;
                
            } else if (category.contains(SHAPE__) && !group.equals("Shape") || category.contains(MED_)) {
                group = category.contains(SHAPE__)
                        ? "Shape":category.replace(MED_, "");
                getDataset().put(group, new TreeMap<>());
            }
            
            getDataset().get(group).put(category, new HashMap<>());
            for (String city : cities) {
                getDataset().get(group).get(category).put(city, 0.0);
            }
            
        }
        
    }
    
}
