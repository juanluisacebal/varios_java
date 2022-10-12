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
      //   Element miElemento;
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
    /*private static String dameNodo(String etiqueta, Node element) {
        Element elemen= (Element) element;
        NodeList nodo= elemen.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node valornodo = (Node) nodo.item(0);
        return valornodo.getNodeValue(); 
    }*/
}
       

    
         
         
         
         
         
         
         
         
         
         
         /*
         
         
         File inputFile = new File("libros.xml");
         DocumentBuilderFactory miFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder miDocumentBuilder = miFactory.newDocumentBuilder();
         Document doc = miDocumentBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.print(doc.getDocumentElement().getNodeName()+ ":\n"); 
         NodeList miNodoLista = doc.getElementsByTagName("libro");
         Element miElemento;
         for (int i = 0; i < miNodoLista.getLength(); i++) {
            System.out.println("i: "+ i);
            Node miNodo = miNodoLista.item(i);
            if (miNodo.getNodeType() == Node.ELEMENT_NODE) {
                //miNodeLista= miElemento.getElementsByTagName("titulo").item(i).getChildNodes();
 
                miElemento= (Element) miNodo;
               // miNodo=(Node) miElemento.getElementsByTagName("titulo");
          //     miNodoLista= miElemento.getElementsByTagName("titulo").item(i).getChildNodes();
                  //     Node miNodo2 = (Node) miNodoLista.item(i);
                        Node miNodo2= miElemento.getElementsByTagName("titulo").item(i);
                        miNodo2 = (Node) miNodoLista.item(i);     
                System.out.print("\nLIBRO:\nTITULO: "+ miNodo2.getTextContent());
//.getNodeValue());//.getTextContent());
                miNodo2= miNodo.getAttributes().getNamedItem("año");
                System.out.print("\nAÑO: "+miElemento.getAttribute("año"));//miElemento.getAttribute("año"));
            
                System.out.print("\nAUTORES:");
         /*       NodeList miListaAutores= miElemento.getElementsByTagName("autor");
                for (int k=0; k <miListaAutores.getLength(); k++){
                    System.out.println("k: "+ k);
                    Node miNodoAutores = miListaAutores.item(k);
                    if(miNodoAutores.getNodeType() == Node.ELEMENT_NODE){
                        miElemento = (Element) miNodoAutores;
                        NodeList nodoListaHijosAutores= miElemento.getElementsByTagName("apellido").item(k).getChildNodes();
                        Node miNodoAutoresHijos = (Node) nodoListaHijosAutores.item(k);
                        System.out.print("APELLIDO: "+ miNodoAutoresHijos.getTextContent());
                        nodoListaHijosAutores= miElemento.getElementsByTagName("nombre").item(k).getChildNodes();
                        miNodoAutoresHijos = (Node) nodoListaHijosAutores.item(k);
                        System.out.println("NOMBRE: "+ miNodoAutoresHijos.getTextContent());
                    }
                }
                        // miNodo= miElemento.getElementsByTagName("editorial").item(0);
                        // miNodo = (Node) miNodeLista.item(0);
                        miNodo= miElemento.getElementsByTagName("editorial").item(i).getChildNodes();
                        miNodo2 = (Node) miNodo.item(i);
                System.out.print("\nEDITORIAL: "+ miNodo2.getTextContent());
                  //      miElemento = (Element) miNodo;
                  miNodo2= miElemento.getElementsByTagName("precio").item(i).getChildNodes();
                        miNodo2 = (Node) miNodo2.item(i);
                        System.out.print("\nPRECIO: "+ miNodo2.getTextContent());
                        
                        
                      //  System.out.println("EDITORIAL: "+ miNodo.getTextContent());
                    //    miNodo= (Node) miElemento.getElementsByTagName("precio").item(0);
                       // miNodo = (Node) miNodeLista.item(i);
                    //    System.out.println("PRECIO: "+ miNodo.getTextContent());
                        System.out.println();
                //}
            }
            }
     }}
        */