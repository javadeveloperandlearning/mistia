package pe.com.cablered.mistia.geometria;

public class Segmento implements Comparable<Segmento> {
	
	
	
	private Punto punto1;
	private Punto punto2;
	private Double distancia; // metros
	
	public Segmento() {
	
	}

	public Segmento(Punto punto1, Punto punto2, Double distancia) {
		super();
		this.punto1 = punto1;
		this.punto2 = punto2;
		this.distancia = distancia;
	}

	public Punto getPunto1() {
		return punto1;
	}

	public void setPunto1(Punto punto1) {
		this.punto1 = punto1;
	}

	public Punto getPunto2() {
		return punto2;
	}

	public void setPunto2(Punto punto2) {
		this.punto2 = punto2;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	@Override
	public int compareTo(Segmento o) {
		int comparedist = 	o.distancia.intValue();
		return this.distancia.intValue()-comparedist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((distancia == null) ? 0 : distancia.hashCode());
		result = prime * result + ((punto1 == null) ? 0 : punto1.hashCode());
		result = prime * result + ((punto2 == null) ? 0 : punto2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Segmento other = (Segmento) obj;
		if (distancia == null) {
			if (other.distancia != null)
				return false;
		} else if (!distancia.equals(other.distancia))
			return false;
		if (punto1 == null) {
			if (other.punto1 != null)
				return false;
		} else if (!punto1.equals(other.punto1))
			return false;
		if (punto2 == null) {
			if (other.punto2 != null)
				return false;
		} else if (!punto2.equals(other.punto2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Segmento [punto1=" + punto1 + ", punto2=" + punto2 + ", distancia=" + distancia + "]";
	}
	
	
	

	

}
