package a7;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ConnectFourWidget extends JPanel implements ActionListener, SpotListener {

	private enum Player {BLACK, RED};
	
	private JSpotBoard _board;		/* SpotBoard playing area. */
	private JLabel _message;		/* Label for messages. */
	private boolean _game_won;		/* Indicates if games was been won already.*/
	private Player _next_to_play;	/* Identifies who has next turn. */     
	private boolean _spots_left;    /* Indicates if spots are left to ply on */
	private int[][] grid;
	
	
	
	public ConnectFourWidget() {
		/* Create SpotBoard and message label. */
		_spots_left = true;
		_board = new JSpotBoard(7,6);
		
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
		}
		_game_won = false;
		_next_to_play = Player.RED;	
		_spots_left = true;
		grid = new int[7][6];
		
		/* Display game start message. */
		
		_message.setText("Welcome to Connect Four. Red to play");
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
		int player_number = 0;
		
		if (_next_to_play == Player.RED) {
			player_color = Color.RED;
			player_name = "Red";
			player_number = 1;
			next_player_name = "Black";
			_next_to_play = Player.BLACK;
		} else {
			player_color = Color.BLACK;
			player_name = "BLACK";
			player_number = 2;
			next_player_name = "Red";
			_next_to_play = Player.RED;			
		}
				
		int x = spot.getSpotX();
		int y = spot.getSpotY();
		//grid[x][y] = player_number;
		
		/* Set color of spot clicked and toggle. */
		for (int i = 5; i >= 0; i--) {
			if (_board.getSpotAt(x, i).isEmpty()) {
				_board.getSpotAt(x, i).setSpotColor(player_color);
				_board.getSpotAt(x, i).toggleSpot();
				grid[x][i] = player_number;
				break;
			}
		}
		_message.setText(next_player_name + " to play");
		
		int xinitial = 0;
		int yinitial = 2;
		
		while (xinitial < 4) {
			int xcounter = xinitial;
			int ycounter = yinitial;
			int consecutive = 1;
			while (xcounter < 6 && ycounter < 5) {
				 if (grid[xcounter][ycounter] == grid[xcounter + 1][ycounter + 1] && grid[xcounter][ycounter] != 0) {
					 consecutive++;
					 if (consecutive == 4) {
							_message.setText(player_name + " wins!");
							_game_won = true;
							break;
					}
				 } else {
					 consecutive = 1;
				 }
				 xcounter++;
				 ycounter++;
			}
			if (yinitial > 0) {
				yinitial--;
			} else {
				xinitial++;
			}
		}
		
		xinitial = 6;
		yinitial = 2;
		
		while (xinitial > 2) {
			int xcounter = xinitial;
			int ycounter = yinitial;
			int consecutive = 1;
			while (xcounter > 0 && ycounter < 5) {
				 if (grid[xcounter][ycounter] == grid[xcounter - 1][ycounter + 1] && grid[xcounter][ycounter] != 0) {
					 consecutive++;
					 if (consecutive == 4) {
							_message.setText(player_name + " wins!");
							_game_won = true;
							break;
					}
				 } else {
					 consecutive = 1;
				 }
				 xcounter--;
				 ycounter++;
			}
			if (yinitial > 0) {
				yinitial--;
			} else {
				xinitial--;
			}
		}
	
		
		for (int i = 6; i >= 0; i--) {
			int consecutive = 1;
			for (int j = 5; j > 0; j--) {
				if (grid[i][j] == grid[i][j - 1] && grid[i][j] != 0) {
					consecutive++;
					if (consecutive == 4) {
						_message.setText(player_name + " wins!");
						_game_won = true;
						break;
					}
				} else {
					consecutive = 1;
				}
			}
		} 
		
		for (int j = 5; j >= 0; j--) {
			int consecutive = 1;
			for (int i = 6; i > 0 ; i--) {
				if (grid[i][j] == grid[i - 1][j] && grid[i][j] != 0) {
					consecutive++;
					if (consecutive == 4) {
						_message.setText(player_name + " wins!");
						_game_won = true;
						break;
					}
				} else {
					consecutive = 1;
				}
			}
		}
	 
		
		_spots_left = false;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				if (grid [i][j] == 0) {
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