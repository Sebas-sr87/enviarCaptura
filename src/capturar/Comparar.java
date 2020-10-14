/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturar;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Sebastian Oliveros
 */
public class Comparar {

    public static BufferedImage imagen1 = null;
    public static BufferedImage imagen2 = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int height1 = 0;
        int width1 = 0;
        int height2 = 0;
        int width2 = 0;
        try {
            File fileA = new File("C:\\Users\\Sebastian Oliveros\\Downloads\\lib\\horatres2.jpg");
            File fileB = new File("C:\\Users\\Sebastian Oliveros\\Downloads\\lib\\horatres3.jpg");

            imagen1 = recortarImagen(fileA);
            imagen2 = recortarImagen(fileB);
            height1 = imagen1.getHeight();
            width1 = imagen1.getWidth();
            height2 = imagen2.getHeight();
            width2 = imagen2.getWidth();
        } catch (Exception e) {
            System.out.println("añadir imagenes");
        }

        long difference = 0;
        for (int y = 0; y < height1; y++) {
            for (int x = 0; x < width1; x++) {
                int rgbA = imagen1.getRGB(x, y);
                int rgbB = imagen2.getRGB(x, y);
                int redA = (rgbA >> 16) & 0xff;
                int greenA = (rgbA >> 8) & 0xff;
                int blueA = (rgbA) & 0xff;
                int redB = (rgbB >> 16) & 0xff;
                int greenB = (rgbB >> 8) & 0xff;
                int blueB = (rgbB) & 0xff;
                difference += Math.abs(redA - redB);
                difference += Math.abs(greenA - greenB);
                difference += Math.abs(blueA - blueB);
            }
        }
        double total_pixels = width1 * height1 * 3;
        double avg_different_pixels = difference / total_pixels;
        double percentage = (avg_different_pixels / 255) * 100;
        System.out.println("total_pixeles: " + total_pixels);
        System.out.println("Diferencia: " + difference);
        System.out.println("Diferencia porcentaje: " + percentage);
//          
    }

    public static int printPixelARGB(int pixel, int tamano) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        Color a = Color.BLACK;
        int rgbblack = a.getRGB();
        Color b = new Color(red, green, blue, alpha);
        int rgbimagen = b.getRGB();
        if (rgbblack == rgbimagen) {
            return 1;
        }
//        System.out.println("rgb: "+rgbblack);
//        System.out.println("rgb2: "+rgb1);
//        System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
        return 0;
    }

    public static boolean comprobarNegro(BufferedImage img) {
        boolean esNegro = false;
        int tamano = img.getHeight() * img.getWidth();
        int cantidadnegro = 0;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                cantidadnegro += printPixelARGB(rgb, tamano);
            }
        }
        if (tamano == cantidadnegro) {
            esNegro = true;
            System.out.println("La pantalla enviada es negra");
        }
        return esNegro;
    }

    public static BufferedImage recortarImagen(File file) {
        BufferedImage subimage = null;
        try {
            BufferedImage img1 = ImageIO.read(file);
            int height = img1.getHeight();
            int width = img1.getWidth();
            int comienzox = 50;
            int comienzoy = 50;
            subimage = img1.getSubimage(comienzox, comienzoy, (width - 100), (height - 100));

        } catch (IOException ex) {
            Logger.getLogger(Comparar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subimage;
    }

    public static void comparar() {
//        double

    }

    public static BufferedImage transImagen(String img) {
        BufferedImage imagenTransformada = null;
        try {
            byte[] imagenbyte = Base64.getDecoder().decode(img);
            ByteArrayInputStream bais = new ByteArrayInputStream(imagenbyte);
            imagenTransformada = ImageIO.read(bais);
        } catch (Exception e) {
            System.out.println("Error: 0" + e.toString());
        }
        return imagenTransformada;
    }

    public static String transImagen(BufferedImage img) {
        String imagentexto = "";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            byte src[] = baos.toByteArray();
            imagentexto = Base64.getEncoder().encodeToString(src);
        } catch (Exception e) {
        }
        return imagentexto;
    }

}
