package view;

import controller.DensityMapController;
import model.MyDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

public class DensityMapGUI extends Tool {
    
    private JLabel densityMapTitleLabel = new JLabel(new ImageIcon("img/titles/densityMapTitle.png"));
    private JLabel userPanel = new JLabel(new ImageIcon("img/densityMap/userPanel.png"));
    private String[] dataOptions = new String[] {
            "- Select data -",
            "Average Dwelling Value",
            "Average Owner Monthly Shelter Cost",
            "Average Renter Monthly Shelter Cost",
            "Household Owners",
            "Household Renters" };
    private JComboBox dataList = new JComboBox<>(dataOptions);
    
    private String[] cityOptions = new String[] {
            "- Select city -",
            "Aurora",
            "East Gwillimbury",
            "Georgina",
            "King",
            "Markham",
            "Newmarket",
            "Richmond Hill",
            "Stouffville",
            "Vaughan",};
    private JComboBox cityList = new JComboBox<>(cityOptions);
    
    private JButton submitButton = new JButton("SUBMIT");
    private JTextArea userResults = new JTextArea();
    
    private JLabel auroraLabel = new JLabel(new ImageIcon("img/densityMap/map/labels/auroraLabel.png"));
    private JLabel eastGwillimburyLabel = new JLabel(new ImageIcon("img/densityMap/map/labels/eastGwillimburyLabel.png"));
    private JLabel georginaLabel = new JLabel(new ImageIcon("img/densityMap/map/labels/georginaLabel.png"));
    private JLabel kingLabel = new JLabel(new ImageIcon("img/densityMap/map/labels/kingLabel.png"));
    private JLabel markhamLabel = new JLabel(new ImageIcon("img/densityMap/map/labels/markhamLabel.png"));
    private JLabel newmarketLabel = new JLabel(new ImageIcon("img/densityMap/map/labels/newmarketLabel.png"));
    private JLabel richmondHillLabel = new JLabel(new ImageIcon("img/densityMap/map/labels/richmondHillLabel.png"));
    private JLabel stouffvilleLabel = new JLabel(new ImageIcon("img/densityMap/map/labels/stouffvilleLabel.png"));
    private JLabel vaughanLabel = new JLabel(new ImageIcon("img/densityMap/map/labels/vaughanLabel.png"));
    
    // TODO call MyDataset.getCities() instead to get the names of all the cities
    private BufferedImage aurora;
    private BufferedImage eastGwillimbury;
    private BufferedImage georgina;
    private BufferedImage king;
    private BufferedImage markham;
    private BufferedImage newmarket;
    private BufferedImage richmondHill;
    private BufferedImage stouffville;
    private BufferedImage vaughan;
    private BufferedImage[] maps = { aurora, eastGwillimbury, georgina, king, markham,
            newmarket, richmondHill, stouffville, vaughan };
    
    private String[] mapNames = { "aurora", "eastGwillimbury", "georgina", "king", "markham",
            "newmarket", "richmondHill", "stouffville", "vaughan" };
    private JLabel[] mapLabels = new JLabel[9];
    
    public DensityMapGUI () {
        
        densityMapTitleLabel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(densityMapTitleLabel);
        
        userResults.setBounds(950, 470, 350, 200);
        userResults.setEditable(false);
        userResults.setText("Please input your data to compare your results!");
        add(userResults);
        
        cityList.setBounds(950, 130, 240, 25);
        cityList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cityList.setBackground(COMBO_BOX_COLOUR);
        cityList.setEnabled(false);
        add(cityList);
        
        submitButton.setBounds(1065, 400, 125, 50);
        submitButton.setBorder(null);
        submitButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        submitButton.setBackground(BUTTON_COLOUR);
        //submitButton.setEnabled(false);
        add(submitButton);
        
        auroraLabel.setBounds(285, 415, 100, 100);
        add(auroraLabel);
        
        eastGwillimburyLabel.setBounds(315, 280, 100, 100);
        add(eastGwillimburyLabel);
        
        georginaLabel.setBounds(360, 140, 100, 100);
        add(georginaLabel);
        
        kingLabel.setBounds(165, 460, 100, 100);
        add(kingLabel);
        
        markhamLabel.setBounds(400, 530, 100, 100);
        add(markhamLabel);
        
        newmarketLabel.setBounds(280, 360, 100, 100);
        add(newmarketLabel);
        
        richmondHillLabel.setBounds(295, 500, 100, 100);
        add(richmondHillLabel);
        
        stouffvilleLabel.setBounds(380, 410, 100, 100);
        add(stouffvilleLabel);
        
        vaughanLabel.setBounds(200, 580, 100, 100);
        add(vaughanLabel);
        
        userPanel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(userPanel);
        
        dataList.setBounds(35, 130, 240, 25);
        dataList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        dataList.setBackground(COMBO_BOX_COLOUR);
        add(dataList);
        
        for (int z = 0; z < maps.length; z++) {
            changeMapColour(z);
        }
    }
    
    public void changeMapColour (int mapIndex) {
        try {
            for (int cityMap = 0; cityMap<maps.length; cityMap++) {
                maps[cityMap] = ImageIO.read(new File("img/densityMap/map/lv1/"+mapNames[cityMap]+"Lv1.png"));
                Image newColouredMap = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(maps[mapIndex].getSource(),
                                new DensityMapController.RedBlueSwapFilter()));
                /*
                0 = aurora
                1 = east gwillimbury
                2 = georgina
                3 = king
                4 = markham
                5 = newmarket
                6 = richmond hill
                7 = stouffville
                8 = vaughan
                */
                
                mapLabels[cityMap] = new JLabel(new ImageIcon(newColouredMap));
                mapLabels[cityMap].setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
                add(mapLabels[cityMap]);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initializeDataToDisplay(MyDataset dataset, String groupName) {
    
    }
    
    public String[] getDataOptions () {
        return dataOptions;
    }
    
    public void setDataOptions (String[] dataOptions) {
        this.dataOptions = dataOptions;
    }
    
    public JComboBox getDataList () {
        return dataList;
    }
    
    public void setDataList (JComboBox dataList) {
        this.dataList = dataList;
    }
    
    public JButton getSubmitButton() {
        return submitButton;
    }
    
    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }
    
    public JLabel getAuroraLabel() {
        return auroraLabel;
    }
    
    public void setAuroraLabel(JLabel auroraLabel) {
        this.auroraLabel = auroraLabel;
    }
    
    public JLabel getEastGwillimburyLabel() {
        return eastGwillimburyLabel;
    }
    
    public void setEastGwillimburyLabel(JLabel eastGwillimburyLabel) {
        this.eastGwillimburyLabel = eastGwillimburyLabel;
    }
    
    public JLabel getGeorginaLabel() {
        return georginaLabel;
    }
    
    public void setGeorginaLabel(JLabel georginaLabel) {
        this.georginaLabel = georginaLabel;
    }
    
    public JLabel getKingLabel() {
        return kingLabel;
    }
    
    public void setKingLabel(JLabel kingLabel) {
        this.kingLabel = kingLabel;
    }
    
    public JLabel getMarkhamLabel() {
        return markhamLabel;
    }
    
    public void setMarkhamLabel(JLabel markhamLabel) {
        this.markhamLabel = markhamLabel;
    }
    
    public JLabel getNewmarketLabel() {
        return newmarketLabel;
    }
    
    public void setNewmarketLabel(JLabel newmarketLabel) {
        this.newmarketLabel = newmarketLabel;
    }
    
    public JLabel getRichmondHillLabel() {
        return richmondHillLabel;
    }
    
    public void setRichmondHillLabel(JLabel richmondHillLabel) {
        this.richmondHillLabel = richmondHillLabel;
    }
    
    public JLabel getStouffvilleLabel() {
        return stouffvilleLabel;
    }
    
    public void setStouffvilleLabel(JLabel stouffvilleLabel) {
        this.stouffvilleLabel = stouffvilleLabel;
    }
    
    public JLabel getVaughanLabel() {
        return vaughanLabel;
    }
    
    public void setVaughanLabel(JLabel vaughanLabel) {
        this.vaughanLabel = vaughanLabel;
    }
}