
package ejercicio2;

/**
 * Clase para guardar la información de los usuarios
 * @author      David Jiménez Riscardo
 * @version     1.0
 */
public class Usuario {
    
    private String nombre;
    private String password;
    private int    id_usuario;
    private int    id_grupo; 
    private String descripcion;
    private String directorio;
    private String cascaron;
    
    /**
     * Método constructor
     * @param nombre        Nombre del usuario
     * @param password      Contraseña
     * @param id_usuario    Id del usuario
     * @param id_grupo      Id del grupo al que pertenece el usuario
     * @param descripcion   Descripción del usuario
     * @param directorio    Directorio de trabajo del usuario
     * @param cascaron      Shell del usuario 
     */
    public Usuario(String nombre, String password, int id_usuario, int id_grupo, String descripcion, String directorio, String cascaron){
        
        this.nombre = nombre;
        this.password = password;
        this.id_usuario = id_usuario;
        this.id_grupo = id_grupo;
        this.descripcion = descripcion;
        this.directorio = directorio;
        this.cascaron = cascaron;
        
    }

    /**
     * Método para construir una cadena con la información que nos interesa devolver
     * @return Cadena con la información del usuario a devolver
     */
    @Override
    public String toString() {
        return "id_usuario=" + this.id_usuario + ", id_grupo=" + this.id_grupo + ", descripcion=" + this.descripcion + ", directorio=" + this.directorio + ", cascaron=" + this.cascaron;
    }   
    
}//Fin de la clase Usuario
