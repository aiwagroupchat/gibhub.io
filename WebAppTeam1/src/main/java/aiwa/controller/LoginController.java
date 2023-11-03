package aiwa.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aiwa.entity.User;
import aiwa.model.UserModel;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doGet called");

		//1.PARAMETER
		// none

		//2.MODEL
		// userの一覧を6get
		UserModel u = new UserModel();
		List<User> users = u.findAll(getServletContext());

		//3.VIEW
		// userの一覧を渡す
		// Login画面を開く

		request.setAttribute("users", users);

		request.getRequestDispatcher("/login.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("doPost called");

		//1.PARAMETER
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");

		//2.MODEL
		UserModel u = new UserModel();
		User user = u.findByIdandPassword(getServletContext(), userid, password);

		//3.VIEW
		if (user == null) {
			List<User> users = u.findAll(getServletContext());
			request.setAttribute("users", users);
			request.setAttribute("userid", userid);

			request.setAttribute("errormessage", "Wrong password");
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);

			response.sendRedirect("index.jsp");
		}

	}
}
