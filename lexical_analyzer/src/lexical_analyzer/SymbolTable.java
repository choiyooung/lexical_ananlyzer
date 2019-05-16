package lexical_analyzer;

public class SymbolTable {
	private String [] symbol;
	
	// next input position variable
	private int lineNum;
	private int lineIndex;
	
	
	// Default constructor
	SymbolTable() {
		
	}
	
	// Constructor
	SymbolTable(String[] symbol, int lineNum, int lineIndex) {
		this.symbol = symbol;
		this.lineNum = lineNum;
		this.lineIndex = lineIndex;
	}
	
	/////////////
	// setter //
	////////////
	
	void setSymbol(String[] symbol) {
		this.symbol = symbol;
	}
	
	void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}
	
	void setLineIndex(int lineIndex) {
		this.lineIndex = lineIndex;
	}
	
	/////////////
	// getter //
	////////////
	
	String[] getSymbol() {
		return symbol;
	}
	
	int getLineNum() {
		return lineNum;
	}
	
	int getLineIndex() {
		return lineIndex;
	}
}
