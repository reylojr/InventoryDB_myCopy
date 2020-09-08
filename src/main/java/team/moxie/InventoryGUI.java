package team.moxie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

public class InventoryGUI {
	private DbDriver driver;

	public InventoryGUI() throws SQLException {
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());

		final DefaultTableModel tableModel = new DefaultTableModel() {

			@Override
			public Class getColumnClass(int column) {
				switch (column) {
					case 1:
						return Integer.class;
					case 2:
					case 3:
						return Double.class;
					default:
						return String.class;
				}
			}
		};

		JTable table = new JTable(tableModel);

		tableModel.addColumn("ID");
		tableModel.addColumn("QUANTITY");
		tableModel.addColumn("WHOLESALE");
		tableModel.addColumn("SALE");
		tableModel.addColumn("SUPPLIER");
		table.setAutoCreateRowSorter(true);
		table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);

		table.getColumnModel().getColumn(0).setMinWidth(100);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(100);

		table.getColumnModel().getColumn(0).setMaxWidth(300);
		table.getColumnModel().getColumn(1).setMaxWidth(300);
		table.getColumnModel().getColumn(2).setMaxWidth(300);
		table.getColumnModel().getColumn(3).setMaxWidth(300);
		table.getColumnModel().getColumn(4).setMaxWidth(300);

		JFormattedTextField NOTCONNECTEDFormattedTextField = new JFormattedTextField();
		NOTCONNECTEDFormattedTextField.setBackground(new Color(-65535));
		NOTCONNECTEDFormattedTextField.setColumns(20);
		NOTCONNECTEDFormattedTextField.setForeground(new Color(-16777216));
		NOTCONNECTEDFormattedTextField.setHorizontalAlignment(0);
		NOTCONNECTEDFormattedTextField.setText("NOT CONNECTED");

		JButton createButton = new JButton();
		createButton.setText("Create Entry");

		JButton searchButton = new JButton();
		searchButton.setText("Search ID");

		JButton searchSuppButton = new JButton();
		searchSuppButton.setText("Search Supplier");

		JButton updateButton = new JButton();
		updateButton.setText("Update Entry");

		JButton deleteButton = new JButton();
		deleteButton.setText("Delete Entry");

		JPanel east = new JPanel();
		GridLayout gridLayout = new GridLayout(5, 1);
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		east.setLayout(gridLayout);
		east.add(searchButton);
		east.add(searchSuppButton);
		east.add(updateButton);
		east.add(createButton);
		east.add(deleteButton);

		JPanel north = new JPanel(new FlowLayout());
		north.add(NOTCONNECTEDFormattedTextField);

		JScrollPane center = new JScrollPane(table);
		center.setPreferredSize(new Dimension(500, 400));
		center.createHorizontalScrollBar();

		panel1.add(east, BorderLayout.EAST);
		panel1.add(center, BorderLayout.CENTER);
		panel1.add(north, BorderLayout.NORTH);

		JFrame frame = new JFrame("Inventory Manager");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1000, 450));
		frame.setLocationRelativeTo(null);
		frame.add(panel1);
		frame.pack();
		frame.setVisible(true);

		try {
			driver = new DbDriver("50.116.26.153", "3306", "inv", "team", "GJ&8YahAh%kS#i");
			NOTCONNECTEDFormattedTextField.setValue("CONNECTED");
			NOTCONNECTEDFormattedTextField.setBackground(new Color(0x65FF5A));
		} catch (Exception e) {
			e.printStackTrace();
		}

		searchButton.addActionListener(
			new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String[] columnNames = { "PID", "QUANTITY", "WHOLESALE", "SALE", "SUPPLIER" };

					String PID = "";

					JTextField productIDField = new JTextField(20);

					JPanel getInfoPanel = new JPanel();
					getInfoPanel.add(new JLabel("Product ID"));
					getInfoPanel.add(productIDField);

					int result1 = JOptionPane.showConfirmDialog(
						null,
						getInfoPanel,
						"Please enter the ID of supplier:",
						JOptionPane.OK_CANCEL_OPTION
					);
					if (result1 == JOptionPane.OK_OPTION) {
						PID = productIDField.getText();
					}

					LinkedList<dbEntry> list = new LinkedList<>();

					list.add(driver.searchById(PID));

					// Clear the rows
					int rowCount = tableModel.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						tableModel.removeRow(i);
					}

					// Add the rows back

					for (dbEntry entry : list) {
						tableModel.addRow(
							new Object[] {
								entry.getId(),
								entry.getQuantity(),
								entry.getWholesalePrice(),
								entry.getSalePrice(),
								entry.getSupplierId()
							}
						);
					}
				}
			}
		);

		searchSuppButton.addActionListener(
			new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String[] columnNames = { "PID", "QUANTITY", "WHOLESALE", "SALE", "SUPPLIER" };

					String supplierID = "";

					JTextField productIDField = new JTextField(5);

					JPanel getInfoPanel = new JPanel();
					getInfoPanel.add(new JLabel("Supplier ID"));
					getInfoPanel.add(productIDField);

					int result1 = JOptionPane.showConfirmDialog(
						null,
						getInfoPanel,
						"Please enter the ID of supplier:",
						JOptionPane.OK_CANCEL_OPTION
					);
					if (result1 == JOptionPane.OK_OPTION) {
						supplierID = productIDField.getText();
					}

					LinkedList<dbEntry> list = driver.searchBySupplier(supplierID);

					// Clear the rows
					int rowCount = tableModel.getRowCount();
					for (int i = rowCount - 1; i >= 0; i--) {
						tableModel.removeRow(i);
					}

					// Add the rows back

					for (dbEntry entry : list) {
						tableModel.addRow(
							new Object[] {
								entry.getId(),
								entry.getQuantity(),
								entry.getWholesalePrice(),
								entry.getSalePrice(),
								entry.getSupplierId()
							}
						);
					}
				}
			}
		);

		updateButton.addActionListener(
			new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					dbEntry selectedEntry;

					JTextField productIDField1 = new JTextField(20);

					JPanel getInfoPanel = new JPanel();
					getInfoPanel.add(new JLabel("Product ID"));
					getInfoPanel.add(productIDField1);

					int result1 = JOptionPane.showConfirmDialog(
						null,
						getInfoPanel,
						"Please enter the ID to update:",
						JOptionPane.OK_CANCEL_OPTION
					);
					if (result1 == JOptionPane.OK_OPTION) {
						selectedEntry = driver.searchById("2FT57YS7CM97");
						if (selectedEntry == null) {
							return;
						}
					} else {
						return;
					}

					// Show the values that are already there
					JTextField productIDField2 = new JTextField(selectedEntry.getId(), 5);
					JTextField quantityField = new JTextField(Integer.toString(selectedEntry.getQuantity()), 5);
					JTextField wholesalePriceField = new JTextField((Double.toString(selectedEntry.getWholesalePrice())), 5);
					JTextField salePriceField = new JTextField((Double.toString(selectedEntry.getSalePrice())), 5);
					JTextField supplierIDField = new JTextField(selectedEntry.getSupplierId(), 5);

					JPanel myPanel = new JPanel();
					myPanel.add(new JLabel("Product ID"));
					myPanel.add(productIDField2);

					myPanel.add(Box.createHorizontalStrut(15)); // a spacer
					myPanel.add(new JLabel("Quantity"));
					myPanel.add(quantityField);

					myPanel.add(Box.createHorizontalStrut(15)); // a spacer
					myPanel.add(new JLabel("Wholesale Price"));
					myPanel.add(wholesalePriceField);

					myPanel.add(Box.createHorizontalStrut(15)); // a spacer
					myPanel.add(new JLabel("Sale Price"));
					myPanel.add(salePriceField);

					myPanel.add(Box.createHorizontalStrut(15)); // a spacer
					myPanel.add(new JLabel("Supplier ID"));
					myPanel.add(supplierIDField);

					int result = JOptionPane.showConfirmDialog(
						null,
						myPanel,
						"Please Enter row entry info:",
						JOptionPane.OK_CANCEL_OPTION
					);
					if (result == JOptionPane.OK_OPTION) {
						// Do the thing!
					}
				}
			}
		);
		deleteButton.addActionListener(
			new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JTextField productIDField = new JTextField(5);

					JPanel myPanel = new JPanel();
					myPanel.add(new JLabel("Product ID"));
					myPanel.add(productIDField);

					int result2 = JOptionPane.showConfirmDialog(
						null,
						myPanel,
						"Please enter the ID to delete:",
						JOptionPane.OK_CANCEL_OPTION
					);
					if (result2 == JOptionPane.OK_OPTION) {
						// Do the thing!
					}
				}
			}
		);
		createButton.addActionListener(
			new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JTextField productIDField = new JTextField(20);
					JTextField quantityField = new JTextField(20);
					JTextField wholesalePriceField = new JTextField(20);
					JTextField salePriceField = new JTextField(20);
					JTextField supplierIDField = new JTextField(20);

					JPanel myPanel = new JPanel();
					myPanel.add(new JLabel("Product ID"));
					myPanel.add(productIDField);

					myPanel.add(Box.createHorizontalStrut(15)); // a spacer
					myPanel.add(new JLabel("Quantity"));
					myPanel.add(quantityField);

					myPanel.add(Box.createHorizontalStrut(15)); // a spacer
					myPanel.add(new JLabel("Wholesale Price"));
					myPanel.add(wholesalePriceField);

					myPanel.add(Box.createHorizontalStrut(15)); // a spacer
					myPanel.add(new JLabel("Sale Price"));
					myPanel.add(salePriceField);

					myPanel.add(Box.createHorizontalStrut(15)); // a spacer
					myPanel.add(new JLabel("Supplier ID"));
					myPanel.add(supplierIDField);

					int result = JOptionPane.showConfirmDialog(
						null,
						myPanel,
						"Please Enter row entry info:",
						JOptionPane.OK_CANCEL_OPTION
					);
					if (result == JOptionPane.OK_OPTION) {
						// Do the thing!
					}
				}
			}
		);
	}
}
