package controller;

import model.MyDataset;
import view.DensityMapGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DensityMapController extends ToolController implements ActionListener {

    private final DensityMapGUI densityGUI; // Access DensityMapGUI

    // Constructor
    public DensityMapController(DensityMapGUI densityGUI) {
        this.densityGUI = densityGUI; // Density GUI
        setUpListeners(); // Setup Listeners
        densityGUI.changeMapColour(); // Initial map colour set to grey
    }

    // Setup listeners
    public void setUpListeners() {
        densityGUI.getSubmitButton().addActionListener(this);
        densityGUI.getDataList().addActionListener(this);
        for (int index = 0; index < densityGUI.getCityLabels().length; index++)
            densityGUI.getCityLabels()[index].addMouseListener(this);
    }

    // Enable components
    public void enableComponents(boolean enable) {
        densityGUI.getCityList().setEnabled(enable);
        densityGUI.getSubmitButton().setEnabled(enable);
        densityGUI.getUserResults().setEnabled(enable);
        densityGUI.getQuestionList().setEnabled(enable);
    }

    // Get user selected dataset
    public void getSelectedData() {
        if (densityGUI.getDataList().getSelectedIndex() == 0) {
            enableComponents(false);
            updateSelectedData();
            updateQuestionBox(0);
            densityGUI.getUserResults().setText("Please select a dataset!");
            return;
        }
        enableComponents(true);
        densityGUI.setDataGroup((String) densityGUI.getDataList().getSelectedItem());
        updateSelectedData();
        updateQuestionBox(densityGUI.getDataList().getSelectedIndex());
    }

    // Update map colour and user results text
    public void updateSelectedData() {
        densityGUI.getUserResults().setText("Please input your information!");
        densityGUI.changeMapColour();
        String text = "Hover region to view" + "<br>" + "Click region for more";
        densityGUI.getHoverInformation().setText("<html><div style='text-align: center;'>" + text + "<html>");

    }

    // Set question combo box
    public void updateQuestionBox(int index) {
        // Store list of options for the question
        List<String> questions = new ArrayList<>();

        if (index == 1) {
            String[] optionList = new String[]{
                    "- Select value of your dwelling -",
                    "$0 - $500,000", "$500,000 - $1,000,000", "$1,000,000 - $1,500,000", "$1,500,000 - $2,000,000",
                    "$2,000,000 - $2,500,000", "$2,500,000 - $3,000,000", "$3,000,000 - $3,500,000"};
            questions.addAll(Arrays.asList(optionList));
        } else if (index == 2) {
            String[] optionList = new String[]{
                    "- Select average monthly shelter cost -",
                    "$0 - $500", "$500 - $1,000", "$1,000 - $1,500", "$1,500 - $2,000",
                    "$2,000 - $2,500", "$2,500 - $3,000", "$3,000 - $3,500", "$3,500 - $4,000"};
            questions.addAll(Arrays.asList(optionList));
        } else if (index == 3) {
            String[] optionList = new String[]{
                    "- Select average monthly shelter cost -",
                    "$0 - $500", "$500 - $1,000", "$1,000 - $1,500",
                    "$1,500 - $2,000", "$2,000 - $2,500", "$2,500 - $3,000"};
            questions.addAll(Arrays.asList(optionList));
        } else if (index == 4) {
            String[] optionList = new String[]{
                    "- Are you a household owner? -",
                    "I am a household owner", "I am not a household owner"};
            questions.addAll(Arrays.asList(optionList));
        } else if (index == 5) {
            String[] optionList = new String[]{
                    "- Are you a household renter? -",
                    "I am a household renter", "I am not a household renter"};
            questions.addAll(Arrays.asList(optionList));
        }

        // Set question list based on selected index
        densityGUI.getQuestionList().setModel(new DefaultComboBoxModel<>(questions.toArray(new String[0])));

    }

    @Override
    public void initializeDataToDisplay(MyDataset[] dataset) {

        // Get profile of housing data
        String profileOfHousing = densityGUI.getValidGroupNames(1).get(0);
        densityGUI.setDataGroup(profileOfHousing);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int selectedCategory = densityGUI.getDataList().getSelectedIndex(); // Access selected data
        int chosen = 0; // Chose household owners or household renters
        String userInput = ""; // User input to display
        String dataTitle = ""; // Type of data to display
        String result = ""; // Final result
        double housingDataValue; // Housing data values
        if (densityGUI.getCityList().getSelectedIndex() == 0)
            housingDataValue = 0;
        else
            housingDataValue = densityGUI.getDataGroup().get(0).getCities().get(densityGUI.getCityName(densityGUI.getCityList().getSelectedIndex() - 1));
        double ownerOrRenter; // Owner or renter values
        if (densityGUI.getCityList().getSelectedIndex() == 0)
            ownerOrRenter = 0;
        else
            ownerOrRenter = densityGUI.getDataGroup().get(chosen).getOGCityData(densityGUI.getCityName(densityGUI.getCityList().getSelectedIndex() - 1));

        // User clicked submit button
        if (e.getSource() == densityGUI.getSubmitButton()) {

            // User did not fill out all questions
            if (densityGUI.getCityList().getSelectedIndex() == 0 || densityGUI.getQuestionList().getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Please answer all questions!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Get values of the inputted answer to question
            String questionString = densityGUI.getQuestionList().getSelectedItem().toString();
            String questionArray[] = questionString.split(" ", 3);

            // For all cities
            for (int x = 0; x < densityGUI.getCityLabels().length; x++) {
                // Average dwelling value, Average owner monthly shelter cost, Average renter monthly shelter cost
                if (selectedCategory >= 1 && selectedCategory <= 3) {

                    // Split question input to usable integer values
                    String indexOne = questionArray[0];
                    String sOne = indexOne.replace("$", "").replace(",", "");
                    int setOne = Integer.parseInt(sOne);

                    String indexTwo = questionArray[2];
                    String sTwo = indexTwo.replace("$", "").replace(",", "");
                    int setTwo = Integer.parseInt(sTwo);

                    // Get users question input
                    for (int city = 1; city < densityGUI.getQuestionList().getItemCount(); city++) {
                        if (densityGUI.getQuestionList().getSelectedIndex() == city)
                            userInput = densityGUI.getQuestionList().getSelectedItem().toString();
                    }

                    if (selectedCategory == 1)
                        dataTitle = "Average Dwelling Value: $";
                    else if (selectedCategory == 2 || selectedCategory == 3)
                        dataTitle = "Average Shelter Cost per Month: $";

                    if (housingDataValue >= setOne && housingDataValue <= setTwo) {
                        result = "According to the dataset,\nyou are apart of the average person in "
                                + densityGUI.getCityList().getSelectedItem();
                    } else if (housingDataValue >= setOne) {
                        result = "According to the dataset,\nyou are below the average person in "
                                + densityGUI.getCityList().getSelectedItem();
                    } else {
                        result = "According to the dataset,\nyou are above the average person in "
                                + densityGUI.getCityList().getSelectedItem();
                    }
                    // Display results in text area
                    densityGUI.getUserResults().setText("Region being viewed: " +
                            densityGUI.getCityList().getSelectedItem() + "\n" + dataTitle
                            + Math.round(housingDataValue) + "\n\n" + "Your input: " + userInput + "\n\n" + result);

                    // Household owner and renter count
                } else if (selectedCategory == 4 || selectedCategory == 5) {

                    if (selectedCategory == 4) {
                        dataTitle = "Total Owners: ";
                        if (densityGUI.getQuestionList().getSelectedIndex() == 1)
                            userInput = "household owner";
                        else
                            userInput = "not a household owner";
                    } else if (selectedCategory == 5) {
                        dataTitle = "Total Renters: ";
                        if (densityGUI.getQuestionList().getSelectedIndex() == 1)
                            userInput = "household renter";
                        else
                            userInput = "not a household renter";
                    }

                    // Access total owners or renters in a city
                    double ownerTotal = densityGUI.getDataGroup().get(0).getOGCityData(densityGUI.getCityName(x));
                    double renterTotal = densityGUI.getDataGroup().get(1).getOGCityData(densityGUI.getCityName(x));

                    // More owners than renters
                    if (ownerTotal > renterTotal) {
                        if (userInput == "household owner") {
                            result = "According to the dataset, " + Math.round(ownerTotal) + " people own a household,\nand "
                                    + Math.round(renterTotal) + " rent a household.\nYou are apart of the average person in "
                                    + densityGUI.getCityList().getSelectedItem();
                        } else {
                            result = "According to the dataset, " + Math.round(ownerTotal) + " people own a household,\nand "
                                    + Math.round(renterTotal) + " rent a household.\nYou are not apart of the average person in "
                                    + densityGUI.getCityList().getSelectedItem();
                        }

                        // More renters than owners (rare case but implemented just in case)
                    } else if (ownerTotal < renterTotal) {
                        if (userInput == "household renter") {
                            result = "According to the dataset, " + Math.round(ownerTotal) + " people own a household,\nand "
                                    + Math.round(renterTotal) + " rent a household.\nYou are apart of the average person in "
                                    + densityGUI.getCityList().getSelectedItem();
                        } else {
                            result = "According to the dataset, " + Math.round(ownerTotal) + " people own a household,\nand "
                                    + Math.round(renterTotal) + " rent a household.\nYou are not apart of the average person in "
                                    + densityGUI.getCityList().getSelectedItem();
                        }
                    }
                    // Display results in text area
                    densityGUI.getUserResults().setText("Region being viewed: "
                            + densityGUI.getCityList().getSelectedItem() + "\n"
                            + dataTitle + Math.round(ownerOrRenter) + "\n\n" + "You are " + userInput + "\n\n"
                            + result);

                }
            }
            JOptionPane.showMessageDialog(null, "Your results have been submtited!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // User selected a data category
        } else if (e.getSource() == densityGUI.getDataList()) {
            getSelectedData();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        // Check all city labels
        for (int index = 0; index < densityGUI.getCityLabels().length; index++) {

            if (e.getSource() == densityGUI.getCityLabels()[index]) { // User clicked a city label

                int selectedCategory = densityGUI.getDataList().getSelectedIndex(); // Access selected data

                if (selectedCategory != 0) { // Users selected category

                    int chosen = 0; // For accessing owners and renters
                    String suffix = ""; // Text format for owners and renters
                    String definition = ""; // Define a data set for user understanding

                    // Get selected category and display data
                    if (selectedCategory >= 1 && selectedCategory <= 3) {
                        if (selectedCategory == 1) {
                            definition = "Owner households in non-farm, non-reserve private dwellings\n" +
                                    "Average value of dwellings ($)";
                        }else if (selectedCategory == 2) {
                            definition = "Owner households in non-farm, non-reserve private dwellings\n" +
                                    "Average monthly shelter costs for owned dwellings ($)";
                        }else if (selectedCategory == 3) {
                            definition = "Tenant households in non-farm, non-reserve private dwellings\n" +
                                    "Average monthly shelter costs for rented dwellings ($)";
                        }

                        JOptionPane.showMessageDialog(null,
                                densityGUI.getDataOptions()[selectedCategory] + " : \n" +
                                        "$" + Math.round(densityGUI.getDataGroup().get(0)
                                        .getCities().get(densityGUI.getCityName(index))) + "\n\n" +
                                        "Definition:\n" + definition + "\n\n" +
                                        "Retrieved from Statistics Canada, 2016 Census",
                                densityGUI.getMapNames()[index].toUpperCase() + " DATA",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else if (selectedCategory == 4 || selectedCategory == 5) {
                        if (selectedCategory == 4) {
                            chosen = 0;
                            suffix = " owners";
                            definition = "Private households by tenure - Owner";
                        } // Household Owners
                        else if (selectedCategory == 5) {
                            chosen = 1;
                            suffix = " renters";
                            definition = "Private households by tenure - Renter";
                        } // Household Renters
                        JOptionPane.showMessageDialog(null,
                                densityGUI.getDataOptions()[selectedCategory] + " : \n" +
                                        Math.round(densityGUI.getDataGroup().get(chosen).
                                                getOGCityData(densityGUI.getCityName(index))) + suffix + "\n\n" +
                                        "Definition:\n" + definition + "\n\n" +
                                        "Retrieved from Statistics Canada, 2016 Census",
                                densityGUI.getMapNames()[index].toUpperCase() + " DATA",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    return;
                } else { // User did not select a dataset
                    JOptionPane.showMessageDialog(null,
                            "You have not selected a dataset!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        int selectedCategory = densityGUI.getDataList().getSelectedIndex(); // Access selected data

        // Hovering over a city label
        for (int index = 0; index < densityGUI.getCityLabels().length; index++) {
            if (e.getSource() == densityGUI.getCityLabels()[index]) {
                if (densityGUI.getDataList().getSelectedIndex() != 0) {
                    if (selectedCategory >= 1 && selectedCategory <= 3) {
                        int value = Math.round(Float.parseFloat(densityGUI.getDataGroup().get(0).getCities().get(densityGUI.getCityName(index)).toString()));
                        String avgDwell = "Avg Dwelling Value" + "<br>" + "$" + value;
                        String costMonthOwner = "Avg Owner $/Month" + "<br>" + "$" + value;
                        String costMonthRenter = "Avg Renter $/Month" + "<br>" + "$" + value;
                        if (selectedCategory == 1)
                            densityGUI.getHoverInformation().setText("<html><div style='text-align: center;'>" + avgDwell + "<html>");
                        else if (selectedCategory == 2)
                            densityGUI.getHoverInformation().setText("<html><div style='text-align: center;'>" + costMonthOwner + "<html>");
                        else if (selectedCategory == 3)
                            densityGUI.getHoverInformation().setText("<html><div style='text-align: center;'>" + costMonthRenter + "<html>");
                    } else if (selectedCategory == 4 || selectedCategory == 5) {
                        double ownerTotal = densityGUI.getDataGroup().get(0).getOGCityData(densityGUI.getCityName(index));
                        double renterTotal = densityGUI.getDataGroup().get(1).getOGCityData(densityGUI.getCityName(index));
                        String owner = "Household Owners" + "<br>" + Math.round(ownerTotal) + " owners";
                        String renter = "Household Renters" + "<br>" + Math.round(renterTotal) + " renters";
                        if (selectedCategory == 4) {
                            densityGUI.getHoverInformation().setText("<html><div style='text-align: center;'>" + owner + "<html>");
                        } else if (selectedCategory == 5) {
                            densityGUI.getHoverInformation().setText("<html><div style='text-align: center;'>" + renter + "<html>");
                        }
                    }
                    densityGUI.remove(densityGUI.getMapLabels()[index]); // Remove old label
                    densityGUI.revalidate();
                    densityGUI.repaint();
                    densityGUI.add(densityGUI.getCitySelectedLabels()[index]); // Replace with selected label
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        // Unhovering over a city label
        for (int index = 0; index < densityGUI.getCityLabels().length; index++) {
            if (e.getSource() == densityGUI.getCityLabels()[index]) {
                if (densityGUI.getDataList().getSelectedIndex() != 0)
                    densityGUI.getHoverInformation().setText("");
                densityGUI.remove(densityGUI.getCitySelectedLabels()[index]); // Remove selected label
                densityGUI.revalidate();
                densityGUI.repaint();
                densityGUI.add(densityGUI.getMapLabels()[index]); // Add original map label
            }
        }
    }
}