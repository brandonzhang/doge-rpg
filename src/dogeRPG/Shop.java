package dogeRPG;

import java.io.BufferedReader; //reading from InputStreamReader
import java.io.IOException;
import java.io.InputStreamReader; //reading from keyboard
import dogeRPG.Armor;
import dogeRPG.Weapon;
import java.util.Random;

public class Shop {
	
	static int armorIndex = 0, weaponIndex = 0;
	
	public static void doShop(Doge doge) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //reads text
		Random random = new Random(); //non-static reference to nextInt(int n)
		
		char input = 'a';
		boolean inShop = true; //for exiting shop

		Armor[] armorArray = { //array of stuff to be bought
			new Armor("some leather", 3, random.nextInt(2), 0),
			new Armor("a wooden shield", 5, random.nextInt(2), 1),
			new Armor("some plating", 8, random.nextInt(3), 2),
			new Armor("cast iron armor", 13, random.nextInt(4), 3),
			new Armor("diamond armor", 21, random.nextInt(6), 4),
			new Armor("a titanium-gold alloy suit", 34, random.nextInt(9), 5),
			new Armor("mithril chainmail", 55, random.nextInt(14), 6),
			new Armor("unobtainium armor", 89, random.nextInt(22), 7),
			new Armor("adamantium armor", 144, random.nextInt(35), 8),
		};
		
		Weapon[] weaponArray = {
			new Weapon("a toy lightsaber", 3, random.nextInt(2), 0),
			new Weapon("a large rock", 5, random.nextInt(2), 1),
			new Weapon("a baseball bat", 8, random.nextInt(3), 2),
			new Weapon("an iron sword", 13, random.nextInt(4), 3),
			new Weapon("a falchion", 21, random.nextInt(6), 4),
			new Weapon("a diamond sword", 34, random.nextInt(9), 5),
			new Weapon("a real lightsaber", 55, random.nextInt(14), 6),
			new Weapon("Andúril", 89, random.nextInt(22), 7),
			new Weapon("the Unbreakable Katana", 144, random.nextInt(35), 8),
		};
		
		System.out.println("\nThe Shop\n");
		
		while (inShop == true) {
			if (input != '\n' && input != '\r') { //only prints when non-newline characters are fed to the BufferedReader
				
				System.out.println("You have " + doge.returnGold() + " gold.");
				
				if (armorIndex == -1 && weaponIndex != -1) { //no more armor
					System.out.println("\nPress 2 to buy " + weaponArray[weaponIndex].returnName() +
							" for " + (int) (weaponArray[weaponIndex].returnPrice() / Math.pow(1.02, doge.returnCHA()) + 0.5) +
							" gold, 3 to buy potion for 5 gold," +
							" 4 to enhance armor for "+ (int) (Math.pow(1.05, doge.getArmor().returnTotalPower()) / Math.pow(1.02, doge.returnCHA()) + 1.5) +
							" gold, 5 to enhance weapon for " + (int) (Math.pow(1.05, doge.getWeapon().returnTotalPower()) / Math.pow(1.02, doge.returnCHA()) + 1.5) + " gold, 6 to exit\n");
				}
				
				else if (weaponIndex == -1 && armorIndex != -1) { //no more weapons
					System.out.println("\nPress 1 to buy " + armorArray[armorIndex].returnName() + 
							" for " + (int) (armorArray[armorIndex].returnPrice() / Math.pow(1.02, doge.returnCHA()) + 0.5) + 
							" gold, 3 to buy potion for 5 gold," +
							" 4 to enhance armor for " + (int) (Math.pow(1.05, doge.getArmor().returnTotalPower()) / Math.pow(1.02, doge.returnCHA()) + 1.5) +
							" gold, 5 to enhance weapon for " + (int) (Math.pow(1.05, doge.getWeapon().returnTotalPower()) / Math.pow(1.02, doge.returnCHA()) + 1.5) + " gold, 6 to exit\n");
				}
				
				else if (weaponIndex == -1 && armorIndex == -1) { //no weapons or armor
					System.out.println("\nPress 3 to buy potion for 5 gold," +
							" 4 to enhance armor for "+ (int) (Math.pow(1.05, doge.getArmor().returnTotalPower()) / Math.pow(1.02, doge.returnCHA()) + 1.5) +
							" gold, 5 to enhance weapon for " + (int) (Math.pow(1.05, doge.getWeapon().returnTotalPower()) / Math.pow(1.02, doge.returnCHA()) + 1.5) + " gold, 6 to exit\n");
				}
				
				else { //weapons and armor available
					System.out.println("\nPress 1 to buy " + armorArray[armorIndex].returnName() + 
							" for " + (int) (armorArray[armorIndex].returnPrice() / Math.pow(1.02, doge.returnCHA()) + 0.5) + 
							" gold, 2 to buy " + weaponArray[weaponIndex].returnName() +
							" for " + (int) (weaponArray[weaponIndex].returnPrice() / Math.pow(1.02, doge.returnCHA()) + 0.5) +
							" gold, 3 to buy potion for 5 gold," +
							" 4 to enhance armor for "+ (int) (Math.pow(1.05, doge.getArmor().returnTotalPower()) / Math.pow(1.02, doge.returnCHA()) + 1.5) +
							" gold, 5 to enhance weapon for " + (int) (Math.pow(1.05, doge.getWeapon().returnTotalPower()) / Math.pow(1.02, doge.returnCHA()) + 1.5) + " gold, 6 to exit\n");
				}
				
				System.out.println("Owned armor: " + doge.getArmor().returnName() + " +" + doge.getArmor().returnPlus() + ", with a base power of " + doge.getArmor().returnBasePower());
				System.out.println("Owned weapon: " + doge.getWeapon().returnName() + " +" + doge.getWeapon().returnPlus() + ", with a base power of " + doge.getWeapon().returnBasePower());
				System.out.println("Owned potions: " + doge.returnPotions());
			}		
			
			try {
				input = (char) br.read();
			} catch (IOException e) {
				System.out.println("I/O error.");
			}
			
			if (input == '1') { //bought armor
				if (armorIndex == -1) { //sentinel value
					System.out.println("No more armor to buy!");
				}
				else if (doge.spendGold((int) (armorArray[armorIndex].returnPrice() / Math.pow(1.02, doge.returnCHA()) + 0.5)) == true) { //if enough gold, gold is spent
					doge.setArmor(armorArray[armorIndex]); //purhcased armor is set
					armorIndex++; //shifts to next armor in armorArray
					if (armorIndex > 8) {
						armorIndex = -1; //no more armor
					}
				}				
			}
			
			else if (input == '2') { //bought weapon
				if (weaponIndex == -1) {
					System.out.println("No more weapons to buy!");
				}
				else if (doge.spendGold((int) (weaponArray[weaponIndex].returnPrice() / Math.pow(1.02, doge.returnCHA()) + 0.5)) == true) { //if enough gold, gold is spent
					doge.setWeapon(weaponArray[weaponIndex]);
					weaponIndex++; //shift to next weapon in weaponArray
					if (weaponIndex > 8) {
						weaponIndex = -1; //no more weapons
					}
				}
			}
			
			else if (input == '3') { //bought potion
				if (doge.spendGold(5) == true) {
					doge.addPotion();
				}
			}
			
			else if (input == '4') { //enhance armor
				if (doge.spendGold((int) (Math.pow(1.05, doge.getArmor().returnTotalPower()) / Math.pow(1.02, doge.returnCHA()) + 1.5)) == true) {
					doge.getArmor().enhance();
				}
			}
			
			else if (input == '5') { //enhance weapon
				if (doge.spendGold((int) (Math.pow(1.05, doge.getWeapon().returnTotalPower()) / Math.pow(1.02, doge.returnCHA()) + 1.5)) == true) {
					doge.getWeapon().enhance();
				}
			}
			else if (input == '6') { //exit shop
				inShop = false;
			}
			
			else if (input == '\n' || input == '\r') { } //newlines and carriage returns are valid input but do nothing
			
			else {
				System.out.println("Invalid input, try again");
			}
		}		
	}
}
