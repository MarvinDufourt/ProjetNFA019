package cnam.net.swingViews;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cnam.net.dao.DAOFactory;
import cnam.net.dao.FrigoDao;
import cnam.net.dao.HistoriqueDaoImpl;
import cnam.net.dao.ProduitDao;
import cnam.net.dao.ProduitReferenceDao;
import cnam.net.dao.UtilisateurDao;
import cnam.net.exception.ConnexionException;
import cnam.net.metier.Frigo;
import cnam.net.metier.Historique;
import cnam.net.metier.Produit;
import cnam.net.metier.ProduitReference;
import cnam.net.metier.Utilisateur;
import cnam.net.swingCommon.TableCellRendererFrigo;
import cnam.net.swingCommon.TableCellRendererProduit;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import java.awt.CardLayout;

import javax.swing.JLayeredPane;
import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class Gui1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel ctpMain;
	private JPanel panel_Connexion;
	private JLabel lblNewLabel;
	private JPanel panel_Admin_Gestion_User;
	private JButton btnAddNewUser;
	private JButton btnDeleteUser;
	private JButton btnRefreshUsers;
	private JScrollPane scrollPane_User_Admin;
	private JTable table_User_Admin;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField textConnect_Username;
	private JButton btnConnect;
	private JButton btnModifyUser;
	private JTextField txt_AddUser_Nom;
	private JTextField txt_AddUser_Prenom;
	private JTextField txt_AddUser_Email;
	private JTextField txt_AddUser_Login;
	private JTextField txt_AddUser_Motdepasse;
	private final ButtonGroup rdbtn_Group_Admin_AddUser = new ButtonGroup();
	private JPanel panel_Admin_ModifyUser;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField txt_ModifyUser_Nom;
	private JLabel lblNewLabel_7;
	private JLabel lbl_Show_User_Id;
	private JLabel lblNewLabel_9;
	private JTextField txt_ModifyUser_Prenom;
	private JLabel lblNewLabel_10;
	private JTextField txt_ModifyUser_Email;
	private JLabel lblNewLabel_11;
	private JTextField txt_ModifyUser_Login;
	private JLabel lblNewLabel_12;
	private JTextField txt_ModifyUser_MotDePasse;
	private JLabel lblNewLabel_13;
	private JRadioButton rdbtn_ModifyUser_Admin_Yes;
	private JRadioButton rdbtn_ModifyUser_Admin_No;
	private final ButtonGroup rdbtn_Group_Admin_Modify = new ButtonGroup();
	private JButton btnAdmin_ModifyUser_SaveChanges;
	private JPanel panel_Menu_Principal;
	private final ButtonGroup admin_ButtonGroupVisibility = new ButtonGroup();
	private JLabel lblNewLabel_16;
	private JLabel lblAdmin_Or_User;
	private JLabel lblNewLabel_17;
	public JLabel lblUserName;
	private JButton btnBack_To_MenuPrincipal;
	private JLabel lblNewLabel_18;
	private JPanel panel_Admin_Gestion_Frigo;
	private JTable table_Frigo_Admin;
	private JButton btnMenu_Principal_GoAdminGestionFrigo;
	private JButton btnRefreshFrigo;
	private JTextField textField_Admin_Nom_Frigo;
	private JTextField textField_Admin_Emplacement_Frigo;
	private JPanel panel_Admin_ModifyFrigo;
	private JTextField txt_ModifyFrigo_Nom;
	private JTextField txt_ModifyFrigo_Emplacement;
	private JTextField txt_ModifyFrigo_TempMatin;
	private JTextField txt_ModifyFrigo_TempAprem;
	private JTable table_Produit_Admin;
	private JTable table_AddProduit_Frigo;
	private JButton btn_Admin_ModifierProduit;
	private JTable table_Modifier_Produit;
	private JTextField txtAdmin_ModifierProduit_DateEntree;
	private JPasswordField passwordField;
	private JPanel panel_Admin_Gestion_ProduitRef;
	private JPanel panel_Admin_Modifier_ProduitRef;
	private JTable table_ProduitRef_Admin;
	private JButton btnRefreshProduitRef;
	private JButton btn_Modify_produitRef;
	private JButton btn_Delete_ProduitRef;
	private JButton btnAjouter_ProduitRef;
	private JTextField txt_AjouterProduitRef_Nom;
	private JTextField text_AjouterProduitRef_Dlc;
	private JLabel lblNewLabel_5_2_2;
	private JButton btn_back_to_GestionProduitRef;
	private JTextField txt_ModifierProduitRef_Nom;
	private JComboBox<Object> combo_Admin_ModifierProduitRef_Categorie;
	private JLabel lblNewLabel_23;
	private JTextField txt_ModifierProduitRef_Dlc;
	private JButton btnModifier_ProduitRef_SaveChanges;
	private JLabel lblNewLabel_24;
	private JLabel lblNewLabel_25;
	private JTable table_AddProduit_Ref;
	private JTextField txtAdmin_ModifierProduit_DLC;
	private JLabel lblProduitErreur;
	private JLabel lblFrigoErreur;
	private DefaultTableCellRenderer rendererProduit = new TableCellRendererProduit();
	private DefaultTableCellRenderer rendererFrigo = new TableCellRendererFrigo();
	private JTable table_Historique;

	/**
	 * Create the frame.
	 */
	public Gui1() {
		setResizable(false);
		setTitle("Projet NFA019 Marvin Dufourt");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		this.setLocationRelativeTo(null);
		ctpMain = new JPanel();
		ctpMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ctpMain);

		JLayeredPane layeredPane = new JLayeredPane();

		GroupLayout gl_ctpMain = new GroupLayout(ctpMain);
		gl_ctpMain.setHorizontalGroup(
				gl_ctpMain.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
				);
		gl_ctpMain.setVerticalGroup(
				gl_ctpMain.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
				);
		layeredPane.setLayout(new CardLayout(0, 0));
		DefaultTableModel modelUtilisateur= new DefaultTableModel();
		DefaultTableModel modelFrigo= new DefaultTableModel();
		DefaultTableModel modelProduit= new DefaultTableModel();
		DefaultTableModel modelProduitRef= new DefaultTableModel();
		DefaultTableModel modelHistorique= new DefaultTableModel();

		panel_Connexion = new JPanel();
		layeredPane.add(panel_Connexion, "name_503076606294000");
		panel_Connexion.setLayout(null);

		////////////////////////////////////////
		//// Création des composants Graphiques
		////////////////////////////////////////

		lblNewLabel = new JLabel("Tra\u00E7age produit");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		lblNewLabel.setBounds(254, 0, 332, 103);
		panel_Connexion.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Please connect below");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(29, 152, 225, 31);
		panel_Connexion.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Username : ");
		lblNewLabel_2.setBounds(29, 219, 82, 14);
		panel_Connexion.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Password :");
		lblNewLabel_3.setBounds(227, 219, 82, 14);
		panel_Connexion.add(lblNewLabel_3);

		textConnect_Username = new JTextField();
		textConnect_Username.setBounds(103, 216, 120, 20);
		panel_Connexion.add(textConnect_Username);
		textConnect_Username.setColumns(10);

		btnConnect = new JButton("Connect");
		btnConnect.setBounds(32, 274, 89, 23);
		panel_Connexion.add(btnConnect);

		passwordField = new JPasswordField();
		passwordField.setBounds(293, 216, 120, 20);
		panel_Connexion.add(passwordField);

		panel_Admin_Gestion_User = new JPanel();
		panel_Admin_Gestion_User.setLayout(null);
		layeredPane.add(panel_Admin_Gestion_User, "name_504755795710100");

		btnAddNewUser = new JButton("Add new user");
		btnAddNewUser.setBounds(24, 97, 115, 23);
		panel_Admin_Gestion_User.add(btnAddNewUser);

		btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.setEnabled(false);
		btnDeleteUser.setBounds(24, 185, 107, 23);
		panel_Admin_Gestion_User.add(btnDeleteUser);

		btnRefreshUsers = new JButton("Refresh");
		btnRefreshUsers.setBounds(675, 185, 89, 23);
		panel_Admin_Gestion_User.add(btnRefreshUsers);

		scrollPane_User_Admin = new JScrollPane();
		scrollPane_User_Admin.setBounds(24, 214, 736, 313);
		panel_Admin_Gestion_User.add(scrollPane_User_Admin);

		table_User_Admin = new JTable(modelUtilisateur){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		table_User_Admin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane_User_Admin.setViewportView(table_User_Admin);

		btnModifyUser = new JButton("Modify user");
		btnModifyUser.setEnabled(false);

		btnModifyUser.setBounds(141, 185, 115, 23);
		panel_Admin_Gestion_User.add(btnModifyUser);

		txt_AddUser_Nom = new JTextField();
		txt_AddUser_Nom.setText("Nom");
		txt_AddUser_Nom.setBounds(146, 98, 86, 20);
		panel_Admin_Gestion_User.add(txt_AddUser_Nom);
		txt_AddUser_Nom.setColumns(10);

		txt_AddUser_Prenom = new JTextField();
		txt_AddUser_Prenom.setText("Prenom");
		txt_AddUser_Prenom.setColumns(10);
		txt_AddUser_Prenom.setBounds(242, 98, 86, 20);
		panel_Admin_Gestion_User.add(txt_AddUser_Prenom);

		txt_AddUser_Email = new JTextField();
		txt_AddUser_Email.setText("Email");
		txt_AddUser_Email.setColumns(10);
		txt_AddUser_Email.setBounds(339, 98, 86, 20);
		panel_Admin_Gestion_User.add(txt_AddUser_Email);

		txt_AddUser_Login = new JTextField();
		txt_AddUser_Login.setText("login");
		txt_AddUser_Login.setColumns(10);
		txt_AddUser_Login.setBounds(435, 98, 86, 20);
		panel_Admin_Gestion_User.add(txt_AddUser_Login);

		txt_AddUser_Motdepasse = new JTextField();
		txt_AddUser_Motdepasse.setText("MotDePasse");
		txt_AddUser_Motdepasse.setColumns(10);
		txt_AddUser_Motdepasse.setBounds(531, 98, 86, 20);
		panel_Admin_Gestion_User.add(txt_AddUser_Motdepasse);

		JLabel lblNewLabel_4 = new JLabel("Will the user be an admin :");
		lblNewLabel_4.setBounds(132, 131, 159, 14);
		panel_Admin_Gestion_User.add(lblNewLabel_4);

		JRadioButton rdbtn_AddUser_Admin_Yes = new JRadioButton("Yes");
		rdbtn_Group_Admin_AddUser.add(rdbtn_AddUser_Admin_Yes);
		rdbtn_AddUser_Admin_Yes.setBounds(297, 127, 47, 23);
		panel_Admin_Gestion_User.add(rdbtn_AddUser_Admin_Yes);

		JRadioButton rdbtn_AddUser_Admin_No = new JRadioButton("No");
		rdbtn_Group_Admin_AddUser.add(rdbtn_AddUser_Admin_No);
		rdbtn_AddUser_Admin_No.setBounds(346, 127, 47, 23);
		panel_Admin_Gestion_User.add(rdbtn_AddUser_Admin_No);
		rdbtn_AddUser_Admin_No.setSelected(rootPaneCheckingEnabled);

		lblNewLabel_5 = new JLabel("Gestion des utilisateurs");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 40));
		lblNewLabel_5.setBounds(191, -14, 554, 100);
		panel_Admin_Gestion_User.add(lblNewLabel_5);

		btnBack_To_MenuPrincipal = new JButton("Back");

		btnBack_To_MenuPrincipal.setBounds(675, 11, 89, 23);
		panel_Admin_Gestion_User.add(btnBack_To_MenuPrincipal);

		panel_Admin_ModifyUser = new JPanel();
		layeredPane.add(panel_Admin_ModifyUser, "name_510471556246800");
		panel_Admin_ModifyUser.setLayout(null);

		lblNewLabel_6 = new JLabel("Modify user n\u00B0");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_6.setBounds(10, 33, 168, 24);
		panel_Admin_ModifyUser.add(lblNewLabel_6);

		JButton btnCanceltoAdmin = new JButton("Cancel");

		btnCanceltoAdmin.setBounds(488, 280, 89, 23);
		panel_Admin_ModifyUser.add(btnCanceltoAdmin);

		txt_ModifyUser_Nom = new JTextField();
		txt_ModifyUser_Nom.setBounds(120, 76, 129, 20);
		panel_Admin_ModifyUser.add(txt_ModifyUser_Nom);
		txt_ModifyUser_Nom.setColumns(10);

		lblNewLabel_7 = new JLabel("Nom :");
		lblNewLabel_7.setBounds(80, 79, 46, 14);
		panel_Admin_ModifyUser.add(lblNewLabel_7);

		lbl_Show_User_Id = new JLabel("");
		lbl_Show_User_Id.setFont(new Font("Arial", Font.BOLD, 20));
		lbl_Show_User_Id.setBounds(159, 36, 118, 19);
		panel_Admin_ModifyUser.add(lbl_Show_User_Id);

		lblNewLabel_9 = new JLabel("Prenom :");
		lblNewLabel_9.setBounds(60, 110, 79, 14);
		panel_Admin_ModifyUser.add(lblNewLabel_9);

		txt_ModifyUser_Prenom = new JTextField();
		txt_ModifyUser_Prenom.setColumns(10);
		txt_ModifyUser_Prenom.setBounds(120, 107, 129, 20);
		panel_Admin_ModifyUser.add(txt_ModifyUser_Prenom);

		lblNewLabel_10 = new JLabel("Email :");
		lblNewLabel_10.setBounds(75, 141, 46, 14);
		panel_Admin_ModifyUser.add(lblNewLabel_10);

		txt_ModifyUser_Email = new JTextField();
		txt_ModifyUser_Email.setColumns(10);
		txt_ModifyUser_Email.setBounds(120, 138, 206, 20);
		panel_Admin_ModifyUser.add(txt_ModifyUser_Email);

		lblNewLabel_11 = new JLabel("Login :");
		lblNewLabel_11.setBounds(75, 172, 46, 14);
		panel_Admin_ModifyUser.add(lblNewLabel_11);

		txt_ModifyUser_Login = new JTextField();
		txt_ModifyUser_Login.setColumns(10);
		txt_ModifyUser_Login.setBounds(120, 169, 129, 20);
		panel_Admin_ModifyUser.add(txt_ModifyUser_Login);

		lblNewLabel_12 = new JLabel("Mot de passe :");
		lblNewLabel_12.setBounds(31, 203, 89, 14);
		panel_Admin_ModifyUser.add(lblNewLabel_12);

		txt_ModifyUser_MotDePasse = new JTextField();
		txt_ModifyUser_MotDePasse.setColumns(10);
		txt_ModifyUser_MotDePasse.setBounds(120, 200, 129, 20);
		panel_Admin_ModifyUser.add(txt_ModifyUser_MotDePasse);

		lblNewLabel_13 = new JLabel("Will the user be an admin :");
		lblNewLabel_13.setBounds(32, 249, 159, 14);
		panel_Admin_ModifyUser.add(lblNewLabel_13);

		rdbtn_ModifyUser_Admin_Yes = new JRadioButton("Yes");
		rdbtn_Group_Admin_Modify.add(rdbtn_ModifyUser_Admin_Yes);
		rdbtn_ModifyUser_Admin_Yes.setBounds(181, 245, 46, 23);
		panel_Admin_ModifyUser.add(rdbtn_ModifyUser_Admin_Yes);

		rdbtn_ModifyUser_Admin_No = new JRadioButton("No");
		rdbtn_Group_Admin_Modify.add(rdbtn_ModifyUser_Admin_No);
		rdbtn_ModifyUser_Admin_No.setBounds(229, 245, 109, 23);
		panel_Admin_ModifyUser.add(rdbtn_ModifyUser_Admin_No);

		btnAdmin_ModifyUser_SaveChanges = new JButton("Save Changes");
		btnAdmin_ModifyUser_SaveChanges.setBounds(349, 280, 129, 23);
		panel_Admin_ModifyUser.add(btnAdmin_ModifyUser_SaveChanges);

		panel_Menu_Principal = new JPanel();
		layeredPane.add(panel_Menu_Principal, "name_1111353405798400");
		panel_Menu_Principal.setLayout(null);

		JLabel lblNewLabel_14 = new JLabel("Menu Principal");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_14.setBounds(264, 11, 394, 49);
		panel_Menu_Principal.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("Admin Tab");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_15.setBounds(610, 164, 97, 19);
		panel_Menu_Principal.add(lblNewLabel_15);

		JButton btnMenu_Principal_GoAdminGestionUser = new JButton("Gestion utilisateurs");

		admin_ButtonGroupVisibility.add(btnMenu_Principal_GoAdminGestionUser);
		btnMenu_Principal_GoAdminGestionUser.setBounds(573, 210, 163, 23);
		panel_Menu_Principal.add(btnMenu_Principal_GoAdminGestionUser);

		JButton btnDisconnect = new JButton("Disconnect");

		btnDisconnect.setBounds(10, 11, 112, 23);
		panel_Menu_Principal.add(btnDisconnect);

		lblNewLabel_16 = new JLabel("Connect\u00E9 en tant que : ");
		lblNewLabel_16.setBounds(10, 74, 132, 14);
		panel_Menu_Principal.add(lblNewLabel_16);

		lblAdmin_Or_User = new JLabel("New label");
		lblAdmin_Or_User.setBounds(149, 74, 80, 14);
		panel_Menu_Principal.add(lblAdmin_Or_User);

		lblNewLabel_17 = new JLabel("Bienvenue ");
		lblNewLabel_17.setBounds(10, 95, 64, 14);
		panel_Menu_Principal.add(lblNewLabel_17);

		lblUserName = new JLabel("New label");
		lblUserName.setBounds(78, 95, 163, 14);
		panel_Menu_Principal.add(lblUserName);

		lblNewLabel_18 = new JLabel("User Tab");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_18.setBounds(59, 168, 97, 19);
		panel_Menu_Principal.add(lblNewLabel_18);

		btnMenu_Principal_GoAdminGestionFrigo = new JButton("Gestion Frigos");
		btnMenu_Principal_GoAdminGestionFrigo.setBounds(10, 270, 163, 23);
		panel_Menu_Principal.add(btnMenu_Principal_GoAdminGestionFrigo);

		JButton btnMenu_Principal_GoAdminGestionProduits = new JButton("Gestion Produits");

		btnMenu_Principal_GoAdminGestionProduits.setBounds(10, 210, 163, 23);
		panel_Menu_Principal.add(btnMenu_Principal_GoAdminGestionProduits);

		JButton btn_Go_GestionProduitRef = new JButton("Gestion produits de r\u00E9f\u00E9rence");
		admin_ButtonGroupVisibility.add(btn_Go_GestionProduitRef);

		btn_Go_GestionProduitRef.setBounds(551, 270, 206, 23);
		panel_Menu_Principal.add(btn_Go_GestionProduitRef);

		JLabel lblAttention = new JLabel("Attention");
		lblAttention.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAttention.setBounds(328, 423, 128, 14);
		panel_Menu_Principal.add(lblAttention);

		JLabel lblNewLabel_27 = new JLabel("Nombre de produits \u00E0 contr\u00F4ler :");
		lblNewLabel_27.setBounds(239, 448, 191, 14);
		panel_Menu_Principal.add(lblNewLabel_27);

		JLabel lblNewLabel_27_1 = new JLabel("Nombre de frigo non contr\u00F4l\u00E9s :");
		lblNewLabel_27_1.setBounds(244, 476, 221, 14);
		panel_Menu_Principal.add(lblNewLabel_27_1);

		lblProduitErreur = new JLabel("New label");
		lblProduitErreur.setBounds(432, 448, 80, 14);
		panel_Menu_Principal.add(lblProduitErreur);

		lblFrigoErreur = new JLabel("0");
		lblFrigoErreur.setBounds(432, 476, 80, 14);
		panel_Menu_Principal.add(lblFrigoErreur);

		JButton btnMenu_Principal_GoHistorique = new JButton("Historique");

		admin_ButtonGroupVisibility.add(btnMenu_Principal_GoHistorique);
		btnMenu_Principal_GoHistorique.setBounds(573, 330, 163, 23);
		panel_Menu_Principal.add(btnMenu_Principal_GoHistorique);

		panel_Admin_Gestion_Frigo = new JPanel();
		layeredPane.add(panel_Admin_Gestion_Frigo, "name_1157988579524400");
		panel_Admin_Gestion_Frigo.setLayout(null);

		JLabel lblNewLabel_5_1 = new JLabel("Gestion Frigo");
		lblNewLabel_5_1.setFont(new Font("Arial", Font.PLAIN, 40));
		lblNewLabel_5_1.setBounds(275, 11, 292, 57);
		panel_Admin_Gestion_Frigo.add(lblNewLabel_5_1);

		JScrollPane scrollPane_Frigo_Admin = new JScrollPane();
		scrollPane_Frigo_Admin.setBounds(24, 226, 736, 313);
		panel_Admin_Gestion_Frigo.add(scrollPane_Frigo_Admin);

		table_Frigo_Admin = new JTable(modelFrigo) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table_Frigo_Admin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane_Frigo_Admin.setViewportView(table_Frigo_Admin);

		JButton btn_Add_New_Frigo = new JButton("Ajouter frigo");
		admin_ButtonGroupVisibility.add(btn_Add_New_Frigo);
		btn_Add_New_Frigo.setBounds(24, 143, 137, 23);
		panel_Admin_Gestion_Frigo.add(btn_Add_New_Frigo);

		JButton btn_Modify_Frigo = new JButton("Modifier Frigo");
		btn_Modify_Frigo.setEnabled(false);
		btn_Modify_Frigo.setBounds(318, 192, 137, 23);
		panel_Admin_Gestion_Frigo.add(btn_Modify_Frigo);

		JButton btn_Delete_Frigo = new JButton("Supprimer Frigo");
		btn_Delete_Frigo.setEnabled(false);
		btn_Delete_Frigo.setBounds(171, 192, 137, 23);
		panel_Admin_Gestion_Frigo.add(btn_Delete_Frigo);

		JButton btnBack_To_MenuPrincipal_1 = new JButton("Back");

		btnBack_To_MenuPrincipal_1.setBounds(671, 11, 89, 23);
		panel_Admin_Gestion_Frigo.add(btnBack_To_MenuPrincipal_1);

		btnRefreshFrigo = new JButton("Refresh");
		btnRefreshFrigo.setBounds(671, 192, 89, 23);
		panel_Admin_Gestion_Frigo.add(btnRefreshFrigo);

		textField_Admin_Nom_Frigo = new JTextField();
		textField_Admin_Nom_Frigo.setText("Nom");
		textField_Admin_Nom_Frigo.setColumns(10);
		textField_Admin_Nom_Frigo.setBounds(181, 146, 86, 20);
		panel_Admin_Gestion_Frigo.add(textField_Admin_Nom_Frigo);

		textField_Admin_Emplacement_Frigo = new JTextField();
		textField_Admin_Emplacement_Frigo.setText("Emplacement");
		textField_Admin_Emplacement_Frigo.setColumns(10);
		textField_Admin_Emplacement_Frigo.setBounds(277, 146, 86, 20);
		panel_Admin_Gestion_Frigo.add(textField_Admin_Emplacement_Frigo);

		JButton btn_ControlerTemp_Frigo = new JButton("Controler Temp");
		btn_ControlerTemp_Frigo.setEnabled(false);
		btn_ControlerTemp_Frigo.setBounds(24, 192, 137, 23);
		panel_Admin_Gestion_Frigo.add(btn_ControlerTemp_Frigo);

		panel_Admin_ModifyFrigo = new JPanel();
		layeredPane.add(panel_Admin_ModifyFrigo, "name_1201773976017600");
		panel_Admin_ModifyFrigo.setLayout(null);

		JLabel lblNewLabel_7_1 = new JLabel("Nom :");
		lblNewLabel_7_1.setBounds(80, 57, 46, 14);
		panel_Admin_ModifyFrigo.add(lblNewLabel_7_1);

		txt_ModifyFrigo_Nom = new JTextField();
		txt_ModifyFrigo_Nom.setColumns(10);
		txt_ModifyFrigo_Nom.setBounds(120, 54, 129, 20);
		panel_Admin_ModifyFrigo.add(txt_ModifyFrigo_Nom);

		JLabel lblNewLabel_9_1 = new JLabel("Emplacement :");
		lblNewLabel_9_1.setBounds(29, 88, 118, 14);
		panel_Admin_ModifyFrigo.add(lblNewLabel_9_1);

		txt_ModifyFrigo_Emplacement = new JTextField();
		txt_ModifyFrigo_Emplacement.setColumns(10);
		txt_ModifyFrigo_Emplacement.setBounds(120, 85, 129, 20);
		panel_Admin_ModifyFrigo.add(txt_ModifyFrigo_Emplacement);

		JLabel lblNewLabel_10_1 = new JLabel("Temp Matin :");
		lblNewLabel_10_1.setBounds(41, 119, 79, 14);
		panel_Admin_ModifyFrigo.add(lblNewLabel_10_1);

		txt_ModifyFrigo_TempMatin = new JTextField();
		txt_ModifyFrigo_TempMatin.setColumns(10);
		txt_ModifyFrigo_TempMatin.setBounds(120, 116, 63, 20);
		panel_Admin_ModifyFrigo.add(txt_ModifyFrigo_TempMatin);

		JLabel lblNewLabel_11_1 = new JLabel("Temp Aprem :");
		lblNewLabel_11_1.setBounds(34, 150, 79, 14);
		panel_Admin_ModifyFrigo.add(lblNewLabel_11_1);

		txt_ModifyFrigo_TempAprem = new JTextField();
		txt_ModifyFrigo_TempAprem.setColumns(10);
		txt_ModifyFrigo_TempAprem.setBounds(120, 147, 63, 20);
		panel_Admin_ModifyFrigo.add(txt_ModifyFrigo_TempAprem);

		JLabel lblNewLabel_6_1 = new JLabel("Modifier Frigo n\u00B0");
		lblNewLabel_6_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_6_1.setBounds(10, 11, 168, 24);
		panel_Admin_ModifyFrigo.add(lblNewLabel_6_1);

		JButton btnAdmin_ModifyFrigo_SaveChanges = new JButton("Save Changes");
		btnAdmin_ModifyFrigo_SaveChanges.setBounds(349, 258, 129, 23);
		panel_Admin_ModifyFrigo.add(btnAdmin_ModifyFrigo_SaveChanges);

		JButton btnCanceltoFrigo = new JButton("Cancel");
		btnCanceltoFrigo.setBounds(488, 258, 89, 23);
		panel_Admin_ModifyFrigo.add(btnCanceltoFrigo);

		JLabel lbl_Show_Frigo_Id = new JLabel("");
		lbl_Show_Frigo_Id.setFont(new Font("Arial", Font.BOLD, 20));
		lbl_Show_Frigo_Id.setBounds(176, 15, 118, 19);
		panel_Admin_ModifyFrigo.add(lbl_Show_Frigo_Id);

		JPanel panel_Admin_Gestion_Produits = new JPanel();

		layeredPane.add(panel_Admin_Gestion_Produits, "name_1545330661054300");
		panel_Admin_Gestion_Produits.setLayout(null);

		JButton btnBack_To_MenuPrincipal_2 = new JButton("Back");

		btnBack_To_MenuPrincipal_2.setBounds(671, 11, 89, 23);
		panel_Admin_Gestion_Produits.add(btnBack_To_MenuPrincipal_2);

		JLabel lblNewLabel_5_2 = new JLabel("Gestion des produits");
		lblNewLabel_5_2.setFont(new Font("Arial", Font.PLAIN, 40));
		lblNewLabel_5_2.setBounds(213, 0, 423, 70);
		panel_Admin_Gestion_Produits.add(lblNewLabel_5_2);

		JScrollPane scrollPane_Produit_Admin = new JScrollPane();
		scrollPane_Produit_Admin.setBounds(24, 218, 736, 313);
		panel_Admin_Gestion_Produits.add(scrollPane_Produit_Admin);

		table_Produit_Admin = new JTable(modelProduit) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};


		table_Produit_Admin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane_Produit_Admin.setViewportView(table_Produit_Admin);

		JButton btnRefreshProduit = new JButton("Refresh");

		btnRefreshProduit.setBounds(671, 184, 89, 23);
		panel_Admin_Gestion_Produits.add(btnRefreshProduit);

		JButton btn_Admin_Go_AjouterProduit = new JButton("Ajouter produit...");

		btn_Admin_Go_AjouterProduit.setBounds(24, 184, 156, 23);
		panel_Admin_Gestion_Produits.add(btn_Admin_Go_AjouterProduit);

		JButton btn_Admin_SupprimerProduit = new JButton("Supprimer produit");


		btn_Admin_SupprimerProduit.setEnabled(false);
		btn_Admin_SupprimerProduit.setBounds(195, 184, 156, 23);
		panel_Admin_Gestion_Produits.add(btn_Admin_SupprimerProduit);

		btn_Admin_ModifierProduit = new JButton("Modifier produit...");

		btn_Admin_ModifierProduit.setEnabled(false);
		btn_Admin_ModifierProduit.setBounds(370, 184, 156, 23);
		panel_Admin_Gestion_Produits.add(btn_Admin_ModifierProduit);

		JLabel lblNewLabel_26 = new JLabel("Date et heure du jour :");
		lblNewLabel_26.setBounds(10, 78, 145, 14);
		panel_Admin_Gestion_Produits.add(lblNewLabel_26);

		JLabel lbl_DateEtHeure = new JLabel("New label");
		lbl_DateEtHeure.setBounds(10, 97, 170, 14);
		panel_Admin_Gestion_Produits.add(lbl_DateEtHeure);

		JButton btn_Changer_Statut = new JButton("Changer Statut");
		btn_Changer_Statut.setEnabled(false);

		btn_Changer_Statut.setBounds(24, 136, 156, 23);
		panel_Admin_Gestion_Produits.add(btn_Changer_Statut);

		JComboBox<String> combo_Produit_Changer_Statut = new JComboBox<String>();
		combo_Produit_Changer_Statut.setModel(new DefaultComboBoxModel<String>(new String[] {"Ok", "Retir\u00E9", "Consom\u00E9"}));
		combo_Produit_Changer_Statut.setBounds(195, 136, 80, 22);
		panel_Admin_Gestion_Produits.add(combo_Produit_Changer_Statut);

		JPanel panel_Admin_Ajouter_Produits = new JPanel();
		layeredPane.add(panel_Admin_Ajouter_Produits, "name_1589713037271700");
		panel_Admin_Ajouter_Produits.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Veuillez s\u00E9lectionner les informations du produit \u00E0 ajouter");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(10, 0, 576, 31);
		panel_Admin_Ajouter_Produits.add(lblNewLabel_8);

		JButton btnBackToGestionProduits = new JButton("Back");

		btnBackToGestionProduits.setBounds(671, 8, 89, 23);
		panel_Admin_Ajouter_Produits.add(btnBackToGestionProduits);

		JLabel lblNewLabel_19_2 = new JLabel("S\u00E9lectionnez la r\u00E9f\u00E9rence du produit");
		lblNewLabel_19_2.setBounds(25, 212, 221, 14);
		panel_Admin_Ajouter_Produits.add(lblNewLabel_19_2);

		JScrollPane scrollPane_AddProduit_Frigo = new JScrollPane();
		scrollPane_AddProduit_Frigo.setBounds(24, 414, 736, 136);
		panel_Admin_Ajouter_Produits.add(scrollPane_AddProduit_Frigo);

		table_AddProduit_Frigo = new JTable(modelFrigo) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table_AddProduit_Frigo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		

		scrollPane_AddProduit_Frigo.setViewportView(table_AddProduit_Frigo);

		JButton btn_Admin_ValiderProduit = new JButton("Valider");

		btn_Admin_ValiderProduit.setBounds(671, 208, 89, 23);
		panel_Admin_Ajouter_Produits.add(btn_Admin_ValiderProduit);

		JScrollPane scrollPane_AddProduit_Ref = new JScrollPane();
		scrollPane_AddProduit_Ref.setBounds(24, 242, 736, 136);
		panel_Admin_Ajouter_Produits.add(scrollPane_AddProduit_Ref);

		table_AddProduit_Ref = new JTable(modelProduitRef) {


			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		scrollPane_AddProduit_Ref.setViewportView(table_AddProduit_Ref);
		table_AddProduit_Ref.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lblNewLabel_19_2_2 = new JLabel("S\u00E9lectionnez le frigo de stockage");
		lblNewLabel_19_2_2.setBounds(25, 389, 221, 14);
		panel_Admin_Ajouter_Produits.add(lblNewLabel_19_2_2);

		JPanel panel_Admin_Modifier_Produits = new JPanel();
		layeredPane.add(panel_Admin_Modifier_Produits, "name_103737022454000");
		panel_Admin_Modifier_Produits.setLayout(null);

		JLabel lblNewLabel_8_1 = new JLabel("Modifiez ci dessous les informations du produit");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8_1.setBounds(10, 0, 576, 31);
		panel_Admin_Modifier_Produits.add(lblNewLabel_8_1);

		JLabel lblNewLabel_20 = new JLabel("Date Entr\u00E9e produit : ");
		lblNewLabel_20.setBounds(31, 118, 122, 14);
		panel_Admin_Modifier_Produits.add(lblNewLabel_20);

		JLabel lblNewLabel_21 = new JLabel("DLC du produit : ");
		lblNewLabel_21.setBounds(56, 160, 150, 14);
		panel_Admin_Modifier_Produits.add(lblNewLabel_21);

		JScrollPane scrollPane_Modifier_Admin = new JScrollPane();
		scrollPane_Modifier_Admin.setBounds(31, 243, 736, 313);
		panel_Admin_Modifier_Produits.add(scrollPane_Modifier_Admin);

		table_Modifier_Produit = new JTable(modelFrigo){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table_Modifier_Produit.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPane_Modifier_Admin.setViewportView(table_Modifier_Produit);

		JLabel lblNewLabel_19_2_1 = new JLabel("Frigo associer au produit :");
		lblNewLabel_19_2_1.setBounds(32, 218, 221, 14);
		panel_Admin_Modifier_Produits.add(lblNewLabel_19_2_1);

		JButton btn_Admin_ValiderModifProduit = new JButton("Valider");

		btn_Admin_ValiderModifProduit.setBounds(678, 214, 89, 23);
		panel_Admin_Modifier_Produits.add(btn_Admin_ValiderModifProduit);

		JButton btnBackToGestionProduits_1 = new JButton("Back");

		btnBackToGestionProduits_1.setBounds(678, 8, 89, 23);
		panel_Admin_Modifier_Produits.add(btnBackToGestionProduits_1);

		txtAdmin_ModifierProduit_DateEntree = new JTextField();
		txtAdmin_ModifierProduit_DateEntree.setBounds(152, 115, 132, 20);
		panel_Admin_Modifier_Produits.add(txtAdmin_ModifierProduit_DateEntree);
		txtAdmin_ModifierProduit_DateEntree.setColumns(10);

		txtAdmin_ModifierProduit_DLC = new JTextField();
		txtAdmin_ModifierProduit_DLC.setColumns(10);
		txtAdmin_ModifierProduit_DLC.setBounds(152, 157, 132, 20);
		panel_Admin_Modifier_Produits.add(txtAdmin_ModifierProduit_DLC);

		JLabel lblNewLabel_19 = new JLabel("Format date :   dd/MM/yyyy HH:mm:ss");
		lblNewLabel_19.setBounds(71, 76, 233, 14);
		panel_Admin_Modifier_Produits.add(lblNewLabel_19);

		panel_Admin_Gestion_ProduitRef = new JPanel();
		layeredPane.add(panel_Admin_Gestion_ProduitRef, "name_608666947551000");
		panel_Admin_Gestion_ProduitRef.setLayout(null);

		JLabel lblNewLabel_5_2_1 = new JLabel("Gestion des Ref produit");
		lblNewLabel_5_2_1.setFont(new Font("Arial", Font.PLAIN, 40));
		lblNewLabel_5_2_1.setBounds(186, 11, 475, 70);
		panel_Admin_Gestion_ProduitRef.add(lblNewLabel_5_2_1);

		JScrollPane scrollPane_ProduitRef_Admin = new JScrollPane();
		scrollPane_ProduitRef_Admin.setBounds(24, 237, 736, 313);
		panel_Admin_Gestion_ProduitRef.add(scrollPane_ProduitRef_Admin);

		table_ProduitRef_Admin = new JTable(modelProduitRef) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		scrollPane_ProduitRef_Admin.setViewportView(table_ProduitRef_Admin);
		table_ProduitRef_Admin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton btnBack_to_MenuPrincipal = new JButton("Back");

		btnBack_to_MenuPrincipal.setBounds(671, 11, 89, 23);
		panel_Admin_Gestion_ProduitRef.add(btnBack_to_MenuPrincipal);

		btnRefreshProduitRef = new JButton("Refresh");

		btnRefreshProduitRef.setBounds(671, 203, 89, 23);
		panel_Admin_Gestion_ProduitRef.add(btnRefreshProduitRef);

		btn_Modify_produitRef = new JButton("Modifier...");

		btn_Modify_produitRef.setEnabled(false);
		btn_Modify_produitRef.setBounds(24, 203, 97, 23);
		panel_Admin_Gestion_ProduitRef.add(btn_Modify_produitRef);

		btn_Delete_ProduitRef = new JButton("Supprimer");

		btn_Delete_ProduitRef.setEnabled(false);
		btn_Delete_ProduitRef.setBounds(139, 203, 102, 23);
		panel_Admin_Gestion_ProduitRef.add(btn_Delete_ProduitRef);

		btnAjouter_ProduitRef = new JButton("Ajouter");

		btnAjouter_ProduitRef.setBounds(434, 133, 89, 23);
		panel_Admin_Gestion_ProduitRef.add(btnAjouter_ProduitRef);

		txt_AjouterProduitRef_Nom = new JTextField();
		txt_AjouterProduitRef_Nom.setText("Nom");
		txt_AjouterProduitRef_Nom.setBounds(24, 134, 86, 20);
		panel_Admin_Gestion_ProduitRef.add(txt_AjouterProduitRef_Nom);
		txt_AjouterProduitRef_Nom.setColumns(10);

		JComboBox<Object> combo_Admin_AjouterProduitRef_Categorie = new JComboBox<Object>(new Object[]{});
		combo_Admin_AjouterProduitRef_Categorie.setModel(new DefaultComboBoxModel<Object>(new String[] {"Viande", "Poisson", "Volaille", "Fromage", "Oeufs", "L\u00E9gumes", "Fruits", "Boisson", "Sauce"}));
		combo_Admin_AjouterProduitRef_Categorie.setBounds(128, 133, 113, 22);
		panel_Admin_Gestion_ProduitRef.add(combo_Admin_AjouterProduitRef_Categorie);

		text_AjouterProduitRef_Dlc = new JTextField();
		text_AjouterProduitRef_Dlc.setText("0");
		text_AjouterProduitRef_Dlc.setBounds(327, 134, 86, 20);
		panel_Admin_Gestion_ProduitRef.add(text_AjouterProduitRef_Dlc);
		text_AjouterProduitRef_Dlc.setColumns(10);

		text_AjouterProduitRef_Dlc.addKeyListener(new KeyAdapter() {
			@Override    ////////////////////// Digit only for this textfield
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		JLabel lblNewLabel_22 = new JLabel("Dlc en Heure");
		lblNewLabel_22.setBounds(251, 137, 76, 14);
		panel_Admin_Gestion_ProduitRef.add(lblNewLabel_22);

		panel_Admin_Modifier_ProduitRef = new JPanel();
		layeredPane.add(panel_Admin_Modifier_ProduitRef, "name_608669161033400");
		panel_Admin_Modifier_ProduitRef.setLayout(null);

		lblNewLabel_5_2_2 = new JLabel("Modifier produit de r\u00E9f\u00E9rence");
		lblNewLabel_5_2_2.setFont(new Font("Arial", Font.PLAIN, 40));
		lblNewLabel_5_2_2.setBounds(140, 11, 547, 70);
		panel_Admin_Modifier_ProduitRef.add(lblNewLabel_5_2_2);

		btn_back_to_GestionProduitRef = new JButton("Back");

		btn_back_to_GestionProduitRef.setBounds(674, 11, 89, 23);
		panel_Admin_Modifier_ProduitRef.add(btn_back_to_GestionProduitRef);

		txt_ModifierProduitRef_Nom = new JTextField();
		txt_ModifierProduitRef_Nom.setText("Nom");
		txt_ModifierProduitRef_Nom.setColumns(10);
		txt_ModifierProduitRef_Nom.setBounds(142, 139, 113, 20);
		panel_Admin_Modifier_ProduitRef.add(txt_ModifierProduitRef_Nom);

		combo_Admin_ModifierProduitRef_Categorie = new JComboBox<Object>(new Object[]{});
		combo_Admin_ModifierProduitRef_Categorie.setModel(new DefaultComboBoxModel<Object>(new String[] {"Viande", "Poisson", "Volaille", "Fromage", "Oeufs", "L\u00E9gumes", "Fruits", "Boisson", "Sauce"}));
		combo_Admin_ModifierProduitRef_Categorie.setBounds(142, 173, 113, 22);
		panel_Admin_Modifier_ProduitRef.add(combo_Admin_ModifierProduitRef_Categorie);

		lblNewLabel_23 = new JLabel("Dlc en Heure :");
		lblNewLabel_23.setBounds(50, 221, 76, 14);
		panel_Admin_Modifier_ProduitRef.add(lblNewLabel_23);

		txt_ModifierProduitRef_Dlc = new JTextField();
		txt_ModifierProduitRef_Dlc.setText("0");
		txt_ModifierProduitRef_Dlc.setColumns(10);
		txt_ModifierProduitRef_Dlc.setBounds(142, 218, 86, 20);
		panel_Admin_Modifier_ProduitRef.add(txt_ModifierProduitRef_Dlc);

		btnModifier_ProduitRef_SaveChanges = new JButton("Valider");

		btnModifier_ProduitRef_SaveChanges.setBounds(332, 272, 89, 23);
		panel_Admin_Modifier_ProduitRef.add(btnModifier_ProduitRef_SaveChanges);

		lblNewLabel_24 = new JLabel("Cat\u00E9gorie :");
		lblNewLabel_24.setBounds(50, 177, 65, 14);
		panel_Admin_Modifier_ProduitRef.add(lblNewLabel_24);

		lblNewLabel_25 = new JLabel("Nom :");
		lblNewLabel_25.setBounds(69, 142, 46, 14);
		panel_Admin_Modifier_ProduitRef.add(lblNewLabel_25);

		JPanel panel_Historique = new JPanel();
		layeredPane.add(panel_Historique, "name_85736005249200");
		panel_Historique.setLayout(null);

		JLabel lblNewLabel_5_3 = new JLabel("Historique");
		lblNewLabel_5_3.setFont(new Font("Arial", Font.PLAIN, 35));
		lblNewLabel_5_3.setBounds(315, 0, 231, 56);
		panel_Historique.add(lblNewLabel_5_3);

		JButton btnBack_To_MenuPrincipal_Historique = new JButton("Back");

		btnBack_To_MenuPrincipal_Historique.setBounds(671, 11, 89, 23);
		panel_Historique.add(btnBack_To_MenuPrincipal_Historique);

		JScrollPane scrollPane_Historique = new JScrollPane();
		scrollPane_Historique.setBounds(24, 96, 736, 454);
		panel_Historique.add(scrollPane_Historique);

		table_Historique = new JTable(modelHistorique){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) { 
				return false;
			}
		};
		scrollPane_Historique.setViewportView(table_Historique);
		table_Historique.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ctpMain.setLayout(gl_ctpMain);

		Historique historique = new Historique();

		//////////////////////////
		//// Initialisation tables
		///////////////////////////
		modelUtilisateur.addColumn("Id");
		modelUtilisateur.addColumn("Prenom");
		modelUtilisateur.addColumn("Nom");
		modelUtilisateur.addColumn("Mail");
		modelUtilisateur.addColumn("Admin");

		modelFrigo.addColumn("Id");
		modelFrigo.addColumn("Nom");
		modelFrigo.addColumn("Emplacement");
		modelFrigo.addColumn("Temp Matin");
		modelFrigo.addColumn("Temp Aprem");
		modelFrigo.addColumn("Date derniere prise");

		modelProduit.addColumn("Id");
		modelProduit.addColumn("Nom");
		modelProduit.addColumn("Catégorie");
		modelProduit.addColumn("Statut");
		modelProduit.addColumn("Frigo Associé");
		modelProduit.addColumn("Ajouté le");
		modelProduit.addColumn("DLC");

		modelProduitRef.addColumn("Id");
		modelProduitRef.addColumn("Nom");
		modelProduitRef.addColumn("Catégorie");
		modelProduitRef.addColumn("Dlc(H)");

		modelHistorique.addColumn("Utilisateur");
		modelHistorique.addColumn("Action");
		modelHistorique.addColumn("Date");

		table_Produit_Admin.getColumnModel().getColumn(0).setMaxWidth(40);
		table_Produit_Admin.getColumnModel().getColumn(3).setMaxWidth(100);
		table_User_Admin.getColumnModel().getColumn(0).setMaxWidth(40);
		table_Frigo_Admin.getColumnModel().getColumn(0).setMaxWidth(40);
		table_Historique.getColumnModel().getColumn(0).setMaxWidth(200);
		table_Historique.getColumnModel().getColumn(0).setMinWidth(120);
		table_Historique.getColumnModel().getColumn(2).setMaxWidth(200);
		table_Historique.getColumnModel().getColumn(2).setMinWidth(120);

		refreshTableUtilisateur(modelUtilisateur);
		refreshTableFrigo(modelFrigo);
		refreshTableProduit(modelProduit);
		refreshTableProduitRef(modelProduitRef);
		refreshTableHistorique(modelHistorique);

		////////////////////
		////Events Section
		////////////////////

		btnRefreshUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTableUtilisateur(modelUtilisateur);
				btnModifyUser.setEnabled(false);
				btnDeleteUser.setEnabled(false);
			}
		});

		btnRefreshFrigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTableFrigo(modelFrigo);
				btn_ControlerTemp_Frigo.setEnabled(false);
				btn_Modify_Frigo.setEnabled(false);
				btn_Delete_Frigo.setEnabled(false);
			}
		});

		btnRefreshProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar dateActuelleCalendar = Calendar.getInstance();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH'h' mm'm'");
				java.util.Date dateActuelle = dateActuelleCalendar.getTime();
				lbl_DateEtHeure.setText(dateFormat.format(dateActuelle));
				refreshTableProduit(modelProduit);
				btn_Changer_Statut.setEnabled(false);
				btn_Admin_SupprimerProduit.setEnabled(false);
				btn_Admin_ModifierProduit.setEnabled(false);
			}
		});

		btnRefreshProduitRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTableProduitRef(modelProduitRef);
				btn_Delete_ProduitRef.setEnabled(false);
				btn_Modify_produitRef.setEnabled(false);
			}
		});

		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DAOFactory daoFactory = DAOFactory.getInstance();
				UtilisateurDao utilisateurDaoImpl = daoFactory.getUtilisateurDaoImpl();
				Utilisateur utilisateur = utilisateurDaoImpl.get(Integer.parseInt(String.valueOf(table_User_Admin.getValueAt(table_User_Admin.getSelectedRow(), 0))));
				if ((JOptionPane.showConfirmDialog(null, "Voulez vous supprimer " +utilisateur.toString()+" ?")) ==0) {
					utilisateurDaoImpl.delete(utilisateur);
					HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
					String statut;
					if (utilisateur.getIsAdmin().equals("Yes")) {
						statut = "Admin";
					} else {
						statut ="Utilisateur";
					}
					historiqueDaoImpl.save(historique, "Supression "+statut+" : "+utilisateur.getPrenom()+" "+utilisateur.getNom()+"      Id :"+utilisateur.getId());
					refreshTableUtilisateur(modelUtilisateur);
					btnModifyUser.setEnabled(false);
					btnDeleteUser.setEnabled(false);
				}
			}
		});

		btn_Delete_ProduitRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				ProduitReferenceDao produitReferenceDaoImpl = daoFactory.getProduitRefDaoImpl();
				ProduitReference produitReference = produitReferenceDaoImpl.get(Integer.parseInt(String.valueOf(table_ProduitRef_Admin.getValueAt(table_ProduitRef_Admin.getSelectedRow(), 0))));
				if ((JOptionPane.showConfirmDialog(null, "Voulez vous supprimer " +produitReference.toString()+" ?")) ==0) {
					produitReferenceDaoImpl.delete(produitReference);
					HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
					historiqueDaoImpl.save(historique, "Suppression Reference produit "+produitReference.getNom()+" catégorie : "+produitReference.getCategorie());
					refreshTableProduitRef(modelProduitRef);
					btn_Delete_ProduitRef.setEnabled(false);
					btn_Modify_produitRef.setEnabled(false);
				}
			}
		});

		btn_Delete_Frigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DAOFactory daoFactory = DAOFactory.getInstance();
				FrigoDao frigoDaoImpl = daoFactory.getFrigoDaoImpl();
				Frigo frigo = frigoDaoImpl.get(Long.parseLong(String.valueOf(table_Frigo_Admin.getValueAt(table_Frigo_Admin.getSelectedRow(), 0))));
				long countproduits = frigoDaoImpl.countAssociatedProduit(frigo);
				if (countproduits==0){
					if ((JOptionPane.showConfirmDialog(null, "Voulez vous supprimer " +frigo.toString()+" ?")) ==0) {
						frigoDaoImpl.delete(frigo);
						HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
						historiqueDaoImpl.save(historique, "Suppression frigo "+frigo.getNom()+"     Id: "+frigo.getId());
						refreshTableFrigo(modelFrigo);
						btn_Modify_Frigo.setEnabled(false);
						btn_Delete_Frigo.setEnabled(false);
					}
				} else if (countproduits!=0){
					JOptionPane.showMessageDialog(null, "Impossible de supprimer le frigo si il y a des produits dedans. (Il y a actuellement " +countproduits+" produits dans le frigo)" );
				}

			}
		});


		btn_Admin_SupprimerProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				ProduitDao produitDaoImpl = daoFactory.getProduitDaoImpl();
				Produit produit = produitDaoImpl.get(Long.parseLong(String.valueOf(table_Produit_Admin.getValueAt(table_Produit_Admin.getSelectedRow(), 0))));
				if ((JOptionPane.showConfirmDialog(null, "Voulez vous supprimer " +produit.toString()+" ?")) ==0) {
					produitDaoImpl.delete(produit);
					HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
					historiqueDaoImpl.save(historique, "Suppression produit "+produit.getNom()+"    Id : "+produit.getId());
					btn_Changer_Statut.setEnabled(false);
					btn_Admin_SupprimerProduit.setEnabled(false);
					btn_Admin_ModifierProduit.setEnabled(false);
					refreshTableProduit(modelProduit);
				}
			}
		});

		btnAddNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				UtilisateurDao utilisateurDaoImpl = daoFactory.getUtilisateurDaoImpl();
				Utilisateur utilisateur1 = new Utilisateur();
				utilisateur1.setEmail(txt_AddUser_Email.getText());
				utilisateur1.setNom(txt_AddUser_Nom.getText());
				utilisateur1.setPrenom(txt_AddUser_Prenom.getText());
				utilisateur1.setLogin(txt_AddUser_Login.getText());
				utilisateur1.setMotDePasse(txt_AddUser_Motdepasse.getText());
				if (rdbtn_AddUser_Admin_Yes.isSelected()) {
					utilisateur1.setIsAdmin("Yes");
				} else {
					utilisateur1.setIsAdmin("No");
				}
				utilisateurDaoImpl.save(utilisateur1);
				btnModifyUser.setEnabled(false);
				btnDeleteUser.setEnabled(false);
				refreshTableUtilisateur(modelUtilisateur);
				HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
				String statut;
				if (utilisateur1.getIsAdmin().equals("Yes")) {
					statut = "Admin";
				} else {
					statut ="Utilisateur";
				}
				historiqueDaoImpl.save(historique, "Ajout "+statut+" : "+utilisateur1.getPrenom()+" "+utilisateur1.getNom()+"     Id : "+utilisateur1.getId());
			}
		});

		btnAjouter_ProduitRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				ProduitReferenceDao produitReferenceDao = daoFactory.getProduitRefDaoImpl();
				ProduitReference produitReference = new ProduitReference();
				produitReference.setNom(txt_AjouterProduitRef_Nom.getText());
				produitReference.setCategorie(String.valueOf(combo_Admin_AjouterProduitRef_Categorie.getSelectedItem()));
				try {
					produitReference.setDlcHeure(Long.parseLong(text_AjouterProduitRef_Dlc.getText()));
				}
				catch (Exception e1) {
					produitReference.setDlcHeure(0);
				}
				produitReferenceDao.save(produitReference);
				HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
				historiqueDaoImpl.save(historique, "Ajout Reference produit "+produitReference.getNom()+" catégorie : "+produitReference.getCategorie()+"      Id : "+produitReference.getId());
				btn_Delete_ProduitRef.setEnabled(false);
				btn_Modify_produitRef.setEnabled(false);
				refreshTableProduitRef(modelProduitRef);
			}
		});

		btn_Add_New_Frigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				FrigoDao frigoDaoImpl = daoFactory.getFrigoDaoImpl();
				Frigo frigo1 = new Frigo();
				frigo1.setEmplacement(textField_Admin_Emplacement_Frigo.getText());
				frigo1.setNom(textField_Admin_Nom_Frigo.getText());
				frigoDaoImpl.save(frigo1);
				HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
				historiqueDaoImpl.save(historique, "Ajout frigo : "+frigo1.getNom()+"     Emplacement : "+frigo1.getEmplacement() + "     Id : "+frigo1.getId());
				refreshTableFrigo(modelFrigo);
				btn_Modify_Frigo.setEnabled(false);
				btn_Delete_Frigo.setEnabled(false);
			}
		});

		btn_Admin_ValiderProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				ProduitDao produitDaoImpl = daoFactory.getProduitDaoImpl();
				Produit produit = new Produit();
				produit.setNom(String.valueOf(table_AddProduit_Ref.getValueAt(table_AddProduit_Ref.getSelectedRow(), 1)));
				produit.setCategorie(String.valueOf(table_AddProduit_Ref.getValueAt(table_AddProduit_Ref.getSelectedRow(), 2)));
				produit.setIdFrigoAssocier(Long.parseLong(String.valueOf(table_AddProduit_Frigo.getValueAt(table_AddProduit_Frigo.getSelectedRow(), 0))));
				produit.setStatut("Ok");

				Calendar dateActuelle = Calendar.getInstance();
				String formatedDateActuelle = dateActuelle.get(Calendar.YEAR)+ "-" + (dateActuelle.get(Calendar.MONTH)+1) + "-" + dateActuelle.get(Calendar.DAY_OF_MONTH) + " " + dateActuelle.get(Calendar.HOUR_OF_DAY) + ":" + dateActuelle.get(Calendar.MINUTE) + ":" + dateActuelle.get(Calendar.SECOND);
				Timestamp TsDateActuelle = Timestamp.valueOf(formatedDateActuelle);
				produit.setDateHeureEntree(TsDateActuelle);

				Timestamp TsDateDlc = Timestamp.valueOf(formatedDateActuelle);
				int dlcHeure = Integer.parseInt(String.valueOf(table_AddProduit_Ref.getValueAt(table_AddProduit_Ref.getSelectedRow(), 3)));
				TsDateDlc.setTime(TsDateDlc.getTime()+(dlcHeure*3600000));
				produit.setDlc(TsDateDlc);

				btn_Changer_Statut.setEnabled(false);
				btn_Admin_ModifierProduit.setEnabled(false);
				btn_Admin_SupprimerProduit.setEnabled(false);
				produitDaoImpl.save(produit);
				HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
				historiqueDaoImpl.save(historique, "Ajout produit "+produit.getNom()+"    Id : "+produit.getId()+"    Frigo : "+produit.getIdFrigoAssocier());
				refreshTableProduit(modelProduit);
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Gestion_Produits);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) throws ConnexionException {
				try {
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setLogin(textConnect_Username.getText());
					utilisateur.setMotDePasse(String.valueOf(passwordField.getPassword()));

					DAOFactory daoFactory = DAOFactory.getInstance();
					UtilisateurDao utilisateurDaoImpl = daoFactory.getUtilisateurDaoImpl();
					utilisateur = utilisateurDaoImpl.connexionUtlisateur(utilisateur.getLogin(), utilisateur.getMotDePasse());

					utilisateur.toString();

					layeredPane.removeAll();
					layeredPane.add(panel_Menu_Principal);
					layeredPane.repaint();
					layeredPane.revalidate();

					lblUserName.setText(utilisateur.getPrenom()+" "+utilisateur.getNom());
					historique.setUtilisateur(utilisateur.getPrenom()+" "+utilisateur.getNom());

					if (utilisateur.getIsAdmin().equals("No")) {

						Enumeration<AbstractButton> enumeration = admin_ButtonGroupVisibility.getElements();
						while (enumeration.hasMoreElements()) {
							enumeration.nextElement().setEnabled(false);
							textField_Admin_Nom_Frigo.setEnabled(false);
							textField_Admin_Emplacement_Frigo.setEnabled(false);
							lblAdmin_Or_User.setText("Utilisateur");
						}
					}

					if (utilisateur.getIsAdmin().equals("Yes")) {

						Enumeration<AbstractButton> enumeration = admin_ButtonGroupVisibility.getElements();
						while (enumeration.hasMoreElements()) {
							enumeration.nextElement().setEnabled(true);
							textField_Admin_Nom_Frigo.setEnabled(true);
							textField_Admin_Emplacement_Frigo.setEnabled(true);
							lblAdmin_Or_User.setText("Admin");
						}
					}
					HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
					historiqueDaoImpl.save(historique, "Connexion");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Mauvais Login ou Mdp");
				}
			}
		});



		btnModifyUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				DAOFactory daoFactory = DAOFactory.getInstance();
				UtilisateurDao utilisateurDaoImpl = daoFactory.getUtilisateurDaoImpl();
				Utilisateur utilisateur = utilisateurDaoImpl.get(Long.parseLong(String.valueOf(table_User_Admin.getValueAt(table_User_Admin.getSelectedRow(), 0))));

				txt_ModifyUser_Nom.setText(utilisateur.getNom());;
				txt_ModifyUser_Prenom.setText(utilisateur.getPrenom());
				txt_ModifyUser_Email.setText(utilisateur.getEmail());
				txt_ModifyUser_Login.setText(utilisateur.getLogin());
				txt_ModifyUser_MotDePasse.setText(utilisateur.getMotDePasse());

				if (utilisateur.getIsAdmin().equals("Yes")) {
					rdbtn_ModifyUser_Admin_Yes.setSelected(rootPaneCheckingEnabled);
				} else {
					rdbtn_ModifyUser_Admin_No.setSelected(rootPaneCheckingEnabled);
				}

				lbl_Show_User_Id.setText(String.valueOf(table_User_Admin.getValueAt(table_User_Admin.getSelectedRow(), 0)));
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_ModifyUser);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btn_Modify_produitRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				ProduitReferenceDao produitReferenceDaoImpl = daoFactory.getProduitRefDaoImpl();
				ProduitReference produitReference = produitReferenceDaoImpl.get(Long.parseLong(String.valueOf(table_ProduitRef_Admin.getValueAt(table_ProduitRef_Admin.getSelectedRow(), 0))));
				txt_ModifierProduitRef_Nom.setText(produitReference.getNom());
				txt_ModifierProduitRef_Dlc.setText(String.valueOf(produitReference.getDlcHeure()));

				int categorie = 0;
				switch (produitReference.getCategorie()) {
				case "Viande":
					categorie = 0;
					break;

				case "Poisson":
					categorie = 1;
					break;

				case "Laitier":
					categorie = 2;
					break;

				case "Oeufs":
					categorie = 3;
					break;

				case "Légumes":
					categorie = 4;
					break;

				case "Fruits":
					categorie = 5;
					break;

				case "Boisson":
					categorie = 6;
					break;


				}
				combo_Admin_ModifierProduitRef_Categorie.setSelectedIndex(categorie);
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Modifier_ProduitRef);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});

		btn_Modify_Frigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DAOFactory daoFactory = DAOFactory.getInstance();
				FrigoDao frigoDaoImpl = daoFactory.getFrigoDaoImpl();
				Frigo frigo = frigoDaoImpl.get(Long.parseLong(String.valueOf(table_Frigo_Admin.getValueAt(table_Frigo_Admin.getSelectedRow(), 0))));

				txt_ModifyFrigo_Nom.setText(frigo.getNom());;
				txt_ModifyFrigo_Emplacement.setText(frigo.getEmplacement());
				txt_ModifyFrigo_TempMatin.setText(String.valueOf(frigo.getTempMatin()));;
				txt_ModifyFrigo_TempAprem.setText(String.valueOf(frigo.getTempAprem()));;

				lbl_Show_Frigo_Id.setText(String.valueOf(table_Frigo_Admin.getValueAt(table_Frigo_Admin.getSelectedRow(), 0)));
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_ModifyFrigo);
				layeredPane.repaint();
				layeredPane.revalidate();


			}
		});

		btnAdmin_ModifyUser_SaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				UtilisateurDao utilisateurDaoImpl = daoFactory.getUtilisateurDaoImpl();
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNom(txt_ModifyUser_Nom.getText());
				utilisateur.setPrenom(txt_ModifyUser_Prenom.getText());
				utilisateur.setEmail(txt_ModifyUser_Email.getText());
				utilisateur.setLogin(txt_ModifyUser_Login.getText());
				utilisateur.setMotDePasse(txt_ModifyUser_MotDePasse.getText());
				utilisateur.setId(Long.parseLong(String.valueOf(table_User_Admin.getValueAt(table_User_Admin.getSelectedRow(), 0))));

				if (rdbtn_ModifyUser_Admin_Yes.isSelected()) {
					utilisateur.setIsAdmin("Yes");
				} else {
					utilisateur.setIsAdmin("No");
				}
				utilisateurDaoImpl.update(utilisateur);
				HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
				String statut;
				if (utilisateur.getIsAdmin().equals("Yes")) {
					statut = "Admin";
				} else {
					statut ="Utilisateur";
				}
				historiqueDaoImpl.save(historique, "Modifier "+statut+" : "+utilisateur.getPrenom()+" "+utilisateur.getNom()+"       Id: "+utilisateur.getId());

				btnModifyUser.setEnabled(false);
				btnDeleteUser.setEnabled(false);
				refreshTableUtilisateur(modelUtilisateur);

				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Gestion_User);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnModifier_ProduitRef_SaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				ProduitReferenceDao produitReferenceDaoImpl = daoFactory.getProduitRefDaoImpl();
				ProduitReference produitReference = new ProduitReference();
				produitReference.setNom(txt_ModifierProduitRef_Nom.getText());
				produitReference.setCategorie(String.valueOf(combo_Admin_ModifierProduitRef_Categorie.getSelectedItem()));
				produitReference.setDlcHeure(Long.parseLong(txt_ModifierProduitRef_Dlc.getText()));
				produitReference.setId(Long.parseLong(String.valueOf(table_ProduitRef_Admin.getValueAt(table_ProduitRef_Admin.getSelectedRow(), 0))));

				produitReferenceDaoImpl.update(produitReference);
				HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
				historiqueDaoImpl.save(historique, "Modification Reference produit "+produitReference.getNom()+" catégorie : "+produitReference.getCategorie()+ "       Id: "+produitReference.getId());

				btn_Delete_ProduitRef.setEnabled(false);
				btn_Modify_produitRef.setEnabled(false);
				refreshTableProduitRef(modelProduitRef);
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Gestion_ProduitRef);
				layeredPane.repaint();
				layeredPane.revalidate();

			}
		});

		btnAdmin_ModifyFrigo_SaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				FrigoDao frigoDaoImpl = daoFactory.getFrigoDaoImpl();
				Frigo frigo = frigoDaoImpl.get(Long.parseLong(String.valueOf(table_Frigo_Admin.getValueAt(table_Frigo_Admin.getSelectedRow(), 0))));
				try {
					frigo.setNom(txt_ModifyFrigo_Nom.getText());
					frigo.setEmplacement(txt_ModifyFrigo_Emplacement.getText());
					frigo.setTempMatin(Double.valueOf(txt_ModifyFrigo_TempMatin.getText()));
					frigo.setTempAprem(Double.valueOf(txt_ModifyFrigo_TempAprem.getText()));

					frigoDaoImpl.update(frigo);
					HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
					historiqueDaoImpl.save(historique, "Modification frigo "+frigo.getNom()+"       Id: "+frigo.getId());

					refreshTableFrigo(modelFrigo);
					btn_ControlerTemp_Frigo.setEnabled(false);
					btn_Delete_Frigo.setEnabled(false);
					btn_Modify_Frigo.setEnabled(false);

					layeredPane.removeAll();
					layeredPane.add(panel_Admin_Gestion_Frigo);
					layeredPane.repaint();
					layeredPane.revalidate();
				} catch (Exception e1) {
					frigo.setTempMatin(0);
					frigo.setTempAprem(0);
					frigoDaoImpl.update(frigo);
					refreshTableFrigo(modelFrigo);
					layeredPane.removeAll();
					layeredPane.add(panel_Admin_Gestion_Frigo);
					layeredPane.repaint();
					layeredPane.revalidate();
				}
			}
		});

		btn_Admin_ModifierProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				ProduitDao produitDaoImpl = daoFactory.getProduitDaoImpl();
				Produit produit = produitDaoImpl.get(Long.parseLong(String.valueOf(table_Produit_Admin.getValueAt(table_Produit_Admin.getSelectedRow(), 0))));

				txtAdmin_ModifierProduit_DateEntree.setText(String.valueOf(table_Produit_Admin.getValueAt(table_Produit_Admin.getSelectedRow(), 5)));
				txtAdmin_ModifierProduit_DLC.setText(String.valueOf(table_Produit_Admin.getValueAt(table_Produit_Admin.getSelectedRow(), 6)));


				int rowLocation=-1;

				for (int i=0; i<table_Modifier_Produit.getRowCount(); i++) {
					if (Long.parseLong(String.valueOf(table_Modifier_Produit.getValueAt(i, 0)))==produit.getIdFrigoAssocier()) {
						rowLocation = i;
					}
				}
				table_Modifier_Produit.setRowSelectionInterval(rowLocation, rowLocation);


				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Modifier_Produits);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btn_Admin_ValiderModifProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				ProduitDao produitDaoImpl = daoFactory.getProduitDaoImpl();
				Produit produit = produitDaoImpl.get(Long.parseLong(String.valueOf(table_Produit_Admin.getValueAt(table_Produit_Admin.getSelectedRow(), 0))));

				try {
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					java.util.Date date = dateFormat.parse(txtAdmin_ModifierProduit_DateEntree.getText());

					Timestamp tsDateEntree = new Timestamp(date.getTime());
					tsDateEntree.setTime(date.getTime());
					produit.setDateHeureEntree(tsDateEntree);

					java.util.Date dateDlc = dateFormat.parse(txtAdmin_ModifierProduit_DLC.getText());

					Timestamp tsDateDlc = new Timestamp(dateDlc.getTime());
					tsDateDlc.setTime(dateDlc.getTime());
					produit.setDlc(tsDateDlc);


					produit.setIdFrigoAssocier(Long.parseLong(String.valueOf(table_Modifier_Produit.getValueAt(table_Modifier_Produit.getSelectedRow(), 0))));
					produitDaoImpl.update(produit);
					HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
					historiqueDaoImpl.save(historique, "Modification Produit "+produit.getNom()+"    Id : "+produit.getId());

					refreshTableProduit(modelProduit);
					btn_Admin_ModifierProduit.setEnabled(false);
					btn_Admin_SupprimerProduit.setEnabled(false);
					btn_Changer_Statut.setEnabled(false);
					layeredPane.removeAll();
					layeredPane.add(panel_Admin_Gestion_Produits);
					layeredPane.repaint();
					layeredPane.revalidate();
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Mauvais format de date : 'dd/MM/yyyy HH:mm:ss'" );
				}

			}
		});

		btn_ControlerTemp_Frigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				FrigoDao frigoDaoImpl = daoFactory.getFrigoDaoImpl();
				Frigo frigo = frigoDaoImpl.get(Long.parseLong(String.valueOf(table_Frigo_Admin.getValueAt(table_Frigo_Admin.getSelectedRow(), 0))));
				try {
					Calendar dateActuelle = Calendar.getInstance();
					String message= null;
					if (dateActuelle.get(Calendar.HOUR_OF_DAY)<13) {
						message = "du matin.";
					} else {
						message = "de l'après-midi.";
					}
					String tempString = JOptionPane.showInputDialog("Veuillez entrer la température " + message);
					double temp = Double.valueOf(tempString);

					if (dateActuelle.get(Calendar.HOUR_OF_DAY)<13) {
						frigo.setTempMatin(temp);
					} else {
						frigo.setTempAprem(temp);
					}

					String formatedDateActuelle = dateActuelle.get(Calendar.YEAR)+ "-" + (dateActuelle.get(Calendar.MONTH)+1) + "-" + dateActuelle.get(Calendar.DAY_OF_MONTH) + " " + dateActuelle.get(Calendar.HOUR_OF_DAY) + ":" + dateActuelle.get(Calendar.MINUTE) + ":" + dateActuelle.get(Calendar.SECOND);
					Timestamp TsDateActuelle = Timestamp.valueOf(formatedDateActuelle);
					frigo.setDateDernierePriseTemp(TsDateActuelle);
					frigoDaoImpl.update(frigo);
					HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
					historiqueDaoImpl.save(historique, "Prise température "+message+" Pour le frigo : "+frigo.getNom()+"     Id : "+frigo.getId());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas entré un nombre");
				}
				btn_Delete_Frigo.setEnabled(false);
				btn_Modify_Frigo.setEnabled(false);
				btn_ControlerTemp_Frigo.setEnabled(false);
				refreshTableFrigo(modelFrigo);
			}
		});

		btn_Changer_Statut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOFactory daoFactory = DAOFactory.getInstance();
				ProduitDao produitDaoImpl = daoFactory.getProduitDaoImpl();
				Produit produit = produitDaoImpl.get(Long.parseLong(String.valueOf(table_Produit_Admin.getValueAt(table_Produit_Admin.getSelectedRow(), 0))));

				produit.setStatut(String.valueOf(combo_Produit_Changer_Statut.getSelectedItem()));
				produitDaoImpl.update(produit);
				HistoriqueDaoImpl historiqueDaoImpl = new HistoriqueDaoImpl(daoFactory);
				historiqueDaoImpl.save(historique, "Changement Statut : "+produit.getStatut()+" pour le produit "+produit.getNom()+"    Id : "+produit.getId());
				refreshTableProduit(modelProduit);
				btn_Changer_Statut.setEnabled(false);
				btn_Admin_SupprimerProduit.setEnabled(false);
				btn_Admin_ModifierProduit.setEnabled(false);
			}
		});

		table_AddProduit_Frigo.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (table_AddProduit_Frigo.getSelectionModel().isSelectionEmpty()==false) {
					if (table_AddProduit_Ref.getSelectedColumn()!=-1) {
						btn_Admin_ValiderProduit.setEnabled(true);
					}
				}
			}
		});

		table_AddProduit_Ref.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (table_AddProduit_Ref.getSelectionModel().isSelectionEmpty()==false) {
					if (table_AddProduit_Frigo.getSelectedColumn()!=-1) {
						btn_Admin_ValiderProduit.setEnabled(true);
					}
				}
			}
		});

		table_Produit_Admin.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (table_Produit_Admin.getSelectionModel().isSelectionEmpty()==false) {
					if (lblAdmin_Or_User.getText().equals("Admin")||lblAdmin_Or_User.getText().equals("New label")) {
						btn_Admin_SupprimerProduit.setEnabled(true);
						btn_Admin_ModifierProduit.setEnabled(true);
					}
					btn_Changer_Statut.setEnabled(true);
				}
			}
		});

		table_User_Admin.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				btnDeleteUser.setEnabled(true);
				btnModifyUser.setEnabled(true);
			}
		});

		table_Frigo_Admin.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (lblAdmin_Or_User.getText().equals("Admin")||lblAdmin_Or_User.getText().equals("New label")) {
					btn_Modify_Frigo.setEnabled(true);
					btn_Delete_Frigo.setEnabled(true);
				}
				btn_ControlerTemp_Frigo.setEnabled(true);
			}
		});

		table_ProduitRef_Admin.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				btn_Delete_ProduitRef.setEnabled(true);
				btn_Modify_produitRef.setEnabled(true);
			}
		});

		btnCanceltoAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Gestion_User);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnCanceltoFrigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Gestion_Frigo);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnMenu_Principal_GoAdminGestionUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Gestion_User);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textConnect_Username.setText("");
				passwordField.setText("");
				layeredPane.removeAll();
				layeredPane.add(panel_Connexion);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnBack_To_MenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_Menu_Principal);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnBack_To_MenuPrincipal_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_Changer_Statut.setEnabled(false);
				btn_Admin_SupprimerProduit.setEnabled(false);
				btn_Admin_ModifierProduit.setEnabled(false);
				layeredPane.removeAll();
				layeredPane.add(panel_Menu_Principal);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnBack_To_MenuPrincipal_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_Modify_Frigo.setEnabled(false);
				btn_Delete_Frigo.setEnabled(false);
				textField_Admin_Emplacement_Frigo.setText("Emplacement");
				textField_Admin_Nom_Frigo.setText("Nom");
				layeredPane.removeAll();
				layeredPane.add(panel_Menu_Principal);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnMenu_Principal_GoAdminGestionFrigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Gestion_Frigo);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnMenu_Principal_GoAdminGestionProduits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar dateActuelleCalendar = Calendar.getInstance();
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH'h' mm'm'");
				java.util.Date dateActuelle = dateActuelleCalendar.getTime();
				lbl_DateEtHeure.setText(dateFormat.format(dateActuelle));
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Gestion_Produits);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btn_Admin_Go_AjouterProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_Admin_ValiderProduit.setEnabled(false);
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Ajouter_Produits);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnBackToGestionProduits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Gestion_Produits);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnBackToGestionProduits_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Gestion_Produits);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnBack_to_MenuPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_Menu_Principal);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btn_Go_GestionProduitRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Gestion_ProduitRef);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btn_back_to_GestionProduitRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_Admin_Gestion_ProduitRef);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnBack_To_MenuPrincipal_Historique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_Menu_Principal);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnMenu_Principal_GoHistorique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTableHistorique(modelHistorique);
				layeredPane.removeAll();
				layeredPane.add(panel_Historique);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
	}

	/////////////////////////
	///// Gestion des Tables
	/////////////////////////
	void refreshTableUtilisateur(DefaultTableModel modelUtilisateur) {
		Connection connexion;
		try { 
			int rowCount = modelUtilisateur.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				modelUtilisateur.removeRow(i);
			}

			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfa019_project?useSSL=false", "root", "");
			PreparedStatement pstm = connexion.prepareStatement("SELECT * FROM utilisateur ORDER BY id");
			ResultSet Rs = pstm.executeQuery();

			while(Rs.next()){
				modelUtilisateur.addRow(new Object[]{Rs.getInt(7), Rs.getString(2),Rs.getString(1),Rs.getString(3),Rs.getString(6)});
			} }catch (SQLException e) {
				e.printStackTrace();
			}
	}

	void refreshTableFrigo(DefaultTableModel modelFrigo) {
		Connection connexion;
		try {
			int rowCount = modelFrigo.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				modelFrigo.removeRow(i);
			}

			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfa019_project?useSSL=false", "root", "");
			PreparedStatement pstm = connexion.prepareStatement("SELECT * FROM frigo ORDER BY id");
			ResultSet Rs = pstm.executeQuery();

			while(Rs.next()){
				String formattedDateDernierePrise = null;
				if (Rs.getTimestamp(6)!=null) {
					formattedDateDernierePrise = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Rs.getTimestamp(6));
				}
				modelFrigo.addRow(new Object[]{Rs.getInt(1), Rs.getString(2), Rs.getString(3), Rs.getDouble(4), Rs.getDouble(5), formattedDateDernierePrise});
			} }catch (SQLException e) {
				e.printStackTrace();
			}

		int count=0;
		Calendar dateActuelleCalendar=Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		for (int i = 0; i < modelFrigo.getRowCount(); i++) {
			try {

				java.util.Date date = dateFormat.parse(String.valueOf(modelFrigo.getValueAt(i, 5)));
				Calendar dateCalendar= Calendar.getInstance();
				dateCalendar.setTime(date);
				if (dateCalendar.get(Calendar.DAY_OF_YEAR) <dateActuelleCalendar.get(Calendar.DAY_OF_YEAR)||dateCalendar.get(Calendar.YEAR) !=dateActuelleCalendar.get(Calendar.YEAR)||(dateActuelleCalendar.get(Calendar.HOUR_OF_DAY)>12&&dateCalendar.get(Calendar.HOUR_OF_DAY)<13)) {
					count++;
				}
			} catch (Exception e) {} 
			finally {
				lblFrigoErreur.setText(String.valueOf(count));
			}
		}
		table_Frigo_Admin.setDefaultRenderer(Object.class, rendererFrigo);
	}

	void refreshTableProduit(DefaultTableModel modelProduit) {
		Connection connexion;
		try {
			int rowCount = modelProduit.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				modelProduit.removeRow(i);
			}

			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfa019_project?useSSL=false", "root", "");
			PreparedStatement pstm = connexion.prepareStatement("SELECT * FROM produit ORDER BY id");
			ResultSet Rs = pstm.executeQuery();

			while(Rs.next()){
				String formattedDlc = null;
				String formattedDateEntreeStringProduit = null;
				if (Rs.getTimestamp(6)!=null) {
					formattedDlc = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Rs.getTimestamp(6));
				}
				if (Rs.getTimestamp(7)!=null) {
					formattedDateEntreeStringProduit = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Rs.getTimestamp(7));
				}
				String statut= Rs.getString(4);
				Calendar dateActuelle = Calendar.getInstance();
				if (dateActuelle.getTimeInMillis()>=Rs.getTimestamp(6).getTime()) {
					if (!Rs.getString(4).equals("Retiré")&&!Rs.getString(4).equals("Consomé"))
						statut = "A contrôler";
				}

				modelProduit.addRow(new Object[]{Rs.getInt(1), Rs.getString(2), Rs.getString(3), statut, Rs.getInt(5), formattedDateEntreeStringProduit, formattedDlc});
			} }catch (SQLException e) {
				e.printStackTrace();
			}

		table_Produit_Admin.setDefaultRenderer(Object.class, rendererProduit);
		int countProduitAControler = 0;
		for (int i = 0; i < modelProduit.getRowCount(); i++) {
			if (modelProduit.getValueAt(i, 3).equals("A contrôler")) {
				countProduitAControler++;
			}
		}
		lblProduitErreur.setText(String.valueOf(countProduitAControler));
	}

	void refreshTableProduitRef(DefaultTableModel modelProduitRef) {
		Connection connexion;
		try {
			int rowCount = modelProduitRef.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				modelProduitRef.removeRow(i);
			}

			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfa019_project?useSSL=false", "root", "");
			PreparedStatement pstm = connexion.prepareStatement("SELECT * FROM produitreference ORDER BY id");
			ResultSet Rs = pstm.executeQuery();

			while(Rs.next()){
				modelProduitRef.addRow(new Object[]{Rs.getInt(1), Rs.getString(2), Rs.getString(3), Rs.getInt(4)});
			} }catch (SQLException e) {
				e.printStackTrace();
			}
	}
	void refreshTableHistorique(DefaultTableModel modelHistorique) {
		Connection connexion;
		try {
			int rowCount = modelHistorique.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				modelHistorique.removeRow(i);
			}
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfa019_project?useSSL=false", "root", "");
			PreparedStatement pstm = connexion.prepareStatement("SELECT * FROM historique ORDER BY date");
			ResultSet Rs = pstm.executeQuery();

			while(Rs.next()){
				String formattedDlc = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Rs.getTimestamp(3));
				modelHistorique.addRow(new Object[]{Rs.getString(1), Rs.getString(2), formattedDlc});
			} }catch (SQLException e) {
				e.printStackTrace();
			}
	}
}