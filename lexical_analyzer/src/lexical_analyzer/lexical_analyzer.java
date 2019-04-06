package lexical_analyzer;

import java.util.ArrayList;

public class lexical_analyzer {
	public String token;
	public static char[] fileString;
	public static int fileIndex;
	public static int line;
	public static int lineIndex;
	public ArrayList<String[]> symbolTable;
	
	public lexical_analyzer(char[] fileString) {
		this.fileString = fileString;
		this.fileIndex = 0;
		this.line = 1;
		this.lineIndex = 0;
		symbolTable = new ArrayList<String[]>();
		
	}
	public void analyze() {
		while(fileIndex < fileString.length) {
			lexeme lexeme = new lexeme();
			String temp = lexeme.lex();
			String err = "err";
			if(temp.equals("WHITESPACE"))
			{
				symbolTable.add(new String[] {temp,lexeme.lexeme});
			}else if(temp.equals(err)) {
				symbolTable.clear();
				break;
			}
		}
	}
	public ArrayList<String[]> getSymBolTable()
	{
		return symbolTable;
	}
	public void printSymBolTable() {
		System.out.println("token        value");
		for(int i= 0;i<symbolTable.size(); i++)
		{
			System.out.println(symbolTable.get(i)[0] +":" + symbolTable.get(i)[1] );
		}
	}
	
}
