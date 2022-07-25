/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiler.main;

import compiler.exceptions.IsLexicalExceptions;
import compiler.lexico.IsScanner;
import compiler.lexico.Token;
import java.io.IOException;

/**
 *
 * @author Dell
 */
public class MainClass {
      
    public static void main(String[] args) throws IOException {
        
       
       
          Token token = null;
          
        try{
        IsScanner sc = new IsScanner("Jabc123 \n ok \n 123 \n > \n aaa \n 12 ");
     
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
