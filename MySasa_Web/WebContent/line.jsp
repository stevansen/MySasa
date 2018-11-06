<%@page import="it.unibz.mysasa.domain.Fermata"%>
<%@page import="it.unibz.mysasa.domain.LineMap"%>
<%@page import="it.unibz.mysasa.util.Operations"%>
<%@page import="it.unibz.mysasa.domain.LinePos"%>
<%@page import="it.unibz.mysasa.util.ParseData"%>
<%
	String line = request.getParameter("line");
	String ort = request.getParameter("ort");
	String lat = request.getParameter("lat");
	String lon = request.getParameter("lon");
	String clat = "46.5600548";
	String clon = "11.2183212";
	String zoom = "11";
	String disp = "";
	String c = "";
	if(lat!=null && lon!=null && lat.length()>3 && lon.length()>3){
		clat = lat;
		clon = lon;
		zoom = "14";
	}
	Fermata fc = Operations.getFermata(ort);
	if(fc!=null){
		clat = ""+fc.getLat();
		clon = ""+fc.getLon();
		zoom = "14";
	}
	LineMap lm = Operations.getLineMapNr(line);
	if(lm!=null && !lm.getLinea().isEmpty()){
		LinePos p = lm.getLinea().get(0);
		c = "&c="+p.getC();
	}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="apple-touch-icon" href="./img/logo.png" />
<link rel="shortcut icon" href="favicon.ico" />
<title>MySASA</title>
<style type="text/css">
html, body, #basicMap {
	width: 100%;
	height: 100%;
	margin: 0;
}

#nav {
	position: fixed;
	top: 5px;
	left: 40px;
	z-index: 9999;
}

#loc {
	position: fixed;
	top: 5px;
	right: 5px;
	z-index: 9998;
}

.navbar {
	height: 40px;
	width: 80px;
	display: table-cell;
	text-align: center;
	vertical-align: middle;
	border-radius: 30%;
	background: white;
}

a:link {
	text-decoration: none;
	font-weight: bold;
	color: #000000;
}

a:visited {
	text-decoration: none;
	font-weight: bold;
	color: #000000;
}
</style>
<script src="https://openlayers.org/api/OpenLayers.js"
	type="text/javascript"></script>
<script
	src="https://svn.osgeo.org/metacrs/proj4js/trunk/lib/proj4js-combined.js"
	type="text/javascript"></script>

<script>



	Proj4js.defs["EPSG:25832"] = "+proj=utm +zone=32 +ellps=GRS80 +units=m +no_defs";
	var fPrj = new OpenLayers.Projection("EPSG:4326"); 
	var fromPrj = new OpenLayers.Projection("EPSG:25832"); // UTM 32N ETRF 2000
	var toPrj = new OpenLayers.Projection("EPSG:900913"); // to Spherical Mercator Projection

	function locate() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition);
		} else {
			alert("Geolocation is not supported by this browser.");
		}
	}
	function showPosition(position) {
		url = 'line.jsp?line=<%=line%>&lat=' + position.coords.latitude + '&lon='+ position.coords.longitude;
		window.location.href = url;
	}
	
	function setBus(lon, lat, markers) {
		var size = new OpenLayers.Size(20, 20);
		var offset = new OpenLayers.Pixel(-(size.w / 2), -(size.h / 2));
		var icon = new OpenLayers.Icon('img/logo.png', size, offset);
		markers.addMarker(new OpenLayers.Marker(new OpenLayers.LonLat(lon, lat).transform(
				fromPrj, toPrj), icon));
	}
	
	function setFermata(lon, lat, markers) {
		var size = new OpenLayers.Size(20, 20);
		var offset = new OpenLayers.Pixel(-(size.w / 2), -(size.h / 2));
		var icon = new OpenLayers.Icon('svg/img.svg?<%=c%>', size, offset);
		markers.addMarker(new OpenLayers.Marker(new OpenLayers.LonLat(lon, lat)
				.transform(fPrj, toPrj), icon));
	}

	OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, {
		defaultHandlerOptions : {
			'single' : true,
			'double' : false,
			'pixelTolerance' : 0,
			'stopSingle' : false,
			'stopDouble' : false
		},

		initialize : function(options) {
			this.handlerOptions = OpenLayers.Util.extend({},
					this.defaultHandlerOptions);
			OpenLayers.Control.prototype.initialize.apply(this, arguments);
			this.handler = new OpenLayers.Handler.Click(this, {
				'click' : this.trigger
			}, this.handlerOptions);
		},

		trigger : function(e) {
			var lonlat = map.getLonLatFromPixel(e.xy);
			lonlat = lonlat.transform(toPrj, fromPrj);
			window.open("tboard.jsp?lat=" + lonlat.lat + "&lon=" + lonlat.lon);
		}

	});

	function init() {

		map = new OpenLayers.Map("basicMap");
		var mapnik = new OpenLayers.Layer.OSM();
		var position = new OpenLayers.LonLat(<%=clon%>, <%=clat%>).transform(fPrj, toPrj);
		var zoom = <%=zoom%>;

		map.addLayer(mapnik);
		map.setCenter(position, zoom);

		var m = new OpenLayers.Layer.Markers("Fermate");
		map.addLayer(m);

		var m2 = new OpenLayers.Layer.Markers("Lines");
		map.addLayer(m2);
	<%if(lm!=null){
		for(LinePos l : lm.getLinea()){
		 disp = l.getName();
		%>
		setBus(<%=l.getX()%>, <%=l.getY()%>, m2);
		<%}
		for(Fermata f: lm.getFermate()){%>
		setFermata(<%=f.getLon()%>, <%=f.getLat()%>, m);
	<%}}%>
	var click = new OpenLayers.Control.Click();
		map.addControl(click);
		click.activate();
	}
</script>
</head>
<body onload="init();">
	<div id="basicMap">
		<div id="nav">
			<a class='navbar' href='index.jsp'><img
				src='./svg/img.svg?<%=c%>' width="20px" height="20px">&nbsp;<%=disp%></a>
		</div>
		<div id="loc">
			<a class='locate' onclick="locate();">
				<img src="img/Locate.png" width="40px" height="40px"/>
			</a>
		</div>
	</div>
</body>
</html>