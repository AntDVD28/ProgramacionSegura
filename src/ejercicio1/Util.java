package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase de utilidad para validar la información
 *
 * @author David Jiménez Riscardo
 * @version 1.0
 */
public class Util {

    /**
     * Método para validar el nombre de usuario Sólo podrá estar formado por
     * letras minúsculas y números. Tamaño mínimo 4 caracteres, tamaño máximo
     * 10.
     * NOTA IMPORTANTE: en el enunciado se indicaba un mínimo de 4, yo he considerado 6
     *
     * @param nombre Cadena a verificar si cumple los requisitos
     * @return Devolveremos true si es válido, false en caso contrario
     */
    public static boolean validaUsuario(String nombre) {

        Pattern p = Pattern.compile("[a-z0-9]{4,10}");
        Matcher m = p.matcher(nombre);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }//fin de validaUsuario

    /**
     * Método para validar la contraseña Debe de contener siempre el valor "x"
     *
     * @param pass Cadena a verificar los requisitos
     * @return Devolveremos true si es válido, false en caso contrario
     */
    public static boolean validaContrasenia(String pass) {

        Pattern p = Pattern.compile("x");
        Matcher m = p.matcher(pass);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }//fin de ValidaContrasenia

    /**
     * Método para validar el Id de usuario y grupo Debe de ser un número
     * positivo comprendido entre 0 y 2000
     *
     * @param id Cadena a verificar los requisitos
     * @return Devolveremos true si es válido, false en caso contrario
     */
    public static boolean validaId(String id) {
        try {
            int numero = Integer.parseInt(id);
            if (numero >= 0 && numero <= 2000) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException excepcion) {
            return false;
        }
    }//fin de validaId

    /**
     * Método para validar la descripción del usuario Puede contener letras
     * mínusculas, mayúsculas, números y algún caracter especial Podríamos
     * dejarla sin contenido
     * NOTA IMPORTANTE: Los caracteres que indico a continuación ñÑáéíóúÁÉÍÓÚ no funcionan y no sé el por qué. El resto sí.
     * He utilizado testeadores de expresiones regulares indicandome que la expresión regular está bien formada
     *
     * @param descripcion Cadena a verificar los requisitos
     * @return Devolveremos true si es válido, false en caso contrario
     */
    public static boolean validaDescripcion(String descripcion) {

        Pattern p = Pattern.compile("[ 0-9a-zA-ZñÑáéíóúÁÉÍÓÚ\\/\\-\\(\\)\\,\\.]*");
        Matcher m = p.matcher(descripcion);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }//fin de validaDescripcion

    /**
     * Método para validar el directorio de un usuario
     *
     * @param directorio Cadena a validar
     * @return Devolveremos true si es válido, false en caso contrario
     */
    public static boolean validaDirectorio(String directorio, String usuario) {

        if (!usuario.equals("root") && directorio.equals("/home/" + usuario)) {
            return true;
        } else if (usuario.equals("root") && directorio.equals("/root")) {
            return true;
        } else {
            return false;
        }
    }//fin de validaDirectorio

    /**
     * Método para validar la shell de un usuario
     *
     * @param cascaron Cadena a validar
     * @return Devolveremos true si es válido, false en caso contrario
     */
    public static boolean validaCascaron(String cascaron) {

        Pattern p = Pattern.compile("(/[a-z]+){2,}");
        Matcher m = p.matcher(cascaron);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }//fin de validaCascaron

    
    //Para testear la clase
    /*
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("Introduzca una cadena");
        try {
            String cadena = br.readLine();
            if (validaCascaron(cadena)) {
                System.out.println("Es válido");
            } else {
                System.out.println("No es válido");
            }
        } catch (IOException ex) {
            Logger.getLogger(ValidadorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

}//Fin de la clase


