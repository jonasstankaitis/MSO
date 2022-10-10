package com.excelservice.repository;


import java.io.File;

import org.springframework.stereotype.Repository;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

@SuppressWarnings("restriction")
@Repository
public class ReportRepository {

    private static void openExcel(File file) {
        ComThread.InitSTA();

        final ActiveXComponent excel = new ActiveXComponent("Excel.Application");
        try {
            excel.setProperty("EnableEvents", new Variant(true));
            Dispatch workbooks = excel.getProperty("Workbooks")
                .toDispatch(); 

            Dispatch workBook = Dispatch.call(workbooks, "Open",
                file.getAbsolutePath()).toDispatch();

            Dispatch.call(workBook, "Save");

            Dispatch.call(workBook, "Close");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ComThread.Release();

        }
    }

    public void generateReport(File temporaryTemplateFile) {
        openExcel(temporaryTemplateFile);
       
    }


}