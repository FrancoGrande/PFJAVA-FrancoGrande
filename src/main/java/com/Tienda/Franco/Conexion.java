package com.Tienda.Franco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/agenda_db";
    private static final String USUARIO = "root";
    private static final String PASSWD = "123456";


    private static Connection conn =null;

    public static Connection obtenerConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(URL, USUARIO, PASSWD);
            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("error al conecat con la base de datos", e);
        }
    }

    public static void cerrarConexion() {

        try {
            if(conn !=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


