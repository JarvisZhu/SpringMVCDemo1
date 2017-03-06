package com.springapp.mvc.controller;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.springapp.mvc.model.Tuser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by Jarvis on 2015/9/19.
 */
public class ExportPdfItext5 extends AbstractItext5PdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfWriter, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        PdfWriter.getInstance(document, response.getOutputStream());// 没什么影响;

        /**
         * 父类的构造函数中设置的是setContentType("application/pdf");所以就默认在浏览器里打开了，这里覆盖默认的设置，改成下载方式;
         * 若将下面这行去掉则就直接在浏览器里打开pdf文件了；
         */
        response.setContentType("application/x-download");

        String name = URLEncoder.encode("速度快凤凰山.pdf", "UTF-8");//使得支持中文文件名;
        response.setHeader("Content-Disposition", "attachment;filename=" + name);

        document.setPageSize(new Rectangle(PageSize.A4));

        Font chineseFont = getChineseFont(36);

        document.open();

        Paragraph header = new Paragraph(new Chunk("PDF 输出测试", chineseFont));
        document.add(header);

        List<Tuser> list = (List<Tuser>) model.get("list");
        for (Tuser tuser : list) {
            Paragraph ph = new Paragraph(tuser.getUserid() + ", " + tuser.getUsername() + ", " + tuser.getUsercount(), chineseFont);
            document.add(ph);
        }

        document.close();
    }

    private static final Font getChineseFont(float size) {
        Font FontChinese = null;
        try {
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);//中文字体
            FontChinese = new Font(bfChinese, size, Font.NORMAL);
        } catch (DocumentException de) {
            de.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return FontChinese;
    }
}
