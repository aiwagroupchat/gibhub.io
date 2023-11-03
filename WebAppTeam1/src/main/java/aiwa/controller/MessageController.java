package aiwa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aiwa.entity.Message;
import aiwa.entity.User;
import aiwa.model.MessageModel;

@WebServlet("/MessageController")
public class MessageController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		System.out.println(id);

		response.setCharacterEncoding("UTF-8");
		MessageModel mm = new MessageModel();
		List<Message> messages = mm.findByIdOver(getServletContext(), Integer.parseInt(id));

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(messages);

		PrintWriter pw = response.getWriter();
		pw.println(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userid");
		String message = request.getParameter("message");

		Message m = new Message();
		User u = new User();
		u.setUserid(userId);
		m.setMessage(message);
		m.setUser(u);

		MessageModel mm = new MessageModel();
		mm.insert(getServletContext(), m);
	}
}
