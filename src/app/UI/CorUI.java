import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collection;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

import app.DAO.CorDAO;
import app.DAO.interfaces.ICorDAO;

public class CorUI extends JFrame implements ActionListener{

    //private ContabilLogicaIF logica;
	//private Collection<String> nomeContas;

    private ICorDAO logica;
    private Collection<String> tipoElementos;

	private JFrame frmAnliseDeMapa;
	private JTextField textField;


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
		this.logica = new CorDAO();
		this.tipoElementos = this.logica.obterSimbolosDasCores();
        initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAnliseDeMapa = new JFrame();
		frmAnliseDeMapa.setTitle("An\u00E1lise de mapa - INF008");
		frmAnliseDeMapa.setBounds(100, 100, 800, 441);
		frmAnliseDeMapa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnliseDeMapa.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Informe o caminho do arquivo para avalia\u00E7ao:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 36, 268, 14);
		frmAnliseDeMapa.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(288, 33, 387, 20);
		frmAnliseDeMapa.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Browser...");
		btnNewButton.setBounds(685, 32, 89, 23);
		frmAnliseDeMapa.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Selecione o tipo de elemento:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(128, 75, 150, 14);
		frmAnliseDeMapa.getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(288, 71, 387, 22);
        this.loadCombo(comboBox);
		frmAnliseDeMapa.getContentPane().add(comboBox);
		
		JButton btnNewButton_1 = new JButton("Analisar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(685, 119, 89, 23);
		frmAnliseDeMapa.getContentPane().add(btnNewButton_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 153, 764, 180);
		frmAnliseDeMapa.getContentPane().add(textPane);
		
		JButton btnNewButton_2 = new JButton("Nova consulta");
		btnNewButton_2.setBounds(624, 368, 150, 23);
		frmAnliseDeMapa.getContentPane().add(btnNewButton_2);
	}

    private void loadCombo(JComboBox combo) {
		for(String tipoElemento : this.tipoElementos)
			combo.addItem(tipoElemento);
	}
	

    //TODO
    @Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			this.registrarFatoContabil();
			JOptionPane.showMessageDialog(this,
				    "Fato Registrado.");			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,
				    "Erro: " + e.getMessage(),
				    "Erro no Registro de Fato",
				    JOptionPane.ERROR_MESSAGE);			
		}
		
	}
}
