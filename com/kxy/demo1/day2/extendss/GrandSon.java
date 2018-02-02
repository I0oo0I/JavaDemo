package com.kxy.demo1.day2.extendss;

public class GrandSon extends Son{
	
	public GrandSon() {
		System.out.println("I am GrandSon");
	}
	
	public GrandSon(String name) {
		System.out.println("I am Grandson, "+name);
	}
	
	public void changeName(String name) {
		setNm(name);
	}
	
	//重写的的方法，访问修饰符，不能低于父类，父类的 setNm 是 protected， son中的不能低于 protected，
	//举一个例子， 加入父类的一个方法是 public， 但是 子类 重写后 是 private， 
	//生成的子类对象向上转型为父类时，父类调用子类的方法，是调用不到的
	@Override
	public void setNm(String nm) {
		// TODO Auto-generated method stub
		super.setNm(nm);
	}
	
	public static void main(String[] args) {
		//new GrandSon();
		new GrandSon("小花");
		
	}
}
