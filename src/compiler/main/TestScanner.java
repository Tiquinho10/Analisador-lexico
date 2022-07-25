/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.main;

import compiler.exceptions.IsLexicalExceptions;
import compiler.lexico.IsScanner;
import compiler.lexico.Token;

/**
 *
 * @author Dell
 */
public class TestScanner {
    
    public static void main(String[] args){
      
          Token token = null;
          int a=3;
           int b=2;
           
           System.out.println(a+b);
          
        try{
        IsScanner sc = new IsScanner("  ifa \n if \n program12 \n + \n else \n *"  +  "\t");
     
        do{
             token = sc.proximoToken();
           if(token != null){
               System.out.println(token);
          
           }
        }while (token != null);
    
            
    }catch(IsLexicalExceptions e){
            System.out.println("Lexical error " + e.getMessage()  );
    }catch(Exception e){
            System.out.println("general error" + e.getMessage());
    }
 }
    }

