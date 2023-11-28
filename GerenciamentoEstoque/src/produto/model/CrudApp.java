package produto.model;
import javax.swing.ImageIcon;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import produto.Produto;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.event.MenuKeyListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.MenuKeyEvent;

public class CrudApp extends JFrame {
    private DefaultTableModel tableModel;
    private List<Produto> produtos;
    private JTable table;
    private JTextField nomeProduto;
    private JTextField qtdEstoque;
    private JTextField precoProdutoField;
    private JTextField marcaProduto;
    private JTextField observacaoProduto;

    public CrudApp() {
    	setBackground(Color.BLACK);
    	setAlwaysOnTop(true);
    	setFont(new Font("Dialog", Font.BOLD, 12));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("IceCream Digital");

        ProdutoDAO produtoDAO = new ProdutoDAO();

        List<Produto> produtos = produtoDAO.listarProdutos();
        JPanel inputPanel2 = new JPanel();
        inputPanel2.setLayout(new FlowLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Código Produto");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Marca do Produto");
        tableModel.addColumn("Preço");
        tableModel.addColumn("Quantidade Estoque");
        tableModel.addColumn("Observação");

        setSize(1024, 720);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        ImageIcon img = new ImageIcon();
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBackground(new Color(11, 201, 234));
        layeredPane.setBounds(10, 11, 988, 61);
        getContentPane().add(layeredPane);
        
        JLabel lblNewLabel_1 = new JLabel("");     
        lblNewLabel_1.setIcon(new ImageIcon(CrudApp.class.getResource("/images/icecream65x65.png")));
        lblNewLabel_1.setBounds(10, 0, 72, 65);
        layeredPane.add(lblNewLabel_1);
        
        JButton btnNewButton = new JButton("Usuários");
        btnNewButton.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 12));
        btnNewButton.setBackground(new Color(60, 191, 255));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		new Tela_Usuario();
        	}
        });
        btnNewButton.setBounds(642, 11, 181, 29);
        layeredPane.add(btnNewButton);
        
        JButton btnRelatorio = new JButton("Relatórios");
        btnRelatorio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		new Tela_Relatorios();
        		
        	}
        });
        btnRelatorio.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 12));
        btnRelatorio.setBackground(new Color(60, 191, 255));
        btnRelatorio.setBounds(189, 11, 181, 29);
        layeredPane.add(btnRelatorio);
        
        JLayeredPane layeredPane_1 = new JLayeredPane();
        layeredPane_1.setBounds(10, 75, 732, 595);
        getContentPane().add(layeredPane_1);
        layeredPane_1.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 712, 535);
        layeredPane_1.add(scrollPane);

        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        
        Button button = new Button("Editar");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		editarProduto();
        	}
        });
        button.setForeground(new Color(0, 0, 0));
        button.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 11));
        button.setBounds(249, 563, 70, 22);
        layeredPane_1.add(button);

        Button button_1 = new Button("Excluir");
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		excluirProduto();
        	}
        });
        button_1.setForeground(new Color(0, 0, 0));
        button_1.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 11));
        button_1.setBounds(414, 563, 70, 22);
        layeredPane_1.add(button_1);
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(99);
        table.getColumnModel().getColumn(2).setPreferredWidth(126);
        table.getColumnModel().getColumn(3).setPreferredWidth(55);
        table.getColumnModel().getColumn(4).setPreferredWidth(199);

        JLayeredPane layeredPane_2 = new JLayeredPane();
        layeredPane_2.setBounds(752, 77, 246, 593);
        getContentPane().add(layeredPane_2);

        nomeProduto = new JTextField();
        nomeProduto.setBounds(10, 219, 226, 20);
        layeredPane_2.add(nomeProduto);
        nomeProduto.setColumns(10);

        qtdEstoque = new JTextField();
        qtdEstoque.setColumns(10);
        qtdEstoque.setBounds(10, 354, 226, 20);
        layeredPane_2.add(qtdEstoque);

        precoProdutoField = new JTextField();
        precoProdutoField.setColumns(10);
        precoProdutoField.setBounds(10, 309, 226, 20);
        layeredPane_2.add(precoProdutoField);

        marcaProduto = new JTextField();
        marcaProduto.setColumns(10);
        marcaProduto.setBounds(10, 267, 226, 20);
        layeredPane_2.add(marcaProduto);

        observacaoProduto = new JTextField();
        observacaoProduto.setColumns(10);
        observacaoProduto.setBounds(10, 409, 226, 146);
        layeredPane_2.add(observacaoProduto);

        JLabel lblNewLabel = new JLabel("Descrição/ Observação:");
        lblNewLabel.setBounds(10, 384, 148, 14);
        layeredPane_2.add(lblNewLabel);

        JLabel lblCadastro = new JLabel("CADASTRO");
        lblCadastro.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 14));
        lblCadastro.setBounds(77, 11, 79, 14);
        layeredPane_2.add(lblCadastro);

        JLabel lblNomeDoProduto = new JLabel("Nome do Produto:");
        lblNomeDoProduto.setBounds(10, 202, 148, 14);
        layeredPane_2.add(lblNomeDoProduto);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(10, 250, 72, 14);
        layeredPane_2.add(lblMarca);

        JLabel lblQuantidadeEmEstoque = new JLabel("Quantidade em Estoque:");
        lblQuantidadeEmEstoque.setBounds(10, 340, 226, 14);
        layeredPane_2.add(lblQuantidadeEmEstoque);

        JLabel lblPreo = new JLabel("Preço:");
        lblPreo.setBounds(10, 293, 119, 14);
        layeredPane_2.add(lblPreo);

        JLabel lblImagemDoProduto = new JLabel("Faça o upload da imagem do produto:");
        lblImagemDoProduto.setBounds(35, 36, 186, 20);
        layeredPane_2.add(lblImagemDoProduto);
        
        Button btnAdicionarProduto = new Button("Adicionar");
        btnAdicionarProduto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		adicionarProduto();
        	}
        });
        btnAdicionarProduto.setForeground(Color.BLACK);
        btnAdicionarProduto.setFont(new Font("Franklin Gothic Medium", Font.PLAIN, 11));
        btnAdicionarProduto.setBounds(97, 561, 70, 22);
        layeredPane_2.add(btnAdicionarProduto);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent e) {
        		setVisible(false);
        		uploadImage();
        		setVisible(true);
        	}
        });
        lblNewLabel_2.setIcon(new ImageIcon(CrudApp.class.getResource("/images/camera__1_-removebg-preview.png")));
        lblNewLabel_2.setBounds(37, 55, 172, 136);
        layeredPane_2.add(lblNewLabel_2);
        getContentPane().setLayout(null);
        setVisible(true);

        for (Produto produto : produtos) {
            Object[] row = {produto.getCodigoProduto(), produto.getNomeProduto(), produto.getModeloProduto(), produto.getPrecoProduto(),  produto.getQuantidadeEstoque(), produto.getDescricaoProduto()};
            tableModel.addRow(row);
        }



    }

    private void atualizaTabela() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listarProdutos();
        for (Produto produto : produtos) {
            Object[] row = {produto.getCodigoProduto(), produto.getNomeProduto(), produto.getModeloProduto(), produto.getPrecoProduto(),  produto.getQuantidadeEstoque(), produto.getDescricaoProduto()};
            tableModel.addRow(row);
        }
    }

    private void limparTabela() {
    	int numRows = table.getRowCount();
    	for (int i = numRows - 1; i >= 0; i--) {
    		tableModel.removeRow(i);
    	}
    }
    
    private void adicionarProduto() { 
        
        String nome = nomeProduto.getText();
        Integer qtdeEstoque = Integer.parseInt(qtdEstoque.getText());
        String modeloProduto = marcaProduto.getText();
        double precoProduto = Double.parseDouble(precoProdutoField.getText()); 
        String descricaoProduto = observacaoProduto.getText();
        
        if (!nome.isEmpty()) {
            Produto produto = new Produto(nome, qtdeEstoque, modeloProduto, precoProduto, descricaoProduto);
            ProdutoDAO produtoDAO = new ProdutoDAO();
            
            produtoDAO.adicionarProduto(produto);
            nomeProduto.setText("");
            this.limparTabela();
            this.atualizaTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Digite um nome de produto válido.");
        }
    }
    
    private void editarProduto() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            List<Produto> produtos = produtoDAO.listarProdutos();
            Produto produto = produtos.get(selectedRow);

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(3, 3, 3, 3); 

            JTextField nomeField = new JTextField(produto.getNomeProduto());
            JTextField modeloField = new JTextField(produto.getModeloProduto());
            JTextField precoField = new JTextField(Double.toString(produto.getPrecoProduto()));
            JTextField quantidadeField = new JTextField(Integer.toString(produto.getQuantidadeEstoque()));
            JTextField descricaoField = new JTextField(produto.getDescricaoProduto());
            
            nomeField.setPreferredSize(new Dimension(200, nomeField.getPreferredSize().height));
            modeloField.setPreferredSize(new Dimension(200, modeloField.getPreferredSize().height));
            precoField.setPreferredSize(new Dimension(200, precoField.getPreferredSize().height));
            quantidadeField.setPreferredSize(new Dimension(200, quantidadeField.getPreferredSize().height));
            descricaoField.setPreferredSize(new Dimension(200, descricaoField.getPreferredSize().height));


            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("Novo nome do Produto:"), gbc);
            gbc.gridx = 1;
            panel.add(nomeField, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(new JLabel("Nova Marca:"), gbc);
            gbc.gridx = 1;
            panel.add(modeloField, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(new JLabel("Novo Preço:"), gbc);
            gbc.gridx = 1;
            panel.add(precoField, gbc);
            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(new JLabel("Nova Quantidade:"), gbc);
            gbc.gridx = 1;
            panel.add(quantidadeField, gbc);
            gbc.gridx = 0;
            gbc.gridy = 4;
            panel.add(new JLabel("Nova Descrição:"), gbc);
            gbc.gridx = 1;
            panel.add(descricaoField, gbc);

            int result = JOptionPane.showConfirmDialog(this, panel, "Editar Produto",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
            	produto.setNomeProduto(nomeField.getText());
            	produto.setModeloProduto(modeloField.getText());
            	produto.setPrecoProduto(Double.parseDouble(precoField.getText()));
            	produto.setQuantidadeEstoque(Integer.parseInt(quantidadeField.getText()));
            	produto.setDescricaoProduto(descricaoField.getText());
            	produtoDAO.atualizarProduto(produto);
                this.limparTabela();
                this.atualizaTabela();
	        }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para editar.");
        }    
    }

    
    private void excluirProduto() {
        int selectedRow = table.getSelectedRow();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.listarProdutos();          

        if (selectedRow != -1) {
        	Produto produto = produtos.get(selectedRow);
            
            produtoDAO.removerProduto(produto.getCodigoProduto());
            this.limparTabela();
            this.atualizaTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.");
        }
    }
    
    private void abrirTelaUsuario() {
    	
    }
    
    public static void uploadImage() {    
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha uma imagem");
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            ProdutoDAO produtoDAO = new ProdutoDAO();
                       
            try (FileInputStream fis = new FileInputStream(selectedFile)) {
            	
            	produtoDAO.insereImagem(fis, selectedFile.length());
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Erro durante o upload da imagem.");
            }
        } else {
            System.out.println("Operação de upload de imagem cancelada pelo usuário.");
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
			public void run() {
                new CrudApp();
            }
        });
    }
}
