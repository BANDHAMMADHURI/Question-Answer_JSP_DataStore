<%@ page
	import="com.google.appengine.api.datastore.DatastoreService,javax.servlet.http.HttpServletRequest"%>
<%@ page
	import="com.google.appengine.api.datastore.DatastoreServiceFactory,com.google.appengine.api.datastore.Entity,com.google.appengine.api.datastore.Key,com.google.appengine.api.datastore.PreparedQuery,com.google.appengine.api.datastore.Query"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuestionProcess</title>
</head>
<body>
	<table border="1">
		<tr>
			<td><b>Q.no</b></td>
			<td><b>Question</b></td>
			<td><b>QuestionId</b></td>
			<td><b>Answers</b></td>
		</tr>
		<%!Key qid;
	String que;%>
		<%
			Query query = new Query("Questions");
			DatastoreService data = DatastoreServiceFactory.getDatastoreService();
			PreparedQuery pq = data.prepare(query);
			int i = 1;
			for (Entity e : pq.asIterable()) {
				que = (String) e.getProperty("question");
				qid = e.getKey();
		%>
		<tr>
			<td><%=i%></td>
			<td><%=que%></td>
			<td><%=qid%></td>
			<!-- <td><input type="button" value="Add Answer"
				onclick="window.location.href='answer.html'"></td> -->
			<td><input type="button" value="Add Answer" onclick="sendData()"></td>
		</tr>
		<%
			i++;
			}
		%>
	</table>
</body>
<!-- <script type="text/javascript">
	function s() {
		alert("hi");
	}
</script> -->
<script>
function sendData()
{
	var questionId=qid;
	json={
			"Q_Id" : questionId;
	}
	jsonObj=JSON.stringify(json);
	xmlHttp = new XMLHttpRequest();
	xmlHttp.open("GET", "/AnswerProcess.jsp?param="+jsonObj);
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if ((xmlHttp.readyState == 4) && (xmlHttp.status == 200)) {
			document.getElementById("body").innerHTML = xmlHttp.responseText;
		}

	};
	}
</script>
</html>