package controller;

import view.DensityMapGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.RGBImageFilter;

public class DensityMapController {
    
    // get selected value for data type
    // get data of selected value
    // change map colours based on data
    
    public static class RedBlueSwapFilter extends RGBImageFilter {
        public int filterRGB(int x, int y, int rgb) {
            return ((rgb & 0xFFE5E7E9));
            // 0xFFE5E7E9 grey
            // 0xFFE2F1FC
            // 0xFFBBDEFB
            // 0xFF90CAF9
            // 0xFF64B5F6
            // 0xFF1E88E5
            // 0xFF1565C0
            
        }
    }
}

