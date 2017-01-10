import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaCrear extends JFrame {

	JPanel panel = new JPanel();
	JMenuBar menuBar = new JMenuBar();
	JMenu menuEdicion = new JMenu("Edicion");
	JMenu menuInicio = new JMenu("Inicio");
	JMenuItem menuResolver = new JMenuItem("Resolver");
	JMenuItem menuAbirDic = new JMenuItem("Abrir diccionario");
	JMenuItem menuGuardarDic = new JMenuItem("Guardar diccionario");
	JMenuItem menuAbrirPas = new JMenuItem("Abrir Pasatiempos");
	JMenuItem menuGuardarPas = new JMenuItem("Guardar Pasatiempos como...");
	JMenuItem menuSalir = new JMenuItem("Salir");
	JMenuItem menuCopiar = new JMenuItem("Copiar");
	JMenuItem menuCortar = new JMenuItem("Cortar");
	JMenuItem menuPegar = new JMenuItem("Pegar");
	JButton botonAbrirPas = new JButton("Abrir Pasatiempos");
	JButton botonGuardarPas = new JButton("Guardar Pasatiempos como...");
	JButton botonAbrirDic = new JButton("Abrir Diccionario");
	JButton botonGuardarDic = new JButton("Guardar Diccionario como...");
	JButton botonResolver = new JButton("Resolver");
	JLabel labelEntrada = new JLabel("Palabra inicial");
	JLabel labelSalida = new JLabel("Palabra final");
	JLabel labelDic = new JLabel("Diccionario");
	JLabel labelNumPal = new JLabel("Nº palabras Intermedias");
	JTextField textNumPal = new JTextField();
	JTextField textEntrada = new JTextField();
	JTextField textSalida = new JTextField();
	JTextArea textDic = new JTextArea();
	
	BorderLayout bl = new BorderLayout();
	BorderLayout blDic = new BorderLayout();
	BorderLayout blBot = new BorderLayout();
	JPanel panelDatos = new JPanel();
	JPanel panelDic = new JPanel();
	JPanel panelBotones = new JPanel();
	JPanel panelBlBot = new JPanel();
	JPanel panelGridCenter = new JPanel();
	GridLayout gl = new GridLayout(12,1,20,10);
	GridLayout glpanel = new GridLayout(1,2,30,10);
	GridLayout glbotones = new GridLayout(2,2,10,10);
	JScrollPane despla = new JScrollPane(textDic);
	
	public VentanaCrear () {
		
		panel.setLayout(bl);
		panelDatos.setLayout(gl);
		panelDic.setLayout(blDic);
		panelGridCenter.setLayout(glpanel);
		panelBotones.setLayout(glbotones);
		panelBlBot.setLayout(blBot);
		panel.add(panelGridCenter, BorderLayout.CENTER);
		panel.add(new JLabel("      "), BorderLayout.EAST);
		panel.add(new JLabel("      "), BorderLayout.WEST);
		panel.add(panelBlBot, BorderLayout.SOUTH);
		
		panelBlBot.add(panelBotones,BorderLayout.CENTER);
		panelBlBot.add(new JLabel("      "),BorderLayout.WEST);
		panelBlBot.add(new JLabel("      "),BorderLayout.EAST);
		panelBlBot.add(new JLabel("      "),BorderLayout.SOUTH);
		
		panelGridCenter.add(panelDatos);
		panelGridCenter.add(panelDic);
		
		panelDatos.add(new JLabel(" "));
		panelDatos.add(labelEntrada);
		panelDatos.add(textEntrada);
		panelDatos.add(labelSalida);
		panelDatos.add(textSalida);
		panelDatos.add(labelNumPal);
		panelDatos.add(textNumPal);
		panelDatos.add(new JLabel(" "));
		panelDatos.add(new JLabel(" "));
		
		panelDatos.add(botonResolver);
		
		panelDic.add(labelDic, BorderLayout.NORTH);
		panelDic.add(despla, BorderLayout.CENTER);
		panelDic.add(new JLabel(" "), BorderLayout.SOUTH);
	
		panelBotones.add(botonAbrirPas);
		panelBotones.add(botonGuardarPas);
		panelBotones.add(botonAbrirDic);
		panelBotones.add(botonGuardarDic);
		
		panel.add(menuBar, BorderLayout.NORTH);
		
		menuBar.add(menuInicio);
		menuBar.add(menuEdicion);
		
		menuInicio.add(menuResolver);
		menuInicio.addSeparator();
		menuInicio.add(menuAbrirPas);
		menuInicio.add(menuGuardarPas);
		menuInicio.addSeparator();
		menuInicio.add(menuAbirDic);
		menuInicio.add(menuGuardarDic);
		menuInicio.addSeparator();
		menuInicio.add(menuSalir);
		
		menuEdicion.add(menuCopiar);
		menuEdicion.add(menuCortar);
		menuEdicion.add(menuPegar);
		
		this.setTitle("Crear Transformacion");
		this.setBounds(0, 0, 800, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.add(panel);
		
		menuCopiar.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				textEntrada.copy();
				textSalida.copy();
				textDic.copy();
				
			}
		});
		
		botonResolver.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				new VentanaResolver().setVisible(true);
				
			}
		});
		
		botonGuardarPas.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				guardarPasatiempos ();
				
			}
		});
		
		menuGuardarDic.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				guardarPasatiempos ();
				
			}
		});
		
		menuCortar.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				textEntrada.cut();
				textSalida.cut();
				textDic.cut();
				
			}
		});
		
		menuPegar.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				
					
			}
		});
		
	}
	
	public void guardarPasatiempos () {
		
		JFileChooser fc = new JFileChooser();
		File file;
		FileWriter fw;
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(textDic.getText());
		buffer.append("\n");
		buffer.append(textEntrada.getText() + " " + textSalida.getText());
		
		try
		{

			fc.showDialog (null, "guardar como");
			file=fc.getSelectedFile ();
			fw = new FileWriter(file);
			fw.write(buffer.toString());
			fw.close();
							
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
