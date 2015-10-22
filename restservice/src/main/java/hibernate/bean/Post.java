package hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Post")
public class Post {

	@Id
	private Integer id;
	private Integer userId;
	private String postId;
	private String network;
	private Integer likes;
	private Integer comments;
	
	@Id
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "UserId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "PostId")
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	
	@Column(name = "Network")
	public String getNetwork() {
		return network;
	}
	
	public void setNetwork(String network) {
		this.network = network;
	}
	
	@Column(name = "Likes")
	public Integer getLikes() {
		return likes;
	}
	
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	
	
	@Column(name = "Comments")
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}

	
}
