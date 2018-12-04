package application;

import java.io.Serializable;

public class Review implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Review [user=" + user + ", review=" + review + "]";
	}
	public Review () {
		
	}

	private User user;
	private String review;

	public Review(User user, String review) {
		super();
		this.user = user;
		this.review = review;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

}
