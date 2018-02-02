package com.kxy.demo1.day2;

import java.util.ArrayList;
import java.util.List;

public class FinallyAndReturn {

	public static void main(String[] args) {
		String name = new Data().doTest();
		System.out.println("result : " + name);
		System.out.println(new Data().doTest2().get(0));
	}
}

class Data{
	/**
	 * 结论： 对于基本类型：
	 * 		1. 若try ， 或者 finally 中有 return， try代码之外，最后面不能有 return了， 程序已经在
	 * 		   try，或者 finally中的return结束了，后面的return编译都通不过
	 * 		2. finally中的代码一定会被执行的
	 * 			a. 只要 finally中有return，程序就会在finally中结束
	 * 			b. 只有 try中有return, finally中修改name值，修改的name值只在finally块中起作用，
	 * 			        程序还是在 try的return结束，返回值，还是 try块中的name值。catch块同理，在哪个
	 * 			        块中结束，name值就是那个块中的值
	 * @return
	 */
	public String doTest() {
		String name = "";
		try {
			//int i = 1 / 0;
			name = "I am try";
			System.out.println("try : " + name);
			return name;
		} catch (Exception e) {
			name = "I am catch";
			System.out.println("catch : " + name);
			return name;
		}finally {
			name = "I am finally";
			System.out.println("fianlly : " + name);
			//return name;
		}
		//return name;
	}
	
	/**
	 * 测试引用数据类型
	 * 结论： 对于引用数据类型，对于同一个引用，finally修改引用的对象值，会影响结果， 运行的结果是  3 
	 * @return
	 */
	public List<String> doTest2() {
		List<String> list = new ArrayList<>();
		list.add("2");
		try {
			return list;
		} catch (Exception e) {
			return list;
		} finally {
			list.set(0, "3");
		}
	}
}
