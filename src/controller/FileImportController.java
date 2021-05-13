package controller;

import model.JourneyToWork;
import model.MyDataset;
import model.ProfileOfHousing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileImportController {
    
    private static final String JOURNEY_TO_WORK_FILE = "./datasets/Profile_of_Housing_by_Dissemination_Area,_2016_Census.csv";
    private static final String PROFILE_OF_HOUSING_FILE = "./datasets/Profile_of_Journey_to_Work_by_Dissemination_Area,_2016_Census.csv";
    
    private JourneyToWork journeyToWork;
    private ProfileOfHousing profileOfHousing;
    
    public FileImportController () {
        
        journeyToWork = new JourneyToWork();
        profileOfHousing = new ProfileOfHousing();
        
    }
    
    public JourneyToWork getJourneyToWork () {
        return journeyToWork;
    }
    
    public ProfileOfHousing getProfileOfHousing () {
        return profileOfHousing;
    }
    
    public void importFiles () {
        importFrom(JOURNEY_TO_WORK_FILE, journeyToWork);
        importFrom(PROFILE_OF_HOUSING_FILE, profileOfHousing);
    }
    
    public void importFrom (String filePath, MyDataset myDataset) {
        
        Scanner input;
        try {
            input = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            return;
        }
        
        input.useDelimiter(",");
        ArrayList<ArrayList<String>> dataset = new ArrayList<>();
        dataset.add(new ArrayList<>());
        
        boolean hasDefectiveData = false;
        while (input.hasNext()) {
            
            String item = input.next();
            
            // Remove the current row if it doesn't have the good
            if (item.length()==0) {
                hasDefectiveData = true;
            }
    
            int back = dataset.size()-1;
            int indexNewLine = item.indexOf('\n');
            
            if (item.length()!=0 && indexNewLine!=-1) {
                
                if (hasDefectiveData) {
                    dataset.get(back).clear();
                    hasDefectiveData = false;
                } else {
                    dataset.get(back).add(item.substring(0, indexNewLine));
                    dataset.add(new ArrayList<>(dataset.get(0).size()));
                }
                back = dataset.size()-1;
                dataset.get(back).add(item.substring(indexNewLine+1));
                
            } else {
                dataset.get(back).add(item);
            }
            
        }
        
        myDataset.setData(dataset);
        
        input.close();
        
    }
    
}
