package a4;

public class HorizontalStackPicture implements Picture {
	
	protected Pixel[][] _pixel_array;
	protected int _width;
	protected int _height;
	
	public HorizontalStackPicture(Picture left, Picture right) {
		
		
		if (left == null || right == null || left.getHeight() != right.getHeight() || left.getWidth() <= 0 || left.getHeight() <= 0 || right.getWidth() <= 0 || right.getHeight() <= 0) {
			throw new IllegalArgumentException("null or invalid input");
		}
		
		_width = left.getWidth() + right.getWidth();
		_height = left.getHeight();
		_pixel_array = new Pixel[_width][_height];
		for (int i = 0; i < left.getHeight(); i++) {
			int width_counter = 0;
			for (int j = 0; j < left.getWidth(); j++) {
				_pixel_array[width_counter][i] = left.getPixel(j, i);
				width_counter++;
			}
			for (int k = 0; k < right.getWidth(); k++) {
				_pixel_array[width_counter][i] = right.getPixel(k, i);
				width_counter++;
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
		MutablePixelArrayPicture it = new MutablePixelArrayPicture(_pixel_array);
		return it.getPixel(x, y);
	}
	
	public Picture paint(int x, int y, Pixel p) {
		MutablePixelArrayPicture it = new MutablePixelArrayPicture(_pixel_array);
		it.paint(x,  y,  p);
		return this;
	}
	public Picture paint(int x, int y, Pixel p, double factor) {
		MutablePixelArrayPicture it = new MutablePixelArrayPicture(_pixel_array);
		it.paint(x, y, p, factor);
		return this;
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		return this;
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		
		return this;
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		
		return this;
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		
		return this;
	}
}