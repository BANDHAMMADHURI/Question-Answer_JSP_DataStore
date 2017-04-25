package com.full.datastore;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class AnswerServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String q_id = req.getParameter("question_Id");
		System.out.println(q_id);
		String answer = req.getParameter("answer");

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Query queryQue = new Query("Questions");
		PreparedQuery qAns = datastore.prepare(queryQue);
		for (Entity e : qAns.asIterable()) {

			String qid = e.getKey().toString();
			if (qid.equals(q_id)) {

				Entity entityAns = new Entity("Answers");
				entityAns.setProperty("answer", answer);
				entityAns.setProperty("q_id", q_id);

				datastore.put(entityAns);
				out.println("successfully added");
			} 
			
		}
		out.println("<br><a href='Main.html'>Home Page</a><br>");
	}
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		int id=1;
		Query query = new Query("Questions");
		DatastoreService data = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery pq = data.prepare(query);

		for (Entity e : pq.asIterable()) {
			String que = (String) e.getProperty("question");
			Key qid = e.getKey();
			out.println(id + " Question : " + que + "<br>  Its Id : " + qid+"<br>");
			id++;
		}
		out.println("<br><a href='Main.html'>Home Page</a>");
	}
}
