/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Allison
 */
public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";
        public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }
        public static void createTable() throws SQLException, ClassNotFoundException {
        String sql = "CREATE TABLE mi_tabla ("
                + "id SERIAL PRIMARY KEY,"
                + "nombre VARCHAR(100) NOT NULL,"
                + "edad INTEGER,"
                + "email VARCHAR(100),"
                + "direccion VARCHAR(200)"
                + ")";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("Tabla creada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }
}
