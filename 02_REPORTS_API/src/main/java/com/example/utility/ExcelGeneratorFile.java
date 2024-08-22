package com.example.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jrtp.entity.EligibilityDetails;
import com.example.jrtp.exception.ExcelCustomExceptions;

public class ExcelGeneratorFile {

	private static final Logger log = LoggerFactory.getLogger(ExcelGeneratorFile.class);
	
	public static byte[] excelFile(List<EligibilityDetails> eligDetails) throws ExcelCustomExceptions{
		log.info("ExcelFile Utility method started");
		try(XSSFWorkbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){  // Using XSSFWorkbook for .xlsx files
			
			XSSFSheet sheet = workbook.createSheet("Eligibility Details");
			XSSFRow headerRow = sheet.createRow(0);

        // Setting headers
			headerRow.createCell(0).setCellValue("Name");
			headerRow.createCell(1).setCellValue("Email");
			headerRow.createCell(2).setCellValue("Mobile");
			headerRow.createCell(3).setCellValue("Gender");
			headerRow.createCell(4).setCellValue("SSN");

        // Populating data rows
			int rowIndex = 1;
			for (EligibilityDetails entity : eligDetails) {
				XSSFRow dataRow = sheet.createRow(rowIndex++);
				dataRow.createCell(0).setCellValue(entity.getName());
				dataRow.createCell(1).setCellValue(entity.getEmail());
				dataRow.createCell(2).setCellValue(entity.getMobile());
				dataRow.createCell(3).setCellValue(entity.getGender().toString()); // Assuming Gender is an enum or needs to be converted to String
				dataRow.createCell(4).setCellValue(entity.getSsn());
			}
	    	workbook.write(outputStream);
	    	log.debug("Workbook successfully created and populated with data");
	    	return outputStream.toByteArray();
		}
		catch(Exception e) {
			log.error("Unexpected error occurred while generating file", e);
			throw new ExcelCustomExceptions("Unexpected error occurred while generating file" + e);
		}
}
}