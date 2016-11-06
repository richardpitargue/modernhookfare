package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import game.gsm.GameStateManager;
import game.gsm.MenuState;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 300;
	public static final int SCALE = 2;
	
	private Thread gameThread;
	private boolean running = false;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private GameStateManager gsm;
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		gsm = new GameStateManager();
		gsm.changeState(new MenuState(gsm), false);
		
		start();
	}
	
	public void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		long now, last = System.nanoTime(), updateLength, lastFPSTime = 0;
		int fps = 0;
		double delta;
		
		while(running) {
			now = System.nanoTime();
			updateLength = now - last;
			last = now;
			delta = updateLength / ((double) OPTIMAL_TIME);
			
			lastFPSTime += updateLength;
			if(lastFPSTime >= 1000000000) {
				System.out.println("FPS: " + fps);
				fps = 0;
				lastFPSTime = 0;
			}
			
			update(delta);
			draw();
			render();
			fps++;
			
			try {
				Thread.sleep((System.nanoTime() - last + OPTIMAL_TIME) / 1000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(double delta) {
		gsm.update(delta);
	}
	
	public void draw() {
		gsm.draw(g);
	}
	
	public void render() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}

}