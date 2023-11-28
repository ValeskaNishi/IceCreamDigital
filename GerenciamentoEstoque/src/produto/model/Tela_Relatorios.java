package produto.model;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import produto.Produto;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.awt.event.ActionEvent;

public class Tela_Relatorios {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Tela_Relatorios();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela_Relatorios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1002, 644);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 11, 986, 61);
		layeredPane.setBackground(new Color(11, 201, 234));
		frame.getContentPane().add(layeredPane);
		
		JButton btnRelatorio = new JButton("Usuários");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Tela_Usuario();
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
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Tela_Relatorios.class.getResource("/images/icecream65x65.png")));
		lblNewLabel_1.setBounds(0, 0, 72, 65);
		layeredPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Escolha o Relatório que deseja e clique no formato de exportação desejada:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(281, 206, 437, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Relatórios");
		lblNewLabel_2.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 14));
		lblNewLabel_2.setBounds(446, 127, 95, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnBaixoEstoque = new JButton("Produtos com baixo estoque TXT");
		btnBaixoEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baixarProdutosBaixoEstoque();
			}


		});
		btnBaixoEstoque.setBounds(377, 279, 216, 23);
		frame.getContentPane().add(btnBaixoEstoque);
		
		JButton btnNewButton = new JButton("Todos os produtos TXT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baixarTodosProdutos();
			}
		});
		btnNewButton.setBounds(377, 351, 216, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	private void baixarProdutosBaixoEstoque() {
		String nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo desejado");
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		List<Produto> produtos = produtoDAO.listarProdutosComPoucoEstoque();
		try {
			File file = new File (nomeArquivo);
			FileWriter fileWriter = new FileWriter(file, false);
			fileWriter.write("codigoProduto;nomeProduto;quantidadeEstoque;modeloProduto;precoProduto;descricaoProduto");
			fileWriter.write("\n");
			for (Produto prod : produtos) {
				fileWriter.write(prod.getCodigoProduto().toString()+";");
				fileWriter.write(prod.getNomeProduto().toString()+";");
				fileWriter.write(prod.getCodigoProduto().toString()+";");
				fileWriter.write(prod.getQuantidadeEstoque().toString()+";");
				fileWriter.write(prod.getModeloProduto().toString()+";");
				fileWriter.write(prod.getPrecoProduto().toString()+";");
				fileWriter.write(prod.getDescricaoProduto().toString()+";");
				fileWriter.write("\n");
			}
			fileWriter.close();
			} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	private void baixarTodosProdutos() {
		String nomeArquivo = JOptionPane.showInputDialog("Digite o nome do arquivo desejado");
		ProdutoDAO produtoDAO = new ProdutoDAO();
		
		List<Produto> produtos = produtoDAO.listarProdutos();
		try {
			File file = new File (nomeArquivo);
			FileWriter fileWriter = new FileWriter(file, false);
			fileWriter.write("codigoProduto;nomeProduto;quantidadeEstoque;modeloProduto;precoProduto;descricaoProduto");
			fileWriter.write("\n");
			for (Produto prod : produtos) {
				fileWriter.write(prod.getCodigoProduto().toString()+";");
				fileWriter.write(prod.getNomeProduto().toString()+";");
				fileWriter.write(prod.getCodigoProduto().toString()+";");
				fileWriter.write(prod.getQuantidadeEstoque().toString()+";");
				fileWriter.write(prod.getModeloProduto().toString()+";");
				fileWriter.write(prod.getPrecoProduto().toString()+";");
				fileWriter.write(prod.getDescricaoProduto().toString()+";");
				fileWriter.write("\n");
			}
			fileWriter.close();
			} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	
	
}
