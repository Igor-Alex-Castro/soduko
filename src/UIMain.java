import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.com.dio.ui.frame.MainFrame;
import br.com.dio.ui.painel.MainPainel;

public class UIMain {
	public static void main(String[] args) {
		
		Dimension dimension = new Dimension(600, 600);
		JPanel mainPanel = new MainPainel(dimension);
		JFrame mainFrame = new MainFrame(dimension, mainPanel);
		mainFrame.revalidate();
		mainFrame.repaint();
		
		
	}
}
