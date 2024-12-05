/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiladora.procesos;

import ventanas.Principal;

/**
 *
 * @author corin
 */
public class Errores {

    public static void getError(Principal p, int nerr) {

        String msg = switch (nerr) {
            case 1 ->
                "\nSe esperaba un punto";
            case 2 ->
                "\nSe esperaba la palabra reservada CONST";
            case 3 ->
                "\nSe esperaba un identificador";
            case 4 ->
                "\nSe esperaba un =";
            case 5 ->
                "\nSe esperaba un numero";
            case 6 ->
                "\nSe esperaba un ;";
            case 7 ->
                "\nSe esperaba un Identificador, call, !, begin, if o while";
            case 8 ->
                "\nSe esperaba un :=";
            case 9 ->
                "\nSe esperaba un end";
            case 10 ->
                "\nSe esperaba un then";
            case 11 ->
                "\nSe esperaba un do";
            case 12 ->
                "\nSe esperaba un operador logico";
            case 13 ->
                "\nSe esperaba un identificador o número";
            default ->
                "\nError desconocido, contacte al programador";
        };
        p.getTxtSalida().append(msg);

    }

}
