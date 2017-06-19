package concurso1Basico;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


class TypeRacerCall{

	
	public int[] indices(int tecla){
		int[] ind=new int[2];
		if (tecla==1){
			ind[0]=0;
			ind[1]=0;
			return ind;
		}else if(tecla==2){
			ind[0]=0;
		    ind[1]=1;
		    return ind;
	    }
	else if(tecla==3){
		ind[0]=0;
	    ind[1]=2;
	    return ind;
    }else if(tecla==4){
		ind[0]=1;
	    ind[1]=0;
	    return ind;
    }else if(tecla==5){
		ind[0]=1;
	    ind[1]=1;
	    return ind;
    }else if(tecla==6){
		ind[0]=1;
	    ind[1]=2;
	    return ind;
    }else if(tecla==7){
		ind[0]=2;
	    ind[1]=0;
	    return ind;
    }else if(tecla==8){
		ind[0]=2;
	    ind[1]=1;
	    return ind;
    }else if(tecla==9){
		ind[0]=2;
	    ind[1]=2;
	    return ind;
    }else if(tecla==15){
		ind[0]=3;
	    ind[1]=0;
	    return ind;
    }else if(tecla==0){
		ind[0]=3;
	    ind[1]=1;
	    return ind;
    }else if(tecla==16){
		ind[0]=3;
	    ind[1]=2;
	    return ind;
    }
		ind[0]=-1;
	    ind[1]=-1;
		return ind;
	}
	
	  /**
	   * Complete la clase Proceso segun la descripcion del problema y 
	   * para que pueda ser usada con el MonticuloMinimo.
	   *
	   * Recuerde que Proceso debe implementar la interfaz Comparable.
	   */
	  static class persona implements Comparable<persona>{
		  int cuenta;
			String nombre;
			
			persona(int cuenta, String nombre){
				this.cuenta=cuenta;
				this.nombre=nombre;
				
			}
			
			 public int compareTo(persona obj) {
				 
				 if(this.cuenta<obj.cuenta){
					 return 1;
					 
				 }else if(this.cuenta>obj.cuenta){
					 return -1;
					 
				 }else{
					 
					 return 0;
				 }
				 
				 
				 
			 }
		 int im=0;
		   int id=0;

		public persona(int id, int im) {
	    this.im=im;
	    this.id=id;
	    }
	    
	 
	    
	    
	  }

	  static class MonticuloMinimo {
	    List<persona> datos;

	    public MonticuloMinimo() {
	      datos = new ArrayList<persona>();
	    }

	    public MonticuloMinimo(persona[] datos) {

	      this.datos = new ArrayList<persona>(datos.length);
	      
	      for (int i = 0; i < datos.length; i++)
	        this.datos.add(datos[i]);
	      for (int i = padre(datos.length-1); i >= 0; i--)
	        desplazarAbajo(i);
	    }

	    public int padre(int u) {
	      return (u-1)/2;
	    }

	    public int izquierdo(int p) {
	      return 2*p+1;
	    }

	    public int derecho(int p) {
	      return 2*p+2;
	    }
	    
	    public void desplazarAbajo(int p) {

	      int izq = izquierdo(p);
	      int der = derecho(p);
	      int mini = p;

	      if (izq < datos.size() && datos.get(izq).compareTo(datos.get(mini)) < 0)
	        mini = izq;
	      if (der < datos.size() && datos.get(der).compareTo(datos.get(mini)) < 0)
	        mini = der;
	      if (p != mini) {
	        Collections.swap(datos, mini, p);
	        desplazarAbajo(mini);
	      }
	    }
	    
	    public void desplazarArriba(int u) {
	      
	      int p = padre(u);
	      
	      if (p >= 0 && datos.get(u).compareTo(datos.get(p)) < 0) {
	        Collections.swap(datos, u, p);
	        desplazarArriba(p);
	      }
	    }
	    
	    public persona consultar() {
	      
	      if (datos.isEmpty())
	        return null;
	      return datos.get(0);
	    }
	  
	    public void insertar(persona v) {
	      
	      datos.add(v);
	      desplazarArriba(datos.size()-1);
	    }
	    
	    public persona extraer() {
	      
	      if (datos.isEmpty())
	        return null;
	      persona v = datos.get(0);
	      datos.set(0, datos.get(datos.size()-1));
	      datos.remove(datos.size()-1);
	      desplazarAbajo(0);
	      return v;
	    }

	    /**
	     * La logica de la operacion priorizar debe
	     * implementarla aqui.
	     */
	    
	    
	    
	    public void priorizar(int k) {
	      
	       int temp= this.consultar().im;
	       this.datos.get(k).im=temp+1;
	       desplazarArriba(k);
	       
	    }
	   
	    
	  }
	  
	  public static void main(String args[]) throws FileNotFoundException {
		  Scanner scan= new Scanner(System.in);
			TypeRacerCall a= new TypeRacerCall();
			String[] in=scan.nextLine().split(" ");
			int casos=Integer.parseInt(in[0]);
			int[][] teclado=new int[4][3];
			int[] principal=new int[2];
			int[] secundario=new int[2];
//			teclado[0][0]=1;
//			teclado[0][1]=2;
//			teclado[0][2]=3;
//			teclado[1][0]=4;
//			teclado[1][1]=5;
//			teclado[1][2]=6;
//			teclado[2][0]=7;
//			teclado[2][1]=8;
//			teclado[2][2]=9;
//			teclado[3][0]=15;
//			teclado[3][1]=0;
//			teclado[3][2]=16;
			ArrayList<persona> gente=new ArrayList<persona>();
			int cuentavieja=0;
			int cuentanueva=0;
			MonticuloMinimo heap=new MonticuloMinimo(); 
			for(int k=0;k<casos;k++){
				int cuenta=0;
				in=scan.nextLine().split(" ");
				principal=a.indices(Integer.parseInt(in[0].substring(0,1)));
				
					for(int i=0;i<in[0].length()-1;i++){
						
						secundario=a.indices(Integer.parseInt(in[0].substring(0+i+1,i+2)));
				        cuenta=cuenta+(Math.abs(secundario[0]-principal[0])+Math.abs(secundario[1]-principal[1]));
				        principal=secundario;
				       
				       
				}
					
					 
					 persona b= new persona(cuenta,in[1]);
					 heap.insertar(b);
					 
					 
			}
			
			 persona c= heap.extraer();
			 persona d=heap.extraer();
			 String p=c.nombre;
			 String q=d.nombre;
			 if (c.cuenta==d.cuenta){
				 if(p.compareTo(q)==-1){
					 System.out.println(c.cuenta);
					 
					 
				 }else if(p.compareTo(q)==1){
					 System.out.println(d.cuenta);
					 
				 }
				 
			 }
			
		
				
	}
			
		}


