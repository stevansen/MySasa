<%@page import="it.unibz.mysasa.domain.Fermata"%>
<%@page import="it.unibz.mysasa.domain.LineMap"%>
<%@page import="it.unibz.mysasa.util.Operations"%>
<%@page import="it.unibz.mysasa.domain.LinePos"%>
<%@page import="it.unibz.mysasa.util.ParseData"%>
<%
  	String line = request.getParameter("line");
	String c = "";
	LineMap lm = Operations.getLineMap(line);
	
	if(lm!=null && !lm.getLinea().isEmpty()){
		LinePos p = lm.getLinea().get(0);
		c = p.getC();
	}
%>
<!DOCTYPE html>
<html>
  <head>
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<link rel="apple-touch-icon" href="./img/logo.png" />
    <link rel="shortcut icon" href="favicon.ico" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
	<title>MySASA</title>
    <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBUdvoCmHO2oWjm5QSzbNJ8VX3JtzqVBmM&v=3.exp&sensor=true"></script>
    <script>    
    
function initialize() {
  var myLatlng = new google.maps.LatLng(46.5600548, 11.2183212);
  var mapOptions = {
    zoom: 10,
    center: myLatlng
  }
  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
  var image =  'img/logoSmall.png';
      
  <%if(lm!=null){
	  int i = 0;
	  for(Fermata f: lm.getFermate()){
		  	i++;
		  %>
		  var marker<%=i%> = new google.maps.Marker({
		      position: new google.maps.LatLng(<%=f.getLat()%>, <%=f.getLon()%>),
		      map: map
		  });  
	  <%  }
	  for(LinePos l : lm.getLinea()){
		  i++;
  %>
  var marker<%=i%> = new google.maps.Marker({
      position: new google.maps.LatLng(<%=l.getLat()%>, <%=l.getLon()%>),
      map: map,
      icon: image
  });
  <%  }
	  
	}%>
}

google.maps.event.addDomListener(window, 'load', initialize);

    </script>
  </head>
  <body>
    <div id="map-canvas"></div>
  </body>
</html>