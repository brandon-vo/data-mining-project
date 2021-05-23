package controller;

import model.JourneyToWork;
import model.MyDataset;
import model.ProfileOfHousing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileImportController {
    
    private static final String JOURNEY_TO_WORK_FILE = "./datasets/Profile_of_Journey_to_Work_by_Dissemination_Area,_2016_Census.csv";
    private static final String PROFILE_OF_HOUSING_FILE = "./datasets/Profile_of_Housing_by_Dissemination_Area,_2016_Census.csv";
    
    public static final ArrayList<ArrayList<String>>[] rawData = new ArrayList[2];
    
    private MyDataset[] datasets;
    
    public FileImportController () {
        
        rawData[0] = new ArrayList<>();
        rawData[1] = new ArrayList<>();
        
        datasets = new MyDataset[2];
        datasets[0] = new JourneyToWork();
        datasets[1] = new ProfileOfHousing();
        
    }
    
    public JourneyToWork getJourneyToWork () {
        return (JourneyToWork) datasets[0];
    }
    
    public ProfileOfHousing getProfileOfHousing () {
        return (ProfileOfHousing) datasets[1];
    }
    
    public void importFiles () {
        importFrom(JOURNEY_TO_WORK_FILE, 0);
        importFrom(PROFILE_OF_HOUSING_FILE, 1);
    }
    
    private void importFrom (String filePath, int index) {
        
        MyDataset myDataset = datasets[index];
        ArrayList<ArrayList<String>> dataset = rawData[index];
        Scanner input;
        try {
            input = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            return;
        }
        
        input.useDelimiter(",");
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
        
        dataset.remove(dataset.size()-1);
        myDataset.setDataset(dataset);
    
        System.out.println("GOO GOO");
        
        input.close();
        
    }
    
}
