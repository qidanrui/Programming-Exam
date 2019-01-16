import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class MultiDoc extends JFrame {

	/**
	 * @param args
	 */
	private JDesktopPane jdp = new JDesktopPane();

	public MultiDoc() {
		JMenuBar jb = new JMenuBar();
		JMenu jm = new JMenu("File");
		JMenuItem ji = new JMenuItem("New");
		jm.setMnemonic('F');
		ji.setMnemonic('N');
		jb.add(jm);
		jm.add(ji);
		ji.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				try {
					final String str = JOptionPane.showInputDialog("请输入文件名");
					FileReader fr = new FileReader(str);
					String s = new String();
					for (int t = fr.read(); t != -1; t = fr.read()) {
						s += (char) t;

					}
					JInternalFrame jif = new JInternalFrame("ok", true, true,
							true, true);
					final JTextArea jta = new JTextArea();
					jta.setText(s);
					jif.add(jta);
					fr.close();
					jif.pack();
					jif.setVisible(true);
					jdp.add(jif);
					JMenuBar jj = new JMenuBar();
					JMenu jjj = new JMenu("File");
					JMenuItem jjjj = new JMenuItem("save");
					jjj.add(jjjj);
					jj.add(jjj);
					jif.setJMenuBar(jj);
					jjjj.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							FileWriter fw;

					try {
						fw = new FileWriter(str);
					

					String ss = jta.getText();
					System.out.print(ss);
					for (int i = 0; i < ss.length(); i++) {

						fw.append(ss.charAt(i));
					

					}
					fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
						}
						
					});
					

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		this.setJMenuBar(jb);
		this.getContentPane().add(jdp);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultiDoc mc = new MultiDoc();
		mc.setVisible(true);
		mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mc.setSize(800, 600);
	}

}
