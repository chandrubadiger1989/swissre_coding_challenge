package com.swissre.organizationservice.config;

public class AppConfig {
    private String csvPath;
    private int minPrct;
    private int maxPrct;
    private int maxReports;
    
	public String getCsvPath() {
		return csvPath;
	}
	public void setCsvPath(String csvPath) {
		this.csvPath = csvPath;
	}
	public int getMinPrct() {
		return minPrct;
	}
	public void setMinPrct(int minPrct) {
		this.minPrct = minPrct;
	}
	public int getMaxPrct() {
		return maxPrct;
	}
	public void setMaxPrct(int maxPrct) {
		this.maxPrct = maxPrct;
	}
	public int getMaxReports() {
		return maxReports;
	}
	public void setMaxReports(int maxReports) {
		this.maxReports = maxReports;
	}
    
}