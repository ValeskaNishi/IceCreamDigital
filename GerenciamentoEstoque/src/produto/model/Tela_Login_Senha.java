package produto.model;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import usuario.Usuario;
import usuario.UsuarioDAO;

public class Tela_Login_Senha {

	private JFrame frame;
	private JTextField emailField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Tela_Login_Senha();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela_Login_Senha() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 128, 192));
		frame.setBounds(100, 100, 984, 639);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Tela_Login_Senha.class.getResource("/images/icecream.png")));
		lblNewLabel.setBounds(26, 74, 514, 448);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("IceCream Digital");
		lblNewLabel_1.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 25));
		lblNewLabel_1.setBounds(614, 148, 203, 63);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Login: ");
		lblNewLabel_2.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(576, 253, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Senha:");
		lblNewLabel_2_1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(576, 330, 46, 14);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		emailField = new JTextField();
		emailField.setBounds(576, 278, 284, 26);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
	
		passwordField = new JPasswordField();
		passwordField.setBounds(576, 350, 284, 26);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}

		});
		
		btnNewButton.setBackground(new Color(11, 201, 234));
		btnNewButton.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 12));
		btnNewButton.setBounds(635, 408, 159, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	private void login() {
		//VALIDAR SE O USUARIO EXISTE.
		//MSG DE ERRO CASO O USARIO NAO EXISTA
		//TROCAR DE TELA CASO EXISTA
		
		String email = emailField.getText();
        String password = passwordField.getText();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        if (!usuarioDAO.checaSeUsuarioExiste(email, password)) {
            JOptionPane.showMessageDialog(this.frame, "Usuário ou senha incorreta.");
            return;
        }
        
        this.frame.setVisible(false);
        
        new CrudApp();
		
	}
	
}
