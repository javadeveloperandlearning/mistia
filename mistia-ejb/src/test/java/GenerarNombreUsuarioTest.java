
public class GenerarNombreUsuarioTest {
	
	
	 public static void main(String[] args) {
		
		 
		/* String str = "Hello I'm your String";
		 String[] splited = str.split("\\s+");
		 for (String string : splited) {
			 System.out.println(string);
		 }*/
		
		 String nombres = "Roger Antonio";
		 String apellidos = "Chaname Guerrero";
		 
		 String[] arraapp =  apellidos.split("\\s+");
		 String l1 =  nombres.substring(0, 1);
		 String l2 =  arraapp[0];
		 String nombreUsuario = l1+l2;
		 System.out.println(nombreUsuario.toUpperCase());
		 
	}

}
