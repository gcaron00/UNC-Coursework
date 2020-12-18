package a4;

public class ImmutablePixelArrayPicture implements Picture {
	
	private Pixel[][] _pixel_array2;
	private int _width2;
	private int _height2;
	
	
	public ImmutablePixelArrayPicture(Pixel[][] pixel_array) {
		if (pixel_array == null || pixel_array.length == 0 || pixel_array[0].length == 0) {
			throw new IllegalArgumentException("null");
		}
		for (int i = 0; i < pixel_array.length; i++) {	
			if (pixel_array[i] == null) {
				throw new IllegalArgumentException("null");
			}
		}
		for (int i = 0; i < pixel_array.length; i++) {
			if (pixel_array[i].length != pixel_array[0].length) {
				throw new IllegalArgumentException("null");
			}
		}
		
		for (int i = 0; i < pixel_array.length; i++) {
			for (int j = 0; j < pixel_array[0].length; j++) {
				if (pixel_array[i][j] == null) {
					throw new IllegalArgumentException("null");
				}
			}
		}
		_width2 = pixel_array.length;
		_height2 = pixel_array[0].length;
		_pixel_array2 = pixel_array;
			
		
	}
	
	public ImmutablePixelArrayPicture(int width, int height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("not in bounds");
		}
		_width2 = width;
		_height2 = height;
		_pixel_array2 = new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) 
				_pixel_array2[i][j] = new GrayPixel(0.5);
			}
		}
		
	
	public ImmutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("not in bounds");
		}
		_width2 = width;
		_height2 = height;
		_pixel_array2 = new Pixel[width][height];
		if (initial_value == null) {
			throw new IllegalArgumentException("other is null");
		} else {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					_pixel_array2[i][j] = initial_value;
				}
			}
		}
			
	}
	
	
	
	public int getWidth() {
		return _width2;
	}
	
	public int getHeight() {
		return _height2;
	}
	
	public Pixel getPixel(int x, int y) {
		if (x < _width2 && x >= 0) {
			if (y < _height2 && y >= 0) {
				return _pixel_array2[x][y];
			} 
		}
		throw new IllegalArgumentException("Not in range");
	}
	
	//because objects are value types they are stored in stack 
	public Picture paint(int x, int y, Pixel p) {
		if (p == null) {
			throw new IllegalArgumentException("other is null");
		}
		if (x < getWidth() && x >= 0) {
			if (y < getHeight() && y >= 0) {
				
				Pixel[][] _pixel_array3 = new Pixel[_width2][_height2];
				for (int i = 0; i < _pixel_array2.length; i++) {
					for (int j = 0; j < _pixel_array2[0].length; j++) {
						_pixel_array3[i][j] = _pixel_array2[i][j];
					}
				}
				MutablePixelArrayPicture Pic = new MutablePixelArrayPicture(_pixel_array3);
				Pic._pixel_array[x][y] = p;
				return Pic; 
				
			} 
		}
		throw new IllegalArgumentException("Not in range");
	}
	
	
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < getWidth() && x >= 0) {
			if (y < getHeight() && y >= 0) {
				
				Pixel[][] _pixel_array3 = new Pixel[_width2][_height2];
				for (int i = 0; i < _pixel_array2.length; i++) {
					for (int j = 0; j < _pixel_array2[0].length; j++) {
						_pixel_array3[i][j] = _pixel_array2[i][j];
					}
				}
				MutablePixelArrayPicture Pic = new MutablePixelArrayPicture(_pixel_array3);
				Pic._pixel_array[x][y] = Pic._pixel_array[x][y].blend(p, factor);
				return Pic;
				
			} 
		}
		throw new IllegalArgumentException("Not in range");
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (ax < getWidth() && ax >= 0 && p != null) {
			if (bx < getWidth() && bx >= 0) {
				if (ay < getHeight() && ay >= 0) {
					if (by < getWidth() && by >= 0) {
						Pixel[][] _pixel_array3 = new Pixel[_width2][_height2];
						for (int i = 0; i < _pixel_array2.length; i++) {
							for (int j = 0; j < _pixel_array2[0].length; j++) {
								_pixel_array3[i][j] = _pixel_array2[i][j];
							}
						}
						MutablePixelArrayPicture Pic = new MutablePixelArrayPicture(_pixel_array3);
						if (ax >= bx) {
							if (ay >= by) {
								for (int i = bx; i <= ax; i++) {
									for (int j = by; j <= ay; j++) {
										Pic._pixel_array[i][j] = p;
									}
								}
								return Pic;
							} else {
								for (int i = bx; i <= ax; i++) {
									for (int j = ay; j <= by; j++) {
										Pic._pixel_array[i][j] = p;
									}
								}
								return Pic;
							}
						} else {
							if (ay > by) {
								for (int i = ax; i <= bx; i++) {
									for (int j = by; j <= ay; j++) {
										Pic._pixel_array[i][j] = p;
									}
								}
								return Pic;
							} else {
								for (int i = ax; i <= bx; i++) {
									for (int j = ay; j <= by; j++) {
										Pic._pixel_array[i][j] = p;
									}
								}
								return Pic;
							}
							
						}
					}
				}
			} 
		}
		throw new IllegalArgumentException("Not in range");
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (ax <= (getWidth() - 1) && ax >= 0 && p != null) {
			if (bx <= (getWidth() - 1) && bx >= 0) {
				if (ay <= (getHeight() - 1) && ay >= 0) {
					if (by <= (getWidth() - 1) && by >= 0) {
						Pixel[][] _pixel_array3 = new Pixel[_width2][_height2];
						for (int i = 0; i < _pixel_array2.length; i++) {
							for (int j = 0; j < _pixel_array2[0].length; j++) {
								_pixel_array3[i][j] = _pixel_array2[i][j];
							}
						}
						MutablePixelArrayPicture Pic = new MutablePixelArrayPicture(_pixel_array3);
						if (ax >= bx) {
							if (ay >= by) {
								for (int i = bx; i <= ax; i++) {
									for (int j = by; j <= ay; j++) {
										Pic._pixel_array[i][j] = Pic._pixel_array[i][j].blend(p, factor);
									}
								}
								return Pic;
							} else {
								for (int i = bx; i <= ax; i++) {
									for (int j = ay; j <= by; j++) {
										Pic._pixel_array[i][j] = Pic._pixel_array[i][j].blend(p, factor);
									}
								}
								return Pic;
							}
						} else {
							if (ay > by) {
								for (int i = ax; i <= bx; i++) {
									for (int j = by; j <= ay; j++) {
										Pic._pixel_array[i][j] = Pic._pixel_array[i][j].blend(p, factor);
									}
								}
								return Pic;
							} else {
								for (int i = ax; i <= bx; i++) {
									for (int j = ay; j <= by; j++) {
										Pic._pixel_array[i][j] = Pic._pixel_array[i][j].blend(p, factor);
									}
								}
								return this;
							}
							
						}
					}
				}
			} 
		}
		throw new IllegalArgumentException("Not in range");
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		if (radius < 0 || p == null) {
			throw new IllegalArgumentException("Not in range");
		}
		Pixel[][] _pixel_array3 = new Pixel[_width2][_height2];
		for (int i = 0; i < _pixel_array2.length; i++) {
			for (int j = 0; j < _pixel_array2[0].length; j++) {
				_pixel_array3[i][j] = _pixel_array2[i][j];
			}
		}
		MutablePixelArrayPicture Pic = new MutablePixelArrayPicture(_pixel_array3);
		for (int i = 0; i <= _width2 - 1; i++) {
			for (int j = 0; j <= _height2 - 1; j++) {
				if (Math.sqrt((i - cx)*(i - cx) + (j - cy) * (j - cy)) <= radius) {
					Pic._pixel_array[i][j] = p;
				}
			}
		}
		return Pic;
		
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius < 0 || p == null) {
			throw new IllegalArgumentException("Not in range");
		}
		Pixel[][] _pixel_array3 = new Pixel[_width2][_height2];
		for (int i = 0; i < _pixel_array2.length; i++) {
			for (int j = 0; j < _pixel_array2[0].length; j++) {
				_pixel_array3[i][j] = _pixel_array2[i][j];
			}
		}
		MutablePixelArrayPicture Pic = new MutablePixelArrayPicture(_pixel_array3);
		for (int i = 0; i <= _width2 - 1; i++) {
			for (int j = 0; j <= _height2 - 1; j++) {
				if (Math.sqrt((i - cx)*(i - cx) + (j - cy) * (j - cy)) <= radius) {
					Pic._pixel_array[i][j] = Pic._pixel_array[i][j].blend(p, factor);
				}
			}
		}
		return this;
	}
			
}