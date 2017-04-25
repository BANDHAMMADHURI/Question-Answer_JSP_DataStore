<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Answer Process</title>
</head>
<body>
<%!
//String q_Id=qid;
%>
<form name="answerForm" action="answerServlet" method="post"><br>
Question Id :<input type="text" name="question_Id" value="" id="question_Id">
Answer : <input type="text" name="answer" id="answer">
<input type="submit" name="submit">
</form>
</body>
 <script>
	function hide() {

		document.getElementById("button").style.visibility = "hidden";
	}
	function retrieveData() {
		xmlHttp = new XMLHttpRequest();
		xmlHttp.open("GET", "/answerServlet");
		xmlHttp.send();
		xmlHttp.onreadystatechange = function() {
			if ((xmlHttp.readyState == 4) && (xmlHttp.status == 200)) {
				document.getElementById("show").innerHTML = xmlHttp.responseText;
			}

		};
	}
</script> 
</html>
