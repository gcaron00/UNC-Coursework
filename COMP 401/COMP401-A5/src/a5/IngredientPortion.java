package a5;

public interface IngredientPortion {

	/* getIngredient()
	 * 
	 * Getter for Ingredient object representing the ingredient
	 * this is object is a portion of.
	 */
	Ingredient getIngredient();

	/* getAmount()
	 * 
	 * Getter for the size (in ounces) of the ingredient portion.
	 */
	double getAmount();
	
	void setAmount(double other);

	/* getName()
	 * getIsVegetarian()
	 * getIsRice()
	 * getIsShellfish()
	 * 
	 * Getters for characteristics of the ingredient.
	 */

	String getName();
	boolean getIsVegetarian();
	boolean getIsRice();
	boolean getIsShellfish();

	
	/*
	 * getCalories()
	 * getCost()
	 * 
	 * Getters for the calorie and price information associated with
	 * this specific portion. This value is not rounded.
	 */
	
	double getCalories();
	double getCost();
	
	/*
	 * combine(IngredientPortion other)
	 * 
	 * If other is null, returns this ingredient portion.
	 * Otherwise...
	 * If Ingredient associated with other is equal
	 * to this ingredient, returns new ingredient portion which
	 * represents the combined amounts of this ingredient portion and
	 * the other ingredient portion. Uses equals() method of the
	 * Ingredient interface to test equality.
	 * 
	 * If other ingredient is not equal to this ingredient, 
	 * throws an IllegalArgumentException().
	 */
	
	IngredientPortion combine(IngredientPortion other);
}
