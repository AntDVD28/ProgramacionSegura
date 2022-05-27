package ejercicio2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase ValidadorUsuarios
 * Recorreremos las cuentas de usuario contenidas en un archivo, guardando sólo las válidas en una estructura de datos y mostrándolas por pantalla
 * @author  David Jiménez Riscardo
 * @version 1.0
 */
public class ValidadorUsuarios {

    /**
     * Método principal del programa
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Estructura de datos para guardar el contenido del fichero          
        Map<String, Usuario> map = new TreeMap<String, Usuario>();
        
        //Instancio el archivo de log
        Logger logger = Log.crearLog();
        
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String nombre_archivo = null;
        System.out.println("Introduzca el nombre del fichero a leer:");
        
        try {
            nombre_archivo = br.readLine();
            
            //>>>>>>>>>Log: Mensaje de inicio del Log
            Log.registrarEvento(Log.Evento.APP_OPEN,logger,nombre_archivo);
            
            //Paso el contenido del fichero a una estructura de datos Map
            map = ValidadorUsuarios.leeFicheroToMap(nombre_archivo, logger);
            if (map != null) {
            //Muestro por pantalla la estructura Map
                ValidadorUsuarios.muestraMap(map);
            }
            
            //>>>>>>>>>Log: Mensaje fin del Log
            Log.registrarEvento(Log.Evento.APP_QUIT, logger,null);

        } catch (IOException ex) {
            Logger.getLogger(ValidadorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//Fin del método main

    
    
    /**
     * Método para volcar el contenido de un fichero en una estructura de tipo
     * Map
     *
     * @param nombre_archivo Nombre del fichero que contiene la información
     * @return Estructura de datos Map
     */
    public static Map<String, Usuario> leeFicheroToMap(String nombre_archivo, Logger logger) throws IOException {

        Map<String, Usuario> map = new TreeMap<String, Usuario>();
        FileReader fr;
        BufferedReader br;
        String linea;
        try {
            fr = new FileReader(nombre_archivo);
            if(fr!=null){
                //>>>>>>>>>>>>Log: Mensaje de archivo abierto
                Log.registrarEvento(Log.Evento.OPEN_FILE_DONE, logger, nombre_archivo);
                
                br = new BufferedReader(fr);
                linea = br.readLine();

                while (linea != null) {
                    String[] cadenas = linea.split(":");
                    //Antes de instanciar el objeto Usuario comprobaré si los datos son válidos
                    if (cadenas.length == 7) {
                        if (Util.validaUsuario(cadenas[0]) && Util.validaContrasenia(cadenas[1])
                                && Util.validaId(cadenas[2]) && Util.validaId(cadenas[3])
                                && Util.validaDescripcion(cadenas[4]) && Util.validaDirectorio(cadenas[5], cadenas[0])
                                && Util.validaCascaron(cadenas[6])) {
                            //Instancio el objeto de tipo usuario
                            Usuario usuario = new Usuario(cadenas[0], cadenas[1], Integer.parseInt(cadenas[2]), Integer.parseInt(cadenas[3]), cadenas[4], cadenas[5], cadenas[6]);
                            //Guardo en el map la clave(nombre del usuario) y los valores(objeto Usuario)
                            map.put(cadenas[0], usuario);
                            //>>>>>>>>>>>Log: Mensaje de usuario válido
                            Log.registrarEvento(Log.Evento.VALID_ACCOUNT, logger, cadenas[0]);
                        }else{
                            Log.registrarEvento(Log.Evento.INVALID_ACCOUNT, logger, cadenas[0]);
                        }
                    }else {
                        Log.registrarEvento(Log.Evento.INVALID_ACCOUNT, logger, cadenas[0]);
                    }
                    linea = br.readLine();
                }
                if (br != null) br.close();  
                if (fr != null) fr.close();
            }                  
        } catch (FileNotFoundException ex) {
            System.out.println("Sin acceso al archivo especificado. Error: " + ex.getMessage());
            //>>>>>>>>>>>>>>Log: Mensaje No se puede abrir el archivo
            Log.registrarEvento(Log.Evento.OPEN_FILE_FAIL, logger, nombre_archivo);
            map = null;
        } catch (IOException ex) {
            System.out.println("Error de E/S. Error: " + ex.getMessage());
            //>>>>>>>>>>>>>>Log: Mensaje No se puede abrir el archivo
            Log.registrarEvento(Log.Evento.OPEN_FILE_FAIL, logger, nombre_archivo);
            map = null;
        }
        return map;
    }//Fin de leeFichero

    
    /**
     * Método para mostrar el contenido de una estructura Map
     *
     * @param map Estructura de datos a mostrar
     */
    public static void muestraMap(Map<String, Usuario> map) {

        System.out.println("LISTA DE USUARIOS CORRECTOS");
        System.out.println("===========================");
        System.out.println("Total de usuarios correctos: " + map.size());
        int num = 1;
        for (String clave : map.keySet()) {
            Usuario usuario = map.get(clave);
            System.out.println(num + ": usuario= " + clave + " " + usuario.toString());
            num++;
        }
    }//Fin de muestraMap

}

