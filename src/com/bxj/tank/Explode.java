package com.bxj.tank;

import java.awt.*;

public class Explode {
	private int x, y;

	private boolean live = true;
	private static boolean init = false;

	private TankClient tc;

	private static Toolkit tk = Toolkit.getDefaultToolkit();

	private static Image[] imgs = {
								tk.getImage(Explode.class.getClassLoader().getResource("images/0.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/1.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/2.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/3.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/4.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/5.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/6.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/7.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/8.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/9.gif")),
								tk.getImage(Explode.class.getClassLoader().getResource("images/10.gif"))
								
								};

	int step = 0;

	public Explode(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void draw(Graphics g) {
		
		
		if (!init) {
			for (int i = 0; i < imgs.length; i++) {
				g.drawImage(imgs[i], -100, -100, null);
			}
			init = true;
		}
		if (!live) {
			tc.explodes.remove(this);

			return;
		}
		if (step == imgs.length) {
			this.live = false;
			step = 0;
		}

		g.drawImage(imgs[step], x, y,null);
		step++;
	}

}
