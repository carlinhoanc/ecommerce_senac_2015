
package ecommerce.util;
import java.util.Calendar;
/**
 *
 * @author Gustavo
 */
public class Protocolo {
     
    /**
     * Esse método retorna o número de milisegundos transcorridos desde o momento que foi chamado até 1/1/1970. 
     * @return 
     */
    public static String getNumeroProtocolo() {
//        Calendar cal = Calendar.getInstance();
//        int h = cal.get(Calendar.HOUR_OF_DAY);
//        int m = cal.get(Calendar.MINUTE);
//        int s = cal.get(Calendar.SECOND);    
//        int seconds = (h * 60 + m) * 60 + s;        
        return String.valueOf(System.currentTimeMillis());
    }

}
