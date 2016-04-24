package com.me.springview;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.me.pojo.JobApplication;

public class PdfReportView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> map, Document pdfdoc, PdfWriter pdfwriter, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ArrayList<JobApplication> applications = (ArrayList<JobApplication>) request.getSession().getAttribute("reportData");
		
		Paragraph paragraph1 = new Paragraph();
        paragraph1.add("Hello World");
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        pdfdoc.add(paragraph1);
        pdfdoc.add(Chunk.NEWLINE);
        
        Table table = new Table(3);
		table.addCell("Job Title");
		table.addCell("Salary");
		table.addCell("Status");
        
        for (JobApplication application : applications ) {
        	System.out.print(application.getApplicationId());
			table.addCell(application.getJob().getJobTitle());
			table.addCell(String.valueOf(application.getJob().getPay()));
			table.addCell(application.getStatus());
		}
        
        pdfdoc.add(table);
        
        pdfdoc.add(Chunk.NEWLINE);
        pdfdoc.add(Chunk.NEWLINE);
        Paragraph paragraph6 = new Paragraph();
        paragraph6.add("Your report so far. Keep working!!");
        paragraph6.setAlignment(Element.ALIGN_JUSTIFIED);
        pdfdoc.add(paragraph6);
         
        pdfdoc.close();
		
	}

}
