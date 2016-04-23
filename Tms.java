package com.briup.ch12;

import java.util.Scanner;	

public class Tms{
	private Teacher[] teas = new Teacher[3];
	private int index = 0;	//数组中教师的个数

	/**
	  程序入口
	*/
	public static void main(String[] args){
		Tms tms = new Tms();
		tms.menu();
		//创建扫描器对象
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("请输入功能编号：");
			//监控标准输入，当用户输入回车，该方法可以将回车之前所有的用户输入返回
			String option = scanner.nextLine();
			switch(option){
				case "1":	//查询所有教师信息
					System.out.println("以下为所有教师的信息：");
					Teacher[] teas = tms.findAll();
					for(int i=0;i<teas.length;i++){
						System.out.println(teas[i]);
					}
					System.out.println("总计 "+tms.index+" 个");
					break;
				case "2":	//添加教师信息
					while(true){
						System.out.println("请输入教师信息【id#name#age】或者输入break返回上一级目录");
						//接受用户的输入
						String teastr = scanner.nextLine();
						if(teastr.equals("break")){
							break;
						}
						//分割字符串
						String[] teaArr = teastr.split("#");
						//将字符串转换为int
						long id = Long.parseLong(teaArr[0]);
						String name = teaArr[1];
						int age = Integer.parseInt(teaArr[2]);
						//封装对象
						Teacher tea = new Teacher(id,name,age);
						tms.save(tea);
						System.out.println("添加成功！");
					}
					break;
				case "3":
					while(true){
						System.out.println("请输入要删除教师的id或输入break返回上一级");

						String idTea = scanner.nextLine();
						if(idTea.equals("break")){
							break;
						}
						//将字符串转换为long
						long id = Long.parseLong(idTea);
						Teacher tea = tms.findById(id);
						if(tea==null){
							System.out.println("您要删除的教师不存在！");
							continue;
						}
						tms.deleteById(id);
						System.out.println("删除成功！");
					}
					break;
				case "4":
					while(true){
						System.out.println("请输入您要修改教师的id或输入break返回上一级");
						String idTea = scanner.nextLine();
						if(idTea.equals("break")){
							break;
						}
						//将字符串转换为long
						long id = Long.parseLong(idTea);
						Teacher tea = tms.findById(id);
						if(tea == null){
							System.out.println("您要修改的教师信息不存在");
							continue;
						}	
						System.out.println("原来："+tea);
						System.out.println("请输入修改后的信息【name#age】:");
						String teastr = scanner.nextLine();
						
						String[] arr = teastr.split("#");
						String name = arr[0];
						int age = Integer.parseInt(arr[1]);
						//包装用户输入的新对象的对象
						Teacher newtea = new Teacher(id,name,age);
						tms.update(newtea);
						System.out.println("修改成功！");
						
					}
					break;
				case "5":
					while(true){
						System.out.println("请输入要查询教师的id或输入break返回上一级");

						String idTea = scanner.nextLine();
						if(idTea.equals("break")){
							break;
						}
						//将字符串转换为long
						long id = Long.parseLong(idTea);
						Teacher tea = tms.findById(id);
						System.out.println(tea==null?"sorry not found!":tea);
					}
					break;
				case "exit":
					System.out.println("欢迎再次使用本系统");
					System.exit(0);
				case "help":
					tms.menu();
					break;
				default:
					System.out.println("输入出错！请重新输入。");
			}
		}
	}
	//菜单
	public void menu(){
		System.out.println("**********教师信息管理系统**********");
		System.out.println("**1. 查询所有教师信息");
		System.out.println("**2. 添加教师信息");
		System.out.println("**3. 删除教师信息");
		System.out.println("**4. 修改教师信息");
		System.out.println("**5. 根据学号查询教师信息");
		System.out.println("**exit. 退出系统");
		System.out.println("**help. 帮助");
		System.out.println("************************************");
	}
	
	//保存教师信息
	public void save(Teacher tea){
		//index 数组中教师个数 3  teas.length 数组长度 3
		if(index>=teas.length){
			//数组扩展
			Teacher[] demo = new Teacher[teas.length+5];
			System.arraycopy(teas,0,demo,0,index);
			teas = demo;
		}
		teas[index++] = tea;
	
	}
	
	public void deleteById(long id){
		//获取该id在数组中索引
		int num = findIndexById(id);
		for(int i=num;i<index-1;i++){
			teas[i] = teas[i+1];
		}
		teas[--index] = null;
	}
	
	public Teacher findById(long id){
		//调用方法通过id获取该id所在数组的索引
		int num = findIndexById(id);
		return num==-1 ? null : teas[num];
	}
	/**
		通过id查找该id的对象在数组中的索引
	*/
	private int findIndexById(long id){
		int num = -1;
		for(int i=0;i<index;i++){
			if(id == teas[i].getId()){
				num = i;
				break;
			}
		}

		return num;
	}
	//查询所有
	public Teacher[] findAll(){
		//数组拷贝技术 teas = {{},{},null} ->teasDemo={{},{}}
		Teacher[] teasDemo = new Teacher[index];
		System.arraycopy(teas,0,teasDemo,0,index);
		return teasDemo;
	}
	//修改 id,   -- name,age
	public void update(Teacher tea){
		for(int i=0;i<index;i++){
			if(tea.getId() == teas[i].getId()){
				teas[i].setName(tea.getName());
				teas[i].setAge(tea.getAge());
			}
		}
	}

}