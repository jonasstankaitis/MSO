package com.excelservice.utils;

public interface FileNamesProvider  {

    public String getReportSourceFileFullName();

    public String getResultFileName();

    public String getMSOTemplateFileFullName(String templateName);

    public String getMSOTemplateCopyFileFullName(String fileExtension);
    
    public String getResultFileFullName(String fileExtension);

}