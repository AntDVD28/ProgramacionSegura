/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplos;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;



/**
 * Ejemplo para aprender a crear un log básico en Java
 * @author  David Jiménez Riscardo
 * @version 1.0
 */
public class ejemploLog {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //Creamos o buscamos el logger a utilizar
        Logger logger = Logger.getLogger("MyLog");
        //Establecemos los niveles de seguridad de los eventos a registrar
        //Indicar los niveles del mayor al menor, en caso contrario no recogerá alguno
        logger.setLevel(Level.SEVERE);
        logger.setLevel(Level.WARNING);
        logger.setLevel(Level.INFO);

        //Indicamos que no queremos ver los mensajes por pantalla
        logger.setUseParentHandlers(false);
        
        try {          
            //Vínculamos el logger a un fichero
            //Indicamos el valor "true" para que los registros se añadan
            FileHandler fh = new FileHandler("mylog.txt", true);         
            //Establecemos el formato del archivo
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            //Añadimos el manejador del archivo a nuestro log
            logger.addHandler(fh);
             
        } catch (IOException ex) {
            Logger.getLogger(ejemploLog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ejemploLog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Ejemplos de registrar eventos
        logger.log(Level.INFO, "Esto es una información\n");
        logger.log(Level.WARNING, "Este es un warning\n");
        logger.log(Level.SEVERE, "Este es un error grave\n");
        logger.log(Level.FINE, "Este no se va a guardar\n");
    }     
    
}
