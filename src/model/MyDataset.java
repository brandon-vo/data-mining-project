package model;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;

import java.util.*;

public abstract class MyDataset {
    
    public static final int CITY_INDEX = 3;
    public static final String TOT_ = "TOT_";
    public static final String SHAPE__ = "Shape__";
    
    private final ArrayList<String> groupIndicators;
    
    /*
        - To access the data call getDataset()
        - The categories are grouped based on what they are about (for example
          if there are categories like ONE_BED, TWO_BED, THREE_BED, they would
          be grouped as NUMBER_OF_BEDS)
        - To access these groups you call their names as  a string like this:
                    getDataset().get("NUMBER_OF_BEDS")
          From there you get access to the specific categories by calling their
          names, which then gives a list of the data attached to all the cities.
        
        EXAMPLE:
            If you want to find out how many people have one bed in aurora city, you would write
                    getDataset().get("NUMBER_OF_BEDS").get("ONE_BED").get("Aurora")
     */
    private final HashMap<String, TreeMap<String, HashMap<String, Double>>> dataset;
    private final HashMap<String, Integer> cityCount;
    private final HashMap<String, HashSet<DataType>> validGroupCharts;
    
    public MyDataset () {
        
        groupIndicators = new ArrayList<>(Arrays.asList(TOT_, SHAPE__));
        dataset = new HashMap<>();
        cityCount = new HashMap<>();
        validGroupCharts = new HashMap<>();
        
    }
    
    public HashMap<String, TreeMap<String, HashMap<String, Double>>> getDataset () {
        return dataset;
    }
    
    public HashMap<String, HashSet<DataType>> getValidGroupCharts () {
        return validGroupCharts;
    }
    
    public ArrayList<String> getGroupIndicators () {
        return groupIndicators;
    }
    
    public int getCityCount (String city) {
        return cityCount.get(city);
    }
    
    public void setDataset (ArrayList<ArrayList<String>> dataset) {
        
        indexDataset(dataset, getCities(dataset));
        assignValidGroupCharts();
        
        ArrayList<Integer> groupIndexes = getGroupIndexes(dataset);
        ArrayList<String> categoryRow = dataset.get(0);
        
        // Put the data into dataset
        for (int row = 1; row<dataset.size(); ++row) {
            for (int col = CITY_INDEX+1; col<dataset.get(row).size(); ++col) {
                
                if (dataset.get(0).get(col).contains(TOT_)) continue;
                
                // Find the group the current category is in with binary search
                String groupName = categoryRow.get(groupIndexes.get(
                        binarySearch(groupIndexes, col)));
                for (String indicator: groupIndicators) {
                    if (!indicator.equals(SHAPE__)) {
                        groupName = groupName.replace(indicator, "");
                    }
                }
                
                if (groupName.contains(SHAPE__)) {
                    groupName = SHAPE__.replace("__", "");
                }
                
                TreeMap<String, HashMap<String, Double>> categories = getDataset().get(groupName);
                
                // Get the current category and add the data to it
                HashMap<String, Double> category = categories.get(categoryRow.get(col));
                ArrayList<String> curRow = dataset.get(row);
                category.put(curRow.get(CITY_INDEX), category.get(curRow.get(CITY_INDEX))
                        +Double.parseDouble(curRow.get(col)));
                
            }
        }
        
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
    
    public ArrayList<Integer> getGroupIndexes (ArrayList<ArrayList<String>> dataset) {
        
        ArrayList<Integer> groupIndexes = new ArrayList<>();
        ArrayList<String> categoryRow = dataset.get(0);
        
        // Index all of groups and categories
        for (int i = CITY_INDEX+1; i<categoryRow.size(); ++i) {
            
            String category = categoryRow.get(i);
            
            // If there is TOT_ or it is the last two categories,
            // then add it to the groups
            for (String indicator: groupIndicators) {
                if (category.contains(indicator)) {
                    groupIndexes.add(i);
                    break;
                }
            }
            
        }
        
        return groupIndexes;
        
    }
    
    public abstract void indexDataset (ArrayList<ArrayList<String>> dataset, HashSet<String> cities);
    
    public abstract void assignValidGroupCharts ();

    /**
     * @param arr = Array to search
     * @param target = target to search for
     * @return the index of target if found. Otherwise, the index
     * before its place in the list
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
