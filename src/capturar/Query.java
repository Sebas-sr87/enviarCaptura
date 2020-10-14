/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturar;

import capturar.bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.Usuario;

/**
 *
 * @author Sebastian Oliveros
 */
public class Query {

    public static synchronized Boolean estadoUsuario(int id) {
        Boolean estado = Boolean.FALSE;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select estado from usuario where id_usuario=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                estado = rs.getBoolean("estado");
            }
        } catch (Exception e) {
            System.out.println("Error estadoUsuario: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return estado;
    }

    public static synchronized Boolean cambiarEstado(boolean stdo, int id,Principal.MainDos maindos) {
        Boolean estado = Boolean.FALSE;
        String query = "update usuario set estado=? where id_usuario=?";
        Connection conexion = null;
        PreparedStatement ps = null;
        try {
            conexion = Conexion.conectar();
            ps = conexion.prepareStatement(query);
            ps.setBoolean(1, stdo);
            ps.setInt(2, id);
            int i = ps.executeUpdate();
            if (i != 0) {
                estado = Boolean.TRUE;
            }
        } catch (Exception e) {
             maindos.txtareaCapturas.append("\nError en el cambio de usuario ");
            System.out.println("Error consulta cambiarEstado: " + e.toString());
        } finally {
            try {
                conexion.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return estado;
    }


    public static Usuario login(String user, String pass) {
        Boolean ingresar = Boolean.FALSE;
        Usuario usuario = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from usuario where nombre=? and clave=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEstado(rs.getBoolean("estado"));
                ingresar = Boolean.TRUE;
            }
        } catch (Exception e) {
            System.out.println("Error login: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuario;
    }

    public static String lahora() {
        Boolean estado = Boolean.FALSE;
        String hora = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from horapc";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp fechadesde = rs.getTimestamp("fechahora");
                hora = new SimpleDateFormat("dd-MM-yyyy hh-mm").format(fechadesde);
            }
        } catch (Exception e) {
            System.out.println("Error lahora: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return hora;
    }
}
