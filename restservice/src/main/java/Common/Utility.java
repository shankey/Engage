package Common;

public class Utility {
	
	public static int SECOND=1000;
	
	public static Boolean isNull(String str){
		
		if(str==null || str.isEmpty()){
			return true;
		}
		
		return false;
	}

}
