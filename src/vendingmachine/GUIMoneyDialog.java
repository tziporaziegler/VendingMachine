package vendingmachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class GUIMoneyDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private VendingMachine vend;
	private JButton Dollar, Quarter, Dime, Nickel;
	private final ButtonGroup MoneyButtons;
	private JTextField MessageDisplay;

	DecimalFormat formatter = new DecimalFormat("$#,##0.00");

	// Create layout using WindowBuilder
	public GUIMoneyDialog(VendingMachine vend, JTextField MessageDisplay) {
		MoneyButtons = new ButtonGroup();
		this.vend = vend;
		this.MessageDisplay = MessageDisplay;
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(getDollar(), GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
				.addComponent(getQuarter(), GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
				.addComponent(getDime(), GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
				.addComponent(getNickel(), GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(getDollar(), GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(getQuarter(), GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(getDime(), GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(getNickel(), GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)));
		getContentPane().setLayout(groupLayout);
	}

	// when the nickel button is clicked, a nickel is added the vendingMachine bank
	// the user's total increases by .05
	private JButton getNickel() {
		if (Nickel == null) {
			Nickel = new JButton("Nickel");
			Nickel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					vend.pay(new Money(0, 0, 0, 1));
					MessageDisplay.setText(formatter.format(vend.getPaid().getTotal()));
				}
			});
			MoneyButtons.add(Nickel);
		}
		return Nickel;
	}

	// when the dime button is clicked, a dime is added the vendingMachine bank
	// the user's total increases by .1
	private JButton getDime() {
		if (Dime == null) {
			Dime = new JButton("Dime");
			Dime.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					vend.pay(new Money(0, 0, 1, 0));
					MessageDisplay.setText(formatter.format(vend.getPaid().getTotal()));
				}
			});
			MoneyButtons.add(Dime);
		}
		return Dime;
	}

	// when the quarter button is clicked, a quarter is added the vendingMachine
	// bank
	// the user's total increases by .25
	private JButton getQuarter() {
		if (Quarter == null) {
			Quarter = new JButton("Quarter");
			Quarter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					vend.pay(new Money(0, 1, 0, 0));
					MessageDisplay.setText(formatter.format(vend.getPaid().getTotal()));
				}
			});
			MoneyButtons.add(Quarter);
		}
		return Quarter;
	}

	// when the dollar button is clicked, a dollar is added the vendingMachine bank
	// the user's total increases by 1
	private JButton getDollar() {
		if (Dollar == null) {
			Dollar = new JButton("Dollar");
			Dollar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					vend.pay(new Money(1, 0, 0, 0));
					MessageDisplay.setText(formatter.format(vend.getPaid().getTotal()));
				}
			});
			MoneyButtons.add(Dollar);
		}
		return Dollar;
	}
}
