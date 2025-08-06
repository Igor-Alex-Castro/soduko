package br.com.dio.ui.input;

import java.awt.Dimension;
import java.awt.Font;
import java.util.EventListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.com.dio.model.Space;
import br.com.dio.service.EventNum;

public class NumberText extends JTextField implements br.com.dio.service.EventListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Space space;
	
	public NumberText(final Space space) {
		this.space = space;
		
		Dimension dimension = new Dimension(50, 50);
		this.setSize(dimension);
		this.setPreferredSize(dimension);
		this.setVisible(true);
		this.setFont(new Font("Arial", Font.PLAIN, 20));
		this.setHorizontalAlignment(CENTER);
		this.setDocument(new NumberTextLimit());
		
		this.setEnabled(!space.isFixed());
		
		if(space.isFixed()) {
			this.setText(space.getActual().toString());
		}
		
		
		this.getDocument().addDocumentListener(new DocumentListener() {
			
			
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changeSpace();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changeSpace();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changeSpace();
			}
			
			private void changeSpace() {
				if(getText().isEmpty()) {
					space.clearSpace();
					return;
				}
				space.setActual(Integer.parseInt(getText()));
			}
		});
	}

	@Override
	public void update(EventNum eventType) {
		// TODO Auto-generated method stub
		if(eventType.equals(EventNum.CLEAR_SPACE)&& (this.isEnabled())) {
			this.setText("");
		}
		
	}
}
