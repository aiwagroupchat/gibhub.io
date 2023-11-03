package aiwa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestController")
public class TestController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");

		PrintWriter pw = response.getWriter();

		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:" + getServletContext().getRealPath("WEB-INF/gpchat.db");

			Connection conn = DriverManager.getConnection(url);
			String sql = "select "
					+ "	*"
					+ " from "
					+ "messages "
					+ "order by "
					+ "	messageid";

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				pw.println("<h1>" + rs.getString("message") + "</h1>");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
