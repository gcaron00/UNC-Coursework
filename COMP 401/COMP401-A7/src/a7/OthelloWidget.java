package a7;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


// yet to do: , restrick to 32 placements. give turns if not turn. count winner if neither have valid turns


public class OthelloWidget extends JPanel implements ActionListener, SpotListener {

	private enum Player {BLACK, WHITE};
	
	private JSpotBoard _board;		/* SpotBoard playing area. */
	private JLabel _message;		/* Label for messages. */
	private boolean _game_won;		/* Indicates if games was been won already.*/
	private Player _next_to_play;	/* Identifies who has next turn. */
	private Color _next_to_play_color;
	
	
	
	
	public OthelloWidget() {
		/* Create SpotBoard and message label. */
		_board = new JSpotBoard(8,8);
		
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
		
		_board.getSpotAt(4,3).setSpotColor(Color.BLACK);
		_board.getSpotAt(4,3).toggleSpot();
		_board.getSpotAt(4,4).setSpotColor(Color.WHITE);
		_board.getSpotAt(4,4).toggleSpot();
		_board.getSpotAt(3,3).setSpotColor(Color.WHITE);
		_board.getSpotAt(3,3).toggleSpot();
		_board.getSpotAt(3,4).setSpotColor(Color.BLACK);
		_board.getSpotAt(3,4).toggleSpot();
		
		_game_won = false;
		_next_to_play = Player.BLACK;
		_next_to_play_color = Color.BLACK;
		
		
		/* Display game start message. */
		
		_message.setText("Welcome to Otello. Black to play");
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
		
		int x = spot.getSpotX();
		int y = spot.getSpotY();
		
		List<Integer> rightxtoflip = new ArrayList<Integer>();
		List<Integer> leftxtoflip = new ArrayList<Integer>();
		List<Integer> aboveytoflip = new ArrayList<Integer>();
		List<Integer> belowytoflip = new ArrayList<Integer>();
		List<int[]> upperrighttoflip = new ArrayList<int[]>();
		List<int[]> upperlefttoflip = new ArrayList<int[]>();
		List<int[]> lowerlefttoflip = new ArrayList<int[]>();
		List<int[]> lowerrighttoflip = new ArrayList<int[]>();
		
		//right
		for (int i = x + 1; i < 8; i++) {
			if (!_board.getSpotAt(i, y).isEmpty()) {
				if (_board.getSpotAt(i, y).getSpotColor() == _next_to_play_color) {
					break;
				} else {
					if (i == 7) {
						rightxtoflip.clear();
						break;
					} else {
						rightxtoflip.add(i);
					}
				}
				
			} else {
				rightxtoflip.clear();
				break;
			}
		}
		
		for (int i: rightxtoflip) {
			_board.getSpotAt(i, y).setSpotColor(_next_to_play_color);
			_board.getSpotAt(i, y).setSpot();
		}
		
		//left
		for (int i = x - 1; i >= 0 ; i--) {
			if (!_board.getSpotAt(i, y).isEmpty()) {
				if (_board.getSpotAt(i, y).getSpotColor() == _next_to_play_color) {
					break;
				} else {
					if (i == 0) {
						leftxtoflip.clear();
						break;
					} else {
						leftxtoflip.add(i);
					}	
				}
				
			} else {
				leftxtoflip.clear();
				break;
			}
		}
		
		for (int i: leftxtoflip) {
			_board.getSpotAt(i, y).setSpotColor(_next_to_play_color);
			_board.getSpotAt(i, y).setSpot();
		}
		
		//above
		for (int i = y - 1; i >= 0 ; i--) {
			if (!_board.getSpotAt(x, i).isEmpty()) {
				if (_board.getSpotAt(x, i).getSpotColor() == _next_to_play_color) {
					break;
				} else {
					if (i == 0) {
						aboveytoflip.clear();
						break;
					} else {
						aboveytoflip.add(i);
					}
				}
				
			} else {
				aboveytoflip.clear();
				break;
			}
		}
		
		for (int i: aboveytoflip) {
			_board.getSpotAt(x, i).setSpotColor(_next_to_play_color);
			_board.getSpotAt(x, i).setSpot();
		}
		
		//bellow 
		for (int i = y + 1; i < 8 ; i++) {
			if (!_board.getSpotAt(x, i).isEmpty()) {
				if (_board.getSpotAt(x, i).getSpotColor() == _next_to_play_color) {
					break;
				} else {
					if (i == 7) {
						belowytoflip.clear();
						break;
					} else {
						belowytoflip.add(i);
					}
				}
				
			} else {
				belowytoflip.clear();
				break;
			}
		}
		
		for (int i: belowytoflip) {
			_board.getSpotAt(x, i).setSpotColor(_next_to_play_color);
			_board.getSpotAt(x, i).setSpot();
		}
		
		//upperright
		int ycounter = y - 1;
		int xcounter = x + 1;
		
		while (ycounter >= 0 && xcounter < 8) {
			if (!_board.getSpotAt(xcounter, ycounter).isEmpty()) {
				if (_board.getSpotAt(xcounter, ycounter).getSpotColor() == _next_to_play_color) {
					break;
				} else {
					if (xcounter == 7 || ycounter == 0) {
						upperrighttoflip.clear();
						break;
					} else {
						int[] pt = {xcounter, ycounter};
						upperrighttoflip.add(pt);
						xcounter++;
						ycounter--;
					}
				}
				
			} else {
				upperrighttoflip.clear();
				break;
			}
		}
		for (int[] i: upperrighttoflip) {
			_board.getSpotAt(i[0], i[1]).setSpotColor(_next_to_play_color);
			_board.getSpotAt(i[0], i[1]).setSpot();
		}
		
		//upperleft	
		ycounter = y - 1;
		xcounter = x - 1;
		
		while (ycounter >= 0 && xcounter >= 0) {
			if (!_board.getSpotAt(xcounter, ycounter).isEmpty()) {
				if (_board.getSpotAt(xcounter, ycounter).getSpotColor() == _next_to_play_color) {
					break;
				} else {
					if (xcounter == 0 || ycounter == 0) {
						upperlefttoflip.clear();
						break;
					} else {
						int[] pt = {xcounter, ycounter};
						upperlefttoflip.add(pt);
						xcounter--;
						ycounter--;
					}
				}
				
			} else {
				upperlefttoflip.clear();
				break;
			}
		}
		for (int[] i: upperlefttoflip) {
			_board.getSpotAt(i[0], i[1]).setSpotColor(_next_to_play_color);
			_board.getSpotAt(i[0], i[1]).setSpot();
		}		
		
		//lower right
		ycounter = y + 1;
		xcounter = x + 1;
		
		while (ycounter < 8 && xcounter < 8) {
			if (!_board.getSpotAt(xcounter, ycounter).isEmpty()) {
				if (_board.getSpotAt(xcounter, ycounter).getSpotColor() == _next_to_play_color) {
					break;
				} else {
					if (xcounter == 7 || ycounter == 7) {
						lowerrighttoflip.clear();
						break;
					} else {
						int[] pt = {xcounter, ycounter};
						lowerrighttoflip.add(pt);
						xcounter++;
						ycounter++;
					}
				}
				
			} else {
				lowerrighttoflip.clear();
				break;
			}
		}
		for (int[] i: lowerrighttoflip) {
			_board.getSpotAt(i[0], i[1]).setSpotColor(_next_to_play_color);
			_board.getSpotAt(i[0], i[1]).setSpot();
		}
		
		//lower left
		ycounter = y + 1;
		xcounter = x - 1;
		
		while (ycounter < 7 && xcounter >= 0) {
			if (!_board.getSpotAt(xcounter, ycounter).isEmpty()) {
				if (_board.getSpotAt(xcounter, ycounter).getSpotColor() == _next_to_play_color) {
					break;
				} else {
					if (xcounter == 0 || ycounter == 7) {
						lowerlefttoflip.clear();
						break;
					} else {
						int[] pt = {xcounter, ycounter};
						lowerlefttoflip.add(pt);
						xcounter--;
						ycounter++;
					}
				}
				
			} else {
				lowerlefttoflip.clear();
				break;
			}
		}
		for (int[] i: lowerlefttoflip) {
			_board.getSpotAt(i[0], i[1]).setSpotColor(_next_to_play_color);
			_board.getSpotAt(i[0], i[1]).setSpot();
		}
		
		

		
		
		/* Set up player and next player name strings,
		 * and player color as local variables to
		 * be used later.
		 */
		if (aboveytoflip.size() + belowytoflip.size() + rightxtoflip.size() + leftxtoflip.size() + 
				upperrighttoflip.size() + upperlefttoflip.size() + lowerlefttoflip.size() + 
				lowerrighttoflip.size() == 0) {
			return;
		}
		
		spot.setSpotColor(_next_to_play_color);
		spot.toggleSpot();
		
		String player_name = null;
		String next_player_name = null;
		Color player_color = null;	
		
		if (_next_to_play == Player.WHITE) {
			player_color = Color.WHITE;
			player_name = "White";
			next_player_name = "Black";
			_next_to_play = Player.BLACK;
			_next_to_play_color = Color.BLACK;
		} else {
			player_color = Color.BLACK;
			player_name = "BLACK";
			next_player_name = "White";
			_next_to_play = Player.WHITE;	
			_next_to_play_color = Color.WHITE;
		}
		
		boolean _player_valid_moves = false;
		boolean _next_valid_moves = false;
		
		for (Spot s: _board) {
			if (movesLeft(_next_to_play_color, s)) {
				_next_valid_moves = true;
			}
			if (movesLeft(player_color, s)) {
				_player_valid_moves = true;
			}
		}
		if (!_next_valid_moves) {
			if (!_player_valid_moves) {
				_game_won = true;
			} else {
				_message.setText("No valid moves for " + next_player_name + ". Skip turn. " + player_name + " to play");
				if (_next_to_play == Player.WHITE) {
					player_color = Color.WHITE;
					player_name = "White";
					next_player_name = "Black";
					_next_to_play = Player.BLACK;
					_next_to_play_color = Color.BLACK;
				} else {
					player_color = Color.BLACK;
					player_name = "BLACK";
					next_player_name = "White";
					_next_to_play = Player.WHITE;	
					_next_to_play_color = Color.WHITE;
				}
			}
		} else {
			_message.setText(next_player_name + " to play");
		}
		
		// score show score for fianl score if won
		int[] score = new int[2];
		
		for (Spot s: _board) {
			if (s.getSpotColor() == Color.BLACK) {
				score[0]++;
			} else {
				if (s.getSpotColor() == Color.WHITE)
				score[1]++;
			}
		}
		
		if (_game_won == true) {
			if (score[0] > score[1]) {
				_message.setText("No remaining moves. Black wins " + score[0] + " to " + score[1]);
			} else {
				if (score [1] > score [0]) {
					_message.setText("No remaining moves. White wins " + score[1] + " to " + score[0]);
				} else {
					_message.setText("No remaining moves. The game is a draw. Score: " + score[0] + " to " + score[1]);
				}
			}
			
		}
	}
	
	
	public boolean movesLeft(Color c, Spot s) {
		if (!s.isEmpty()) {
			return false;
		} else {
			int x = s.getSpotX();
			int y = s.getSpotY();
			
			List<Integer> rightxtoflip = new ArrayList<Integer>();
			List<Integer> leftxtoflip = new ArrayList<Integer>();
			List<Integer> aboveytoflip = new ArrayList<Integer>();
			List<Integer> belowytoflip = new ArrayList<Integer>();
			List<int[]> upperrighttoflip = new ArrayList<int[]>();
			List<int[]> upperlefttoflip = new ArrayList<int[]>();
			List<int[]> lowerlefttoflip = new ArrayList<int[]>();
			List<int[]> lowerrighttoflip = new ArrayList<int[]>();
			
			//right
			for (int i = x + 1; i < 8; i++) {
				if (!_board.getSpotAt(i, y).isEmpty()) {
					if (_board.getSpotAt(i, y).getSpotColor() == c) {
						break;
					} else {
						if (i == 7) {
							rightxtoflip.clear();
							break;
						} else {
							rightxtoflip.add(i);
						}
					}
					
				} else {
					rightxtoflip.clear();
					break;
				}
			}
			//left
			for (int i = x - 1; i >= 0 ; i--) {
				if (!_board.getSpotAt(i, y).isEmpty()) {
					if (_board.getSpotAt(i, y).getSpotColor() == c) {
						break;
					} else {
						if (i == 0) {
							leftxtoflip.clear();
							break;
						} else {
							leftxtoflip.add(i);
						}
					}
					
				} else {
					leftxtoflip.clear();
					break;
				}
			}
			//above
			for (int i = y - 1; i >= 0 ; i--) {
				if (!_board.getSpotAt(x, i).isEmpty()) {
					if (_board.getSpotAt(x, i).getSpotColor() == c) {
						break;
					} else {
						if (i == 0) {
							aboveytoflip.clear();
							break;
						} else {
							aboveytoflip.add(i);
						}	
					}
					
				} else {
					aboveytoflip.clear();
					break;
				}
			}
			//bellow 
			for (int i = y + 1; i < 8 ; i++) {
				if (!_board.getSpotAt(x, i).isEmpty()) {
					if (_board.getSpotAt(x, i).getSpotColor() == c) {
						break;
					} else {
						if (i == 7) {
							belowytoflip.clear();
							break;
						} else {
							belowytoflip.add(i);
						}
					}
					
				} else {
					belowytoflip.clear();
					break;
				}
			}
			
			//upperright
			int ycounter = y - 1;
			int xcounter = x + 1;
			
			while (ycounter >= 0 && xcounter < 8) {
				if (!_board.getSpotAt(xcounter, ycounter).isEmpty()) {
					if (_board.getSpotAt(xcounter, ycounter).getSpotColor() == _next_to_play_color) {
						break;
					} else {
						if (xcounter == 7 || ycounter == 0) {
							upperrighttoflip.clear();
							break;
						} else {
							int[] pt = {xcounter, ycounter};
							upperrighttoflip.add(pt);
							xcounter++;
							ycounter--;
						}
					}
					
				} else {
					upperrighttoflip.clear();
					break;
				}
			}
	
			//upperleft	
			ycounter = y - 1;
			xcounter = x - 1;
			
			while (ycounter >= 0 && xcounter >= 0) {
				if (!_board.getSpotAt(xcounter, ycounter).isEmpty()) {
					if (_board.getSpotAt(xcounter, ycounter).getSpotColor() == _next_to_play_color) {
						break;
					} else {
						if (xcounter == 0 || ycounter == 0) {
							upperlefttoflip.clear();
							break;
						} else {
							int[] pt = {xcounter, ycounter};
							upperlefttoflip.add(pt);
							xcounter--;
							ycounter--;
						}
					}
					
				} else {
					upperlefttoflip.clear();
					break;
				}
			}
		
			//lower right
			ycounter = y + 1;
			xcounter = x + 1;
			
			while (ycounter < 8 && xcounter < 8) {
				if (!_board.getSpotAt(xcounter, ycounter).isEmpty()) {
					if (_board.getSpotAt(xcounter, ycounter).getSpotColor() == _next_to_play_color) {
						break;
					} else {
						if (xcounter == 7 || ycounter == 7) {
							lowerrighttoflip.clear();
							break;
						} else {
							int[] pt = {xcounter, ycounter};
							lowerrighttoflip.add(pt);
							xcounter++;
							ycounter++;
						}
					}
					
				} else {
					lowerrighttoflip.clear();
					break;
				}
			}
			
			//lower left
			ycounter = y + 1;
			xcounter = x - 1;
			
			while (ycounter < 7 && xcounter >= 0) {
				if (!_board.getSpotAt(xcounter, ycounter).isEmpty()) {
					if (_board.getSpotAt(xcounter, ycounter).getSpotColor() == _next_to_play_color) {
						break;
					} else {
						if (xcounter == 0 || ycounter == 7) {
							lowerlefttoflip.clear();
							break;
						} else {
							int[] pt = {xcounter, ycounter};
							lowerlefttoflip.add(pt);
							xcounter--;
							ycounter++;
						}
					}
					
				} else {
					lowerlefttoflip.clear();
					break;
				}
			}
			
			if (aboveytoflip.size() + belowytoflip.size() + rightxtoflip.size() + leftxtoflip.size() + 
					upperrighttoflip.size() + upperlefttoflip.size() + lowerlefttoflip.size() + 
					lowerrighttoflip.size() > 0) {
				return true;
			} else {
				return false;
			}
		}
	}
			

	@Override
	public void spotEntered(Spot spot) {
		// TODO Auto-generated method stub* Highlight spot if game still going on. */
		if (_game_won) {
			return;
		}
		if (movesLeft(_next_to_play_color, spot) == true) {
			spot.highlightSpot();
		}
	}

	@Override
	public void spotExited(Spot spot) {
		// TODO Auto-generated method stub
		spot.unhighlightSpot();
	}
}