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
	
	//��д�ĵķ������������η������ܵ��ڸ��࣬����� setNm �� protected�� son�еĲ��ܵ��� protected��
	//��һ�����ӣ� ���븸���һ�������� public�� ���� ���� ��д�� �� private�� 
	//���ɵ������������ת��Ϊ����ʱ�������������ķ������ǵ��ò�����
	@Override
	public void setNm(String nm) {
		// TODO Auto-generated method stub
		super.setNm(nm);
	}
	
	public static void main(String[] args) {
		//new GrandSon();
		new GrandSon("С��");
		
	}
}
