package view;

import javax.swing.*;

public class DensityMapGUI extends Tool {

    private JLabel densityMapTitleLabel = new JLabel("Density Map");
    private JLabel mapLabel = new JLabel();
    private String[] dataOptions = new String[]{
            "Average dwelling value",
            "Average monthly shelter cost (owner)",
            "Average monthly shelter cost (renter)",
            "Household Owners",
            "Household Renters"};
    private JComboBox dataList = new JComboBox<>(dataOptions);
    private JButton submitButton = new JButton("SUBMIT");
    private JLabel aurora = new JLabel(new ImageIcon("img/map/aurora.png"));
    private JLabel eastGwillimbury = new JLabel(new ImageIcon("img/map/eastGwillimbury.png"));
    private JLabel georgina = new JLabel(new ImageIcon("img/map/georgina.png"));
    private JLabel king = new JLabel(new ImageIcon("img/map/king.png"));
    private JLabel markham = new JLabel(new ImageIcon("img/map/markham.png"));
    private JLabel newmarket = new JLabel(new ImageIcon("img/map/newmarket.png"));
    private JLabel richmondhill = new JLabel(new ImageIcon("img/map/richmondHill.png"));
    private JLabel stouffville = new JLabel(new ImageIcon("img/map/stouffville.png"));

    public DensityMapGUI() {


    }

}
