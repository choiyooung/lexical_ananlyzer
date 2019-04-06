package lexical_analyzer;

import static lexical_analyzer.lexical_analyzer.fileString;
import static lexical_analyzer.lexical_analyzer.fileIndex;
import static lexical_analyzer.lexical_analyzer.line;
import static lexical_analyzer.lexical_analyzer.lineIndex;

public class lexeme {
	public String token;
	public String lexeme;
	public int charClass; // 다음 들어오는 글자가 숫자면 0, 글자면 1, +,-,*,/이면 2, =,<,>,!이면 3, ;이면 4, { or } 이면 5, comma
							// 이면 6 , whiteSpace이면 7, ( or ) 이면 8, " 이면 9 ,나머지는 -1
	public char nextChar;
	public final int DIGIT = 0;
	public final int LETTER = 1;
	public final int ARITHMETIC_OPERATION = 2;
	public final int C_A_OPERATION = 3; // compare_assignment_operation의 줄임말
	public final int SEMICOLON = 4;
	public final int BRAKET = 5;
	public final int COMMA = 6;
	public final int WHITESPACE = 7;
	public final int PAREN = 8;
	public final int QUOTES = 9;
	public final int REMAINDER = -1;

	public lexeme() {
		this.token = "";
		this.lexeme = "";
		this.charClass = -1;
	}
	public String lex() {
		int errDetectIndex = lineIndex;
		getChar();
		switch (this.charClass) {
		case LETTER:
			addChar();
			getChar();
			while (this.charClass == LETTER || this.charClass == DIGIT) {
				addChar();
				getChar();
			}
			return lookup(lexeme);
		case DIGIT:
			addChar();
			getChar();
			while (this.charClass == DIGIT) {
				addChar();
				getChar();
			}

			this.token = "INTEGER";
			System.out.println(this.lexeme + ":" + this.token);
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
				if (((frontChar >= 48 && frontChar <= 57)
						&& (fileString[fileIndex] >= 48 && fileString[fileIndex] <= 57))
						|| (frontChar >= 48 && frontChar <= 57
								&& fileString[fileIndex ] == '-'))
					return lookup(lexeme);
				getChar();
				if (this.charClass == DIGIT) {
					while (this.charClass == DIGIT) {
						addChar();
						getChar();
					}
					this.token = "INTEGER";
					System.out.println(this.lexeme + ":" + this.token);
					return token;
				}
			}
			return lookup(lexeme);
		case C_A_OPERATION:
			addChar();
			getChar();
			while (this.charClass == C_A_OPERATION) {
				addChar();
				getChar();
			}
			return lookup(lexeme);
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
					token = "LITERAL";
					System.out.println(this.lexeme + ":" + this.token);
					return token;
				}
			}
			System.out.println("err:" + line + "줄" + errDetectIndex + "번째부터  문장이 완서되지 않았습니다.");
			break;
		case REMAINDER:
			addChar();
//			getChar();
//			while (this.charClass != WHITESPACE) {
//				addChar();
//				getChar();
//			}
			System.out.println("err:" + line + "줄" + errDetectIndex + "번째에  " + this.lexeme + " 은 맞지않는 lexical입니다. ");
			break;
		}
		return "err";
	}
	public String lookup(String lexeme) {
		switch (lexeme) {
		case "int":
			token = "INT";
			break;
		case "char":
			token = "CHAR";
			break;
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
		default:
			token = "IDENTIFIER";
		}
		System.out.println(this.lexeme + ":" + this.token);
		return token;
	}
	public void getChar() {
		// 다으메 들어온 단어가 Digit이면
		if (fileIndex >= fileString.length) {
			this.charClass = -1;
			return;
		}
		this.nextChar = fileString[fileIndex];
		if (this.nextChar >= 48 && this.nextChar <= 57) { // digit이면
			this.charClass = 0;
		} else if ((this.nextChar >= 65 && this.nextChar <= 90) || (this.nextChar >= 97 && this.nextChar <= 122)) { // 다음에
																													// 들어온
																													// 글자가
																													// letter이면
			this.charClass = 1;
		} else if (this.nextChar == '+' || this.nextChar == '-' || this.nextChar == '*' || this.nextChar == '/') { // +,-,*,/이면
			this.charClass = 2;
		} else if (this.nextChar == '=' || this.nextChar == '<' || this.nextChar == '>' || this.nextChar == '!') { // =,<,>,!
																													// 이면
			this.charClass = 3;
		} else if (this.nextChar == ';') { // ; 이면
			this.charClass = 4;
		} else if (this.nextChar == '{' || this.nextChar == '}') { // {,} 이면
			this.charClass = 5;
		} else if (this.nextChar == ',') { // , 이면
			this.charClass = 6;
		} else if (this.nextChar == 9 || this.nextChar == 10 || this.nextChar == 13 || this.nextChar == 32) {
			this.charClass = 7;
		} else if (this.nextChar == '(' || this.nextChar == ')') { // (,) 이면
			this.charClass = 8;
		} else if (this.nextChar == '"') { // " 이면
			this.charClass = 9;
		} else {
			this.charClass = -1;
		}
	}
	public void addChar() {
		this.lexeme += this.nextChar;
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
			lineIndex++;
		}
		fileIndex++;
	}
}
