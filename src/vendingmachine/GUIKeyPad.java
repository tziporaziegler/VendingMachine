package vendingmachine;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class GUIKeyPad extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7;
	private JButton btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH;
	private JButton btnEnter;
	private StringBuilder code;
	private Container container;
	private GUIVendingMachine vend;

	// constructor
	public GUIKeyPad(GUIVendingMachine vend) {
		this.vend = vend;
		code = new StringBuilder();
		container = getContentPane();
		setUpButtons();
		positionButtons();
		setSize(156, 197);
	}

	// listener used for all keys on keypad except enter
	ActionListener clickon = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			code.append(((JButton) e.getSource()).getText());
		}
	};

	// create keypad layout using a gridbag
	public void positionButtons() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 5 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0 };
		container.setLayout(gridBagLayout);
		Insets inset = new Insets(1, 1, 1, 1);
		GridBagConstraints gbc_btn0 = new GridBagConstraints();
		gbc_btn0.fill = GridBagConstraints.BOTH;
		gbc_btn0.insets = inset;
		gbc_btn0.gridx = 0;
		gbc_btn0.gridy = 0;
		container.add(btn0, gbc_btn0);
		GridBagConstraints gbc_btn1 = new GridBagConstraints();
		gbc_btn1.fill = GridBagConstraints.BOTH;
		gbc_btn1.insets = inset;
		gbc_btn1.gridx = 1;
		gbc_btn1.gridy = 0;
		container.add(btn1, gbc_btn1);
		GridBagConstraints gbc_btn2 = new GridBagConstraints();
		gbc_btn2.fill = GridBagConstraints.BOTH;
		gbc_btn2.insets = inset;
		gbc_btn2.gridx = 2;
		gbc_btn2.gridy = 0;
		container.add(btn2, gbc_btn2);
		GridBagConstraints gbc_btn3 = new GridBagConstraints();
		gbc_btn3.fill = GridBagConstraints.BOTH;
		gbc_btn3.insets = inset;
		gbc_btn3.gridx = 3;
		gbc_btn3.gridy = 0;
		container.add(btn3, gbc_btn3);
		GridBagConstraints gbc_btn4 = new GridBagConstraints();
		gbc_btn4.fill = GridBagConstraints.BOTH;
		gbc_btn4.insets = inset;
		gbc_btn4.gridx = 0;
		gbc_btn4.gridy = 1;
		container.add(btn4, gbc_btn4);
		GridBagConstraints gbc_btn5 = new GridBagConstraints();
		gbc_btn5.fill = GridBagConstraints.BOTH;
		gbc_btn5.insets = inset;
		gbc_btn5.gridx = 1;
		gbc_btn5.gridy = 1;
		container.add(btn5, gbc_btn5);
		GridBagConstraints gbc_btn6 = new GridBagConstraints();
		gbc_btn6.fill = GridBagConstraints.BOTH;
		gbc_btn6.insets = inset;
		gbc_btn6.gridx = 2;
		gbc_btn6.gridy = 1;
		container.add(btn6, gbc_btn6);
		GridBagConstraints gbc_btn7 = new GridBagConstraints();
		gbc_btn7.fill = GridBagConstraints.BOTH;
		gbc_btn7.insets = inset;
		gbc_btn7.gridx = 3;
		gbc_btn7.gridy = 1;
		container.add(btn7, gbc_btn7);
		GridBagConstraints gbc_btnA = new GridBagConstraints();
		gbc_btnA.fill = GridBagConstraints.BOTH;
		gbc_btnA.insets = inset;
		gbc_btnA.gridx = 0;
		gbc_btnA.gridy = 2;
		container.add(btnA, gbc_btnA);
		GridBagConstraints gbc_btnB = new GridBagConstraints();
		gbc_btnB.fill = GridBagConstraints.BOTH;
		gbc_btnB.insets = inset;
		gbc_btnB.gridx = 1;
		gbc_btnB.gridy = 2;
		container.add(btnB, gbc_btnB);
		GridBagConstraints gbc_btnC = new GridBagConstraints();
		gbc_btnC.fill = GridBagConstraints.BOTH;
		gbc_btnC.insets = inset;
		gbc_btnC.gridx = 2;
		gbc_btnC.gridy = 2;
		container.add(btnC, gbc_btnC);
		GridBagConstraints gbc_btnD = new GridBagConstraints();
		gbc_btnD.fill = GridBagConstraints.BOTH;
		gbc_btnD.insets = inset;
		gbc_btnD.gridx = 3;
		gbc_btnD.gridy = 2;
		container.add(btnD, gbc_btnD);
		GridBagConstraints gbc_btnE = new GridBagConstraints();
		gbc_btnE.fill = GridBagConstraints.BOTH;
		gbc_btnE.insets = inset;
		gbc_btnE.gridx = 0;
		gbc_btnE.gridy = 3;
		container.add(btnE, gbc_btnE);
		GridBagConstraints gbc_btnF = new GridBagConstraints();
		gbc_btnF.fill = GridBagConstraints.BOTH;
		gbc_btnF.insets = inset;
		gbc_btnF.gridx = 1;
		gbc_btnF.gridy = 3;
		container.add(btnF, gbc_btnF);
		GridBagConstraints gbc_btnG = new GridBagConstraints();
		gbc_btnG.fill = GridBagConstraints.BOTH;
		gbc_btnG.insets = inset;
		gbc_btnG.gridx = 2;
		gbc_btnG.gridy = 3;
		container.add(btnG, gbc_btnG);
		GridBagConstraints gbc_btnH = new GridBagConstraints();
		gbc_btnH.fill = GridBagConstraints.BOTH;
		gbc_btnH.insets = inset;
		gbc_btnH.gridx = 3;
		gbc_btnH.gridy = 3;
		container.add(btnH, gbc_btnH);
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.gridwidth = 4;
		gbc_btnEnter.insets = new Insets(0, 0, 0, 0);
		gbc_btnEnter.gridx = 0;
		gbc_btnEnter.gridy = 4;
		container.add(btnEnter, gbc_btnEnter);
	}

	private void setUpButtons() {
		btn0 = new JButton("0");
		btn0.addActionListener(clickon);
		btn1 = new JButton("1");
		btn1.addActionListener(clickon);
		btn2 = new JButton("2");
		btn2.addActionListener(clickon);
		btn3 = new JButton("3");
		btn3.addActionListener(clickon);
		btn4 = new JButton("4");
		btn4.addActionListener(clickon);
		btn5 = new JButton("5");
		btn5.addActionListener(clickon);
		btn6 = new JButton("6");
		btn6.addActionListener(clickon);
		btn7 = new JButton("7");
		btnA = new JButton("A");
		btnA.addActionListener(clickon);
		btnB = new JButton("B");
		btnB.addActionListener(clickon);
		btnC = new JButton("C");
		btnC.addActionListener(clickon);
		btnD = new JButton("D");
		btnD.addActionListener(clickon);
		btnE = new JButton("E");
		btnE.addActionListener(clickon);
		btnF = new JButton("F");
		btnF.addActionListener(clickon);
		btnG = new JButton("G");
		btnG.addActionListener(clickon);
		btnH = new JButton("H");
		btnH.addActionListener(clickon);

		// create enter button with own action listener
		// when pressed, the code previously entered will be sent to the buy method
		btnEnter = new JButton("ENTER");
		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String want = code.toString();
				vend.updateCode(want.toString());
				dispose();
				code.setLength(0);
			}
		});
	}
}
