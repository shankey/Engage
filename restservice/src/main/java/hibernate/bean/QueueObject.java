package hibernate.bean;

public class QueueObject implements Comparable<QueueObject>{
	
	public static Integer USER = 1;
	public static Integer LIKE = 2;
	public static Integer COMMENT = 3;
	
	public Integer type;
	public Object obj;
	
	
	public QueueObject(Integer type, Object obj)  {
		super();
		this.type = type;
		this.obj = obj;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public String toString(){
		return type + " "+ obj.toString();
	}
	@Override
	public int compareTo(QueueObject o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
