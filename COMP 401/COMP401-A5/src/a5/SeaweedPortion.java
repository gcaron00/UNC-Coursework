package a5;

public class SeaweedPortion extends PortionImpl implements IngredientPortion {
	
	public SeaweedPortion(double amount) {
		super(amount);
		Ingredient ing = new Seaweed();
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