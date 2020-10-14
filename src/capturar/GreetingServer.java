/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturar;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GreetingServer extends Thread {

    private ServerSocket serverSocket;
    Socket server;

    public GreetingServer(int port) throws IOException, SQLException, ClassNotFoundException, Exception {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(180000);
    }

    public void run() {
        int i = 0;
        while (true) {
            try {
                server = serverSocket.accept();
                
                InputStream inputStream = server.getInputStream();
                BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(server.getInputStream()));
                ImageIO.write(img, "jpg", new File("C:\\Users\\Sebastian Oliveros\\Pictures\\hora" + i + ".jpg"));
                JFrame frame = new JFrame();
                frame.getContentPane().add(new JLabel(new ImageIcon(img)));
                frame.pack();
                frame.setVisible(true);

            } catch (SocketTimeoutException st) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;

            } catch (Exception ex) {
                System.out.println(ex);
            }
            i++;
        }
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException, Exception {
        Thread t = new GreetingServer(6066);
        t.start();
    }
}

//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.InputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.nio.ByteBuffer;
//import javax.imageio.ImageIO;
//
///**
// *
// * @author Sebastian Oliveros
// */
//public class GreetingServer {
//    
//    public static void main(String[] args) throws Exception {
//        try {
//            ServerSocket serverSocket = new ServerSocket(13085);
//            Socket socket = serverSocket.accept();
//            InputStream inputStream = socket.getInputStream();
//            
//            System.out.println("Reading: " + System.currentTimeMillis());
//            
//            byte[] sizeAr = new byte[8];
//            inputStream.read(sizeAr);
//            int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
//            
//            byte[] imageAr = new byte[size];
//            inputStream.read(imageAr);
//            
//            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
//            
//            System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
//            ImageIO.write(image, "jpg", new File("C:\\Users\\Sebastian Oliveros\\Pictures\\joker.jpg"));
//            Thread.sleep(120000);
//            serverSocket.close();
//        } catch (Exception e) {
//            System.out.println("Error receive: " + e.toString());
//        }
//        
//    }
//    
//}

//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.logging.Level;
//import java.util.logging.Logger;
// 
//public class GreetingServer {
// 
//    public static void main(String[] args) {
// 
//        ServerSocket servidor = null;
//        Socket sc = null;
//        DataInputStream in;
//        DataOutputStream out;
// 
//        //puerto de nuestro servidor
//        final int PUERTO = 5000;
// 
//        try {
//            //Creamos el socket del servidor
//            servidor = new ServerSocket(PUERTO);
//            System.out.println("Servidor iniciado");
// 
//            //Siempre estara escuchando peticiones
//            while (true) {
// 
//                //Espero a que un cliente se conecte
//                sc = servidor.accept();
// 
//                System.out.println("Cliente conectado");
//                in = new DataInputStream(sc.getInputStream());
//                out = new DataOutputStream(sc.getOutputStream());
// 
//                //Leo el mensaje que me envia
//                String mensaje = in.readUTF();
// 
//                System.out.println(mensaje);
// 
//                //Le envio un mensaje
//                out.writeUTF("¡Hola mundo desde el servidor!");
// 
//                //Cierro el socket
//                sc.close();
//                System.out.println("Cliente desconectado");
// 
//            }
// 
//        } catch (IOException ex) {
//            Logger.getLogger(GreetingServer.class.getName()).log(Level.SEVERE, null, ex);
//        }
// 
//    }
// 
//}