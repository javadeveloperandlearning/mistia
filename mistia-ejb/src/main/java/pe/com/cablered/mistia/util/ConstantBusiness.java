package pe.com.cablered.mistia.util;

public class ConstantBusiness {
	
	
	// tipos de solicitudes de servicio
	public static final int TIPO_SOLICITUD_INSTALACION =  1;
	public static final int TIPO_SOLICITUD_RECONEXION =  2;
	public static final int TIPO_SOLICITUD_MODIFACION_SERVICIO =  3;
	public static final int TIPO_SOLICITUD_BAJA =  4;
	public static final int TIPO_SOLICITUD_SUSPENSION =  5;
	public static final int TIPO_SOLICITUD_TRASLADO =  6;
	public static final int TIPO_SOLICITUD_CORTE =  7;
	public static final int TIPO_SOLICITUD_AVERIA =  8;
	
	// prioridades de la solicitudes de servicio
	public static final int PRIOIDAD_SOLICITUD_SERVICIO_ALTA = 1;  
	public static final int PRIOIDAD_SOLICITUD_SERVICIO_MEDIA = 2;  
	public static final int PRIOIDAD_SOLICITUD_SERVICIO_BAJA = 3;  
	
	
	// competencias 
	
	public static final int COMPETENCIA_PRODUCTIVIDAD = 1;
	public static final int COMPETENCIA_TRABAJO_EQUIPO = 2;
	public static final int COMPETENCIA_ORIENTACION_METAS = 3;
	public static final int COMPETENCIA_CALIDAD_ATENCION= 4;
	
	
	// estados programacion
	
	public static final int  ESTADO_PROGRAMACION_GENERADO = 1;
	public static final int  ESTADO_PROGRAMACION_EJECUCION = 2;
	public static final int  ESTADO_PROGRAMACION_ANULADO = 3;
	public static final int  ESTADO_PROGRAMACION_CERRADO = 4;
	
	public static final String  DESCRIPCION_ESTADO_PROGRAMACION_ANULADO = "Anulado";
	
	
	
	// estados solicitud de servicio	
	public static final int  ESTADO_SOLICITUD_PENDIENTE =5;
	public static final int  ESTADO_SOLICITUD_ASIGNADO = 6;
	public static final int  ESTADO_SOLICITUD_CAMINO = 7;
	public static final int  ESTADO_SOLICITUD_EJECUCION = 8;
	public static final int  ESTADO_SOLICITUD_CANCELADO= 9;	
	public static final int  ESTADO_SOLICITUD_CERRADO = 10;

	
	
	// grupos de estados
	

	public static final int  GRUPO_ESTADO_SOLICITUD_SERVICIO = 2;
	
	
	public static final int PRIORIDAD_SOLICITUD_ALTA =  1;
	public static final int PRIORIDAD_SOLICITUD_MEDIA = 2;
	public static final int PRIORIDAD_SOLICITUD_BAJA =  3;
	
	
	//tiempo promedio de jorndad
	
	public static final double TIEMPO_JORNADA =  480.00;
	// formato de fecha
	public static final String HORA_FORMAT =  "HH:mm aaa";
    public static final String  FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm:ss";

	//redes neuronales
	
	public static final int COD_RED_NEURONAL_PROGRAMACION =  1;
	
	
	
	// nueva programacion
	public static final String ACCION_PROGRAMACION = "accion_programacion";
	public static final int ACCION_NUEVA_PROGRAMACION = 1;
	public static final int ACCION_EDITA_PROGRAMACION = 2;
	
	// acciones 
	
	public static final int ACCION_NUEVO = 1;
	public static final int ACCION_EDITA = 2;
	
	public static final int URGENTE = 1;
	public static final int NO_URGENTE = 99;
	
	
	// constantes
	
	public static final int CONSTANTE_ENCUESTA_CALIDAD_ATENCION = 1;
	
	public static final int CONSTANTE_ṔREGUNTA_CALIDAD_ATENCION_1 = 1;
	public static final int CONSTANTE_ṔREGUNTA_CALIDAD_ATENCION_2 = 2;
	
	public static final int CODIGO_TODOS = 0;
	public static final String DES_SELECCIONE = "--Seleccione--";
	public static final int CODIGO_SELECCIONE = -1;
	
	
	// dias
	public static final int LUN = 1;
	public static final int MAR = 2;
	public static final int MIE = 3;
	public static final int JUE = 4;
	public static final int VIE = 5;
	public static final int SAB = 6;
	public static final int DOM = 7;
	

	// Deparatamentos
	public static final int COD_DEPA_HUANCAYO = 12;
	
	
	//tipos
	public static final int COD_GRUPO_TIPO_DOMICILIO = 1;
	public static final int COD_GRUPO_TIPO_VELO_INTER = 2;
	public static final int COD_GRUPO_TIPO_PAQ_CABLE = 3;

	
	
	
	
	

	
	
}
