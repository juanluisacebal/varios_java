package acebal_rico_juanluis_ad2_tarea;
import java.io.*;

import org.w3c.dom.*;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 *
 * @author Juan Luis Acebal Rico
 * 
 * Lo comentado son pruebas que hago que en vez de borrar, mantengo
 * para poder consultarlo como referencia y consulta propia en el futuro.
 */
public class Apartado1Impl {
      Apartado1Impl() throws FileNotFoundException, IOException  {
   // public static void main(String[] args) throws Exception {
        
        //Aqui primero he hecho un array de empleados, luego lo he modificado a
        //arraylist para que valga para 5 o para 100
        //ARRAY DE EMPLEADOS:
        //Empleados misempleados[] = new Empleados[5];
        ArrayList <Empleados> misempleados=new ArrayList <Empleados>();
        misempleados.add(new Empleados(1, "Juan      ","Albacete  ",  11000.50F, 0.1f));
        misempleados.add(new Empleados(2, "Pepe","Bilbao",  12000.50F, 0.2f));
        misempleados.add(new Empleados(3, "Luis","Jerez",  13000.50F, 0.3f));
     /*   misempleados.add(new Empleados(4, "Jorge","Villarrobledo",  14000.50F, 0.4f));
        misempleados.add(new Empleados(5, "Juanito","La Roda",  15000.50F, 0.5f));
        misempleados.add(new Empleados(6, "Alfonso","Aguas Nuevas",  16000.50F, 0.6f));
        misempleados.add(new Empleados(7, "Simon","La Felipa",  17000.50F, 0.7f));
        misempleados.add(new Empleados(8, "Eduardo","Chinchilla",  18000.50F, 0.8f));
        */
        System.out.println("Empleados a escribir en EMPLEADOS.DAT:");
        for (Empleados e: misempleados){
            System.out.println(e.codigo+e.nombre+e.direccion+e.salario+e.comision);
        }
       RandomAccessFile archivo=null; 
       for(Empleados e: misempleados)
       {
           archivo= new RandomAccessFile("EMPLEADOS.DAT", "rw");
           String linea= e.codigo+";"+
                        e.nombre+";"+
                        e.direccion+";"+
                        e.salario+";"+
                        e.comision+";\n";
           
           archivo.seek(archivo.length());
           archivo.writeBytes(linea);
           /*archivo.writeInt(e.codigo);
           StringBuffer buffer = null;
           int k= e.nombre.length();
           buffer = new StringBuffer( e.nombre );      
           buffer.setLength(20); 
           archivo.writeChars(buffer.toString());
           archivo.writeFloat(e.salario);
           archivo.writeChars(e.direccion);
           archivo.writeFloat(e.comision);*/
           archivo.close();
       }
       //System.out.println("PARTE B: ");
       try{
       DocumentBuilderFactory mifactory= DocumentBuilderFactory.newInstance();
       DocumentBuilder mibuilder= mifactory.newDocumentBuilder();
       Document miDoc = mibuilder.newDocument();
       Element empleados = miDoc.createElement("empleados");
       miDoc.appendChild(empleados);
       String elcodigo="", elnombre="", ladireccion="";
       String elsalario="", lacomision="";
       char letra;
       String frase;
       //  char frase[]=new char[20];
       //   String frases=new String (frase);
       //String prueba=null;
       //  Empleados elempleado= null;
       // File file=new File("empleados.dat");
       String linea="";
       archivo= new RandomAccessFile("empleados.dat", "r");
       int separador=0;
       int pos=0;
       archivo.seek(0);
       while (archivo.length()>archivo.getFilePointer()) 
       {
            //System.out.println(archivo.length()+ "  "+archivo.getFilePointer());
            linea=archivo.readLine();
            //System.out.println(archivo.length()+ "  "+archivo.getFilePointer()+ " LINEA: "+ linea.length());
            //System.out.println(linea);
            separador=0;
            frase="";
            for (int f=0; f<linea.length(); f++){
                //System.out.println(f);
                //if (f==0)
                    //System.out.println("El tamaño de linea es: " + linea.length());
                letra=linea.charAt(f);
                //System.out.print("L:"+ linea.charAt(f)+ " ");
                if (letra!=';'){
                    frase=frase+letra;
                }
                if (letra==';'){
                    separador++;
                    if (separador==1){ 
                        elcodigo=frase;
                        //System.out.print("Separador=1 ");
                        //System.out.println(elcodigo + " "+ frase);
                        frase="";
                    }
                    if (separador==2)  
                    { 
                        elnombre=frase;
                        //System.out.print("Separador=2 ");
                        //System.out.println(elnombre + " "+ frase);
                        frase="";
                    }
                    if (separador==3)  { 
                        ladireccion=frase;
                        //System.out.print("Separador=3 ");
                        //System.out.println(ladireccion + " "+ frase);
                        frase="";        
                    }
                    if (separador==4)  { 
                        elsalario=frase;               
                        //System.out.print("Separador=4 ");
                        //System.out.println(elsalario + " "+ frase);
                        frase="";
                    }
                    if (separador==5)  { 
                        lacomision=frase;
                        //System.out.print("Separador=5 ");
                        //System.out.println(lacomision + " "+ frase);
                        frase="";
                    }
                }
            }
            //System.out.println(elcodigo);
            //System.out.println(elnombre);
            //System.out.println(ladireccion);
            //System.out.println(elsalario);
            //System.out.println(lacomision);
            // elempleado=new Empleados(elcodigo,elnombre,ladireccion,elsalario,lacomision);
            Element empleado = miDoc.createElement("empleado"); 
            Element nombre = miDoc.createElement("nombre");
            Element direccion = miDoc.createElement("direccion");
            Element salario = miDoc.createElement("salario");
            Element comision = miDoc.createElement("comision");
            Attr miatributo = miDoc.createAttribute("codigo");
            miatributo.setValue(elcodigo);//elempleado.codigo+""+z);  //kkk
            empleado.setAttributeNode(miatributo);
            nombre.setTextContent(elnombre);//elempleado.nombre+z);//kkkkkk
            direccion.setTextContent(ladireccion);//elempleado.direccion+z);//kkkkk
            salario.setTextContent(elsalario);//elempleado.salario+""+z);  //kkk
            comision.setTextContent(lacomision);//elempleado.comision+""+z);  //kkkkkkk
            empleado.appendChild(nombre);
            empleado.appendChild(direccion);
            empleado.appendChild(salario);
            empleado.appendChild(comision); 
            empleados.appendChild(empleado);
            if (archivo.getFilePointer()==archivo.length()) {
                archivo.close();
                break;
            }
        }
        //TransformerFactory transformerFactory = TransformerFactory.newInstance();
        //Transformer transformer = transformerFactory.newTransformer();
        Transformer miTransformer = TransformerFactory.newInstance().newTransformer();
        miTransformer.setOutputProperty(OutputKeys.INDENT, "YES");
        miTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource miSource = new DOMSource(miDoc);
        StreamResult miResult = new StreamResult(new File("empleados.xml"));
        System.out.println("Empleados YA escritos en empleados.xml desde empleados.dat");
        miTransformer.transform(miSource, miResult);
        } 
    catch (Exception e) {
        e.printStackTrace();
    }    
}

class Elementos {
       void Elementos(String tipo, String valor, Element elementosuperior, Document documento){
           Element mielemmento = documento.createElement(tipo); // Creamos el nodo hijo
	   Text text = documento.createTextNode(valor); // Se le da el valor
	   elementosuperior.appendChild(mielemmento); // Pegamos el elemento hijo a la raiz
	   mielemmento.appendChild(text); // Pegamos el valor del nodo	 	
  //  return null;
    }
    Element empleado;
}
}


class Empleados{
    public void setCodigo(int codigo) {this.codigo = codigo;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setDireccion(String direccion) {this.direccion = direccion;}
    public void setSalario(float salario) {this.salario = salario;}
    public void setComision(float comision) {this.comision = comision;}
    public int getCodigo() {return codigo;}
    public String getNombre() {return nombre;}
    public String getDireccion() {return direccion;}
    public float getSalario() {return salario;}
    public float getComision() {return comision;}
    public Empleados(int codigo, String nombre, String direccion, float salario, float comision) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.salario = salario;
        this.comision = comision;}
    int codigo;
    String nombre;
    String direccion;
    float salario;
    float comision;
}


