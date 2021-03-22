package acebal_rico_juanluis_ad2_tarea;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author Juan Luis Acebal Rico
 */

//IMPLEMENTACION EN DOM
public class Apartado2ParteAImpl { 
     Apartado2ParteAImpl() throws SAXException, IOException, ParserConfigurationException {
         File inputFile = new File("libros.xml");
         DocumentBuilderFactory miFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder miDocumentBuilder = miFactory.newDocumentBuilder();
         Document doc = miDocumentBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.print ((doc.getDocumentElement().getNodeName()+ ":\n").toUpperCase()); 
         for (int c=0;c<80;c++) System.out.print("-");
         NodeList miNodoLista = doc.getElementsByTagName("libro");
            for(int i=0;i<miNodoLista.getLength();i++)
            {
                Node miNodo = miNodoLista.item(i);
                if(miNodo.getNodeType()==Node.ELEMENT_NODE) 
                {
                    Element miElemento = (Element)miNodo; 
                    System.out.println("\nTITULO: "+ dameNodo("titulo",miElemento, null));
                    System.out.println("AÑO: "+ miElemento.getAttribute("año"));
                    NodeList autores = miElemento.getElementsByTagName("autor");
                    for(int k=0;k<autores.getLength();k++)
                    {
                        Node autor = autores.item(k); //obtener un nodo
                        if(autor.getNodeType()==Node.ELEMENT_NODE) //tipo de nodo
                        {
                            System.out.println("AUTOR: APELLIDO: "+ dameNodo("apellido", null ,autor)+ " , NOMBRE: " +dameNodo("nombre", null,autor)  );
                        }
                    }
                    System.out.println("EDITORIAL: "+ dameNodo("editorial",miElemento, null));
                    System.out.println("PRECIO: "+ dameNodo("precio",miElemento, null));
                    for (int c=0;c<60;c++) System.out.print("-");
                }
            }
    }

    private static String dameNodo(String etiqueta, Element elelemento, Node elnodo) {
        if( elelemento==null){
            elelemento= (Element) elnodo;
        }
        NodeList esteNodo=elelemento.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node textoNodo = (Node) esteNodo.item(0);
        return textoNodo.getNodeValue();
    }
}