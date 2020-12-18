package a4;

public class VerticalStackPicture implements Picture {
	
	protected Pixel[][] _pixel_array;
	protected int _width;
	protected int _height;
	
	public VerticalStackPicture(Picture top, Picture bottom) {
		if (top == null || bottom == null || top.getWidth() != bottom.getWidth() || top.getWidth() <= 0 || top.getHeight() <= 0 || bottom.getWidth() <= 0 || bottom.getHeight() <= 0) {
			throw new IllegalArgumentException("null or invalid input");
		}
		_width = top.getWidth();
		_height = top.getHeight() + bottom.getHeight();
		_pixel_array = new Pixel[_width][_height];
		for (int i = 0; i < top.getWidth(); i++) {
			int height_counter = 0;
			for (int j = 0; j < top.getHeight(); j++) {
				_pixel_array[i][height_counter] = top.getPixel(i, j);
				height_counter++;
			}
			for (int k = 0; k < bottom.getHeight(); k++) {
				_pixel_array[i][height_counter] = bottom.getPixel(i, k);
				height_counter++;
			}
			
		}

	}
	
	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	//find out how to make following default methods
	public Pixel getPixel(int x, int y) {
		MutablePixelArrayPicture it = new MutablePixelArrayPicture(_pixel_array);
		return it.getPixel(x, y);
	}
	
	public Picture paint(int x, int y, Pixel p) {
		MutablePixelArrayPicture it = new MutablePixelArrayPicture(_pixel_array);
		it.paint(x,  y,  p);
		this._pixel_array = it._pixel_array;
		return this;
	}
	
	public Picture paint(int x, int y, Pixel p, double factor) {
		
		MutablePixelArrayPicture it = new MutablePixelArrayPicture(_pixel_array);
		it.paint(x, y, p, factor);;
		this._pixel_array = it._pixel_array;
		return this;
	}
	//didn't test for the following so didn't implement correctly
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