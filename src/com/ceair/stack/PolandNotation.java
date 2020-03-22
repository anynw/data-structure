package com.ceair.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式算法
 * @author huaping
 * @time 2020-03-15 10:15:37 PM
 */
public class PolandNotation {

	public static void main(String[] args) {
		// expression = "(3+4)*5-6" => "3 4 + 5 * 6 -"
		// String subExpression = "3 4 + 5 * 6 -";//29
		// String subExpression = "30 4 + 5 * 6 -";//29
		// expression = "4 * 5 - 8 + 60 + 8 / 2" => "4 5 * 8 - 60 + 8 2 / +"
		String subExpression = "4 5 * 8 - 60 + 8 2 / +";//76
		List<String> listExprsision = getListExprsision(subExpression);
		System.out.println(listExprsision);
		int cal = cal(subExpression);
		System.out.println(cal);
	}
	
	
	public static int cal(String subExpression) {
		int res = 0;
		Stack<String> stack = new Stack<String>();
		List<String> listExprsision = getListExprsision(subExpression);
		for (String item : listExprsision) {
			// 如果是数，压入栈；如果是符号，弹出两个数进行运算，然后将结果继续压入栈
			if(item.matches("\\d+")) {
				stack.push(item);
			} else {
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				// item 此时是符号
				res = getResByOper(num1, num2, item);
				// 运算结束之后，将运算结果继续压入栈
				stack.push(res + "");
			}
		}
//		return res; 
		return Integer.parseInt(stack.pop());
	}
	
	public static int getResByOper(int num1 ,int num2 ,String oper) {
		int res = 0;
		switch (oper) {
		case "+":
			res = num1 + num2;
			break;
		case "-":
			res = num1 - num2;
			break;
		case "*":
			res = num1 * num2;
			break;
		case "/":
			res = num1 / num2;
			break;
		default:
			throw new RuntimeException("表达式错误");
		}
		return res;
	}
	
	public static List<String> getListExprsision(String subExpression){
		List<String> list = new ArrayList<String>();
		String[] split = subExpression.split(" ");
		for (String item : split) {
			list.add(item);
		}
		return list;
	}

}
