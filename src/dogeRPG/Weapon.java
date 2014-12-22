/* Weapon.java last updated 15122013
 * Creates and modifies a Weapon object, which is used by the Doge and the Enemy
 * 15122013 added enhance(), price
 */
package dogeRPG;

public class Weapon {
	private String name;
	private int basepower, plus, totalpower, price;
	
	//constructor
	public Weapon(String n, int b, int p, int index) { //e.g. wow +5
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