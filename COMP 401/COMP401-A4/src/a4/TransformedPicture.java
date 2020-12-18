package a4;

public class TransformedPicture implements Picture {
	
	private PixelTransformation _xform;
	private Picture _source;
	private int _width;
	private int _height;
	
	
	public TransformedPicture(Picture source, PixelTransformation xform) {
		_xform = xform;
		_source = source;
		_width = source.getWidth();
		_height = source.getHeight();
	}

	@Override
	public int getWidth() {
		
		return _width;
	}

	@Override
	public int getHeight() {
		return _height;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		return _xform.transform(_source.getPixel(x, y));
	}

	@Override
	public Picture paint(int x, int y, Pixel p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Picture paint(int x, int y, Pixel p, double factor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}