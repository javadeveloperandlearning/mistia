<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mistia</title>
<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
	height: 100%;
	width: 100%
}

div#soli-div {
 position: absolute;
  top: 5%;
  left: 1%;
  z-index: 5;


}

div#cuadrilla-div {
 position: absolute;
  top: 5%;
  right: 1%;
  z-index: 5;
  width: 20%;
  background-color: #fff;
  border: 1px solid #999;
}



div#salir-div {

  botton: 50%;
  right: 5%;
  z-index: 5;
   width: 2.5%;
}


div#botonera-div {
 position: absolute;
 bottom:2%;
 width: 100%;
 

}


div#test-div {
 position: absolute;
  top: 50%;
  left: 50%;
  z-index: 5;
}


#container {
  position: relative;
  width: 300px;
  height: 200px;
}
#block {
  background: #CCC;
  filter: alpha(opacity=60);
  /* IE */
  -moz-opacity: 0.6;
  /* Mozilla */
  opacity: 0.6;
  /* CSS3 */
  position: absolute;
  top: 50%;
  left: 50%;
  height: 100%;
  width: 100%;
}
#text {

  top:50%;
  left: 50%;
  width: 100%;
  height: 100%;
  

  vertical-align: middle; 
  text-align: center; 
  
}

#text {
	z-index: 10;
}




/* Optional: Makes the sample page fill the window. */
html, body {
	font-size: 12px;
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>

<script type="text/javascript"	src="${pageContext.request.contextPath}/resources/js/jquery-1.7.2.js"></script>
<link	href="${pageContext.request.contextPath}/resources/jquery-ui-1.11.4.custom/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="${pageContext.request.contextPath}/resources/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/resources/jquery.jqGrid-4.4.3/js/jquery.jqGrid.src.js"></script>
<script type="text/javascript"	src="${pageContext.request.contextPath}/resources/jquery.jqGrid-4.4.3/js/i18n/grid.locale-es.js"></script>
<link	href="${pageContext.request.contextPath}/resources/jquery.jqGrid-4.4.3/css/ui.jqgrid.css" rel="stylesheet" type="text/css" />

</head>
<body>


	<div id="map" style="float: left"></div>

	 
	</div>


	<div id="botonera-div" >
	

		<div style="align:center; width:100%" >

			<table  align="center" width="20%" border="0">

				<tr>
						<td>
						<button   class="boton" id="btnSeleccionar">Seleccionar</button> 
						</td>
			
					
					<td>
					<button   class="boton" id="btnGeneGrup">Cancelar</button>

					</td>
					
					
				</tr>
			</table>

		</div>
	
	</div>
	<script type="text/javascript" src="resources/js/geometria.js"></script>

	<script >

	  		var map ;
			var citymap = new Array();
			var infoWindow;
			var polygontarget;
			var markers = [];
			var gruposgenerados = [];
			var bermudaTriangle = null;
			$(".boton").button().css({width:"200"});
			
			var mappoints =  [

				{latitude:-12.03687, longitude:-75.19209},
				{latitude:-12.06875, longitude:-75.19656},
				{latitude:-12.06698, longitude:-75.19945},
				{latitude:-12.05156, longitude:-75.20253},
				{latitude:-12.0693, longitude:-75.21595},
				{latitude:-12.06039, longitude:-75.20429},
				{latitude:-12.06969, longitude:-75.21122},
				{latitude:-12.08023, longitude:-75.22001},
				{latitude:-12.07674, longitude:-75.21473},
				{latitude:-12.06611, longitude:-75.21003},
				{latitude:-12.06892, longitude:-75.19803},
				{latitude:-12.07087, longitude:-75.21329},
				{latitude:-12.07702, longitude:-75.21485},
				{latitude:-12.08159, longitude:-75.22826},
				{latitude:-12.08464, longitude:-75.2263},
				{latitude:-12.0803, longitude:-75.21692},
				{latitude:-12.06617, longitude:-75.19826},
				{latitude:-12.05993, longitude:-75.18059},
				{latitude:-12.04302, longitude:-75.19827},
				{latitude:-12.06856, longitude:-75.20342},
				{latitude:-12.08011, longitude:-75.21683},
				{latitude:-12.08209, longitude:-75.2302},
				{latitude:-12.0587, longitude:-75.20239},
				{latitude:-12.08108, longitude:-75.21694},
				{latitude:-12.05548, longitude:-75.20159},
				{latitude:-12.06875, longitude:-75.19656},
				{latitude:-12.06249, longitude:-75.19924},
				{latitude:-12.0704, longitude:-75.19823},
				{latitude:-12.07609, longitude:-75.23074},
				{latitude:-12.07703, longitude:-75.21963},
				{latitude:-12.05601, longitude:-75.20046},
				{latitude:-12.06567, longitude:-75.21237},
				{latitude:-12.07127, longitude:-75.20448},
				{latitude:-12.05346, longitude:-75.18604},
				{latitude:-12.07778, longitude:-75.20964},
				{latitude:-12.02079, longitude:-75.18386},
				{latitude:-12.05644, longitude:-75.19107},
				{latitude:-12.07355, longitude:-75.21913},
				{latitude:-12.07627, longitude:-75.20988},
				{latitude:-12.05917, longitude:-75.18205},
				{latitude:-12.07087, longitude:-75.21329},
				{latitude:-12.02486, longitude:-75.19345},
				{latitude:-12.05994, longitude:-75.19356},
				{latitude:-12.05211, longitude:-75.1966},
				{latitude:-12.07702, longitude:-75.21485},
				{latitude:-12.07509, longitude:-75.21161},
				{latitude:-12.07416, longitude:-75.21651},
				{latitude:-12.02726, longitude:-75.18268},
				{latitude:-12.06242, longitude:-75.19687},
				{latitude:-12.05584, longitude:-75.1817},

				];

			
			$("#btnSeleccionar").live("click",function(){

				console.log("seleccionando puntos #### ");
			    var distanceLimit = 5000; //in meters
			    var numberRandomPoints = 2300;
			    mappoints = generateMapPoints({latitude:-12.055224,longitude:-75.195773} , distanceLimit, numberRandomPoints);
			    console.log(mappoints);
				var  cant =  0;
			    
				for(var key in mappoints){
					var  latLng =  new google.maps.LatLng(mappoints[key].latitude,mappoints[key].longitude );
					var  latLng2 =  new google.maps.LatLng(mappoints[key].latitude,mappoints[key].longitude2 );

			         var inside =  google.maps.geometry.poly.containsLocation(latLng, bermudaTriangle)
			         if(inside){
			            new google.maps.Marker({
				              position: latLng,
				              map: map,
				              icon: {
				                path: google.maps.SymbolPath.CIRCLE,
				                fillColor: "#56df23",
				                fillOpacity: .2,
				                strokeColor: '#000',
				                strokeWeight: .5,
				                scale: 5
				              }
				            });

			            cant++;

			           // console.log( 'points.add(new Point('+mappoints[key].latitude+','+ mappoints[key].longitude+'));');
			            console.log( 'points.add(new Point('+mappoints[key].latitude+','+ mappoints[key].longitude+'));');
			         }

			        /*console.log(" añadiendo marcadores ");
		            new google.maps.Marker({
			              position: latLng2,
			              map: map,
			              icon: {
			                path: google.maps.SymbolPath.CIRCLE,
			                fillColor: "red",
			                fillOpacity: .2,
			                strokeColor: 'white',
			                strokeWeight: .5,
			                scale: 10
			              }
			            });*/
				}

				console.log(" cant puntos : "+cant);
				
					


			});



			
			
			function initMap() {
				    map = new google.maps.Map(document.getElementById('map'), {
				        zoom: 14,
				        //zoom: 13,
				        center: {lat : -12.055224,	lng : -75.195773}//,
				        //mapTypeId: 'terrain'
				    });
	    
			}

		    function loadMap() {

				$.ajax({
						url:"rest/cobertura/coberturanodos.html",
						type:"POST",
						datatype:"json",
						success:function(data){
						pintarmapa(data);

															      
				 }});
		    }
	

		    
 	  	  	function pintarmapa(nodos){
		        // Create the map.
			      map = new google.maps.Map(document.getElementById('map'), {
			          zoom: 14,
			          center: {lat : -12.055224,	lng : -75.195773}//,
			           //center: {lat: 24.886, lng: -70.268},
			          //mapTypeId: 'terrain'
			      });

				console.log(nodos);

			


			     /*for (var city in citymap) {
			          var radio  =   Math.sqrt(citymap[city].population) * 50;
			          var cityCircle = new google.maps.Circle({
				        idx:citymap[city].idx,
				        grupo:citymap[city].name,
			            strokeColor: citymap[city].color,
			            strokeOpacity: 0.8,
			            strokeWeight: 2,
			            fillColor: citymap[city].color,
			            fillOpacity: 0.35,
			            map: map,
			            center: citymap[city].center,
			            radius: citymap[city].radio,
			            area: citymap[city].area,
			            label: 'test'
			          });
			      }*/
				 
			     // Define the LatLng coordinates for the polygon's path.
			        var triangleCoords = [
			        	{lat: -12.009472, lng: -75.172256},
			        	//{lat: -12.021729, lng: -75.173629},
			        	{lat: -12.024751, lng: -75.179905},
			        	{lat: -12.031970, lng: -75.185130},
			        	//{lat: -12.037847, lng: -75.179294},
						{lat: -12.036438, lng: -75.188477},
						//{lat: -12.061784, lng: -75.186595},
						
			        	{lat: -12.061853, lng: -75.180495},
			        	{lat: -12.076970, lng: -75.206165},
			        	{lat: -12.087368, lng: -75.230792},
			        	{lat: -12.079434, lng: -75.232029},
			        	{lat: -12.071658, lng: -75.233997},
			        	{lat: -12.070616, lng: -75.219422},
			        	{lat: -12.060875, lng: -75.209870},
			        	{lat: -12.051055, lng: -75.203948},
			        	{lat: -12.030713, lng: -75.198347},
			        	{lat: -12.009781, lng: -75.181023},
			        	{lat: -12.009472, lng: -75.172256}
			        ];


					console.log(" ### perimetro huancayo");
			      	for(var key in  triangleCoords){
			      		console.log(triangleCoords[key].lat+","+ triangleCoords[key].lng);
				    }

			      
			        // Construct the polygon.
			        bermudaTriangle = new google.maps.Polygon({
			          paths: triangleCoords,
			          strokeColor: '#FF0000',
			          strokeOpacity: 0.8,
			          strokeWeight: 2,
			          fillColor: '#FF0000',
			          fillOpacity: 0.35
			        });
			        bermudaTriangle.setMap(map);




			        google.maps.event.addListener(map, 'click', function(e) {
			            var resultColor =
			                google.maps.geometry.poly.containsLocation(e.latLng, bermudaTriangle) ?
			                'blue' :
			                'green';

			            new google.maps.Marker({
			              position: e.latLng,
			              map: map,
			              icon: {
			                path: google.maps.SymbolPath.CIRCLE,
			                fillColor: resultColor,
			                fillOpacity: .2,
			                strokeColor: 'white',
			                strokeWeight: .5,
			                scale: 10
			              }
			            });
			          });

 	 	 	 }




	
	</script>


	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyChk5fjsxbMDJmHbDA1Ap8fO34fplNvbDE&callback=loadMap">		
	</script>


</body>
</html>