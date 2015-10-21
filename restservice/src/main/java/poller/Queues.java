package poller;

import hibernate.bean.User;

import java.util.PriorityQueue;
import java.util.Queue;

public class Queues {
	
	private static Queue<User> urlQueue = new PriorityQueue<User>();
	
	public static Queue<User> getUrlQueue(){
		return urlQueue;
	}

}
