import java.util.*;
import java.io.*;
import java.io.FileInputStream;
public class Gramatica{
	static BufferedReader obj;
	static char[] alfabeto;
	static String symbols;
	static String[] gramatica;
	File doc;
	public static void main(String[] args) throws Exception{
		if(args.length>1){
			String Fpath = args[0]; //  posicon del nombre del archvio 
			String bandera = args[1]; // posicion de la bandera afn,afd,check 
			//String FNameExit = args[2]; // nombre archvo salida 
			//String banderaCheck = args[3]; //Check 
			//String path = Fpath.substring(0, Fpath.length()-4); // nombre del archvo sin extension 
			//String extensionPath = path.substring(path.length()-4, path.length());//extension del primer archivo .gld
			//String NameExit = FNameExit.substring(0, FNameExit.length()-4);// nombre del archvio de salida sin la extension 
			//String extensionExit = NameExit.substring(NameExit.length()-4, NameExit.length());//extension del archivo de salida .Afn o .Afd 
			 
			if(bandera.equals("-afd")){
				//afd();
				Factorizar prueba = new Factorizar(Fpath);
				
			}else if(bandera.equals("-afn")){
				//afn();
				System.out.println();
			}else if(bandera.equals("-check")){
				//cuerdas();
				System.out.println();
			}else{
				System.out.println("no ingreso el nombre del archivo correctamente o no existe");
			}

		}else{
			System.out.println("el archvio no se complilo correctamente, vuelva a probarlo");

		}
	}	
}