package com.excelservice.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
 
@ConfigurationProperties(prefix = "path")
@Configuration("configPaths")
public class ConfigPaths {
	
    private String msoTemplatesPath;
    private String resultPath;
    
	public String getMsoTemplatesPath() {
		return msoTemplatesPath;
	}
	public void setMsoTemplatesPath(String msoTemplatesPath) {
		this.msoTemplatesPath = msoTemplatesPath;
	}
	public String getResultPath() {
		return resultPath;
	}
	public void setResultPath(String resultPath) {
		this.resultPath = resultPath;
	}
    
	
	
 
}