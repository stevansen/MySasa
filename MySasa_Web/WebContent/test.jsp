<html>
<head>
<script type="text/javascript">
function locate() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(showPos);
	} else {
		alert("Geolocation is not supported by this browser.");
	}
}
function showPos(position) {
	url = "line.jsp?line=10B&lat=" + position.coords.latitude + "&lon="+ position.coords.longitude;
	alert(url);
	window.location.href = url;
}
	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition);
		} else {
			alert("Geolocation is not supported by this browser.");
		}
	}
	function showPosition(position) {
		alert("Latitude: " + position.coords.latitude + "  Longitude: "
				+ position.coords.longitude);
	}
</script>
</head>
<body onload="getLocation()">
	<a class='locate' onclick="locate();">
				<img src="img/Locate.png"/>
	</a>

</body>
</html>
