package a5;

public class EelPortion extends PortionImpl implements IngredientPortion {
	
	public EelPortion(double amount) {
		super(amount);
		Ingredient ing = new Eel();
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