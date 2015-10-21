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
	private Integer postId;
	private Integer network;
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
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	
	@Column(name = "Network")
	public Integer getNetwork() {
		return network;
	}
	
	public void setNetwork(Integer network) {
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
