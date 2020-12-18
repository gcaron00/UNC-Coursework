package a5;

public interface Sushi {

	/* getName()
	 * 
	 * Getter for name of sushi.
	 */
	String getName();
	
	/* getIngredients()
	 * 
	 * Getter for component ingredients of sushi.
	 */
	IngredientPortion[] getIngredients();
	
	/* getCalories()
	 * 
	 * Getter for sum of caloric content of component
	 * ingredient portions rounded to nearest integer.
	 */
	int getCalories();
	
	/* getCost()
	 * 
	 * Getter for sum of costs of component ingredient
	 * portions rounded to nearest cent.
	 */
	double getCost();
	
	/* getHasRice()
	 * getHasShellfish()
	 * 
	 * Returns true if any of the component ingredients
	 * is rice or shellfish respectively. Otherwise false.
	 * 
	 */
	boolean getHasRice();
	boolean getHasShellfish();

	/* getIsVegetarian()
	 * 
	 * Returns true only if all of the component
	 * ingredients are vegetarian. Otherwise false.
	 */

	boolean getIsVegetarian();
}
