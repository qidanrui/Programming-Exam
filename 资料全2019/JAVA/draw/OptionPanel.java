package thu.hcguo.draw;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class OptionPanel extends JPanel{
	private static final long serialVersionUID = -1449143728045114453L;

	public OptionPanel() {
		JButton btnLine = makeButton("Line", Type.LINE);
		JButton btnEllipse = makeButton("Ellipse", Type.ELLIPSE);
		JButton btnCircle = makeButton("Circle", Type.CIRCLE);
		JButton btnRetangle = makeButton("Rectangle", Type.RECTANGLE);
		JButton btnSquare = makeButton("Square", Type.SQUARE);

		add(btnLine);
		add(btnEllipse);
		add(btnCircle);
		add(btnRetangle);
		add(btnSquare);

		final JRadioButton fill = new JRadioButton("Fill");
		final JRadioButton unfill = new JRadioButton("UnFill", true);

		fill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DrawBoardPanel.isFill = fill.isSelected();
			}
		});
		unfill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DrawBoardPanel.isFill = fill.isSelected();
			}
		});

		ButtonGroup group = new ButtonGroup();
		group.add(fill);
		group.add(unfill);
		add(fill);
		add(unfill);

		JButton btnChooseColor = new JButton("Choose Color");
		btnChooseColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(null,
						"Change label1 Color", Color.BLACK);
				if (color != null)
					DrawBoardPanel.color = color;
			}
		});
		add(btnChooseColor);
	}

	public JButton makeButton(String str, final int type) {
		JButton btn = new JButton(str);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DrawBoardPanel.type = type;
			}
		});

		return btn;
	}

}
