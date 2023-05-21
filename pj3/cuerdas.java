import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class cuerdas{
    public static void cuerda(String nameA, String extA, String Nname, String Next) throws Exception{
        String filename = nameA+extA; // leer el nombre del archivo y su extension 
        String NewFileName = Nname+Next; // el archivio que vamos a crear con los resultados de cuerdas aceptadas o rechazadas 
        String Line; // leer la linea 
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){ //intentar abrir el archvio 
            BufferedWriter bw = new BufferedWriter(new FileWriter(NewFileName)); // archivo para poder escribir 
            while((Line = br.readLine())!=null){// leer la linea hasta que termine/linea vacia 
                if( "la funcion del proyecto" == "aceptada"){
                        String aceptada = "aceptada";
                        bw.write(aceptada);
                }else if( "la funcion del proyecto" == "rechazada"){
                    String rechazada = "rechazada";
                    bw.write(rechazada); 
                }
            }
        } catch (IOException e){ 
            System.out.println("el archivo no se pudo crear correctamente vuelva a intentar");
        }
    }
} 