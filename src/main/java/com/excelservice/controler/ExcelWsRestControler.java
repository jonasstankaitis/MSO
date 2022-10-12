package com.excelservice.controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excelservice.config.ConfigPaths;
import com.excelservice.repository.ReportRepository;
import com.excelservice.service.ReportSource;
import com.excelservice.utils.FileNamesProvider;
import com.excelservice.utils.FileNamesProviderImpl;
import com.excelservice.utils.ReportData;

@RestController
public class ExcelWsRestControler {

    @Autowired
    private ReportRepository repository;

    @Autowired
    private ReportSource reportSource;
    
    @Autowired
	ConfigPaths configPaths;
      
    //TODO: @RequestMapping(path = "/getRegisteredTemplatesList", method = RequestMethod.GET)
    //TODO: @RequestMapping(path = "/registerTemplate", method = RequestMethod.POST)    
    
    @RequestMapping(path = "/getreport", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response) throws SQLException, IOException {
            	
    	FileNamesProvider fileNamesProvider = new FileNamesProviderImpl(configPaths.getMsoTemplatesPath(),configPaths.getResultPath());
    	
        ReportData reportData = reportSource.getReportSource(fileNamesProvider.getResultFileName());

        File reportSourceFile = new File(fileNamesProvider.getReportSourceFileFullName());
        FileUtils.copyInputStreamToFile(reportData.getReportSource(), reportSourceFile);

        File templateFile = new File(fileNamesProvider.getMSOTemplateFileFullName(reportData.getReportTemplateName()));
        File temporaryTemplateFile = new File(fileNamesProvider.getMSOTemplateCopyFileFullName(reportData.getReportTemplateName()));
        FileUtils.copyFile(templateFile, temporaryTemplateFile);
       
        repository.generateReport(temporaryTemplateFile);
        File resultFile = new File(fileNamesProvider.getResultFileFullName(reportData.getReportTemplateName()));

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + templateFile.getName() + "\"");
        response.setContentLength((int) resultFile.length());
        response.setContentType("application/octet-stream");

        OutputStream outputStream = response.getOutputStream();
        FileInputStream inputStream = new FileInputStream(resultFile);

        IOUtils.copy(inputStream ,outputStream);

        outputStream.close(); 
        inputStream.close();
        resultFile.delete();
        temporaryTemplateFile.delete();
        reportSourceFile.delete();

    }
}