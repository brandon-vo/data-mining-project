package controller;

import model.JourneyToWork;
import model.ProfileOfHousing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileImportController {

    public JourneyToWork journeyToWork;
    public ProfileOfHousing profileOfHousing;
    
    public FileImportController () {
        
        journeyToWork = new JourneyToWork();
        profileOfHousing = new ProfileOfHousing();
        
    }
    
    public void importFiles () {
    
    
    
    }
    
    public void importJourneyToWork () throws FileNotFoundException {
    
        Scanner input = new Scanner(new File("./datasets/Profile_of_Housing_by_Dissemination_Area,_2016_Census.csv"));
        
        
    }
    
    public void importProfileOfHousing () {
    
    
    
    }

}
