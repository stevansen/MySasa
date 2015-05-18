<html>
<head>
<script type="text/javascript">
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
</body>
</html>
