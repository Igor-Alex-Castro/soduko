package br.com.dio.ui.button;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FinishGamaButton extends JButton {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FinishGamaButton(final ActionListener actionListener) {
		
		this.setText("Finalizar jogo");
		this.addActionListener(actionListener);
	}
}
