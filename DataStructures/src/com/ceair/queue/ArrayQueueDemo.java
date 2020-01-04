package com.ceair.queue;
/**
 * 队列
 * @author huaping
 * @time 2020-01-04 1:03:10 PM
 */
public class ArrayQueueDemo {
	public static void main(String[] args) {
		
	}
	
	class ArrayQueue {
		private int maxSize;
		private int front;
		private int rear;
		private int[] arr;
		
		//创建队列的构造器
		public ArrayQueue (int maxArrSize) {
			maxSize = maxArrSize;
			arr = new int[maxSize];
			front = -1;
			rear = -1;
		}
		//判断队列是否已满
		public boolean isFull() {
			return front == rear;
		}
		//判断队列是否为空
		public boolean isEmpty() {
			return front == maxSize -1;
		}
		//往队列中添加数据
		public void addQueue(int value) {
			if(isFull()) {
				System.out.println("队列已满，不能添加数据");
				return;
			}
			rear++;
			arr[rear] = value;
		}
		//从队列中取数据
		public int getQueue() {
			if(isEmpty()) {
				throw new RuntimeException("队列为空，不能取数据");
			}
			front++;
			return arr[front];
		}
	}
	
	
	
}
