package lexical_analyzer;

import static lexical_analyzer.lexical_analyzer.fileString;
import static lexical_analyzer.lexical_analyzer.fileIndex;
import static lexical_analyzer.lexical_analyzer.line;
import static lexical_analyzer.lexical_analyzer.lineIndex;

public class lexeme {

	// syntactic category determined by lexeme(etc. Intger, Indentifier, operater .....) 
	public String token;  
	
	// The variable that add next input after the last part of lexeme;
	public String lexeme; 
	
	public String tokenValue;
	
	//charClass variable is variable that determines the type of the next input  
	public int charClass; // if next input is digit, value is  0
						  // if next input is letter, value is 1,
						  // if next input is +,-,*and /,value is 2,
						  // if next input is  =,<,> and !, value is  3,
					      // if next input is ; , value is 4
	  					  // if next input is { or } , value is 5
						  // if next input is ,(comma) , value is 6
						  // if next input is whitespace, value is 7
						  // if next input is ( or ), value is 8B in the pile
						  // if next input is " , value is 9
						  // if next input is remainder(error input), value is -1
	
	// Strore next input(one char)
	public char nextChar; 
	
	//  macros used to charClass  and used to determine the token
	public final int DIGIT = 0; 	
	public final int LETTER = 1; 	
	public final int ARITHMETIC_OPERATION = 2;
	public final int C_A_OPERATION = 3; // compare operation and assignment operation
	public final int SEMICOLON = 4;
	public final int BRAKET = 5;
	public final int COMMA = 6;
	public final int WHITESPACE = 7;
	public final int PAREN = 8;
	public final int QUOTES = 9;
	public final int REMAINDER = -1;
	
	//lexeme Constructor
	public lexeme() {
		this.token = "";
		this.lexeme = "";
		this.tokenValue = "";
		this.charClass = -1;
	}
	
	// determine to token and token value(lexeme). if there is a lexeme that does not fit, return err;
	public String lex() {
		// store lexeme of line-position 
		int errDetectIndex = lineIndex;
		
		// first input take it
		getChar();
		
		// check lexeme according to first input
		// if you want to understand code below, check the documentation
		// so I will not explain the code
		switch (this.charClass) {
		case LETTER:
			addChar();
			getChar();
			while (this.charClass == LETTER || this.charClass == DIGIT) {
				addChar();
				getChar();
			}
			this.tokenValue = lexeme;
			return lookup(lexeme);
		case DIGIT:
			addChar();
			getChar();
			while (this.charClass == DIGIT) {
				addChar();
				getChar();
			}
			if(lexeme.charAt(0) == '0') {
				System.out.println("err:" + line + "줄" + errDetectIndex + "번째 integer가  잘못 되습니다.");
				break;
			}
			this.tokenValue = lexeme;
			this.token = "INTEGER";
			return token;
		case ARITHMETIC_OPERATION:
			addChar();
			int frontCharIndex = 0;
			char frontChar;
			if (fileIndex >= 2) {
				frontCharIndex = fileIndex - 2;
				while (fileString[frontCharIndex] == ' ') {
					frontCharIndex--;
					if(frontCharIndex < 0){
						frontChar = 0;
						frontCharIndex= 0;
						break;
					}
				}
				frontChar = fileString[frontCharIndex];
			}else {
				frontChar = 0;
			}
			if (this.nextChar == '-') {
				if (((frontChar >= 48 && frontChar <= 57)&& (fileString[fileIndex] >= 48 && fileString[fileIndex] <= 57))|| (frontChar >= 48 && frontChar <= 57&& fileString[fileIndex ] == '-')) {
					return lookup(lexeme);
				}
				getChar();
				if (this.charClass == DIGIT ) {
					while (this.charClass == DIGIT) {
						addChar();
						getChar();
					}
					if(lexeme.charAt(1) == '0') {
						System.out.println("err:" + line + "줄" + errDetectIndex + "번째 integer가  잘못 되습니다.");
						break;
					}
					this.tokenValue = lexeme;
					this.token = "INTEGER";
					return token;
				}
			}
			return lookup(lexeme);
		case C_A_OPERATION:
			addChar();
			getChar();
			while(this.charClass == C_A_OPERATION) {
				addChar();
				getChar();
			}
			lookup(lexeme);
			if(token.equals("COMPARISON_OPERATION"))
			{
				this.tokenValue = lexeme;
				return token;
			}else if(token.equals("ASSIGNMENT_OPERATION")){
				return token;
			}else {
				System.out.println("err:" + line + "줄" + errDetectIndex + "번째 comparsion operation이 잘못 되습니다.");
				break;
			}
		case SEMICOLON:
			addChar();
			return lookup(lexeme);
		case BRAKET:
			addChar();
			return lookup(lexeme);
		case COMMA:
			addChar();
			return lookup(lexeme);
		case WHITESPACE:
			addChar();
			getChar();
			while (this.charClass == WHITESPACE) {
				addChar();
				getChar();
			}
			token = "WHITESPACE";
			return token;
		case PAREN:
			addChar();
			return lookup(lexeme);
		case QUOTES:
			errDetectIndex = lineIndex;
			fileIndex++;
			getChar();
			while (fileString.length >= fileIndex) {
				addChar();
				getChar();
				if (this.charClass == QUOTES) {
					fileIndex++;
					this.tokenValue = lexeme;
					token = "LITERAL";
					return token;
				}
			}
			System.out.println("err:" + line + "줄" + errDetectIndex + "번째부터  문장이 완서되지 않았습니다.");
			break;
		case REMAINDER:
			addChar();
			System.out.println("err:" + line + "줄" + errDetectIndex + "번째에  " + this.lexeme + " 은 맞지않는 lexical입니다. ");
			break;
		}
		return "err";
	}
	// function that determines token according to lexeme
	public String lookup(String lexeme) {
		// check that lexeme is keword
		switch (lexeme) {
		case "if":
			token = "IF";
			break;
		case "while":
			token = "WHILE";
			break;
		case "return":
			token = "RETURN";
			break;
		case "else":
			token = "ELSE";
			break;
		}
		
		// check that lexeme is arithmetic operation
		switch (lexeme) {
		case "+":
			token = "ADD_OPERATION";
			break;
		case "-":
			token = "SUB_OPERATION";
			break;
		case "*":
			token = "MULTI_OPERATION";
			break;
		case "/":
			token = "DIV_OPERATION";
			break;
		}
		
		// check that lexeme is variabl type 
		switch (lexeme) {
		case "int":
			token = "VARTYPE";
			break;
		case "char":
			token = "VARTYPE";
			break;
		}
		
		// check that lexeme is comparison operation, assignment operation
		switch (lexeme) {
		case "<":
			token = "COMPARISON_OPERATION";
			break;
		case ">":
			token = "COMPARISON_OPERATION";
			break;
		case "==":
			token = "COMPARISON_OPERATION";
			break;
		case "!=":
			token = "COMPARISON_OPERATION";
			break;
		case "<=":
			token = "COMPARISON_OPERATION";
			break;
		case ">=":
			token = "COMPARISON_OPERATION";
			break;
		case "=":
			token = "ASSIGNMENT_OPERATION";
			break;
		}
		
		// check that lexeme is braket, paren, comma
		switch (lexeme) {
		case "{":
			token = "LBRACKET";
			break;
		case "}":
			token = "RBRACKET";
			break;
		case "(":
			token = "LPAREN";
			break;
		case ")":
			token = "RPAREN";
			break;
		case ",":
			token = "COMMA";
			break;
		case ";":
			token = "SEMICOLON";
			break;
		}
		// defualt is indentifier
		if(token == "")
		{
			token = "IDENTIFIER";
		}
		
		// return token
		return token;
	}
	
	// the function to determine what kind of next input is
	public void getChar() {
		
		// if file is end,  doesn't perform function getChar
		if (fileIndex >= fileString.length) {
			this.charClass = -2; // unmeaningful value Store.
			return;
		}
		// store one character in fileStirng in nextChar 
		this.nextChar = fileString[fileIndex];
		
		// determine to type of nextChar
		if (this.nextChar >= 48 && this.nextChar <= 57) {
			this.charClass = DIGIT;
		} else if ((this.nextChar >= 65 && this.nextChar <= 90) || (this.nextChar >= 97 && this.nextChar <= 122)) {
			this.charClass = LETTER;
		} else if (this.nextChar == '+' || this.nextChar == '-' || this.nextChar == '*' || this.nextChar == '/') { 
			this.charClass = ARITHMETIC_OPERATION;
		} else if (this.nextChar == '=' || this.nextChar == '<' || this.nextChar == '>' || this.nextChar == '!') {
			this.charClass = C_A_OPERATION;
		} else if (this.nextChar == ';') { 
			this.charClass = SEMICOLON;
		} else if (this.nextChar == '{' || this.nextChar == '}') { 
			this.charClass = BRAKET;
		} else if (this.nextChar == ',') {
			this.charClass = COMMA;
		} else if (this.nextChar == 9 || this.nextChar == 10 || this.nextChar == 13 || this.nextChar == 32) {
			this.charClass = WHITESPACE;
		} else if (this.nextChar == '(' || this.nextChar == ')') {
			this.charClass = PAREN;
		} else if (this.nextChar == '"') {
			this.charClass = QUOTES;
		} else {
			this.charClass = REMAINDER;
		}
	}
	
	// the function to add next input at the last part of lexeme
	public void addChar() {
		//add next input at the last part of lexeme
		this.lexeme += this.nextChar;
		
		//  whitespace type is different, so file position index need to add a differnet value(tap is 4,space is 1) 
		if (this.nextChar == 13) {
			line++;
			lineIndex = 0;
		} else if (this.nextChar == 9)
			lineIndex += 4;
		else if (this.nextChar == 32)
			lineIndex++;
		else if (this.nextChar == 10)
			lineIndex = 0;
		else {
			//if whitespace is  
			lineIndex++;
		}
		// increase the index of fileString for next input.
		fileIndex++;
	}
}
