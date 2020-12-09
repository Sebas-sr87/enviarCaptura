/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peticiones;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import model.dao.Usuario;
import org.w3c.dom.Document;

/**
 *
 * @author Jose
 */
public class login {
static String url="http://192.168.100.144:8080/Sapeo/";
    public static void main(String[] args) {
        try {
//            Usuario loginUusaurio = loginUusaurio("sebastian", "123d456");
//            if (loginUusaurio.getNombre() == null) {
//
//            } else {
//
//            }
//            estadoUsuario(1);
//cambiarEstado(true, 1);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }

    }

    public static Boolean cambiarEstado(boolean state, int id) {
        Boolean estado = Boolean.FALSE;
        try {
            URL url = new URL(login.url+"CambiarEstado");
//            URL url = new URL("http://200.75.13.14:8082/Capturas/RecibeXML.jsp");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Usuario use = Usuario.usuario;
            String xml = "<usuario><id>" + id + "</id><estado>"+state+"</estado></usuario>";
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            try (OutputStream outputStream = con.getOutputStream()) {
                byte[] b = xml.getBytes("UTF-8");
                outputStream.write(b);
                outputStream.flush();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String linea;
            String xml2 = "";
            while ((linea = in.readLine()) != null) {
                xml2 += linea + "\r\n";
                System.out.println("peticionGet: " + linea);
            }
            DOMParser parser = new DOMParser();
            parser.parse(new org.xml.sax.InputSource(new java.io.StringReader(xml2)));
            Document doc = parser.getDocument();
            String respuesta = doc.getElementsByTagName("respuesta").item(0).getTextContent();
            estado = Boolean.valueOf(respuesta.trim());
            System.out.println("respuesta = " + respuesta);

        } catch (Exception e) {
            System.out.println("Error enviarDatos: " + e.toString());

        }

        return estado;
    }

    public static Boolean estadoUsuario(int id) {
        Boolean estado = Boolean.FALSE;
        try {
            URL url = new URL(login.url+"EstadoUsuario");
//            URL url = new URL("http://200.75.13.14:8082/Capturas/RecibeXML.jsp");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Usuario use = Usuario.usuario;
            String xml = "<usuario><id>" + id + "</id></usuario>";
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            try (OutputStream outputStream = con.getOutputStream()) {
                byte[] b = xml.getBytes("UTF-8");
                outputStream.write(b);
                outputStream.flush();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String linea;
            String xml2 = "";
            while ((linea = in.readLine()) != null) {
                xml2 += linea + "\r\n";
                System.out.println("peticionGet: " + linea);
            }
            DOMParser parser = new DOMParser();
            parser.parse(new org.xml.sax.InputSource(new java.io.StringReader(xml2)));
            Document doc = parser.getDocument();
            String respuesta = doc.getElementsByTagName("respuesta").item(0).getTextContent();
            estado = Boolean.valueOf(respuesta.trim());
            System.out.println("respuesta = " + respuesta);

        } catch (Exception e) {
            System.out.println("Error enviarDatos: " + e.toString());

        }

        return estado;
    }

    public static Usuario loginUusaurio(String user, String pass) {
        Usuario usuario = new Usuario();
        String error = "";
        try {
            URL url = new URL(login.url+"LoginCapturas");
//            URL url = new URL("http://200.75.13.14:8082/Capturas/RecibeXML.jsp");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Usuario use = Usuario.usuario;
            String xml = "<usuario><user>" + user + "</user><pass>" + pass + "</pass></usuario>";
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            try (OutputStream outputStream = con.getOutputStream()) {
                byte[] b = xml.getBytes("UTF-8");
                outputStream.write(b);
                outputStream.flush();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String linea;
            String xml2 = "";
            while ((linea = in.readLine()) != null) {
                xml2 += linea + "\r\n";
                System.out.println("peticionGet: " + linea);
            }
            DOMParser parser = new DOMParser();
            parser.parse(new org.xml.sax.InputSource(new java.io.StringReader(xml2)));
            Document doc = parser.getDocument();
            String id = doc.getElementsByTagName("id").item(0).getTextContent();
            String nombre = doc.getElementsByTagName("nombre").item(0).getTextContent();
            String apellido = doc.getElementsByTagName("apellido").item(0).getTextContent();
            String estado = doc.getElementsByTagName("estado").item(0).getTextContent();
            String correo = doc.getElementsByTagName("correo").item(0).getTextContent();

            usuario = new Usuario(Integer.parseInt(id.trim()), nombre, apellido, Boolean.valueOf(estado), correo);

        } catch (Exception e) {
            System.out.println("Error enviarDatos: " + e.toString());

        }
        return usuario;

    }
}
