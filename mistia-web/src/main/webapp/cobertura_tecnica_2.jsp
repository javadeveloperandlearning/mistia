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

            div#botonera-div-top {
                position: absolute;
                top:2%;
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

    <div id="botonera-div-top"    align="center">
        <input id="txtdireccion" style="width: 30%" type="text"   >
        <button   class="boton" id="btnbuscar">Buscar</button>
        <!--button   class="boton" id="btnlimpiar">Limpiar</button-->
        <button   class="boton" id="btnsalir">Volver</button>    
    </div>


    <!--div id="botonera-div" align="right" >
        <button   class="boton" id="btnsalir">Salir</button>
    </div-->


    <script type="text/javascript" src="resources/js/geometria.js"></script>

    <script >

        var map;
        var citymap = new Array();

        var infoWindow;
        var polygontarget;
        var markers = [];
        var markers_around = [];
        var circles = [];
        var gruposgenerados = [];
        var markerselect = null;
        var bermudaTriangle = null;
        var direcciones = ['mid1', 'mid2', 'mid3'];
        var posteservicio = {};
        var puntoservicio = {};



        $(".boton").button().css({width: "200"});
        $("#btnsalir").live("click", function () {
            window.location.href = "solicitud_servicio_generar.xhtml";
        });
        $("#btnlimpiar").live("click", function () {
            window.location.href = "principal.xhtml";
        });



        $("#btnbuscar").live("click", function () {

            //buscar la dirección y obtener su geolocalización
            //var address =  'Peru+Huancayo+jr pegaso';
            if ($.trim($("#txtdireccion").val()) == "") {
                $("#pmensaje").css({color: 'red'})
                $("#pmensaje").html("<span class='ui-icon ui-icon-circle-close' style='float:left;'></span>De ingresar una dirección válida");
                $("#mensaje").dialog("open");
                return false;
            }

            var Pais = 'Peru';
            var Provincia = 'Huancayo';
            var address = Pais + '+' + Provincia + '+' + $("#txtdireccion").val();
            console.log(address);
            $.ajax({
                url: 'https://maps.googleapis.com/maps/api/geocode/json?address=' + address + ',+CA&key=AIzaSyChk5fjsxbMDJmHbDA1Ap8fO34fplNvbDE',
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    //console.log(data);
                    console.log(data['results']);
                    var results = data['results'];

                    var long_name = results[0].address_components[0].long_name;
                    var myLatLng = results[0].geometry.location;
                    console.log('my location');
                    console.log(myLatLng);
                    if (long_name != null && long_name == Provincia) {

                        $("#pmensaje").css({color: 'red'})
                        $("#pmensaje").html("<span class='ui-icon ui-icon-circle-close' style='float:left;'></span>La dirección no existe");
                        $("#mensaje").dialog("open");
                        return false;
                    }

                    var formatted_address = results[0].formatted_address;

                    removeAllMark();

                    var mensaje = '';
                    var pinColor = '446600';
                    var cirColor = '446600';

                    // validacion de cobertura 
                    puntoservicio = myLatLng;

                    $.ajax({
                        url: "rest/cobertura/validarcobertura.html",
                        data: {latitud: myLatLng.lat, longitud: myLatLng.lng},
                        type: "POST",
                        datatype: "json",
                        success: function (data) {
                            postescercanos = data['postesceranos'];
                            console.log(postescercanos);
                            //console.log(data);
                            if (postescercanos != null && postescercanos.length > 0) {
                                $("#pmensaje").css({color: 'black'})
                                mensaje = 'La dirección tiene cobertura '
                                pinColor = '446600';
                                posteservicio = postescercanos[0];

                            } else {
                                $("#pmensaje").css({color: 'red'})
                                mensaje = 'La dirección no tiene cobertura '
                                pinColor = 'ff0000';
                            }
                            $("#pmensaje").css({color: 'balck'})
                            // respuesta exitosa de busqueda
                            console.log("long_name" + results[0].address_components[0].long_name);
                            console.log(results[0].geometry.location);

                            $("#pmensaje").html("<span class='ui-icon ui-icon-circle-close' style='float:left;'></span>" + mensaje);
                            $("#mensaje").dialog("open");


                            var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
                                    new google.maps.Size(21, 34),
                                    new google.maps.Point(0, 0),
                                    new google.maps.Point(10, 34));
                            var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
                                    new google.maps.Size(40, 37),
                                    new google.maps.Point(0, 0),
                                    new google.maps.Point(12, 35));

                            //var myLatLng = {lat: -12.069051679036212, lng: -75.20355835952567};
                            //console.log('generando circulo');

                            // ciruclo de ubicacion 
                            var cityCircle = new google.maps.Circle({
                                strokeOpacity: 0.8,
                                strokeWeight: 2,
                                fillColor: '#' + pinColor,
                                fillOpacity: 0.35,
                                map: map,
                                radius: 100.307937047031,
                                center: myLatLng,
                                label: 'Tiene Cobertura'
                            });

                            var marker = new google.maps.Marker({
                                position: myLatLng,
                                map: map,
                                descripcion: '<p><strong> Dirección ' + formatted_address + '</strong> </p>',
                                draggable: true,
                                icon: pinImage,
                                shadow: pinShadow,
                                editable: true,
                                animation: google.maps.Animation.DROP
                            });

                            var infowindow = new google.maps.InfoWindow({
                                content: marker.descripcion
                            });

                            marker.addListener('mouseover', function () {
                                infowindow.open(map, marker);
                            });

                            marker.addListener('mouseout', function () {
                                infowindow.close();
                            });



                            markers.push(marker);
                            circles.push(cityCircle);


                            map.setCenter(myLatLng);
                            map.setZoom(17);


                            for (var i = 0; i < postescercanos.length; i++) {

                                var g = postescercanos[i];
                                console.log('añadiendo poste');
                                console.log(postescercanos[i]);

                                var tag = '<p>' + g.descripcion + ' (' + g.latitudcentral + ',' + g.longitudcentral + ')</p>';

                                addMark(tag, g.numero, g.latitudcentral, g.longitudcentral);
                                /*var _color = '#446600';
                                 var pColor = _color.substring(1, _color.length);
                                 var pImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pColor,
                                 new google.maps.Size(21, 34),
                                 new google.maps.Point(0, 0),
                                 new google.maps.Point(10, 34));
                                 
                                 var pShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
                                 new google.maps.Size(40, 37),
                                 new google.maps.Point(0, 0),
                                 new google.maps.Point(12, 35));
                                 
                                 
                                 // poste 
                                 var _marker = new google.maps.Marker({
                                 position: {lat: g.latitudcentral, lng: g.longitudcentral},
                                 map: map,
                                 descripcion: '<p>'+g.latitudcentral+','+g.longitudcentral+'</p>',
                                 icon: pImage,
                                 shadow: pShadow,
                                 fillColor: "blue",
                                 fillOpacity: .2,
                                 strokeColor: 'white',
                                 strokeWeight: .5,
                                 scale: 10
                                 });
                                 
                                 
                                 var _infowindow = new google.maps.InfoWindow({
                                 content: _marker.descripcion
                                 });
                                 
                                 _marker.addListener('mouseover', function () {
                                 _infowindow.open(map, _marker);
                                 });
                                 
                                 _marker.addListener('mouseout', function () {
                                 _infowindow.close();
                                 });
                                 
                                 markers_around.push(_marker);*/


                            }




                        }});


                    /*if(false){
                     $("#pmensaje").css({color:'black'})
                     mensaje = 'La dirección tiene cobertura '
                     pinColor =  '446600';
                     
                     }else{
                     $("#pmensaje").css({color:'red'})
                     mensaje = 'La dirección no tiene cobertura '
                     pinColor = 'ff0000';
                     }*/





                }
            });


            //<span class="ui-icon ui-icon-check" style="float:left;"></span>La dirección tiene cobertura

        });



        $("#txtdireccion").keypress(function (event) {
            console.log('onkeypress');
        });

        $("#txtdireccion").autocomplete({
            source: direcciones,
            select: function (event, ui) {
                //alert(ui.item.id);
                return false;
            },
            change: function (event, ui) {
                console.log('algorimos');
            }

        });


        function initMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                zoom: 13,
                //zoom: 13,
                center: {lat: -12.055224, lng: -75.195773}//,
                //mapTypeId: 'terrain'
            });
        }

        function loadMap() {

            $.ajax({
                url: "rest/cobertura/coberturanodos.html",
                type: "POST",
                datatype: "json",
                success: function (data) {
                    pintarmapa(data);
                }});

            $("#mensaje").dialog({
                autoOpen: false,
                width: 400,
                buttons: [
                    {
                        text: "Aceptar",
                        click: function () {

                            var _posteservicio = {codigoPoste: posteservicio.codigoPoste,
                                latitud: posteservicio.latitudcentral,
                                longitud: posteservicio.longitudcentral
                            };
                            var _puntoservicio = {latitud: puntoservicio.lat, longitud: puntoservicio.lng};

                            sendRequest("rest/cobertura/selecionarposteservicio.html", _posteservicio); // enviando posite mas cercano
                            sendRequest("rest/cobertura/selecionarpuntoservcio.html", _puntoservicio); // enviando punto de servicio seleccionado o encontrado en el mapa

                            posteservicio = {};
                            puntoservicio = {};

                            $(this).dialog("close");
                        }
                    }
                ]
            });
        }

        function sendRequest(myurl, mydata) {
            $.ajax({
                url: myurl,
                data: mydata,
                type: "POST",
                datatype: "json",
                success: function (data) {
                    console.log(data);
                }});
        }




        function pintarmapa(nodos) {
            // Create the map.
            map = new google.maps.Map(document.getElementById('map'), {
                zoom: 14,
                center: {lat: -12.055224, lng: -75.195773}//,
            });

            //console.log(nodos);

            var _color = '#446600';
            var pColor = _color.substring(1, _color.length);
            var pImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pColor,
                    new google.maps.Size(21, 34),
                    new google.maps.Point(0, 0),
                    new google.maps.Point(10, 34));

            var pShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
                    new google.maps.Size(40, 37),
                    new google.maps.Point(0, 0),
                    new google.maps.Point(12, 35));


            var idx = 0;
            for (var i = 0; i < nodos.length; i++) {
                var g = nodos[i];
                citymap[idx] = {
                    idx: g.numero,
                    name: g.descripcion,
                    center: {lat: g.latitudcentral, lng: g.longitudcentral},
                    population: 50,
                    color: g.color,
                    radio: g.radio,
                    area: g.area
                };
                // nodo 
                new google.maps.Marker({
                    position: {lat: g.latitudcentral, lng: g.longitudcentral},
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
                strokeOpacity: 0.4,
                strokeWeight: 2,
                fillColor: '#FF0000',
                fillOpacity: 0.1
            });
            bermudaTriangle.setMap(map);
            
            
            
            // addListenersOnPolygon(bermudaTriangle);

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





        function addMark(tag, numero, _lat, _lon) {

            var _color = '#ff0000';
            var pColor = _color.substring(1, _color.length);
            var pImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pColor,
                    new google.maps.Size(21, 34),
                    new google.maps.Point(0, 0),
                    new google.maps.Point(10, 34));

            var iconBase = 'https://developers.google.com/maps/documentation/javascript/examples/full/images/';

            var pShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
                    new google.maps.Size(40, 37),
                    new google.maps.Point(0, 0),
                    new google.maps.Point(12, 35));

            var marker = new google.maps.Marker({
                position: {lat: _lat, lng: _lon},
                map: map,
                numero: numero,
                descripcion: tag,
                draggable: true,
                icon: iconBase + 'info-i_maps.png',
                shadow: pShadow,
                editable: true,
                animation: google.maps.Animation.DROP
            });


            var infowindow = new google.maps.InfoWindow({
                content: marker.descripcion
            });

            marker.addListener('mouseover', function () {
                infowindow.open(map, marker);
            });

            marker.addListener('mouseout', function () {
                infowindow.close();
            });

            markers_around.push(marker);
        }



        function removeAllMark() {

            for (var i = 0; i < markers.length; i++) {
                markers[i].setMap(null);
            }

            for (var i = 0; i < circles.length; i++) {
                circles[i].setMap(null);
            }
        }


        var addListenersOnPolygon = function (polygon) {

            google.maps.event.addListener(polygon, 'click', function (e) {

                if (markerselect != null) {

                    markerselect.setMap(null);
                }
                var _color = '#0000b3';
                var pColor = _color.substring(1, _color.length);
                var pImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pColor,
                        new google.maps.Size(21, 34),
                        new google.maps.Point(0, 0),
                        new google.maps.Point(10, 34));

                var pShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
                        new google.maps.Size(40, 37),
                        new google.maps.Point(0, 0),
                        new google.maps.Point(12, 35));


                markerselect = new google.maps.Marker({
                    position: e.latLng,
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

    <div id="mensaje" title="Mensaje">	
        <p id="pmensaje"  > <span class="ui-icon ui-icon-check" style="float:left;"></span>La dirección tiene cobertura <p>

    </div>
</body>
</html>