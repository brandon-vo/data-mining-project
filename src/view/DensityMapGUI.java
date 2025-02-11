package view;

import util.ColourFilter;
import util.IndividualColourFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

/**
 * Create graphical components for the Density Map GUI
 * @author Brandon Vo
 */
public class DensityMapGUI extends Tool {
    
    // Fields
    private JLabel densityMapTitleLabel = new JLabel(new ImageIcon("img/titles/densityMapTitle.png")); // Title
    private JLabel userPanel = new JLabel(new ImageIcon("img/densityMap/userPanel.png")); // User panel
    private String[] dataOptions = new String[] { // Store data options
            "- Select data -",
            "Average Dwelling Value",
            "Average Renter Monthly Shelter Cost",
            "Average Owner Monthly Shelter Cost",
            "Household Owner Count",
            "Household Renter Count" };
    private JComboBox dataList = new JComboBox<>(dataOptions); // Combo box which gives all data categories
    
    private String[] cityOptions = new String[] { // Store city options
            "- Select city -",
            "Aurora",
            "East Gwillimbury",
            "Georgina",
            "King",
            "Markham",
            "Newmarket",
            "Richmond Hill",
            "Whitchurch-Stouffville",
            "Vaughan", };
    private JComboBox cityList = new JComboBox<>(cityOptions); // Combo box which gives the list of cities
    private JComboBox questionList = new JComboBox(); // Combo box to store question to ask to user
    private JButton submitButton = new JButton("SUBMIT"); // Button to submit input results
    private JTextArea userResults = new JTextArea(); // Text area for input results
    
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
            newmarket, richmondHill, stouffville, vaughan }; // Buffered images for all cities on the map
    
    private String[] mapNames = { "aurora", "eastGwillimbury", "georgina", "king", "markham",
            "newmarket", "richmondHill", "stouffville", "vaughan" }; // Accessing name of a city for the map
    private JLabel[] mapLabels = new JLabel[9]; // Array of labels with the map of the city
    private JLabel[] cityLabels = new JLabel[9]; // Array of labels with the city text on it
    private JLabel[] citySelectedLabels = new JLabel[9]; // Array of labels with selected map image
    private JLabel hoverInformation = new JLabel();
    
    private int colourID; // Store colour ID for a map
    
    // Constructor
    public DensityMapGUI () {
        
        // Title label
        densityMapTitleLabel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(densityMapTitleLabel);
        
        // User results text area
        userResults.setBounds(950, 470, 350, 200);
        userResults.setEditable(false);
        userResults.setText("Please select a dataset!");
        add(userResults);
        
        // List of cities combo box
        cityList.setBounds(950, 130, 240, 25);
        cityList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        cityList.setBackground(COMBO_BOX_COLOUR);
        cityList.setEnabled(false);
        add(cityList);
        
        // List of questions combo box
        questionList.setBounds(950, 200, 240, 25);
        questionList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        questionList.setBackground(COMBO_BOX_COLOUR);
        questionList.setEnabled(false);
        add(questionList);
        
        // Submit button
        submitButton.setBounds(1065, 400, 125, 50);
        submitButton.setBorder(null);
        submitButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        submitButton.setBackground(BUTTON_COLOUR);
        submitButton.setEnabled(false);
        add(submitButton);
        
        // City labels and selected map images
        for (int label = 0; label<cityLabels.length; label++) {
            cityLabels[label] = new JLabel(new ImageIcon("img/densityMap/map/labels/"+mapNames[label]+"Label.png"));
            add(cityLabels[label]);
            citySelectedLabels[label] = new JLabel(new ImageIcon("img/densityMap/map/selected/"+mapNames[label]+"Selected.png"));
            citySelectedLabels[label].setBounds(0, 0, 1366, 768);
        }
        
        // Set city label position
        cityLabels[0].setBounds(285, 440, 100, 50);  // Aurora
        cityLabels[1].setBounds(315, 280, 100, 100); // East Gwillimbury
        cityLabels[2].setBounds(360, 140, 100, 100); // Georgina
        cityLabels[3].setBounds(165, 460, 100, 100); // King
        cityLabels[4].setBounds(400, 530, 100, 100); // Markham
        cityLabels[5].setBounds(280, 360, 100, 100); // Newmarket
        cityLabels[6].setBounds(295, 500, 100, 100); // Richmond Hill
        cityLabels[7].setBounds(380, 410, 100, 100); // Stouffville
        cityLabels[8].setBounds(200, 580, 100, 100); // Vaughan
        
        // User panel
        userPanel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(userPanel);
        
        // List of data combo box
        dataList.setBounds(35, 130, 240, 25);
        dataList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        dataList.setBackground(COMBO_BOX_COLOUR);
        add(dataList);
        
        // City information which displays data for hovered city
        String text = "Welcome"+"<br>"+"Please select a dataset";
        
        hoverInformation.setBounds(MainFrame.WIDTH/2-130, MainFrame.HEIGHT/2-80, 300, 100);
        hoverInformation.setFont(new Font("Tahoma", Font.BOLD, 25));
        hoverInformation.setOpaque(true);
        hoverInformation.setBackground(COMBO_BOX_COLOUR);
        hoverInformation.setText("<html><div style='text-align: center;'>"+text+"<html>");
        hoverInformation.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        hoverInformation.setHorizontalAlignment(SwingConstants.CENTER);
        hoverInformation.setVerticalAlignment(SwingConstants.CENTER);
        add(hoverInformation);
        
    }
    
    // Change whole map colour
    public void changeMapColour () {
        
        ColourFilter filter = new ColourFilter(this); // ColourFilter class
        
        try {
            
            for (int cityIndex = 0; cityIndex<maps.length; cityIndex++) {
                
                // Remove the old city label if it exists
                if (mapLabels[cityIndex]!=null) {
                    remove(mapLabels[cityIndex]);
                    revalidate();
                    repaint();
                }
                
                // Read map image
                maps[cityIndex] = ImageIO.read(new File("img/densityMap/map/lv1/"+mapNames[cityIndex]+"Lv1.png"));
                
                // Create an image with colour filter
                Image newColouredMap = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(maps[cityIndex].getSource(), filter));
                
                // Add new image to screen
                mapLabels[cityIndex] = new JLabel(new ImageIcon(newColouredMap));
                mapLabels[cityIndex].setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
                add(mapLabels[cityIndex]);
                
            }
            
        } catch (IOException e) {
            e.printStackTrace(); // Error with reading file
        }
        
    }
    
    // Changing individual maps
    public void changeIndividualMap (int city) {
        IndividualColourFilter newFilter = new IndividualColourFilter(this);
        
        try {
            // Remove old labels
            remove(mapLabels[city]);
            revalidate();
            repaint();
            
            // Read map image
            maps[city] = ImageIO.read(new File("img/densityMap/map/lv1/"+mapNames[city]+"Lv1.png"));
            
            // Create an image with colour filter
            Image newColouredMap = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(maps[city].getSource(), newFilter));
            
            // Add new image to screen
            mapLabels[city] = new JLabel(new ImageIcon(newColouredMap));
            mapLabels[city].setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
            add(mapLabels[city]);
            
        } catch (IOException e) {
            e.printStackTrace(); // Error with reading file
        }
    }
    
    // Set colour value for the value of data in a city
    public void setColourIDs (int value) {
        if (value==0) {
            colourID = 0xFFE5E7E9; // Grey
        } else if (value==1) {
            colourID = 0xFFBBDEFB; // Lightest Blue
        } else if (value==2) {
            colourID = 0xFF90CAF9; // Light Blue
        } else if (value==3) {
            colourID = 0xFF64B5F6; // Blue
        } else if (value==4) {
            colourID = 0xFF1E88E5; // Darker Blue
        } else if (value==5) {
            colourID = 0xFF1565C0; // Darkest BLue
        }
    }
    
    public int getColourID () {
        return colourID;
    }
    
    public JComboBox getDataList () {
        return dataList;
    }
    
    public JComboBox getCityList () {
        return cityList;
    }
    
    public JComboBox getQuestionList () {
        return questionList;
    }
    
    public JButton getSubmitButton () {
        return submitButton;
    }
    
    public JTextArea getUserResults () {
        return userResults;
    }
    
    public JLabel[] getCityLabels () {
        return cityLabels;
    }
    
    public String getCityName (int index) {
        return cityOptions[index+1];
    }
    
    public String[] getMapNames () {
        return mapNames;
    }
    
    public String[] getDataOptions () {
        return dataOptions;
    }
    
    public JLabel[] getCitySelectedLabels () {
        return citySelectedLabels;
    }
    
    public JLabel[] getMapLabels () {
        return mapLabels;
    }
    
    public JLabel getHoverInformation () {
        return hoverInformation;
    }
    
}