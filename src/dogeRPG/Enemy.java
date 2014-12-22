/* Enemy.java updated 07122013
 * Creates enemy for combat with Doge RPG with stats, gold and exp drop.
 * 07122013: steepened stats curve, added exp and gold calc for level
 */
package dogeRPG;

import java.util.Random;

public class Enemy {
	private int HP, HPMAX, STR, CON, DEX, INT, level, exp, gold;
	private String name;
	private Weapon weapon;
	private Armor armor;
	
	//default constructor, uses level to determine stats
	public Enemy(String n, int l, Weapon w, Armor a) {
		
		Random random = new Random(); //creates a Random object so that the reference is not static
		
		level = l;
		HP = HPMAX = (int) ((random.nextInt(3) + level + 1) * 3 + 0.5); //random int between level + 2 and level + 4
		STR = (int) ((random.nextInt(3) + level + 1.5) * 2 + 0.5);
		CON = (int) ((random.nextInt(3) + level + 1.5) * 2 + 0.5);
		DEX = (int) ((random.nextInt(3) + level + 1.5) * 2 + 0.5);
		INT = (int) ((random.nextInt(3) + level + 1.5) * 2 + 0.5);
		name = n;
		exp = (int) ((30.0 * level / 7) + 0.5);
		gold = (int) (Math.pow(level, 2) + level + 3);
		
		weapon = w;
		armor = a;
	}
	
	//accessors
	public int returnHP() {
		return HP;
	}
	public int returnSTR() {
		return STR;
	}
	public int returnCON() {
		return CON;
	}
	public int returnDEX() {
		return DEX;
	}
	public int returnINT() {
		return INT;
	}
	public int returnLevel() {
		return level;
	}
	public String returnName() {
		return name;
	}
	public int returnEXP() {
		return exp;
	}
	public int returnGold() {
		return gold;
	}
	
	public void printStats() {
		System.out.println("Max HP: " + HPMAX + "\nHP: " + HP + "\nSTR: " + STR + "\nCON: " + CON + "\nDEX: " + DEX + "\nINT: " + INT);
		System.out.println("Armor: " + getArmor().returnName() + " +" + getArmor().returnPlus() + ", total power " + getArmor().returnTotalPower());
		System.out.println("Weapon: " + getWeapon().returnName() + " +" + getWeapon().returnPlus() + ", total power " + getWeapon().returnTotalPower());
	}
	
	//mutators
	public int takeDamage(int n) {
		HP -= n;
		if (HP <= 0) {
			HP = 0;
			return 0;
		}
		return HP;
	}
	
	//weapon and armor
	public Weapon getWeapon() {
		return weapon;
	}

	public Armor getArmor() {
		return armor;
	}
}