package pe.com.cablered.mistia.ia.clustering;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
	
	public List<Point> points;
	public Point centroid;
	public int id;
	
	
	
	
	
	//Creates a new Cluster
	public Cluster(int id) {
		this.id = id;
		this.points = new ArrayList();
		this.centroid = null;
	}
	
	
	public Cluster(){
		
		
	}
	
	
	
	
	
	public void setId(int id) {
		this.id = id;
	}


	public Cluster(Point centroid){
		this.centroid =  centroid;
		this.points = new ArrayList();
		
	}
	
	public Cluster(int id ,Point centroid){
		this.id =  id;
		this.centroid =  centroid;
		this.points = new ArrayList();
		
	}
	

	public List<Point> getPoints() {
		return points;
	}
	
	public void addPoint(Point point) {
		points.add(point);
	}

	public void setPoints(List points) {
		this.points = points;
	}

	public Point getCentroid() {
		return centroid;
	}

	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}

	public int getId() {
		return id;
	}
	
	public void clear() {
		
		
		points.clear();
		
	}
	
	public void plotCluster() {
		System.out.println("[Cluster: " + id+"]");
		System.out.println("[Centroid: " + centroid + "]");
		System.out.println("[Points: \n");
		
		
		if( points!=null && points.size()>0){
			for(Point p : points) {
				System.out.println(p);
			}
		}
		System.out.println("]");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Cluster other = (Cluster) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cluster [centroid=" + centroid + ", id=" + id + "]";
	}
	
	
	
	
	
	
	
	
	
	

}