package vendingmachine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class GUIMessageDisplay extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	public GUIMessageDisplay(JTextField txtDisplay) throws InterruptedException {
		getContentPane().setBackground(new Color(0, 0, 0));
		setUndecorated(true);
		setBackground(Color.DARK_GRAY);
		setForeground(Color.GREEN);
		setSize(125, 25);
		setLocationRelativeTo(txtDisplay);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getTextField(), BorderLayout.CENTER);
		textField.setText(txtDisplay.getText());

		// whenever the messageDisplay is called, it will only stay visible for 1 second
		Timer time = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		time.start();
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setEditable(false);
			textField.setDragEnabled(false);
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setBackground(Color.DARK_GRAY);
			textField.setForeground(new Color(50, 205, 50));
			textField.setColumns(10);
		}
		return textField;
	}
}