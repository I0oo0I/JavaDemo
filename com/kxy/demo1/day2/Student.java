package com.kxy.demo1.day2;

public class Student {

	private String name;
	
	private int age;
	
	private Score core;
	
	public Student(String name, int age, Score core) {
		this.name = name;
		this.age = age;
		this.core = core;
	}
	
	public Student() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Score getCore() {
		return core;
	}

	public void setCore(Score core) {
		this.core = core;
	}
	
	@Override
	public String toString() {
		return "姓名：" + this.name + " 年龄：" +  this.age + " 成绩：【 语文 = " + this.core.getChinese() + " 数学 = " + this.core.getMath() + " 】";
	}
	
}

//内部类包访问权限
class Score{
	int chinese;
	
	int math;
	
	public Score() {
		
	}
	
	public Score(int chinese, int math) {
		this.chinese = chinese;
		this.math = math;
	}

	public int getChinese() {
		return chinese;
	}

	public int getMath() {
		return math;
	}

	public void setChinese(int chinese) {
		this.chinese = chinese;
	}

	public void setMath(int math) {
		this.math = math;
	}
	
}
