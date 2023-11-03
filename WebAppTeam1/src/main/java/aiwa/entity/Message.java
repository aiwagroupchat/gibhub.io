package aiwa.entity;

public class Message {
	private int messageid;
	private String message;
	private String sendAt;
	private User user;
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSendAt() {
		return sendAt;
	}
	public void setSendAt(String sendAt) {
		this.sendAt = sendAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
