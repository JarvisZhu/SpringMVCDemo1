package com.springapp.mvc.controller;

import com.springapp.mvc.model.Tuser;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Jarvis on 2015/9/19.
 */
public class ExportExcelJxl extends AbstractJExcelView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, WritableWorkbook wb, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Tuser> list = (List<Tuser>) model.get("list");

        /** 写多页 */
        for (int i = 0; i < 3; i++) {
            WritableSheet sheet = wb.createSheet("测试导出Excel" + i, i);
            sheet.setRowView(0, 400);// 设置列宽

            WritableCellFormat style = new WritableCellFormat();
            style.setBackground(Colour.LIME);
            style.setAlignment(Alignment.CENTRE);

            // 列头
            String[] excelHeader = {"userid", "姓名", "数量"};

            for (int m = 0; m < excelHeader.length; m++) {
                sheet.setColumnView(m, 20);// 设置行高
                sheet.addCell(new Label(m, 0, excelHeader[m], style));
            }

            int line = 1;
            WritableCellFormat datastyle = new WritableCellFormat();
            datastyle.setAlignment(Alignment.RIGHT);

            for (Tuser tuser : list) {
                sheet.addCell(new Label(0, line, tuser.getUserid().toString(), datastyle));
                sheet.addCell(new Label(1, line, tuser.getUsername(), datastyle));
                sheet.addCell(new Label(2, line, tuser.getUsercount().toString(), datastyle));
                line++;
            }
        }

//        wb.close();// 使用Jxl，这里不能close，否则下载的文件损坏无法打开，poi就可以关闭;
    }
}
