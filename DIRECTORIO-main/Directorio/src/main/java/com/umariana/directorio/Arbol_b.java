package com.umariana.directorio;


import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Asus
 */
public class Arbol_b implements Serializable {
    
    private infContacto contacRaiz; // Contacto que sirve como raíz del árbol
    
    private int listaContac; // Contador para llevar el número de contactos en el árbol
    
    // Constructor de la clase Arbol_b
    public Arbol_b( ){
        contacRaiz = null; // Inicializa la raíz como nula
        listaContac = 0; // Inicializa el contador de contactos en 0
    }
    
    // Método para obtener una lista de todos los contactos en el árbol
    public ArrayList<infContacto> listaDeContactos () {
        ArrayList<infContacto> lC = new ArrayList<>(); // Crea una nueva lista de contactos
        guardarNuevoContactoL(contacRaiz,lC); // Llama al método privado para guardar los contactos en la lista
        return lC; // Retorna la lista de contactos
    }
    
    // Método privado para guardar los contactos en una lista recursivamente
    private void guardarNuevoContactoL (infContacto contac, ArrayList<infContacto>lC){
        if(contac != null) { // Si el contacto no es nulo
            guardarNuevoContactoL(contac.getIzquierdo(), lC); // Llama recursivamente para el subárbol izquierdo
            lC.add(contac); // Agrega el contacto actual a la lista
            guardarNuevoContactoL(contac.getDererecho(),lC); // Llama recursivamente para el subárbol derecho
        }   
    }
    
    // Método privado para verificar si un contacto ya está guardado en el árbol
    private boolean contactoYaGuardado (infContacto contac,  String nombre){
        if(contac == null){ // Si el contacto es nulo, no está guardado
            return false;
        }
        int r = nombre.compareTo(contac.getNombre()); // Compara el nombre del contacto actual con el nombre buscado
        if(r == 0){ // Si son iguales, el contacto ya está guardado
            return true; 
        }else if( r < 0 ){ // Si el nombre buscado es menor, busca en el subárbol izquierdo
            return contactoYaGuardado (contac.getIzquierdo(), nombre);
        }else{ // Si el nombre buscado es mayor, busca en el subárbol derecho
            return contactoYaGuardado ( contac.getDererecho(), nombre);
        }
    }
  
    // Método para guardar un nuevo contacto en el árbol
    public void guardarContacto (int id, String nombre, String apellido, String correo,String direccion, String telefono)throws ContactoExistenteException{
        infContacto n = new infContacto(id,nombre,apellido,correo,direccion,telefono,null,null); // Crea un nuevo contacto
        if(contactoYaGuardado(contacRaiz, nombre)){ // Verifica si el contacto ya está guardado
            throw new ContactoExistenteException(nombre); // Si está guardado, lanza una excepción
        }
        if(contacRaiz == null){ // Si el árbol está vacío, el nuevo contacto se convierte en la raíz
            contacRaiz = n;
        } else
            contacRaiz.ingresar(n); // Si no está vacío, llama al método ingresar del contacto raíz para insertar el nuevo contacto en el árbol
        listaContac++; // Incrementa el contador de contactos
    }
    
    // Método para eliminar un contacto del árbol
    public void borrarContac(String nombre){
        contacRaiz = contacRaiz.borrar(nombre); // Llama al método borrar del contacto raíz para eliminar el contacto
        listaContac--; // Decrementa el contador de contactos
    }
    
    // Método para buscar un contacto por su nombre en el árbol
    public infContacto  buscarC (String nombre){
        return contacRaiz == null ? null : contacRaiz.Buscar(nombre); // Llama al método Buscar del contacto raíz para buscar el contacto por su nombre
    }
    
    // Método para obtener el peso del árbol (número total de nodos)
    public int Peso() {
        return contacRaiz == null ? 0: contacRaiz.Peso(); // Retorna el peso del árbol o 0 si está vacío
    }
}

