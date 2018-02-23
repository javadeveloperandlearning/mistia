package pe.com.cablered.mistia.ia.tsp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pe.com.cablered.mistia.geometria.Geometria;
import pe.com.cablered.mistia.geometria.Punto;
import pe.com.cablered.mistia.geometria.Segmento;

public class TSPHeuristica {
	
	
	private static String SEGMENTO_OLD = "s";
	private static String SEGMENTO1 = "s1";
	private static String SEGMENTO2 = "s2";
	private static String DISTANCIA_MIN  = "dmin";
	
	
	public static Punto PUNTO_INICIAL = new Punto(0,-12.045916, -75.195270,0.0); // punto de la bas(partida y retorno)
	
	
	public static void main(String[] args) {
		
	
		Punto puntoini = new Punto(0,-12.045916, -75.195270,0.0);
		
		
		TSPHeuristica tsp   =  new TSPHeuristica();
		System.out.println("mosgtrando valores inciales ");
		tsp.mostratPuntosHeuristica(getPuntos());
		
		//Ruta ruta  =  tsp.getRuta(puntoini, getPuntos());
		List<Punto> puntos  =  tsp.getRuta(puntoini, getPuntos());
		
		System.out.println(" puntos ordenanos ");
		tsp.mostratPuntosHeuristica(puntos);
		
		
	}
	
	
	
	
	
	
	
	//TSP  con heusitica(cambio)
	public List<Punto>    getRuta(Punto puntoini, List<Punto> puntos ){
		
		
		List<Punto> puntosList  = new ArrayList<>();
		//puntoini.setOrden(1);
		
		List<Punto> puntosuniverso  = new ArrayList<>();
		
		//System.out.println(" puntos universo init :"+puntosuniverso.size());
				
		puntosuniverso.addAll(puntos)	;
				
		List<Ruta> rutas  = new ArrayList<>();
		//rutas.add(ruta1);

		Integer orden =  1;
		// inicio de busqueda de la mejor ruta
		Punto puntopriorizadoOld   =  null; 
		while(puntosuniverso.size()>0){
			
			/*Ruta rutaold = null;
			if(rutas.size()>0){
				 rutaold =  rutas.get(rutas.size()-1);
			}*/
			
			//obtenemos el punto mas cercano a la ruta
			
			
			Punto puntopriorizado   =  null;
			//Ruta rutanueva =  null;
			
			if(puntopriorizadoOld==null){
				// primera iteracion
				puntopriorizado =  getPuntoPriorizado(puntoini, puntos);
				puntopriorizado.setOrden(orden);
				//rutanueva =  getRutaNueva(puntoini, puntopriorizado);
				puntosList.add(puntopriorizado);
				orden++;
				puntosuniverso.remove(puntopriorizado);
				puntosuniverso.remove(puntoini);
			
				
			
				
			}else{ 
				puntopriorizado = getPuntoPriorizado(puntopriorizadoOld,  puntosuniverso);
				puntopriorizado.setOrden(orden);
				 orden++;
				 //rutanueva =  getRutaNueva(puntopriorizado, rutaold, orden);
				 puntosList.add(puntopriorizado);
				 
				 //orden++;
				 puntosuniverso.remove(puntopriorizado);
			}
			//a partir del punto mas cercano obtenemos una ruta;
			
			// añadimos la nueva ruta encontrada
			//rutas.add(rutanueva);
			puntopriorizadoOld =  puntopriorizado;
			
		}	
		
		/*Ruta ruta =  null;
		
		if(rutas!=null && rutas.size()>0){
			ruta =  rutas.get(rutas.size()-1);
		}*/
		
			
		//return ruta;
		return puntosList;
		
	}
	
	
	
	private Punto  getPuntoPriorizado(Punto puntoini,  List<Punto> puntos ){
		// heuristica de hora de atencion 
		// heuristica de prioridad
		// urgencia
		// distancia	
		
		Punto puntopriorizado  = null;
		// calculamos la heuristica de distancias a los demas puntos
		for (Punto punto : puntos) {
			if( (puntoini!=null && punto!=null) && !puntoini.equals(punto)  ){
				double d =  Geometria.distanciaDeCoordenadas(puntoini, punto);
				punto.getDatos().put("distanci", d);
			}
		}
		
		// ordenamos por las heuristicas definidad en la clase comparator
		// hora atencion, prioridad, urgencia, distancia
		Collections.sort(puntos, new PuntoHeuristicaComparator1());
		System.out.println("### mostrando las heuristicas #####");
		mostratPuntosHeuristica(puntos);
	
		if( puntos!=null  && puntos.size()>0){
			puntopriorizado = puntos.get(0);
		}
		
		/*Punto pa = null ;
		for (Punto p : puntos) {
			if(pa!=null){
				String key =  p.getDatos().toString();
				String keya =  pa.getDatos().toString();
				if(!key.equals(keya)){
					puntopriorizado =  p;
					break;
				}

			}
			pa =  p ;
		}*/

		return puntopriorizado;
	}
	
	
	private Ruta getRutaNueva(Punto puntoini, Punto puntocercano){
		
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
			
			if(!nom.equals(SEGMENTO_OLD)  && !nom.equals(DISTANCIA_MIN) ){
				
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
	 
	 
	 private void mostratPuntosHeuristica(List<Punto> puntosList){
		 for (Punto punto : puntosList) {
			System.out.println(punto.getNumero()+" - " +punto.getDatos());
		}
	 }
	
	
	
	public static  List<Punto> getPuntos(){
		
	
		/*
			Long tiempoat1= (Long) mp1.get("tiempoat"); // tiempo atencion
			Integer priorida1= (Integer) mp1.get("priorida"); // prioridad
			Integer urgencia1= (Integer) mp1.get("urgencia"); // urgencia
			Double distanci1= (Double) mp1.get("distanci"); // distancia
		 * */
		
		List<Punto> puntos =  new ArrayList<Punto>();
		try{

			//-12.045916, -75.195270
			
			Punto p1 =  new Punto(1,-12.037512,-75.183327,0.0);
			p1.setDatos(getDatos("16/06/2017 09:00:00", 2 , 1));
			
			Punto p2 =  new Punto(2,-12.041961,-75.184786,0.0);
			p2.setDatos(getDatos("16/06/2017 09:00:00",1 , 2));
			
			Punto p3 =  new Punto(3,-12.0381,-75.1841,0.0);
			p3.setDatos(getDatos("16/06/2017 09:00:00",2 , 2));
			
			Punto p4 =  new Punto(4,-12.041542,-75.185816,0.0);
			p4.setDatos(getDatos("16/06/2017 11:00:00",0 , 0));
			
			Punto p5 =  new Punto(5,-12.037764,-75.181096,0.0);
			p5.setDatos(getDatos("16/06/2017 11:15:00",0 , 0));
			
			Punto p6 =  new Punto(6,-12.042801,-75.190108,0.0);
			p6.setDatos(getDatos("16/06/2017 11:00:00",0 , 0));
			
			Punto p7 =  new Punto(7,-12.04364,-75.184014,0.0);
			p7.setDatos(getDatos("16/06/2017 11:00:00",0 , 0));
			
			Punto p8 =  new Punto(8,-12.045739,-75.185387,0.0);
			p8.setDatos(getDatos("16/06/2017 11:30:00",0 , 0));
			
			Punto p9 =  new Punto(9,-12.04683,-75.187361,0.0);
			p9.setDatos(getDatos("16/06/2017 11:50:00",0 , 0));
			
			Punto p10 =  new Punto(10,-12.050775,-75.187962,0.0);
			p10.setDatos(getDatos("16/06/2017 12:30:00",0 , 0));
			
			Punto p11 =  new Punto(11,-12.053797,-75.184271,0.0);
			p11.setDatos(getDatos("16/06/2017 13:00:00",0 , 0));
			
			puntos.add(p8);
			puntos.add(p9);
			puntos.add(p3);
			puntos.add(p4);
			puntos.add(p5);
			puntos.add(p6);
			puntos.add(p7);
			puntos.add(p10);
			puntos.add(p1);
			puntos.add(p2);
			puntos.add(p11);
			
			
		}catch(Exception e ){
			
			e.printStackTrace();
			
		}
		
		return puntos;
	
	}
	
	public  static Map getDatos(String fechaatencion, Integer priorida, Integer urgencia   ){
		String DATE_TIME_FORMA =  "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat sdf =  new SimpleDateFormat(DATE_TIME_FORMA);
		Map mp1 =  new LinkedHashMap<>();
		try {
			
			Date date1 = sdf.parse(fechaatencion);
			mp1.put("fecha", fechaatencion);
			mp1.put("tiempoat", date1.getTime());
			mp1.put("priorida",  priorida);
			mp1.put("urgencia", urgencia);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mp1;
	}

}
