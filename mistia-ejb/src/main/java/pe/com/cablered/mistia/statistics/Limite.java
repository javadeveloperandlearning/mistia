package pe.com.cablered.mistia.statistics;

public class Limite {
	
	private double superior;
	
	private double inferior;

	public double getSuperior() {
		return superior;
	}

	public void setSuperior(double superior) {
		this.superior = superior;
	}

	public double getInferior() {
		return inferior;
	}

	public void setInferior(double inferior) {
		this.inferior = inferior;
	}

	@Override
	public String toString() {
		return "Limite [inferior =" + inferior  + ", superior=" + superior + "]";
	}

	
	
	
}
