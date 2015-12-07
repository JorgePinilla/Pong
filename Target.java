import java.awt.Rectangle;
import java.lang.Math;

public class Target {

  int x, y, width, height, speedX, speedY;
  double randomX, randomY;
  boolean lost, collisionD, collisionU, collisionPU, collisionPL, collisionEN;
	
	public Target(){
		height = 10;
		width = 10;
		lost = true;
		collisionD = false;
		collisionU = false;
		collisionPU = false;
		collisionPL = false;
		collisionEN = false;
	}
	
	public int getTargetX(){
		return x;
	}
	
	public int getTargetY(){
		return y;
	}
	
	public int getTargetWidth(){
		return width;
	}
	
	public int getTargetHeight(){
		return height;
	}
	
	public Rectangle getTargetBounds(){
		return new Rectangle(getTargetX(), getTargetY(), getTargetWidth(), getTargetHeight());
	}
	
	public void targetMove(){
		if(lost){
			randomX = Math.random();
			randomY = Math.random();
			x = 400;
			y = 300;
			if(randomX <= 0.5)
				speedX = -5;
			else
				speedX = 5;
			if(randomY <= 0.5)
				speedY = -5;
			else
				speedY = 5;
			lost = false;
		}
		else{
			if(collisionD || collisionU){
				x += speedX;
				speedY *= -1;
				y += speedY;
			}
			else if(collisionPU || collisionPL){
				speedX *= -1;
				x += speedX;
				speedY *= -1;
				y += speedY;
			}
			else if(collisionEN){
				y += speedY;
				speedX *= -1;
				x += speedX;
			}
			else{
				x += speedX;
				y += speedY;
			}
		}
	}
}
