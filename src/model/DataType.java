package model;

public enum DataType {
    
    PIE_CHART(0, "PIE CHART"),
    DENSITY_MAP(2, "DENSITY MAP"),
    LINE_CHART(3, "LINE CHART"),
    SCATTER_CHART(4, "SCATTER PLOT"),
    BAR_CHART(5, "DOUBLE BAR GRAPH");
    
    private final int value;
    private final String chartDescription;
    
    DataType (int value, String chartDescription) {
        this.value = value;
        this.chartDescription = chartDescription;
    }
    
    public int getValue () {
        return value;
    }
    
    public String getChartDescription () {
        return chartDescription;
    }
    
}
