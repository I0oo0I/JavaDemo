package com.kxy.demo1.day2;

public class HotEnumTest {

	public static void main(String[] args) {
		HotEnum[] values = HotEnum.values();
		
		if(null != values && values.length > 0) {
			for(HotEnum hotEnum : values) {
				System.out.println(hotEnum+".ordinal="+hotEnum.ordinal());
				switch (hotEnum) {
				case COLD:
					System.out.println("this is cold");
					break;
				case HOT:
					System.out.println("This is Hot");
					break;
				case VERY_HOT:
					System.out.println("This is very hot");
					break;
				default:
					break;
				}
			}
		
		}
		
	}
}
