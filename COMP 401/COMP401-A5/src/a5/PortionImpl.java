package a5;

public class PortionImpl implements IngredientPortion {
	protected double _amount;
	protected Ingredient _ing;
	
	public PortionImpl(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("can't do that");
		}
		_amount = amount;
	}
	
	public void setIng(Ingredient ing) {
		_ing = ing;
	}
	
	
	public Ingredient getIngredient() {
		return _ing;
	}

	
	public double getAmount() {
		return _amount;
	}
	
	public void setAmount(double otheramount) {
		_amount = _amount + otheramount;
	}

	
	public String getName() {
		return _ing.getName();
	}

	
	public boolean getIsVegetarian() {
		return _ing.getIsVegetarian();
	}

	
	public boolean getIsRice() {
		return _ing.getIsRice();
	}

	
	public boolean getIsShellfish() {
		return _ing.getIsShellfish();
	}

	
	public double getCalories() {
		return _ing.getCaloriesPerOunce() * _amount;
	}

	
	public double getCost() {
		return _ing.getPricePerOunce() * _amount;
	}

	
	public IngredientPortion combine(IngredientPortion other) {
		if (this.getIngredient() != other.getIngredient()) {
			throw new IllegalArgumentException("can't do that");
		}
		this.setAmount(other.getAmount());
		return this;
	}
	
}