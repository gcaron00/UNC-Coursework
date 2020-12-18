package a4;

public class MutablePixelArrayPicture implements Picture {
	
	protected Pixel[][] _pixel_array;
	protected int _width;
	protected int _height;
	
	
	public MutablePixelArrayPicture(Pixel[][] pixel_array) {
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
		_width = pixel_array.length;
		_height = pixel_array[0].length;
		_pixel_array = pixel_array;
			
		
	}
	
	public MutablePixelArrayPicture(int width, int height) {
		this(width, height, new GrayPixel(0.5));
	}
		
	
	public MutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException("not in bounds");
		}
		_width = width;
		_height = height;
		_pixel_array = new Pixel[width][height];
		if (initial_value == null) {
			throw new IllegalArgumentException("other is null");
		} else {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					_pixel_array[i][j] = initial_value;
				}
			}
		}
			
	}
	
	
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public Pixel getPixel(int x, int y) {
		if (x < getWidth() && x >= 0) {
			if (y < getHeight() && y >= 0) {
				return _pixel_array[x][y];
			} 
		}
		throw new IllegalArgumentException("Not in range");
	}
	
	public Picture paint(int x, int y, Pixel p) {
		if (p == null) {
			throw new IllegalArgumentException("other is null");
		}
		if (x < getWidth() && x >= 0) {
			if (y < getHeight() && y >= 0) {
				
				_pixel_array[x][y] = p;
				return this;
				
			} 
		}
		throw new IllegalArgumentException("Not in range");
	}
	
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < getWidth() && x >= 0) {
			if (y < getHeight() && y >= 0) {
				
				_pixel_array[x][y] = _pixel_array[x][y].blend(p, factor);
				return this;
				
			} 
		}
		throw new IllegalArgumentException("Not in range");
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		if (ax < getWidth() && ax >= 0 && p != null) {
			if (bx < getWidth() && bx >= 0) {
				if (ay < getHeight() && ay >= 0) {
					if (by < getWidth() && by >= 0) {
						if (ax >= bx) {
							if (ay >= by) {
								for (int i = bx; i <= ax; i++) {
									for (int j = by; j <= ay; j++) {
										_pixel_array[i][j] = p;
									}
								}
								return this;
							} else {
								for (int i = bx; i <= ax; i++) {
									for (int j = ay; j <= by; j++) {
										_pixel_array[i][j] = p;
									}
								}
								return this;
							}
						} else {
							if (ay > by) {
								for (int i = ax; i <= bx; i++) {
									for (int j = by; j <= ay; j++) {
										_pixel_array[i][j] = p;
									}
								}
								return this;
							} else {
								for (int i = ax; i <= bx; i++) {
									for (int j = ay; j <= by; j++) {
										_pixel_array[i][j] = p;
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
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		if (ax <= (getWidth() - 1) && ax >= 0 && p != null) {
			if (bx <= (getWidth() - 1) && bx >= 0) {
				if (ay <= (getHeight() - 1) && ay >= 0) {
					if (by <= (getWidth() - 1) && by >= 0) {
						if (ax >= bx) {
							if (ay >= by) {
								for (int i = bx; i <= ax; i++) {
									for (int j = by; j <= ay; j++) {
										_pixel_array[i][j] = _pixel_array[i][j].blend(p, factor);
									}
								}
								return this;
							} else {
								for (int i = bx; i <= ax; i++) {
									for (int j = ay; j <= by; j++) {
										_pixel_array[i][j] = _pixel_array[i][j].blend(p, factor);
									}
								}
								return this;
							}
						} else {
							if (ay > by) {
								for (int i = ax; i <= bx; i++) {
									for (int j = by; j <= ay; j++) {
										_pixel_array[i][j] = _pixel_array[i][j].blend(p, factor);
									}
								}
								return this;
							} else {
								for (int i = ax; i <= bx; i++) {
									for (int j = ay; j <= by; j++) {
										_pixel_array[i][j] = _pixel_array[i][j].blend(p, factor);
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
		for (int i = 0; i <= _width - 1; i++) {
			for (int j = 0; j <= _height - 1; j++) {
				if (Math.sqrt((i - cx)*(i - cx) + (j - cy) * (j - cy)) <= radius) {
					_pixel_array[i][j] = p;
				}
			}
		}
		return this;
		
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (radius < 0 || p == null) {
			throw new IllegalArgumentException("Not in range");
		}
		for (int i = 0; i <= _width - 1; i++) {
			for (int j = 0; j <= _height - 1; j++) {
				if (Math.sqrt((i - cx)*(i - cx) + (j - cy) * (j - cy)) <= radius) {
					_pixel_array[i][j] = _pixel_array[i][j].blend(p, factor);
				}
			}
		}
		return this;
	}
}