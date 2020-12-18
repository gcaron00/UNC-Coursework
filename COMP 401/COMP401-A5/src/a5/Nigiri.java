package a5;



public class Nigiri implements Sushi {
	private String _name;
	private IngredientPortion[] _ings;
	
	public enum NigiriType {TUNA, YELLOWTAIL, EEL, CRAB, SHRIMP};
	
	public Nigiri(NigiriType type) {
		if (type == null) {
			throw new IllegalArgumentException("can't do that");
		}
		_ings = new IngredientPortion[2];
		switch (type) {
			case TUNA:
				// could do this.setFields(name, (new ingredientportion array) ings);
				_name = "tuna nigiri";
				_ings[0] = new TunaPortion(0.75);
				_ings[1] = new RicePortion(0.50);
				break;
			case YELLOWTAIL:
				_name = "yellowtail nigiri";
				_ings[0] = new YellowtailPortion(0.75);
				_ings[1] = new RicePortion(0.50);
				break;
			case EEL:
				_name = "eel nigiri";
				_ings[0] = new EelPortion(0.75);
				_ings[1] = new RicePortion(0.50);
				break;
			case CRAB:
				_name = "crab nigiri";
				_ings[0] = new CrabPortion(0.75);
				_ings[1] = new RicePortion(0.50);
				break;
			case SHRIMP:
				_name = "shrimp nigiri";
				_ings[0] = new ShrimpPortion(0.75);
				_ings[1] = new RicePortion(0.50);
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