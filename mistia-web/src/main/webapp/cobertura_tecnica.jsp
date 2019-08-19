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
        <input id="txtdireccion" style="width: 30%" type="text" class="controls"  placeholder="Ingrese dirección a buscar"   >
        <button   class="boton" id="btnbuscar">Buscar</button>       
        <button   class="boton" id="btnsalir">Volver</button>    
    </div>
    
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
        var coberturaPolygon = null;
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

        function preload(titulo) {
            var html = "<div id='dialog-preload' title='" + titulo + "'>" + "<div  align='center' ><img src='/mistia-web/resources/images/preloader.gif'/></div>" + "</div>";
            $("#content").html(html);
            // alert($("#dialog-preload").attr('title'));
            $("#dialog-preload").dialog({
                autoOpen: false,
                width: 400,
                modal: true
            });
        }


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
            preload("Buscando cobertura");
            $("#dialog-preload").dialog("open");

            $.ajax({
                url: 'https://maps.googleapis.com/maps/api/geocode/json?address=' + address + ',+CA&key=AIzaSyChk5fjsxbMDJmHbDA1Ap8fO34fplNvbDE',
                type: 'GET',
                dataType: 'json',
                success: function (data) {

                    var results = data['results'];
                    var long_name = results[0].address_components[0].long_name;
                    var myLatLng = results[0].geometry.location;
                    if (long_name != null && long_name == Provincia) {
                        $("#pmensaje").css({color: 'red'})
                        $("#pmensaje").html("<span class='ui-icon ui-icon-circle-close' style='float:left;'></span>La dirección no existe");
                        $("#mensaje").dialog("open");
                        $("#dialog-preload").dialog("close");
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
                            $("#dialog-preload").dialog("close");
                            postescercanos = data['postesceranos'];
                            //console.log(postescercanos);
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

                            // ciruclo de ubicacion 
                            /* var cityCircle = new google.maps.Circle({
                             strokeOpacity: 0.8,
                             strokeWeight: 2,
                             fillColor: '#' + pinColor,
                             fillOpacity: 0.35,
                             map: map,
                             radius: 100.307937047031,
                             center: myLatLng,
                             label: 'Tiene Cobertura'
                             });*/

                            var marker = new google.maps.Marker({
                                position: myLatLng,
                                map: map,
                                descripcion: '<p><strong>' + formatted_address + '</strong> </p>',
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


                            marker.addListener('click', function (event) {
                                puntoservicio.lat = this.position.lat();
                                puntoservicio.lng = this.position.lng();
                                $("#dialog-confirm").dialog("open");

                            });
                            
                            
                            try{
                                // removiendo marcador 
                                for (var i = 0; i < markers.length; i++) {
                                        markers[i].setMap(null);
                                }
                                //removiendo puntos mas cercanos
                                for (var i = 0; i < markers_around.length; i++) {
                                    markers_around[i].setMap(null);
                                }
                            }catch(err){
                            
                            }

                            markers.push(marker);
                            map.setCenter(myLatLng);
                            map.setZoom(17);
                            

                             // postes cercanos   
                            /*for (var i = 0; i < postescercanos.length; i++) {
                                var g = postescercanos[i];
                                var tag = '<p>' + g.descripcion + ' (' + g.latitudcentral + ',' + g.longitudcentral + ')</p>';
                                addMark(tag, g.numero, g.latitudcentral, g.longitudcentral);

                            }*/
                        }});
                }
            });

        });



       /* $("#txtdireccion").keypress(function (event) {
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
        });*/


        function loadMap() {

            $.ajax({
                url: "rest/cobertura/coberturanodos.html",
                type: "POST",
                datatype: "json",
                success: function (data) {
                    pintarmapa(data);
                }});


            // mensaje
            $("#mensaje").dialog({
                autoOpen: false,
                width: 400,
                buttons: [
                    {
                        text: "Aceptar",
                        click: function () {

                            /*
                             alert("test");
                             var _posteservicio = {codigoPoste: posteservicio.codigoPoste,
                             latitud: posteservicio.latitudcentral,
                             longitud: posteservicio.longitudcentral
                             };
                             var _puntoservicio = {latitud: puntoservicio.lat, longitud: puntoservicio.lng};
                             sendRequest("rest/cobertura/selecionarposteservicio.html", _posteservicio); // enviando posite mas cercano
                             sendRequest("rest/cobertura/selecionarpuntoservcio.html", _puntoservicio); // enviando punto de servicio seleccionado o encontrado en el mapa
                             posteservicio = {};
                             puntoservicio = {};
                             
                             */
                            $(this).dialog("close");
                        }
                    }
                ]
            });

            // confimacion

            $("#dialog-confirm").dialog({
                resizable: false,
                autoOpen: false,
                height: "auto",
                width: 400,
                modal: true,
                buttons: {
                    Aceptar: function () {


                        var _puntoservicio = {latitud: puntoservicio.lat, longitud: puntoservicio.lng};
                        console.log("punto seleccionado :");
                        console.log(_puntoservicio);
                        //sendRequest("rest/cobertura/selecionarpuntoservcio.html", _puntoservicio); // enviando punto de servicio seleccionado o encontrado en el mapa
                    
                         $.ajax({
                            url: "rest/cobertura/selecionarpuntoservcio.html",
                            data: _puntoservicio,
                            type: "POST",
                            datatype: "json",
                            success: function (data) {
                                puntoservicio = {};
                                window.location.href = "solicitud_servicio_generar.xhtml";
                         }});


                        $(this).dialog("close");
                    },
                    Cancelar: function () {
                        $(this).dialog("close");
                    }
                }
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

            flightPlanCoordinates = [];
            circles = [];
            var triangleCoords = [];
            var idx = 0;
            for (var i = 0; i < nodos.length; i++) {
                var g = nodos[i];
                var coor = {lat: g.latitudcentral, lng: g.longitudcentral};
                triangleCoords.push(coor);
                var tag = g.longitudcentral + ', ' + g.longitudcentral;

                idx++;
            }

            coberturaPolygon = new google.maps.Polygon({
                paths: triangleCoords,
                strokeColor: '#0000FF',
                strokeOpacity: 0.4,
                strokeWeight: 0,
                fillColor: '#0000FF',
                fillOpacity: 0.35
            });
            coberturaPolygon.setMap(map);
            
            loadSearcher();
        }

        function loadSearcher() {
            // Create the search box and link it to the UI element.
            var input = document.getElementById('txtdireccion');
            var searchBox = new google.maps.places.SearchBox(input);
            map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

            // Bias the SearchBox results towards current map's viewport.
            map.addListener('bounds_changed', function () {
                searchBox.setBounds(map.getBounds());
            });

            var markers = [];
            // Listen for the event fired when the user selects a prediction and retrieve
            // more details for that place.
            searchBox.addListener('places_changed', function () {
                var places = searchBox.getPlaces();

                if (places.length == 0) {
                    return;
                }

                // Clear out the old markers.
                markers.forEach(function (marker) {
                    marker.setMap(null);
                });
                markers = [];

                // For each place, get the icon, name and location.
               /* var bounds = new google.maps.LatLngBounds();
                places.forEach(function (place) {
                    if (!place.geometry) {
                        console.log("Returned place contains no geometry");
                        return;
                    }
                    var icon = {
                        url: place.icon,
                        size: new google.maps.Size(71, 71),
                        origin: new google.maps.Point(0, 0),
                        anchor: new google.maps.Point(17, 34),
                        scaledSize: new google.maps.Size(25, 25)
                    };

                    // Create a marker for each place.
                    markers.push(new google.maps.Marker({
                        map: map,
                        icon: icon,
                        title: place.name,
                        position: place.geometry.location
                    }));

                    if (place.geometry.viewport) {
                        // Only geocodes have viewport.
                        bounds.union(place.geometry.viewport);
                    } else {
                        bounds.extend(place.geometry.location);
                    }
                });
                map.fitBounds(bounds);*/
            });
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
                draggable: false,
                icon: iconBase + 'info-i_maps.png',
                shadow: pShadow,
                editable: true,
                animation: google.maps.Animation.DROP
            });


            var infowindow = new google.maps.InfoWindow({
                content: marker.descripcion
            });

            marker.addListener('mouseover', function () {
                //console.log('numero: ' + marker.numero);
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
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyChk5fjsxbMDJmHbDA1Ap8fO34fplNvbDE&libraries=places&callback=loadMap">
    </script>

    <div id="mensaje" title="Mensaje">	
        <p id="pmensaje"  > <span class="ui-icon ui-icon-check" style="float:left;"></span>La dirección tiene cobertura <p>

    </div>
    <div id="dialog-confirm" title="Confimación">
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>¿Esta seguro de seleccionar el punto?</p>
    </div>

    <div id="content">
    </div>
</body>
</html>