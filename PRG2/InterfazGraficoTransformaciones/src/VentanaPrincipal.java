
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame{

	JPanel panel = new JPanel();
	JPanel panelCrear = new JPanel();
	JPanel panelResolver = new JPanel();
	JPanel panelSalir = new JPanel();
	JPanel panelGl = new JPanel();
	
	JMenuBar barraMenu = new JMenuBar();
	JMenu menu = new JMenu("Inicio");
	JMenuItem menuCrear = new JMenuItem("Crear Pasatiempos");
	JMenuItem menuResolver = new JMenuItem("Resolver Pasatiempos");
	JMenuItem menuSalir = new JMenuItem("Salir");
	JButton botonCrear = new JButton("Crear Pasatiempos");
	JButton botonResolver = new JButton("Resolver Pasatiempos");
	JButton botonSalir = new JButton("Salir");
	
	BorderLayout bl = new BorderLayout();
	BorderLayout blCrear = new BorderLayout();
	BorderLayout blResolver = new BorderLayout();
	BorderLayout blSalir = new BorderLayout();
	
	GridLayout gl = new GridLayout(3, 1);
	
	//GridBagLayout gbl = new GridBagLayout();
	//GridBagConstraints gbc = new GridBagConstraints();
	
	public VentanaPrincipal(){
	
		panel.setLayout(bl);
		panelCrear.setLayout(blCrear);
		panelResolver.setLayout(blResolver);
		panelGl.setLayout(gl);
		panelSalir.setLayout(blSalir);
		
		panel.add(barraMenu, BorderLayout.NORTH);
		panel.add(panelGl, BorderLayout.CENTER);
		
		panelGl.add(panelCrear);
		panelGl.add(panelResolver);
		panelGl.add(panelSalir);

		panelCrear.add(botonCrear,BorderLayout.CENTER);
		panelCrear.add(new JLabel("      "),BorderLayout.WEST);
		panelCrear.add(new JLabel("      "),BorderLayout.EAST);
		panelCrear.add(new JLabel(" "),BorderLayout.NORTH);
		
		panelResolver.add(botonResolver,BorderLayout.CENTER);
		panelResolver.add(new JLabel(" "),BorderLayout.NORTH);
		panelResolver.add(new JLabel(" "),BorderLayout.SOUTH);
		panelResolver.add(new JLabel("      "),BorderLayout.WEST);
		panelResolver.add(new JLabel("      "),BorderLayout.EAST);
		
		panelSalir.add(botonSalir,BorderLayout.CENTER);
		panelSalir.add(new JLabel("      "),BorderLayout.WEST);
		panelSalir.add(new JLabel("      "),BorderLayout.EAST);
		panelSalir.add(new JLabel(" "),BorderLayout.SOUTH);
		
		this.add(panel);
		
		barraMenu.add(menu);
		menu.add(menuCrear);
		menu.add(menuResolver);
		menu.addSeparator();
		menu.add(menuSalir);
		
		/*
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(barraMenu, gbc);
		
		gbc.gridx =0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 100;
		gbc.weighty = 100;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(botonCrear, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 100;
		gbc.weighty = 100;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(botonResolver, gbc);
		*/
		
		this.setTitle("Transformaciones");
		this.setBounds(0,0, 600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		botonCrear.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				new VentanaCrear().setVisible(true);
				
			}
		});
		
		botonResolver.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				new VentanaResolver().setVisible(true);
				
			}
		});
		
		menuSalir.addActionListener (new ActionListener ( )
		{
			public void actionPerformed (ActionEvent arg0) {
			
				System.exit(0);
				
			}
		});
		
	}
	
}
