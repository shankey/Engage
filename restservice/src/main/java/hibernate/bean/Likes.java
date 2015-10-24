package hibernate.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Likes")
public class Likes {
	
	@Id
	private Integer id;
	private String postId;
	private String likeHandle;
	private String likeId;
	
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
	
	@Column(name = "LikeId")
	public String getLikeId() {
		return likeId;
	}
	public void setLikeId(String userId) {
		this.likeId = userId;
	}
	
	
	public String toString(){
		return likeHandle + " " + postId;
	}
}
