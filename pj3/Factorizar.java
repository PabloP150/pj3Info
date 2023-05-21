import java.util.*;
import java.io.*;
import java.io.FileInputStream;
public class Factorizar{

    static BufferedReader obj;
	static char[] alfabeto;
	static String[] symbols;
	static String[] gramatica;
	static File doc;
    static String band;
	static String simInicial;

    public Factorizar(String path) throws Exception {
		doc = new File(path);
		obj = new BufferedReader(new FileReader(doc));
		String symbols1 = obj.readLine().replaceAll(",", "");//guarda los simbolos del archivo
		symbols = new String[symbols1.length()];
		symbols = symbols1.split("");
		

		String alfabeto1 = obj.readLine().replaceAll(",", "");//guarda el alfabeto sin las comas
		simInicial = obj.readLine();//guarda el simbolo inicial 
		alfabeto = new char[alfabeto1.length()];//se inicializa el size del arreglo de chars con el length del alfabeto
		alfabeto = alfabeto1.toCharArray();// se guarda el alfabeto en un arreglo de char
		int cantLines = cantLineas(path);//obtiene la cantidad de lineas que hay en el archivo leido 
		gramatica = new String[cantLines-3];//se crea el size del arreglo para la gramatica
		//obj.mark(); // se marca el punto anterior a la primera lectura de reglas de produccion
        
		int cont = 0;
		//se guardan las lineas de produccion en un arreglo sin el -> 
		while (cont <= (cantLines-4)){
			gramatica[cont] = obj.readLine().replaceAll("->", " ");
            cont++;
		}

		//obj.reset();// se regresa al punto marcado del archivo
		// en este for se reescribe el arreglo para poder eliminar el ->
		List<String> prelista = new ArrayList<>(Arrays.asList(gramatica));
		List<String> listaGram = new ArrayList<>(prelista);  		
		
		List<String> lista = new ArrayList<>(Arrays.asList(symbols));
		List<String> listaVar = new ArrayList<>(lista);
		List<String> listaFin = new ArrayList<String>();
		int p = 0;
		for(int i = 0; i < listaGram.size();i++){
			
			String cuerda = listaGram.get(i);
			boolean validacion = validar(cuerda); 
			if ((validacion != true)&&(cuerda.length()>4)){
				String Var = Character.toString(cuerda.charAt(0));
				String restante = cuerda.substring(3,5);
				String Nuevo = Var + Integer.toString(p);
				p+=1;
				if(i == (listaGram.size()-1)){
					listaGram.set(i,cuerda.charAt(0)+" "+cuerda.charAt(2)+Nuevo);
					listaGram.add(Nuevo + " " + restante);

				}else if (i < (listaGram.size()-1)){
					listaGram.set(i,cuerda.charAt(0)+" "+cuerda.charAt(2)+Nuevo);
					listaGram.add(i+1, Nuevo + " " + restante);
				}
				listaVar.add(Nuevo);
				i++;
				
			}else if((validacion != true)&&(cuerda.length()==4)){
                
                if (i == (listaGram.size()-1)){
                    String Var = Character.toString(cuerda.charAt(0));
				    String restante = Character.toString(cuerda.charAt(3));
				    String Nuevo = Var + Integer.toString(i);
				    listaGram.set(i,cuerda.charAt(0)+" "+cuerda.charAt(2)+Nuevo);
                    listaGram.add(Nuevo + " " + restante);
				    listaVar.add(Nuevo); 

					
                }else if(i < listaGram.size()){
                    String Var = Character.toString(cuerda.charAt(0));
				    String restante = Character.toString(cuerda.charAt(3));
				    String Nuevo = Var + Integer.toString(i);
				    listaGram.set(i,cuerda.charAt(0)+" "+cuerda.charAt(2)+Nuevo);
                    listaGram.set(i+1, Nuevo + " " + restante);
				    listaVar.add(Nuevo);
					
                }
				i+=1;
			}		
		}
		//se agregan estados finales
		for(int j = 0; j<listaGram.size();j++){
			String tex = listaGram.get(j);
			for(char c : alfabeto){
				if(c == tex.charAt(tex.length()-1)){
					String Var = Character.toString(tex.charAt(0));
					String nuevo = Var + "f";
					tex+=nuevo; 
					
					listaGram.set(j,tex);
					if (!(listaVar.contains(nuevo))){
						listaVar.add(nuevo);
						listaFin.add(nuevo);
					}
					break;
				}
			}
		}
        System.out.println(listaGram + " Gramatica Factorizada");
        System.out.println(listaVar + " ArrayList de variables");
		System.out.println(listaFin + " ArrayList de estados Finales");
}
    //metodo para saber la cantidad de lineas del archivo 
	public static int cantLineas(String Archivo)throws Exception {
		File arc = new File(Archivo);
		int lineas = 0;
		BufferedReader reader = new BufferedReader(new FileReader(arc));
		while (reader.readLine() != null) {
                lineas++;
            }
		return lineas;
	}
	 // esta funcion valida una posicion del alfabeto con la siguiente posicion 
 	public boolean validar(String prod){
		boolean bandera = true;  
		char anterior = '\0';
        if(prod.length()>4){
            for (int i = 0; i < prod.length();i++){
			    char actual = prod.charAt(i);
				//System.out.println(actual + " soy actual");
				//System.out.println(anterior + " soy anterior");

			    if((isAlfabeto(alfabeto, actual))&&(isAlfabeto(alfabeto, anterior))){
				    bandera = false;
				    break;	
			    }
			    anterior = actual; 
		    }
        }else if(prod.length()==4){
			
            for (int i = 0; i < prod.length();i++){
			    char actual = prod.charAt(i);
				//System.out.println(actual+" menor a 4");
			    if((isAlfabeto(alfabeto, actual))&&(isAlfabeto(alfabeto, anterior))){
				    bandera = false;
				    break;	
			    }
			    anterior = actual; 
		    }
        }
		return bandera;
	}

	public boolean isAlfabeto(char[] arreglo, char elemento){
		for(char c : arreglo){
			if(c == elemento){
				return true;
			}
		}
		return false;
	} 
}