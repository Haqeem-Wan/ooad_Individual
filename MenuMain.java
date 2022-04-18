/*
~~~   General  Information   ~~~

Name            : Wan Nashrul Haqeem Bin Wan Kamal
Student ID      : 1191102618
Faculty         : Faculty of Computer Science and Informatics
Course          : Bahelors Degree of Computer Science (Hons.)
Specialisation  : Cybersecurity

~~~  Assignment Information  ~~~

Design Pattern  : Builder
Restaurant Name : O'Leary's Shake Shack
*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MenuMain {
	public static ArrayList<BundleItem> getBundleItemList(ArrayList<MenuItem> menuItemList) {
		ArrayList<BundleItem> bundleItemList = new ArrayList<BundleItem>();
		List<String> menuItemNames = new ArrayList<String>();
		String name, menuItemName;
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		double discount;

		try {
			File bundleItemFile = new File("bundleItemFile.txt");
			Scanner scanFile = new Scanner(bundleItemFile);
			scanFile.useDelimiter(",|\\r\\n");
			
			while (scanFile.hasNextLine()) {
				name = scanFile.next();
				menuItemName = scanFile.next();
				menuItemNames = Arrays.asList(menuItemName.split("||"));

				for(int i = 0; i < menuItemNames.size(); i++) {
					for(int j = 0; j < menuItemList.size(); j++) {
						if(menuItemNames.get(i).equals(menuItemList.get(j).getName())) {
							menuItems.add(menuItemList.get(j));
							break;
						}
					}
				}

				discount = Double.parseDouble(scanFile.nextLine());

				bundleItemList.add(new BundleItem.BundleItemBuilder(name, menuItems, discount).build());
			}

			scanFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return bundleItemList;
	}

	public static ArrayList<MenuItem> getMenuItemList() {
		ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
		String checkNewCategory, name, foodOrDrink, category;
		double price, discount;

		try {
			File menuItemFile = new File("menuItemFile.txt");
			Scanner scanFile = new Scanner(menuItemFile);
			scanFile.useDelimiter(",|\\r\\n");

			while(scanFile.hasNextLine()) {
				MenuItem newItem;
				checkNewCategory = scanFile.next();

				if(checkNewCategory.equals("//")) {
					continue;
				}

				name = checkNewCategory;
				foodOrDrink = scanFile.next();
				category = scanFile.next();
				price = Double.parseDouble(scanFile.next());
				discount = Double.parseDouble(scanFile.next());

				if(discount == -1) {
					newItem = new MenuItem.MenuItemBuilder(name, foodOrDrink, category, price).build();
				}

				else {
					newItem = new MenuItem.MenuItemBuilder(name, foodOrDrink, category, price).setDiscount(discount).build();
				}

				menuItemList.add(newItem);
			}

			scanFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return menuItemList;
	}

	public static void buildMenu() {
		ArrayList<MenuItem> menuItemList = getMenuItemList();

		System.out.println(menuItemList.get(0).getDiscount());
	}

	public static void main(String[] args) {
		buildMenu();
	}
}