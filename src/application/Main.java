package application;

import controller.ApplicationController;
import controller.FileImportController;
import model.JourneyToWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class Main {
    
    public static void main (String[] args) {
    
        FileImportController files = new FileImportController();
        files.importFiles();
        
//        new ApplicationController();
        
    }
    
}
