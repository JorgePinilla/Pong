import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
public class DrawPanel extends JPanel implements KeyListener{
	
	BufferedImage buffer;
	Entity player;
	Entity enemy;
	Target ball;
	
	public DrawPanel(){
		setIgnoreRepaint(true);
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void keyTyped(KeyEvent e){
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP)
			player.up = true;
		if(key == KeyEvent.VK_DOWN)
			player.down = true;
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_UP)
			player.up = false;
		if(key == KeyEvent.VK_DOWN)
			player.down = false;
	}
	
	public void Initialize(){
		buffer = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
		player = new Entity(0, 250);
		enemy = new Entity(775, 250);
		ball = new Target();
	}
	
	public void update(){
		ball.targetMove();
		player.move();
		
		if(ball.getTargetY() - 20 > enemy.getY() && ball.getTargetX() > 400 && !enemy.collision2)
			enemy.y = enemy.y + enemy.speed;
		else if(ball.getTargetY() - 10 < enemy.getY() && ball.getTargetX() > 400 && !enemy.collision1)
			enemy.y = enemy.y - enemy.speed;
		else
			enemy.y = enemy.y + 0;
	}
	
	public void checkCollisions(){
		if (player.getY() <= 0)
			player.collision1 = true;
		else
			player.collision1 = false;
		if(player.getY() >= 467)
			player.collision2 = true;
		else
			player.collision2 = false;
		
		
		if (enemy.getY() <= 0)
			enemy.collision1 = true;
		else
			enemy.collision1 = false;
		if(enemy.getY() >= 467)
			enemy.collision2 = true;
		else
			enemy.collision2 = false;
		
		
		if(ball.getTargetY() <= 0)
			ball.collisionU = true;
		else
			ball.collisionU = false;
		if(ball.getTargetY() >= 560)
			ball.collisionD = true;
		else
			ball.collisionD = false;
		
		
		if(ball.getTargetX() < 0)
			ball.lost = true;
		if(ball.getTargetX() > 800)
			ball.lost = true;
		
		if(ball.getTargetX() < 400){
			if(ball.getTargetBounds().intersects(player.getUpperBounds()))
				ball.collisionPU = true;
			else
				ball.collisionPU = false;
			if(ball.getTargetBounds().intersects(player.getLowerBounds()))
				ball.collisionPL = true;
			else
				ball.collisionPL = false;
			if(ball.getTargetBounds().intersects(player.getBoundsP()))
				ball.collisionEN = true;
			else
				ball.collisionEN = false;
		}
		else{
			if(ball.getTargetBounds().intersects(enemy.getUpperBounds()))
				ball.collisionPU = true;
			else
				ball.collisionPU = false;
			if(ball.getTargetBounds().intersects(enemy.getLowerBounds()))
				ball.collisionPL = true;
			else
				ball.collisionPL = false;
			if(ball.getTargetBounds().intersects(enemy.getBoundsE()))
				ball.collisionEN = true;
			else
				ball.collisionEN = false;
		}
	}
	
	public void drawBuffer(){
		Graphics2D b = buffer.createGraphics();
		b.setColor(Color.black);
		b.fillRect(0, 0, 800, 600);
		b.setColor(Color.white);
		b.fillRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
		b.setColor(Color.white);
		b.fillRect(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
		b.setColor(Color.white);
		b.fillOval(ball.getTargetX(), ball.getTargetY(), ball.getTargetWidth(), ball.getTargetHeight());
		b.dispose();
	}
	
	public void drawScreen(){
		Graphics2D g = (Graphics2D)this.getGraphics();
		g.drawImage(buffer, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	public void startGame(){
		Initialize();
		while(true){
			try{
				update();
				checkCollisions();
				drawBuffer();
				drawScreen();
				Thread.sleep(7);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
