package com.project.wangyuming.wechat.util;

public class RandomImg {
	private static final String path = "./simple/view/webScoket/static/img/";
	private static final String[] images = {
		"haha.gif", "barrager.png", "cute.png", "heisenberg.png", "mj.gif", "yaseng.png"
	};
	
	public static String randomImage(){
		int index = (int)(Math.random() * 6 + 0);
		
		return path + images[index];
	}
	
	public static void main(String[] args){
		for (int i = 0; i < 150; i++)
			System.out.println((int)(Math.random() * 6 + 0));
	}
}
