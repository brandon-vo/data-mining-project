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
        
        int lv = 0;
        switch (gui.getDataList().getSelectedIndex()) {
            
            case 0: lv = 0xFFE5E7E9; break;
            case 1: lv = 0xFF90CAF9; break;
            case 2: lv = 0xFF90CAF9; break;
            case 3: lv = 0xFF64B5F6; break;
            case 4: lv = 0xFF1E88E5; break;
            case 5: lv = 0xFF1565C0; break;
            
        }
        return (rgb & lv);
        
    }
    
}
