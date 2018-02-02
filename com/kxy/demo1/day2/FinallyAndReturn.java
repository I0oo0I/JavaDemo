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
	 * ���ۣ� ���ڻ������ͣ�
	 * 		1. ��try �� ���� finally ���� return�� try����֮�⣬����治���� return�ˣ� �����Ѿ���
	 * 		   try������ finally�е�return�����ˣ������return���붼ͨ����
	 * 		2. finally�еĴ���һ���ᱻִ�е�
	 * 			a. ֻҪ finally����return������ͻ���finally�н���
	 * 			b. ֻ�� try����return, finally���޸�nameֵ���޸ĵ�nameֵֻ��finally���������ã�
	 * 			        �������� try��return����������ֵ������ try���е�nameֵ��catch��ͬ�����ĸ�
	 * 			        ���н�����nameֵ�����Ǹ����е�ֵ
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
	 * ����������������
	 * ���ۣ� ���������������ͣ�����ͬһ�����ã�finally�޸����õĶ���ֵ����Ӱ������ ���еĽ����  3 
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
