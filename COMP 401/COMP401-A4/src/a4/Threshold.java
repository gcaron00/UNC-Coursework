package a4;

public class Threshold implements PixelTransformation {
	
	private double _threshold;
	
	public Threshold(double threshold) {
		_threshold = threshold;
	}
	
	//white all 1s black all 0s
	public Pixel transform(Pixel p) {
		if (p.getIntensity() > _threshold) {
			return p.lighten(1);
		} else {
			return p.darken(1);
		}
		
	}
}