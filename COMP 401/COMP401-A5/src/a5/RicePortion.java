package a5;

public class RicePortion extends PortionImpl implements IngredientPortion {
	
	public RicePortion(double amount) {
		super(amount);
		Ingredient ing = new Rice();
		this.setIng(ing);
	}
	
	public IngredientPortion combine(IngredientPortion other) {
		if (this.getIngredient().getName() != other.getIngredient().getName()) {
			throw new IllegalArgumentException("can't do that");
		}
		this.setAmount(other.getAmount());
		return this;
	}
}