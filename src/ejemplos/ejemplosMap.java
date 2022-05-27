package ejemplos;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/**
 * Ejemplo para ver el funcionamiento de la estructura de datos Map
 * @author  David Jiménez Riscardo
 * @version 1.0
 */
public class ejemplosMap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Con una estructura TreeMap conseguiremos que el mapa se ordene de forma natural
        //En la práctica indexaré los campos por el nombre de usuario, de esta forma evitaremos tener más de una entrada con el mismo usuario
        //Quedará guardada la última entrada válida del usuario
        Map<Integer, String> map = new TreeMap<Integer, String>();
        map.put(1, "Casillas");		
        map.put(15, "Ramos");
        map.put(3, "Pique");		
        map.put(5, "Puyol");
        map.put(11, "Capdevila");	
        map.put(14, "Xabi Alonso");
        map.put(16, "Busquets");	
        map.put(8, "Xavi Hernandez");
        map.put(18, "Pedrito");		
        map.put(6, "Iniesta");
        map.put(7, "Villa");       
    
    
        for(Integer clave : map.keySet()) {
            String valor = map.get(clave);
            System.out.println("Clave: " + clave + ", valor: " + valor);
        }
    
    }
}
