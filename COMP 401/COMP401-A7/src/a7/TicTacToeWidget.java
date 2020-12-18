package a7;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TicTacToeWidget extends JPanel implements ActionListener, SpotListener {

	private enum Player {BLACK, WHITE};
	
	private JSpotBoard _board;		/* SpotBoard playing area. */
	private JLabel _message;		/* Label for messages. */
	private boolean _game_won;		/* Indicates if games was been won already.*/
	private Player _next_to_play;	/* Identifies who has next turn. */     
	private boolean _spots_left;    /* Indicates if spots are left to ply on */
	private Color[][] grid;
	
	
	
	public TicTacToeWidget() {
		/* Create SpotBoard and message label. */
		_spots_left = true;
		_board = new JSpotBoard(3,3);
		
		_message = new JLabel();
		
		/* Set layout and place SpotBoard at center. */
		
		setLayout(new BorderLayout());
		add(_board, BorderLayout.CENTER);

		/* Create subpanel for message area and reset button. */
		
		JPanel reset_message_panel = new JPanel();
		reset_message_panel.setLayout(new BorderLayout());

		/* Reset button. Add ourselves as the action listener. */
		
		JButton reset_button = new JButton("Restart");
		reset_button.addActionListener(this);
		reset_message_panel.add(reset_button, BorderLayout.EAST);
		reset_message_panel.add(_message, BorderLayout.CENTER);

		/* Add subpanel in south area of layout. */
		
		add(reset_message_panel, BorderLayout.SOUTH);

		/* Add ourselves as a spot listener for all of the
		 * spots on the spot board.
		 */
		_board.addSpotListener(this);

		/* Reset game. */
		resetGame();
	}
	
	private void resetGame() {
		/* Clear all spots on board. Uses the fact that SpotBoard
		 * implements Iterable<Spot> to do this in a for-each loop.
		 */

		for (Spot s : _board) {
			s.clearSpot();
			s.setBackground(Color.LIGHT_GRAY);
		}
		_game_won = false;
		_next_to_play = Player.WHITE;	
		_spots_left = true;
		grid = new Color[3][3];
		
		/* Display game start message. */
		
		_message.setText("Welcome to Tic Tac Toe. White to play");
	}
	
	public void actionPerformed(ActionEvent e) {
		/* Handles reset game button. Simply reset the game. */
		resetGame();
	}

	/* Implementation of SpotListener below. Implements game
	 * logic as responses to enter/exit/click on spots.
	 */
	
	
	@Override
	public void spotClicked(Spot spot) {
		/* If game already won, do nothing. */
		if (_game_won || !spot.isEmpty()) {
			return;
		}


		/* Set up player and next player name strings,
		 * and player color as local variables to
		 * be used later.
		 */
		
		String player_name = null;
		String next_player_name = null;
		Color player_color = null;
		
		if (_next_to_play == Player.WHITE) {
			player_color = Color.WHITE;
			player_name = "White";
			next_player_name = "Black";
			_next_to_play = Player.BLACK;
		} else {
			player_color = Color.BLACK;
			player_name = "BLACK";
			next_player_name = "White";
			_next_to_play = Player.WHITE;			
		}
				
		
		/* Set color of spot clicked and toggle. */
	
		spot.setSpotColor(player_color);
		spot.toggleSpot();
	
		_message.setText(next_player_name + " to play");

		/* Check if spot clicked is secret spot.
		 * If so, mark game as won and update background
		 * of spot to show that it was the secret spot.
		 */

		/* Update the message depending on what happened.
		 * If spot is empty, then we must have just cleared the spot. Update message accordingly.
		 * If spot is not empty and the game is won, we must have
		 * just won. Calculate score and display as part of game won message.
		 * If spot is not empty and the game is not won, update message to
		 * report spot coordinates and indicate whose turn is next.
		 */
		
		int x = spot.getSpotX();
		int y = spot.getSpotY();
		grid[x][y] = player_color;
		
		for (int i = 0; i < 3; i++) {
			if (grid[i][0] != null && grid[i][0] == (grid[i][1]) && grid [i][1] == (grid[i][2])) {
				_message.setText(player_name + " wins!");
				_game_won = true;
			}
			if (grid[0][i] != null && grid[0][i] == (grid[1][i]) && grid[1][i] == (grid[2][i])) {
				_message.setText(player_name + " wins!");
				_game_won = true;
			}
		}

		
		if (grid[0][0] != null && grid[0][0] == (grid[1][1]) && grid[1][1] == (grid[2][2])) {
			_message.setText(player_name + " wins!");
			_game_won = true;
		}
		if (grid[1][1] != null && grid[2][0] == (grid[1][1]) && grid[1][1] == (grid[0][2])) {
			_message.setText(player_name + " wins!");
			_game_won = true;
		}
		
		_spots_left = false;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (grid [i][j] == null) {
					_spots_left = true;
				}
			}
		}
		if (_spots_left == false) {
			_message.setText("Tis a draw");
		}
	}
			
			

	@Override
	public void spotEntered(Spot spot) {
		// TODO Auto-generated method stub* Highlight spot if game still going on. */
		if (_game_won) {
			return;
		}
		if (spot.isEmpty() == true) {
			spot.highlightSpot();
		}
	}

	@Override
	public void spotExited(Spot spot) {
		// TODO Auto-generated method stub
		spot.unhighlightSpot();
	}
}