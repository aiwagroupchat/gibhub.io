package aiwa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import aiwa.entity.Message;
import aiwa.entity.User;

public class MessageModel {

	public List<Message> findByIdOver(ServletContext context, int id) {
		List<Message> result = new ArrayList<>();
		try {
			//
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:" + context.getRealPath("WEB-INF/gpchat.db");

			Connection conn = DriverManager.getConnection(url);
			String sql = "SELECT * FROM messages m inner join users u on m.userid = u.userid  where messageid > ? ORDER BY messageid";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Message message = new Message();
				message.setMessageid(rs.getInt("messageid"));
				message.setMessage(rs.getString("message"));
				message.setSendAt(rs.getString("sendAt"));

				User user = new User();
				user.setUserid(rs.getString("userid"));
				user.setUsername(rs.getString("username"));
				user.setProfilepicture(rs.getString("profilepicture"));
				message.setUser(user);

				result.add(message);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void insert(ServletContext context, Message m) {

		try {
			Class.forName("org.sqlite.JDBC");

			String url = "jdbc:sqlite:" + context.getRealPath("WEB-INF/gpchat.db");

			Connection conn = DriverManager.getConnection(url);
			String sql = "insert into messages(userid, message, sendAt) values(?,?,datetime('now','localtime'))";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, m.getUser().getUserid());
			stmt.setString(2, m.getMessage());
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
