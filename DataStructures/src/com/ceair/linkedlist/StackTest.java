package com.ceair.linkedlist;

import java.util.Stack;
/**
 * 栈stack的特点测试：先进后出
 * @author huaping
 * @time 2020-03-14 6:20:23 PM
 */
public class StackTest {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		
		stack.add("a");
		stack.add("b");
		stack.add("c");
		
		String peek = stack.peek();
		System.out.println(peek);
		
		while(stack.size() > 0) {
			System.out.println(stack.pop());
		}
	}

}
