package a5;

public class IngredientImpl implements Ingredient {
	private String _name;
	private double _ppo;
	private int _cpo;
	private boolean _veg;
	private boolean _rice;
	private boolean _sf;
	
	public IngredientImpl(String name, double ppo, int cpo, boolean veg, boolean rice, boolean sf) {
		_name = name;
		_ppo = ppo;
		_cpo = cpo;
		_veg = veg;
		_rice = rice;
		_sf = sf;
	}
	public String getName() {
		return _name;
	}

	@Override
	public double getCaloriesPerDollar() {
		double cal = _cpo;
		return cal / _ppo;
	}

	@Override
	public int getCaloriesPerOunce() {
		return _cpo;
	}

	@Override
	public double getPricePerOunce() {
		return _ppo;
	}

	@Override
	public boolean getIsVegetarian() {
		return _veg;
	}

	@Override
	public boolean getIsRice() {
		return _rice;
	}

	@Override
	public boolean getIsShellfish() {
		return _sf;
	}

	@Override
	public boolean equals(Ingredient other) {
		if (other == null) {
			return false;
		}
		if (_name == other.getName() && _cpo == other.getCaloriesPerOunce() && _veg == other.getIsVegetarian() && _rice == other.getIsRice() && _sf == other.getIsShellfish()) {
			if ((_ppo - 0.01) <= other.getPricePerOunce() && other.getPricePerOunce() <= (_ppo + 0.01)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}