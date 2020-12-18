package a4;

public class GammaCorrect implements PixelTransformation {
	
	private double _gamma;
	
	public GammaCorrect(double gamma) {
		_gamma = gamma;
	}
	public Pixel transform(Pixel p) {
		return new ColorPixel(Math.pow(p.getRed(), 1.0/_gamma), Math.pow(p.getGreen(), 1.0/_gamma), Math.pow(p.getBlue(), 1.0/_gamma));
	}
}