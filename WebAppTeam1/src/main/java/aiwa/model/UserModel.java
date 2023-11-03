package aiwa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import aiwa.entity.User;

public class UserModel {
	public User getUserById(String userId, ServletContext context) {
		User user = null;
		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:" + context.getRealPath("WEB-INF/gpchat.db");

			Connection conn = DriverManager.getConnection(url);
			String sql = "SELECT * FROM users WHERE userid = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserid(rs.getString("userid"));
				user.setUsername(rs.getString("username"));
				user.setProfilepicture(rs.getString("profilepicture"));
				user.setPassword(rs.getString("password"));
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> findAll(ServletContext context) {
		List<User> result = new ArrayList<>();
		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:" + context.getRealPath("WEB-INF/gpchat.db");

			Connection conn = DriverManager.getConnection(url);
			String sql = "SELECT * FROM users";

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getString("userid"));
				user.setUsername(rs.getString("username"));
				user.setProfilepicture(rs.getString("profilepicture"));
				user.setPassword(rs.getString("password"));
				result.add(user);
			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public User findByIdandPassword(ServletContext context, String id, String password) {

		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:" + context.getRealPath("WEB-INF/gpchat.db");

			Connection conn = DriverManager.getConnection(url);
			String sql = "select * from users where userid = ? and password = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getString("userid"));
				user.setUsername(rs.getString("username"));
				user.setProfilepicture(rs.getString("profilepicture"));
				user.setPassword(rs.getString("password"));

				conn.close();
				return user;

			}

			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
