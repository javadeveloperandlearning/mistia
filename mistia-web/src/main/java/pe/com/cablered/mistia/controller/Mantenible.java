package pe.com.cablered.mistia.controller;

public interface Mantenible {
	
	
	
	public void mostrar();
	
	public void nuevo();
	
	public void editar();
	
	public void grabar();
	
	public void eliminar(Object object);
	
	public static final  String NUEVO = "1";
	public static final String EDITAR = "2";
	
	public static final  String NUEVO_KEY = "NUEVO";
	public static final String EDITAR_KEY = "EDITAR";
	public static final String SELECCIONE_LABEL = "--Seleccione--"; 
	public static final int SELECCIONE_VALUE = 0; 
	
	
	public static final String TODOS_LABEL = "--Todos--"; 
	public static final int TODOS_VALUE = 0; 
	
	
	
	
	

}
