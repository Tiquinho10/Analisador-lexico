/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.lexico;

import compiler.exceptions.IsLexicalExceptions;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class IsScanner {

    private char[] content;
    private int estado;
    private int pos;
    private int line;
    private int column;

    public IsScanner(String filename) {
        try {
            line = 1;
            column = 0;
            
            String txtConteudo;

            txtConteudo = filename;
          

            content = txtConteudo.toCharArray();

            System.out.println(" conteudo " + content.toString());
            pos = 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    
    public Token proximoToken() {
        Token token = null;
        Token tk_error = null;
        char currentChar;
        String term = "";
        if (isEOF()) {
            System.out.println("Fim");
            return null;
        }

        estado = 0;
//        while(true){
        while (!isEOF()) {
            currentChar = nextChar();

            switch (estado) {
                case 0:
                    if (isChar(currentChar)) {
                        term += currentChar;
                        estado = 1;
                        
                    } else if (isAt(currentChar)) {
                        term += currentChar;
                        estado = 16;

                    }else if (isDigit(currentChar)) {
                        term += currentChar;
                        estado = 4;

                    } else if (isAssign(currentChar + "")) {
                       
                        term += currentChar;
                        estado = 6;

                    } else if (isOperator(currentChar)) {
                        term += currentChar;
                        estado = 8;
                    } else if (isPonctuation(currentChar)) {
                        term += currentChar;
                        estado = 9;
                    } else if (isSpecial(currentChar)) {
                        term += currentChar;
                        estado = 10;
                    } else if (isSpace(currentChar)) {
                        estado = 0;
                    } else if (isSumOp(currentChar)) {
                        term += currentChar;
                        estado = 11;
                    } else if (isSubOp(currentChar)) {
                        term += currentChar;
                        estado = 12;
                    } else if (isTimOp(currentChar)) {
                        term += currentChar;
                        estado = 13;
                    } else if (isDivOp(currentChar)) {
                        term += currentChar;
                        estado = 14;
                    } else if (currentChar == '>') {
                        term += currentChar;
                        estado = 15;
                    } else {
                        term += currentChar;
                        tk_error = new Token();
                        tk_error.setText(term);
                        tk_error.setLine(line);
                        tk_error.setColumn(column - term.length());

                        throw new IsLexicalExceptions("Unrecognized SYMBOL " + tk_error.getText() + " At LINE:" + tk_error.getLine() + " and Column:" + tk_error.getColumn());
                    }
                    break;
                case 1:
                    if (isKeyWord(term)) {
                      
                         int l = line;
                         String y = term;
                      
                          term += " ";
                          term += currentChar;
                          
                       String a = term.replaceAll(" ", ""); 
                        
                       if(isSpace(currentChar) || isPonctuation(currentChar) || isSpecial(currentChar)  ){  
                            line = l;       
                           term = y;
                            estado = 2;
                           
                            back();
                             
                       }
                    if (!isKeyWord(a)) {                  
                          String x = term.replaceAll(" ", "");
                          term = x;     
                    }
                      
                    }else {
                        
                        if (isChar(currentChar) || isDigit(currentChar)) {
                            
                            term += currentChar;
                            estado = 1;                           
                        } else if (isSpace(currentChar) || isOperator(currentChar) || isDivOp(currentChar) || isTimOp(currentChar) || isSubOp(currentChar) || isSumOp(currentChar) || isPonctuation(currentChar) || isAssign(currentChar + "") || isAt(currentChar) || isSpecial(currentChar)) {
                            estado = 3;
                           
                            back();


                        } else {
                            term += currentChar;
                            tk_error = new Token();
                            tk_error.setText(term);
                            tk_error.setLine(line);
                            tk_error.setColumn(column - term.length());

                            throw new IsLexicalExceptions("Malformed Identifier " + tk_error.getText() + " At LINE:" + tk_error.getLine() + " and Column:" + tk_error.getColumn());
                        }
                    }
                    break;
                case 2:
                  
                    
                    back();
                    token = new Token();
                    token.setType(Token.TK_KEYWORD);
                    token.setText(term);
                    token.setLine(line);
                    token.setColumn(column - term.length());
                    
                    return token;
                    
                case 3:
                    back();
                    token = new Token();
                    token.setType(Token.TK_IDENTIFIER);
                    token.setText(term);
                    token.setLine(line);
                    token.setColumn(column - term.length());
                   
                    return token;
                case 4:
                    if (isDigit(currentChar)) {
                        estado = 4;
                        term += currentChar;
                    } else if (!isChar(currentChar) || isSpace(currentChar)) {
                        estado = 5;
                        back();
                    }else{  
                        term += currentChar;
                        tk_error = new Token();
                        tk_error.setText(term);
                        tk_error.setLine(line);
                        tk_error.setColumn(column - term.length());

                        throw new IsLexicalExceptions("Unrecognized Number : " + tk_error.getText() + " At LINE:" + tk_error.getLine() + " and Column:" + tk_error.getColumn());

                    }
                    break;
                case 5:
                    token = new Token();
                    token.setType(Token.TK_NUMBER);
                    token.setText(term);
                    token.setLine(line);
                    token.setColumn(column - term.length());

                    back();
                    return token;
                case 6:
                    
                    if (currentChar == '=') {
                        estado = 6;
                        term += currentChar;
                        

                    } else if (isSpace(currentChar) || isChar(currentChar) || isAssign(term)) {
                        estado = 7;
                        back();
                    }

                    break;
                case 7:
                  
                    token = new Token();
                    token.setText(term);
                    token.setType(Token.TK_ASSIGN);
                    token.setLine(line);
                    token.setColumn(column - term.length());
                    back();
                   
                    return token;
                case 8:

                    token = new Token();
                    token.setType(Token.TK_OPERATOR);
                    token.setText(term);
                    token.setLine(line);
                    token.setColumn(column - term.length());
                    back();
                    return token;

                case 9:
                    token = new Token();
                    token.setType(Token.TK_PONCTUATION);
                    token.setText(term);
                    token.setLine(line);
                    token.setColumn(column - term.length());
                    back();
                    return token;
                case 10:
                    token = new Token();
                    token.setType(Token.TK_SPECIAL);
                    token.setText(term);
                    token.setLine(line);
                    token.setColumn(column - term.length());
                    back();
                    return token;

                case 11:
                    token = new Token();
                    token.setType(Token.TK_SUM);
                    token.setText(term);
                    token.setLine(line);
                    token.setColumn(column - term.length());
                    back();
                    return token;
                case 12:
                    token = new Token();
                    token.setType(Token.TK_SUBTRACTION);
                    token.setText(term);
                    token.setLine(line);
                    token.setColumn(column - term.length());
                    back();
                    return token;

                case 13:
                    token = new Token();
                    token.setType(Token.TK_MULTIPLICATION);
                    token.setText(term);
                    token.setLine(line);
                    token.setColumn(column - term.length());
                    back();
                    return token;

                case 14:
                    token = new Token();
                    token.setType(Token.TK_DIVISION);
                    token.setText(term);
                    token.setLine(line);
                    token.setColumn(column - term.length());
                    back();
                    return token;
                case 15:
                    if (currentChar == '=') {
                        System.out.println("Maior ou igual");
                    } else {
                        System.out.println("outro");
                    }
                    break;
                case 16:
                    token = new Token();
                    token.setType(Token.TK_At);
                    token.setText(term);
                    token.setLine(line);
                    token.setColumn(column - term.length());
                    back();
                    return token;

//                        
            }
        }
        return null;
    }

    
    private boolean isKeyWord(String word) {
        return word.equals("program") || word.equals("true") || word.equals("false") || word.equals("char") || word.equals("integer") || word.equals("boolean") || word.equals("write") || word.equals("read") || word.equals("if") || word.equals("else") || word.equals("begin") || word.equals("end") || word.equals("function") || word.equals("procedure") || word.equals("for") || word.equals("var") || word.equals("then") || word.equals("while") || word.equals("not") || word.equals("and") || word.equals("or");
    }

    private boolean isSumOp(char c) {
        return c == '+';
    }

    private boolean isSubOp(char c) {
        return c == '-';
    }

    private boolean isTimOp(char c) {
        return c == '*';
    }

    private boolean isDivOp(char c) {
        return c == '/';
    }

    private boolean isAssign(String c) {
        return c.equals(":=") || c.equals(":");
    }

    private boolean isPonctuation(char c) {
        return c == ';' || c == '.' || c == ',';
    }

    private boolean isSpecial(char c) {
        return c == '{' || c == '}' || c == '(' || c == ')' || c == '[' || c == ']';
    }

    private boolean isAt(char c) {
        return c == '@';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isOperator(char c) {
        return c == '>' || c == '<' || c == '!';
    }

    private boolean isSpace(char c) {
        if (c == '\n' || c == '\r') {
            line++;
            column = 0;
        }

        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private char nextChar() {

        if (isEOF()) {
            System.out.println("fim");
            return '\0';
        }
        column++;
        return content[pos++];

    }

    private boolean isEOF() {

        return pos == content.length;

    }

    private void back() {
        pos--;
        column--;
    }

    @Override
    public String toString() {
        return "IsScanner{" + "content=" + content + ", estado=" + estado + ", pos=" + pos + '}';
    }

}
