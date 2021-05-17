package view;

import controller.DensityMapController;
import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DensityMapGUI extends Tool {

    private JLabel densityMapTitleLabel = new JLabel(new ImageIcon("img/densityMap/labels/densityMapTitle.png"));
    private JLabel userPanel = new JLabel(new ImageIcon("img/densityMap/labels/userPanel.png"));
    private String[] dataOptions = new String[]{
            "- Select data -",
            "Average Dwelling Value",
            "Average Owner Monthly Shelter Cost",
            "Average Renter Monthly Shelter Cost",
            "Household Owners",
            "Household Renters"};
    private JComboBox dataList = new JComboBox<>(dataOptions);
    private JButton submitButton = new JButton("SUBMIT");
    private JLabel aurora = new JLabel(new ImageIcon("img/densityMap/map/aurora.png"));
    private JLabel eastGwillimbury = new JLabel(new ImageIcon("img/densityMap/map/eastGwillimbury.png"));
    private JLabel georgina = new JLabel(new ImageIcon("img/densityMap/map/georgina.png"));
    private JLabel king = new JLabel(new ImageIcon("img/densityMap/map/king.png"));
    private JLabel markham = new JLabel(new ImageIcon("img/densityMap/map/markham.png"));
    private JLabel newmarket = new JLabel(new ImageIcon("img/densityMap/map/newmarket.png"));
    private JLabel richmondHill = new JLabel(new ImageIcon("img/densityMap/map/richmondHill.png"));
    private JLabel stouffville = new JLabel(new ImageIcon("img/densityMap/map/stouffville.png"));
    private JLabel vaughan = new JLabel(new ImageIcon("img/densityMap/map/vaughan.png"));

    private BufferedImage image;

    public DensityMapGUI (int x, int y, int width, int height) {

        super(x, y, width, height);
        setLayout(null);
        setBackground(getBackgroundColour());

        add(getBackButton());

        densityMapTitleLabel.setBounds(0, 0, 1366, 768);
        add(densityMapTitleLabel);

        submitButton.setBounds(1065, 400, 125, 50);
        submitButton.setBorder(null);
        submitButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        submitButton.setBackground(new Color(145, 172, 219));
        add(submitButton);

        aurora.setBounds(0, 0, 1366, 768);
        eastGwillimbury.setBounds(0, 0, 1366, 768);
        georgina.setBounds(0, 0, 1366, 768);
        king.setBounds(0, 0, 1366, 768);
        markham.setBounds(0, 0, 1366, 768);
        newmarket.setBounds(0, 0, 1366, 768);
        richmondHill.setBounds(0, 0, 1366, 768);
        stouffville.setBounds(0, 0, 1366, 768);
        vaughan.setBounds(0, 0, 1366, 768);
        add(aurora);
        add(eastGwillimbury);
        add(georgina);
        add(king);
        add(markham);
        add(newmarket);
        add(richmondHill);
        add(stouffville);
        add(vaughan);

        userPanel.setBounds(0, 0, 1366, 768);
        add(userPanel);

        dataList.setBounds(35, 130, 240, 25);
        dataList.setFont(new Font("Tahoma", Font.PLAIN, 12));
        dataList.setForeground(new Color(18, 71, 115));
        add(dataList);

        setVisible(true);

    }

    public String[] getDataOptions() {
        return dataOptions;
    }

    public void setDataOptions(String[] dataOptions) {
        this.dataOptions = dataOptions;
    }

    public JComboBox getDataList() {
        return dataList;
    }

    public void setDataList(JComboBox dataList) {
        this.dataList = dataList;
    }

}