package pe.com.cablered.mistia.util;



public class ConstantSecurity {
	
	// tipo de menus
	public static final int TIPO_MENU_TRANSACCION = 1;
	public static final int TIPO_MENU_CONSULTA = 2;
	public static final int TIPO_MENU_MANTENIMIENTO = 3;
	

	
	public static final int TIPO_MENU_FACTURACION = 4;
	public static final int TIPO_MENU_COMERCIAL = 5;
	public static final int TIPO_MENU_PROGRAMACION = 6;
	
	public static final int TIPO_MENU_MONITOREO = 7;
	public static final int TIPO_MENU_EJECUCION = 8;
	public static final int TIPO_MENU_SEGURIDAD = 9;
	public static final int TIPO_MENU_ATENCION_CLIENTE = 10;
	public static final int TIPO_MENU_CLIENTE = 0;
	
	
	
	
	
	public static final int COD_MODU_SEGURIDAD = 1;	
	public static final String USER_SESSION = "usuario";
	
	//repuestas de logueo
	
	public static final int COD_OK = 0;
	public static final String  MSG_OK = "LA OPERACIÓN SE EJECUTO CON EXITO";
	public static final int COD_ERR = 1;
	public static final String  MSG_ERRO = "ERROR AL REALIZAR LA OPERACIÓN";
	
	public static final int COD_USER_ERRO_SESI = 1;
	public static final String  MSG_USER_ERRO_SESI = "password y/o clave incorrectos";
	public static final int COD_USER_BLOQ = 2;
	public static final String  MSG_USER_BLOQ = "Usuario bloqueado";
	
	public static final int COD_USER_EXIST = 3;
	public static final String  MSG_USER_EXIST = "La cuenta usuario ya existe. Registre otra ";
	
	public static final int COD_USER_CLAVE_DIF = 4;
	public static final String  MSG_USER_CLAVE_DIF = "La clave de confirmación es diferente";
	
	public static final int COD_ERRO_VALI = 1000;
	public static final String  MSG_ERRO_VALI = "Ingreso un valor válido";
	
	//grupos de estadps 
	
	public static final int COD_GRUPO_ACTIVO = 1;
	


}
