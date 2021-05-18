package model;

public enum DataType {
    
    PIE_CHART(0), DENSITY_MAP(1), LINE_CHART(2),
    SCATTER_CHART(3), BAR_CHART(4);
    
    private final int value;
    DataType (int value) {
        this.value = value;
    }
    
    public int getValue () {
        return value;
    }
    
}
