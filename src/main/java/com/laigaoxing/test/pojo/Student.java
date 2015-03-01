package com.laigaoxing.test.pojo;

public class Student implements Comparable<Student> {
	private int age;
	private String address;
	private String name;
	private String birthday;
	private String higth;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getHigth() {
		return higth;
	}
	public void setHigth(String higth) {
		this.higth = higth;
	}
	
	@Override
	public int compareTo(Student o) {
		return o.age > getAge()==true ? 1 : 0;
	}
	
}
