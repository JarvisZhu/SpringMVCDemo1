package com.springapp.mvc.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * 对于pdf格式的输出，spring 缺省提供的模板类为AbstractPdfView,并利用iText来实现pdf的输出。但此类只支持iText 2.x的版本，而目前iText最新的版本是5.x，
 * 为支持最新版本的iText类库，需要根据AbstractPdfView的内容依样画葫芦自己实现一个PdfView模板类
 */
public abstract class AbstractItext5PdfView extends AbstractView {
    public AbstractItext5PdfView() {
        setContentType("application/pdf");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream baos = createTemporaryOutputStream();
        Document document = newDocument();
        PdfWriter writer = newWriter(document, baos);
        prepareWriter(model, writer, request);
        buildPdfMetadata(model, document, request);
        document.open();
        buildPdfDocument(model, document, writer, request, response);
        document.close();
        writeToResponse(response, baos);
    }

    protected Document newDocument() {
        return new Document(PageSize.A4);
    }

    protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
        return PdfWriter.getInstance(document, os);
    }

    protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request) throws DocumentException {
        writer.setViewerPreferences(getViewerPreferences());
    }

    protected int getViewerPreferences() {
        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
    }

    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
    }

    protected abstract void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
