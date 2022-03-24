package bg.seachess.seachess.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

import bg.seachess.seachess.desk.Desk;
import bg.seachess.seachess.desk.Position;
import bg.seachess.seachess.main.SeaChess;
import bg.seachess.seachess.participants.PlayerParticipant;

public class MainWindow {
    private static final char MARK = PlayerParticipant.PLAYER_ONE_MARK;

    private JFrame window;
    private final Desk desk;
    private final Map<Position, JToggleButton> buttons;

    public MainWindow(SeaChess seachess) {
	window = new JFrame("TicTacToe");
	window.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	this.buttons = new HashMap<>();
	int size = seachess.getDesk().getSize();
	window.setLayout(new GridLayout(size, size));

	desk = seachess.getDesk();
	tmp(desk);

	populate();

	window.setLocationRelativeTo(null);
	window.pack();
	window.setVisible(true);
    }

    private void tmp(Desk desk) {
	desk.occupyPosition(new Position(0, 1), 'X');
	desk.occupyPosition(new Position(1, 0), 'o');
	desk.occupyPosition(new Position(1, 1), 'X');
	desk.occupyPosition(new Position(2, 2), '0');
	desk.occupyPosition(new Position(2, 0), 'X');
    }

    private void populate() {
	for (int row = 0; row < desk.getSize(); row++) {
	    for (int column = 0; column < desk.getSize(); column++) {
		Position position = new Position(row, column);
		JToggleButton button = new JToggleButton(desk.getField(position) + "");
		button.addActionListener(l -> click(button, position));
		buttons.put(position, button);
		if (desk.isFieldFree(position)) {
		    button.setBackground(Color.white);
		} else {
		    button.setBackground(Color.GREEN);
		    button.setEnabled(false);
		    button.setSelected(true);
		}
		window.add(button);
	    }
	}
    }

    private void checkVictory() {
	if (desk.victoryCondition()) {
	    Collection<Position> victoryPositions = desk.victoryPositions();
	    for (Position pos : buttons.keySet()) {
		if (victoryPositions.contains(pos)) {
		    JToggleButton button = buttons.get(pos);
		    button.setBackground(Color.RED);
		}
	    }
	    window.repaint();
	    System.out.println("WIN");
	}
    }

    private void click(JToggleButton button, Position position) {
	toggleButton(button, position);

    }

    private void toggleButton(JToggleButton button, Position position) {
	if (button.isSelected()) {
	    button.setEnabled(false);
	    button.setText(MARK + "");
	    desk.occupyPosition(position, MARK);
	    checkVictory();
	}
    }

}
