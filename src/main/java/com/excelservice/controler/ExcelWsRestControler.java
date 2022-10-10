package com.excelservice.controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excelservice.repository.ReportRepository;
import com.excelservice.service.ReportSource;
import com.excelservice.utils.Constants;
import com.excelservice.utils.ReportData;

@RestController
public class ExcelWsRestControler {

	@Autowired
	private ReportRepository repository;
		
	@Autowired
	private ReportSource reportSource;
		
	@RequestMapping(path = "/getreport", method = RequestMethod.GET)
	public void getFile(HttpServletResponse response) throws  SQLException, IOException{
		
      String uniqueReportKey = UUID.randomUUID().toString();
      String resultFileName = "res"+uniqueReportKey;
      
	  ReportData reportData = reportSource.getReportSource(resultFileName);
		
      File reportSourceFile = new File(Constants.RESULT_PATH + uniqueReportKey + ".TXT");
        
      FileUtils.copyInputStreamToFile(reportData.getReportSource(), reportSourceFile);
       
       
      File templateFile = new File(Constants.MSO_TEMPLATES_PATH + reportData.getReportTemplateName());
      File temporaryTemplateFile = new File(Constants.RESULT_PATH + uniqueReportKey + "." + reportData.getFileExtension());
      FileUtils.copyFile(templateFile,temporaryTemplateFile);
       
      
       repository.generateReport(temporaryTemplateFile);
       File resultFile = new File(Constants.RESULT_PATH + resultFileName + "." + reportData.getFileExtension());
          
	   response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+templateFile.getName()+"\"");
	   response.setContentLength((int) resultFile.length());
	   response.setContentType("application/octet-stream");
	    
	   OutputStream out = response.getOutputStream();
	   FileInputStream in = new FileInputStream(resultFile);
	    
	   IOUtils.copy(in,out);

	   out.close();
	   in.close();
	   resultFile.delete();
	   temporaryTemplateFile.delete();
	   reportSourceFile.delete(); 
	
	}
}