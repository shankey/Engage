package hibernate.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "Test")
public class Test {
	
	@Id
	private int Id;
	private String name;
	
	
 
	public Test() {
	}
 
	public Test(int Id, String name
			) {
		this.Id = Id;
		this.name = name;
	
	
	}
 
	@Id
	@Column(name = "Id", unique = true, nullable = false, precision = 5, scale = 0)
	public int Id() {
		return this.Id;
	}
 
	public void setId(int Id) {
		this.Id = Id;
	}
 
	@Column(name = "Name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
  
	
}
