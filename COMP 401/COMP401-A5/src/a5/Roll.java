package a5;

public class Roll implements Sushi {
	private String _name;
	private IngredientPortion[] _ings;
	
	public Roll (String name, IngredientPortion[] roll_ingredients) {
		if (roll_ingredients == null) {
			throw new IllegalArgumentException("you can't do that");
		} 
		for (int i = 0; i < roll_ingredients.length; i++) {
			if (roll_ingredients[i] == null) {
				throw new IllegalArgumentException("you can't do that");
			}
		}
		int lengthcounter = 1;
		for (int i = 1; i < roll_ingredients.length; i++) {
			int indicator2 = 0;
			for (int j = 0; j < i; j++) {
				if (roll_ingredients[i].getName() == roll_ingredients[j].getName()) {
					j = i;
					indicator2 = 1;
				}
			}
			if (indicator2 == 0) {
				lengthcounter++;
			}
		}
		
		IngredientPortion[] holder = new IngredientPortion[lengthcounter];
		int counter = 1;
		holder[0] = roll_ingredients[0];
		for (int i = 1; i < roll_ingredients.length; i++) {
			int indicator = 0;
			for (int j = 0; j < i; j++) {
				if (roll_ingredients[i].getName() == holder[j].getName()) {
					holder[j].combine(roll_ingredients[i]);
					indicator = 1;
					j = i;
				}
			}
			if (indicator == 0) {
				holder[counter] = roll_ingredients[i];
				counter++;
			}
		}
		IngredientPortion[] holder2 = new IngredientPortion[lengthcounter + 1];
		for (int i = 0; i < holder.length; i++) {
			if (holder[i].getIngredient().getName() == "seaweed") {
				if (holder[i].getAmount() >= 0.1) {
					_ings = holder.clone();
					i = holder.length;
				} else {
					holder[i].setAmount(0.1 - holder[i].getAmount());
					_ings = holder.clone();
					i = holder.length;
				}
			} else {
				if (i == holder.length - 1) {
					for (int j = 0; j < lengthcounter; j++) {
						holder2[j] = holder[j];
					}
					holder2[lengthcounter] = new SeaweedPortion(0.1);
					_ings = holder2.clone();
				}
			}
		}
		
		_name = name;
		//_ings = holder.clone();
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
		return Math.round(cost * 100.0) / 100.0;
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
