package lexical_analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		lexical_analyzer lexi = new lexical_analyzer(readFile("test.c"));
		lexi.analyze();
		lexi.printSymBolTable();
		
	}
	 public static char[] readFile(String filename) {
		 char[] fileString = null;
	      try {
	         File file = new File(filename);
	         fileString = new char[(int)file.length()];
	         FileReader fileReader = new FileReader(file);
	         BufferedReader bufReader = new BufferedReader(fileReader);
	         bufReader.read(fileString);
//	         buffer.append(fileString);
	         bufReader.close(); 
	         fileReader.close();
	      } catch (FileNotFoundException e) {  
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	      return fileString;
	   }

}
