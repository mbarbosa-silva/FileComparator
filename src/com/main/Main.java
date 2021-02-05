package com.main;

import com.util.FileType;
import java.util.List;
import com.IO.ReadFileFactory;

@SuppressWarnings("rawtypes")
public class Main {
	
    public static void main(String[] args) {
    	
		List<List> linesFromSourceFile = new ReadFileFactory()
												.defineFileType(FileType.SOURCE)
												.openFile("exemplo.txt")
												.readFileContent()
												.getFileContent();	
		
		List<List> linesFromTargetFile = new ReadFileFactory()
												.defineFileType(FileType.TARGET)
												.openFile("exemplo2.txt")
												.readFileContent()
												.getFileContent();	
		
		if(linesFromTargetFile.size() == linesFromSourceFile.size()) {
			for(int i=0;i<linesFromSourceFile.size();i++) {
				if(linesFromSourceFile.get(i).size() == linesFromTargetFile.get(i).size()) {
					for(int j=0;j<linesFromTargetFile.get(i).size();j++) {
						if(!linesFromTargetFile.get(i).get(j).equals(linesFromSourceFile.get(i).get(j))) {
							System.out.print("the elements of line " + (i+1) + " and colum " + (j+1) + " does not match:\n\ttarget file element: " + linesFromTargetFile.get(i).get(j) + "\n\tsource file element: " + linesFromSourceFile.get(i).get(j) + "\n");
						}
					}
				} else {
					System.out.print("Number of colums of the files at line " + (i+1) + " does not match:\n\ttarget file num of colums: " + linesFromTargetFile.get(i).size() + "\n\tsource file num of colums: " + linesFromSourceFile.get(i).size() + "\n");
					break;
				}
			}
		} else {
			System.out.print("Number of lines of the files does not match:\n\ttarget file num of lines: " + linesFromTargetFile.size() + "\n\tsource file num of lines: " + linesFromSourceFile.size() + "\n");
		}
				
    }
	
}
