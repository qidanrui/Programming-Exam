package thu.hcguo.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	public ControlPanel() {
		addButton("Start");
		addButton("Restart");
		addButton("Save");
		addButton("Load");
	}

	public void addButton(String name) {
		JButton btn = new JButton(name);
		btn.setActionCommand(name);
		btn.addActionListener(this);
		add(btn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) e.getSource();
		String filename = "chess";
		if (btn.getActionCommand().equals("Start")) {
			ChessBoardPanel.init();
		}
		if (btn.getActionCommand().equals("Restart")) {
			ChessBoardPanel.init();
		}
		if (btn.getActionCommand().equals("Save")) {
			if (ChessBoardPanel.save(filename)) {
				JOptionPane.showMessageDialog(this, "Save success!", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Save fail!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			ChessBoardPanel.init();
		}
		if (btn.getActionCommand().equals("Load")) {
			if(ChessBoardPanel.read(filename)) {
				JOptionPane.showMessageDialog(this, "Read success!", "Info",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Read fail!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}
