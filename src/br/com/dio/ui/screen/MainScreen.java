package br.com.dio.ui.screen;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.dio.model.GameStatusEnum;
import br.com.dio.model.Space;
import br.com.dio.service.BoardService;
import br.com.dio.service.EventNum;
import br.com.dio.service.NotifuerService;
import br.com.dio.ui.button.CheckGameStatusButton;
import br.com.dio.ui.button.FinishGamaButton;
import br.com.dio.ui.button.ResetButton;
import br.com.dio.ui.frame.MainFrame;
import br.com.dio.ui.input.NumberText;
import br.com.dio.ui.painel.MainPainel;
import br.com.dio.ui.painel.SudokoSector;

public class MainScreen {

	
	
	private final static Dimension dimension = new Dimension(600,600);
	
	private final BoardService boardService;
	private final NotifuerService notifuerService;

	private JButton checkGamesStatusButton;
	private JButton finishgameButton;
	private JButton	resetButton;
	
	
	public MainScreen(final Map<String, String> gameConfig) {
		
		this.boardService  = new BoardService(gameConfig);
		this.notifuerService = new NotifuerService();
	}
	
	
	public void buildMainScreen() {
		JPanel mainPanel = new MainPainel(dimension);
		JFrame mainFrame = new MainFrame(dimension, mainPanel);
		for(int r = 0; r < 9; r += 3) {
			int endRow = r +2;
			for(int c =0; c< 9; c += 3) {
				int endCol = c + 2;
				
				List<Space> spaces = getSpacesFromSector(boardService.gaetSpaces(), c, endCol,r, endRow);
				JPanel sector = generationSection(spaces);
				mainPanel.add(sector);
			}
		}
		
		addResetButton(mainPanel);
		addShowGameStatusButton(mainPanel);
		addFinishgameButton(mainPanel);
		
		
		
		mainFrame.revalidate();
		mainFrame.repaint();
		
		
		
	}
	
	private List<Space> getSpacesFromSector(List<List<Space>> spaces, 
			final int initColl, final int endColl,
			final int initRow, final int endRow){
		
		List<Space> spaceSector = new ArrayList<>();
		for(int r = initRow; r <= endRow; r++) {
			for(int c = initColl; c <= endColl; c++) {
				spaceSector.add(spaces.get(c).get(r));
			}
		}
		
		return spaceSector;
		
	}
	
	private JPanel generationSection(final List<Space> spaces) {
		List<NumberText> fields = new ArrayList<>(spaces.stream().map(NumberText::new).toList());
		fields.forEach(t -> notifuerService.subscriber(EventNum.CLEAR_SPACE, t));
		return new SudokoSector(fields);
	}


	private void addFinishgameButton(final JPanel mainPanel) {
		 finishgameButton = new FinishGamaButton(e -> {
			if(boardService.gameIsFinished()) {
				JOptionPane.showMessageDialog(null, "Parabéns você concluiu o jogo");
				resetButton.setEnabled(false);
				checkGamesStatusButton.setEnabled(false);
				finishgameButton.setEnabled(false);
			}else {
				JOptionPane.showConfirmDialog(null, "Seu jogo tem alguma inconsistência, ajuste e tente novamente");
			}
		});
		
		
		mainPanel.add(finishgameButton);
	}


	private void addShowGameStatusButton(JPanel mainPanel) {
		
		JButton  checkGamesStatusButton= new CheckGameStatusButton(e -> {
			boolean hasErrors = boardService.hasEerros();
			GameStatusEnum gameStatus = boardService.getStatus();
			
			String message = switch(gameStatus) {
			case COMPLETE -> "O jogo não foi iniciado";
			case INCOMPLETE -> "O jogo está incompleto";
			case NON_STARTED -> "O jogo está completo";

			};
			
			message += hasErrors ? "e contém erros" : "e não contém erros";
			
			JOptionPane.showMessageDialog(mainPanel, message);
		});
		mainPanel.add(checkGamesStatusButton);
		
	}


	private void addResetButton(JPanel mainPanel) {
			resetButton = new ResetButton(e -> {
			 int dialogResult = JOptionPane.
					showConfirmDialog(null, "Deseja realmente iniciar o jogo? ", "Limpar o jogo", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			 
			 if(dialogResult == 0) {
				 boardService.reset();
				 notifuerService.notify(EventNum.CLEAR_SPACE);
			 }
		});
		mainPanel.add(resetButton);
	}
	
	
	
}
