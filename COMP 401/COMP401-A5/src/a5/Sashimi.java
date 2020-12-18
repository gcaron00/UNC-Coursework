package a5;

public class Sashimi implements Sushi {
	private String _name;
	private IngredientPortion[] _ings;
	
	public enum SashimiType {TUNA, YELLOWTAIL, EEL, CRAB, SHRIMP};
	
	public Sashimi(SashimiType type) {
		if (type == null) {
			throw new IllegalArgumentException("can't do that");
		}
		_ings = new IngredientPortion[1];
		switch (type) {
			case TUNA:
				// could do this.setFields(name, (new ingredientportion array) ings);
				_name = "tuna sashimi";
				_ings[0] = new TunaPortion(0.75);
				break;
			case YELLOWTAIL:
				_name = "yellowtail sashimi";
				_ings[0] = new YellowtailPortion(0.75);
				break;
			case EEL:
				_name = "eel sashimi";
				_ings[0] = new EelPortion(0.75);
				break;
			case CRAB:
				_name = "crab sashimi";
				_ings[0] = new CrabPortion(0.75);
				break;
			case SHRIMP:
				_name = "shrimp sashimi";
				_ings[0] = new ShrimpPortion(0.75);
				break;
				
		}
	}
	public String getName() {
		return _name;
	}

	public IngredientPortion[] getIngredients() {
		return _ings.clone();
	}

	public int getCalories() {
		double cals = 0;
		for (int i = 0; i < _ings.length; i++) {
			cals = cals + _ings[i].getCalories();
		}
		int calsint = (int) Math.round(cals);
		return calsint;
	}

	public double getCost() {
		double cost = 0;
		for (int i = 0; i < _ings.length; i++) {
			cost = cost + _ings[i].getCost();
		}
		return cost;
	}

	public boolean getHasRice() {
		for (int i = 0; i < _ings.length; i++) {
			if (_ings[i].getIsRice()) {
				return true;
			}
		} 
		return false;
		
	}

	public boolean getHasShellfish() {
		for (int i = 0; i < _ings.length; i++) {
			if (_ings[i].getIsShellfish()) {
				return true;
			}
		} 
		return false;
	}

	public boolean getIsVegetarian() {
		for (int i = 0; i < _ings.length; i++) {
			if (_ings[i].getIsVegetarian() == false) {
				return false;
			}
		} 
		return true;
	}
}