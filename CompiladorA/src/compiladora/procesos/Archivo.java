/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compiladora.procesos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import ventanas.Principal;

/**
 *
 * @author corin
 */
public class Archivo {
    private static File getFile(Principal v){
           
        String filePath;
        File selectedFile = null;
        JFileChooser fileChooser = new JFileChooser();

        // Mostrar el diálogo para abrir archivos
        int result = fileChooser.showOpenDialog(v);

        // Si se seleccionó un archivo, obtener la ruta
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
            v.getTxtSalida().setText("Ruta del archivo seleccionado: " + filePath);
            //System.out.println("Ruta del archivo seleccionado: " + filePath);
        } else {
           v.getTxtSalida().setText("No se seleccionó ningún archivo.");
            //System.out.println("No se seleccionó ningún archivo.");
        //}       
    }
    return selectedFile;
    }
    
     private static String getContenido(Principal v){
         //File archivo = getFile(v);
         File archivo = new File("C:\\Users\\corin\\Documents\\Prueba2.txt");
         if (archivo == null){
             return "";
         }
         
         StringBuilder contenido = new StringBuilder();
         
          try (FileReader fileReader = new FileReader(archivo);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            // Lee cada línea del archivo y la agrega al StringBuilder
            while ((line = bufferedReader.readLine()) != null) {
                contenido.append(line).append("\n");
            }
        } catch (IOException e) {
            v.getTxtSalida().setText("Error al leer el archivo " + e.getMessage());
        }   
         return contenido.toString();
     }
     
     public static void mostrarContenido(Principal v){
         String contenido = getContenido(v);
         v.getTxtContenido().setText(contenido);
     }
     
    
    }

    