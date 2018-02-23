package pe.com.cablered.mistia.ia.clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pe.com.cablered.mistia.geometria.Geometria;
import pe.com.cablered.mistia.geometria.Punto;

public class Point {
	
	
	
		private int number;
	    private double x = 0;
	    private double y = 0;
	    private int cluster_number = 0;
	    private double cost = 0.0;
	    
	    public Point(int number, double x, double y)
	    {
	    	this.number =  number;
	    	this.setX(x);
	        this.setY(y);
	    }
	    

	    public Point(double x, double y)
	    {
	        this.setX(x);
	        this.setY(y);
	    }
	    
	    public void setX(double x) {
	        this.x = x;
	    }
	    
	    public double getX()  {
	        return this.x;
	    }
	    
	    public void setY(double y) {
	        this.y = y;
	    }
	    
	    public double getY() {
	        return this.y;
	    }
	    
	    public void setCluster(int n) {
	        this.cluster_number = n;
	    }
	    
	    public int getCluster() {
	        return this.cluster_number;
	    }
	    
	    //Calculates the distance between two points.
	    protected static double distance(Point p, Point centroid) {
	        //return Math.sqrt(Math.pow((centroid.getY() - p.getY()), 2) + Math.pow((centroid.getX() - p.getX()), 2));
	  
	    	Punto p1 =  new Punto(p.getX(), p.getY());
	    	Punto p2 =  new Punto(centroid.getX(), centroid.getY());
	    	//return Geometria.distanciaDeCoordenadas(p1, p2);
	    	return Math.pow( Geometria.distanciaDeCoordenadas(p1, p2),2);
	     
	    }
	    
	    //Creates random point
	    protected static Point createRandomPoint(int min, int max) {
	    	Random r = new Random();
	    	double x = min + (max - min) * r.nextDouble();
	    	double y = min + (max - min) * r.nextDouble();
	    	return new Point(x,y);
	    }
	    
	    protected static List createRandomPoints(int min, int max, int number) {
	    	List points = new ArrayList(number);
	    	for(int i = 0; i < number; i++) {
	    		points.add(createRandomPoint(min,max));
	    	}
	    	return points;
	    }
	    
	    public String toString() {
	    	return "("+x+","+y+")";
	    }
	    
	    public String toString2() {
	    	return "("+x+","+y+")   centroide : "+cluster_number;
	    }

		public double getCost() {
			return cost;
		}

		public void setCost(double cost) {
			this.cost = cost;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(x);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(y);
			result = prime * result + (int) (temp ^ (temp >>> 32));
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
			Point other = (Point) obj;
			if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
				return false;
			if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
				return false;
			return true;
		}
	    
	    
	    

}
