package hibernate.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Engagement")
public class Engagement {
	
	@Id
	private Integer id;
	private String userId;
	private String followerId;
	private String followerHandle;
	private Integer engageScore;
	
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
	
	@Column(name = "UserId")
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "FollowerId")
	public String getFollowerId() {
		return followerId;
	}
	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}
	
	@Column(name = "FollowerHandle")
	public String getFollowerHandle() {
		return followerHandle;
	}
	public void setFollowerHandle(String followerHandle) {
		this.followerHandle = followerHandle;
	}
	
	@Column(name = "EngageScore")
	public Integer getEngageScore() {
		return engageScore;
	}
	public void setEngageScore(Integer engageScore) {
		this.engageScore = engageScore;
	}
	
	

}
