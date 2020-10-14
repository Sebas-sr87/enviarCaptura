/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturar;

import java.io.File;

/**
 *
 * @author Sebastian Oliveros
 */
public class crearvideo implements Runnable {

    private String nombreimagen;

    public crearvideo(String imagen) {
        this.nombreimagen = imagen;
    }

    @Override
    public void run() {
        boolean conta = true;
        while (conta) {
                try {
                    String[] cmd = {"c:\\ffmpeg\\bin\\ffmpeg", "-r", "1/1", "-i", "C:\\Users\\Sebastian Oliveros\\Documents\\NetBeansProjects\\capturar\\Imagenes\\Horauno%01d.png", "-c:v", "libx264","-crf" ,"24", "-r", "30", "-pix_fmt", "yuv410p", "C:\\Users\\Sebastian Oliveros\\Documents\\NetBeansProjects\\capturar\\Imagenes\\"+nombreimagen+".mp4"};
                    Runtime.getRuntime().exec(cmd);
                } catch (Exception e) {
                    System.out.println("Error en comando: "+e.toString());
                }
//            eliminarArchivos(nombreimagen);
            conta = false;
        }
    }
    public static void eliminarArchivos(String nombre){
        for (int i = 1; i <= 60; i++) {
            File fichero = new File("C:\\Users\\Sebastian Oliveros\\Documents\\NetBeansProjects\\capturar\\Imagenes\\"+nombre+i+".png");
            boolean delete = fichero.delete();
            if(delete){
                System.out.println("Borro archivo: "+nombre+i);
            }else{
            System.out.println("NO Borro archivo: "+nombre+i);
            }
            
        }
    
    }

}
