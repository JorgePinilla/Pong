import javax.swing.*;

public class Gui {
	JFrame frame;
	DrawPanel panel;
	
	public Gui(){
		frame = new JFrame("pong");
		panel = new DrawPanel();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	
	public void go(){
		panel.startGame();
	}
	
	public static void main(String[] args){
		Gui game = new Gui();
		game.go();
	}
}
