/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiladora.simbolos;

/**
 *
 * @author corin
 */
public class Cuadruplo {
    private String op, opn1, opn2, result;

    public Cuadruplo(String op, String opn1, String result) {
        this.op = op;
        this.opn1 = opn1;
        this.result = result;
    }

    public Cuadruplo(String op, String result) {
        this.op = op;
        this.result = result;
    }

    public Cuadruplo(String op) {
        this.op = op;
    }
    
    public Cuadruplo(String op, String opn1, String opn2, String result) {
        this.op = op;
        this.opn1 = opn1;
        this.opn2 = opn2;
        this.result = result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
    @Override
    public String toString() {
        return "[" + op + "," + opn1 + "," + opn2 + "," + result + "]\n";
    }

    
}
