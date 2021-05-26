package util;

import view.DensityMapGUI;

import java.awt.image.RGBImageFilter;

public class ColourFilter extends RGBImageFilter {
    
    private final DensityMapGUI gui;
    
    public ColourFilter (DensityMapGUI gui) {
        this.gui = gui;
    }
    
    // Change map colour
    @Override
    public int filterRGB(int x, int y, int rgb) {
        
        int lv = 0xFFE5E7E9; // Default grey

        return (rgb & lv); // Return the colour to change the map to
        
    }
    
}
