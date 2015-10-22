package hibernate.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User implements Comparable<Url> {
	
	@Id
	private Integer id;
	private String handle;
	private String network;
	private Integer mentions;
	private String location;
	private String bioData;
	private Integer status;
	private String userId;
	
	
	@Id
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Column(name = "Handle")
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	
	@Column(name = "Network")
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	
	@Column(name = "Mentions")
	public Integer getMentions() {
		return mentions;
	}
	public void setMentions(Integer mentions) {
		this.mentions = mentions;
	}
	
	@Column(name = "Location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name = "Biodata")
	public String getBioData() {
		return bioData;
	}
	public void setBioData(String bioData) {
		this.bioData = bioData;
	}
	
	@Column(name = "Status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "UserId")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	@Override
	public int compareTo(Url o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}