package com.IO;

import com.util.FileType;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

@SuppressWarnings("rawtypes")
public class ReadFileFactory {

	private Scanner input; 
	private FileReader file;
	private List<String> lineFileContent;
	private List<List> fileContent;
	
	private String wrapper;
	private String separator;
	private String newLine;
	
	public ReadFileFactory(){
		lineFileContent = new ArrayList<String>();
		fileContent = new ArrayList<List>();
	}
	
	public ReadFileFactory openFile(String fileName) {
		try {
			file = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		input = new Scanner(file);
		return this;
	}
	
	@SuppressWarnings("resource")
	public ReadFileFactory readFileContent() {
		
		String lineElement;
		Scanner tempScanner;
		
		/*
		 * the following line of code will remove the line separator,
		 * thus separating the file content in well defined lines.
		 */
		input.useDelimiter(newLine);
		
		while (input.hasNext()) {
			
			/*
			 * the following line is dividing the file's line into separated elements using the SEPARATOR
			 * value as parameter to divide it.
			 */
			tempScanner = new Scanner(input.next()).useDelimiter(separator);
				
			while(tempScanner.hasNext()) {
				lineElement = tempScanner.next();
				
				/*
				 * the following 2 lines of code removes the STRING wrappers
				 */
				lineElement = lineElement.substring(wrapper.length(),lineElement.length());
				lineElement = lineElement.substring(0,lineElement.length() - wrapper.length());
				
				lineFileContent.add(lineElement);
			}
		
			fileContent.add(lineFileContent);
			lineFileContent = new ArrayList<String>();
		}
		
		return this;
	}

	public List<List> getFileContent(){
		return fileContent;
	}
	
	public ReadFileFactory defineFileType(FileType fileType){
		
		String propertyFileName = "sourceFile.properties";
		
		if(fileType == FileType.TARGET)
			propertyFileName = "targetFile.properties";
		
		InputStream input = ReadFileFactory.class.getClassLoader().getResourceAsStream(propertyFileName);
		Properties prop = new Properties();
		
		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		separator = prop.getProperty("SEPARATOR");
		wrapper = prop.getProperty("WRAPPER");
		newLine = prop.getProperty("NEWLINE");
		
		return this;
	}
}