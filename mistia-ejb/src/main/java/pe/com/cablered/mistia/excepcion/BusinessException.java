package pe.com.cablered.mistia.excepcion;

public class BusinessException extends Exception {
	
	private int codigo;

	public BusinessException(int codigo, String mensaje ) {
		 super( mensaje);
		 this.codigo =  codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	

}
