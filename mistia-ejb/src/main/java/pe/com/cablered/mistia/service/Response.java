package pe.com.cablered.mistia.service;

public class Response {
	
	public static final  int OK =  0;
	public static final  String MSG_OK =  "Operación realizada con éxito";
	
	public static final  int ERROR =  1;
	public static final  String MSG_ERROR =  "Ocurrió un error";
	
	private int codigo;
	private String mensaje;
	private Object data ;
	
	public Response() {
	
	}
	
	

	public Response(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}



	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}



	public Object getData() {
		return data;
	}



	public void setData(Object data) {
		this.data = data;
	}



	@Override
	public String toString() {
		return "Response [codigo=" + codigo + ", mensaje=" + mensaje + ", data=" + data + "]";
	}
	
	
	

}
