/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.mavenproject3;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;


/**
 *
 * @author Allison
 */
@WebServlet("/GuardarMiTablaServlet")
public class GuardarMiTablaServlet extends HttpServlet {

    private static String jsp;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Recibir parámetros del formulario
        String nombre = request.getParameter("nombre");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String email = request.getParameter("email");
        String direccion = request.getParameter("direccion");

        // Crear objeto MiTabla y asignarle los valores
        MiTabla miTabla = new MiTabla();
        miTabla.setNombre(nombre);
        miTabla.setEdad(edad);
        miTabla.setEmail(email);
        miTabla.setDireccion(direccion);

        // Crear instancia DAO
        MiTablaDAO miTablaDAO = new MiTablaDAO();

        try {
            // Invocar método create() para insertar el objeto en la base de datos
            miTablaDAO.insert(miTabla);
        } catch (SQLException ex) {
            Logger.getLogger(GuardarMiTablaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Verificar cambios en la base de datos (opcional)
        List<MiTabla> miTablaList = null;
        try {
            miTablaList = miTablaDAO.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(GuardarMiTablaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (MiTabla item : miTablaList) {
            System.out.println(item.getId() + " - " + item.getNombre() + " - " + item.getEdad() + " - " + item.getEmail() + " - " + item.getDireccion());
        }

        // Redireccionar a otra página (opcional)
        response.sendRedirect(GuardarMiTablaServlet.jsp);
    }
}

