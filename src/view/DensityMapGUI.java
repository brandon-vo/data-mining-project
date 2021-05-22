package view;

import model.MyDataset;
import util.ColourFilter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class DensityMapGUI extends Tool {

    private JLabel densityMapTitleLabel = new JLabel(new ImageIcon("img/titles/densityMapTitle.png"));
    private JLabel userPanel = new JLabel(new ImageIcon("img/densityMap/userPanel.png"));
    private String[] dataOptions = new String[]{
            "- Select data -",
            "Average Dwelling Value",
            "Average Owner Monthly Shelter Cost",
            "Average Renter Monthly Shelter Cost",
            "Household Owners",
            "Household Renters"};
    private JComboBox dataList = new JComboBox<>(dataOptions);

    private String[] cityOptions = new String[]{
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
    private JComboBox questionList = new JComboBox();
    private JButton submitButton = new JButton("SUBMIT");
    private JTextArea userResults = new JTextArea();

    private BufferedImage aurora;
    private BufferedImage eastGwillimbury;
    private BufferedImage georgina;
    private BufferedImage king;
    private BufferedImage markham;
    private BufferedImage newmarket;
    private BufferedImage richmondHill;
    private BufferedImage stouffville;
    private BufferedImage vaughan;
    private BufferedImage[] maps = {aurora, eastGwillimbury, georgina, king, markham,
            newmarket, richmondHill, stouffville, vaughan};

    private String[] mapNames = {"aurora", "eastGwillimbury", "georgina", "king", "markham",
            "newmarket", "richmondHill", "stouffville", "vaughan"};
    private JLabel[] mapLabels = new JLabel[9];
    private JLabel[] cityLabels = new JLabel[9];

    public DensityMapGUI() {

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

        // City labels
        for (int label = 0; label < cityLabels.length; label++) {
            cityLabels[label] = new JLabel(new ImageIcon("img/densityMap/map/labels/" + mapNames[label] + "label.png"));
            add(cityLabels[label]);
        }

        // Set city label position
        cityLabels[0].setBounds(285, 440, 100, 50); // Aurora
        cityLabels[1].setBounds(315, 280, 100, 100); // East Gwillimbury
        cityLabels[2].setBounds(360, 140, 100, 100); // Georgina
        cityLabels[3].setBounds(165, 460, 100, 100); // King
        cityLabels[4].setBounds(400, 530, 100, 100); // Markham
        cityLabels[5].setBounds(280, 360, 100, 100); // Newmarket
        cityLabels[6].setBounds(295, 500, 100, 100); // Richmond Hill
        cityLabels[7].setBounds(380, 410, 100, 100); // Stouffville
        cityLabels[8].setBounds(200, 580, 100, 100); // Vaughan

        userPanel.setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        add(userPanel);

        // List of data combo box
        dataList.setBounds(35, 130, 240, 25);
        dataList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        dataList.setBackground(COMBO_BOX_COLOUR);
        add(dataList);

    }

    // Change map colour
    public void changeMapColour() {

        ColourFilter filter = new ColourFilter(this);

        try {

            for (int cityMap = 0; cityMap < maps.length; cityMap++) {

                // Remove the old city label if it exists
                if (mapLabels[cityMap] != null) {
                    remove(mapLabels[cityMap]);
                    revalidate();
                    repaint();
                }

                maps[cityMap] = ImageIO.read(new File("img/densityMap/map/lv1/" + mapNames[cityMap] + "Lv1.png"));

                Image newColouredMap = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(maps[cityMap].getSource(), filter));

                mapLabels[cityMap] = new JLabel(new ImageIcon(newColouredMap));
                mapLabels[cityMap].setBounds(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
                add(mapLabels[cityMap]);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initializeDataToDisplay(MyDataset[] dataset) {

//        String profileOfHousing = getValidGroups(1).get(0);
//        setDataGroup(dataset[1].getDataset().get(profileOfHousing));

    }

    public JComboBox getDataList() {
        return dataList;
    }

    public JComboBox getCityList() {
        return cityList;
    }

    public JComboBox getQuestionList() {
        return questionList;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JTextArea getUserResults() {
        return userResults;
    }

    public String[] getMapNames() { return mapNames; }

    public JLabel[] getCityLabels() { return cityLabels; }

}