/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturar;

import Principal.MainDos;
import static capturar.Query.login;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.dao.Usuario;
import peticiones.login;

/**
 *
 * @author Sebastian Oliveros
 */
public class Capturar implements Runnable {

    public static Usuario user = Usuario.usuario;
    public static boolean cambio;
    public MainDos md;

    public Capturar(MainDos md) {
        this.md = md;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AWTException, InterruptedException, IOException {
        // TODO code application logic here

        String[] miArrreglo = {"Horauno", "Horados", "Horatres", "Horacuatro", "Horacinco", "Horaseis", "Horasiete", "Horaocho", "Horanueve"};
        int i = 0;
        while (true) {
            String nombre = miArrreglo[2];
            Date a = new Date();
//            System.out.println("Comenzo a las: " + a);
            for (int j = 0; j < 60; j++) {
                try {
                    String nombrecompleto = nombre + j;
                    capturarImagen(nombrecompleto, i);
                    Thread.sleep(59000);
                } catch (Exception e) {
                    System.out.println("Error capturar: " + e.toString());
                }
            }
            Date b = new Date();
//            System.out.println("Termino a las: " + b);
            i++;
        }

    }

    public static void captura(String name) {
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRectangle = new Rectangle(screenSize);
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRectangle);

            File file = new File("Imagenes/" + name + ".png");
            ImageIO.write(image, "png", file);
        } catch (HeadlessException | AWTException | IOException e) {
            System.out.println("Error: " + e.toString());
        }

    }

    public static Boolean capturarImagen(String nombreImagen, int numerovideo) throws IOException {
        Boolean enviado = Boolean.FALSE;
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRectangle = new Rectangle(screenSize);
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(screenRectangle);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byte src[] = baos.toByteArray();
            String imagenString = Base64.getEncoder().encodeToString(src);
            enviado = enviarDatos(imagenString, nombreImagen, numerovideo);
        } catch (Exception e) {
            System.out.println("Error capturarImagen: " + e.toString());
        }
        return enviado;
    }

    public static Boolean enviarDatos(String imagenString, String nombreImagen, int numerovideo) {
        Boolean enviado = Boolean.FALSE;
        try {
//          URL url = new URL("http://localhost:8084/Sapeo/RecibeXML.jsp");
            URL url = new URL("http://200.75.13.14:8082/Capturas/RecibeXML.jsp");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Usuario use = Usuario.usuario;
            String xml = "<usuario>\n"
//                    + "<idusuario>" + use.id_usuario + "</idusuario>"
//                    + "	<nombre>" + use.nombre+ "</nombre>\n"
                    + "<idusuario>" + 1 + "</idusuario>"
                    + "	<nombre>sebastian</nombre>\n"
                    + "<horavideo>" + numerovideo + "</horavideo>"
                    + "	<imagen>" + imagenString + "</imagen>\n"
                    + "<nombreimagen>" + nombreImagen + "</nombreimagen>"
                    + "</usuario>";
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            try (OutputStream outputStream = con.getOutputStream()) {
                byte[] b = xml.getBytes("UTF-8");
                outputStream.write(b);
                outputStream.flush();
            }
           
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream())); 
//            System.out.println(con.getErrorStream());
            enviado = Boolean.TRUE;
            System.out.println("Se ha enviado una captura!");
        } catch (Exception e) {
            System.out.println("Error enviarDatos: " + e.toString());

        }
        return enviado;
    }

    @Override
    public void run() {

        while (true) {
            String[] miArrreglo = {"Horauno", "Horados", "Horatres", "Horacuatro", "Horacinco", "Horaseis", "Horasiete", "Horaocho", "Horanueve"};
            int i = 0;
//            System.out.println(" whiletrue");
            while (cambio) {
                String nombre = miArrreglo[i];
//                System.out.println(" while Cambio true");
                for (int j = 0; j < 60 && cambio == true; j++) {
//                    System.out.println(" for Cambio true");
                    try {
                        String nombrecompleto = nombre + j;
                        Boolean estadoUsuario = login.estadoUsuario(user.getId_usuario());
                        if (estadoUsuario) {
                            Boolean capturarImagen = capturarImagen(nombrecompleto, i);
                            if (capturarImagen) {
                                this.md.agregarTextArea("Se ha enviado una captura correctamente.");
                            } else {
                                this.md.agregarTextArea("No se ha enviado una captura correctamente.");
                            }
                        } else {
                            this.md.agregarTextArea("Usuario Ocupado.");
                        }
                        Thread.sleep(59000);
                    } catch (Exception e) {
                        System.out.println("Error capturar: " + e.toString());
                    }
                }
            }
        }

    }
}
