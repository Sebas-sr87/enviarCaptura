/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capturar;

//import com.profesorfalken.jpowershell.PowerShell;
//import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
//import com.profesorfalken.jpowershell.PowerShellResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Oliveros
 */
public class Procesos {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferedReader crearScript = crearScript();
//        while (true) {
//            try {
                ejecutarScript(crearScript);
//                Thread.sleep(500);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Procesos.class.getName()).log(Level.SEVERE, "Error al ejecutar el script", ex);
//            }
//        }

//      
    }

    public static void ejecutarScript(BufferedReader objReader) {
//        try (PowerShell powerShell = PowerShell.openSession()) {
//            System.out.println("******Ejecutando Script******");
//            PowerShellResponse executeScript = powerShell.executeScript(objReader);
//            System.out.println("Script output:" + executeScript.getCommandOutput());
            while (true) {    
//               PowerShellResponse executCommand = powerShell.executeCommand("$a = [tricks]::GetForegroundWindow()");

//                PowerShellResponse executeCommand = powerShell.executeCommand("get-process | ? { $_.mainwindowhandle -eq $a } | Select -ExpandProperty Name");
//                System.out.println("Script output:" + executeCommand.getCommandOutput());
            }
            
//            powerShell.close();
//        } catch (Exception e) {
//            System.out.println("Error ejecutar Script: " + e.toString());
//        }
    }
   

    public static BufferedReader crearScript() {
        BufferedReader objReader = null;
        try {
            File archivo = new File("texto.ps1");
            if (!archivo.exists()) {
                FileWriter escribir = new FileWriter(archivo, true);
                String salto = System.getProperty("line.separator");
                escribir.write("Add-Type @\"" + salto + "using System;" + salto + "using System.Runtime.InteropServices;" + salto
                        + " public class Tricks { " + salto + "	[DllImport(\"user32.dll\")]" + salto + "	public static extern IntPtr GetForegroundWindow();}" + salto
                        + "\"@" + salto + "$a = [tricks]::GetForegroundWindow() " + salto
                        + "get-process | ? { $_.mainwindowhandle -eq $a } | Select -ExpandProperty Name");
                escribir.close();
            }
            System.out.println("******Obteniendo Script******");
            objReader = new BufferedReader(new FileReader(archivo));
        } catch (Exception e) {
            System.out.println("Error crear Script:" + e.toString());
        }
        return objReader;

    }
}
