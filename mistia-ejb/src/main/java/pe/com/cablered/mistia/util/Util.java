package pe.com.cablered.mistia.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import pe.com.cablered.mistia.model.SolicitudServicio;

public class Util {

	

	
	public static void main(String[] args) {
		
		
		 Random rand = new Random();
		 System.out.println(rand.nextDouble());
		 
	}
	
	/**
	 * Returns a psuedo-random number between min and max, inclusive.
	 * The difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min Minimim value
	 * @param max Maximim value.  Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	
	
	public static ArrayList<Integer> getRandomNonRepeatingIntegers(int size, int min,
	        int max) {
	    ArrayList<Integer> numbers = new ArrayList<Integer>();

	    while (numbers.size() < size) {
	        int random = randInt(min, max);

	        if (!numbers.contains(random)) {
	            numbers.add(random);
	        }
	    }
	    return numbers;
	}
	
	
	public static String getTag (SolicitudServicio s){
		
		Calendar cal =  Calendar.getInstance();
		cal.setTime(s.getFechaSolicitud());
		int year     =  cal.get(Calendar.YEAR);
		String nsoli =  "00000"+s.getNumeroSolicitud() ;
		nsoli =  nsoli.substring(nsoli.length()-5,nsoli.length());
		String tag = s.getTipoSolicitud().getAbreviatura()+""+year+""+nsoli;
		return tag;
		
	}
	
	public static String  getNombreNumero(int numero ){
		
		String nombre = null;
		
		switch (numero) {
		case 1:
			nombre =  "Uno";
			break;
		case 2:
			nombre =  "Dos";
			break;
		case 3:
			nombre =  "Tres";
			break;
		case 4:
			nombre =  "Cuatro";
			break;
		case 5:
			nombre =  "Cinco";
			break;
		case 6:
			nombre =  "Seis";
			break;
		case 7:
			nombre =  "Siete";
			break;
		case 8:
			nombre =  "Ocho";
			break;
		case 9:
			nombre =  "Nueve";
			break;
		case 10:
			nombre =  "Diez";
			break;
		case 11:
			nombre =  "Once";
			break;
			
		default:
			nombre = null;
			break;
		}
	
		return nombre;
	}
	
	
	
}
