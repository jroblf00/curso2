import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaResolver extends JFrame {

	int cantPalInter = 20;
	int tamPalabras = 16;
	
	ArrayList<JTextField[]> arrayPalInter = new ArrayList<JTextField[]>();
	
	JPanel panel = new JPanel();
	JPanel panelInfo = new JPanel();
	JPanel panelPalInter = new JPanel();
	JPanel panelPalInterBack = new JPanel();
	JPanel panelBotonesBl = new JPanel();
	JPanel panelBotones = new JPanel();
	JPanel panelText = new JPanel();
	JPanel panelDic = new JPanel();
	JPanel panelDicBack = new JPanel();
	JPanel panelLestraPalInter = new JPanel();
	
	BorderLayout bl = new BorderLayout();
	BorderLayout blPalInter = new BorderLayout();
	BorderLayout blPalInterBack = new BorderLayout();
	BorderLayout blPalDic = new BorderLayout();
	BorderLayout blPalDicBack = new BorderLayout();
	BorderLayout blBotones = new BorderLayout();
	GridLayout glPanelInfo = new GridLayout(1,2, 20, 20);
	GridLayout glBotones = new GridLayout(1, 3, 20, 20);
	GridLayout glText = new GridLayout(5,1);
	GridLayout glLestrasPalInter = new GridLayout(cantPalInter, tamPalabras);
	
	
	JMenuBar menuBar = new JMenuBar();
	JMenu menuInicio = new JMenu("Inicio");
	JMenuItem menuResolver = new JMenuItem("Resolver");
	JMenuItem menuAyuda = new JMenuItem("Solicitar Ayuda");
	JMenuItem menuCargarPas = new JMenuItem("Cargar Pasatiempos");
	JMenuItem menuSalir = new JMenuItem("Salir");
	
	JLabel labelEntrada = new JLabel("Palabra Inicial");
	JLabel labelSalida = new JLabel("Palabra Salida");
	JLabel labelDic = new JLabel("Diccionario");
	JLabel labelPalInter = new JLabel("Palabras Intermedias");
	
	JTextField textEntrada = new JTextField(); 
	JTextField textSalida = new JTextField();
	JTextArea textDic = new JTextArea();
	
	JButton botonCargar = new JButton("Cargar Pasatiempos");
	JButton botonAyuda = new JButton("Solicitar Ayuda");
	JButton botonResolver = new JButton("Resolver");
	
	JScrollPane scrollTextDic = new JScrollPane(textDic);
	JScrollPane scrollLetrasPalInter = new JScrollPane(panelLestraPalInter);
	
	public VentanaResolver () {
		
		textDic.setEditable(false);
		textEntrada.setEditable(false);
		textSalida.setEditable(false);
		
		panel.setLayout(bl);
		panelBotones.setLayout(glBotones);
		panelBotonesBl.setLayout(blBotones);
		panelPalInter.setLayout(blPalInter);
		panelPalInterBack.setLayout(blPalInterBack);
		panelInfo.setLayout(glPanelInfo);
		panelDic.setLayout(blPalDic);
		panelDicBack.setLayout(blPalDicBack);
		panelText.setLayout(glText);
		panelLestraPalInter.setLayout(glLestrasPalInter);
		
		panel.add(menuBar, BorderLayout.NORTH);
		panel.add(panelInfo, BorderLayout.CENTER);
		panel.add(panelBotonesBl, BorderLayout.SOUTH);
		
		panelBotonesBl.add(panelBotones,BorderLayout.CENTER);
		panelBotonesBl.add(new JLabel(" "),BorderLayout.SOUTH);
		panelBotonesBl.add(new JLabel(" "),BorderLayout.NORTH);
		panelBotonesBl.add(new JLabel("      "),BorderLayout.EAST);
		panelBotonesBl.add(new JLabel("      "),BorderLayout.WEST);
		
		panelInfo.add(panelDicBack);
		panelInfo.add(panelPalInterBack);
		
		panelDicBack.add(panelDic, BorderLayout.CENTER);
		panelDicBack.add(new JLabel(" "),BorderLayout.SOUTH);
		panelDicBack.add(new JLabel(" "),BorderLayout.NORTH);
		panelDicBack.add(new JLabel("      "),BorderLayout.EAST);
		panelDicBack.add(new JLabel("      "),BorderLayout.WEST);
		
		panelPalInterBack.add(panelPalInter, BorderLayout.CENTER);
		panelPalInterBack.add(new JLabel(" "),BorderLayout.SOUTH);
		panelPalInterBack.add(new JLabel(" "),BorderLayout.NORTH);
		panelPalInterBack.add(new JLabel("      "),BorderLayout.EAST);
		panelPalInterBack.add(new JLabel("      "),BorderLayout.WEST);
		
		panelPalInter.add(labelPalInter, BorderLayout.NORTH);
		panelPalInter.add(scrollLetrasPalInter, BorderLayout.CENTER);
		
		panelDic.add(panelText, BorderLayout.NORTH);
		panelDic.add(scrollTextDic, BorderLayout.CENTER);
		insertarTextPalInter ();
		
		panelText.add(labelEntrada);
		panelText.add(textEntrada);
		panelText.add(labelSalida);
		panelText.add(textSalida);
		panelText.add(labelDic);
		
		menuBar.add(menuInicio);
		
		menuInicio.add(menuCargarPas);
		menuInicio.addSeparator();
		menuInicio.add(menuResolver);
		menuInicio.add(menuAyuda);
		menuInicio.addSeparator();
		menuInicio.add(menuSalir);
		
		panelBotones.add(botonCargar);
		panelBotones.add(botonAyuda);
		panelBotones.add(botonResolver);
		
		this.setTitle("Resolver Transformacion");
		this.setBounds(0, 0, 800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(panel);
		
	}
	
	public JTextField[] iniciarTextPalInter () {
		
		JTextField letrasPalInter[] = new JTextField[16];
		
		for (int i=0; i<letrasPalInter.length; i++) {
			
			//letrasPalInter[i] = new JTextField(1);
			letrasPalInter[i] = new LetraPalabraIntermedia();
		}
		
		return letrasPalInter;
		
	}
	
	public void generarTextPalInter () {
		
		for (int i=0; i<cantPalInter; i++) {
			
			arrayPalInter.add(iniciarTextPalInter ());
			
		}
		
	}
	
	public void insertarTextPalInter () {
		
		iniciarTextPalInter ();
		generarTextPalInter ();
		
		for (int i=0; i<tamPalabras; i++) {
			
			for (int j=0; j<arrayPalInter.size(); j++) {
				
				panelLestraPalInter.add(arrayPalInter.get(j)[i]);
				
			}
			
		}
		
	}
	
}
