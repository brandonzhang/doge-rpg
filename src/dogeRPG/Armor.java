/* Armor.java last updated 15122013
 * Creates an Armor object, essentially the same as the Weapon object, which accounts for defense.
 * 15122013 added enhance(), price
 */
package dogeRPG;

public class Armor {
	private String name;
	private int basepower, plus, totalpower, price;
	
	//constructor
	public Armor(String n, int b, int p, int index) { //e.g. wow +5
		name = n;
		basepower = b;
		plus = p;
		totalpower = b + p;
		price = (int) Math.pow(3, index + 1);
	}
	
	//accessors
	public String returnName() {
		return name;
	}
	public int returnBasePower() {
		return basepower;
	}
	public int returnPlus() {
		return plus;
	}
	public int returnTotalPower() {
		return totalpower;
	}
	public int returnPrice() {
		return price;
	}
	
	//mutator
	public void enhance() {
		plus++;
	}
}
