package lexical_analyzer;

import static lexical_analyzer.lexical_analyzer.fileString;
import static lexical_analyzer.lexical_analyzer.fileIndex;

public class lexeme {
	public String token;
	public String lexeme;
	public int charClass; // ���� ������ ���ڰ� ���ڸ� 0, ���ڸ� 1, +,-,*,/�̸� 2, =,<,>,!�̸� 3, ;�̸� 4, { or } �̸� 5, comma �̸�  6 , whiteSpace�̸� 7, ( or ) �̸� 8, " �̸� 9 ,�������� -1
	public char nextChar;
	public final int DIGIT = 0;
	public final int LETTER = 1;
	public final int ARITHMETIC_OPERATION = 2;
	public final int C_A_OPERATION = 3; // compare_assignment_operation�� ���Ӹ�
	public final int SEMICOLON = 4;
	public final int BRAKET = 5;
	public final int COMMA = 6;
	public final int WHITESPACE = 7;
	public final int PAREN = 8;
	public final int REMAINDER = -1;
	
	public lexeme() {
		this.token = "";
		this.lexeme = "";
		this.charClass = -1;
	}
	public String lex()
	{
		getChar();
		switch(this.charClass) {
		case LETTER:
			addChar();
			getChar();
			while(this.charClass == LETTER || this.charClass == DIGIT)
			{
				addChar();
				getChar();
			}
			return lookup(lexeme);
		case DIGIT:
			addChar();
			getChar();
			while(this.charClass == DIGIT) {
				addChar();
				getChar();
			}
			this.token = "INTEGER";
			System.out.println(this.lexeme + ":" +this.token);
			return "NULL";
		case ARITHMETIC_OPERATION:
			addChar();
			return lookup(lexeme);
			
		}
		
		return null;
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
		case ",":
			token = "COMMA";
			break;

		}
		
		System.out.println(this.lexeme + ":" +this.token);
		return "";
	}
	public void getChar() {
		// ������ ���� �ܾ Digit�̸�
		if(fileIndex >= fileString.length) {this.charClass = -1; return;}
		this.nextChar = fileString[fileIndex];
		fileIndex++;
		if (this.nextChar >= 48 && this.nextChar <= 57) {
			this.charClass = 0;
		} else if ((this.nextChar >= 65 && this.nextChar <= 90) || (this.nextChar >= 97 && this.nextChar <= 122)) { // ������ ���� ���ڰ� letter�̸�
			this.charClass = 1;
		} else if (this.nextChar == '+' || this.nextChar == '-' || this.nextChar == '*' || this.nextChar == '/') { // +,-,*,/�̸�
			this.charClass = 2;
		} else if (this.nextChar == '=' || this.nextChar == '<' || this.nextChar == '>' || this.nextChar == '!') { // =,<,>,! �̸�
			this.charClass = 3;
		} else if (this.nextChar == ';') { // ; �̸�
			this.charClass = 4;
		} else if (this.nextChar == '{' || this.nextChar == '}') { // {,} �̸�
			this.charClass = 5;
		} else if (this.nextChar == ',') { // , �̸�
			this.charClass = 6;
		} else if (this.nextChar == 9 || this.nextChar == 10 || this.nextChar == 13 || this.nextChar == 32) {
			this.charClass = 7;
		} else if (this.nextChar == '(' || this.nextChar == ')') { // (,) �̸�
			this.charClass = 8;
		} else if(this.nextChar == '"'){
			this.charClass = 9;
		} else {
			this.charClass = -1;
		}
	}
	public void addChar() {
		this.lexeme += this.nextChar;
	}
}
