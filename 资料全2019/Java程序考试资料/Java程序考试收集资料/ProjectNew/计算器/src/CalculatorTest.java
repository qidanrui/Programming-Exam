import javax.swing.JFrame;
public class CalculatorTest {
  public static void main(String args[])
  {
	  CalculatorFrame calculator=new CalculatorFrame();
	  calculator.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	  calculator.setResizable(false);
	  calculator.setVisible(true);
	  calculator.setSize(300, 400);
	  
  }
}
