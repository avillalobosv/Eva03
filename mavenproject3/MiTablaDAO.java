/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Allison
 */
public class MiTablaDAO {
    private static final String TABLE_NAME = "mi_tabla";

    public void insert(MiTabla miTabla) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (nombre, edad, email, direccion) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, miTabla.getNombre());
            pstmt.setInt(2, miTabla.getEdad());
            pstmt.setString(3, miTabla.getEmail());
            pstmt.setString(4, miTabla.getDireccion());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiTablaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // Método para obtener una lista de todas las filas de la tabla.
    public List<MiTabla> getAll() throws SQLException {
        List<MiTabla> miTablaList = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                MiTabla miTabla = new MiTabla();
                miTabla.setId(rs.getInt("id"));
                miTabla.setNombre(rs.getString("nombre"));
                miTabla.setEdad(rs.getInt("edad"));
                miTabla.setEmail(rs.getString("email"));
                miTabla.setDireccion(rs.getString("direccion"));
                miTablaList.add(miTabla);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MiTablaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return miTablaList;
    }
}
