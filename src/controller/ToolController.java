package controller;

import model.MyDataset;

import javax.swing.event.MouseInputAdapter;

public abstract class ToolController extends MouseInputAdapter {
    
    /**
     * Function to set the data that will be displayed on the screen
     * @param dataset = where the data will be taken from
     */
    public abstract void initializeDataToDisplay (MyDataset[] dataset);
    
}
