package a4;

public class GradientPicture implements Picture {

	private Pixel _upper_left;
	private Pixel _upper_right;
	private Pixel _lower_left;
	private Pixel _lower_right;
	private int _width;
	private int _height;
	
	public GradientPicture(int width, int height, Pixel upper_left, Pixel upper_right, Pixel lower_left, Pixel lower_right) {
		if (width <= 0 || height <= 0 || upper_left == null || upper_right == null || lower_left == null || lower_right == null) {
			throw new IllegalArgumentException("illegal input");
		}
		_upper_left = upper_left;
		_upper_right = upper_right;
		_lower_left = lower_left;
		_lower_right = lower_right;
		_width = width;
		_height = height;
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
				Pixel a;
				Pixel b;
				Pixel c;
				double y2 = y;
				double x2 = x;
				if (getHeight() == 1) {
					a = _upper_left;
					b = _upper_right;
				} else {
					a = _upper_left.blend(_lower_left, y2/(_height - 1));
					b = _upper_right.blend(_lower_right, y2/(_height - 1));
				}
				if (getWidth() == 1) {
					c = a;
				} else {
					c = a.blend(b, x2/(_width - 1));
				}
				
				return c;
			} 
		}
		throw new IllegalArgumentException("Not in range");
	}
	
	public Picture paint(int x, int y, Pixel p) {
		Pixel[][] gradientarray = new Pixel[getWidth()][getHeight()];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				gradientarray[i][j] = getPixel(i, j);
			}
		}
		Picture it = new MutablePixelArrayPicture(gradientarray);
		return it.paint(x, y, p);
	}
	
	public Picture paint(int x, int y, Pixel p, double factor) {
		Pixel[][] gradientarray = new Pixel[getWidth()][getHeight()];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				gradientarray[i][j] = getPixel(i, j);
			}
		}
		Picture it = new MutablePixelArrayPicture(gradientarray);
		return it.paint(x, y, p, factor);
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		Pixel[][] gradientarray = new Pixel[getWidth()][getHeight()];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				gradientarray[i][j] = getPixel(i, j);
			}
		}
		Picture it = new MutablePixelArrayPicture(gradientarray);
		return it.paint(ax, ay, bx, by, p);
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		Pixel[][] gradientarray = new Pixel[getWidth()][getHeight()];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				gradientarray[i][j] = getPixel(i, j);
			}
		}
		Picture it = new MutablePixelArrayPicture(gradientarray);
		return it.paint(ax, ay, bx, by, p, factor);
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		Pixel[][] gradientarray = new Pixel[getWidth()][getHeight()];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				gradientarray[i][j] = getPixel(i, j);
			}
		}
		Picture it = new MutablePixelArrayPicture(gradientarray);
		return it.paint(cx, cy, radius, p);
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		Pixel[][] gradientarray = new Pixel[getWidth()][getHeight()];
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				gradientarray[i][j] = getPixel(i, j);
			}
		}
		Picture it = new MutablePixelArrayPicture(gradientarray);
		return it.paint(cx, cy, radius, p, factor);
	}
}
