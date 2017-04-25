package com.full.datastore;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class QuestionsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		Query query=new Query("Questions");
		DatastoreService data=DatastoreServiceFactory.getDatastoreService();
		PreparedQuery pq=data.prepare(query);
		int i=1;
		for(Entity e:pq.asIterable())
		{   
			String que = (String) e.getProperty("question");
			// qid= e.getKey();
			Key qid=  e.getKey();
			//List questionIdList=new ArrayList();
			//PojoClass.questionIdList.add(qid);
			out.println(i+"  Question is : <i>"+que+"</i> <br> <tb>   Its Id : "+qid+"<br>");
			i++;
			//out.println(que+"<input type='button' value='add Answer' onclick='answer.html'");
			//out.println("<br><a href='answer.html' q_id='qid'>"+que+"</a><br> and the id is : " +qid);
			
			//RequestDispatcher rd=req.getRequestDispatcher("answer.html");  
			
			//rd.forward(req, resp);
			//req.setAttribute("question",(Object)que);
			//out.println("<script src='myScript.js'></script>");
		}
		
		out.println("<br>===============");
		/*out.println("<br>Wanna Ask a Question : click <a href='questions.html'>here</a>");
		out.println("<br>want to add answer?");
		out.println("<a href='answer.html'>click here</a>");
		//out.println("hi");
		out.println("<br><a href='Main.html'>Home Page</a>");*/
		out.println("<br>If you want to add an ANSWER , please make a note of <i>Question_Id</i>");
		out.println("<br><a href='answer.html'>Add an Answer</a>");
		out.println("<br><a href='questions.html'>Add a Question</a>");
		out.println("<br><a href='Main.html'>Home Page</a>");


	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
	//	int id=1;
		String question=req.getParameter("question");
		DatastoreService data=DatastoreServiceFactory.getDatastoreService();
		Entity ent=new Entity("Questions");
		ent.setProperty("question", question);
		//ent.setProperty("q_id", id);
		data.put(ent);
		out.println("successfully added");
		out.println("<a href='Main.html'>Home Page</a>");
		//id++;
	}
}