package lexical_analyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// the execution command is "java lexcial_analyzer <<filename>>"
		//String filename = args[0];

		String filename = "./test.c";
		
		// file open and give file content to fileString of lexical_analyzer
		lexical_analyzer lex_analyzer = new lexical_analyzer(readFile(filename));

		// lexical analyzer start
		lex_analyzer.analyze();

		// lexical analyzer is terminated, print symbolTable, and save symbolTable as file
		//lex_analyzer.printSymBolTable();

		writeFile(filename, lexical_analyzer.symbolTable);
	}

	// <<filename>> file open and file-all String return
	public static char[] readFile(String filename) {
		 char[] fileString = null;
	      try {
	    	  // test.c file open
	         File file = new File(filename);
	         fileString = new char[(int)file.length()];
	         FileReader fileReader = new FileReader(file);
	         BufferedReader bufReader = new BufferedReader(fileReader);

	         // Store all string in a file to variable named "fileString"
	         bufReader.read(fileString);

	         //file close
	         bufReader.close();
	         fileReader.close();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }

				// fileString return
	      return fileString;
	   }

	// <<filename>>.out.txt file open and symbolTable save in this file
	public static void writeFile(String filename, ArrayList<SymbolTable> symbolTable) {
		char[] fileString = null;

		try {
  	  // <<filename>>.out.txt file open
			FileWriter fw = new FileWriter(filename + ".out");
      BufferedWriter bw = new BufferedWriter(fw);

      for(int i = 0; i< symbolTable.size();i++) {
      	bw.write(symbolTable.get(i).getSymbol()[0]);
      	bw.write('\t');
      	bw.write(symbolTable.get(i).getSymbol()[1]);
      	bw.write('\t');
      	bw.write(Integer.toString(symbolTable.get(i).getLineNum()));
      	bw.write('\t');
      	bw.write(Integer.toString(symbolTable.get(i).getLineIndex()));
      	bw.newLine();

      }
      bw.close();
      fw.close();
    } catch (FileNotFoundException e) {
       e.printStackTrace();
    } catch (IOException e) {
       e.printStackTrace();
    }
	}

}
