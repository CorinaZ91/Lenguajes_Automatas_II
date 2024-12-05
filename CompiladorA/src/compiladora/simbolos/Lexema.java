/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiladora.simbolos;

/**
 *
 * @author corin
 */
public class Lexema {
    private final String dato;
    private final int token;
    private String valor;

    public Lexema(String dato, int token) {
        this.dato = dato;
        this.token = token;
        
    }

    public String getDato() {
        return dato;
    }

    public int getToken() {
        return token;
    }

    public String getValor() {
        return valor;
    }
    
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        return "[ " + dato + '\t' + token + " ]";
    }
    
    
}
