package produto.model;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import produto.Produto;
import usuario.Usuario;
import usuario.UsuarioDAO;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

public class Tela_Usuario {

	private JFrame frame;
	private JTextField emailField;
	private JTextField senhaField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Tela_Usuario();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela_Usuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("Entrei no initialize");
		frame = new JFrame();
		frame.setBounds(100, 100, 995, 647);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		List<Usuario> usuarios = usuarioDAO.listarUsuario();
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(11, 201, 234));
		layeredPane.setBounds(0, 11, 976, 61);
		frame.getContentPane().add(layeredPane);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Tela_Usuario.class.getResource("/images/icecream65x65.png")));
		lblNewLabel_1.setBounds(10, 0, 72, 65);
		layeredPane.add(lblNewLabel_1);
		
		JButton btnRelatorio = new JButton("Relatórios");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Tela_Relatorios();
			}
		});
		btnRelatorio.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 12));
		btnRelatorio.setBackground(new Color(60, 191, 255));
		btnRelatorio.setBounds(599, 11, 181, 29);
		layeredPane.add(btnRelatorio);
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new CrudApp();
			}
		});
		btnProdutos.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 12));
		btnProdutos.setBackground(new Color(60, 191, 255));
		btnProdutos.setBounds(184, 11, 181, 29);
		layeredPane.add(btnProdutos);
		
		JLabel lblNewLabel = new JLabel("Usuários");
		lblNewLabel.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 14));
		lblNewLabel.setBounds(171, 127, 63, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Crie um usuário:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(56, 208, 113, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setBounds(56, 277, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Senha:");
		lblNewLabel_3_1.setBounds(56, 355, 46, 14);
		frame.getContentPane().add(lblNewLabel_3_1);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(56, 311, 284, 26);
		frame.getContentPane().add(emailField);
		
		senhaField = new JTextField();
		senhaField.setColumns(10);
		senhaField.setBounds(56, 390, 284, 26);
		frame.getContentPane().add(senhaField);
		
		Button btnCancelarAcao = new Button("Cancelar");
		btnCancelarAcao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emailField.setText("");
				senhaField.setText("");
			}
		});
		btnCancelarAcao.setForeground(Color.BLACK);
		btnCancelarAcao.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 11));
		btnCancelarAcao.setBounds(229, 472, 70, 22);
		frame.getContentPane().add(btnCancelarAcao);
		
		Button btnCriarUsuario = new Button("Criar");
		btnCriarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criarUsuario();
				  JOptionPane.showMessageDialog(null,"Uuário criado com sucesso.");
			}

		});
		btnCriarUsuario.setForeground(Color.BLACK);
		btnCriarUsuario.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 11));
		btnCriarUsuario.setBounds(88, 472, 70, 22);
		frame.getContentPane().add(btnCriarUsuario);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(541, 175, 396, 397);
		frame.getContentPane().add(scrollPane);
		
		DefaultTableModel tableModel = new DefaultTableModel(); 
		
		tableModel.addColumn("Usuários Cadastrados");
		table = new JTable(tableModel);
			
		scrollPane.setViewportView(table);
		
		JLabel lblListaDeUsurios = new JLabel("Lista de Usuários");
		lblListaDeUsurios.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 14));
		lblListaDeUsurios.setBounds(680, 127, 121, 14);
		frame.getContentPane().add(lblListaDeUsurios);

		for(Usuario user : usuarios) {
			Object[] row = {user.getLoginUsuario()};
			tableModel.addRow(row);
		}
		
		
	}
	
	
	private void criarUsuario() {
        String email = emailField.getText();
        String password = senhaField.getText();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        if (usuarioDAO.checaSeUsuarioExiste(email, password)) {
        	 JOptionPane.showMessageDialog(null, "Usuario já cadastrado no sistema.");
            return;
        }
        
        Usuario usuario = new Usuario(email, password);
        usuarioDAO.adicionarUsuario(usuario);
               
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
