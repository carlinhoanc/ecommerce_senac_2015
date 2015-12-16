
package ecommerce.util;
/**
 *
 * @author Gustavo
 */
public class Protocolo {
     
    /**
     * Esse método retorna o número de milisegundos transcorridos desde o momento que foi chamado até 1/1/1970. 
     * @return retorna uma string
     */
    public static String getNumeroProtocolo() {
        return String.valueOf(System.currentTimeMillis());
    }

}
