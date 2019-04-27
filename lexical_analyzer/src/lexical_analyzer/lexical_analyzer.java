package lexical_analyzer;

import java.util.ArrayList;

public class lexical_analyzer {
	
	// token을 저장한다.
	public static char[] fileString;
	public static int fileIndex;
	// next input position variable
	public static int line;
	public static int lineIndex;
	// store token and token value in symbolTable 
	public ArrayList<String[]> symbolTable;
	
	//lexical_analyer Construct
	public lexical_analyzer(char[] fileString) {
		this.fileString = fileString;
		this.fileIndex = 0;
		this.line = 1;
		this.lineIndex = 0;
		symbolTable = new ArrayList<String[]>();
		
	}
	// divide fileString by toekn and token value 
	public void analyze() {
		while(fileIndex < fileString.length) {
			lexeme lexeme = new lexeme();
			
			// variabl to store token for a moment
			String tempToeken = lexeme.lex();
			
			// variable to store tokenvalue
			String tempValue = lexeme.tokenValue;
			
			// variable to check err
			String err = "err";
			
			// if token is not "WHITSPACE", store token and token value in symbolTable 
			if(!tempToeken.equals("WHITESPACE") && !tempToeken.equals(err))
			{
				symbolTable.add(new String[] {tempToeken,tempValue});
			}else if(tempToeken.equals(err)) {
				// if token is "err", symboltable is clear
				symbolTable.clear();
				break;
			}
		}
	}
	// return symboltable
	public ArrayList<String[]> getSymBolTable()
	{
		return symbolTable;
	}
	
	// print symboltable to console
	public void printSymBolTable() {
		System.out.println("		token            value");
		for(int i= 0;i<symbolTable.size(); i++)
		{
			System.out.format("%20s", symbolTable.get(i)[0]);
			System.out.print("  :  ");
			System.out.format("%12s\n", symbolTable.get(i)[1]);
		}
	}
	
}
