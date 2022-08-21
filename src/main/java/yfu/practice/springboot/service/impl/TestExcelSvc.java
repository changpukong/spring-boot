package yfu.practice.springboot.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import yfu.practice.springboot.dto.TwdDeposit;
import yfu.practice.springboot.dto.base.Listable;

@Service
public class TestExcelSvc {

	public ResponseEntity<byte[]> genXlxsFile() throws IOException {
		// 待寫入資料
		List<TwdDeposit> twdDepositList = Arrays.asList(
				new TwdDeposit("A123456789", "活存", "03117710090700", "888"),
				new TwdDeposit("X101010107", "定存", "03117710090787", "999"));

		try (Workbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();) {

			// 創建頁籤
			Sheet twdSheet = workbook.createSheet("台幣");
			Sheet ifxSheet = workbook.createSheet("外幣");

			// 寫入header
			writeRow(twdSheet.createRow(0), Arrays.asList("ID", "帳戶類別", "帳號", "餘額"));
			writeRow(ifxSheet.createRow(0), Arrays.asList("ID", "帳戶類別", "帳號", "幣別", "餘額"));

			// 寫入資料
			writeData(twdSheet, twdDepositList);

			// 輸出成byte array
			workbook.write(baos);
			baos.flush();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			headers.setContentDisposition(ContentDisposition.attachment().filename("test.xlsx").build());
			
			return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
		}
	}

	private void writeData(Sheet sheet, List<? extends Listable> data) {
		for (int i = 0; i < data.size(); i++) {
			Row row = sheet.createRow(i + 1);
			writeRow(row, data.get(i).toList());
		}
	}

	private void writeRow(Row row, List<String> data) {
		for (int i = 0; i < data.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(data.get(i));
		}
	}
	
}