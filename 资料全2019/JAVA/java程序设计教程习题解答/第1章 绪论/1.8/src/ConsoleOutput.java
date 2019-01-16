import javax.swing.JApplet;
import java.awt.Graphics;
public class ConsoleOutput extends JApplet{
	public void paint(Graphics g){
		String str = "*";
		for(int i=0;i<39;i++){
			str+="*";
		}
		String str1="**Practice makes perfect";
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawString(str, 10, 20);
		g.drawString(str1, 10, 30);
		g.drawString(str, 10, 40);
	}
}
