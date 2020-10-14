/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturar;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class GreetingClient {

    Image newimg;
    static BufferedImage bimg;
    byte[] bytes;

    public static void main(String[] args) {

        String serverName = "localhost";
        int port = 6066;
        while (true) {
            try {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Rectangle screenRectangle = new Rectangle(screenSize);
                Robot robot = new Robot();
                Socket client = new Socket(serverName, port);
                OutputStream outputStream = client.getOutputStream();
                bimg = robot.createScreenCapture(screenRectangle);
                ImageIO.write(bimg, "JPG", outputStream);
                client.close();
                Thread.sleep(10000);
            } catch (IOException | AWTException e) {
                e.printStackTrace();
            } catch (InterruptedException ex) {
                System.out.println("Error en thread: "+ex.toString());
            }

        }

    }
}
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.OutputStream;
//import java.net.Socket;
//import java.nio.ByteBuffer;
//import javax.imageio.ImageIO;

/**
 *
 * @author Sebastian Oliveros
 */
//public class GreetingClient {
//
//    public static void main(String[] args) throws Exception {
//        try {
//            Socket socket = new Socket("localhost", 13085);
//            OutputStream outputStream =  socket.getOutputStream();
//            
//            BufferedImage image = ImageIO.read(new File("C:\\Users\\Sebastian Oliveros\\Pictures\\hora0.jpg"));
//            
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            ImageIO.write(image, "jpg", byteArrayOutputStream);
//            
//            byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
//            outputStream.write(size);
//            outputStream.write(byteArrayOutputStream.toByteArray());
//            outputStream.flush();
//            System.out.println("Flushed: " + System.currentTimeMillis());
//            
//            Thread.sleep(120000);
//            System.out.println("Closing: " + System.currentTimeMillis());
//            socket.close();
//            
//  
//            
//        }catch(Exception e){
//            System.out.println("Error Send: "+e.toString());
//        }
//    }
//}
//import java.awt.AWTException;
//import java.awt.Dimension;
//import java.awt.Rectangle;
//import java.awt.Robot;
//import java.awt.Toolkit;
//import java.awt.image.BufferedImage;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.Socket;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.imageio.ImageIO;
//
//public class GreetingClient {
//
//    public static void main(String[] args) throws AWTException {
//
//        //Host del servidor
//        final String HOST = "localhost";
//        //Puerto del servidor
//        final int PUERTO = 5000;
//        DataInputStream in;
//        DataOutputStream out;
//
//        try {
//            //Creo el socket para conectarme con el cliente
//            Socket sc = new Socket(HOST, PUERTO);
//           
//
//            in = new DataInputStream(sc.getInputStream());
//            out = new DataOutputStream(sc.getOutputStream());
//            //Envio un mensaje al cliente
//            out.writeUTF("¡Hola mundo desde el cliente!");
//
//            //Recibo el mensaje del servidor
//            String mensaje = in.readUTF();
//
//            System.out.println(mensaje);
//
//            sc.close();
//
//        } catch (IOException ex) {
//            Logger.getLogger(GreetingClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//}
