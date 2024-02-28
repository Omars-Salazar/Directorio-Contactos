/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.umariana.directorio.Arbol_b;
import com.umariana.directorio.ContactoExistenteException;
import com.umariana.directorio.infContacto;
import com.umariana.directorio.metodos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
@WebServlet(name = "SvDirectorio", urlPatterns = {"/SvDirectorio"})
public class SvDirectorio extends HttpServlet {
    
    // Método privado para obtener una lista de contactos de un árbol binario
    private ArrayList<infContacto> lista ( Arbol_b arbol_b){
        return arbol_b.listaDeContactos();
    }

    // Método para manejar las solicitudes GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext(); // Obtiene el contexto del servlet
        Arbol_b lC = (Arbol_b) context.getAttribute("arbol_b"); // Obtiene el árbol de contactos del contexto
        
        // Si el árbol de contactos es nulo, redirecciona a index.jsp
        if(lC == null){
            resp.sendRedirect("index.jsp");
            return;
        }
        
        String action = req.getParameter("action"); // Obtiene el parámetro "action" de la solicitud
        
        // Si la acción es "eliminarC", elimina un contacto
        if("eliminarC".equals(action)){
            String nombre = req.getParameter("nombre"); // Obtiene el nombre del contacto a eliminar
            System.out.println("Eliminado: "+ nombre); // Imprime el nombre del contacto eliminado
            
            try{
                lC.borrarContac(nombre); // Llama al método para eliminar el contacto del árbol
                System.out.println("Contac deletd successfully"); // Imprime un mensaje de éxito en la eliminación
                req.setAttribute("eliminarC", nombre); // Establece un atributo en la solicitud indicando el contacto eliminado
                metodos.añadirContacto(lC, context); // Llama a un método para agregar el contacto actualizado al contexto
                HttpSession session = req.getSession(); // Obtiene la sesión HTTP
                session.setAttribute("lC", lC.listaDeContactos()); // Establece un atributo en la sesión con la lista actualizada de contactos
                resp.sendRedirect(req.getContextPath() + "/index.jsp" ); // Redirecciona a index.jsp
            } catch (Exception e){
                e.printStackTrace(); // Imprime la traza de la excepción
                req.setAttribute("deleteError", "Error deleting contact"); // Establece un atributo en la solicitud indicando un error en la eliminación del contacto
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp"); // Obtiene el despachador de la solicitud para redireccionar a index.jsp
                dispatcher.forward(req, resp); // Redirecciona a index.jsp
            }
        }
    }
    
    // Método para manejar las solicitudes POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext context = getServletContext(); // Obtiene el contexto del servlet
        
        Arbol_b arbol_b = (Arbol_b) context.getAttribute("arbol_b"); // Obtiene el árbol de contactos del contexto
        
        // Si el árbol de contactos es nulo, crea un nuevo árbol
        if (arbol_b == null){
            arbol_b = new Arbol_b ();
            context.setAttribute("arbol_b", arbol_b); // Establece el nuevo árbol en el contexto
        } 
        
        // Obtiene los parámetros del formulario para agregar un nuevo contacto
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String correo = request.getParameter("correo");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        
        
        try { 
            // Guarda el nuevo contacto en el árbol
            arbol_b.guardarContacto(Integer.parseInt(id), nombre, apellido, correo, direccion, telefono);
            metodos.añadirContacto(arbol_b, context); // Llama a un método para agregar el contacto actualizado al contexto
            ArrayList<infContacto> lC = lista (arbol_b); // Obtiene la lista actualizada de contactos
            HttpSession session = request.getSession(); // Obtiene la sesión HTTP
            session.setAttribute("lC", lC); // Establece un atributo en la sesión con la lista actualizada de contactos
            session.setAttribute("alertType", "success"); // Establece un atributo en la sesión para indicar un mensaje de éxito
            response.sendRedirect(request.getContextPath() + "/index.jsp"); // Redirecciona a index.jsp
            
        } catch (ContactoExistenteException e){
            HttpSession session = request.getSession(); // Obtiene la sesión HTTP
            session.setAttribute("alerType", "danger"); // Establece un atributo en la sesión para indicar un mensaje de peligro
            
            response.sendRedirect(request.getContextPath() + "/index.jsp"); // Redirecciona a index.jsp
        }
    }
        

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
