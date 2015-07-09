// The wall can block the bullet shot by enemy,but not 'I'.
package com.bxj.tank;


import java.awt.*;
import java.util.*;

public class Wall {
	private int x,y,w,h;
	private TankClient tc;
	
	
	
	
	public Wall(int x,int y,int w,int h,TankClient tc){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tc = tc;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x,y,w,h);
		g.setColor(c);
	}
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
		
	}
	
	
		


}
