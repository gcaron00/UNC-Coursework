package a4;

public class MonochromePicture implements Picture {
	
	private Pixel _value;
	private int _width;
	private int _height;
	
	public MonochromePicture(int width, int height, Pixel value) {
		if (width <= 0 || height <= 0 || value ==null) {
			throw new IllegalArgumentException("illegal input");
		}
		_width = width;
		_height = height;
		_value = value;
	}

	public int getWidth() {
		return _width;
	}
	
	public int getHeight() {
		return _height;
	}
	
	public Pixel getPixel(int x, int y) {
		Picture it = new MutablePixelArrayPicture(_width, _height, _value);
		return it.getPixel(x, y);
	}
	
	public Picture paint(int x, int y, Pixel p) {
		Picture it = new MutablePixelArrayPicture(_width, _height, _value);
		return it.paint(x, y, p);
	}
	
	public Picture paint(int x, int y, Pixel p, double factor) {
		Picture it = new MutablePixelArrayPicture(_width, _height, _value);
		return it.paint(x, y, p, factor);
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		Picture it = new MutablePixelArrayPicture(_width, _height, _value);
		return it.paint(ax, ay, bx, by, p);
	}
	
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		Picture it = new MutablePixelArrayPicture(_width, _height, _value);
		return it.paint(ax, ay, bx, by, p, factor);
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		Picture it = new MutablePixelArrayPicture(_width, _height, _value);
		return it.paint(cx, cy, radius, p);
	}
	
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		Picture it = new MutablePixelArrayPicture(_width, _height, _value);
		return it.paint(cx, cy, radius, p, factor);
	}
}