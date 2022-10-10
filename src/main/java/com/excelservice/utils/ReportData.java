package com.excelservice.utils;

import java.io.InputStream;

public class ReportData {

	private InputStream reportSource;
	private String reportTemplateName;
	private String fileExtension;
	
	public ReportData(InputStream reportSource, String reportTemplateName, String fileExtension) {
		this.reportSource = reportSource;
		this.reportTemplateName = reportTemplateName;
		this.fileExtension = fileExtension;
	}

	public InputStream getReportSource() {
		return reportSource;
	}

	public String getReportTemplateName() {
		return reportTemplateName;
	}

	public String getFileExtension() {
		return fileExtension;
	} 
	
	
	
	
	
}
