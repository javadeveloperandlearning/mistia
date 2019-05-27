<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Generar Programación</title>
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
	<div id="soli-div"><table id="lstsolicitud"></table></div>

	
	<div id="cuadrilla-div">

	
		<table  id="cuadrillas-list"  class="cuadrillas-list"  width="100%" border="0"  cellspacing="5"  cellpadding="6" ></table>
		<table    width="100%" border="0"  cellspacing="5"  cellpadding="6" >
			<tr>
				<td align="center">
				<!-- 
					// aqui
				 -->
		
	
				</td>
				
			</tr>
		 </table>
		 <!--  
		 <table id="lstresulasig">   </table>
		 
		 -->
	</div>


	<table style="visibility:hidden;"  id="cuadrillas-list-default"  class="cuadrillas-list-default"  width="100%" border="0"  cellspacing="4"  cellpadding="5" >

		<tr  style="height:50px ">
			<td >
				<div  style="float:left; width:50%" class="ui-widget  ui-widget-content arrastable" alt = "drcua"   >	
				  <p    align="center" > Los Magníficossss</p>
				</div>
				 <div  drcua="drcua1" style="float:left; width:20%" class="ui-widget  ui-widget-content" alt ="drgrup">	
				 
				 	
				  <p align="center" >&nbsp;</p>
				</div>
				 <div  style="float:left; width:20%" class="ui-widget-content " alt = "drdet">	
					<p align="center" ><a  alt="detaplan" href="#"  > 
					<img alt="" src="${pageContext.request.contextPath}/resources/images/list.png"    height="18" width="18"></a></p>
				 </div>
			</td>
		</tr>
	 </table>

	 <input  id="cdaselected" type="hidden" value="" >
	 <input  id="ngrupotarget" type="hidden" value="" >
	 <input  id="grupotarget" type="hidden" value="" >
	 <input  id="areatarget" type="hidden" value="" >
	 <input  id="proggenerada" type="hidden" value="" >
	 <input  id="progeditada" type="hidden" value="" >
	 
	 
	 <input  id="numeroprogramacion" type="hidden" value="" >
	
	  
	 <div id="dialogplan" title="Detalle de Plan de Trabajo">	
	 
		 
		 <table  class="cuadrilla-asig"   width="100%" border="0"  cellspacing="5"  cellpadding="6" >
		 	
		 	<tr>
				<td>
				<strong>
					<div  style="float:left; width:40%"    >	
					  <p    align="center" > Cuadrilla </p>
					</div>
					 <div   style="float:left; width:20%">	
					  <p align="center" >Grupo asignado</p>
					</div>
				</strong>
				</td>
			</tr>
	
			<tr>
				<td>
					<div  style="float:left; width:40%" class="ui-widget-content arrastable"  alt = "cuaasig"      >	
					  <p    align="center" > Los Magníficossss</p>
					</div>
					 <div   style="float:left; width:20%" class="ui-widget-content"  alt = "gruasig">	
					  <p align="center" >&nbsp;</p>
					</div>
	
				</td>
			</tr>
		 </table>

		<table id="lstdetalleplan">   </table>
			
	 </div>
	 
	</div>
	

	
	
		
	</div>

	<div id="botonera-div" >
	
		<div style="float:left; width:30%" >
		&nbsp;
		</div>
		<div style="float:left; width:55%" >

			<table  width="30%" border="0">

				<tr>
					<td><button   class="boton" id="btnGeneGrup">Generar Programación</button> </td>
					<td>
					<!-- 
					<button  class="boton" id="btnGuardaProg">Guardar Programación</button>
					 -->
					</td>
					
					<td>
					<button id="btnejecutar">Ejecutar</button> 
					<!--  
					<button  class="boton" id="btnReiniProgra">Reiniciar Programación</button>
					-->
					</td>
					
					
				</tr>

				<td>
				</td>
				<td>
				
				<!-- 
				<button id="btnSalir">Salir</button>
				 -->
				 </td>
				<td></td>
				</tr>

			</table>

		</div>
		
		<div style="float:left; width:15%">
				<div align="center">
				
				
				<!-- 
				
				<div  style="float:left; width:20%;height: 20%" class="ui-widget-content"  >	
					<a id="btnGuardaProg" title="Guardar Programación" href="#"  > 
					<p  align="center"   >
					<img alt="" src="${pageContext.request.contextPath}/resources/images/save.png"    height="18" width="18">
					</p>
					</a>
				</div>

				<div  style="float:left; width:10%;height: 10%"   >
					<p  align="center"   >
						&nbsp;
					</p>
				</div>
				
				
				<div  style="float:left; width:20%;height: 20%" class="ui-widget-content"  >	
					<a id="btnReiniProgra" title="Reiniciar" href="#"  > 
					<p  align="center"   >
					<img alt="" src="${pageContext.request.contextPath}/resources/images/refresh.png"    height="18" width="18">
					</p>
					</a>
				</div>
				 -->


				<div  style="float:left; width:10%;height: 10%"   >
					<p  align="center"   >
						&nbsp;
					</p>
				</div>
			
				<div  style="float:left;width:20%;height: 20%" class="ui-widget-content"  >	
					<a id="btnSalir" title="Salir" href="#"  > 
					<p  align="center"   >
					<img alt="" src="${pageContext.request.contextPath}/resources/images/salir.png"    height="18" width="18">
					</p>
					</a>
				</div>	
				
				</div>
		</div>


	</div>
	


	
	<div id="dialog" title="Alerta">		
	</div>
	
	<div id="confirmar" title="Aviso">		
	</div>
	
	 <div id="confirmarreasig" style="visibility:visible;" title="Aviso">	
	 	<div>
		 	Se reasignará la solicitud de servicio
		</div>
		 
		 <div>
			 <div  id="content-soli1" style="float:left;width:40%;"   >
			 <h1> Solicitud 1</h1>
			 </div>
			 <div style="float:left;width:15%;"   >
			 <h2> >></h2>
			 </div>
			 	<div id="content-grupo1" style="float:left;width:45%;"   >
			  <h1> Grupo 1</h1>
			 </div>
		 </div>
		 
		 <div>
		  ¿Desea realizar el cambio?
		 </div>
	</div>
	
	 <div id="dglgencuad" title="Generación de Grupos" >
		 <p> Ingrese número de grupos sugeridos </p>
		 <p> <input type="text" id ="txtnumerogrupos" > </p>
		 <p></p>
		 <p id="msgene" style="color: #D8000C; " >
		 </p>
	 </div>

	<script type="text/javascript" src="resources/js/generarprogramacion.js"></script>
	<script type="text/javascript" src="resources/js/geometria.js"></script>

	<script >

	  		var map ;
			var citymap = new Array();
			var infoWindow;
			var polygontarget;
			var markers = [];
			var gruposgenerados = [];
			

			function initMap() {
				    map = new google.maps.Map(document.getElementById('map'), {
				        zoom: 14,
				        //zoom: 13,
				        center: {lat : -12.055224,	lng : -75.195773}//,
				        //mapTypeId: 'terrain'
				    });

					var accion =  $("#accion").val();
					var ACCION_EDITA_PROGRAMACION =  $("#accion_edita_programacion").val();
					var ACCION_NUEVA_PROGRAMACION =  $("#accion_nueva_programacion").val();

				    if(accion == ACCION_EDITA_PROGRAMACION){
				    	loadMapTemp();
				    	mostrarPlanesTemp();

				    	
				    }
    
			}



			function addMark( tag, numero, _lat, _lon, tipo){
				
			      var _color =  '#ff0000';
			      var pColor = _color.substring(1,_color.length);	
			      var pImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pColor,
				          new google.maps.Size(21, 34),
				          new google.maps.Point(0,0),
				          new google.maps.Point(10, 34));
		          
				  var pShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
				          new google.maps.Size(40, 37),
				          new google.maps.Point(0, 0),
				          new google.maps.Point(12, 35));
					
				  var marker = new google.maps.Marker({
						    position: {lat : _lat,	lng : _lon},
						    map: map,
						    numero:numero,
						    descripcion: 'Solicitud: '+tag +'</strong> </p><p><strong>  Tipo: '+tipo+'</strong> </p>',
						    draggable: true,
				            icon: pImage,
				            shadow: pShadow,
				            editable: true,
		            		animation: google.maps.Animation.DROP
					});


					var infowindow = new google.maps.InfoWindow({
	        	          content: marker.descripcion
	        	    });

	      	      	marker.addListener('mouseover', function() {
	      	          infowindow.open(map, marker);
	      	        });
	      	        
	      	  		marker.addListener('mouseout', function() {
	    	          infowindow.close();
	    	        });
	    	        
	      	  		markers.push( marker);
			}

			function removeMark(numero){
				console.log(markers);
				for (var i = 0; i < markers.length; i++) {
					if(markers[i].numero ==  numero){
					  markers[i].setMap(null);
					  markers[i].splice(i,1);
					  return ;
				    }
			    }
			}
			
		      
		    function loadMap(solicitudesselect, numerogrupos) {



						$.ajax({
							url:"rest/programacion/generarprogramacion.html",
							type:"POST",
							data: {"psolicitudesselect":solicitudesselect.toString(), "pnumerogrupos":numerogrupos},
							datatype:"json",
							success:function(data){

								console.log("## MOSTRANDO GRUPOS ###");
								console.log(data.grupos);
							 	// pintamos el mapa con el grupo
								pintarmapa(data.grupos);
								// pintamos los planes de trabajo
								mostrarAsignaciones(data.planes);
								gruposgenerados =  data.grupos;

								/*var solicitudesselected  =    $("#lstsolicitud").jqGrid('getGridParam','selarrrow');
								console.log("#SOLICIUDES SELECCIONADAS");	
								console.log(solicitudesselected);
								console.log("#GRUPOS");	
								console.log(data.grupos);*/
								// aqui 
							

								
								// Cargamos nuevamente las solictudes
								cargarsolicitudes(data.grupos);
								$("#proggenerada").val(true);

								
															      
				      	}});
		    }


		    function loadMapTemp() {

				$.ajax({
					url:"rest/programacion/gruposgenerados.html",
					type:"POST",
					datatype:"json",
					success:function(grupos){
						pintarmapa(grupos);
						      
		      	}});
    		}


    		function mostrarPlanesTemp(){

				$.ajax({
					url:"rest/programacion/planestrabajoasignados.html",
					type:"POST",
					datatype:"json",
					success:function(data){
						if(data!=null && data.length>0){
							mostrarAsignaciones(data);
						}
					},
				    error: function (request, status, error) {
				    	
				    }
				});
            }


		     // eventos para los poligonos 
     	     var addListenersOnPolygon = function(polygon) {
         	     console.log("###  añadiendo eventos  ###");
		    	  	google.maps.event.addListener(polygon, 'mouseover', function (event) {
		    		  console.log("mouseover");
			    	  //alert(event.latLng )
			    	polygontarget = polygon;
		    	    //console.log(polygon);
		    	    // ultimo grupo de destino
		    	    $("#ngrupotarget").val(polygon.idx);
		    	    $("#grupotarget").val(polygon.grupo);
		    	    //area del grupo seleccionado
		    	    $("#areatarget").val(polygon.area);
		    	    
		    	   // asisgnarcuadrilla(polygon);
		    	  }); 
		    }

		     



 		    

 		    var addListenerOnMarker =  function(marker){


 				 console.log(" ## añadiendo eventos ##");

 				 /*google.maps.event.addListener(marker, 'dragstart', function handleEvent(event) {
 	 				 console.log("clicando marker ");
 	 				 console.log(marker); 
 	 			 });*/
 				 
 	 	  		
 	    	 	 google.maps.event.addListener(marker, 'dragend', function handleEvent(event) {
				 $marker  = this;
				 var latcent =  polygontarget.getCenter().lat();
				 var lngcent =  polygontarget.getCenter().lng();
				 
				 // nuevo punto añadir al sector
				 var latactu =  $marker.posini.lat;
				 var lngactu =  $marker.posini.lng;

				 var grupotarget =  polygontarget.grupo;
				 var grupomarker =  $marker.grupo;

				 var  numerogrup =  $marker.numerogrup;
				 var _numerosoli =  $marker.numerosolicitud;
				 var _numerogrup =  polygontarget.idx;

				 console.log(" #### grupo origen->" + numerogrup);
				 console.log(" #### -> "+_numerosoli);
				 console.log(" #### -> "+_numerogrup);
				
				 if(numerogrup == _numerogrup){
			      	var oldposition =  $marker.posini;
			      	marker.setPosition(oldposition);
					return false;
				 }

				 var htmlconf = '';	
				     htmlconf+=	'<div> Se reasignará la solicitud de servicio';
				     htmlconf+=	'</div><div><div  id="content-soli1" style="float:left;width:40%;"><h1> Solicitud 1</h1></div>';
				     htmlconf+=	'<div style="float:left;width:15%;"   ><h2> >></h2></div><div id="content-grupo1" style="float:left;width:45%;"><h1> Grupo 1</h1>';
				     htmlconf+=	'</div></div><div>¿Desea realizar el cambio?</div>';


				$("#content-soli1").html("<h2> Solicitud # "+$marker.numerosolicitud+"</h2>");
				$("#content-grupo1").html("<h2>"+grupotarget+"</h2>");
					 
				 //$( "#confirmarreasig" ).html(htmlconf);
				 $( "#confirmarreasig" ).dialog({
							autoOpen: false,
							width: 400,
							buttons: [
								{
									text: "Si",	
									click: function() {

										$.ajax({
											url:"rest/programacion/reasignarsolicitud.html",
											type:"POST",
											data:{numerosoli:_numerosoli, numerogrup: _numerogrup},
											datatype:"json",
											success:function(grupos){
	
												pintarmapa(grupos);
												$( "#confirmarreasig" ).dialog( "close" );
												$( "#confirmarreasig" ).css({visibility:"hidden"});
												$("#progeditada").val(true);
												//$( "#confirmarreasig" ).html("");
								
								      	}});
										
									}
					
								},
								{
									text: "No",
									click: function() {
										 $( "#confirmarreasig" ).dialog( "close" );
										 $( "#confirmarreasig" ).css({visibility:"hidden"});
										 //$( "#confirmarreasig" ).html("");
								      	 var oldposition =  $marker.posini;
								      	 marker.setPosition(oldposition);

									}
								}
							]
						});
						
				 $( "#confirmarreasig" ).css({visibility:"visible"});
				 $( "#confirmarreasig" ).dialog("open");

				      	
		    	 		    	   
  		    });  



 	    	 	// windows info 
      	        var infowindow = new google.maps.InfoWindow({
        	          content: marker.descripcion
        	        });

      	      	marker.addListener('mouseover', function() {
      	          infowindow.open(map, marker);
      	        });
      	        
      	  		marker.addListener('mouseout', function() {
    	          infowindow.close();
    	        });
		    	
  		    	
 	  	  	}


 	  	  	function pintarmapa(grupos){


				//console.log("creando el mapa ");
		        // Create the map.
			      map = new google.maps.Map(document.getElementById('map'), {
			          zoom: 14,
			          center: {lat : -12.055224,	lng : -75.195773}//,
			          //mapTypeId: 'terrain'
			      });



					 
				//console.log("los grupos ");
				//console.log(grupos);
					
				var  idx = 0;  
				for (var i=0;i< grupos.length;i++) {
					var g   = grupos[i];

														 								
					citymap[idx] =  {
							  idx: g.numero ,
							  name:g.descripcion,
					          center: {lat: g.latitudcentral, lng: g.longitudcentral},
					          population: 50,
					          color:g.color,
					          radio:g.radio,
					          area :g.area
					          
					        };

			          var _color =  g.color;	
			          console.log(_color);		 
				      var pinColor = _color.substring(1,_color.length);								      
				      var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
				          new google.maps.Size(21, 34),
				          new google.maps.Point(0,0),
				          new google.maps.Point(10, 34));
				      var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
				          new google.maps.Size(40, 37),
				          new google.maps.Point(0, 0),
				          new google.maps.Point(12, 35));
			        

					// detalle de los grupos
					
					 for (var j in g.detalles) {

							d =  g.detalles[j];
							console.log(d);
							
						  // añdiendo marcadores 
							var myLatLng = {lat: d.latitud, lng: d.longitud};
							var marker = new google.maps.Marker({
								    position: myLatLng,
								    map: map,
								    descripcion: '<p>'+g.descripcion +'</p>'+'<p><strong> Solicitud: '+ d.tag +'</strong> </p><p><strong>  Tipo: '+d.tipo+'</strong> </p>',
								    //title: '('+d.latitud+" , "+ d.longitud+')',
								    draggable: true,
						            icon: pinImage,
						            shadow: pinShadow,
						            editable: true,
						            //datos del grupo al que pertence
						            numerogrup:g.numero,
						            grupo:g.descripcion,
						            areagrupo:g.area, 
						            centro: {lat: g.latitudcentral, lng: g.longitudcentral},// punto central
				            		posini:{lat: d.latitud, lng: d.longitud},
				            		numerosolicitud: d.numeroSolicitud,
				            		animation: google.maps.Animation.DROP
							});



							addListenerOnMarker(marker);
							
					 }							        

					idx++;	
				 }
				 /*console.log("las cidudades ");
				 console.log(citymap);
				 console.log("cargando el mapa despues ajax ");*/

					// construyendo circulo ksd
			        for (var city in citymap) {
			          // Add the circle for this city to the map.
			           var radio  =   Math.sqrt(citymap[city].population) * 50;

				      //console.log("el radio :"+citymap[city].radio);
				      //console.log("el radio pop :"+radio);
			          
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

			          addListenersOnPolygon(cityCircle);
			        }

 	 	 	 }




	
	</script>


	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyChk5fjsxbMDJmHbDA1Ap8fO34fplNvbDE&callback=initMap">		
	</script>


<input type="hidden" id ="switch1">
<input type="hidden" id ="accion" value="${accion_programacion}">
<input type="hidden" id ="accion_nueva_programacion" value="${ACCION_NUEVA_PROGRAMACION}">
<input type="hidden" id ="accion_edita_programacion" value="<c:out value='${ACCION_EDITA_PROGRAMACION}'/>">

</body>
</html>