package com.bxj.tank;

import java.awt.*;


public class Blood {
	private int x,y,w,h;
	private int step = 0;
	
	private boolean live = true;
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	
	//指明血块的运动轨迹，由pos中各个点构成
	private int[][] pos = {
			{350, 300}, {360, 300}, {375, 275}, {400, 200}, {360, 270}, {365, 290}, {340, 280}
							};
	public Blood(){
		x = pos[0][0];
		y = pos[0][1];
		w=h=15;
	}
	
	public void draw(Graphics g){
		if(!live) return;
		Color c = g.getColor();
		g.setColor(Color.MAGENTA);
		g.fillRect(x,y,w,h);
		g.setColor(c);
		
		move();
	}

	private void move() {
		step++;
		if(step==pos.length){
			step = 0;
		}
		x=pos[step][0];
		y=pos[step][0];
		
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}

}
