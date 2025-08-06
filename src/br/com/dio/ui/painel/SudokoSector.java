package br.com.dio.ui.painel;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import br.com.dio.ui.input.NumberText;


import static java.awt.Color.black;

public class SudokoSector  extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SudokoSector(final List<NumberText> textFields) {
		Dimension dimension = new Dimension(170, 170);
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		
		this.setBorder(new LineBorder(black, 2, true ));
		this.setVisible(true);
		
		textFields.forEach(this::add);
	}
}
