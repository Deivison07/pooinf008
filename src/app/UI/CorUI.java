package app.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

import app.UI.DTO.ItemDeAnaliseDTO;
import app.servico.Biblioteca;
import app.servico.interfaces.IBiblioteca;

@SuppressWarnings("serial")
public class CorUI extends JPanel implements ActionListener {

    private IBiblioteca logica;

	private JFrame frmAnliseDeMapa;
	private JTextField textFieldEndereco;
	JTextPane txtPaneElemento = new JTextPane();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CorUI window = new CorUI();
					window.frmAnliseDeMapa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public CorUI() throws SQLException {
		this.logica = new Biblioteca();
        initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Configurations 
		frmAnliseDeMapa = new JFrame();
		frmAnliseDeMapa.setResizable(false);
		frmAnliseDeMapa.setTitle("An\u00E1lise de mapa - INF008");
		frmAnliseDeMapa.setBounds(100, 100, 800, 405);
		frmAnliseDeMapa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnliseDeMapa.getContentPane().setLayout(null);
		

		//###Label para escolha do arquivo
		JLabel lblNewLabel = new JLabel("Informe o caminho do arquivo para avalia\u00E7ao:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 36, 268, 14);
		//###
		frmAnliseDeMapa.getContentPane().add(lblNewLabel);
		
		//###Campo que exibe o caminho do arquivo
		textFieldEndereco = new JTextField();
		textFieldEndereco.setBounds(288, 33, 387, 20);
		textFieldEndereco.setEditable(false);
		//###
		frmAnliseDeMapa.getContentPane().add(textFieldEndereco);
		textFieldEndereco.setColumns(10);
		
		//Botao para abertura da janela para escolha do arquivo
		JButton btnBrowser = new JButton("Browser...");
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		//evento do clique do botao 
		//Abre janela do windows para selecao de arquivo e retorna endereco do arquivo para o campo de texto.
		btnBrowser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(e);
				 int returnVal = fc.showOpenDialog(CorUI.this);
				    if (returnVal == JFileChooser.APPROVE_OPTION) {
				        File file = fc.getSelectedFile();
						textFieldEndereco.setText(file.getPath());
				} else {
					System.out.println("Erro na abertura");
				}
			}
		});
		btnBrowser.setBounds(685, 32, 89, 23);
		//###
		frmAnliseDeMapa.getContentPane().add(btnBrowser);
		
		//###Label para exibicao dos elementos no BD
		JLabel lblNewLabel_1 = new JLabel("Selecione o tipo de elemento:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(128, 75, 150, 14);
		//###
		frmAnliseDeMapa.getContentPane().add(lblNewLabel_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(288, 71, 387, 22);
		loadCombo(comboBox);
		
		//###
		frmAnliseDeMapa.getContentPane().add(comboBox);
		
		//###Botao para analisar o arquivo conforme o elemento escolhido
		JButton btnAnalisar = new JButton("Analisar");
		
		btnAnalisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String simbolo = (String) comboBox.getSelectedItem();
				try {
					Collection<ItemDeAnaliseDTO> dados = logica
							.analisarImagem(textFieldEndereco.getText(), simbolo);
					
					//Inserir a String de Elemento por porcentagem
					loadAnalisePane(txtPaneElemento, dados);
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					System.out.println("Erro na analise: " + e1.getMessage());
				}
			}
		});
		
		btnAnalisar.setBounds(685, 119, 89, 23);
		frmAnliseDeMapa.getContentPane().add(btnAnalisar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 153, 764, 202);
		frmAnliseDeMapa.getContentPane().add(scrollPane);
		
		//Pane para exibir os elementos
		scrollPane.setViewportView(txtPaneElemento);
		txtPaneElemento.setEditable(false);
	}
	
	private void loadCombo(JComboBox<String> comboBox){
		try {
			Collection<String> simbolos = this.logica.obterSimbolosDasCores();
			for (String simbolo : simbolos) {
				comboBox.addItem(simbolo);
			}
		} catch (SQLException e) {
		}
	}

	private void loadAnalisePane(JTextPane txtPaneElemento, Collection<ItemDeAnaliseDTO> dados){
		String text = "";
		for ( ItemDeAnaliseDTO elemento : dados){
			text = elemento.getDescricao + ": " + Double.toString(elemento.getPercentual)+ " . \n";
			txtPaneElemento.set(text);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
