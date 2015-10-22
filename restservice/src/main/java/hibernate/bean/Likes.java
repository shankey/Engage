package hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Id;

public class Likes {
	
	@Id
	private Integer id;
	private String postId;
	private String likeHandle;
	
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
	
	@Column(name = "LikeHandle")
	public String getLikeHandle() {
		return likeHandle;
	}
	public void setLikeHandle(String likeHandle) {
		this.likeHandle = likeHandle;
	}

	public String toString(){
		return likeHandle + " " + postId;
	}
}
