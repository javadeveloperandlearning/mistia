<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="//maps.google.com/maps/api/js?sensor=false"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

    <script>
    var distanceLimit = 2000; //in meters
    var numberRandomPoints = 200;
    var mapZoomLevel = 11;
    var locationindex = 0;
    var locations = [
        {'name': 'Oxford, England', 'latitude': 51.73213, 'longitude': -1.20631},
        {'name': 'Quito, Ecuador', 'latitude': -0.2333, 'longitude': -78.5167},
        {'name': 'Ushuaia, Argentina', 'latitude': -54.8000, 'longitude': -68.3000},
        {'name': 'McMurdo Station, Antartica', 'latitude': -77.847281, 'longitude': 166.667942},
        {'name': 'Norilsk, Siberia', 'latitude': 69.3333, 'longitude': 88.2167},
        {'name': 'Greenwich, England', 'latitude': 51.4800, 'longitude': 0.0000},
        {'name': 'Suva, Fiji', 'latitude': -18.1416, 'longitude': 178.4419},
        {'name': 'Tokyo, Japan', 'latitude': 35.6833, 'longitude': 139.6833},
        {'name': 'Mumbai, India', 'latitude': 18.9750, 'longitude': 72.8258},
        {'name': 'New York, USA', 'latitude': 40.7127, 'longitude': -74.0059},
        {'name': 'Moscow, Russia', 'latitude': 55.7500, 'longitude': 37.6167},
        {'name': 'Cape Town, South Africa', 'latitude': -33.9253, 'longitude': 18.4239},
        {'name': 'Cairo, Egypt', 'latitude': 30.0500, 'longitude': 31.2333},
        {'name': 'Sydney, Australia', 'latitude': -33.8650, 'longitude': 151.2094},
    ];
    </script>
</head>
<body>
<div id="topbar">
    <select id="location_switch">
    <script>
        for (i=0; i<locations.length; i++) {
            document.write('<option value="' + i + '">' + locations[i].name + '</option>');
        }
    </script>
    </select>
    <img src="http://google.com/mapfiles/ms/micons/ylw-pushpin.png" style="height:15px;"> = Center
    <img src="https://maps.gstatic.com/mapfiles/ms2/micons/red.png" style="height:15px;"> = No Longitude Adjustment
    <img src="https://maps.gstatic.com/mapfiles/ms2/micons/pink.png" style="height:15px;"> = With Longitude Adjustment (var xp = x / Math.cos(y0);)
</div>

<div id="map_canvas" style="position:absolute; top:30px; left:0px; height:100%; height:calc(100% - 30px); width:100%;overflow:hidden;"></div>

<script>
var markers = [];
var currentcircle;

//Create the default map
var mapcenter = new google.maps.LatLng(locations[locationindex].latitude, locations[locationindex].longitude);
var myOptions = {
    zoom: mapZoomLevel,
    scaleControl: true,
    center: mapcenter
};
var map = new google.maps.Map(document.getElementById('map_canvas'), myOptions);

//Draw default items
var centermarker = addCenterMarker(mapcenter, locations[locationindex].name + '<br>' + locations[locationindex].latitude + ', ' + locations[locationindex].longitude);
var mappoints = generateMapPoints(locations[locationindex], distanceLimit, numberRandomPoints);
drawRadiusCircle(map, centermarker, distanceLimit);
createRandomMapMarkers(map, mappoints);

//Create random lat/long coordinates in a specified radius around a center point
function randomGeo(center, radius) {
    var y0 = center.latitude;
    var x0 = center.longitude;
    var rd = radius / 111300; //about 111300 meters in one degree

    var u = Math.random();
    var v = Math.random();

    var w = rd * Math.sqrt(u);
    var t = 2 * Math.PI * v;
    var x = w * Math.cos(t);
    var y = w * Math.sin(t);

    //Adjust the x-coordinate for the shrinking of the east-west distances
    var xp = x / Math.cos(y0);

    var newlat = y + y0;
    var newlon = x + x0;
    var newlon2 = xp + x0;

    return {
        'latitude': newlat.toFixed(5),
        'longitude': newlon.toFixed(5),
        'longitude2': newlon2.toFixed(5),
        'distance': distance(center.latitude, center.longitude, newlat, newlon).toFixed(2),
        'distance2': distance(center.latitude, center.longitude, newlat, newlon2).toFixed(2),
    };
}

//Calc the distance between 2 coordinates as the crow flies
function distance(lat1, lon1, lat2, lon2) {
    var R = 6371000;
    var a = 0.5 - Math.cos((lat2 - lat1) * Math.PI / 180) / 2 + Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) * (1 - Math.cos((lon2 - lon1) * Math.PI / 180)) / 2;
    return R * 2 * Math.asin(Math.sqrt(a));
}

//Generate a number of mappoints
function generateMapPoints(centerpoint, distance, amount) {
    var mappoints = [];
    for (var i=0; i<amount; i++) {
        mappoints.push(randomGeo(centerpoint, distance));
    }
    return mappoints;
}

//Add a unique center marker
function addCenterMarker(centerposition, title) {
    
    var infowindow = new google.maps.InfoWindow({
        content: title
    });
    
    var newmarker = new google.maps.Marker({
        icon: 'http://google.com/mapfiles/ms/micons/ylw-pushpin.png',
        position: mapcenter,
        map: map,
        title: title,
        zIndex: 3
    });
    
    google.maps.event.addListenerOnce(map, 'tilesloaded', function() {
        infowindow.open(map,newmarker);
    });
    
    markers.push(newmarker);
    return newmarker;
}

//Draw a circle on the map
function drawRadiusCircle (map, marker, distance) {
    currentcircle = new google.maps.Circle({
        map: map,
        radius: distance
    });
    currentcircle.bindTo('center', marker, 'position');
}

//Create markers for the randomly generated points
function createRandomMapMarkers(map, mappoints) {
    for (var i = 0; i < mappoints.length; i++) {
        //Map points without the east/west adjustment
        var newmappoint = new google.maps.LatLng(mappoints[i].latitude, mappoints[i].longitude);
        var marker = new google.maps.Marker({
            position:newmappoint,
            map: map,
            title: mappoints[i].latitude + ', ' + mappoints[i].longitude + ' | ' + mappoints[i].distance + 'm',
            zIndex: 2
        });
        markers.push(marker);

        //Map points with the east/west adjustment
        var newmappoint = new google.maps.LatLng(mappoints[i].latitude, mappoints[i].longitude2);
        var marker = new google.maps.Marker({
            icon: 'https://maps.gstatic.com/mapfiles/ms2/micons/pink.png',
            position:newmappoint,
            map: map,
            title: mappoints[i].latitude + ', ' + mappoints[i].longitude2 + ' | ' + mappoints[i].distance2 + 'm',
            zIndex: 1
        });
        markers.push(marker);
    }
}

//Destroy all markers
function clearMarkers() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
}

$('#location_switch').change(function() {
    var newlocation = $(this).val();
    
    clearMarkers();

    mapcenter = new google.maps.LatLng(locations[newlocation].latitude, locations[newlocation].longitude);
    map.panTo(mapcenter);
    centermarker = addCenterMarker(mapcenter, locations[newlocation].name + '<br>' + locations[newlocation].latitude + ', ' + locations[newlocation].longitude);
    mappoints = generateMapPoints(locations[newlocation], distanceLimit, numberRandomPoints);

    //Draw default items
    currentcircle.setMap(null);
    drawRadiusCircle(map, centermarker, distanceLimit);
    createRandomMapMarkers(map, mappoints);
});
</script>
</body>
</html>