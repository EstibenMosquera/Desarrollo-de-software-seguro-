/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iudigital.func.center.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.iudigital.func.center.utils.Mensajes;

/**
 *
 * @author Eliath
 */
public class Conexion {
    
    
    private final String URL = "jdbc:mysql://localhost:3306/IUD_ELIATH_Funcionarios?serverTimezone=UTC";
    private final String USUARIO = "root";
    private final String PASSWORD = "12345";


    private Connection con;

    public Conexion() {
        try {
            this.con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            con.createStatement();
        } catch (SQLException ex) {
            Mensajes.mensajeError(ex.getMessage(), "Error De Conexión");
        }
    }
    
    

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    
}
