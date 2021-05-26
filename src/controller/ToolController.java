package controller;

import model.MyDataset;

import javax.swing.event.MouseInputAdapter;

/**
 * Parent class to every tool controller
 * @author Felix
 */
public abstract class ToolController extends MouseInputAdapter {
    
    /**
     * Function to set the data that will be displayed on the screen
     * @param dataset = where the data will be taken from
     */
    public abstract void initializeDataToDisplay (MyDataset[] dataset);
    
}
