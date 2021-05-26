package util;

import view.DensityMapGUI;

import java.awt.image.RGBImageFilter;

public class IndividualColourFilter extends RGBImageFilter {

    private final DensityMapGUI gui;

    public IndividualColourFilter (DensityMapGUI gui) {
        this.gui = gui;
    }

    // Change map colour
    @Override
    public int filterRGB(int x, int y, int rgb) {

        int lv = gui.getColourID(); // Get colour ID

        return (rgb & lv); // Return the colour to change the map to

    }

}
