package hibernate.bean;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Post")
public class Post {

	@Id
	private Integer id;
	private String ownerId;
	private String postId;
	private String network;
	private Long comments;
	private Long likes;
	private Integer status;
	private Integer errorComment;
	private Integer errorLikes;
	private Timestamp postCreated;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "OwnerId")
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
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
	
	@Column(name = "Comments")
	public Long getComments() {
		return comments;
	}
	public void setComments(Long comments) {
		this.comments = comments;
	}
		

	
	@Column(name = "Likes")
	public Long getLikes() {
		return likes;
	}
	public void setLikes(Long likes) {
		this.likes = likes;
	}
	
	@Column(name = "Status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	

	@Column(name = "LikeError")
	public Integer getErrorComment() {
		return errorComment;
	}
	public void setErrorComment(Integer errorComment) {
		this.errorComment = errorComment;
	}

	
	@Column(name = "CommentError")
	public Integer getErrorLikes() {
		return errorLikes;
	}
	public void setErrorLikes(Integer errorLikes) {
		this.errorLikes = errorLikes;
	}
	
	@Column(name = "PostCreated")
	public Timestamp getPostCreated() {
		return postCreated;
	}
	public void setPostCreated(Timestamp postCreated) {
		this.postCreated = postCreated;
	}
	
	public String toString(){
		return ownerId + " " + postId;
	}
	
}
