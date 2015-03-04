<%@page import="it.unibz.mysasa.util.Operations"%>
<%@page import="it.unibz.mysasa.domain.Fermata"%>
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
</style>
<script src="http://openlayers.org/api/OpenLayers.js"
	type="text/javascript"></script>
<script>
	var fromPrj = new OpenLayers.Projection("EPSG:4326"); // Transform from WGS 1984
	var toPrj = new OpenLayers.Projection("EPSG:900913"); // to Spherical Mercator Projection

	function setMarker(lon, lat, markers) {
		var size = new OpenLayers.Size(20, 20);
		var offset = new OpenLayers.Pixel(-(size.w / 2), -(size.h / 2));
		var icon = new OpenLayers.Icon('svg/img.svg?t=Station', size, offset);
		markers.addMarker(new OpenLayers.Marker(new OpenLayers.LonLat(lon, lat)
				.transform(fromPrj, toPrj), icon));
	}
	
	OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, {                
        defaultHandlerOptions: {
            'single': true,
            'double': false,
            'pixelTolerance': 0,
            'stopSingle': false,
            'stopDouble': false
        },

        initialize: function(options) {
            this.handlerOptions = OpenLayers.Util.extend(
                {}, this.defaultHandlerOptions
            );
            OpenLayers.Control.prototype.initialize.apply(
                this, arguments
            ); 
            this.handler = new OpenLayers.Handler.Click(
                this, {
                    'click': this.trigger
                }, this.handlerOptions
            );
        }, 

        trigger: function(e) {
            var lonlat = map.getLonLatFromPixel(e.xy);
            lonlat = lonlat.transform(toPrj, fromPrj);
            window.open("stationboard.jsp?lat="+ lonlat.lat+"&lon="+lonlat.lon);
        }

    });

	function init() {
		map = new OpenLayers.Map("basicMap");
		var mapnik = new OpenLayers.Layer.OSM();
		var position = new OpenLayers.LonLat(11.2183212, 46.5600548).transform(
				fromPrj, toPrj);
		var zoom = 11;

		map.addLayer(mapnik);
		map.setCenter(position, zoom);

		var m = new OpenLayers.Layer.Markers("Markers");
		map.addLayer(m);
	<%for(Fermata f : Operations.getFermate()){%>
		setMarker(<%=f.getLon()%>, <%=f.getLat()%>, m);
    <%}%>
	    
    
    	var click = new OpenLayers.Control.Click();
    	map.addControl(click);
    	click.activate();
        
	}
</script>
</head>
<body onload="init();">
	<div id="basicMap"></div>
</body>
</html>
