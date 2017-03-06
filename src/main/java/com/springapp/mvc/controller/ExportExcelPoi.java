package com.springapp.mvc.controller;

import com.springapp.mvc.model.Tuser;
import jxl.write.Label;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Jarvis on 2015/9/19.
 */
public class ExportExcelPoi extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Tuser> list = (List<Tuser>) model.get("list");

        for (int i = 0; i < 3; i++) {
            Sheet sheet = workbook.createSheet("book" + i);

            String[] excelHeader = {"userid", "姓名", "数量"};
            int columnLength = excelHeader.length;

            Row row = sheet.createRow(0);
            for (int m = 0; m < columnLength; m++) {
                Cell cell = row.createCell(m);
                cell.setCellValue(excelHeader[m]);
            }

            int line = 1;
            for (Tuser tuser : list) {
                row = sheet.createRow(line);
                row.createCell(0).setCellValue(tuser.getUserid());
                row.createCell(1).setCellValue(tuser.getUsername());
                row.createCell(2).setCellValue(tuser.getUsercount());
                line++;
            }
        }

        workbook.close();
    }
}
