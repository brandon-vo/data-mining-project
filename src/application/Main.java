package application;

import controller.FileImportController;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    
    public static void main (String[] args) {
    
        FileImportController files = new FileImportController();
        files.importFiles();
        
    }
    
}
