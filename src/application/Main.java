package application;

import controller.ApplicationController;
import controller.FileImportController;
import view.MainFrame;

import javax.swing.*;

public class Main {
    
    public static void main (String[] args) {
    
//        FileImportController controller = new FileImportController();
//        controller.importFiles();
        new ApplicationController();
    
    }
    
}
