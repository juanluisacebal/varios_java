package acebal_rico_juanluis_ad2_tarea;

import java.io.IOException;
import org.xml.sax.*;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
/**
 *
 * @author Juan Luis Acebal Rico
 */
public class Main {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException  {
        Apartado2ParteBImpl c;
        Apartado2ParteAImpl b;
        Apartado1Impl a;
        b= new Apartado2ParteAImpl();
        Scanner apartado;
        do{
        System.out.println();
        for (int n=0;n<60;n++) System.out.print("*");
        System.out.println("\nElige:\n a.Apartado 1 \n b.Apartado 2.DOM\n c.Apartado2.SAX\n d.SALIR");
        for (int n=0;n<60;n++) System.out.print("*");
        apartado= new Scanner (System.in);
        //System.out.print(apartado.nextLine());
        if (apartado.nextLine().equals("a")){
            System.out.printf("Apartado 1:");
            a= new Apartado1Impl();
        }
        else if (apartado.nextLine().equals("b")){
            System.out.printf("Apartado 2, PARTE A:\n");
            b= new Apartado2ParteAImpl();
        }
        else if (apartado.nextLine().equals("c")){
            System.out.printf("Apartado 2, PARTE B:\n");
            c= new Apartado2ParteBImpl();
        }
        } while( !apartado.nextLine().equals("d"));   
        
            
        
        
        
    }
}
    