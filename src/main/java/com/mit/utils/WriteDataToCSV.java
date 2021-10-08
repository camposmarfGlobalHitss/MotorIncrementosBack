package com.mit.utils;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class WriteDataToCSV {

	public static void writeToCSV(PrintWriter writer, List<String> students) {
		try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
			for (String stud : students) {
				csvPrinter.printRecord("prueba","de", "impresion");
			}
			csvPrinter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
