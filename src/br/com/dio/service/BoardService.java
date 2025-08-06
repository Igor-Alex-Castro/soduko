package br.com.dio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.dio.model.Board;
import br.com.dio.model.GameStatusEnum;
import br.com.dio.model.Space;

public class BoardService {
	private final static int BOARD_LIMIT = 9;
	
	private final Board board;
	
	public BoardService(final Map<String, String> gameConfig) {
		
		this.board = new Board(initBoard(gameConfig));
	}
	
	public List<List<Space>> gaetSpaces(){
		
		return this.board.getSpaces();
	}
	
	public void reset() {
		this.board.reset();
	}
	
	public boolean hasEerros() {
		return this.board.hasErros();
	}
	
	public GameStatusEnum getStatus() {
		return board.getStatus();
		
	}
	
	public boolean gameIsFinished() {
		return board.gameIsFinihed();
	}

	private List<List<Space>> initBoard(Map<String, String> gameConfig) {
		
		
		List<List<Space>> spaces = new ArrayList<List<Space>>();
		for (int i = 0; i < BOARD_LIMIT; i++) {
			spaces.add(new ArrayList<Space>());
			for (int j = 0; j <  BOARD_LIMIT; j++) {
				String postitionConfig =  gameConfig.get("%s,%s".formatted(i,j));
				Integer expected = Integer.parseInt(postitionConfig.split(",")[0]);
				boolean fixed = Boolean.parseBoolean(postitionConfig.split(",")[1]);
				Space currentSpace = new Space(expected, fixed);
				
				spaces.get(i).add(currentSpace);
			}
			
		}
		return  spaces;

	}
	
}
