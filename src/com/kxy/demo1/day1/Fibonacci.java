package com.kxy.demo1.day1;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

	public static String getFibonacii(int num) {
		List<Integer> list = new ArrayList<>();
		if(num < 2) {
			return "数字必须不小于2";
		}else {
			list.add(1);
			list.add(1);
			int sum = 0;
			int time = 2;
			
			while(sum < num) {
				sum = list.get(time-1) + list.get(time-2);
				time ++;
				list.add(sum);
			}
			
			if(sum == num) {
				return list.toString();
			}else {
				return num + "不是斐波那契数字";
			}
		}
	}
	
	/**
	 * 获取所有4位的吸血鬼数字,将四个数字拆开，两两组合，的乘积是原数字的值
	 * @return
	 */
	public static String getAllVampire() {
		List<String> result = new ArrayList<>();
		z:for(int i = 1000; i < 9999; i++) {
			if(i % 100 == 0) {
				continue;
			}
			
			int a = i/1000;
			int b = (i - a * 1000)/100;
			int c = (i - a*1000 - b * 100)/10;
			int d = i - a * 1000 - b * 100 - c * 10;
			
			int ab = a*10 + b;
			int ac = a*10 + c;
			int ad = a*10 + d;
			int ba = b*10 + a;
			int ca = c*10 + a;
			int da = d*10 + a;
			
			List<Integer> list = new ArrayList<>();
			list.add(ab); 
			list.add(ac); 
			list.add(ad); 
			list.add(ba); 
			list.add(ca); 
			list.add(da); 
			
			t:for(int y = 0; y < list.size(); y++) {
				if(i % list.get(y) == 0) {
					boolean add = false;
					int rs = i / list.get(y);
					switch (y) {
					case 0:
						if(checkRs(rs, c, d)) {
							add = true;
						}else {
							continue t;
						}
						break;
					case 1:
						if(checkRs(rs, b, d)) {
							add = true;
						}else {
							continue t;
						}
						break;
					case 2:
						if(checkRs(rs, b, c)) {
							add = true;
						}else {
							continue t;
						}
						break;
					case 3:
						if(checkRs(rs, c, d)) {
							add = true;
						}else {
							continue t;
						}
						break;
					case 4:
						if(checkRs(rs, b, d)) {
							add = true;
						}else {
							continue t;
						}
						break;
					case 5:
						if(checkRs(rs, b, c)) {
							add = true;
						}else {
							continue t;
						}
						break;
					default:
						break;
					}
					
					if(add) {
						result.add(i+"="+rs+"*"+(i/rs));
						add = false;
						continue z;
					}
				}
			}
		}
		return result.toString();
	}
	
	private static boolean checkRs(int rs, int num1, int num2) {
		int rs1 = num1 * 10 + num2;
		int rs2 = num2 * 10 + num1;
		if(rs1 == rs || rs2 == rs) {
			return true;
		}else {
			return false;
		}
	}
}
