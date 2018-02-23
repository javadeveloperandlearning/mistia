package pe.com.cablered.mistia.ia.tsp;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pe.com.cablered.mistia.geometria.Geometria;
import pe.com.cablered.mistia.geometria.Punto;
import pe.com.cablered.mistia.geometria.Segmento;


/** 
 * TSP 
 * clase encargada de obtener la ruta mas optima en base a la distancia entre puntos geograficos
 * en base al problema del agente viajero
 * 
 * */
public class TSP {

	
	private static String SEGMENTO_OLD = "s";
	private static String SEGMENTO1 = "s1";
	private static String SEGMENTO2 = "s2";
	private static String DISTANCIA_MIN  = "dmin";
	
	
	public static Punto PUNTO_INICIAL = new Punto(0,-12.045916, -75.195270,0.0); // punto de la base(partida y retorno)
	
	
	public static void main(String[] args) {
		
	
		Punto puntoini = new Punto(0,-12.045916, -75.195270,0.0);
		
		
		TSP tsp   =  new TSP();
		Ruta ruta =   tsp.getRuta(puntoini, getPuntos());
		List<Punto> puntos  =  ruta.getPuntosList();
		
		System.out.println(" puntos ordenanos ");
		tsp.mostratPuntos(puntos);
		
		
	}
	
	
	
	
	
	
	
	//TSP convencional
	public Ruta  getRuta(Punto puntoini, List<Punto> puntos ){
		
		
		puntoini.setOrden(1);
		
		List<Punto> puntosuniverso  = new ArrayList<>();
		
		//System.out.println(" puntos universo init :"+puntosuniverso.size());
				
		puntosuniverso.addAll(puntos)	;
				
		List<Ruta> rutas  = new ArrayList<>();
		//rutas.add(ruta1);

		Integer orden =  2;
		// inicio de busqueda de la mejor ruta
		while(puntosuniverso.size()>0){
			
			Ruta rutaold = null;
			if(rutas.size()>0){
				 rutaold =  rutas.get(rutas.size()-1);
			}
			
			//obtenemos el punto mas cercano a la ruta
			Punto _puntocercano   =  null;
			Ruta rutanueva =  null;
			
			if(rutaold==null){
				// primera iteracion
				_puntocercano =  getPuntoCercano(puntoini, puntos);
				_puntocercano.setOrden(orden);
				rutanueva =  getNuevaRuta(puntoini, _puntocercano);
				orden++;
				puntosuniverso.remove(_puntocercano);
				puntosuniverso.remove(puntoini);
				
			}else{ 
				_puntocercano = getPuntoCercano(rutaold, puntosuniverso);
				_puntocercano.setOrden(orden);
				 orden++;
				 rutanueva =  getRutaNueva(_puntocercano, rutaold, orden);
				 orden++;
				 puntosuniverso.remove(_puntocercano);
			}
			//a partir del punto mas cercano obtenemos una ruta;
			
			// añadimos la nueva ruta encontrada
			rutas.add(rutanueva);

		}	
		
		Ruta ruta =  null;
		
		if(rutas!=null && rutas.size()>0){
			ruta =  rutas.get(rutas.size()-1);
		}
		
			
		return ruta;
	}
	
	
	
	private Ruta getNuevaRuta(Punto puntoini, Punto puntocercano){
		
			Segmento s1 = Geometria.createSegmeto(puntoini, puntocercano);
			Segmento s2 = Geometria.createSegmeto(puntocercano, puntoini);
			// formando la primera ruta 
			Ruta ruta1  =  new Ruta();
			ruta1.getSegmentos().add(s1);
			ruta1.getSegmentos().add(s2);
			return ruta1;
	}
	
	
	
	
	
	
	private Ruta getRutaNueva(Punto puntocercano, Ruta rutaold, Integer orden) {

		Punto pc =  puntocercano;
		List<Segmento> segmentos =  rutaold.getSegmentos();
		
		
		List<Map<String, Object>>  subrutas = new ArrayList<>();
		
		// aplicando formula 
		for (Segmento s : segmentos) {
			
			//hallamos proximos segmentos
			Punto p1 =  s.getPunto1();
			Punto p2  = s.getPunto2();		
			
			//nuevos segmentos para añadir a la ruta
			Segmento s1 =  Geometria.createSegmeto(pc, p1);
			Segmento s2 =  Geometria.createSegmeto(pc, p2);
			
			Map<String, Object> subruta = new LinkedHashMap();
			subruta.put(SEGMENTO1, s1);
			subruta.put(SEGMENTO2, s2);
			subruta.put(SEGMENTO_OLD, s);
			subrutas.add(subruta);
			
		 }
		
		//evaluando las rutas con mejor costo
		for (Map<String, Object> subruta : subrutas) {
			
			Double d1 =  ((Segmento)subruta.get(SEGMENTO1)).getDistancia();
			Double d2 =  ((Segmento)subruta.get(SEGMENTO2)).getDistancia();
			Double da =  ((Segmento)subruta.get(SEGMENTO_OLD)).getDistancia();// distancia del segmento anterior
			Double dmin =   d1+d2-da; 
			//System.out.println(""+d1+"+"+d2+ " - "+da+ " =  "+dmin);
			subruta.put(DISTANCIA_MIN, dmin);
		
		}
		
		//Obtenemos la subrutas   
		Collections.sort(subrutas, new SubRutaComparator());		

		// seleccionamos el de menor costo
		Map<String, Object> _subruta  = subrutas.get(0);
		
		setOrden(_subruta, orden);
		
		//System.out.println(" ruta con el menor costos = "+_subruta.get("dmin"));
		Segmento s1 = (Segmento)_subruta.get(SEGMENTO1);
		Segmento s2 = (Segmento)_subruta.get(SEGMENTO2);
		Segmento s = (Segmento)_subruta.get(SEGMENTO_OLD);

		// removemos el aterior segmento	

		rutaold.getSegmentos().remove(s);
		
		// añadimos los nuevos segmentos
		rutaold.getSegmentos().add(s1);
		rutaold.getSegmentos().add(s2);
		
		//mostrarRutas(rutaold);
		
		return rutaold;
	}
	
	private void setOrden(Map<String, Object>  _subruta, Integer orden){
		
		Iterator<String> it =  _subruta.keySet().iterator();
		
		
		while( it.hasNext()){
			
			String nom =    it.next();
			
			if(!nom.equals(TSP.SEGMENTO_OLD)  && !nom.equals(TSP.DISTANCIA_MIN) ){
				
				Segmento s =  (Segmento)_subruta.get(nom);
				
				if(s.getPunto1().getOrden()==null){
					
					s.getPunto1().setOrden(orden);
					
				}else if(s.getPunto2().getOrden()==null){
					
					s.getPunto2().setOrden(orden);
				}
				
			}   
			
			
			
		}
		
		Segmento s1 = (Segmento)_subruta.get(SEGMENTO1);
		Segmento s2 = (Segmento)_subruta.get(SEGMENTO2);
		
		
		
	}
	
	/***
	 * 
	 * */

	private Punto getPuntoCercano(Ruta ruta, List<Punto> puntosuniverso) {
	
		//obtenemos el centroide de la rutas
		List<Segmento> segmentos  =  ruta.getSegmentos();
		Punto[] puntoArray = Geometria.toArrayPuntos(segmentos);
		// centroide de la ruta
		Punto puntocentral  =   Geometria.puntoCentral(puntoArray);
		//ubicar el punto mas cercano del centroide de la ruta
		return getPuntoCercano(puntocentral,puntosuniverso );
	}


	/***
	 * getPuntoCercano
	 * obtiene el punto mas cercano a un punto incial de una determinada de puntos
	 * 
	 * */
	 private Punto getPuntoCercano(Punto puntoini,  List<Punto> puntos ){
			
		   List<Segmento> segmentos = new ArrayList<Segmento>();
			// todos contra todos
			for (Punto punto : puntos) {
				if( (puntoini!=null && punto!=null) && !puntoini.equals(punto)  ){
						double d =  Geometria.distanciaDeCoordenadas(puntoini, punto);
						segmentos.add(new Segmento(puntoini, punto, d));
				}
			}
			
			Collections.sort(segmentos);
			Punto puntocerano =  segmentos.get(0).getPunto2();
		 
		 return puntocerano;
		 
	 }
	
	 
	 private void mostrarRutas(Ruta ruta){
			List<Segmento> _segmentos =  ruta.getSegmentos();
			for (Segmento _segmento : _segmentos) {
				System.out.println(_segmento.toString());
				
			}
	 }
	 
	 private void mostratPuntos(List<Punto> puntosList){
		 for (Punto punto : puntosList) {
			System.out.println(punto);
		}
		 
	 }
	 
	 

	
	public static  List<Punto> getPuntos(){
		
		
		//-12.045916, -75.195270
		
		List<Punto> puntos =  new ArrayList<Punto>();
		puntos.add(new Punto(1,-12.037512,-75.183327,0.0));
		puntos.add(new Punto(2,-12.037261,-75.184271,0.0));
		puntos.add(new Punto(3,-12.041961,-75.184786,0.0));
		puntos.add(new Punto(4,-12.0381,-75.1841,0.0));
		puntos.add(new Punto(5,-12.041542,-75.185816,0.0));
		puntos.add(new Punto(6,-12.037764,-75.181096,0.0));
		puntos.add(new Punto(7,-12.042801,-75.190108,0.0));
		puntos.add(new Punto(8,-12.04364,-75.184014,0.0));
		puntos.add(new Punto(9,-12.045739,-75.185387,0.0));
		puntos.add(new Punto(10,-12.04683,-75.187361,0.0));
		puntos.add(new Punto(11,-12.050775,-75.187962,0.0));
		puntos.add(new Punto(12,-12.053797,-75.184271,0.0));
		return puntos;
		
	}
	
}
