
package acebal_rico_juanluis_ad2_tarea;

import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.*;
/**
 *
 * @author Juan Luis Acebal Rico
 */
//IMPLEMENTACION EN SAX
public class Apartado2ParteBImpl { 
      Apartado2ParteBImpl()throws SAXException, IOException {
        XMLReader miLectorXML = XMLReaderFactory.createXMLReader();
        ElDefaultHandlerParaMiClase miHandler= new ElDefaultHandlerParaMiClase();
        miLectorXML.setContentHandler(miHandler);
        InputSource fileXML = new InputSource("libros.xml");
        miLectorXML.parse(fileXML);

    }
}
class ElDefaultHandlerParaMiClase extends DefaultHandler {
        public ElDefaultHandlerParaMiClase() {
            super();
        }
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes){

            System.out.print("-"+ localName.toUpperCase()+": ");
            if (attributes.getValue("año") !=null) 
            System.out.print("\n-AÑO: "+attributes.getValue("año"));
            if(localName.equals("libros") || localName.equals("libro") || localName.equals("año" )   || localName.equals("autor" ) )
                System.out.println("");
        }    
        
        @Override
        public void characters(char[] caracter, int inicio, int posicion){
            String elemento= new String(caracter,inicio,posicion);
            elemento=elemento.replaceAll("\n", "");
            elemento=elemento.replaceAll("\t", "");
            if (  "".equals(elemento) );
            else 
                System.out.println ( elemento+ " ");
        }
    }
