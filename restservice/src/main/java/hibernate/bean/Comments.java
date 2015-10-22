package hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Id;

public class Comments {
	
	@Id
	private Integer id;
	private String postId;
	private String commentHandle;
	
	@Id
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "PostId")
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	
	@Column(name = "CommentHandle")
	public String getCommentHandle() {
		return commentHandle;
	}
	public void setCommentHandle(String commentHandle) {
		this.commentHandle = commentHandle;
	}
	
	public String toString(){
		return commentHandle + " " + postId;
	}

}
