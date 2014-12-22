/* CalculateDamage.java updated 14122013
 * Contains methods to calculate damage for enemy and doge, and manages enemy defeat
 * 07122013 added enemy to calculations and enemyDamage, added defeatEnemy
 * 09122013 changed dodging algorithms
 * 11122013 added doBattle()
 * 14122013 added accuracy, tweaked doBattle()
 */
package dogeRPG;
import dogeRPG.Doge;
import java.util.Random;
import java.io.BufferedReader; //reading from InputStreamReader
import java.io.IOException;
import java.io.InputStreamReader; //reading from keyboard

public class Battle {
	
	static boolean playing = true;
	
	public static void dogeDamage(Doge doge, Enemy enemy) {
		
		Random random = new Random(); //must make non-static reference to nextDouble()
		
		double crit = random.nextDouble(); //random double between 0 and 1
		double critChance = 0.03 * (doge.returnDEX() / 6); //crit chance scales linearly, hits 100% at lv 200
		double critDamage = 0.03 * (doge.returnINT() / 2) + 1; //crit damage scales linearly as well, hits 200% at ~ lv 67
		double dodge = random.nextDouble();
		double dodgeChance = 0.04 * (doge.returnDEX() / 8); //dodging
		double accuracy = random.nextDouble();
		double accuracyChance = Math.pow(0.995, 50 - doge.returnINT()); //accuracy
		int damage = 0; //damage inflicted
		
		if (critChance > 0.9) { //crit chance capped at 90%
			critChance = 0.9;
		}
		if (dodgeChance > 0.9) { //dodge chance capped at 90%
			dodgeChance = 0.9;
		}
		
		if (dodge < dodgeChance) { //enemy dodged
			System.out.println("                                                such disappoint\n\n\n     so dodge\n\n                        wow");
			System.out.println("The enemy dodged your attack!");
			System.out.println(enemy.returnName() + "'s HP: " + enemy.returnHP());
			return;
		}
		else if (accuracy > accuracyChance) {
			System.out.println("   so miss\n\n\n                  doge no accurate\n\n                                                       such sad");
			System.out.println("You missed the enemy!");
			System.out.println(enemy.returnName() + "'s HP: " + enemy.returnHP());
			return;
		}
		else if (crit < critChance) { //critical hit scored
			System.out.println("                     wow\n\n                                                   such luck\n\n\n                                     very critical");
			damage = (int) ((double) (doge.getWeapon().returnTotalPower() + (Math.pow(doge.returnSTR(), 2.3)) / 8.0) / (enemy.returnCON() + enemy.getArmor().returnTotalPower()) * critDamage + 1.5); //+0.5 accounts for incorrect integer rounding while casting
			System.out.println("   much damage\n\n                  " + damage + "\n\n\n                                                               wow");
			System.out.println("You critically struck the enemy with " + doge.getWeapon().returnName() + " +" + doge.getWeapon().returnPlus() + ", dealing " + damage + " damage.");
			
			enemy.takeDamage(damage);
			System.out.println(enemy.returnName() + "'s HP: " + enemy.returnHP());
			return;
		}
		
		else {
			damage = (int) ((double) (doge.getWeapon().returnTotalPower() + (Math.pow(doge.returnSTR(), 2.3)) / 8.0) / (enemy.returnCON() + enemy.getArmor().returnTotalPower()) + 1.5);
			System.out.println("   much damage\n\n                                                             " + damage + "\n\n\n                          wow");
			System.out.println("You struck the enemy with " + doge.getWeapon().returnName() + " +" + doge.getWeapon().returnPlus() + ", dealing " + damage + " damage.");
			
			enemy.takeDamage(damage);
			System.out.println(enemy.returnName() + "'s HP: " + enemy.returnHP());
			return;
		}
	}
	public static void enemyDamage(Doge doge, Enemy enemy) {
		
		Random random = new Random(); //must make non-static reference to nextDouble()
		
		double crit = random.nextDouble(); //random double between 0 and 1
		double critChance = 0.03 * (enemy.returnDEX() / 6); //crit chance scales linearly, hits 100% at lv 200
		double critDamage = 0.03 * (enemy.returnINT() / 2) + 1; //crit damage scales linearly as well, hits 200% at ~ lv 67
		double dodge = random.nextDouble();
		double dodgeChance = 0.04 * (doge.returnDEX() / 8); //dodging
		double accuracy = random.nextDouble();
		double accuracyChance = Math.pow(0.995, 50 - enemy.returnINT()); //accuracy
		int damage = 0; //damage inflicted
		
		if (critChance > 0.9) { //crit chance capped at 90%
			critChance = 0.9;
		}
		if (dodgeChance > 0.9) { //dodge chance capped at 90%
			dodgeChance = 0.9;
		}
		
		if (dodge < dodgeChance) { //you dodged
			System.out.println("                                                             doge such ninja\n\n\n     so dodge\n\n                        wow");
			System.out.println("You dodged the enemy's attack!");
			System.out.println("Your HP: " + doge.returnHP());
			return;
		}
		else if (accuracy > accuracyChance) { //enemy missed
			System.out.println("   so miss\n\n\n                                                    wow\n\n                          very luck");
			System.out.println("The enemy missed you!");
			System.out.println("Your HP: " + doge.returnHP());
			return;
		}
		
		else if (crit < critChance) { //critical hit scored
			System.out.println("                     wow\n\n                                                   so pain\n\n\n                                     very critical");
			damage = (int) ((double) (enemy.getWeapon().returnTotalPower() + (Math.pow(enemy.returnSTR(), 2.3)) / 8.0) / (doge.returnCON() + doge.getArmor().returnTotalPower()) * critDamage + 1.5); //+0.5 accounts for incorrect integer rounding while casting
			System.out.println("   much damage\n\n                  " + damage + "\n\n\n                                                               doge very sad");
			System.out.println("The " + enemy.returnName() + " critically struck you with its " + enemy.getWeapon().returnName() + " +" + enemy.getWeapon().returnPlus() + ", dealing " + damage + " damage.");
			
			doge.takeDamage(damage);
			System.out.println("Your HP: " + doge.returnHP());
			return;
		}
		
		else { //normal hit
			damage = (int) ((double) (enemy.getWeapon().returnTotalPower() + (Math.pow(enemy.returnSTR(), 2.3)) / 8.0) / (doge.returnCON() + doge.getArmor().returnTotalPower()) + 1.5);
			System.out.println("   much damage\n\n                                                             " + damage + "\n\n\n                          so pain");
			System.out.println("The " + enemy.returnName() + " struck you with its " + enemy.getWeapon().returnName() + " +" + enemy.getWeapon().returnPlus() + ", dealing " + damage + " damage.");
			
			doge.takeDamage(damage);
			System.out.println("Your HP: " + doge.returnHP());
			return;
		}
	}
	
	public static void defeatEnemy(Doge doge, Enemy enemy) {
		System.out.println("Enemy defeated! You gain " + enemy.returnEXP() + " EXP and " + enemy.returnGold() * (int) (doge.returnCHA() / 10.0 + 0.5) + " gold!\n");
		doge.addEXP(enemy.returnEXP());
		doge.addGold(enemy.returnGold() * (int) (doge.returnCHA() / 10.0 + 0.5));
	}
	
	public static void defeat() {
		System.out.println("You lose!");
		playing = false;
	}
	
	public static void doBattle(Doge doge, Enemy enemy) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char a = 'z';
		System.out.println("Your stats: ");
		doge.printStats();
		System.out.println("\n" + enemy.returnName() + "'s stats: ");
		enemy.printStats();
		System.out.println("Press 1 to attack, 2 to use potion");
		
		while (doge.returnHP() > 0 && enemy.returnHP() > 0) {
			
			try {
				a = (char) br.read();
			} catch (IOException e) {
				System.out.println("I/O error");
			}
			
			if (a == '1') {
				dogeDamage(doge, enemy);
				
				if (enemy.returnHP() == 0) {
					defeatEnemy(doge, enemy);
					return;
				}
				
				else {
					enemyDamage(doge, enemy);
				}
			}
			
			else if (a == '2') {
				doge.usePotion();
				enemyDamage(doge, enemy);
			}
			
			else if (a == '\n' || a == '\r') { }
			
			else {
				System.out.println("Invalid input, try again");
			}
		}
		if (enemy.returnHP() == 0) {
			defeatEnemy(doge, enemy);
			return;
		}
		if (doge.returnHP() == 0) {
			defeat();
			return;
		}
	}
}