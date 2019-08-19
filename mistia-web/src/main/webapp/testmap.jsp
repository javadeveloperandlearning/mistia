<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>tes1</title>
        <style>
            /* Always set the map height explicitly to define the size of the div
                   * element that contains the map. */
            #map {
                height: 100%;
            }
            /* Optional: Makes the sample page fill the window. */
            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }
            div#botonera-div-top {
                position: absolute;
                top:2%;
                width: 100%;

            }
        </style>
        <script type="text/javascript"	src="${pageContext.request.contextPath}/resources/js/jquery-1.7.2.js"></script>
    </head>
    <body>
        <div id="map"></div>
        <script >
            outarray = [];
            var markers_around = [];
            var window_array = [];
            
            // This example creates a triangular polygon with a hole in it.

            function initMap() {
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 14,
                    center: {lat: -12.055224, lng: -75.195773}//,86, lng: -70.268},
                });

                var pColor = 'ff0000';
                var pImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pColor,
                        new google.maps.Size(21, 34),
                        new google.maps.Point(0, 0),
                        new google.maps.Point(10, 34));

                var pShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
                        new google.maps.Size(40, 37),
                        new google.maps.Point(0, 0),
                        new google.maps.Point(12, 35));

                var marker = new google.maps.Marker({
                    position: {lat: -12.055224, lng: -75.195773},
                    map: map,
                    draggable: true,
                    icon: pImage,
                    shadow: pShadow,
                    editable: true,
                    animation: google.maps.Animation.DROP
                });

                // Define the LatLng coordinates for the polygon's  outer path.
                /* var outerCoords = [
                 {lat: 25.774, lng: -80.190},
                 {lat: 18.466, lng: -66.118},
                 {lat: 32.321, lng: -64.757}
                 ];*/

                // Define the LatLng coordinates for the polygon's inner path.
                // Note that the points forming the inner path are wound in the
                // opposite direction to those in the outer path, to form the hole.
                /*var innerCoords = [
                 {lat: 28.745, lng: -70.579},
                 {lat: 29.570, lng: -67.514},
                 {lat: 27.339, lng: -66.668}
                 ];*/

                // Construct the polygon, including both paths.
                /*var bermudaTriangle = new google.maps.Polygon({
                 paths: [outerCoords, innerCoords],
                 strokeColor: '#FFC107',
                 strokeOpacity: 0.8,
                 strokeWeight: 2,
                 fillColor: '#FFC107',
                 fillOpacity: 0.35
                 });*/


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

                var inner = [
                    {lat: -12.065883894225271, lng: -75.20684515881351},
                    {lat: -12.072094975728042, lng: -75.2090767567139},
                    {lat: -12.075536188625984, lng: -75.20409857678226},
                    {lat: -12.071759245273597, lng: -75.19714629101566},
                    {lat: -12.067310777037077, lng: -75.20152365612796}
                ]

                var inner2 = [
                    {lat: -12.05153070948583, lng: -75.20461356091312},
                    {lat: -12.054972186352584, lng: -75.20667349743655},
                    {lat: -12.058077871484251, lng: -75.2024677937012},
                    {lat: -12.056902751608964, lng: -75.19620215344241},
                    {lat: -12.05320948420321, lng: -75.19826208996585},
                    {lat: -12.051866465269478, lng: -75.20057951855472},
                    {lat: -12.05782606051581, lng: -75.19937788891605}

                ]
                // Construct the polygon.
                bermudaTriangle = new google.maps.Polygon({
                    paths: [triangleCoords, inner, inner2],
                    strokeColor: '#FF0000',
                    strokeOpacity: 0.4,
                    strokeWeight: 2,
                    fillColor: '#FF0000',
                    fillOpacity: 0.35
                });

                //bermudaTriangle.setMap(map);




                google.maps.event.addListener(map, 'click', function (e) {

                    var resultColor = 'blue';
                    var coor = {lat: e.latLng.lat(), lng: e.latLng.lng()};
                    console.log(coor);
                    outarray.push(coor);

                    var _puntoservicio = {latitud: e.latLng.lat(), longitud: e.latLng.lng()};
                    console.log("punto seleccionado :");
                    console.log(_puntoservicio);
                    sendRequest("rest/cobertura/registrarpostetest.html", _puntoservicio); // enviando punto de servicio seleccionado o encontrado en el mapa

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
                // bermudaTriangle.setMap(map);



                $("#btnbuscar").click(function () {

                });




                 $.ajax({
                    url: "rest/cobertura/coberturanodos.html",
                    type: "POST",
                    datatype: "json",
                    success: function (data) {
                        pintarmapa(data);
                 }});


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

                var idx = 0;
                for (var i = 0; i < nodos.length; i++) {
                    var g = nodos[i];

                    // marker blue
                    var tag =  g.latitudcentral+','+g.longitudcentral;
                    var resultColor = 'red';
                    var  marker = new google.maps.Marker({
                        position: {lat: g.latitudcentral, lng: g.longitudcentral},
                        map: map,
                        descripcion:tag,
                        icon: {
                            path: google.maps.SymbolPath.CIRCLE,
                            fillColor: resultColor,
                            fillOpacity: .5,
                            strokeColor: 'white',
                            strokeWeight: .5,
                            scale: 10
                        }
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
                    window_array.push(infowindow);
                    // postes mas cercanos
                    idx++;
                }
                 console.log('cant marker : '+markers_around.length);
                 console.log('cant windows : '+markers_around.length);
            }





        </script>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyChk5fjsxbMDJmHbDA1Ap8fO34fplNvbDE&callback=initMap">
        </script>


        <div id="botonera-div-top"    align="center">

            <button   class="boton" id="btnbuscar">Buscar</button>



        </div>

    </body>
</html>