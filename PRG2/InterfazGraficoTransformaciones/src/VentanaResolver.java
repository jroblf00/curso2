import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaResolver extends JFrame {

	int cantPalInter = 20;
	int tamPalabras = 16;
	
	String resolucion;
	
	ArrayList<JTextField[]> arrayPalInter;/* = new ArrayList<JTextField[]>();*/
	
	
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
	GridLayout glLestrasPalInter;
	
	
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
	
	public VentanaResolver (String diccionario, String entrada, String salida, int cantPal) {
		
		textDic.setText(diccionario);
		textEntrada.setText(entrada);
		textSalida.setText(salida);
		cantPalInter = cantPal;
		tamPalabras = textEntrada.getText().length();
		
		glLestrasPalInter = crearGridLayout(cantPalInter, tamPalabras);
		
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
		if (textEntrada.getText().compareTo("")!=0) {
		resolver();
		mostrarPrimeraPalabra();
		mostrarUltimaPalabra();
		}
			
			
			
		
		
		botonCargar.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				try {
					
					cargar();
					
				} catch (IOException e) {
					
					e.printStackTrace();
			
				}
				
			}
			
		});
		
		botonResolver.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				mostrarResolucion();
				
			}
		});
	
		menuAyuda.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				ayuda();
				
			}
		});
		
		botonAyuda.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				ayuda();
				
			}
		});
		
		///////////////////
		
		menuCargarPas.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				try {
					
					cargar();
					
				} catch (IOException e) {
					
					e.printStackTrace();
			
				}
				
			}
			
		});
		
		menuResolver.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				mostrarResolucion();
				
			}
		});
	
		
		
		menuSalir.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				dispose();
				
			}
		});
		
	}
	
	
	
	public JTextField[] iniciarTextPalInter () {
		
		JTextField letrasPalInter[] = new JTextField[16];
		
		for (int i=0; i<letrasPalInter.length; i++) {
			
			letrasPalInter[i] = new LetraPalabraIntermedia();
			
		}
		
		return letrasPalInter;
		
	}
	
	public ArrayList<JTextField[]> generarTextPalInter () {
		
		ArrayList<JTextField[]> arraylist = new ArrayList<JTextField[]>();
		
		for (int i=0; i<cantPalInter; i++) {
			
			arraylist.add(iniciarTextPalInter ());
			
		}
		
		arrayPalInter = arraylist;
		
		return arraylist;
		
	}
	
	public void insertarTextPalInter () {
		
		panelLestraPalInter.removeAll();
		iniciarTextPalInter ();
		generarTextPalInter ();
		
		for (int i=0; i<tamPalabras; i++) {
			
			for (int j=0; j<cantPalInter; j++) {
				
				panelLestraPalInter.add(arrayPalInter.get(j)[i]);
				
			}
			
		}
		
		
		
	}
	
	public void setTextDiccionario(String texto) {
		
		textDic.setText(texto);
		
	}
	
	public void setTextPalEntrada (String texto) {
		
		textEntrada.setText(texto);
		
	}
	
	public void setTextPalSalida (String texto) {
		
		textSalida.setText(texto);
		
	}
	
	public GridLayout crearGridLayout(int filas, int columnas) {
		
		if (columnas < 1) {
		
			columnas = 16;
			
		}
			
		GridLayout gl = new GridLayout(filas, columnas);
		
		return gl;
				
	}
	
	public void abrirPasatiempos () throws IOException {
		
		String cadena;
		StringBuffer sb = new StringBuffer();
		JFileChooser fc = new JFileChooser();
		BufferedReader buffer = null;
		fc.showOpenDialog(null);
		FileReader fr;
			
		fr = new FileReader(fc.getSelectedFile());
		buffer = new BufferedReader(fr);
		
		cadena = buffer.readLine();
		StringTokenizer st = new StringTokenizer(cadena," ");
    	textEntrada.setText(st.nextToken());
    	textSalida.setText(st.nextToken());
    	cantPalInter = Integer.parseInt(st.nextToken());
				
		while((cadena = buffer.readLine())!=null) {
		    	
		    sb.append(cadena);
		    sb.append("\n");
		    	 
		}
		    
		textDic.setText(sb.toString());    
	     buffer.close();
	   
	}

	public void resolver () {
	
		Diccionario diccionario = new Diccionario();
		StringTokenizer st = new StringTokenizer(textDic.getText());
		
		while (st.hasMoreTokens()) {
			
			diccionario.insertar(st.nextToken());
			
		}
	
		Transformacion transformacion = new Transformacion(diccionario);
		transformacion.generarCaminos();
		transformacion.obtenerCamino(textEntrada.getText(), textSalida.getText());
		if (transformacion.hayCamino() == false) {
			
			JOptionPane.showMessageDialog(null,"No se ha encontrado solucion.");
			
		}
		else {
		
			resolucion = transformacion.getCamino();
		
		}
		
		
	}
	
	public void mostrarPrimeraPalabra () {
		
		if (tamPalabras>0) {
			
			int cont = 0;
			
			for (int i=0; i<cantPalInter; i++) {
				
				for (int j=0; j<tamPalabras; j++) {
					
					if (cont<tamPalabras) {
						
						arrayPalInter.get(j)[i].setText(Character.toString(textEntrada.getText().charAt(cont)));
						arrayPalInter.get(j)[i].setEditable(false);
						cont++;
						
					}
					
				}
				
			}
		
		}
		
	}
	
	public void mostrarUltimaPalabra () {
		
		int cont=0;
		int cont2=0;
		for (int i=0; i<16; i++) {
			
			for (int j=0; j<arrayPalInter.size(); j++) {
				
				if (cont<tamPalabras && cont2>=tamPalabras*(cantPalInter-1)) {
					
					arrayPalInter.get(j)[i].setText(Character.toString(textSalida.getText().charAt(cont)));
					arrayPalInter.get(j)[i].setEditable(false);
					cont++;
					
				}
				
				cont2++;
				
			}
		}
		
	}
	
	public void mostrarResolucion () {
		
		int cont = 0;
		
		for (int i=0; i<arrayPalInter.get(0).length; i++) {
			
			for (int j=0; j<arrayPalInter.size(); j++) {
				
				if (cont<resolucion.length()) {
					
					arrayPalInter.get(j)[i].setText(Character.toString(resolucion.charAt(cont)));
					cont++;
					
				}
				
			}
			
		}
		
	}
	
	public void ayuda() {
		
		boolean letraCambiada=false;
		int cont = 0;
		
		for (int i=0; i<arrayPalInter.get(0).length; i++) {
			
			for (int j=0; j<arrayPalInter.size(); j++) {
				
				if (cont<resolucion.length()) {
					
					if (arrayPalInter.get(j)[i].getText().compareTo(
							Character.toString(resolucion.charAt(cont))) != 0 && !letraCambiada){
						
						letraCambiada=true;
						
						arrayPalInter.get(j)[i].setText(Character.toString(resolucion.charAt(cont)));
						arrayPalInter.get(j)[i].setBackground(Color.RED);
					}
					
					cont++;
					
				}
				
			}
			
		}
		
	}
	
	public void cargar () throws IOException {
		
		abrirPasatiempos ();
		tamPalabras = textEntrada.getText().length();
		glLestrasPalInter = crearGridLayout(cantPalInter, tamPalabras);
		panelLestraPalInter.setLayout(glLestrasPalInter);
		scrollLetrasPalInter.removeAll();
		scrollLetrasPalInter.add(panelLestraPalInter);
		insertarTextPalInter ();
		panelPalInter.updateUI();
		panelPalInter.repaint();
		repaint();
		resolver();
		mostrarPrimeraPalabra();
		mostrarUltimaPalabra(); 
		
	}
	
}
