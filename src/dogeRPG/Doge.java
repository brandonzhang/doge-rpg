/* Doge.java Last updated 15122013
 * Creates the player character for the Doge RPG with its stats rolled, levels it up, deals with skillpoint assignment, and deals with EXP and gold.
 * 07122013: fixed bug with input and levelUp(), steepened neededEXP curve
 * 09122013: added check for HP > HPMAX in usePotion(int n)
 * 11122013: added defeated and check for negative HP
 * 14122013: added potionsOwned and tweaked usePotion(), added printStats()
 * 15122013: added owned Weapon and Armor and check for gold < 0
 */
package dogeRPG;

import java.io.BufferedReader; //reading from InputStreamReader
import java.io.IOException;
import java.io.InputStreamReader; //reading from keyboard
import java.util.Random;

import dogeRPG.Armor;
import dogeRPG.Weapon;

public class Doge { 
	private int HP, HPMAX, STR, CON, DEX, INT, CHA, skillpoints, level, exp, gold, neededEXP, potionsOwned;
	private Armor armor;
	private Weapon weapon;
	
	//default constructor, rolls stats
	public Doge() {
		boolean isOK = false;
		Random random = new Random(); //creates a Random object so that the reference is not static
		
		skillpoints = 0;
		level = 1;
		exp = 0;
		gold = 1;
		neededEXP = 8;
		potionsOwned = 1;
		
		armor = new Armor("your fur", 1, random.nextInt(2), -1); //armor owned
		weapon = new Weapon("your paw", 1, random.nextInt(2), -1);
		System.out.println("Rolling for stats...");
		
		while (isOK == false) { //until user accepts stats
			HP = HPMAX = random.nextInt(6) + 15; //random int between 15 and 20
			STR = random.nextInt(6) + 7; //random int between 7 and 12
			CON = random.nextInt(6) + 7;
			DEX = random.nextInt(6) + 7;
			INT = random.nextInt(6) + 7;
			CHA = random.nextInt(6) + 7;
			
			printStats();
			System.out.println("Is this ok? Y/N");
			
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); //reads text
			char yn = 'a'; //contains Y/N input
				
			while (yn != 'y' & yn != 'Y' & yn != 'n' & yn != 'N') {
				
				try {
					yn = (char) keyboard.read(); //gets newest char
				} catch (IOException e) {
					System.out.println("I/O error");
				}
				
				if (yn == 'y' | yn == 'Y') {
					isOK = true; //exits loop as stats are OK
				}					
				else if (yn == 'n' | yn == 'N') {
					System.out.println("Roll again");
				}
				else if (yn == '\r' | yn == '\n') { } //to account for carriage return when user enters data
				else {
					System.out.println("Invalid input, try again");
				}
			}
		}
	}
	
	public void levelUp() {
		level++;
		HPMAX += 2;
		HP = HPMAX;
		STR++;
		CON++;
		DEX++;
		INT++;
		CHA++;
		skillpoints += 2;
		neededEXP = (int) ((5 * Math.pow(level, 2)) / 4 + 9.5);
		
		System.out.println("Level up! You are now level " + level);
		printStats();
		System.out.println("You have " + skillpoints + " skillpoints.\nPress 1 to add HP, 2 to add STR, 3 to add CON, 4 to add DEX, 5 to add INT, and 6 to add CHA");
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); //reads text
		char point = 'a'; //stores data for reading
		
		while (skillpoints > 0) {
			
			try {
				point = (char) keyboard.read(); //gets newest int
			} catch (IOException e) {
				System.out.println("I/O error");
			}
			
			if (point == '1') {
				incrementHP();
				printStats();
				skillpoints--;
			}
			else if (point == '2') {
				incrementSTR();
				printStats();
				skillpoints--;
			}
			else if (point == '3') {
				incrementCON();
				printStats();
				skillpoints--;
			}
			else if (point == '4') {
				incrementDEX();
				printStats();
				skillpoints--;
			}
			else if (point == '5') {
				incrementINT();
				printStats();
				skillpoints--;
			}
			else if (point == '6') {
				incrementCHA();
				printStats();
				skillpoints--;
			}
			
			else if (point == '\n' | point == '\r') { } //account for carriage return/newline when user presses enter
			
			else {
				System.out.println("Invalid input, try again");
			}
		}
	}
	
	//used for skill points
	public void incrementHP() {
		HPMAX++;
		HP++;
	}
	public void incrementSTR() {
		STR++;
	}
	public void incrementCON() {
		CON++;
	}
	public void incrementDEX() {
		DEX++;
	}
	public void incrementINT() {
		INT++;
	}
	public void incrementCHA() {
		CHA++;
	}
	
	//accessors
	public int returnHPMAX() {
		return HPMAX;
	}
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
	public int returnCHA() {
		return CHA;
	}
	public int returnLevel() {
		return level;
	}
	public int returnEXP() {
		return exp;
	}
	public int returnNeededEXP() {
		return neededEXP;
	}
	public int returnGold() {
		return gold;
	}
	public int returnPotions() {
		return potionsOwned;
	}
	
	//mutators
	public int addEXP(int n) {
		exp += n;
		neededEXP -= n;
		if (neededEXP <= 0) {
			levelUp();
		}
		return exp;
	}
	public int addGold(int n) {
		gold += n;
		return gold;
	}
	public boolean spendGold(int n) {
		gold -= n;
		if (gold < 0) {
			System.out.println("Not enough money!");
			gold += n;
			return false;
		}
		return true;
	}
	public int takeDamage(int n) {
		HP -= n;
		if (HP <= 0) {
			HP = 0;
			return 0;
		}
		return HP;
	}
	public void usePotion() {
		if (potionsOwned <= 0) {
			System.out.println("You have no potions!");
			return;
		}
		
		int oldHP = HP;
		
		HP += 20;
		potionsOwned--;
		
		if (HP > HPMAX) {
			HP = HPMAX;
			System.out.println("You used a potion, gaining " + (HP - oldHP) + " HP.");
			return;
		}
		System.out.println("You used a potion, gaining 20 HP.");
	}
	
	public void addPotion() {
		potionsOwned++;
	}
	
	public void printStats() {
		System.out.println("Max HP: " + HPMAX + "\nHP: " + HP + "\nSTR: " + STR + "\nCON: " + CON + "\nDEX: " + DEX + "\nINT: " + INT + "\nCHA: " + CHA);
		System.out.println("Armor: " + getArmor().returnName() + " +" + getArmor().returnPlus() + ", base power " + getArmor().returnBasePower());
		System.out.println("Weapon: " + getWeapon().returnName() + " +" + getWeapon().returnPlus() + ", base power " + getWeapon().returnBasePower());
	}
	//modifying weapon and armor
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Armor getArmor() {
		return armor;
	}
	public void setArmor(Armor armor) {
		this.armor = armor;
	}
}