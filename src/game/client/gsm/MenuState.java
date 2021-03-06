package game.client.gsm;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.client.GamePanel;
import game.client.Music;

public class MenuState extends State {

	private Music music = new Music();
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		music.mainmenu(true);
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void draw(Graphics2D g) {
		BufferedImage img, logo, logo2;
		try {
			img = ImageIO.read(getClass().getClassLoader().getResourceAsStream("callOfPudge.jpg"));
			g.drawImage(img, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);
			
			logo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("logo.png"));
			g.drawImage(logo, 55, 5, 500, 50, null);
			
			logo2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("logo2.png"));
			g.drawImage(logo2, 105, 55, 400, 25, null);
			
			g.setColor(Color.WHITE);
			g.drawString("PRESS H FOR HELP", 240, 280);
			g.drawString("PRESS ENTER TO CONTINUE", 215, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onExit() {
		
	}

	@Override
	public void keyTyped(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int keyCode) {
		if(keyCode == KeyEvent.VK_ENTER) {
			music.mainmenu(false);
			gsm.changeState(new GameCreationState(gsm), false);
		} else if(keyCode == KeyEvent.VK_H) {
			gsm.changeState(new GameManualState(gsm), false);
		}
	}

	@Override
	public void keyReleased(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
