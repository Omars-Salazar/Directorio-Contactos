/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.directorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.servlet.ServletContext;
/**
 *
 * @author Asus
 */
public class metodos {
    // Clase que contiene métodos para manipular contactos
    
    // Método para añadir un contacto al archivo de datos
    public static void añadirContacto(Arbol_b contac, ServletContext context) throws IOException {
        // Obtener la ruta relativa al archivo de datos
        String relativePath = "/data/contac.ser";
        // Obtener la ruta absoluta del archivo de datos en el contexto del servlet
        String absPath = context.getRealPath(relativePath);
        // Crear un objeto de tipo File para el archivo de datos
        File arc = new File(absPath);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arc))) {
            // Escribir el objeto Arbol_b en el archivo de datos
            oos.writeObject(contac);
            System.out.println("Contactos añadidos");
        } catch (IOException e) {
            // Manejar cualquier excepción de entrada/salida
            e.printStackTrace();
            System.out.println("ERROR AL AÑADIR, REVISE CORRECTAMENTE E INTENTELO DE NUEVO");
        }
    }
    
    // Método para cargar los contactos desde el archivo de datos
    public static Arbol_b cargarContactos(ServletContext context) throws IOException, ClassNotFoundException {
        // Inicializar el objeto Arbol_b para los contactos
        Arbol_b directorioo = null;
        // Obtener la ruta relativa al archivo de datos
        String relativePath = "/data/contac.ser";
        // Obtener la ruta absoluta del archivo de datos en el contexto del servlet
        String absPath = context.getRealPath(relativePath);
        // Crear un objeto de tipo File para el archivo de datos
        File arc = new File(absPath);
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arc))) {
            // Leer el objeto Arbol_b desde el archivo de datos
            directorioo = (Arbol_b) ois.readObject();
            System.out.println("CONTACTOS CARGADOS");
        } catch (IOException | ClassNotFoundException e) {
            // Manejar cualquier excepción de entrada/salida o de clase no encontrada
            e.printStackTrace();
            System.out.println("ERROR AL CARGAR CONTACTOS, REVISE E INTENTE NUEVAMENTE");
        }
        
        // Si el objeto Arbol_b está vacío, inicializarlo
        if (directorioo == null) {
            directorioo = new Arbol_b();
        }
        
        // Devolver el objeto Arbol_b cargado o inicializado
        return directorioo;
    }        
}

