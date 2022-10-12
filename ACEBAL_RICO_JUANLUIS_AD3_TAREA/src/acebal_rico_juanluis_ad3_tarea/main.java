package acebal_rico_juanluis_ad3_tarea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Juan Luis Acebal Rico
 */
public class main {
    public static void main(String[] args) throws ClassNotFoundException {
        /* me da SQLExceptio: Unable to load authentication plugin 'caching_sha2_password'
            entonces lo soluciono en mysql:
            ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '1234';
            flush privileges;
            Info aquí:
            https://es.stackoverflow.com/questions/326205/error-en-conexi%C3%B3n-mysql-y-java-unable-to-load-authentication-plugin-caching-sh
            */
            
            /*Otro error que me daba después de el anterior que lo he solucionado bajandome otro driver:
            https://es.stackoverflow.com/questions/155835/porque-es-el-error-java-math-biginteger-cannot-be-cast-to-java-lang-long
            */
        //1.CREAR CONEXION con un pool de conexiones:
            
        String bd="jdbc:mysql://localhost:3306/elearning?useSSL=false";
        Connection miConexion;
        try {
            miConexion = DriverManager.getConnection(bd,"root","root");
            System.out.println("\nPARTE 1--");
            dameInfo a=new dameInfo(miConexion);
            for (int i=0;i<90;i++) System.out.print("*");
            System.out.println("\nPARTE 2--");
            damePasajeros b=new damePasajeros(miConexion);
            for (int i=0;i<40;i++) System.out.print("*");
            dameVuelos c=new dameVuelos(miConexion);
            for (int i=0;i<90;i++) System.out.print("*");
            System.out.println("\nPARTE 3--");
            damePasajerosDelVuelo d=new damePasajerosDelVuelo(miConexion, "AI-1289-9");
            for (int i=0;i<90;i++) System.out.print("*");
            System.out.println("\nPARTE 4--");
            InsertaPasajero e=new InsertaPasajero( miConexion, 146 , "AI-1289-9","TU","SI" );
            for (int i=0;i<90;i++) System.out.print("*");
            System.out.println("\nPARTE 5--");
            //Aqui se puede poner o bien numero de pasajero, o la suma de campos de clave primaria, 
            //es decir, borrar todos los campos para un pasajero
            //o borrar un vuelo de un pasajero, como no sabía cual era de las 2 
            //mejor hecho para el ejercicio, lo he hecho de las dos formas
            //si está en null, borra todos los vuelos de ese pasajero, si está con un número de vuelo, solo esa linea:
            BorraPasajero f=new BorraPasajero( miConexion, 146 , null ); 
            for (int i=0;i<90;i++) System.out.print("*");
            System.out.println("\nPARTE 6--");
            actualizaPlazas g=new actualizaPlazas(miConexion, "AI-1289-9");
            for (int i=0;i<90;i++) System.out.print("*");
            System.out.println("\nPARTE 7--");
            procedenciaVuelosPasajeros h= new procedenciaVuelosPasajeros(miConexion);
            for (int i=0;i<90;i++) System.out.print("*");
            System.out.println("\nPARTE 8--");
            vueloMasLLeno i=new vueloMasLLeno(miConexion);
            miConexion.close();
            
            
        } catch (SQLException e) {
            System.out.println("NO HA CONECTADO!!");
            e.printStackTrace();
        }
         
        
            
          
        
        
    }
    
}

class dameInfo {
    dameInfo(Connection miConexion) throws ClassNotFoundException{
        try {
            //2.CREAR STATEMENT    
            Statement miStatement= miConexion.createStatement();
            //3.EJECUTAR SQL
            ResultSet miResultSet=miStatement.executeQuery("SHOW TABLES;");
            while(miResultSet.next()){
                System.out.println(miResultSet.getString(1));
            }
        } catch (SQLException e) {
            Logger.getLogger(dameInfo.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("ERROR!!");
            e.printStackTrace();
        }

}
}

class damePasajeros {
    damePasajeros(Connection miConexion) throws ClassNotFoundException{
        try {
            //2.CREAR STATEMENT    
            Statement miStatement= miConexion.createStatement();
            //3.EJECUTAR SQL
            ResultSet miResultSet;
            miResultSet=miStatement.executeQuery("select * from pasajeros; ");
            System.out.println("\nNUM COD_VUELO TIPO_PLAZA FUMADOR");
            while(miResultSet.next()){
                System.out.println(miResultSet.getString(1)+" "+miResultSet.getString(2)+" "+ miResultSet.getString(3)+" " + miResultSet.getString(4));
            }
        } catch (SQLException e) {
            Logger.getLogger(dameInfo.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("ERROR!!");
            e.printStackTrace();
        }

}
}

class dameVuelos {
    dameVuelos(Connection miConexion) throws ClassNotFoundException{
        try {
            //2.CREAR STATEMENT    
            Statement miStatement= miConexion.createStatement();
            //3.EJECUTAR SQL
            ResultSet miResultSet=miStatement.executeQuery("select * from vuelos; ");
        //    System.out.println(miResultSet.getNString(1));
            System.out.println("\nCOD_VUELO HORA_SALIDA DESTINO PROCEDENCIA PLAZAS_FUMADOR PLAZAS_NO_FUMADOR PLAZAS_TURISTA PLAZAS PRIMERA");
            while(miResultSet.next()){
                System.out.println(miResultSet.getString(1)+" "+miResultSet.getString(2)+" "+ miResultSet.getString(3)+" " + miResultSet.getString(4)
                +" "+ miResultSet.getString(5)+" " + miResultSet.getString(6)
                +" " + miResultSet.getString(7)+" " + miResultSet.getString(8)
                );
            }
        } catch (SQLException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }

}
}


class damePasajerosDelVuelo {
    damePasajerosDelVuelo(Connection miConexion, String vuelo) throws ClassNotFoundException{
        try {
            //2.CREAR STATEMENT    
            Statement miStatement= miConexion.createStatement();
            //3.EJECUTAR SQL
            ResultSet miResultSet;
            miResultSet=miStatement.executeQuery("SELECT * from pasajeros where pasajeros.COD_VUELO=\'"+vuelo+"\'");
            System.out.println("\nNUM COD_VUELO TIPO_PLAZA FUMADOR");
            while(miResultSet.next()){
                System.out.println(miResultSet.getString(1)+" "+miResultSet.getString(2)+" "+ miResultSet.getString(3)+" " + miResultSet.getString(4));
            } 
        } catch (SQLException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }

}
}



class InsertaPasajero {
    InsertaPasajero(Connection miConexion, int num, String cod_vuelo,String tipo_plaza,String fumador) throws ClassNotFoundException{
        try {
            //2.CREAR -PREPARED- STATEMENT    
            PreparedStatement miConsultaPreparada=miConexion.prepareStatement("INSERT INTO PASAJEROS VALUES (?,?,?,?)");
            miConsultaPreparada.setInt(1, num);
            miConsultaPreparada.setString(2, cod_vuelo);
            miConsultaPreparada.setString(3, tipo_plaza);
            miConsultaPreparada.setString(4, fumador);
            //3.EJECUTAR SQL
            int a=miConsultaPreparada.executeUpdate();
            System.out.println("ARROW:"+ a);
        } catch (SQLException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }

}
}




class BorraPasajero {
    BorraPasajero(Connection miConexion, int num, String cod_vuelo) throws ClassNotFoundException{
        try {
            //2.CREAR -PREPARED- STATEMENT 
            PreparedStatement miConsultaPreparada;
            if (cod_vuelo!=null){
                 miConsultaPreparada=miConexion.prepareStatement("DELETE FROM PASAJEROS WHERE NUM=? AND COD_VUELO=?;");
                miConsultaPreparada.setInt(1, num);
                miConsultaPreparada.setString(2, cod_vuelo);
            }
            else{
            miConsultaPreparada=miConexion.prepareStatement("DELETE FROM PASAJEROS WHERE NUM=?;");
            miConsultaPreparada.setInt(1, num);
            }
            //3.EJECUTAR SQL
            int a=miConsultaPreparada.executeUpdate();
            System.out.println("ARROW:"+ a);
        } catch (SQLException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }

}
}


class actualizaPlazas {
    actualizaPlazas(Connection miConexion, String cod_vuelo) throws ClassNotFoundException{
        try {
            //2.CREAR -PREPARED- STATEMENT    
            PreparedStatement miConsultaPreparada=miConexion.prepareStatement
        ("UPDATE VUELOS SET PLAZAS_NO_FUMADOR=PLAZAS_NO_FUMADOR+PLAZAS_FUMADOR, PLAZAS_FUMADOR=0 where COD_VUELO=?");
            miConsultaPreparada.setString(1, cod_vuelo);
            //3.EJECUTAR SQL
            int a=miConsultaPreparada.executeUpdate();
            System.out.println("ARROW:"+ a);
        } catch (SQLException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }

}
}


class procedenciaVuelosPasajeros {
    procedenciaVuelosPasajeros(Connection miConexion) throws ClassNotFoundException{
        try {
            //Aquí me he liado un poco pero al final lo he conseguido. ha sido
            //una consulta muy larga
            //estoy casi seguro que mas corto podía ser, pero lo he conseguido hacer
            
            String largaSQL="SELECT PROCEDENCIA AS 'ORIGEN', \n" +
                        "COUNT(*) AS 'VUELOS RUTA'\n" +
", SUM((\n" +
"SELECT COUNT(NUM) \n" +
"FROM pasajeros \n" +
"WHERE pasajeros.COD_VUELO=vuelos.cod_vuelo \n" +
"group by procedencia \n" +
")) AS 'PASAJEROS'\n" +
"FROM  vuelos where  vuelos.procedencia IN \n" +
"(SELECT PROCEDENCIA )\n" +
"group by  vuelos.PROCEDENCIA;";
            //2.CREAR STATEMENT    
            Statement miStatement= miConexion.createStatement();
            //3.EJECUTAR SQL
            ResultSet miResultSet;
            miResultSet=miStatement.executeQuery(largaSQL);
            System.out.println("\nNUM CIUDAD VUELOS PASAJEROS");
            String proc, vuel, pasa;
            while(miResultSet.next()){
                pasa=miResultSet.getString(3);
                if (pasa==null) pasa="0";               
                System.out.println(miResultSet.getString(1)+" "+miResultSet.getString(2)+" "+pasa);
            } 
        } catch (SQLException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }

}
}



class vueloMasLLeno {
    vueloMasLLeno(Connection miConexion) throws ClassNotFoundException{
        try {
            //Aquí me he liado un poco pero al final lo he conseguido. ha sido
            //una consulta muy larga
            //estoy casi seguro que mas corto podía ser, pero lo he conseguido hacer
            
            String sentencia="SELECT vuelos.COD_VUELO,vuelos.HORA_SALIDA,vuelos.PROCEDENCIA, "
                    + "vuelos.DESTINO, COUNT(NUM) AS 'NºPASAJEROS'\n" +
                    "FROM pasajeros , vuelos\n" +
                    "WHERE pasajeros.COD_VUELO=vuelos.cod_vuelo \n" +
                    "group by vuelos.COD_VUELO ORDER BY COUNT(NUM) DESC LIMIT 1;";
            //2.CREAR STATEMENT    
            Statement miStatement= miConexion.createStatement();
            //3.EJECUTAR SQL
            ResultSet miResultSet;
            miResultSet=miStatement.executeQuery(sentencia);
            System.out.println("\nCOD_VUELO HORA_SALIDA PROCEDENCIA DESTINO NºPASAJEROS");
            while(miResultSet.next()){             
                System.out.println(miResultSet.getString(1)+" "+miResultSet.getString(2)
                +" "+miResultSet.getString(3)+" "+miResultSet.getString(4)+" "+miResultSet.getString(5));
            } 
        } catch (SQLException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }

}
}




