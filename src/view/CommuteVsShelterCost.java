package view;

import javax.swing.*;

public class CommuteVsShelterCost extends Tool {
    
    private JLabel titleLabel = new JLabel("Most Common Commute Type vs Average Monthly Shelter Cost");
    private JButton markhamButton = new JButton("Markham");
    private JButton vaughnButton = new JButton("Vaughn");
    private JButton richmondButton = new JButton("Richmond Hill");
    private JButton auroraButton = new JButton("Aurora");
    private JButton newMarketButton = new JButton("Newmarket");
    private JLabel currentData = new JLabel("the current city being displayed");
    
    
    public CommuteVsShelterCost (int x, int y, int width, int height) {
        
        super(x, y, width, height);
    }
    
}
