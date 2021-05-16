package view;

import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;

import javax.swing.*;


public class CommuteVsShelterCost extends Tool {
    
    private JLabel titleLabel = new JLabel("Most Common Commute Type vs Average Monthly Shelter Cost");
    
    public CommuteVsShelterCost () {
        setSize(1366, 768);
    }
    
    @Override
    public void addChangeListener (DatasetChangeListener datasetChangeListener) {
    
    }
    
    @Override
    public void removeChangeListener (DatasetChangeListener datasetChangeListener) {
    
    }
    
    @Override
    public DatasetGroup getGroup () {
        return null;
    }
    
    @Override
    public void setGroup (DatasetGroup datasetGroup) {
    
    }
    
}
