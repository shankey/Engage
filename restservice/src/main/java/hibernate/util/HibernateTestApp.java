package hibernate.util;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.bao.LikesBAO;
 
 
public class HibernateTestApp {
	
	public static void main(String[] args) {
//		new TimelineBAO().getTimelineData("781685528");
		new LikesBAO().getLikesData("1099386911917098329_781685528", InstaAPIEndPoints.ACCESS_TOKEN);
		
 
		
	}
}