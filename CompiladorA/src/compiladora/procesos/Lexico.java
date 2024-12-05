/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiladora.procesos;

import compiladora.simbolos.Lexema;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ventanas.Principal;

/**
 *
 * @author corin
 */
public class Lexico {
    private static final List <String> palabrasReservadas = List.of("const", "var", "procedure", "call", "begin", "end", "if", "then", "while", "do");
    
    public static void procesarLexemas(Principal v){
        //ArrayList<String> elementos = separaElementos(v);
        ArrayList<Lexema> lexemas = generaListaLexemas(v);
        v.getTxtIntermedio().setText("");
        
        for (Lexema lexema:lexemas){
            v.getTxtIntermedio().append(lexema+"\n");
        }
    }
    private static ArrayList <String> separaElementos (Principal v){
        String cadena =v.getTxtContenido().getText().toLowerCase();
        ArrayList<String> elementos = new ArrayList<>();
        
        String regex = "[a-zA-Z][a-zA-Z0-9_]*"      // Identificadores
                     + "|[1-9][0-9]*"               // Números (empezando de 1 a 9)
                     + "|:=|!|\\+|\\-|\\*|\\/"      // Operadores aritméticos
                     + "|=|#|!=|>=|<=|>|<"          //Operadores logicos
                     + "|\\.|,|;|\\(|\\)"
                     + "|\\n|\\t";  
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cadena);
        
         while (matcher.find()) {
            elementos.add(matcher.group());
         }
        return elementos;
    }
    
    public static ArrayList<Lexema> generaListaLexemas (Principal v){
        ArrayList<String> elementos = separaElementos(v);
          ArrayList<Lexema> lexemas = new ArrayList();
          
          for (String elemento: elementos){
              int token = -1;
              if (palabrasReservadas.contains(elemento)){
                  token = palabrasReservadas.indexOf(elemento);
                
              }
              else if (elemento.matches("[a-zA-Z][a-zA-Z0-9_]*")){
                  token = 100;
                  
              }else if (elemento.matches("[1-9][0-9]*")){
                  token = 200;
                  
              }else {
                      token = switch(elemento){
                          case ":=" ->
                              20;
                          case "!" ->
                              21;
                          case "+" ->
                              22;
                          case "-" ->
                              23;
                          case "*" ->
                              24;
                          case "/" ->
                              25;
                          case "=" ->
                              30;
                          case "#" ->
                              31;
                          case "<" ->
                              32;
                          case "<=" ->
                              33;
                          case ">" ->
                              34;
                          case ">=" ->
                              35;
                          case "." ->
                              40;
                          case "," ->
                              41;
                          case ";" ->
                              42;
                          case "(" ->
                              50;
                          case ")" ->
                              51;
                          case "\n" ->
                              60;
                          case "\t" ->
                              61;
                          default ->
                              -1;                   
                      };//Token desconocido EOF
              }
              lexemas.add(new Lexema(elemento, token));
          } 
          return lexemas;
    }
    
}
