package com.excelservice.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.excelservice.utils.ReportData;

@Service
public interface ReportSource {
	 public ReportData getReportSource(String SourceFilename) throws SQLException;
}
