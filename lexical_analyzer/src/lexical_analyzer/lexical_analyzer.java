package lexical_analyzer;

public class lexical_analyzer {
	public String token;
	public static char[] fileString;
	public static int fileIndex;
	
	public lexical_analyzer(char[] fileString) {
		this.fileString = fileString;
		this.fileIndex = 0;
	}
	public void analyze() {
		while(fileIndex < fileString.length) {
			lexeme lexeme = new lexeme();
			lexeme.lex();
		}
	}
}
