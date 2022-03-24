package bg.seachess.seachess.main;

import java.awt.EventQueue;

import bg.seachess.seachess.gui.MainWindow;

public class Main {

    public static void main(String[] args) {

	SeaChess chess = new SeaChess();

	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		new MainWindow(chess);

	    }
	});
    }

}
