package com.bxj.tank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.*;


public class Missile {
	
	public static final int XSPEED=10;
	public static final int YSPEED=10;
	public static final int WIDTH=10;
	public static final int HEIGHT=10;

	private boolean good;
	private boolean live = true;
	
	public boolean isLive() {
		return live;
	}

	private int x, y;
	Direction dir;
	private TankClient tc;

	private static Toolkit tk = Toolkit.getDefaultToolkit();

	private static Image[] missileImgs = null;
	private static Map<String, Image> imgs = new HashMap<String,Image>();
	static{
						missileImgs = new Image[]{
								tk.getImage(Explode.class.getClassLoader().getResource("images/missileL.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/missileLU.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/missileU.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/missileRU.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/missileR.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/missileRD.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/missileD.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/missileLD.gif")),
								};
						imgs.put("L",missileImgs[0]);
						imgs.put("LU",missileImgs[1]);
						imgs.put("U",missileImgs[2]);
						imgs.put("RU",missileImgs[3]);
						imgs.put("R",missileImgs[4]);
						imgs.put("RD",missileImgs[5]);
						imgs.put("D",missileImgs[6]);
						imgs.put("LD",missileImgs[7]);
	}


	public Missile(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public Missile(int x,int y,Direction dir, boolean good,TankClient tc){
		this(x,y,dir);
		this.good =good;
		this.tc = tc;
	
	}

	public void draw(Graphics g) {
		if(!live){
			tc.missiles.remove(this);
			return;
		}
		switch (dir) {
		case L:
			g.drawImage(imgs.get("L"),x,y,null);
			break;
		case LU:
			g.drawImage(imgs.get("LU"),x,y,null);
			break;
		case U:
			g.drawImage(imgs.get("U"),x,y,null);
			break;
		case RU:
			g.drawImage(imgs.get("RU"),x,y,null);
			break;
		case R:
			g.drawImage(imgs.get("R"),x,y,null);
			break;
		case RD:
			g.drawImage(imgs.get("RD"),x,y,null);
			break;
		case D:
			g.drawImage(imgs.get("D"),x,y,null);
			break;
		case LD:
			g.drawImage(imgs.get("LD"),x,y,null);
			break;

		}
		
		
		move();

	}

	private void move() {

		switch (dir) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case STOP:
			break;
		}
		
		if(x<0||y<0||x>TankClient.GAME_WIDTH||y>TankClient.GAME_HEIGHT){
			live = false;
			
		}
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,WIDTH,HEIGHT);
	}
	
	public boolean hitTank(Tank t){
		if(this.getRect().intersects(t.getRect())&&t.isLive()&&this.good!=t.isGood()){
			if(t.isGood()){
				t.setLife(t.getLife()-20);
				if(t.getLife() <=0) t.setLive(false);
			}else{
				t.setLive (false);
			}
			
			this.live = false;
			Explode e = new Explode(x,y,this.tc);
			tc.explodes.add(e);
			return true;
		}
		return false;
		
	}
	
	public boolean hitTanks (List<Tank> tanks){
		for(int i=0;i<tanks.size();i++){
			if(hitTank(tanks.get(i)))
				return true;
		}
		return false;
	}
	
	public boolean collidesWithWall(Wall w){
		if(this.getRect().intersects( w.getRect())){
			tc.missiles.remove(this);
			return true;
		}
		return false;
	}

}
