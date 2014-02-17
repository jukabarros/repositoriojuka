package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

import servidor.TCPJogador;
import servidor.TCPServidorJogo;

import entidade.Jogador;

public class TelaCampo extends JFrame{
	
	private static final long serialVersionUID = 7127021364137565637L;

	private Jogador jogador;

	private JPanel contentPane;
	
	private JButton btn00;
	private JButton btn01;
	private JButton btn02;
	private JButton btn03;
	private JButton btn04;
	private JButton btn05;
	private JButton btn06;
	private JButton btn07;
	private JButton btn08;
	private JButton btn09;
	private JButton btn10;
	private JButton btn11;
	private JButton btn12;
	private JButton btn13;
	private JButton btn14;
	private JButton btn15;
	private JButton btn16;
	private JButton btn17;
	private JButton btn18;
	private JButton btn19;
	private JButton btn20;
	private JButton btn21;
	private JButton btn22;
	private JButton btn23;
	private JButton btn24;
	private JButton btn25;
	private JButton btn26;
	private JButton btn27;
	private JButton btn28;
	private JButton btn29;
	private JButton btn39;
	private JButton btn38;
	private JButton btn37;
	private JButton btn36;
	private JButton btn35;
	private JButton btn34;
	private JButton btn33;
	private JButton btn32;
	private JButton btn31;
	private JButton btn30;
	private JButton btn49;
	private JButton btn48;
	private JButton btn47;
	private JButton btn46;
	private JButton btn45;
	private JButton btn44;
	private JButton btn43;
	private JButton btn42;
	private JButton btn41;
	private JButton btn40;
	private JButton btn59;
	private JButton btn58;
	private JButton btn57;
	private JButton btn56;
	private JButton btn55;
	private JButton btn54;
	private JButton btn53;
	private JButton btn52;
	private JButton btn51;
	private JButton btn50;
	private JButton btn69;
	private JButton btn68;
	private JButton btn67;
	private JButton btn66;
	private JButton btn65;
	private JButton btn64;
	private JButton btn63;
	private JButton btn62;
	private JButton btn61;
	private JButton btn60;
	private JButton btn79;
	private JButton btn78;
	private JButton btn77;
	private JButton btn76;
	private JButton btn75;
	private JButton btn74;
	private JButton btn73;
	private JButton btn72;
	private JButton btn71;
	private JButton btn70;
	private JButton btn89;
	private JButton btn88;
	private JButton btn87;
	private JButton btn86;
	private JButton btn85;
	private JButton btn84;
	private JButton btn83;
	private JButton btn82;
	private JButton btn81;
	private JButton btn80;
	private JButton btn99;
	private JButton btn98;
	private JButton btn97;
	private JButton btn96;
	private JButton btn95;
	private JButton btn94;
	private JButton btn93;
	private JButton btn92;
	private JButton btn91;
	private JButton btn90;
	private JTextField textLogin;
	private JPasswordField passwordSenha;
	private JButton btnConectar;
	
	private JLabel lblpontos1;
	
	private String comandoTCP;
	private TCPServidorJogo servidor;
	
	private String mensagemLabel; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCampo frame = new TelaCampo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCampo() throws Exception{
		//jogador = new Jogador("Juka", "1", 0); // Colocar o jogador Online
		
		mensagemLabel = "Aguardando o login...";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 734);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 580, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 458, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		JButton btnHabilitar = new JButton("habilitar");
		btnHabilitar.setVisible(false);
		btnHabilitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//habilitarBotoes();
			}
		});
		
		JButton btnDesabilitar = new JButton("Desabilitar");
		btnDesabilitar.setVisible(false);
		btnDesabilitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//desabilitarBotoes();
			}
		});
		
		textLogin = new JTextField();
		textLogin.setColumns(10);
		
			
		JLabel lbllogin = new JLabel("Login");
		
		JLabel lblSenha = new JLabel("Senha");
		
		passwordSenha = new JPasswordField();
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				comandoTCP = "login "+textLogin.getText()+" "+passwordSenha.getText()+"";
				String respostaLogin = executarComandoTCP(comandoTCP); // Recebe a resposta do servidor
				String[] dividirStringRetorno = respostaLogin.split(" ");
				
				jogador = new Jogador(dividirStringRetorno[1], dividirStringRetorno[2], 0);
				if (dividirStringRetorno[0].equals("login OK")){
					mensagemLabel = imprimirMensagem("Bem Vindo ao Jogo");
				
				}
			}
		});
		
		
		JLabel lblJogador1 = new JLabel("Sua Pontuação");
		
		JLabel lblJogador2 = new JLabel("");
		
		JLabel lblJogador3 = new JLabel("");
		
		JLabel lblJogador4 = new JLabel("");
		
		lblpontos1 = new JLabel("0000");
		
		JLabel lblpontos2 = new JLabel("");
		
		JLabel lblpontos3 = new JLabel("");
		
		JLabel lblpontos4 = new JLabel("");
		
		System.out.println("MENSAGEM LABEL: "+mensagemLabel);
		
		JLabel lblmensagem = new JLabel(imprimirMensagem(mensagemLabel));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblJogador4)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblpontos4))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblJogador3)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblpontos3))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblJogador2)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblpontos2))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblJogador1)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblpontos1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(11)
							.addComponent(lbllogin)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblSenha)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnConectar)
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnDesabilitar)
								.addComponent(btnHabilitar)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(177)
							.addComponent(lblmensagem)))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbllogin)
						.addComponent(lblSenha)
						.addComponent(passwordSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConectar)
						.addComponent(btnHabilitar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDesabilitar)
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJogador1)
						.addComponent(lblpontos1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJogador2)
						.addComponent(lblpontos2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJogador3)
						.addComponent(lblpontos3)
						.addComponent(lblmensagem))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblJogador4)
						.addComponent(lblpontos4))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel_1.linkSize(SwingConstants.HORIZONTAL, new Component[] {textLogin, passwordSenha});
		panel_1.setLayout(gl_panel_1);
		
		btn10 = new JButton("");
		btn10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(1,0);
				btn10.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn11 = new JButton("");
		btn11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(1,1);
				btn11.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn12 = new JButton("");
		btn12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(1,2);
				btn12.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn13 = new JButton("");
		btn13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(1,3);
				btn13.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn14 = new JButton("");
		btn14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(1,4);
				btn14.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		btn15 = new JButton("");
		btn15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(1,5);
				btn15.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn16 = new JButton("");
		btn16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(1,6);
				btn16.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn17 = new JButton("");
		btn17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(1,7);
				btn17.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn18 = new JButton("");
		btn18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(1,8);
				btn18.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn19 = new JButton("");
		btn19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(1,9);
				btn19.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn20 = new JButton("");
		btn20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(2,0);
				btn20.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn21 = new JButton("");
		btn21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(2,1);
				btn21.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn22 = new JButton("");
		btn22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(2,2);
				btn22.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn23 = new JButton("");
		btn23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(2,3);
				btn23.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn24 = new JButton("");
		btn24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(2,4);
				btn24.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn25 = new JButton("");
		btn25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(2,5);
				btn25.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn26 = new JButton("");
		btn26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(2,6);
				btn26.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn27 = new JButton("");
		btn27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(2,7);
				btn27.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn28 = new JButton("");
		btn28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(2,8);
				btn28.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn29 = new JButton("");
		btn29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(2,9);
				btn29.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn39 = new JButton("");
		btn39.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(3,9);
				btn39.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn38 = new JButton("");
		btn38.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(3,8);
				btn38.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn37 = new JButton("");
		btn37.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(3,7);
				btn37.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn36 = new JButton("");
		btn36.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(3,6);
				btn36.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn35 = new JButton("");
		btn35.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(3,5);
				btn35.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn34 = new JButton("");
		btn34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(3,4);
				btn34.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn33 = new JButton("");
		btn33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(3,3);
				btn33.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn32 = new JButton("");
		btn32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(3,2);
				btn32.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn31 = new JButton("");
		btn31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(3,1);
				btn31.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn30 = new JButton("");
		btn30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(3,0);
				btn30.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn49 = new JButton("");
		btn49.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(4,9);
				btn49.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn48 = new JButton("");
		btn48.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(4,8);
				btn48.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn47 = new JButton("");
		btn47.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(4,7);
				btn47.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn46 = new JButton("");
		btn46.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(4,6);
				btn46.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn45 = new JButton("");
		btn45.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(4,5);
				btn45.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn44 = new JButton("");
		btn44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(4,4);
				btn44.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn43 = new JButton("");
		btn43.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(4,3);
				btn43.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn42 = new JButton("");
		btn42.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(4,2);
				btn42.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn41 = new JButton("");
		btn41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(4,1);
				btn41.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn40 = new JButton("");
		btn40.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(4,0);
				btn40.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn59 = new JButton("");
		btn59.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(5,9);
				btn59.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn58 = new JButton("");
		btn58.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(5,8);
				btn58.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn57 = new JButton("");
		btn57.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(5,7);
				btn57.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn56 = new JButton("");
		btn56.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(5,6);
				btn56.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn55 = new JButton("");
		btn55.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(5,5);
				btn55.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn54 = new JButton("");
		btn54.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(5,4);
				btn54.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn53 = new JButton("");
		btn53.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(5,3);
				btn53.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn52 = new JButton("");
		btn52.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(5,2);
				btn52.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn51 = new JButton("");
		btn51.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(5,1);
				btn51.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn50 = new JButton("");
		btn50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(5,0);
				btn50.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn69 = new JButton("");
		btn69.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(6,9);
				btn69.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn68 = new JButton("");
		btn68.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(6,8);
				btn68.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn67 = new JButton("");
		btn67.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(6,7);
				btn67.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn66 = new JButton("");
		btn66.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(6,6);
				btn66.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn65 = new JButton("");
		btn65.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(6,5);
				btn65.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn64 = new JButton("");
		btn64.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(6,4);
				btn64.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn63 = new JButton("");
		btn63.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(6,3);
				btn63.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn62 = new JButton("");
		btn62.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(6,2);
				btn62.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn61 = new JButton("");
		btn61.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(6,1);
				btn61.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn60 = new JButton("");
		btn60.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(6,0);
				btn60.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn79 = new JButton("");
		btn79.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(7,9);
				btn79.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn78 = new JButton("");
		btn78.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(7,8);
				btn78.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn77 = new JButton("");
		btn77.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(7,7);
				btn77.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn76 = new JButton("");
		btn76.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(7,6);
				btn76.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn75 = new JButton("");
		btn75.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(7,5);
				btn75.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn74 = new JButton("");
		btn74.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(7,4);
				btn74.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn73 = new JButton("");
		btn73.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(7,3);
				btn73.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn72 = new JButton("");
		btn72.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(7,2);
				btn72.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn71 = new JButton("");
		btn71.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(7,1);
				btn71.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		btn70 = new JButton("");
		btn70.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(7,0);
				btn70.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn89 = new JButton("");
		btn89.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(8,9);
				btn89.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		btn88 = new JButton("");
		btn88.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(8,8);
				btn88.setIcon(new ImageIcon(verificarFigura(jogada)));
			}
		});
		btn87 = new JButton("");
		btn87.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(8,7);
				btn87.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn86 = new JButton("");
		btn86.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(8,6);
				btn86.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		btn85 = new JButton("");
		btn85.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(8,5);
				btn85.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		btn84 = new JButton("");
		btn84.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(8,4);
				btn84.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn83 = new JButton("");
		btn83.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(8,3);
				btn83.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn82 = new JButton("");
		btn82.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(8,2);
				btn82.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn81 = new JButton("");
		btn81.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(8,1);
				btn81.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn80 = new JButton("");
		btn80.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(8,0);
				btn80.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn99 = new JButton("");
		btn99.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(9,9);
				btn99.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn98 = new JButton("");
		btn98.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(9,8);
				btn98.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn97 = new JButton("");
		btn97.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(9,7);
				btn97.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn96 = new JButton("");
		btn96.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(9,6);
				btn96.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn95 = new JButton("");
		btn95.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(9,5);
				btn95.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn94 = new JButton("");
		btn94.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(9,4);
				btn94.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn93 = new JButton("");
		btn93.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(9,3);
				btn93.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn92 = new JButton("");
		btn92.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(9,2);
				btn92.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn91 = new JButton("");
		btn91.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(9,1);
				btn91.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn90 = new JButton("");
		btn90.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(9,0);
				btn90.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn00 = new JButton("");
		btn00.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(0,0);
				btn00.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn01 = new JButton("");
		btn01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(0,1);
				btn01.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		
		btn02 = new JButton("");
		btn02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(0,2);
				btn02.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		
		btn03 = new JButton("");
		btn03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(0,3);
				btn03.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn04 = new JButton("");
		btn04.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(0,4);
				btn04.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn05 = new JButton("");
		btn05.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(0,5);
				btn05.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn06 = new JButton("");
		btn06.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(0,6);
				btn06.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn07 = new JButton("");
		btn07.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(0,7);
				btn07.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn08 = new JButton("");
		btn08.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(0,8);
				btn08.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});
		
		btn09 = new JButton("");
		btn09.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jogada = Jogada(0,9);
				btn09.setIcon(new ImageIcon(verificarFigura(jogada)));
				verificarPontuacao(jogada);
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btn10, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn11, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn12, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn13, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn14, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn15, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn16, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn17, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn18, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn19, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btn20, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn21, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn22, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn23, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn24, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn25, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn26, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn27, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn28, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn29, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btn30, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn31, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn32, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn33, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn34, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn35, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn36, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn37, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn38, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn39, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btn40, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn41, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn42, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn43, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn44, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn45, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn46, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn47, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn48, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn49, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btn50, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn51, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn52, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn53, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn54, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn55, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn56, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn57, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn58, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn59, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btn60, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn61, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn62, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn63, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn64, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn65, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn66, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn67, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn68, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn69, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btn70, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn71, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn72, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn73, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn74, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn75, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn76, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn77, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn78, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn79, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btn80, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn81, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn82, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn83, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn84, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn85, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn86, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn87, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn88, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn89, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btn90, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn91, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn92, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn93, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn94, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn95, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn96, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn97, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn98, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn99, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btn00, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn01, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn02, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn03, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn04, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn05, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn06, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn07, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn08, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btn09, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(btn00, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(btn01, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(btn02, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(btn03, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(btn04, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(btn05, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(btn06, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(btn07, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(btn08, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(btn09, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn10, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn11, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn12, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn13, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn14, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn15, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn16, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn17, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn18, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn19, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn20, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn21, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn22, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn23, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn24, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn25, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn26, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn27, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn28, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn29, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn30, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn31, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn32, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn33, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn34, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn35, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn36, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn37, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn38, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn39, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn48, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn49, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn40, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn41, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn42, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn43, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn44, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn45, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn46, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn47, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn50, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn51, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn52, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn53, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn54, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn55, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn56, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn57, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn58, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn59, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn60, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn61, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn62, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn63, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn64, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn65, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn66, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn67, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn68, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn69, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn70, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn71, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn72, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn73, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn74, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn75, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn76, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn77, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn78, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn79, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn80, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn81, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn82, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn83, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn84, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn85, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn86, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn87, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn88, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn89, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn90, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn91, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn92, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn93, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn94, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn95, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn96, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn97, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn98, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn99, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.linkSize(SwingConstants.VERTICAL, new Component[] {btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn39, btn38, btn37, btn36, btn35, btn34, btn33, btn32, btn31, btn30, btn49, btn48, btn47, btn46, btn45, btn44, btn43, btn42, btn41, btn40, btn59, btn58, btn57, btn56, btn55, btn54, btn53, btn52, btn51, btn50, btn69, btn68, btn67, btn66, btn65, btn64, btn63, btn62, btn61, btn60, btn79, btn78, btn77, btn76, btn75, btn74, btn73, btn72, btn71, btn70, btn89, btn88, btn87, btn86, btn85, btn84, btn83, btn82, btn81, btn80, btn99, btn98, btn97, btn96, btn95, btn94, btn93, btn92, btn91, btn90});
		gl_panel.linkSize(SwingConstants.HORIZONTAL, new Component[] {btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20, btn21, btn22, btn23, btn24, btn25, btn26, btn27, btn28, btn29, btn39, btn38, btn37, btn36, btn35, btn34, btn33, btn32, btn31, btn30, btn49, btn48, btn47, btn46, btn45, btn44, btn43, btn42, btn41, btn40, btn59, btn58, btn57, btn56, btn55, btn54, btn53, btn52, btn51, btn50, btn69, btn68, btn67, btn66, btn65, btn64, btn63, btn62, btn61, btn60, btn79, btn78, btn77, btn76, btn75, btn74, btn73, btn72, btn71, btn70, btn89, btn88, btn87, btn86, btn85, btn84, btn83, btn82, btn81, btn80, btn99, btn98, btn97, btn96, btn95, btn94, btn93, btn92, btn91, btn90});
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		desabilitarBotoes();
	}
	
	public void habilitarBotoes(){
		btn10.setEnabled(true);
		btn11.setEnabled(true);
		btn12.setEnabled(true);
		btn13.setEnabled(true);
		btn14.setEnabled(true);
		btn15.setEnabled(true);
		btn16.setEnabled(true);
		btn17.setEnabled(true);
		btn18.setEnabled(true);
		btn19.setEnabled(true);
		btn20.setEnabled(true);
		btn21.setEnabled(true);
		btn22.setEnabled(true);
		btn23.setEnabled(true);
		btn24.setEnabled(true);
		btn25.setEnabled(true);
		btn26.setEnabled(true);
		btn27.setEnabled(true);
		btn28.setEnabled(true);
		btn29.setEnabled(true);
		btn39.setEnabled(true);
		btn38.setEnabled(true);
		btn37.setEnabled(true);
		btn36.setEnabled(true);
		btn35.setEnabled(true);
		btn34.setEnabled(true);
		btn33.setEnabled(true);
		btn32.setEnabled(true);
		btn31.setEnabled(true);
		btn30.setEnabled(true);
		btn49.setEnabled(true);
		btn48.setEnabled(true);
		btn47.setEnabled(true);
		btn46.setEnabled(true);
		btn45.setEnabled(true);
		btn44.setEnabled(true);
		btn43.setEnabled(true);
		btn42.setEnabled(true);
		btn41.setEnabled(true);
		btn40.setEnabled(true);
		btn59.setEnabled(true);
		btn58.setEnabled(true);
		btn57.setEnabled(true);
		btn56.setEnabled(true);
		btn55.setEnabled(true);
		btn54.setEnabled(true);
		btn53.setEnabled(true);
		btn52.setEnabled(true);
		btn51.setEnabled(true);
		btn50.setEnabled(true);
		btn69.setEnabled(true);
		btn68.setEnabled(true);
		btn67.setEnabled(true);
		btn66.setEnabled(true);
		btn65.setEnabled(true);
		btn64.setEnabled(true);
		btn63.setEnabled(true);
		btn62.setEnabled(true);
		btn61.setEnabled(true);
		btn60.setEnabled(true);
		btn79.setEnabled(true);
		btn78.setEnabled(true);
		btn77.setEnabled(true);
		btn76.setEnabled(true);
		btn75.setEnabled(true);
		btn74.setEnabled(true);
		btn73.setEnabled(true);
		btn72.setEnabled(true);
		btn71.setEnabled(true);
		btn70.setEnabled(true);
		btn89.setEnabled(true);
		btn88.setEnabled(true);
		btn87.setEnabled(true);
		btn86.setEnabled(true);
		btn85.setEnabled(true);
		btn84.setEnabled(true);
		btn83.setEnabled(true);
		btn82.setEnabled(true);
		btn81.setEnabled(true);
		btn80.setEnabled(true);
		btn99.setEnabled(true);
		btn98.setEnabled(true);
		btn97.setEnabled(true);
		btn96.setEnabled(true);
		btn95.setEnabled(true);
		btn94.setEnabled(true);
		btn93.setEnabled(true);
		btn92.setEnabled(true);
		btn91.setEnabled(true);
		btn90.setEnabled(true);
		btn00.setEnabled(true);
		btn01.setEnabled(true);
		btn02.setEnabled(true);
		btn03.setEnabled(true);
		btn04.setEnabled(true);
		btn05.setEnabled(true);
		btn06.setEnabled(true);
		btn07.setEnabled(true);
		btn08.setEnabled(true);
		btn09.setEnabled(true);
	}
	
	public void desabilitarBotoes(){
		btn10.setEnabled(false);
		btn11.setEnabled(false);
		btn12.setEnabled(false);
		btn13.setEnabled(false);
		btn14.setEnabled(false);
		btn15.setEnabled(false);
		btn16.setEnabled(false);
		btn17.setEnabled(false);
		btn18.setEnabled(false);
		btn19.setEnabled(false);
		btn20.setEnabled(false);
		btn21.setEnabled(false);
		btn22.setEnabled(false);
		btn23.setEnabled(false);
		btn24.setEnabled(false);
		btn25.setEnabled(false);
		btn26.setEnabled(false);
		btn27.setEnabled(false);
		btn28.setEnabled(false);
		btn29.setEnabled(false);
		btn39.setEnabled(false);
		btn38.setEnabled(false);
		btn37.setEnabled(false);
		btn36.setEnabled(false);
		btn35.setEnabled(false);
		btn34.setEnabled(false);
		btn33.setEnabled(false);
		btn32.setEnabled(false);
		btn31.setEnabled(false);
		btn30.setEnabled(false);
		btn49.setEnabled(false);
		btn48.setEnabled(false);
		btn47.setEnabled(false);
		btn46.setEnabled(false);
		btn45.setEnabled(false);
		btn44.setEnabled(false);
		btn43.setEnabled(false);
		btn42.setEnabled(false);
		btn41.setEnabled(false);
		btn40.setEnabled(false);
		btn59.setEnabled(false);
		btn58.setEnabled(false);
		btn57.setEnabled(false);
		btn56.setEnabled(false);
		btn55.setEnabled(false);
		btn54.setEnabled(false);
		btn53.setEnabled(false);
		btn52.setEnabled(false);
		btn51.setEnabled(false);
		btn50.setEnabled(false);
		btn69.setEnabled(false);
		btn68.setEnabled(false);
		btn67.setEnabled(false);
		btn66.setEnabled(false);
		btn65.setEnabled(false);
		btn64.setEnabled(false);
		btn63.setEnabled(false);
		btn62.setEnabled(false);
		btn61.setEnabled(false);
		btn60.setEnabled(false);
		btn79.setEnabled(false);
		btn78.setEnabled(false);
		btn77.setEnabled(false);
		btn76.setEnabled(false);
		btn75.setEnabled(false);
		btn74.setEnabled(false);
		btn73.setEnabled(false);
		btn72.setEnabled(false);
		btn71.setEnabled(false);
		btn70.setEnabled(false);
		btn89.setEnabled(false);
		btn88.setEnabled(false);
		btn87.setEnabled(false);
		btn86.setEnabled(false);
		btn85.setEnabled(false);
		btn84.setEnabled(false);
		btn83.setEnabled(false);
		btn82.setEnabled(false);
		btn81.setEnabled(false);
		btn80.setEnabled(false);
		btn99.setEnabled(false);
		btn98.setEnabled(false);
		btn97.setEnabled(false);
		btn96.setEnabled(false);
		btn95.setEnabled(false);
		btn94.setEnabled(false);
		btn93.setEnabled(false);
		btn92.setEnabled(false);
		btn91.setEnabled(false);
		btn90.setEnabled(false);
		btn00.setEnabled(false);
		btn01.setEnabled(false);
		btn02.setEnabled(false);
		btn03.setEnabled(false);
		btn04.setEnabled(false);
		btn05.setEnabled(false);
		btn06.setEnabled(false);
		btn07.setEnabled(false);
		btn08.setEnabled(false);
		btn09.setEnabled(false);
	}
	
	public String Jogada(int linha, int coluna){
		comandoTCP = "ver "+linha+" "+coluna+" "+jogador.getLogin();
		String resposta = executarComandoTCP(comandoTCP);
		String[] dividirStringRetorno = resposta.split(" "); // Quando o retorno tiver um num de palavras >1
		return dividirStringRetorno[1];
			
	}
	
	public String verificarFigura(String jogada){
		if(jogada.equals("bomba")||jogada.equals("bombaX")){
			return "icon/bomba.jpg";
		}else if(jogada.equals("ouro") || jogada.equals("ouroX")){
			return "icon/moeda.jpg";
		}else if(jogada.equals("bau") || jogada.equals("bauX")){
			return "icon/bau.jpg";
		}else{
			return "icon/nada.jpg";
		}
	}
	
	// FAZER O ATUALIZAR PONTUACAO!!
	public void verificarPontuacao(String jogada){
		if(jogada.equals("bomba")){
			jogador.setPontuacao(jogador.getPontuacao()-100);
		}else if(jogada.equals("ouro")){
			jogador.setPontuacao(jogador.getPontuacao()+100);;
		}else if(jogada.equals("bau")){
			jogador.setPontuacao(jogador.getPontuacao()+1000);
		}
		lblpontos1.setText(String.valueOf(jogador.getPontuacao()));
		comandoTCP = "atualizar_pontos "+jogador.getLogin()+" "+jogador.getPontuacao();
		executarComandoTCP(comandoTCP);
		if (jogada.equals("bau") || jogada.equals("bauX")){
			executarComandoTCP("fim_jogo");
		}
	}
	
	public void desabilitarLogin(){ // Desabilitando login depois da validacao
		textLogin.setEnabled(false);
		passwordSenha.setEnabled(false);
		btnConectar.setEnabled(false);
		
	}
	
	
	public String executarComandoTCP(String comandoTCP){
		TCPJogador tcp = new TCPJogador();
		tcp.conectar();
		String retorno = tcp.enviarComandoServidor(comandoTCP);
		String[] dividirStringRetorno = retorno.split(" ");
		if (dividirStringRetorno[0].equals("loginOK")){
			desabilitarLogin();
			habilitarBotoes();
			
		}
		else if (retorno.equals("fim_jogo")){
			desabilitarBotoes();
		}
		return retorno;
	}

	public String imprimirMensagem(String mensagem){
		mensagemLabel = mensagem;
		return mensagemLabel;
	}
	
	
	
}
