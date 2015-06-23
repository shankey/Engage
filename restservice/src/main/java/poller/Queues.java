package poller;

import hibernate.bean.Url;

import java.util.PriorityQueue;
import java.util.Queue;

public class Queues {
	
	private static Queue<Url> urlQueue = new PriorityQueue<Url>();
	
	public static Queue<Url> getUrlQueue(){
		return urlQueue;
	}

}
