/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.directorio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class infContacto implements Comparable, Serializable {
    
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String direccion;
    private String telefono;
    private infContacto izquierdo;
    private infContacto derecho; 
    
    
    

    public infContacto() {
    }
    
    
    public infContacto(int id, String nombre, String apellido, String correo, String direccion, String telefono, infContacto izquierdo , infContacto derecho) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.izquierdo = null;
        this.derecho = null;
    }

    public int compareTo (Object T) {
        infContacto otro = (infContacto) T;
        return nombre.compareToIgnoreCase(otro.nombre);
        
    }
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public infContacto getIzquierdo() {
        return izquierdo;
    }

    public infContacto getDererecho() {
        return derecho;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setNombres(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

public void setIzq(infContacto izq) {
    this.izquierdo = izquierdo; // Establece el hijo izquierdo del nodo actual
}

public void setDer(infContacto der) {
    this.derecho = derecho; // Establece el hijo derecho del nodo actual
}

public boolean Hoja() {
    return izquierdo == null && derecho == null; // Verifica si el nodo es una hoja (no tiene hijos)
}

public infContacto Menor() {
    return (izquierdo == null) ? this : izquierdo.Menor(); // Retorna el nodo más pequeño (menor valor) del subárbol izquierdo
}

public infContacto Mayor() {
    return (derecho == null) ? this : derecho.Mayor(); // Retorna el nodo más grande (mayor valor) del subárbol derecho
}

public int Altura() {
    if (Hoja()) // Si el nodo es una hoja, su altura es 1
        return 1;
    else {
        // Calcula la altura recursivamente tomando el máximo entre las alturas de los subárboles izquierdo y derecho
        int n1 = (izquierdo == null) ? 0 : izquierdo.Altura();
        int n2 = (derecho == null) ? 0 : derecho.Altura();
        return 1 + Math.max(n1, n2);
    }
}

public int peso_I() {
    int peso = 0;
    ArrayList f = new ArrayList();
    infContacto y = this;
    while (y != null) {
        peso++;
        if (y.izquierdo != null) {
            if (y.derecho != null)
                f.add(y.derecho); // Agrega el hijo derecho a la lista de nodos pendientes
            y = y.izquierdo; // Se mueve al hijo izquierdo
        } else if (y.derecho != null) {
            y = y.derecho; // Si no hay hijo izquierdo pero hay hijo derecho, se mueve al hijo derecho
        } else if (f.size() != 0) {
            y = (infContacto) f.get(0); // Si no hay hijos izquierdo ni derecho, se mueve al siguiente nodo pendiente en la lista
            f.remove(0);
        } else {
            y = null; // Si no hay más nodos pendientes, termina el bucle
        }
    }
    return peso; // Retorna el peso del árbol (cantidad de nodos)
}
public int Peso (){
    // Método que calcula el peso del subárbol que tiene al contacto como raíz
    int k1 = (izquierdo == null) ? 0 : izquierdo.Peso(); // Calcula el peso del subárbol izquierdo
    int k2 = (derecho == null) ? 0 : derecho.Peso(); // Calcula el peso del subárbol derecho
    return 1 + k1 + k2 ; // Retorna el peso total del subárbol
}

public int NumHojas (){
    // Método que cuenta el número de hojas en el subárbol que tiene al contacto como raíz
    if ( Hoja ( ) ) // Si el contacto es una hoja, retorna 1
        return 1;
    else {
        int j1 = (izquierdo == null ) ? 0 : izquierdo.NumHojas(); // Cuenta las hojas del subárbol izquierdo
        int j2 = (derecho == null)? 0 : derecho.NumHojas(); // Cuenta las hojas del subárbol derecho
        return j1 + j2 ;  // Retorna la suma de las hojas de ambos subárboles
    }
}

public void ingresar ( infContacto nuevo ) throws ContactoExistenteException{
    // Método para insertar un nuevo contacto en el árbol
    if(compareTo (nuevo) == 0 ) // Si el contacto ya existe en el árbol, lanza una excepción
        throw new ContactoExistenteException(nuevo.nombre);

    if ( compareTo (nuevo) > 0 ){ // Compara el nuevo contacto con el contacto actual para decidir en qué subárbol insertarlo
        if(izquierdo == null)
            izquierdo = nuevo; // Si el subárbol izquierdo está vacío, inserta el nuevo contacto aquí
        else
            izquierdo.ingresar (nuevo); // Si no está vacío, llama recursivamente a ingresar en el subárbol izquierdo
    }
    else{
        if(derecho == null)
            derecho = nuevo; // Si el subárbol derecho está vacío, inserta el nuevo contacto aquí
        else 
            derecho.ingresar(nuevo); // Si no está vacío, llama recursivamente a ingresar en el subárbol derecho
    }
}

public infContacto BuscarI(String Nombre ){
    // Método para buscar un contacto por su nombre en el subárbol que tiene al contacto como raíz
    infContacto y = this;
    while(y != null ){ // Recorre el subárbol mientras no se llegue a una hoja
        int z = y.nombre.compareToIgnoreCase(Nombre); // Compara el nombre del contacto actual con el nombre buscado
        if(z == 0) // Si son iguales, se encontró el contacto buscado y se retorna
            return y;
        else if ( z > 0)
            y = y.izquierdo; // Si el nombre buscado es menor, busca en el subárbol izquierdo
        else 
            y = y.derecho; // Si el nombre buscado es mayor, busca en el subárbol derecho
    }
    return null; // Si no se encuentra el contacto, retorna null
}

public infContacto Buscar ( String Nombres){
    // Método para buscar un contacto por su nombre en todo el árbol
    if(nombre.compareToIgnoreCase(Nombres)== 0) // Si el nombre del contacto actual es igual al nombre buscado, se encontró y se retorna
        return this; 
    else if (nombre.compareToIgnoreCase(Nombres) > 0) // Si el nombre buscado es menor, busca en el subárbol izquierdo
        return (izquierdo == null) ? null : izquierdo.Buscar(Nombres);
    else 
        return (derecho == null) ? null : derecho.Buscar(Nombres); // Si el nombre buscado es mayor, busca en el subárbol derecho
}

public infContacto borrar (String Nombre){
    // Método para eliminar un contacto del árbol
    if (Hoja( )) // Si el contacto es una hoja
        return null; // No se puede eliminar nada, entonces retorna null
    if(nombre.compareToIgnoreCase(nombre ) == 0){ // Si se encontró el contacto a eliminar
        if(izquierdo == null)
            return derecho; // Si no hay subárbol izquierdo, retorna el subárbol derecho
        if(derecho == null)
            return izquierdo; // Si no hay subárbol derecho, retorna el subárbol izquierdo
        infContacto sc = derecho.Menor(); // Encuentra el sucesor del contacto a eliminar
        derecho = derecho.borrar(sc.getNombre() ); // Borra el sucesor del subárbol derecho
        sc.izquierdo = izquierdo; // Actualiza los hijos del sucesor
        sc.derecho = derecho;
        return sc; // Retorna el sucesor
    }
    else if (nombre.compareToIgnoreCase(Nombre) > 0)
        izquierdo = izquierdo.borrar(Nombre); // Si el nombre buscado es menor, busca en el subárbol izquierdo
    else
        derecho = derecho.borrar(Nombre); // Si el nombre buscado es mayor, busca en el subárbol derecho
    return this;
}
}
// El resto de los métodos (Peso, NumHojas, ingresar, BuscarI, Buscar, borrar) parecen métodos relacionados con operaciones en un árbol binario de búsqueda. Cada método realiza una operación específica como calcular el peso del árbol, buscar un nodo, ingresar un nuevo nodo, etc.

    

