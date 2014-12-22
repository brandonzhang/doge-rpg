/* DogeRPG.java last updated 15122013
 * Finally, the runtime for the Doge RPG.
 */

package dogeRPG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;

import dogeRPG.Armor;
import dogeRPG.Battle;
import dogeRPG.Doge;
import dogeRPG.Enemy;
import dogeRPG.Weapon;
import dogeRPG.Shop;

public class DogeRPG {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Random random = new Random();
		
		char input = 'z';
		
		while (Battle.playing == true) {
			System.out.println("             wow\n\n\n                                                         such rpg\n\n\n                          starring doge\n\n                                   wow");
			System.out.println("Press any key to begin.");
			
			try {
				input = (char) br.read();
			} catch (IOException e) {
				System.out.println("I/O error.");
			}
			
			Doge doge = new Doge();
			
			while (doge.returnHP() > 0) {
				Enemy enemy = new Enemy("anti-doge", doge.returnLevel(),
					new Weapon("anti-paw", doge.returnLevel() + random.nextInt(doge.returnLevel() + 2 / 3), random.nextInt(doge.returnLevel() + 2 / 3), -1),
					new Armor("anti-fur", doge.returnLevel() + random.nextInt(doge.returnLevel() + 2 / 3), random.nextInt(doge.returnLevel() + 2 / 3), -1));
				
				Battle.doBattle(doge, enemy);
				
				if (Battle.playing == false) {
					System.out.println("Would you like to try again? Y/N");
					boolean validInput = false;
					
					while (validInput == false) {
						
						try {
							input = (char) br.read();
						} catch (IOException e) {
							System.out.println("I/O error.");
						}
						
						if (input == 'y' || input == 'Y') {
							validInput = true;
							Battle.playing = true;
							Shop.armorIndex = Shop.weaponIndex = 0;
						} 
						
						else if (input == 'n' || input == 'N') {
							validInput = true;
						}
						
						else if (input != '\n' && input != '\r') {
							System.out.println("Invalid input, try again");
						}
					}					
					break;
				}
				
				Shop.doShop(doge);
			}
		}		
	}
}
