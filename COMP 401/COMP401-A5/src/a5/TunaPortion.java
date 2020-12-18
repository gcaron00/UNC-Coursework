package a5;

public class TunaPortion extends PortionImpl implements IngredientPortion {
	
	public TunaPortion(double amount) {
		super(amount);
		Ingredient ing = new Tuna();
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