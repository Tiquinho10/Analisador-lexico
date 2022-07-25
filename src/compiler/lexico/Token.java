/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.lexico;

/**
 *
 * @author Dell
 */
public class Token {
    public static final int TK_IDENTIFIER = 0;
    public static final int TK_NUMBER = 1;
    public static final int TK_OPERATOR = 2;
    public static final int TK_PONCTUATION = 3;
    public static final int TK_ASSIGN = 4;
    public static final int TK_KEYWORD = 5;
    public static final int TK_SUM = 6;
    public static final int TK_SUBTRACTION = 7;
    public static final int TK_MULTIPLICATION= 8; 
    public static final int TK_DIVISION = 9; 
    public static final int TK_SPECIAL = 10;
      public static final int TK_At = 11;
    
    
    public static final String TK_TEXT[] = {
           "IDENTIFIER", "NUMBER", "OPERATOR", "PONCTUATION", "ASSIGMENT", "KEYWORD", "sum_op", "sub_op", "multi_op", "div_op", "SPECIAL","AROBA"
    };
    
    
    private int type;
    private String text;
    private int line;
    private int column;

    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public Token() {
        
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if(type == 5 || type == 10){
            //return "Token{" + "type=" + text + ", text=" + text  +'}';
             return "Tipo Token= " + text + "|| Token=" + text;
        }
        
       // return "Token{" + "type=" + TK_TEXT[type] + ", text=" + text  +'}';
         return "Tipo Token= " + TK_TEXT[type] + " || Token= " + text;
    }
   

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    
    
    
}
