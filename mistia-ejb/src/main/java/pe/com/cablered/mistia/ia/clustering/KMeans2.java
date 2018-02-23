package pe.com.cablered.mistia.ia.clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pe.com.cablered.mistia.geometria.Geometria;
import pe.com.cablered.mistia.geometria.Punto;
import pe.com.cablered.mistia.util.Util;

public class KMeans2 {

	//Number of Clusters. This metric should be related to the number of points
    public int NUM_CLUSTERS = 3;    
    //Number of Points
    private int NUM_POINTS = 15;
    //Min and Max X and Y
    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 10;
    
    private List<Point> points;
    private List<Cluster> clusters;
    
    public KMeans2() {
    	this.points = new ArrayList();
    	this.clusters = new ArrayList();    	
    }
    
   
    public List<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}



	public List<Point> getPoints() {
		return points;
	}


	public void setPoints(List<Point> points) {
		this.points = points;
	}


	public static void main(String[] args) {
    	
    	KMeans2 kmeans = new KMeans2();
    	
    	kmeans.testInitKpp();
    
    	//kmeans.init();
    	kmeans.calculate();
    }
    
	
	
	public void startkpp( int cantcluster){
		
		points = getPointList();
		clusters =  cretateInitClusters(cantcluster);		
		clusters = kpp(points, clusters);
		NUM_CLUSTERS = clusters.size();
		
	}
	
	public void testInitKpp(){
	
		
		points = getPointList();
		clusters =  cretateInitClusters(90);		
		clusters = kpp(points, clusters);
	
		NUM_CLUSTERS = clusters.size();
		
		/*
		 *
		apache 
		NUM_CLUSTERS = 3;
		clusters = kppApache(points, 3, new Random());
		*/
		
		
		/*clusters.add(new Cluster(1,  new Point(-12.044396,-75.195859)));
		clusters.add(new Cluster(1,  new Point(-12.012496,-75.175688)));
		clusters.add(new Cluster(1,  new Point(-12.075517,-75.229887)));
		*/
		
		/*
		 
		[Cluster: 0]
		[Centroid: (-12.044396,-75.195859)]
		[Points: 
		
		]
		[Cluster: 0]
		[Centroid: (-12.012496,-75.175688)]
		[Points: 
		
		]
		[Cluster: 0]
		[Centroid: (-12.075517,-75.229887)]
		[Points: 
		  
		 * */
		
		
		
		
		
		
		
		int i =   0; 
		for (Cluster cluster : clusters) {
			cluster.plotCluster();
			cluster.setId(i);
			i++;
		}
		
		
		
		System.out.println(" ### cant de nodos #####");
		for (Cluster cluster : clusters) {
			System.out.println("{latitude:"+cluster.getCentroid().getX()+", longitude:"+cluster.getCentroid().getY()+"},");
		}
		
		//plotClusters();

	}
	
	
	public static  List<Cluster> cretateInitClusters(int numberCluter){
		
		List<Cluster> clusters =  new ArrayList<>();
		for (int i = 0; i < numberCluter; i++) {
			clusters.add(new Cluster(i));
		}
		
		return clusters;
	}
	
	
	
    //Initializes the process
    public void init() {

  
    	//Create Points
    	points = getPointList();
    	
    	double costoPromedioCluster = 6.0;  
  
    	
		Double costototal = 0.0; 
		for (Point point : points) {
			costototal = costototal+point.getCost();
		}
		
		
		Double dcantcluster= costototal/costoPromedioCluster ;
		NUM_CLUSTERS = dcantcluster.intValue();
		NUM_CLUSTERS = 6;
	
		List<Integer> indexran = Util.getRandomNonRepeatingIntegers(NUM_CLUSTERS,0, points.size()-1);;
	    	
    	/*
    	for (Point p : points) {
    		System.out.println(p.getX()+" - "+p.getY());
		}*/
    	
    	//Create Clusters
    	//Set Random Centroids
    	for (int i = 0; i < NUM_CLUSTERS; i++) {
    		Cluster cluster = new Cluster(i);
    		//Point centroid = Point.createRandomPoint(MIN_COORDINATE,MAX_COORDINATE);
    		Point centroid = points.get(indexran.get(i));
    		cluster.setCentroid(centroid);
    		clusters.add(cluster);
    	}
    	
    	//Print Initial state
    	plotClusters();
    }
    
    
    
    
    
    
    
    

	private void plotClusters() {
    	for (int i = 0; i < NUM_CLUSTERS; i++) {
    		Cluster c = clusters.get(i);
    		c.plotCluster();
    	}
    }
    
	//The process to calculate the K Means, with iterating method.
    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        
        // Add in new data, one at a time, recalculating centroids with each new one. 
        while(!finish) {
        	//Clear cluster state
        	clearClusters();
        	
        	List<Point> lastCentroids = getCentroids();
        	
        	//Assign points to the closer cluster
        	assignCluster();
            
            //Calculate new centroids.
        	//calculateCentroids();
        	calculateCentroidsEarth();
        	iteration++;
        	
        	List<Point> currentCentroids = getCentroids();
        	
        	//Calculates total distance between new and old Centroids
        	double distance = 0.0;
        	for(int i = 0; i < lastCentroids.size(); i++) {
        		distance += Point.distance(lastCentroids.get(i),currentCentroids.get(i));
        	}
        	System.out.println("#################");
        	System.out.println("Iteration: " + iteration);
        	System.out.println("Centroid distances: " + distance);
        	plotClusters();
        	        	
        	if(distance == 0) {
        		finish = true;
        	}
        }
    }
    
    private void clearClusters() {
    	for(Cluster cluster : clusters) {
    		cluster.clear();
    	}
    }
    
    private List<Point> getCentroids() {
    	List<Point> centroids = new ArrayList(NUM_CLUSTERS);
    	for(Cluster cluster : clusters) {
    		Point aux = cluster.getCentroid();
    		Point point = new Point(aux.getX(),aux.getY());
    		centroids.add(point);
    	}
    	return centroids;
    }
    
    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max; 
        int cluster = 0;                 
        double distance = 0.0; 
        
        for(Point point : points) {
        	min = max;
            for(int i = 0; i < NUM_CLUSTERS; i++) {
            	Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            point.setCluster(cluster);
            clusters.get(cluster).addPoint(point);
        }
    }
    
    // cluster mas cercano al punto
    private Cluster    nearestClusterCenter(Point point, List<Cluster> clusters ){

    	Cluster cluster = null;
    	double mindist  = Double.MAX_VALUE;
    	double distance = 0.0;    
    	// solo se evalua hasta la lista 
    	for(Cluster c : clusters) {
    		 distance = Point.distance(point, c.getCentroid());
    		 if(distance < mindist){
    			 mindist = distance;
    			 cluster = c;
    			 break;
             }
         }
    	return cluster;
    }
    
    
    private List<Cluster>  kpp( List<Point> points, List<Cluster> clusters){
    	
    	Random random =  new Random();
    	// eleigimos un punto al azar y lo establecemos como centroide inicial
    	int idx =  random.nextInt(points.size());
    	clusters.get(0).setCentroid(points.get(idx));
    	System.out.println(" punto seleccionado "+points.get(idx));
    	// iteramos cluster vs puntos 
    	int ic =  0;
    	for (Cluster _cluster : clusters) {
    		
    		int i = 0;
    		double suma  = 0.0;
    		double[] dist =  new double[points.size()];
    		for (Point point : points) {
    			List<Cluster> sublist =  new ArrayList<>();
    			sublist  =  clusters.subList(0, ic+1);
    			Cluster c = nearestClusterCenter(point, sublist);
    			dist[i] =  Point.distance(point, c.getCentroid());
    			suma+=dist[i];
    			i++;
			}
    		
    		suma*=random.nextDouble();
    		i = 0;
    		for (double d : dist) {
    			suma -=d;
    			if(!(suma > 0)){
    				System.out.println(" añadiendo punto "+points.get(i));
    				_cluster.setCentroid( points.get(i));
        			break;
    			}
    			i++;
			}
    		ic++;
		}
    	
  
    	for (Point point : points) {
    		Cluster c =  	nearestClusterCenter(point, clusters);
    		int i = clusters.indexOf(c);
    		if(i!= -1){
    			point.setCluster(i);
    		}
		}
    	
    	return clusters;
    }
    
    
    private List<Cluster>  kppApache( List<Point> points, int k, Random random ){
    	

    	final List<Point> pointSet = new ArrayList(points);
        final List<Cluster> resultSet = new ArrayList<>();
        // Choose one center uniformly at random from among the data points.
    			
    	int idx =  random.nextInt(pointSet.size());		
        final Point firstPoint = pointSet.remove(random.nextInt(pointSet.size()));
        
        System.out.println(" primer punto : "+firstPoint.toString());
       /* Cluster c1 =  new Cluster(0);
        c1.setCentroid(firstPoint);*/

        resultSet.add(new Cluster(firstPoint));

        final double[] dx2 = new double[pointSet.size()];
        while (resultSet.size() < k) {
            // For each data point x, compute D(x), the distance between x and
            // the nearest center that has already been chosen.
            int sum = 0;
            for (int i = 0; i < pointSet.size(); i++) {
                final Point p = pointSet.get(i);
                final Cluster nearest = nearestClusterCenter(p, resultSet);
                final double d = Point.distance(p, nearest.getCentroid());
                sum += d * d;
                dx2[i] = sum;
            }

            // Add one new data point as a center. Each point x is chosen with
            // probability proportional to D(x)2
            final double r = random.nextDouble() * sum;
            for (int i = 0 ; i < dx2.length; i++) {
                if (dx2[i] >= r) {
                    final Point p = pointSet.remove(i);
                    
                    System.out.println(" añadiendo punto "+  p);
                    resultSet.add(new Cluster(p));
                    break;
                }
            }
        }

        return resultSet;
    
    }
    	
  /*  public List<Cluster> cluster(final List points, final int k, final int maxIterations) {
    	
    	
    				Random random = new Random();
					// create the initial clusters
					List<Cluster> clusters = kppApache(points, k, random);
					assignPointsToClusters(clusters, points);
					
					// iterate through updating the centers until we're done
					final int max = (maxIterations < 0) ? Integer.MAX_VALUE : maxIterations;
					for (int count = 0; count < max; count++) {
						boolean clusteringChanged = false;
						List<Cluster> newClusters = new ArrayList<>();
						for (final Cluster cluster : clusters) {
							
							Point newCenter = cluster.getCenter().centroidOf(cluster.getPoints());
							
							if (!newCenter.equals(cluster.getCentroid())) {
								clusteringChanged = true;
							}
							newClusters.add(new Cluster(newCenter));
							
						}
						
						if (!clusteringChanged) {
							return clusters;
						}
						assignPointsToClusters(newClusters, points);
						clusters = newClusters;
					}
					return clusters;

    }*/

    
    private static  void    assignPointsToClusters(final List<Cluster> clusters, final List<Point> points) {
	    for (final Point p : points) {
	        Cluster cluster = getNearestCluster(clusters, p);
	        cluster.addPoint(p);
	    }
	}
	    
    
    
    private static  Cluster   getNearestCluster(final List<Cluster> clusters, final Point point ) {
	    double minDistance = Double.MAX_VALUE;
	    Cluster minCluster = null;
	    for (final Cluster  c : clusters) {
	        //final double distance = point.distanceFrom(c.getCentroid());
	    	final double distance = Point.distance(point, c.getCentroid());
	        if (distance < minDistance) {
	            minDistance = distance;
	            minCluster = c;
	        }
	    }
	    return minCluster;
    }
    
    

    
    
    
    
    
    private void calculateCentroids() {
        for(Cluster cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            List<Point> list = cluster.getPoints();
            int n_points = list.size();
            
            for(Point point : list) {
            	sumX += point.getX();
                sumY += point.getY();
            }
            
            Point centroid = cluster.getCentroid();
            if(n_points > 0) {
            	double newX = sumX / n_points;
            	double newY = sumY / n_points;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }
    
    
    private void calculateCentroidsEarth() {
    	
    	
        for(Cluster cluster : clusters) {
        	
            double sumX = 0;
            double sumY = 0;
            List<Point> list = cluster.getPoints();
            int n_points = list.size();
            
            
            Punto[] puntos =   new Punto[list.size()];
            int i = 0;
            for(Point point : list) {
            	puntos[i] =  new Punto(point.getX(), point.getY());
            	i ++;
            }
            
            
            Punto  punto = 	Geometria.puntoCentral(puntos);
            if(punto!=null){
            	cluster.setCentroid(new Point(punto.getLatitud(), punto.getLongitud()));
            }
                        
        }

    }
    
    
    
    
    
    public static List<Point> getPointList(){
    	
    	List<Point>  points =  new ArrayList<Point>();
    	
    	points.add(new Point(-12.06086,-75.18496));  
    	points.add(new Point(-12.07080,-75.22153));  
    	points.add(new Point(-12.05262,-75.18302));  
    	points.add(new Point(-12.07749,-75.21118));  
    	points.add(new Point(-12.04302,-75.19827));  
    	points.add(new Point(-12.01644,-75.18178));  
    	points.add(new Point(-12.07060,-75.20597));  
    	points.add(new Point(-12.06363,-75.19241));  
    	points.add(new Point(-12.01964,-75.18563));  
    	points.add(new Point(-12.07040,-75.19823));  
    	points.add(new Point(-12.03132,-75.19123));  
    	points.add(new Point(-12.03481,-75.19935));  
    	points.add(new Point(-12.08218,-75.21917));  
    	points.add(new Point(-12.06856,-75.20342));  
    	points.add(new Point(-12.01853,-75.17817));  
    	points.add(new Point(-12.03759,-75.19056));  
    	points.add(new Point(-12.04146,-75.19351));  
    	points.add(new Point(-12.03867,-75.19572));  
    	points.add(new Point(-12.06021,-75.18771));  
    	points.add(new Point(-12.02718,-75.18663));  
    	points.add(new Point(-12.05158,-75.18152));  
    	points.add(new Point(-12.04409,-75.19344));  
    	points.add(new Point(-12.05993,-75.18059));  
    	points.add(new Point(-12.03458,-75.19284));  
    	points.add(new Point(-12.06208,-75.20820));  
    	points.add(new Point(-12.06028,-75.18947));  
    	points.add(new Point(-12.04800,-75.18124));  
    	points.add(new Point(-12.01711,-75.18221));  
    	points.add(new Point(-12.02232,-75.18918));  
    	points.add(new Point(-12.03343,-75.18549));  
    	points.add(new Point(-12.06735,-75.21447));  
    	points.add(new Point(-12.07254,-75.20159));  
    	points.add(new Point(-12.06247,-75.20016));  
    	points.add(new Point(-12.03900,-75.19640));  
    	points.add(new Point(-12.01749,-75.17530));  
    	points.add(new Point(-12.05488,-75.19762));  
    	points.add(new Point(-12.04222,-75.18578));  
    	points.add(new Point(-12.02148,-75.18616));  
    	points.add(new Point(-12.06242,-75.19687));  
    	points.add(new Point(-12.06273,-75.20697));  
    	points.add(new Point(-12.06933,-75.21364));  
    	points.add(new Point(-12.06955,-75.20868));  
    	points.add(new Point(-12.07100,-75.21264));  
    	points.add(new Point(-12.04783,-75.18763));  
    	points.add(new Point(-12.04453,-75.19856));  
    	points.add(new Point(-12.06391,-75.18776));  
    	points.add(new Point(-12.02127,-75.18300));  
    	points.add(new Point(-12.06732,-75.21030));  
    	points.add(new Point(-12.03469,-75.18991));  
    	points.add(new Point(-12.03246,-75.18578));  
    	points.add(new Point(-12.04652,-75.20226));  
    	points.add(new Point(-12.04728,-75.18750));  
    	points.add(new Point(-12.04717,-75.18342));  
    	points.add(new Point(-12.08183,-75.22988));  
    	points.add(new Point(-12.08050,-75.21916));  
    	points.add(new Point(-12.01512,-75.18058));  
    	points.add(new Point(-12.07242,-75.21295));  
    	points.add(new Point(-12.07836,-75.21827));  
    	points.add(new Point(-12.07710,-75.21853));  
    	points.add(new Point(-12.03569,-75.18823));  
    	points.add(new Point(-12.04221,-75.19017));  
    	points.add(new Point(-12.05372,-75.20184));  
    	points.add(new Point(-12.04162,-75.19085));  
    	points.add(new Point(-12.01835,-75.18810));  
    	points.add(new Point(-12.02635,-75.18013));  
    	points.add(new Point(-12.07587,-75.20849));  
    	points.add(new Point(-12.06121,-75.20987));  
    	points.add(new Point(-12.05440,-75.18266));  
    	points.add(new Point(-12.05072,-75.19062));  
    	points.add(new Point(-12.02797,-75.18552));  
    	points.add(new Point(-12.07086,-75.21332));  
    	points.add(new Point(-12.03257,-75.19017));  
    	points.add(new Point(-12.06211,-75.18462));  
    	points.add(new Point(-12.07100,-75.22089));  
    	points.add(new Point(-12.07627,-75.20988));  
    	points.add(new Point(-12.08258,-75.22892));  
    	points.add(new Point(-12.05399,-75.18908));  
    	points.add(new Point(-12.05501,-75.20464));  
    	points.add(new Point(-12.02846,-75.18367));  
    	points.add(new Point(-12.04652,-75.19405));  
    	points.add(new Point(-12.05462,-75.18565));  
    	points.add(new Point(-12.08159,-75.22826));  
    	points.add(new Point(-12.06690,-75.20112));  
    	points.add(new Point(-12.06204,-75.18645));  
    	points.add(new Point(-12.02079,-75.18386));  
    	points.add(new Point(-12.06611,-75.21003));  
    	points.add(new Point(-12.07542,-75.21130));  
    	points.add(new Point(-12.03493,-75.19626));  
    	points.add(new Point(-12.07708,-75.22351));  
    	points.add(new Point(-12.05548,-75.20159));  
    	points.add(new Point(-12.04872,-75.20193));  
    	points.add(new Point(-12.08119,-75.22880));  
    	points.add(new Point(-12.05985,-75.19137));  
    	points.add(new Point(-12.04735,-75.18310));  
    	points.add(new Point(-12.06162,-75.18442));  
    	points.add(new Point(-12.08435,-75.22988));  
    	points.add(new Point(-12.02982,-75.18939));  
    	points.add(new Point(-12.01990,-75.18183));  
    	points.add(new Point(-12.04883,-75.19467));  
    	points.add(new Point(-12.02726,-75.18268));  
    	points.add(new Point(-12.07368,-75.21738));  
    	points.add(new Point(-12.07674,-75.21473));  
    	points.add(new Point(-12.06892,-75.19803));  
    	points.add(new Point(-12.03694,-75.18961));  
    	points.add(new Point(-12.07416,-75.21651));  
    	points.add(new Point(-12.05655,-75.19657));  
    	points.add(new Point(-12.06512,-75.20627));  
    	points.add(new Point(-12.06372,-75.20900));  
    	points.add(new Point(-12.02108,-75.18475));  
    	points.add(new Point(-12.03073,-75.19360));  
    	points.add(new Point(-12.08177,-75.21968));  
    	points.add(new Point(-12.07927,-75.23135));  
    	points.add(new Point(-12.03769,-75.18599));  
    	points.add(new Point(-12.04789,-75.19255));  
    	points.add(new Point(-12.03518,-75.18987));  
    	points.add(new Point(-12.07503,-75.22128));  
    	points.add(new Point(-12.01488,-75.18331));  
    	points.add(new Point(-12.01948,-75.18568));  
    	points.add(new Point(-12.02370,-75.17692));  
    	points.add(new Point(-12.03334,-75.18825));  
    	points.add(new Point(-12.07648,-75.22772));  
    	points.add(new Point(-12.02890,-75.18547));  
    	points.add(new Point(-12.07162,-75.21609));  
    	points.add(new Point(-12.02473,-75.19332));  
    	points.add(new Point(-12.03616,-75.18679));  
    	points.add(new Point(-12.02494,-75.19334));  
    	points.add(new Point(-12.02860,-75.19086));  
    	points.add(new Point(-12.08016,-75.21634));  
    	points.add(new Point(-12.03400,-75.18423));  
    	points.add(new Point(-12.02371,-75.18343));  
    	points.add(new Point(-12.06687,-75.19507));  
    	points.add(new Point(-12.05855,-75.20681));  
    	points.add(new Point(-12.07028,-75.20301));  
    	points.add(new Point(-12.07143,-75.22050));  
    	points.add(new Point(-12.06533,-75.18987));  
    	points.add(new Point(-12.06843,-75.19198));  
    	points.add(new Point(-12.07630,-75.23004));  
    	points.add(new Point(-12.03233,-75.18715));  
    	points.add(new Point(-12.06286,-75.18342));  
    	points.add(new Point(-12.07203,-75.20063));  
    	points.add(new Point(-12.02500,-75.18593));  
    	points.add(new Point(-12.07019,-75.21552));  
    	points.add(new Point(-12.03613,-75.19897));  
    	points.add(new Point(-12.04282,-75.19329));  
    	points.add(new Point(-12.05743,-75.18757));  
    	points.add(new Point(-12.03935,-75.18671));  
    	points.add(new Point(-12.05789,-75.19835));  
    	points.add(new Point(-12.03571,-75.19743));  
    	points.add(new Point(-12.03341,-75.18801));  
    	points.add(new Point(-12.05217,-75.20460));  
    	points.add(new Point(-12.07583,-75.20648));  
    	points.add(new Point(-12.01426,-75.17857));  
    	points.add(new Point(-12.05598,-75.18494));  
    	points.add(new Point(-12.06964,-75.19768));  
    	points.add(new Point(-12.07391,-75.20538));  
    	points.add(new Point(-12.06367,-75.19982));  
    	points.add(new Point(-12.02015,-75.17963));  
    	points.add(new Point(-12.05476,-75.18163));  
    	points.add(new Point(-12.07254,-75.22533));  
    	points.add(new Point(-12.04861,-75.19725));  
    	points.add(new Point(-12.05902,-75.19259));  
    	points.add(new Point(-12.07400,-75.21468));  
    	points.add(new Point(-12.05226,-75.18438));  
    	points.add(new Point(-12.06906,-75.21575));  
    	points.add(new Point(-12.03902,-75.18897));  
    	points.add(new Point(-12.08089,-75.22970));  
    	points.add(new Point(-12.05151,-75.18233));  
    	points.add(new Point(-12.03985,-75.19301));  
    	points.add(new Point(-12.08063,-75.22229));  
    	points.add(new Point(-12.04181,-75.18686));  
    	points.add(new Point(-12.01841,-75.17827));  
    	points.add(new Point(-12.06589,-75.20803));  
    	points.add(new Point(-12.08167,-75.22453));  
    	points.add(new Point(-12.02964,-75.18441));  
    	points.add(new Point(-12.05181,-75.18159));  
    	points.add(new Point(-12.05584,-75.18170));  
    	points.add(new Point(-12.07650,-75.21301));  
    	points.add(new Point(-12.03661,-75.18874));  
    	points.add(new Point(-12.02670,-75.18504));  
    	points.add(new Point(-12.05989,-75.19439));  
    	points.add(new Point(-12.06104,-75.20959));  
    	points.add(new Point(-12.03896,-75.18798));  
    	points.add(new Point(-12.01986,-75.18633));  
    	points.add(new Point(-12.07763,-75.23138));  
    	points.add(new Point(-12.04087,-75.19507));  
    	points.add(new Point(-12.08044,-75.22986));  
    	points.add(new Point(-12.07795,-75.22308));  
    	points.add(new Point(-12.07589,-75.20806));  
    	points.add(new Point(-12.02284,-75.18391));  
    	points.add(new Point(-12.02192,-75.18822));  
    	points.add(new Point(-12.06930,-75.21595));  
    	points.add(new Point(-12.03874,-75.19141));  
    	points.add(new Point(-12.07848,-75.22410));  
    	points.add(new Point(-12.06320,-75.19559));  
    	points.add(new Point(-12.07437,-75.22053));  
    	points.add(new Point(-12.05167,-75.20048));  
    	points.add(new Point(-12.06979,-75.20596));  
    	points.add(new Point(-12.06451,-75.20655));  
    	points.add(new Point(-12.02528,-75.17783));  
    	points.add(new Point(-12.02983,-75.19150));  
    	points.add(new Point(-12.05808,-75.20279));  
    	points.add(new Point(-12.03138,-75.18752));  
    	points.add(new Point(-12.04307,-75.19356));  
    	points.add(new Point(-12.03082,-75.18619));  
    	points.add(new Point(-12.01712,-75.17866));  
    	points.add(new Point(-12.03643,-75.18509));  
    	points.add(new Point(-12.01598,-75.18061));  
    	points.add(new Point(-12.01849,-75.18756));  
    	points.add(new Point(-12.06702,-75.21339));  
    	points.add(new Point(-12.01564,-75.18088));  
    	points.add(new Point(-12.02203,-75.18407));  
    	points.add(new Point(-12.04034,-75.18036));  
    	points.add(new Point(-12.05853,-75.19414));  
    	points.add(new Point(-12.08081,-75.21835));  
    	points.add(new Point(-12.02578,-75.18138));  
    	points.add(new Point(-12.01666,-75.17796));  
    	points.add(new Point(-12.04625,-75.18680));  
    	points.add(new Point(-12.04353,-75.18602));  
    	points.add(new Point(-12.01853,-75.17601));  
    	points.add(new Point(-12.07339,-75.22805));  
    	points.add(new Point(-12.05772,-75.18614));  
    	points.add(new Point(-12.08184,-75.22479));  
    	points.add(new Point(-12.02823,-75.19380));  
    	points.add(new Point(-12.04551,-75.18244));  
    	points.add(new Point(-12.03049,-75.18559));  
    	points.add(new Point(-12.06060,-75.20154));  
    	points.add(new Point(-12.02871,-75.19629));  
    	points.add(new Point(-12.05999,-75.20405));  
    	points.add(new Point(-12.03288,-75.19160));  
    	points.add(new Point(-12.05211,-75.19660));  
    	points.add(new Point(-12.06733,-75.19131));  
    	points.add(new Point(-12.02810,-75.19090));  
    	points.add(new Point(-12.07893,-75.21981));  
    	points.add(new Point(-12.06460,-75.18811));  
    	points.add(new Point(-12.08044,-75.22305));  
    	points.add(new Point(-12.01762,-75.17604));  
    	points.add(new Point(-12.07336,-75.23267));  
    	points.add(new Point(-12.07919,-75.21532));  
    	points.add(new Point(-12.07703,-75.21963));  
    	points.add(new Point(-12.08314,-75.22803));  
    	points.add(new Point(-12.05770,-75.20540));  
    	points.add(new Point(-12.03689,-75.19062));  
    	points.add(new Point(-12.04963,-75.18060));  
    	points.add(new Point(-12.03988,-75.18764));  
    	points.add(new Point(-12.02916,-75.18720));  
    	points.add(new Point(-12.07043,-75.21067));  
    	points.add(new Point(-12.05004,-75.19101));  
    	points.add(new Point(-12.08030,-75.21692));  
    	points.add(new Point(-12.08032,-75.23048));  
    	points.add(new Point(-12.05645,-75.20156));  
    	points.add(new Point(-12.07742,-75.22714));  
    	points.add(new Point(-12.04569,-75.18855));  
    	points.add(new Point(-12.03210,-75.18730));  
    	points.add(new Point(-12.06075,-75.20718));  
    	points.add(new Point(-12.07509,-75.21161));  
    	points.add(new Point(-12.04254,-75.19248));  
    	points.add(new Point(-12.02695,-75.18472));  
    	points.add(new Point(-12.07194,-75.22168));  
    	points.add(new Point(-12.06631,-75.18944));  
    	points.add(new Point(-12.02888,-75.18722));  
    	points.add(new Point(-12.02959,-75.19281));  
    	points.add(new Point(-12.05997,-75.18391));  
    	points.add(new Point(-12.01693,-75.18005));  
    	points.add(new Point(-12.02890,-75.19641));  
    	points.add(new Point(-12.06368,-75.19409));  
    	points.add(new Point(-12.07688,-75.21754));  
    	points.add(new Point(-12.07495,-75.21340));  
    	points.add(new Point(-12.08082,-75.21836));  
    	points.add(new Point(-12.02592,-75.17876));  
    	points.add(new Point(-12.04351,-75.19360));  
    	points.add(new Point(-12.03437,-75.18323));  
    	points.add(new Point(-12.07395,-75.22904));  
    	points.add(new Point(-12.07225,-75.21653));  
    	points.add(new Point(-12.05056,-75.19964));  
    	points.add(new Point(-12.03994,-75.19899));  
    	points.add(new Point(-12.02693,-75.18755));  
    	points.add(new Point(-12.07054,-75.21162));  
    	points.add(new Point(-12.05762,-75.20590));  
    	points.add(new Point(-12.06307,-75.20592));  
    	points.add(new Point(-12.06157,-75.20155));  
    	points.add(new Point(-12.04125,-75.19062));  
    	points.add(new Point(-12.03619,-75.18702));  
    	points.add(new Point(-12.03663,-75.19148));  
    	points.add(new Point(-12.04858,-75.19022));  
    	points.add(new Point(-12.07420,-75.21726));  
    	points.add(new Point(-12.08011,-75.21683));  
    	points.add(new Point(-12.06508,-75.19937));  
    	points.add(new Point(-12.03569,-75.19779));  
    	points.add(new Point(-12.07609,-75.23074));  
    	points.add(new Point(-12.03084,-75.18616));  
    	points.add(new Point(-12.07379,-75.20722));  
    	points.add(new Point(-12.02430,-75.18635));  
    	points.add(new Point(-12.07132,-75.22381));  
    	points.add(new Point(-12.03682,-75.18784));  
    	points.add(new Point(-12.02160,-75.18799));  
    	points.add(new Point(-12.01689,-75.17387));  
    	points.add(new Point(-12.07143,-75.21777));  
    	points.add(new Point(-12.06702,-75.20816));  
    	points.add(new Point(-12.07851,-75.22203));  
    	points.add(new Point(-12.05272,-75.19301));  
    	points.add(new Point(-12.06333,-75.20225));  
    	points.add(new Point(-12.08085,-75.23153));  
    	points.add(new Point(-12.02172,-75.18904));  
    	points.add(new Point(-12.07354,-75.23009));  
    	points.add(new Point(-12.04401,-75.18180));  
    	points.add(new Point(-12.06875,-75.19307));  
    	points.add(new Point(-12.06567,-75.21237));  
    	points.add(new Point(-12.06617,-75.19826));  
    	points.add(new Point(-12.06985,-75.21595));  
    	points.add(new Point(-12.05994,-75.19356));  
    	points.add(new Point(-12.05442,-75.19465));  
    	points.add(new Point(-12.07030,-75.21306));  
    	points.add(new Point(-12.05601,-75.20046));  
    	points.add(new Point(-12.01795,-75.18264));  
    	points.add(new Point(-12.06475,-75.18803));  
    	points.add(new Point(-12.06490,-75.21264));  
    	points.add(new Point(-12.04798,-75.19773));  
    	points.add(new Point(-12.08523,-75.22573));  
    	points.add(new Point(-12.07476,-75.20992));  
    	points.add(new Point(-12.06130,-75.18579));  
    	points.add(new Point(-12.03958,-75.19749));  
    	points.add(new Point(-12.05704,-75.20223));  
    	points.add(new Point(-12.06256,-75.21028));  
    	points.add(new Point(-12.04880,-75.19208));  
    	points.add(new Point(-12.07225,-75.20257));  
    	points.add(new Point(-12.02992,-75.18360));  
    	points.add(new Point(-12.03134,-75.19676));  
    	points.add(new Point(-12.04598,-75.20162));  
    	points.add(new Point(-12.06619,-75.20290));  
    	points.add(new Point(-12.03981,-75.19280));  
    	points.add(new Point(-12.06151,-75.19780));  
    	points.add(new Point(-12.07067,-75.19699));  
    	points.add(new Point(-12.07671,-75.23081));  
    	points.add(new Point(-12.04300,-75.19989));  
    	points.add(new Point(-12.04266,-75.19187));  
    	points.add(new Point(-12.01807,-75.18749));  
    	points.add(new Point(-12.06121,-75.18291));  
    	points.add(new Point(-12.07252,-75.23335));  
    	points.add(new Point(-12.08220,-75.23153));  
    	points.add(new Point(-12.05131,-75.19714));  
    	points.add(new Point(-12.05252,-75.19564));  
    	points.add(new Point(-12.08209,-75.23020));  
    	points.add(new Point(-12.06737,-75.20420));  
    	points.add(new Point(-12.02795,-75.18910));  
    	points.add(new Point(-12.01611,-75.18429));  
    	points.add(new Point(-12.04841,-75.18533));  
    	points.add(new Point(-12.07280,-75.20267));  
    	points.add(new Point(-12.04058,-75.19861));  
    	points.add(new Point(-12.02656,-75.18424));  
    	points.add(new Point(-12.06100,-75.20910));  
    	points.add(new Point(-12.05805,-75.19572));  
    	points.add(new Point(-12.03600,-75.19522));  
    	points.add(new Point(-12.07351,-75.20695));  
    	points.add(new Point(-12.03041,-75.18960));  
    	points.add(new Point(-12.05145,-75.19897));  
    	points.add(new Point(-12.03873,-75.18276));  
    	points.add(new Point(-12.05516,-75.18686));  
    	points.add(new Point(-12.05119,-75.19957));  
    	points.add(new Point(-12.07778,-75.20964));  
    	points.add(new Point(-12.05269,-75.20387));  
    	points.add(new Point(-12.03329,-75.19137));  
    	points.add(new Point(-12.08182,-75.22558));  
    	points.add(new Point(-12.02401,-75.17727));  
    	points.add(new Point(-12.03609,-75.19788));  
    	points.add(new Point(-12.06282,-75.19630));  
    	points.add(new Point(-12.05433,-75.19282));  
    	points.add(new Point(-12.04995,-75.19688));  
    	points.add(new Point(-12.03147,-75.18718));  
    	points.add(new Point(-12.05644,-75.19107));  
    	points.add(new Point(-12.04686,-75.19999));  
    	points.add(new Point(-12.04926,-75.19569));  
    	points.add(new Point(-12.04395,-75.19197));  
    	points.add(new Point(-12.05778,-75.18904));  
    	points.add(new Point(-12.02770,-75.19073));  
    	points.add(new Point(-12.03418,-75.18451));  
    	points.add(new Point(-12.02111,-75.18628));  
    	points.add(new Point(-12.05595,-75.18074));  
    	points.add(new Point(-12.04832,-75.20135));  
    	points.add(new Point(-12.07257,-75.20697));  
    	points.add(new Point(-12.07134,-75.22017));  
    	points.add(new Point(-12.04198,-75.19230));  
    	points.add(new Point(-12.03509,-75.19062));  
    	points.add(new Point(-12.06726,-75.20019));  
    	points.add(new Point(-12.07702,-75.21485));  
    	points.add(new Point(-12.07574,-75.22392));  
    	points.add(new Point(-12.07672,-75.21644));  
    	points.add(new Point(-12.04269,-75.19222));  
    	points.add(new Point(-12.04278,-75.19462));  
    	points.add(new Point(-12.05065,-75.19923));  
    	points.add(new Point(-12.04330,-75.18341));  
    	points.add(new Point(-12.06496,-75.20280));  
    	points.add(new Point(-12.02584,-75.18615));  
    	points.add(new Point(-12.06125,-75.20451));  
    	points.add(new Point(-12.03495,-75.18574));  
    	points.add(new Point(-12.06583,-75.21434));  
    	points.add(new Point(-12.04642,-75.18668));  
    	points.add(new Point(-12.06398,-75.20919));  
    	points.add(new Point(-12.03135,-75.18542));  
    	points.add(new Point(-12.05547,-75.20214));  
    	points.add(new Point(-12.02383,-75.18055));  
    	points.add(new Point(-12.02705,-75.18909));  
    	points.add(new Point(-12.03687,-75.19209));  
    	points.add(new Point(-12.06948,-75.21032));  
    	points.add(new Point(-12.07979,-75.21799));  
    	points.add(new Point(-12.05917,-75.18205));  
    	points.add(new Point(-12.06443,-75.18795));  
    	points.add(new Point(-12.05938,-75.18940));  
    	points.add(new Point(-12.04774,-75.18606));  
    	points.add(new Point(-12.06568,-75.20979));  
    	points.add(new Point(-12.07610,-75.23112));  
    	points.add(new Point(-12.04277,-75.18132));  
    	points.add(new Point(-12.05750,-75.20398));  
    	points.add(new Point(-12.07200,-75.21803));  
    	points.add(new Point(-12.03453,-75.19153));  
    	points.add(new Point(-12.04183,-75.18260));  
    	points.add(new Point(-12.05209,-75.19660));  
    	points.add(new Point(-12.02910,-75.18267));  
    	points.add(new Point(-12.06483,-75.20731));  
    	points.add(new Point(-12.05814,-75.20614));  
    	points.add(new Point(-12.05550,-75.20209));  
    	points.add(new Point(-12.06698,-75.19945));  
    	points.add(new Point(-12.02518,-75.17978));  
    	points.add(new Point(-12.04434,-75.19813));  
    	points.add(new Point(-12.02387,-75.19106));  
    	points.add(new Point(-12.05760,-75.20206));  
    	points.add(new Point(-12.06820,-75.19886));  
    	points.add(new Point(-12.07527,-75.21562));  
    	points.add(new Point(-12.04572,-75.19724));  
    	points.add(new Point(-12.08023,-75.22001));  
    	points.add(new Point(-12.05198,-75.18704));  
    	points.add(new Point(-12.05045,-75.19517));  
    	points.add(new Point(-12.07935,-75.22898));  
    	points.add(new Point(-12.05051,-75.18622));  
    	points.add(new Point(-12.04756,-75.19902));  
    	points.add(new Point(-12.04769,-75.19122));  
    	points.add(new Point(-12.02996,-75.19159));  
    	points.add(new Point(-12.07348,-75.20760));  
    	points.add(new Point(-12.07316,-75.22333));  
    	points.add(new Point(-12.02756,-75.19217));  
    	points.add(new Point(-12.07327,-75.20664));  
    	points.add(new Point(-12.07127,-75.20448));  
    	points.add(new Point(-12.02629,-75.18950));  
    	points.add(new Point(-12.07671,-75.21482));  
    	points.add(new Point(-12.04572,-75.18985));  
    	points.add(new Point(-12.07233,-75.22243));  
    	points.add(new Point(-12.05829,-75.20044));  
    	points.add(new Point(-12.07503,-75.20497));  
    	points.add(new Point(-12.02567,-75.17923));  
    	points.add(new Point(-12.01831,-75.18808));  
    	points.add(new Point(-12.05848,-75.19699));  
    	points.add(new Point(-12.05649,-75.20105));  
    	points.add(new Point(-12.05371,-75.18836));  
    	points.add(new Point(-12.02690,-75.19146));  
    	points.add(new Point(-12.05715,-75.19042));  
    	points.add(new Point(-12.01658,-75.18281));  
    	points.add(new Point(-12.04455,-75.19449));  
    	points.add(new Point(-12.05842,-75.20139));  
    	points.add(new Point(-12.08464,-75.22630));  
    	points.add(new Point(-12.04605,-75.19317));  
    	points.add(new Point(-12.07309,-75.22782));  
    	points.add(new Point(-12.08207,-75.22919));  
    	points.add(new Point(-12.05552,-75.19997));  
    	points.add(new Point(-12.06092,-75.18613));  
    	points.add(new Point(-12.05718,-75.19265));  
    	points.add(new Point(-12.04424,-75.18784));  
    	points.add(new Point(-12.06969,-75.21122));  
    	points.add(new Point(-12.01484,-75.17880));  
    	points.add(new Point(-12.08246,-75.22149));  
    	points.add(new Point(-12.06745,-75.20328));  
    	points.add(new Point(-12.05404,-75.19897));  
    	points.add(new Point(-12.06246,-75.18451));  
    	points.add(new Point(-12.02158,-75.17503));  
    	points.add(new Point(-12.04605,-75.19949));  
    	points.add(new Point(-12.06546,-75.19598));  
    	points.add(new Point(-12.07501,-75.20336));  
    	points.add(new Point(-12.01910,-75.17849));  
    	points.add(new Point(-12.05595,-75.19925));  
    	points.add(new Point(-12.03333,-75.19429));  
    	points.add(new Point(-12.06775,-75.19276));  
    	points.add(new Point(-12.07846,-75.22853));  
    	points.add(new Point(-12.02486,-75.19345));  
    	points.add(new Point(-12.07632,-75.22529));  
    	points.add(new Point(-12.04624,-75.20096));  
    	points.add(new Point(-12.02356,-75.18663));  
    	points.add(new Point(-12.07350,-75.21270));  
    	points.add(new Point(-12.06039,-75.20429));  
    	points.add(new Point(-12.03863,-75.19699));  
    	points.add(new Point(-12.04823,-75.18650));  
    	points.add(new Point(-12.04290,-75.18779));  
    	points.add(new Point(-12.03450,-75.18290));  
    	points.add(new Point(-12.02880,-75.19299));  
    	points.add(new Point(-12.05877,-75.18708));  
    	points.add(new Point(-12.07654,-75.21241));  
    	points.add(new Point(-12.05625,-75.19360));  
    	points.add(new Point(-12.05812,-75.18059));  
    	points.add(new Point(-12.05877,-75.18564));  
    	points.add(new Point(-12.05952,-75.19921));  
    	points.add(new Point(-12.08032,-75.23043));  
    	points.add(new Point(-12.07141,-75.22015));  
    	points.add(new Point(-12.02698,-75.18510));  
    	points.add(new Point(-12.04881,-75.18111));  
    	points.add(new Point(-12.03437,-75.18588));  
    	points.add(new Point(-12.04091,-75.18895));  
    	points.add(new Point(-12.02926,-75.19560));  
    	points.add(new Point(-12.05491,-75.20269));  
    	points.add(new Point(-12.06623,-75.20062));  
    	points.add(new Point(-12.06335,-75.18892));  
    	points.add(new Point(-12.07022,-75.20706));  
    	points.add(new Point(-12.02041,-75.17504));  
    	points.add(new Point(-12.07749,-75.22061));  
    	points.add(new Point(-12.06875,-75.19656));  
    	points.add(new Point(-12.07199,-75.22397));  
    	points.add(new Point(-12.07488,-75.23151));  
    	points.add(new Point(-12.07312,-75.21238));  
    	points.add(new Point(-12.07217,-75.21490));  
    	points.add(new Point(-12.07355,-75.21913));  
    	points.add(new Point(-12.08256,-75.22439));  
    	points.add(new Point(-12.06487,-75.21219));  
    	points.add(new Point(-12.05316,-75.19323));  
    	points.add(new Point(-12.06097,-75.18157));  
    	points.add(new Point(-12.05837,-75.18838));  
    	points.add(new Point(-12.04897,-75.18845));  
    	points.add(new Point(-12.05255,-75.20098));  
    	points.add(new Point(-12.05801,-75.18591));  
    	points.add(new Point(-12.07268,-75.22357));  
    	points.add(new Point(-12.05156,-75.20253));  
    	points.add(new Point(-12.03184,-75.19420));  
    	points.add(new Point(-12.03467,-75.19793));  
    	points.add(new Point(-12.08035,-75.21557));  
    	points.add(new Point(-12.07168,-75.21832));  
    	points.add(new Point(-12.01914,-75.18838));  
    	points.add(new Point(-12.07600,-75.20928));  
    	points.add(new Point(-12.05372,-75.19485));  
    	points.add(new Point(-12.05285,-75.20472));  
    	points.add(new Point(-12.04682,-75.19689));  
    	points.add(new Point(-12.06249,-75.19924));  
    	points.add(new Point(-12.04363,-75.17979));  
    	points.add(new Point(-12.06532,-75.19803));  
    	points.add(new Point(-12.05636,-75.18509));  
    	points.add(new Point(-12.06330,-75.20117));  
    	points.add(new Point(-12.08108,-75.21694));  
    	points.add(new Point(-12.01557,-75.18188));  
    	points.add(new Point(-12.02509,-75.18482));  
    	points.add(new Point(-12.06518,-75.19662));  
    	points.add(new Point(-12.01416,-75.18249));  
    	points.add(new Point(-12.07634,-75.21621));  
    	points.add(new Point(-12.05346,-75.18604));  
    	points.add(new Point(-12.05911,-75.19985));  
    	points.add(new Point(-12.07087,-75.21329));  
    	points.add(new Point(-12.05870,-75.20239));  
    	points.add(new Point(-12.05472,-75.20055));  
    	points.add(new Point(-12.03583,-75.18927));  
    	points.add(new Point(-12.07879,-75.21295));  
    	points.add(new Point(-12.07353,-75.22415));  
    	points.add(new Point(-12.04305,-75.18903));  
    	points.add(new Point(-12.07317,-75.23252));  
    	points.add(new Point(-12.07843,-75.23145));  
    	points.add(new Point(-12.04334,-75.19010));  
    	points.add(new Point(-12.03180,-75.19298));  
    	points.add(new Point(-12.02298,-75.18406));  
    	points.add(new Point(-12.03421,-75.19204));
    	/*points.add(new Point(-12.010948, -75.175982));
    	points.add(new Point(-12.012496, -75.175688));
    	points.add(new Point(-12.037512, -75.183327));
    	points.add(new Point(-12.019968, -75.188048));
    	points.add(new Point(-12.037261, -75.184271));
    	points.add(new Point(-12.029538, -75.194314));
    	points.add(new Point(-12.022318, -75.190280));
    	points.add(new Point(-12.021647, -75.183585));
    	points.add(new Point(-12.041961, -75.184786));
    	points.add(new Point(-12.038016, -75.195000));
    	points.add(new Point(-12.030797, -75.194399));
    	points.add(new Point(-12.038100, -75.184100));
    	points.add(new Point(-12.037932, -75.193713));
    	points.add(new Point(-12.036337, -75.197060));
    	points.add(new Point(-12.037093, -75.194228));
    	points.add(new Point(-12.041542, -75.185816));
    	points.add(new Point(-12.037764, -75.181096));
    	points.add(new Point(-12.042801, -75.190108));
    	points.add(new Point(-12.043640, -75.184014));
    	points.add(new Point(-12.045739, -75.185387));
    	points.add(new Point(-12.044396, -75.195859));
    	points.add(new Point(-12.046830, -75.187361));
    	points.add(new Point(-12.049348, -75.199378));
    	points.add(new Point(-12.050775, -75.187962));
    	points.add(new Point(-12.053797, -75.184271));
    	points.add(new Point(-12.052286, -75.201523));
    	points.add(new Point(-12.057742, -75.194399));
    	points.add(new Point(-12.062526, -75.189850));
    	points.add(new Point(-12.059337, -75.204270));
    	points.add(new Point(-12.060176, -75.204098));
    	points.add(new Point(-12.060344, -75.205901));
    	points.add(new Point(-12.069829, -75.203841));
    	points.add(new Point(-12.066639, -75.211566));
    	points.add(new Point(-12.068905, -75.213969));
    	points.add(new Point(-12.071675, -75.217745));
    	points.add(new Point(-12.072179, -75.223582));
    	points.add(new Point(-12.075517, -75.229887));
    	points.add(new Point(-12.082984, -75.231136));*/
    	
    	
    	
    	
    	return points;
    }
    
    
}
