
package ejercicio2;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.Logger;

/**
 * Clase para la gestión de un Log de errores
 * @author  David Jiménez Riscardo
 * @version 1.0
 */
public class Log {
 
    
    public enum Evento {
        OPEN_FILE_DONE, OPEN_FILE_FAIL, VALID_ACCOUNT, INVALID_ACCOUNT, APP_OPEN, APP_QUIT
    }
    
    /**
     * Método para crear un Log
     * @return Objeto de tipo Logger
     */
    public static Logger crearLog(){
        
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
            FileHandler fh = new FileHandler("filtrador.log", true);
            //Establecemos el formato del archivo
            SimpleFormatter sf = new SimpleFormatter();
            fh.setFormatter(sf);
            //Añadimos el manejador del archivo a nuestro log
            logger.addHandler(fh);
        } catch (IOException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logger;
    }

    /**
     * Método para personalizar los mensajes escritos en el Log
     * @param evento Eventos definidos en el tipo Enum
     * @param logger Objeto que referencia al log
     * @param info Campo que utilizaremos para mostrar información adicional en el log
     * @throws IOException 
     */
    public static void registrarEvento(Evento evento, Logger logger, String info) throws IOException {
        String mensaje;
        switch (evento) {
            case OPEN_FILE_DONE:
                //Registrar evento de archivo abierto
                mensaje = "Archivo abierto: "+info;
                mensaje += "\n";
                logger.log(Level.INFO, mensaje);
                break;
            case OPEN_FILE_FAIL:
                //Registrar evento de error de E/S al intentar abrir un archivo
                mensaje = "Error de E/S en ";
                mensaje += "\n";
                logger.log(Level.SEVERE, mensaje);
                break;
            case VALID_ACCOUNT:
                //Registrar evento de cuenta de usuario válida
                mensaje = "Cuenta de usuario válida. Línea aceptada: "+info;
                mensaje += "\n";
                logger.log(Level.INFO, mensaje);
                break;
            case INVALID_ACCOUNT:
                //Registrar evento de cuenta de usuario no válida
                mensaje = "Cuenta de usuario NO válida. Línea rechazada: "+info;
                mensaje += "\n";
                logger.log(Level.WARNING, mensaje);
                break;
            case APP_OPEN:
                //Registrar evento de aplicación iniciada
                mensaje = "Aplicación iniciada";
                mensaje += "\n";
                logger.log(Level.INFO, mensaje);
                break;
            case APP_QUIT:
                //Registrar evento de aplicación finalizada
                mensaje = "Aplicación finalizada";
                mensaje += "\n\n\n\n";
                logger.log(Level.INFO, mensaje);
                break;                  
        }        
    }//Fin de registrarEvento
    
    /*
    //Para testear la clase
    public static void main(String[] args) throws IOException {

        String info = null;       
        Logger logger = Log.crearLog();
        
        Log.registrarEvento(Evento.APP_OPEN,logger,info);
        Log.registrarEvento(Evento.APP_QUIT, logger,info);
        Log.registrarEvento(Evento.OPEN_FILE_DONE, logger,info);
        Log.registrarEvento(Evento.OPEN_FILE_FAIL, logger,info);
        Log.registrarEvento(Evento.VALID_ACCOUNT, logger,info);
        Log.registrarEvento(Evento.INVALID_ACCOUNT,logger,info);
    }*/

}//Fin de la clase
