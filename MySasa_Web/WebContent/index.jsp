<%@page import="it.unibz.mysasa.domain.LinePos"%>
<%@page import="it.unibz.mysasa.util.ParseData"%>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<link rel="apple-touch-icon" href="./img/logo.png" />
    <link rel="shortcut icon" href="favicon.ico" />
	<title>MySASA</title>
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	<script	src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	<script type="text/javascript">
	$('#something').click(function() {
	    location.reload();
	});
	</script>
</head>
<body>
	<div data-role="page" id="stations">
    	<div data-role="content">
		<ul data-role="listview" data-filter="true" data-inset="true">
		<%
			ParseData pd = new ParseData();
			for (LinePos p : pd.getPosition()) {
		%>
		<li>
			<a href="line.jsp?line=<%=p.getLine()%>&ort=<%=p.getOrt()%>" data-ajax="false" data-rel="external">
			<div>
			<img src="./svg/img.svg?c=<%=p.getC()%>" width="30px" height="30px" />
			<%=p.getName()%> - <%=p.getOrtname()%>
			</div>
			</a>
		</li>
		<% 
			}
		%>
	    </ul>
		</div>
	</div>
</body>
</html>