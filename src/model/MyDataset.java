package model;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import util.Category;
import view.Tool;

import java.util.*;

public abstract class MyDataset {
    
    public static final int CITY_INDEX = 3;
    public static final String TOT_ = "TOT_";
    public static final String MED_ = "MED_";
    public static final String SHAPE__ = "Shape__";
    
    private final ArrayList<String> groupIndicators;
    
    /*
        - To access the data call getDataset()
        - The categories are grouped based on what they are about (for example
          if there are categories like ONE_BED, TWO_BED, THREE_BED, they would
          be grouped as NUMBER_OF_BEDS)
        - To access these groups you call their names as a string like this:
                    getDataset().get("NUMBER_OF_BEDS")
          From there you get access to the specific categories through indexing
          by integers, which then gives a list of the data attached to all the
          cities.
        
        EXAMPLE:
            If you want to find out how many people have one bed (index 1) in
            aurora city, you would write
                    getDataset().get("NUMBER_OF_BEDS").get(1).get("Aurora")
     */
    private final HashMap<String, ArrayList<Category>> dataset;
    
    public static final HashMap<String, Integer> cityCount = new HashMap<>();
    
    public MyDataset () {
        groupIndicators = new ArrayList<>(Arrays.asList(TOT_, SHAPE__));
        dataset = new HashMap<>();
    }
    
    public HashMap<String, ArrayList<Category>> getDataset () {
        return dataset;
    }
    
    public ArrayList<String> getGroupIndicators () {
        return groupIndicators;
    }
    
    public void setDataset (ArrayList<ArrayList<String>> dataset) {
        
        indexDataset(dataset, getCities(dataset));
        
        ArrayList<Integer> groupIndexes = getGroupIndexes(dataset);
        ArrayList<String> categoryRow = dataset.get(0);
        
        // Put the data into dataset
        for (int row = 1; row<dataset.size(); ++row) {
            for (int col = CITY_INDEX+1; col<dataset.get(row).size(); ++col) {
                
                if (dataset.get(0).get(col).contains(TOT_) || dataset.get(0).get(col).contains(MED_)) {
                    continue;
                }
                
                // Find the group the current category is in with binary search
                int groupIndex = groupIndexes.get(binarySearch(groupIndexes, col));
                String groupName = categoryRow.get(groupIndex);
                
                // Decrement to include shape area's first category
                if (groupName.contains(SHAPE__)) {
                    --groupIndex;
                    groupName = SHAPE__.replace("__", "");
                } else if (groupName.contains(TOT_)) {
                    groupName = groupName.replace(TOT_, "");
                } else if (groupName.contains(MED_)) {
                    groupName = categoryRow.get(groupIndex+1);
                }
                
                if (groupName.contains(SHAPE__)) {
                    groupName = SHAPE__.replace("__", "");
                }
                
                ArrayList<Category> categories = this.dataset.get(groupName);
                
                // Get the current category and add the data to it
                ArrayList<String> curRow = dataset.get(row);
                categories.get(col-groupIndex-1).addToCity(curRow.get(CITY_INDEX),
                        Double.parseDouble(curRow.get(col)));
            }
        }
        
        // Set everything to the average
        for (ArrayList<Category> group : this.dataset.values()) {
            for (Category category : group) {
                for (Map.Entry<String, Double> city : category.getCities().entrySet()) {
                    city.setValue(city.getValue()/cityCount.get(city.getKey()));
                }
            }
        }
        
    }
    
    public static String[] getCities () {
        String[] cities = new String[cityCount.size()];
        cityCount.keySet().toArray(cities);
        return cities;
    }
    
    public HashSet<String> getCities (ArrayList<ArrayList<String>> dataset) {
        
        HashSet<String> cities = new HashSet<>();
        
        // Identify the cities that were surveyed and count them
        for (int i = 1; i<dataset.size(); ++i) {
            String city = dataset.get(i).get(CITY_INDEX);
            cities.add(city);
            cityCount.put(city, cityCount.getOrDefault(city, 0)+1);
        }
        
        return cities;
        
    }
    
    /**
     * @param dataset the raw dataset
     * @return the gorup indexes in dataset
     */
    private ArrayList<Integer> getGroupIndexes (ArrayList<ArrayList<String>> dataset) {
        
        ArrayList<Integer> groupIndexes = new ArrayList<>();
        ArrayList<String> categoryRow = dataset.get(0);
        
        // Index all of groups and categories
        for (int i = CITY_INDEX+1; i<categoryRow.size()-1; ++i) {
            
            String category = categoryRow.get(i);
            
            // If there is a group indicator, add it to the groups
            for (String indicator : groupIndicators) {
                if (category.contains(indicator)) {
                    groupIndexes.add(i);
                    break;
                }
            }
            
        }
        
        return groupIndexes;
        
    }
    
    /**
     * Index the categories and city Arraylist and HashMap in dataset
     * @param dataset
     * @param cities
     */
    public abstract void indexDataset (ArrayList<ArrayList<String>> dataset, HashSet<String> cities);
    
    public abstract void assignValidGroupCharts (Tool[] tools);
    
    /**
     * @param arr    = Array to search
     * @param target = target to search for
     * @return the index of the starting interval the target is in in arr
     */
    public static int binarySearch (ArrayList<Integer> arr, int target) {
        
        int l, mid, r;
        l = 0;
        r = arr.size()-1;
        while (l<=r) {
            mid = l+(r-l)/2;
            if (arr.get(mid)==target) {
                return mid;
            } else if (arr.get(mid)<target) {
                l = mid+1;
            } else if (target<arr.get(mid)) {
                r = mid-1;
            }
        }
        return l-1;
        
    }
    
}
