package com.bxj.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * ������������̹����Ϸ��������
 * @author Administrator
 *
 */

public class TankClient extends Frame {
	
	/**
	 * ����̹����Ϸ�Ŀ��
	 * ������Ϸ�ĸ߶�
	 */
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;

	Wall w1 = new Wall(600, 300, 25, 250, this), w2 = new Wall(50, 200, 200,
			25, this);
	Tank myTank = new Tank(400,300, true, Direction.STOP, this);
	Blood b = new Blood();

	List<Missile> missiles = new ArrayList<Missile>();
	List<Explode> explodes = new ArrayList<Explode>();
	List<Tank> tanks = new ArrayList<Tank>();

	Image offScreenImage = null;

	public void paint(Graphics g) {
		if (tanks.size() <= 0) {
			for (int i = 0; i < Integer.parseInt(PropertyMgr.getProperty("reProduceTankCount")); i++) {
				tanks.add(new Tank(100 + 40 * (i + 1), 100, false,
						Direction.D, this));
			}
		}
		
		/*
		 * ָ���ӵ�����ը��̹�˵�����
		 * �Լ�̹�˵�����ֵ
		 */
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("missiles count   " + missiles.size(), 10, 50);
		g.drawString("explodes count   " + explodes.size(), 10, 70);
		g.drawString("tanks    count   " + tanks.size(), 10, 90);
		g.drawString("myTank   life    " + myTank.getLife(), 10, 110);
		g.setColor(c);
		
		for (int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			m.hitTanks(tanks);
			m.hitTank(myTank);
			m.collidesWithWall(w1);
			m.collidesWithWall(w2);
			// if(!m.isLive()) missiles.remove(m);
			// else m.draw(g);
			m.draw(g);
		}
		for (int i = 0; i < explodes.size(); i++) {
			Explode e = explodes.get(i);
			e.draw(g);
		}

		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			t.collidesWithWall(w1);
			t.collidesWithWall(w2);
			t.collidesWithTanks(tanks);
			t.draw(g);
		}
		myTank.draw(g);
		myTank.eat(b);
		w1.draw(g);
		w2.draw(g);
		b.draw(g);

	}

	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	
	/**
	 * ��������ʾ̹��������
	 */
	public void lauchFrame() {
		
		
		
		int initTankCount =Integer.parseInt(PropertyMgr.getProperty("initTankCount"));

		for (int i = 0; i < initTankCount; i++) {
			tanks.add(new Tank(100 + 40 * (i + 1), 100, false,
					Direction.D, this));
		}

		
		this.setLocation(400, 100);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		this.setBackground(Color.BLACK);
		this.setResizable(false);
		this.setTitle("TankWar");

		this.addKeyListener(new KeyMonitor());

		setVisible(true);
		new Thread(new PaintThread()).start();
		
		
	}

	public static void main(String[] e) {
		TankClient tc = new TankClient();
		tc.lauchFrame();

	}

	private class PaintThread implements Runnable {
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

	private class KeyMonitor extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}

	}
}
