<%@page import="it.unibz.mysasa.util.Tools"%>
<%@page import="it.unibz.mysasa.domain.timetable.TiRides"%>
<%@page import="it.unibz.mysasa.domain.timetable.TiTable"%>
<%@page import="it.unibz.mysasa.util.Operations"%>
<%
  String lat = request.getParameter("lat");
  String lon = request.getParameter("lon");
  String mode = request.getParameter("mode");
  TiTable t = null;
  if(mode!=null && mode.equals("gps")){
	  t = Operations.getClosestTimetable(lat, lon);
  }else{
	  t = Operations.getClosestTimetableConv(lat, lon);
  }
%>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<link rel="apple-touch-icon" href="./img/logo.png" />
    <link rel="shortcut icon" href="favicon.ico" />
	<style type="text/css">
		.ui-icon-mysasa-loc {
			background-image: url("img/Locate.png");
		}
	</style>
	<title>MySASA</title>
	<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
	<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
	<script	src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	<script>
	function locate() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition);
		} else {
			alert("Geolocation is not supported by this browser.");
		}
	}
	function showPosition(position) {
		url = "tboard.jsp?mode=gps&lat=" + position.coords.latitude + "&lon="+ position.coords.longitude;
		window.location.href = url;
	}
	</script>
</head>
<body>
  <%if(t!=null){%>
    <div data-role="header">
    	<a data-role="button" href="index.jsp" data-icon="home" data-iconpos="left" class="ui-btn-left" rel="external">
      	Home
    	</a>
		<h1>
		<%=t.stationname%>
		</h1>
		<a onclick="locate();" rel="external" class="ui-btn-right">
      		<img src="img/Locate.png" width="30px" height="30px"/>
    	</a>
	</div>
    <table data-role="table" data-mode="reflow" class="ui-responsive table-stroke">
    <thead>
      <tr>
    	<th data-priority="1">Linie</th>
    	<th style="width:10%">Abfahrt</th>
        <th style="width:10%"> </th>
		<th data-priority="4">Ziel</th>
	  </tr>
    </thead>
    <tbody>
    <%if(t.rides!=null) for(TiRides r : t.rides){%>
    	<tr>
    		<td><%=r.lidname%></td>
    		<td><%=r.departure%></td>
    		<td><%=Tools.getFormatSec(r.delay_sec)%></td>
    		<td><%=r.last_station%></td>
    	</tr>
    <%}%>
    </tbody>
    <tfoot>
    <tr>
    		<td>Station: <%=t.ortnr%></td>
    	</tr>
    </tfoot>
    </table>
  <%}%>
</body>
</html>