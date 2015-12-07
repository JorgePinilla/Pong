import java.awt.Rectangle;

public class Entity {
	
	int x, y, speed, width, height;
	boolean up, down, collision1, collision2, ballCollision;
	
	public Entity(int x, int y){
		this.x = x;
		this.y = y;
		speed = 5;
		width = 25;
		height = 100;
		up = false;
		down = false;
		collision1 = false;
		collision2 = false;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Rectangle getBoundsP(){
		return new Rectangle(getX() + (getWidth() - 1), getY() + 1, 1, getHeight() - 1);
	}
	
	public Rectangle getBoundsE(){
		return new Rectangle(getX(), getY() + 1, 1, getHeight() - 1);
	}
	
	public Rectangle getUpperBounds(){
		return new Rectangle(getX(), getY(), getWidth(), 1);
	}
	
	public Rectangle getLowerBounds(){
		return new Rectangle(getX(), getY() + getHeight() - 1, getWidth(), 1);
	}
	
	public void move(){
			if(up && !collision1)
				y -= speed;
			else
				y += 0;
			if(down && !collision2)
				y += speed;
			else
				y += 0;
	}
}
