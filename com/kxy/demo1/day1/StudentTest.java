package com.kxy.demo1.day1;

public class StudentTest {

	public static void main(String[] args) {
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(1);
		Integer a1 = Integer.valueOf(50);
		Integer a2 = Integer.valueOf(50);
		Integer b1 = Integer.valueOf(-128);
		Integer b2 = Integer.valueOf(-128);
		Integer c1 = Integer.valueOf(127);
		Integer c2 = Integer.valueOf(127);
		Integer d1 = Integer.valueOf(128);
		Integer d2 = Integer.valueOf(128);
		
//		public static Integer valueOf(int i) {
//	        if (i >= IntegerCache.low && i <= IntegerCache.high)
//	            return IntegerCache.cache[i + (-IntegerCache.low)];
//	        return new Integer(i);
//	    }
		// IntegerCache.low = -128  IntegerCache.high = 127, 缓存了 -128~127的对象，只要 i的值在这个范围，就不会创建新对象，
		// 会从 IntegerCache 中取缓存好的对象，超过了这个范围  return new Integer(i); 就会创建新的对象
		System.out.println(i1==i2);			//false  使用了new i1和i2就没有任何的关系，只是刚好它们的值是相等的，但是 存的引用是不一样的，== 比的是引用
		System.out.println(a1 == a2);		//true
		System.out.println(b1 == b2);		//true
		System.out.println(c1 == c2);		//true
		System.out.println(d1 == d2);		//false
		
		int a = 15 + 15*16 + 15*16*16 + 7*16*16*16;   //Ox7ffff   
		int b = 2;
		for(int i = 0; i < 14; i++) {                 //short  最大 是  2^15-1
			b*=2;
		}
		System.out.println(a);
		System.out.println(b-1);
		System.out.println(Integer.toBinaryString(1000000000));
		System.out.println((2e15)-1);
		System.out.println(Float.toHexString(Float.MAX_VALUE));
		
		int ff = 0x23f0;
		int bv = 0x34ff;
		System.out.println("ff:" + ff);
		System.out.println("bv:" + bv);
	
		//数在计算机中是以补码存在的，正数的反码补码都和原码一样，short 5   原码：0000 0101，-5 先取反码 ：1111 1010， 在加1是补码 ：1111 1011； 
		
		System.out.println();
		System.out.println("&运算,同真为真");
		System.out.println("ff:" + Integer.toBinaryString(ff));
		System.out.println("bv:" + Integer.toBinaryString(bv));
		System.out.println("rs:" + Integer.toBinaryString(ff & bv));
		System.out.println();
		System.out.println("|运算，有真为真");
		System.out.println("ff:" + Integer.toBinaryString(ff));
		System.out.println("bv:" + Integer.toBinaryString(bv));
		System.out.println("rs:" + Integer.toBinaryString(ff | bv));
		System.out.println();
		System.out.println("^运算，异或运算，同真为假，同假为假，真假为真");
		System.out.println("ff:" + Integer.toBinaryString(ff));
		System.out.println("bv:" + Integer.toBinaryString(bv));
		System.out.println("rs:" + Integer.toBinaryString(ff ^ bv));//结果少了一位，是因为第一位是0
		System.out.println();
		System.out.println("~运算，取反操作");
		System.out.println("ff:" + Integer.toBinaryString(ff));
		System.out.println("bv:" + Integer.toBinaryString(bv));
		System.out.println("rs:" + Integer.toBinaryString(~ff)); //int 类型，会补齐32位，前面的0，取反都会变成1
		
	}
}
