/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturar.bd;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Sebastian Oliveros
 */
public class Conexion {
    public static String HOST = "192.168.100.144";
    //public static String DATABASE = "acdkall_test";
    public static String DATABASE = "Capturar";
    //public static String USER = "user_acdkall";
    public static String USER = "postgres";
    //public static String PASS = "acdkall2012";
    public static String PASS = "adp2019";
    public static Connection conectar(){
        Connection con = null;
        try{
            Class.forName("org.postgresql.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://"+HOST+":5432/"+DATABASE,USER,PASS);
        }catch(Exception e){
            System.out.println("Error en conectar de ConexionPostgres: "+e);
        }
        return con;
    }
    public static void main(String[] args) {
        conectar();
    }
}
