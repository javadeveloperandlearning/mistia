package pe.com.cablered.mistia.geometria;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Geometria {
	
	
	
	
	public static String[] colors = { 
			
			"#ff0000",
			"#446600",
			"#003399",
			"#e6e600",
			"#73e600",
			"#3366ff",
			"#ff8c1a",
			"#FF0000",
			"#ff99bb", 
			"#88cc00",
			"#d5ff80",
			"#ff8000",
			"#1a8cff",
			"#034f84",
			"#618685",
			"#4040a1",
			"#b0aac0",
			"#f4a688",
			"#e0876a",
			"#622569",
			"#588c7e",
			"#f2ae72",
			"#bc5a45"
			/*"#3366ff",
			"#ff8c1a",
			"#FF0000",
			"#ff99bb", 
			"#88cc00",
			"#3366ff",
			"#ff8c1a",
			"#FF0000",
			"#ff99bb",
			"#ff8c1a",
			"#FF0000",
			"#ff99bb",
			"#88cc00",
			"#3366ff",
			"#ff8c1a",
			"#FF0000",
			"#ff99bb"*/
			
	
	
	
			};
	
	
	
	
	
	
	
	/**
	 * devuelve el punto central entre una lista de puntos geograficos
	 * 
	 * */

	public static  Punto  puntoCentral(Punto[] puntos){
		
		if(puntos.length == 1){
			
			return puntos[0];
		}
		
	    double x = 0.0;
	    double y = 0.0;
	    double z = 0.0;
	   
		if(puntos==null || puntos.length ==0){
			return null;
		}
		
		int cant =  puntos.length ;
		for (Punto punto : puntos) {
			
			double lat   = punto.getLatitud()*Math.PI/180;
			double lon   = punto.getLongitud()*Math.PI/180;
			
			double a = Math.cos(lat) * Math.cos(lon);
			double b = Math.cos(lat) * Math.sin(lon);
			double c = Math.sin(lat);
	        x += a;
	        y += b;
	        z += c;
		}
		
	    x /= cant;
	    y /= cant;
	    z /= cant;
		
	    double lon = Math.atan2(y, x);
	    double hyp = Math.sqrt(x*x+y*y);
	    double lat = Math.atan2(z, hyp);
	    
	    lon = lon * 180/ Math.PI;
		lat = lat * 180/ Math.PI;
	    
	    
		return new Punto(lat, lon);
		
	}
	
	
	
	/**
	 *devuele la distancia entre dos puntos geográficos  
	 * 
	 * */
	
	public static double distanciaDeCoordenadas(Punto p1, Punto  p2) {  
		
		double lat1 =  p1.getLatitud();
		double lng1 =  p1.getLongitud();
		double lat2 =  p2.getLatitud();
		double lng2 = p2.getLongitud();
        //double radioTierra = 3958.75;//en millas  
        double radioTierra = 6371 * 1000;//en kilómetros  
        double dLat = Math.toRadians(lat2 - lat1);  
        double dLng = Math.toRadians(lng2 - lng1);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
   
        return distancia;  
    }  
	
	
	/**
	 * Devuel el radio maximo desde un punto central hacia una lista de puntos
	 * 
	 * */
	public static double getRadioMaximo(Punto puntoCentral, Punto [] puntos){
		
		double radio = 0.0;
		
		List<Double> distancias = new ArrayList();
		
		for (Punto punto : puntos) {
			double d  = Geometria.distanciaDeCoordenadas(puntoCentral, punto);
			distancias.add(d);
		}
		
		Collections.sort(distancias);
		if(distancias.size()>0){
			radio  =  distancias.get(distancias.size()-1);
		}
		return radio;
	};
	
	
	
	public static double getAreaCirculo(double radio){
		
		return  Math.PI * radio+radio;
	}
	
	
	
	public static List<Segmento> getSegmetos(List<Punto> puntoList){
		
		List<Segmento> segmentos = new ArrayList<Segmento>();

		// todos contra todos
		for (Punto p1 : puntoList) {
			for (Punto p2 : puntoList) {
				if( (p1!=null && p2!=null) && !p1.equals(p2)  ){
					double d =  Geometria.distanciaDeCoordenadas(p1, p2);
					segmentos.add(new Segmento(p1, p2, d));
					
				}
			}
		}
		
		Collections.sort(segmentos);
		
		return segmentos;
	}
	
	
	
	public static Segmento createSegmeto(Punto p1, Punto  p2){
		
		double d =  Geometria.distanciaDeCoordenadas(p1, p2);
		return new Segmento(p1, p2, d);
		
	}
	
	
	
	public static  List<Punto>  toListPuntos(List<Segmento> segmentos ){
		
		Set<Punto> puntoSet =  new HashSet<Punto>();
		
		for (Segmento s : segmentos) {
			puntoSet.add(s.getPunto1());
			puntoSet.add(s.getPunto2());
		}
		
		List<Punto> puntos =  new ArrayList<Punto>();
		puntos.addAll(puntoSet);
		return puntos;
	}
		
	
	public static  Punto[]  toArrayPuntos(List<Segmento> segmentos ){
		List<Punto> puntos =  Geometria.toListPuntos(segmentos);
		Punto[] puntoArray = new Punto[puntos.size()];
		puntos.toArray(puntoArray);
		return puntoArray;
	}
	
	
	
	
	
	/*bool IsPointInPolygon(List<Loc> poly, Loc point)
	{
	    int i, j;
	    bool c = false;
	    for (i = 0, j = poly.Count - 1; i < poly.Count; j = i++)
	    {
	        if ((((poly[i].Lt <= point.Lt) && (point.Lt < poly[j].Lt)) 
	                || ((poly[j].Lt <= point.Lt) && (point.Lt < poly[i].Lt))) 
	                && (point.Lg < (poly[j].Lg - poly[i].Lg) * (point.Lt - poly[i].Lt) 
	                    / (poly[j].Lt - poly[i].Lt) + poly[i].Lg))

	            c = !c;
	        }
	    }

	    return c;
	}*/
	
	/*
	 * 
	 private static bool IsPointInPolygon(List<Loc> poly, Loc point)
{
    int i, j;
    bool c = false;
    for (i = 0, j = poly.Count - 1; i < poly.Count; j = i++)
    {
        if ((((poly[i].Lt <= point.Lt) && (point.Lt < poly[j].Lt)) |
            ((poly[j].Lt <= point.Lt) && (point.Lt < poly[i].Lt))) &&
            (point.Lg < (poly[j].Lg - poly[i].Lg) * (point.Lt - poly[i].Lt) / (poly[j].Lt - poly[i].Lt) + poly[i].Lg))
            c = !c;
    }
    return c;
}
	 * */
	
	public static boolean IsPointInPolygon(List<Punto> puntosList, Punto punto){
		
	    int i, j;
	    boolean c = false;
	    for (i = 0, j = puntosList.size() - 1; i < puntosList.size(); j = i++){
	    	if (
	    			(((     puntosList.get(i).getLatitud()      <= punto.getLatitud())      && (punto.getLatitud() < puntosList.get(j).getLatitud())) 
	                | ((puntosList.get(i).getLatitud()      <= punto.getLatitud())      && (punto.getLatitud() < puntosList.get(i).getLatitud() ))) 
	                && (punto.getLongitud()< (puntosList.get(j).getLongitud() - puntosList.get(i).getLongitud()) * (punto.getLatitud()- puntosList.get(i).getLatitud()) 
	                    / (puntosList.get(j).getLatitud() - puntosList.get(i).getLatitud() ) + puntosList.get(i).getLongitud())){
	            c = !c;
	    	}
	    	System.out.println( " i ="+i +" -  j="+j);
	    	
	    }
	   	    
		return  c;
	}


	
	public static void main(String[] args) {
		
		
		List<Punto> puntosList  = new ArrayList();
		puntosList.add(new Punto(-12.009472, -75.172256));
		puntosList.add(new Punto(-12.021729, -75.173629));
		puntosList.add(new Punto(-12.031970, -75.185130));
		puntosList.add(new Punto(-12.037847, -75.179294));
		puntosList.add(new Punto(-12.061853, -75.180495));
		puntosList.add(new Punto(-12.076970, -75.206165));
		puntosList.add(new Punto(-12.087368, -75.230792));
		puntosList.add(new Punto(-12.079434, -75.232029));
		puntosList.add(new Punto(-12.071658, -75.233997));
		puntosList.add(new Punto(-12.070616, -75.219422));
		puntosList.add(new Punto(-12.060875, -75.209870));
		puntosList.add(new Punto(-12.051055, -75.203948));
		puntosList.add(new Punto(-12.030713, -75.198347));
		puntosList.add(new Punto(-12.009781, -75.181023));
		puntosList.add(new Punto(-12.009472, -75.172256));
		
		
		  System.out.println(IsPointInPolygon(puntosList, new Punto(-12.048335, -75.193663)));// dentro
		  System.out.println(IsPointInPolygon(puntosList, new Punto(-12.063326, -75.216699)));// fuera
		
		  System.out.println(IsPointInPolygon(puntosList, new Punto(-12.051055, -75.223689)));// fuera
		  
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
