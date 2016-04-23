package com.briup.ch12;

import java.util.Scanner;	

public class Tms{
	private Teacher[] teas = new Teacher[3];
	private int index = 0;	//�����н�ʦ�ĸ���

	/**
	  �������
	*/
	public static void main(String[] args){
		Tms tms = new Tms();
		tms.menu();
		//����ɨ��������
		Scanner scanner = new Scanner(System.in);
		while(true){
			System.out.print("�����빦�ܱ�ţ�");
			//��ر�׼���룬���û�����س����÷������Խ��س�֮ǰ���е��û����뷵��
			String option = scanner.nextLine();
			switch(option){
				case "1":	//��ѯ���н�ʦ��Ϣ
					System.out.println("����Ϊ���н�ʦ����Ϣ��");
					Teacher[] teas = tms.findAll();
					for(int i=0;i<teas.length;i++){
						System.out.println(teas[i]);
					}
					System.out.println("�ܼ� "+tms.index+" ��");
					break;
				case "2":	//��ӽ�ʦ��Ϣ
					while(true){
						System.out.println("�������ʦ��Ϣ��id#name#age����������break������һ��Ŀ¼");
						//�����û�������
						String teastr = scanner.nextLine();
						if(teastr.equals("break")){
							break;
						}
						//�ָ��ַ���
						String[] teaArr = teastr.split("#");
						//���ַ���ת��Ϊint
						long id = Long.parseLong(teaArr[0]);
						String name = teaArr[1];
						int age = Integer.parseInt(teaArr[2]);
						//��װ����
						Teacher tea = new Teacher(id,name,age);
						tms.save(tea);
						System.out.println("��ӳɹ���");
					}
					break;
				case "3":
					while(true){
						System.out.println("������Ҫɾ����ʦ��id������break������һ��");

						String idTea = scanner.nextLine();
						if(idTea.equals("break")){
							break;
						}
						//���ַ���ת��Ϊlong
						long id = Long.parseLong(idTea);
						Teacher tea = tms.findById(id);
						if(tea==null){
							System.out.println("��Ҫɾ���Ľ�ʦ�����ڣ�");
							continue;
						}
						tms.deleteById(id);
						System.out.println("ɾ���ɹ���");
					}
					break;
				case "4":
					while(true){
						System.out.println("��������Ҫ�޸Ľ�ʦ��id������break������һ��");
						String idTea = scanner.nextLine();
						if(idTea.equals("break")){
							break;
						}
						//���ַ���ת��Ϊlong
						long id = Long.parseLong(idTea);
						Teacher tea = tms.findById(id);
						if(tea == null){
							System.out.println("��Ҫ�޸ĵĽ�ʦ��Ϣ������");
							continue;
						}	
						System.out.println("ԭ����"+tea);
						System.out.println("�������޸ĺ����Ϣ��name#age��:");
						String teastr = scanner.nextLine();
						
						String[] arr = teastr.split("#");
						String name = arr[0];
						int age = Integer.parseInt(arr[1]);
						//��װ�û�������¶���Ķ���
						Teacher newtea = new Teacher(id,name,age);
						tms.update(newtea);
						System.out.println("�޸ĳɹ���");
						
					}
					break;
				case "5":
					while(true){
						System.out.println("������Ҫ��ѯ��ʦ��id������break������һ��");

						String idTea = scanner.nextLine();
						if(idTea.equals("break")){
							break;
						}
						//���ַ���ת��Ϊlong
						long id = Long.parseLong(idTea);
						Teacher tea = tms.findById(id);
						System.out.println(tea==null?"sorry not found!":tea);
					}
					break;
				case "exit":
					System.out.println("��ӭ�ٴ�ʹ�ñ�ϵͳ");
					System.exit(0);
				case "help":
					tms.menu();
					break;
				default:
					System.out.println("����������������롣");
			}
		}
	}
	//�˵�
	public void menu(){
		System.out.println("**********��ʦ��Ϣ����ϵͳ**********");
		System.out.println("**1. ��ѯ���н�ʦ��Ϣ");
		System.out.println("**2. ��ӽ�ʦ��Ϣ");
		System.out.println("**3. ɾ����ʦ��Ϣ");
		System.out.println("**4. �޸Ľ�ʦ��Ϣ");
		System.out.println("**5. ����ѧ�Ų�ѯ��ʦ��Ϣ");
		System.out.println("**exit. �˳�ϵͳ");
		System.out.println("**help. ����");
		System.out.println("************************************");
	}
	
	//�����ʦ��Ϣ
	public void save(Teacher tea){
		//index �����н�ʦ���� 3  teas.length ���鳤�� 3
		if(index>=teas.length){
			//������չ
			Teacher[] demo = new Teacher[teas.length+5];
			System.arraycopy(teas,0,demo,0,index);
			teas = demo;
		}
		teas[index++] = tea;
	
	}
	
	public void deleteById(long id){
		//��ȡ��id������������
		int num = findIndexById(id);
		for(int i=num;i<index-1;i++){
			teas[i] = teas[i+1];
		}
		teas[--index] = null;
	}
	
	public Teacher findById(long id){
		//���÷���ͨ��id��ȡ��id�������������
		int num = findIndexById(id);
		return num==-1 ? null : teas[num];
	}
	/**
		ͨ��id���Ҹ�id�Ķ����������е�����
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
	//��ѯ����
	public Teacher[] findAll(){
		//���鿽������ teas = {{},{},null} ->teasDemo={{},{}}
		Teacher[] teasDemo = new Teacher[index];
		System.arraycopy(teas,0,teasDemo,0,index);
		return teasDemo;
	}
	//�޸� id,   -- name,age
	public void update(Teacher tea){
		for(int i=0;i<index;i++){
			if(tea.getId() == teas[i].getId()){
				teas[i].setName(tea.getName());
				teas[i].setAge(tea.getAge());
			}
		}
	}

}