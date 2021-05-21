package controller;

<<<<<<< HEAD
=======
import view.HousingTrendGUI;

>>>>>>> 990cfc2b62a3f9dcc7c7c1afbb578d8d6052d864
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScatterPlotController implements ActionListener {
<<<<<<< HEAD
    @Override
    public void actionPerformed (ActionEvent e) {
    
=======
    private HousingTrendGUI gui;
    public ScatterPlotController(HousingTrendGUI gui){
        this.gui = gui;
        setUpListeners();
    }

    public void setUpListeners() {
        gui.getLocation1().addActionListener(this);
        gui.getVariable().addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
>>>>>>> 990cfc2b62a3f9dcc7c7c1afbb578d8d6052d864
    }
}
