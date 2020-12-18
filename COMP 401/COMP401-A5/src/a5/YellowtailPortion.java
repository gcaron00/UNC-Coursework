package a5;

public class YellowtailPortion extends PortionImpl implements IngredientPortion {
	
	public YellowtailPortion(double amount) {
		super(amount);
		Ingredient ing = new Yellowtail();
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