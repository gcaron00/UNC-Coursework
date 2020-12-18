package a5;

public class CrabPortion extends PortionImpl implements IngredientPortion {
	
	public CrabPortion(double amount) {
		super(amount);
		Ingredient ing = new Crab();
		this.setIng(ing);
	}
	
	public IngredientPortion combine(IngredientPortion other) {
		if (this.getIngredient() != other.getIngredient()) {
			throw new IllegalArgumentException("can't do that");
		}
		this.setAmount(other.getAmount());
		return this;
	}
}