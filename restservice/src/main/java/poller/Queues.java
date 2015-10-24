package poller;

import hibernate.bean.QueueObject;

import java.util.PriorityQueue;
import java.util.Queue;

public class Queues {
	
	private static Queue<QueueObject> queue = new PriorityQueue<QueueObject>();
	
	public static Queue<QueueObject> getQueue(){
		return queue;
	}

}
