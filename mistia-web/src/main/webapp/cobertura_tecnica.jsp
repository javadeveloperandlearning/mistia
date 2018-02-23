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
					<button   class="boton" id="btnCancelar">Cancelar</button>

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
			var markerselect =  null;

			var bermudaTriangle = null;
			
			$(".boton").button().css({width:"200"});

			$("#btnSeleccionar").live("click",function(){
				
				window.location.href= "solicitud_servicio_generar.xhtml";
			});

			$("#btnCancelar").live("click",function(){
				window.location.href= "solicitud_servicio_generar.xhtml";
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

			      var _color =  '#446600';
			      var pColor = _color.substring(1,_color.length);	
			      var pImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pColor,
				          new google.maps.Size(21, 34),
				          new google.maps.Point(0,0),
				          new google.maps.Point(10, 34));

				  var pShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
				          new google.maps.Size(40, 37),
				          new google.maps.Point(0, 0),
				          new google.maps.Point(12, 35));
					
				var  idx = 0;  
				for (var i=0;i< nodos.length;i++) {
					var g   = nodos[i];
					citymap[idx] =  {
							  idx: g.numero ,
							  name:g.descripcion,
					          center: {lat: g.latitudcentral, lng: g.longitudcentral},
					          population: 50,
					          color:g.color,
					          radio:g.radio,
					          area :g.area
					        };

						 // nodo 
						 new google.maps.Marker({
			              position:{lat: g.latitudcentral, lng: g.longitudcentral},
			              map: map,
			                icon: pImage,
			                shadow: pShadow,
			                fillColor: "blue",
			                fillOpacity: .2,
			                strokeColor: 'white',
			                strokeWeight: .5,
			                scale: 10
			            });

			        
					idx++;	
				 }


			     // Define the LatLng coordinates for the polygon's path.
			        var triangleCoords = [
			        	{lat: -12.009472, lng: -75.172256},
			        	{lat: -12.021729, lng: -75.173629},
			        	{lat: -12.031970, lng: -75.185130},
			        	{lat: -12.037847, lng: -75.179294},
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
			        addListenersOnPolygon(bermudaTriangle);

			      /*  google.maps.event.addListener(map, 'click', function(e) {

				    alert(" añadiendo marcador ");
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


				    });*/


 	 	 	 }



    	     var addListenersOnPolygon = function(polygon) {
        	     
		    	 google.maps.event.addListener(polygon, 'click', function (e) {

			    	 
		    		 if(markerselect!=null  ){

		    			 markerselect.setMap(null);
					 }
				      var _color =  '#0000b3';
				      var pColor = _color.substring(1,_color.length);	
				      var pImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pColor,
					          new google.maps.Size(21, 34),
					          new google.maps.Point(0,0),
					          new google.maps.Point(10, 34));

					  var pShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
					          new google.maps.Size(40, 37),
					          new google.maps.Point(0, 0),
					          new google.maps.Point(12, 35));


					  markerselect =  new google.maps.Marker({
			              position:e.latLng,
			               map: map,
			                icon: pImage,
			                shadow: pShadow,
			                fillColor: "blue",
			                fillOpacity: .2,
			                strokeColor: 'white',
			                strokeWeight: .5,
			                scale: 10
			            });
			    	  

		    	 }); 
		    }


	
	</script>


	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyChk5fjsxbMDJmHbDA1Ap8fO34fplNvbDE&callback=loadMap">		
	</script>


</body>
</html>