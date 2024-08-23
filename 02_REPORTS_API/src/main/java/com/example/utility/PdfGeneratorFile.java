package com.example.utility;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jrtp.entity.EligibilityDetails;
import com.example.jrtp.exception.PdfCustomExceptions;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfGeneratorFile {

	private static final Logger log = LoggerFactory.getLogger(ExcelGeneratorFile.class);
	
	public static byte[] generatPdfFile(List<EligibilityDetails> entities) throws PdfCustomExceptions {
		log.info("pdf file utility started");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(document, outputStream);
			document.open();
		
			Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			font.setSize(18);
	    
		    Paragraph p = new Paragraph("Search Report", font);
		    p.setAlignment(Paragraph.ALIGN_CENTER);
		    document.add(p);
		    
		    PdfPTable table = new PdfPTable(5);
		    table.setWidthPercentage(100f);
		    table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
		    table.setSpacingBefore(10);
		     
		    PdfPCell cell = new PdfPCell();
		    cell.setPadding(5);
		     
		    font = FontFactory.getFont(FontFactory.HELVETICA);
		    cell.setPhrase(new Phrase("Name", font));
		     
		    table.addCell(cell);
		     
		    cell.setPhrase(new Phrase("E-mail", font));
		    table.addCell(cell);
	     
		    cell.setPhrase(new Phrase("Ph no", font));
		    table.addCell(cell);
		     
		    cell.setPhrase(new Phrase("Gender", font));
		    table.addCell(cell);
		     
		    cell.setPhrase(new Phrase("SSN", font));
		    table.addCell(cell); 
		    
		    for(EligibilityDetails entity:entities) {
		    	table.addCell(entity.getName());
		    	table.addCell(entity.getEmail());
		    	table.addCell(String.valueOf(entity.getMobile()));
		    	table.addCell(String.valueOf(entity.getGender()));
		    	table.addCell(String.valueOf(entity.getSsn()));
		}
		    document.add(table);
		    
		}catch(DocumentException e) {
			log.error("Error occurred while creating the document",e);
			throw new PdfCustomExceptions("Error occurred while creating the document", e);
		}catch(Exception e) {
			log.error("An unexpected exception was caught",e);
			throw new PdfCustomExceptions("An unexpected exception was caught: ", e);
		}finally {
			if(document!=null) {
				document.close();
			}
		}
		log.debug("Converted to byte array");
	    return outputStream.toByteArray();
	   
}
}

