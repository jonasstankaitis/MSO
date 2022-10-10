package com.excelservice.service;

import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import com.excelservice.utils.Constants;
import com.excelservice.utils.ReportData;

@Service
public class ReportSourceImpl implements ReportSource {

  @PersistenceContext
  private EntityManager entityManager;
  
  public ReportData getReportSource(String SourceFilename) throws SQLException {  
	StoredProcedureQuery nlsSetter = entityManager.createStoredProcedureQuery(Constants.NLS_PROCEDURE);
	nlsSetter.execute();
	
    StoredProcedureQuery sourceGetter = entityManager.createStoredProcedureQuery(Constants.REPORT_SOURCE_PROCEDURE);

    sourceGetter.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
    sourceGetter.registerStoredProcedureParameter(2, String.class, ParameterMode.OUT);
    sourceGetter.registerStoredProcedureParameter(3, String.class, ParameterMode.OUT);
    sourceGetter.registerStoredProcedureParameter(4, Blob.class, ParameterMode.OUT);

    sourceGetter.setParameter(1, SourceFilename);

    sourceGetter.execute();

    String msoTemplateName = (String) sourceGetter.getOutputParameterValue(2);

    String fileExtension = (String) sourceGetter.getOutputParameterValue(3);

    Blob outMessage = (Blob) sourceGetter.getOutputParameterValue(4);
    return new ReportData(outMessage.getBinaryStream(), msoTemplateName, fileExtension);

  }
}