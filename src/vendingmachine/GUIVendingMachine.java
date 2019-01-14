package vendingmachine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

public class GUIVendingMachine extends JFrame {
	private static final long serialVersionUID = 1L;
	private VendingMachine vend;
	private JPanel foodPics, DispenserPanel, Buttons;
	private JButton keyPad, moneySlot, changeDispenser, dispensedFood;
	private JLabel label;
	private JTextField txtDisplay;
	private GUIMoneyDialog money;
	private GUIKeyPad keys;

	// constructor takes a regular VendingMachine as an arguments
	public GUIVendingMachine(VendingMachine vend) {
		this.vend = vend;
		try {

			vend.getInventory().load("inventory.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		setTitle("Vending Machine");
		setSize(390, 535);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(getDispenserPanel(), BorderLayout.CENTER);
		setVisible(true);
		setResizable(false);

		money = new GUIMoneyDialog(vend, txtDisplay);
		money.setSize(90, 130);
		money.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		keys = new GUIKeyPad(this);
	}

	public void updateCode(String code) {
		buying(code);
	}

	// this method basically calls the regular buy method in VendingMachine,
	// it just used a GUI display to display all messages
	public void buying(String code) {
		DecimalFormat formatter = new DecimalFormat("$#,##0.00");
		try {
			// attempt to purchase item that corresponds to inputed code
			double change = vend.buy(code).getTotal();

			// if purchase is successful, the food dispenser displays the the name of the
			// purchased item
			// the food dispenser becomes enabled so the user can click on the button and
			// "take" the item
			dispensedFood.setEnabled(true);
			dispensedFood.setText(vend.getInventory().get(code).getName());

			// if there is change left over, the change dispenser should display that amount
			// the change dispenser becomes enabled so the user can click on the button and
			// "take" the change
			if (change > 0) {
				changeDispenser.setEnabled(true);
				changeDispenser.setText(formatter.format(change));
			}

			// reset text display
			txtDisplay.setText("Insert $");
		} catch (CodeNotFoundException | NotEnoughPaidException | NotEnoughChangeException e1) {
			// set text display to error message
			txtDisplay.setText(e1.getMessage());

			try {
				// open a pop-up message display so user can view complete error message in
				// larger font
				GUIMessageDisplay popup = new GUIMessageDisplay(txtDisplay);
				popup.setVisible(true);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}
		}
	}

	public VendingMachine getVend() {
		return vend;
	}

	private JButton getKeyPad() {
		if (keyPad == null) {
			keyPad = new JButton("");
			keyPad.setIcon(new ImageIcon(getClass().getResource("KeyPad3.jpg")));
			keyPad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					keys.setLocationRelativeTo(keyPad);
					keys.setVisible(true);

				}
			});

		}
		return keyPad;
	}

	private JButton getMoneySlot() {
		if (moneySlot == null) {
			moneySlot = new JButton("|");

			moneySlot.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					money.setLocation(moneySlot.getLocationOnScreen());
					money.setVisible(true);
				}
			});
		}
		return moneySlot;
	}

	private JButton getChangeDispenser() {
		if (changeDispenser == null) {
			changeDispenser = new JButton("");
			changeDispenser.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					changeDispenser.setText("");
					changeDispenser.setEnabled(false);
				}
			});
			changeDispenser.setEnabled(false);
			changeDispenser.setBackground(Color.LIGHT_GRAY);
			changeDispenser.setForeground(new Color(128, 0, 0));
		}
		return changeDispenser;
	}

	private JButton getDispensedFood() {
		if (dispensedFood == null) {
			dispensedFood = new JButton("");
			dispensedFood.setForeground(new Color(128, 0, 0));
			dispensedFood.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispensedFood.setText("");
					dispensedFood.setEnabled(false);
				}
			});
			dispensedFood.setEnabled(false);
		}
		return dispensedFood;
	}

	private JTextField getMessageDisplay() {
		if (txtDisplay == null) {
			txtDisplay = new JTextField();
			txtDisplay.setEditable(false);
			txtDisplay.setText("Insert $");
			txtDisplay.setFont(new Font("Traveling _Typewriter", Font.PLAIN, 10));
			txtDisplay.setForeground(new Color(50, 205, 50));
			txtDisplay.setBackground(Color.DARK_GRAY);
			txtDisplay.setColumns(10);
		}
		return txtDisplay;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			JDialog inv = new JDialog();
			JTextArea invtf = new JTextArea();

			// the inventory appears when mouse hovers over the vending machine food picture
			// and diappears when the mouse exits the picture
			label.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					// this size will only work for an inventory with 5 items
					// if changing inventory size, can make inv size relative to list size
					// adding a certain amount of pixels per item
					inv.setSize(175, 85);

					// set the inventory location relative to the vending machine food picture
					inv.setLocationRelativeTo(foodPics);

					inv.setUndecorated(true);
					inv.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					inv.setVisible(true);
					inv.getContentPane().add(invtf);

					invtf.setSize(175, 85);
					invtf.setEditable(false);
					invtf.setText(vend.getInventory().toString());
				}

				@Override
				public void mouseExited(MouseEvent e) {
					inv.dispose();
				}
			});

			label.setIcon(new ImageIcon(getClass().getResource("vendingfood.jpg")));
		}
		return label;
	}

	// created food pic/dispenser panel using WindowBuilder
	private JPanel getFoodPics() {
		if (foodPics == null) {
			foodPics = new JPanel();
			foodPics.setBorder(
					new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 128), new Color(0, 0, 128), null, null));
			foodPics.setBackground(new Color(0, 0, 0));

			GroupLayout gl_FoodPics = new GroupLayout(foodPics);
			gl_FoodPics.setHorizontalGroup(gl_FoodPics.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_FoodPics
							.createSequentialGroup().addGap(69).addComponent(getDispensedFood(),
									GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(70, Short.MAX_VALUE)));
			gl_FoodPics.setVerticalGroup(gl_FoodPics.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_FoodPics
							.createSequentialGroup().addGap(18).addComponent(getDispensedFood(),
									GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(28, Short.MAX_VALUE)));
			foodPics.setLayout(gl_FoodPics);
		}
		return foodPics;
	}

	// created dispenserPanel using WindowBuilder
	private JPanel getDispenserPanel() {
		if (DispenserPanel == null) {
			DispenserPanel = new JPanel();
			DispenserPanel.setBorder(null);
			DispenserPanel.setBackground(new Color(0, 0, 0));
			GroupLayout gl_DispenserPanel = new GroupLayout(DispenserPanel);
			gl_DispenserPanel.setHorizontalGroup(gl_DispenserPanel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_DispenserPanel.createSequentialGroup()
							.addComponent(getFoodPics(), GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE).addGap(86))
					.addGroup(
							gl_DispenserPanel.createSequentialGroup().addContainerGap(304, Short.MAX_VALUE)
									.addComponent(getButtons(), GroupLayout.PREFERRED_SIZE, 80,
											GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
					.addGroup(Alignment.LEADING,
							gl_DispenserPanel
									.createSequentialGroup().addGap(6).addComponent(getLabel(),
											GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
									.addContainerGap()));
			gl_DispenserPanel.setVerticalGroup(gl_DispenserPanel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_DispenserPanel.createSequentialGroup().addGroup(gl_DispenserPanel
							.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING,
									gl_DispenserPanel.createSequentialGroup().addContainerGap().addComponent(getLabel())
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(getFoodPics(), GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
							.addComponent(getButtons(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
									GroupLayout.PREFERRED_SIZE))
							.addContainerGap()));
			DispenserPanel.setLayout(gl_DispenserPanel);
		}
		return DispenserPanel;
	}

	// created side panel layout using WindowBuilders
	private JPanel getButtons() {
		if (Buttons == null) {
			Buttons = new JPanel();
			Buttons.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 128), new Color(0, 0, 128),
					new Color(0, 0, 128), new Color(0, 0, 128)));
			Buttons.setBackground(new Color(0, 0, 0));
			GroupLayout gl_Buttons = new GroupLayout(Buttons);
			gl_Buttons.setHorizontalGroup(gl_Buttons.createParallelGroup(Alignment.LEADING).addGroup(gl_Buttons
					.createSequentialGroup().addContainerGap()
					.addGroup(gl_Buttons.createParallelGroup(Alignment.LEADING).addGroup(gl_Buttons
							.createSequentialGroup()
							.addGroup(gl_Buttons.createParallelGroup(Alignment.LEADING)
									.addComponent(getKeyPad(), GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE)
									.addComponent(getChangeDispenser(), GroupLayout.PREFERRED_SIZE, 64, Short.MAX_VALUE)
									.addComponent(getMessageDisplay(), GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
							.addContainerGap())
							.addGroup(Alignment.TRAILING,
									gl_Buttons
											.createSequentialGroup().addComponent(getMoneySlot(),
													GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
											.addGap(26)))));
			gl_Buttons.setVerticalGroup(gl_Buttons.createParallelGroup(Alignment.LEADING).addGroup(gl_Buttons
					.createSequentialGroup().addGap(107)
					.addComponent(getMessageDisplay(), GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18).addComponent(getKeyPad(), GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(31).addComponent(getMoneySlot()).addGap(48).addComponent(getChangeDispenser())
					.addContainerGap(128, Short.MAX_VALUE)));
			Buttons.setLayout(gl_Buttons);
		}
		return Buttons;
	}

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new GUIVendingMachine(new VendingMachine(new Inventory(), new Money(10, 10, 10, 10)));
	}
}