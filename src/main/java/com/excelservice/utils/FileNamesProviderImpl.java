package com.excelservice.utils;

import java.util.UUID;

public class FileNamesProviderImpl implements FileNamesProvider {
   
    private String templatesPath;
    private String resultPath;
    private String uniqueNameKey;
    private String resultFileName;
    private String reportSourceFileFullName;

    public FileNamesProviderImpl(String templatesPath, String resultPath) {
    	   	
        this.uniqueNameKey = UUID.randomUUID().toString();
        this.resultFileName = Constants.RESULT_FILENAME_PREFIX + this.uniqueNameKey;
        this.reportSourceFileFullName = Constants.RESULT_PATH + this.uniqueNameKey + "." + Constants.DATA_FILE_PREFIX;
        this.templatesPath = templatesPath;
        this.resultPath = resultPath;
    }

    public String getReportSourceFileFullName() {
        return reportSourceFileFullName;
    }

    public String getResultFileName() {
        return resultFileName;
    }

    public String getMSOTemplateFileFullName(String templateName) {
        return  this.templatesPath + templateName;
    }

    public String getMSOTemplateCopyFileFullName(String fileExtension) {
        return this.resultPath + this.uniqueNameKey + "." + fileExtension;
    }
    
    public String getResultFileFullName(String fileExtension) {
        return this.resultPath + this.uniqueNameKey + "." + fileExtension;
    }

}