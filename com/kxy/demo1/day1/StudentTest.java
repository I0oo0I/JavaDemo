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
		// IntegerCache.low = -128  IntegerCache.high = 127, ������ -128~127�Ķ���ֻҪ i��ֵ�������Χ���Ͳ��ᴴ���¶���
		// ��� IntegerCache ��ȡ����õĶ��󣬳����������Χ  return new Integer(i); �ͻᴴ���µĶ���
		System.out.println(i1==i2);			//false  ʹ����new i1��i2��û���κεĹ�ϵ��ֻ�Ǹպ����ǵ�ֵ����ȵģ����� ��������ǲ�һ���ģ�== �ȵ�������
		System.out.println(a1 == a2);		//true
		System.out.println(b1 == b2);		//true
		System.out.println(c1 == c2);		//true
		System.out.println(d1 == d2);		//false
		
		int a = 15 + 15*16 + 15*16*16 + 7*16*16*16;   //Ox7ffff   
		int b = 2;
		for(int i = 0; i < 14; i++) {                 //short  ��� ��  2^15-1
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
	
		//���ڼ���������Բ�����ڵģ������ķ��벹�붼��ԭ��һ����short 5   ԭ�룺0000 0101��-5 ��ȡ���� ��1111 1010�� �ڼ�1�ǲ��� ��1111 1011�� 
		
		System.out.println();
		System.out.println("&����,ͬ��Ϊ��");
		System.out.println("ff:" + Integer.toBinaryString(ff));
		System.out.println("bv:" + Integer.toBinaryString(bv));
		System.out.println("rs:" + Integer.toBinaryString(ff & bv));
		System.out.println();
		System.out.println("|���㣬����Ϊ��");
		System.out.println("ff:" + Integer.toBinaryString(ff));
		System.out.println("bv:" + Integer.toBinaryString(bv));
		System.out.println("rs:" + Integer.toBinaryString(ff | bv));
		System.out.println();
		System.out.println("^���㣬������㣬ͬ��Ϊ�٣�ͬ��Ϊ�٣����Ϊ��");
		System.out.println("ff:" + Integer.toBinaryString(ff));
		System.out.println("bv:" + Integer.toBinaryString(bv));
		System.out.println("rs:" + Integer.toBinaryString(ff ^ bv));//�������һλ������Ϊ��һλ��0
		System.out.println();
		System.out.println("~���㣬ȡ������");
		System.out.println("ff:" + Integer.toBinaryString(ff));
		System.out.println("bv:" + Integer.toBinaryString(bv));
		System.out.println("rs:" + Integer.toBinaryString(~ff)); //int ���ͣ��Ჹ��32λ��ǰ���0��ȡ��������1
		
	}
}
