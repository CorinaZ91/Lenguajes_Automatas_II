/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiladora.procesos;

import compiladora.procesos.Errores;
import compiladora.procesos.Lexico;
import compiladora.simbolos.Cuadruplo;
import compiladora.simbolos.Lexema;
import java.util.ArrayList;
import java.util.Stack;
import ventanas.Principal;

/**
 *
 * @author corin
 */
public class Sintactico {

    private static ArrayList<Lexema> lexemas = null;
    private static int indice = 0;
    private static int token;
    private static Principal v;
    private static Stack<String> oprs;
    private static Stack<String> opnds;
    private static Stack<Integer> incom;
    private static Stack<Integer> ciclos;
    private static ArrayList<Cuadruplo> cuadruplos;
    private static int contador;
    private static String actual;

    /**
     * private static int nextToken() {
     *
     * if (indice >= lexemas.size()) { return -1; }
     * v.getTxtIntermedio().append(lexemas.get(indice).getDato() + "");
     *
     * int t = lexemas.get(indice).getToken(); while(t>=60 && t<70 && indice
     * <lexemas.size()){ indice ++;
     * v.getTxtIntermedio().append(lexemas.get(indice).getDato() + ""); //indice
     * ++; //PRUEBA t = lexemas.get(indice).getToken();
     * //System.out.println(indice+""+t+""+lexemas.get(indice).getDato()+"");
     *
     * }
     * // System.out.println(indice+""+t+""+lexemas.get(indice).getDato()+"");
     * System.out.println(t);
     *
     * indice++; return t; }
     *
     */
    private static int nextToken() {
        if ((indice < lexemas.size())) {
            int token = lexemas.get(indice).getToken();
            actual = lexemas.get(indice).getDato();
            indice++;
            
            System.out.println("actal: " + actual);
            return token;

        }
        return -1;

    }

    public static void analizaSintaxis(Principal v) {
        Sintactico.v = v;
        lexemas = Lexico.generaListaLexemas(v);
        for (int i = 0; i < lexemas.size(); i++) {
            System.out.println(lexemas.get(i).getToken());
            if (lexemas.get(i).getToken() == 60 || lexemas.get(i).getToken() == 61) {
                lexemas.remove(i);
            }
        }
        System.out.println(lexemas.toString());
        /*
        for (int i = 0; i< lexemas.size(); i++){
            int token = nextToken();
         v.getTextIntermedio().append(lexemas.get((indice).getDato());
            System.out.println(token);
        }*/

        v.getTxtSalida().setText("");
        v.getTxtIntermedio().setText("");
        oprs = new Stack();
        opnds = new Stack();
        incom = new Stack();
        ciclos = new Stack();
        cuadruplos = new ArrayList();
        indice = 0;
        contador = 1;
        token = nextToken();
        //System.out.println(lexemas);
        program();
    }

    //block();
    //program = block ".";
    //program ::= block 40;
    private static void program() {
        block();
        if (token != 40) {
            Errores.getError(v, 1);
        } else {
            cuadruplos.add(new Cuadruplo("ret"));
            System.out.println(cuadruplos.toString());
            v.getTxtSalida().append("\nCompile Success");
        } //De aqui en adelante cuadno se consigue un token, hay que traer el que sigue, token = nextToken();
        //System.out.println(cuadruplos);
    }

    //blockC = "const" ident "=" number {"," ident "=" number} ";"
    //blockC = 0 100 30 200 blockCciclo 42
    //blockC = NULL
    private static void blockC() {
        //token = nextToken();
        if (token != 0) {
            //Errores.getError(v, 2);
            return;
        }
        token = nextToken();
        if (token != 100) {
            Errores.getError(v, 3);
            return;
        }
        token = nextToken();
        if (token != 30) {
            Errores.getError(v, 4);
            return;
        }
        token = nextToken();
        if (token != 200) {
            Errores.getError(v, 5);
            return;
        }
        token = nextToken();
        blockCciclo();
        if (token != 42) {
            Errores.getError(v, 6);
            return;
        }
        token = nextToken();
    }

    //blockCciclo = "," ident "=" number blockCciclo
    //blockCciclo = 41 100 30 200 blockCciclo
    //blockCciclo = NULL
    private static void blockCciclo() {
        if (token != 41) {
            //Errores.getError(v, 2);
            return;
        }
        token = nextToken();
        if (token != 100) {
            Errores.getError(v, 3);
            return;
        }
        token = nextToken();
        if (token != 30) {
            Errores.getError(v, 4);
            return;
        }
        token = nextToken();
        if (token != 200) {
            Errores.getError(v, 5);
            return;
        }
        token = nextToken();
        blockCciclo();
    }

    //blockV = "var" ident {"," ident} ";"
    //blockV = 1 100 blockVciclo 42
    //blockV = NULL
    private static void blockV() {
        if (token != 1) {
            return;
        }
        token = nextToken();
        if (token != 100) {
            Errores.getError(v, 5);
            return;
        }
        token = nextToken();
        blockVciclo();
        if (token != 42) {
            Errores.getError(v, 6);
            return;
        }
        token = nextToken();
    }

    //blockVciclo = 41 100 blockVciclo
    //blockVciclo = NULL
    private static void blockVciclo() {
        if (token != 41) {
            return;
        }
        token = nextToken();
        if (token != 100) {
            Errores.getError(v, 5);
            return;
        }
        token = nextToken();
        blockVciclo();
    }

    //blockP = "procedure" ident ";" block ";" blockP
    //blockP = 2 100 42 block 42 blockP
    //blockP = NULL
    private static void blockP() {
        if (token != 2) {
            return;
        }
        token = nextToken();
        if (token != 100) {
            Errores.getError(v, 5);
            return;
        }
        token = nextToken();
        if (token != 42) {
            Errores.getError(v, 6);
            return;
        }
        token = nextToken();
        block();
        if (token != 42) {
            Errores.getError(v, 6);
            return;
        }
        token = nextToken();
        blockP();
    }

    //block = blockC blockV blockP statement
    private static void block() {
        blockC();
        blockV();
        blockP();
        statement();
    }

    private static void statement() {

        switch (token) {
            case 100 -> { //ident ":=" expression >> 100 20 expression
                //identificador entra a la pila
                //System.out.println(lexemas.get(indice - 1).getDato());
                opnds.push(lexemas.get(indice - 1).getDato());
        System.out.println("salio");

                token = nextToken();
                if (token != 20) {
                    Errores.getError(v, 8);
                    return;
                }
                while (!oprs.empty() && mayorOigualP(oprs.peek(), lexemas.get(indice - 1).getDato())) {
                    generarCuad();
                }
                //El token de la pila es >= precedencia
                //Si, mientras si, sacar y generar cuadruplos
                //no, meter a la pila
                oprs.push(lexemas.get(indice - 1).getDato());
                //System.out.println(oprs.toString());
                token = nextToken();
                expression();
                //mientras no sea pila vacia generar cuadruplos
                while (!oprs.empty()) {
                    generarCuad();
                }
            }
            case 3 -> {  //"call" ident  >> 3 100
                token = nextToken();

                if (token != 100) {
                    Errores.getError(v, 3);
                    return;
                }
                //generar cuadruplo (call, , , identificador) ident que corresponda a la palabra del indice del token
                cuadruplos.add(new Cuadruplo("call", "", "", lexemas.get(indice - 1).getDato()));
                token = nextToken();
            }
            case 21 -> { //"!" expression  >> 21 expression
                //el tope de la pila es igual o mayor precedencia
                //si, mientras generamos cuadruplos
                while (!oprs.empty() && mayorOigualP(oprs.peek(), lexemas.get(indice - 1).getDato())) {
                    generarCuad();
                }
                //no, meter a la pila operadores (not)
                oprs.push(lexemas.get(indice - 1).getDato());
                token = nextToken();
                //if (token != 100) {
                //    Errores.getError(v, 3);
                //    return;
                //}
                //token = nextToken();
                //break;
                expression();
                //mientras no pila vacia genrar cuadruplos
                while (!oprs.empty()) {
                    generarCuad();
                }
            }
            case 4 -> { //"begin" statement {";" statement } "end" >> 4 statement stBegin 5
                token = nextToken();
                statement();
                stBegin();
                if (token != 5) { // "end"
                    Errores.getError(v, 9);
                    return;
                }
                token = nextToken();

            }
            case 6 -> {//"if" condition "then" statement  >> 6 condition 7 statement
                token = nextToken();
                condition();
                //mientras no pila vacia generar cuadruplos
                while (!oprs.empty()) {
                    generarCuad();
                }
                if (token != 7) {//7 
                    Errores.getError(v, 3);
                    return;
                }
                token = nextToken();
                statement();
                //cuadruplo siguiehte saca al incompletot
                int incompleto = incom.pop();
                cuadruplos.get(incompleto).setResult(cuadruplos.size() + "");
            }
            case 8 -> { //statement = "while" condition "do" statement ]; >> 8 condition 9 statement
                token = nextToken();
                //primer cuadruplo = true
                //int pcuadruplo = true;
                condition();
                //mientras no pila vacia generar cuadruplos
                while (!oprs.empty()) {
                    generarCuad();
                }
                if (token != 9) { //9 
                    Errores.getError(v, 11);//11
                    return;
                }
                token = nextToken();
                statement();
                //cuadruplos.add(new Cuadruplo(complem(), "", "", lexemas.get(indice - 1).getDato()));
                //generar cuadruplo (jump, , ,ciclo) //saca de ciclos
                //cuadruplo siguiente saca de 
            }
            case -1 -> {
                return;
            }
            default -> {
                Errores.getError(v, 7);
            }
        }
    }

    //stBegin = ";" statement stBegin
    //stBegin = 42 statement stBegin
    //stBegin = NULL
    private static void stBegin() {
        if (token != 42) {
            return;
        }
        token = nextToken();
        statement();
        stBegin();

    }

    // condition = expression oplogical expression ;
    private static void condition() {
        expression();
        opLogical();
        expression();

    }

    //oplogical = "="|"#"|"<"|"<="|">"|">="
    //oplogical = 30 | 31 | 32 | 33 | 34 | 35
    private static void opLogical() {
        switch (token) {
            case 30, 31, 32, 33, 34, 35 -> {
                //si tipe de pila >=presedencia
                //si, mientras si generar cuadriplos 
                //si primerCuadruplo(), meter a ciclos, 
                //primerCuadruplo = false
                //no, meter a la pila
                token = nextToken();
            }
            default ->
                Errores.getError(v, 12);
        }
    }

    //signo = "+"|"-"
    //signo = 22 |23
    //signo = NULL 
    private static void signo() {
        if (!(token == 22 || token == 23)) {
            return;
        }
        token = nextToken();
        expression();
    }

    // expression = signo term expCiclo;
    private static void expression() {
        signo();
        term();
        expCiclo();

    }

    //expCiclo = ("+"|"-") expression
    //expCiclo = (22 | 23) expression
    //expCiclo = NULL
    private static void expCiclo() {
        if (!(token == 22 || token == 23)) {
            return;
        }
        token = nextToken();
        expression();

        //si tipe de la pila >= prese
        //si, mientras si, generar cuadruplo
        //si, primerCuadruplo, meter a ciclos,  primer cuadruplo = false
        //no, meter a la pila
    }

    //termCiclo = ("*"|"/") term
    //termCiclo = (24 | 25) term
    //termCiclo = NULL
    private static void termCiclo() {
        if (!(token == 24 || token == 25)) {
            return;
        }
        token = nextToken();

//si tipe de la pila >= prese
//si, mientras si, generar cuadruplo
        //si, primerCuadruplo, meter a ciclos; primer cuadruplo = false
//no, meter a la pila
        term();

    }

    //term = factor termCiclo; 
    private static void term() {
        factor();
        termCiclo();
    }

    //factor = ident | number | "(" expression ")";
    //factor = 100 | 200 | 50 expression 51;  
    private static void factor() {
        switch (token) {
            case 100, 200 -> {
//meter pila
                token = nextToken();
                opnds.push(lexemas.get(indice - 1).getDato());
            }
            case 50 -> {
//meter a la pila
                token = nextToken();
                expression();
                if (token != 51) {
                    Errores.getError(v, 13);
                    return;
                }
                token = nextToken();//if token dif a 53
//saca pila
//mientras diferente de (
                //si primerCuadr, meter ciclos; primer caudruplo = false
                //generar cuadruplo
            }
        }
    }

    private static void generarCuad() {
        String op = oprs.pop();
        String opnd2 = "";
        String opnd1 = "";
        switch (op) {
            case "(" -> {
                return;
            }
            case ":=" -> {
                opnd2 = opnds.pop();
                opnd1 = opnds.pop();
                cuadruplos.add(new Cuadruplo(op, opnd2, "", opnd1));
            }
            case "=", "#", "<", "<=", ">", ">=" -> {
                opnd2 = opnds.pop();
                opnd1 = opnds.pop();
                incom.push(cuadruplos.size());
                cuadruplos.add(new Cuadruplo(complem(op), opnd1, opnd2, ""));
            }
            default -> {
                opnd2 = opnds.pop();
                opnd1 = opnds.pop();
                String result = "_R" + contador++;
                opnds.push(result);
                cuadruplos.add(new Cuadruplo(op, opnd1, opnd2, result));
            }

        }
        //String opnd2 = opnds.pop();
        //String opnd1 = opnds.pop();
        //String result = "R" + indice; //generar contador de Rs
        //cuadruplos.add(new Cuadruplo(op, opnd1, opnd2, result));
        //=
        //op log
        //( o )
    }

    private static boolean mayorOigualP(String tope, String actual) {
        //boolean sies= false;
        return preced(tope) >= preced(actual);
        //return sies;
    }

    private static int preced(String valor) {
        switch (valor) {
            case "^", "!" -> {
                return 10;
            }
            case "*", "/" -> {
                return 9;
            }

            case "+", "-" -> {
                return 8;
            }
            case ":=", "=", "#", "<", "<=", ">", ">=" -> {
                return 7;
            }

            case "(", ")" -> {
                return 6;
            }

        }
        return -1;

    }

    private static String complem(String opLog) {
        return switch (opLog) {
            case "=" ->
                "jne";
            case ":=" ->
                "jne";
            case "#" ->
                "je";
            case "<" ->
                "jge";
            case "<=" ->
                "jg";
            case ">" ->
                "jle";
            case ">=" ->
                "jl";
            default ->
                "jmp";
        };

    }
}

/*
Gramatica GLC1
Los corchetes son para opcional
Los parentesis tambien, y en algunas ocaciones se quedan

program = block "." ;

block = [ "const" ident "=" number {"," ident "=" number} ";"]
        [ "var" ident {"," ident} ";"]
        { "procedure" ident ";" block ";" } statement ;

statement = [ ident ":=" expression | "call" ident 
              | "?" ident | "!" expression 
              | "begin" statement {";" statement } "end" 
              | "if" condition "then" statement 
              | "while" condition "do" statement ];

condition = "odd" expression |
            expression ("="|"#"|"<"|"<="|">"|">=") expression ;

expression = [ "+"|"-"] term { ("+"|"-") term};

term = factor {("*"|"/") factor};

factor = ident | number | "(" expression ")";
 */
