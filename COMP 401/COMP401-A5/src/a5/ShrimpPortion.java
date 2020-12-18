package a5;

public class ShrimpPortion extends PortionImpl implements IngredientPortion {
	
	public ShrimpPortion(double amount) {
		super(amount);
		Ingredient ing = new Shrimp();
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