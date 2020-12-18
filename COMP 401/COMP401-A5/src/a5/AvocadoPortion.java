package a5;

public class AvocadoPortion extends PortionImpl implements IngredientPortion {
	
	public AvocadoPortion(double amount) {
		super(amount);
		Ingredient ing = new Avocado();
		this.setIng(ing);
	}
	
	public IngredientPortion combine(IngredientPortion other) {
		if (this.getName() == other.getName()) {
			IngredientPortion added = new AvocadoPortion(this.getAmount() + other.getAmount());
			//this.setAmount(other.getAmount());
			return added;
		} else {
			throw new IllegalArgumentException("can't do that");
		}
		
	}
}