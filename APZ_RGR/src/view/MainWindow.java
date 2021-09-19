package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Month;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import model.Box;
import model.Component;
import model.Product;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frmPatternCompositeVladysalv;
	private JTree tree;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmPatternCompositeVladysalv.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPatternCompositeVladysalv = new JFrame();
		frmPatternCompositeVladysalv.setTitle("Pattern Composite, Vladyslav Vasylenko, PI-181");
		this.frmPatternCompositeVladysalv.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				MainWindow.this.onWindowOpened();
			}
		});
		frmPatternCompositeVladysalv.setBounds(100, 100, 770, 560);
		frmPatternCompositeVladysalv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPatternCompositeVladysalv.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 500, 250);
		this.frmPatternCompositeVladysalv.getContentPane().add(scrollPane);

		scrollPane.setViewportView(tree);
		this.tree = new JTree();
		tree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.tree.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		scrollPane.setViewportView(this.tree);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 252, 756, 271);
		frmPatternCompositeVladysalv.getContentPane().add(scrollPane_1);

		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		textArea.setBounds(0, 0, 756, 271);
		scrollPane_1.setViewportView(textArea);

		JButton btnAddBox = new JButton("Add box");
		btnAddBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddBox.setBounds(581, 20, 121, 27);
		frmPatternCompositeVladysalv.getContentPane().add(btnAddBox);

		JButton btnRemove = new JButton("Remove");
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemove.setBounds(581, 110, 121, 27);
		frmPatternCompositeVladysalv.getContentPane().add(btnRemove);

		JButton btnAddProduct = new JButton("Add product");
		btnAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddProduct.setBounds(581, 64, 121, 27);
		frmPatternCompositeVladysalv.getContentPane().add(btnAddProduct);

		JButton btnInfo = new JButton("Info");
		btnInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInfo.setBounds(581, 156, 121, 27);
		frmPatternCompositeVladysalv.getContentPane().add(btnInfo);

		JButton btnBoxValue = new JButton("Box Value");
		btnBoxValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBoxValue.setBounds(581, 204, 121, 27);
		frmPatternCompositeVladysalv.getContentPane().add(btnBoxValue);
		btnRemove.addActionListener((e) -> {
			this.onRemove();
		});
		btnAddBox.addActionListener((e) -> {
			this.onAddBox();
		});
		btnAddProduct.addActionListener((e) -> {
			this.onAddProduct();
		});
		btnInfo.addActionListener((e) -> {
			this.info();
		});
		btnBoxValue.addActionListener((e) -> {
			this.boxValue();
		});
	}

	private TreeModel getStartModel() throws Exception {
		Box rootBox = new Box("Кореневий ящик");
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootBox);
		return (new JTree(root)).getModel();
	}

	protected void onWindowOpened() {
		try {
			this.tree.setModel(this.getStartModel());
			this.expandAll();
		} catch (Exception var2) {
			var2.printStackTrace();
		}
	}

	private void expandAll() {
		for (int i = 0; i < this.tree.getRowCount(); ++i) {
			this.tree.expandRow(i);
		}

	}

	protected void onAddBox() {
		DefaultMutableTreeNode parent = this.getSelectedNode();
		Box box = null;
		if (parent != null) {
			if (parent.getUserObject() instanceof Box) {
				DlgBox dlgbox = new DlgBox();
				dlgbox.setVisible(true);
				if (dlgbox.ok) {
					try {
						box = dlgbox.createBox();
						((Box) parent.getUserObject()).Add(box);
					} catch (Exception var6) {
						JOptionPane.showMessageDialog(this.tree, var6.getMessage(),
								this.frmPatternCompositeVladysalv.getTitle(), 0);
						return;
					}
				}
				dlgbox.setVisible(false);

				if (box != null) {
					DefaultMutableTreeNode newSon = new DefaultMutableTreeNode(box);
					parent.add(newSon);
					DefaultMutableTreeNode par = (DefaultMutableTreeNode) newSon.getParent();
					this.tree.updateUI();
					this.expandAll();
				}
			} else if (!(parent.getUserObject() instanceof Box)) {
				JOptionPane.showMessageDialog(this.tree, "В продукт неможливо додати новий компонент!",
						this.frmPatternCompositeVladysalv.getTitle(), 0);
			}
		}
	}

	protected void onAddProduct() {
		DefaultMutableTreeNode parent = this.getSelectedNode();
		Product pr = null;
		if (parent != null) {
			if (parent.getUserObject() instanceof Box) {
				DlgProduct dlgproduct = new DlgProduct();
				dlgproduct.setVisible(true);
				if (dlgproduct.ok && dlgproduct.productValue > 0) {
					try {
						pr = dlgproduct.createProduct();
						((Box) parent.getUserObject()).Add(pr);
					} catch (Exception var6) {
						JOptionPane.showMessageDialog(this.tree, var6.getMessage(),
								this.frmPatternCompositeVladysalv.getTitle(), 0);
						return;
					}
				}
				dlgproduct.setVisible(false);
				if (pr != null) {
					DefaultMutableTreeNode newSon = new DefaultMutableTreeNode(pr);
					parent.add(newSon);
					this.tree.updateUI();
					this.expandAll();
				}
			} else if (!(parent.getUserObject() instanceof Box)) {
				JOptionPane.showMessageDialog(this.tree, "В продукт неможливо додати новий компонент!",
						this.frmPatternCompositeVladysalv.getTitle(), 0);
			}
		}
	}

	// Видалення вузла
	protected void onRemove() {
		DefaultMutableTreeNode node = this.getSelectedNode();
		if (node != null) {
			if (node.getParent() != null) {
				DefaultMutableTreeNode p = (DefaultMutableTreeNode) node.getParent();
				((Box) p.getUserObject()).Remove((Component) node.getUserObject());
			}
			node.removeFromParent();
			this.tree.setSelectionPath((TreePath) null);
			this.tree.updateUI();
		}
	}

	private DefaultMutableTreeNode getSelectedNode() {
		Object selectNode = this.tree.getLastSelectedPathComponent();
		if (selectNode == null) {
			JOptionPane.showMessageDialog(this.tree, "Вузол не вибраний!", this.frmPatternCompositeVladysalv.getTitle(),
					0);
			return null;
		} else {
			return (DefaultMutableTreeNode) selectNode;
		}
	}

	public JTree getTree() {
		return tree;
	}

	public void info() {
		DefaultMutableTreeNode parent = this.getSelectedNode();
		if (parent != null) {
			textArea.setText(((Component) parent.getUserObject()).Info());
		}
	}

	public void boxValue() {
		DefaultMutableTreeNode parent = this.getSelectedNode();
		if (parent != null) {
			if (parent.getUserObject() instanceof Box) {
				textArea.setText("Загальна ціна продуктів, які містяться в цьому ящику, дорівнює "
						+ ((Box) parent.getUserObject()).getBoxValue());
			} else {
				JOptionPane.showMessageDialog(this.tree, "Виберіть ящик!");
			}
		}
	}

}
