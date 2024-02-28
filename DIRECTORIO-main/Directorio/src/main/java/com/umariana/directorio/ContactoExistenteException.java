/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.directorio;

import java.io.Serializable;
/**
 *
 * @author Asus
 */
public class ContactoExistenteException extends Exception implements Serializable {
    /**
     * Constructor de la clase ContactoExistenteException.
     * @param nC El nombre del contacto que ya existe.
     */
    public ContactoExistenteException(String nC) {
        // Llama al constructor de la clase padre (Exception) con un mensaje personalizado.
        super("Ya existe: " + nC);
    }
}
