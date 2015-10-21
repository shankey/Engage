package com.engage.bao;

import com.engage.api.wrapper.InstaAPIEndPoints;
import com.engage.api.wrapper.InstaAPIWrapper;
import com.engage.common.Utility;

public class TimelineBAO {
	
	public void getTimelineData(String id){
		InstaAPIWrapper.callTimeLine(InstaAPIEndPoints.TIMELINE_FEED, InstaAPIEndPoints.ACCESS_TOKEN, 
				Utility.get30DayOldTimeStamp(), null);
	}

}
