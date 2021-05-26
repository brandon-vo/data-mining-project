package model;

/**
 * Identifiers for all the tools
 * @author Felix
 */
public enum DataType {
    
    // Identifiers
    PIE_CHART(0, "PIE CHART"),
    DENSITY_MAP(1, "DENSITY MAP"),
    LINE_CHART(2, "LINE CHART"),
    SCATTER_CHART(3, "SCATTER PLOT"),
    BAR_CHART(4, "BAR CHART");
    
    private final int value;
    private final String chartDescription;
    
    DataType (int value, String chartDescription) {
        this.value = value;
        this.chartDescription = chartDescription;
    }
    
    // Getters
    public int getValue () {
        return value;
    }
    
    public String getChartDescription () {
        return chartDescription;
    }
    
}
