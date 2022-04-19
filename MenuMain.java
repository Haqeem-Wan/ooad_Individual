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
	public static ArrayList<DiscountVoucher> buildDiscountVoucherList() {
		ArrayList<DiscountVoucher> discountVoucherList = new ArrayList<DiscountVoucher>();
		String name, voucherCode;
		double discount;

		try {
			File discountVoucherFile = new File("discountVoucherFile.txt");
			Scanner scanFile = new Scanner(discountVoucherFile);
			scanFile.useDelimiter(",|\\r\\n");

			while (scanFile.hasNextLine()) {
				name = scanFile.next();
				voucherCode = scanFile.next();
				discount = Double.parseDouble(scanFile.next());

				discountVoucherList.add(new DiscountVoucher.DiscountVoucherBuilder(name,voucherCode,discount).build());
			}

			scanFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return discountVoucherList;
	}

	public static ArrayList<BundleItem> buildBundleItemList(ArrayList<MenuItem> menuItemList) {
		ArrayList<BundleItem> bundleItemList = new ArrayList<BundleItem>();
		String[] menuItemNames;
		String name;
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		double discount;

		try {
			File bundleItemFile = new File("menuBundleFile.txt");
			Scanner scanFile = new Scanner(bundleItemFile);
			scanFile.useDelimiter(",|\\r\\n");
			
			while (scanFile.hasNextLine()) {
				name = scanFile.next();
				menuItemNames = scanFile.next().split("[||]");
				discount = Double.parseDouble(scanFile.next());

				for(int i = 0; i < menuItemNames.length; i++) {
					for(int j = 0; j < menuItemList.size(); j++) {
						if(menuItemNames[i].equals(menuItemList.get(j).getName())) {
							menuItems.add(menuItemList.get(j));
							break;
						}
					}
				}

				bundleItemList.add(new BundleItem.BundleItemBuilder(name, menuItems, discount).build()
				);
				menuItems.clear();
			}

			scanFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return bundleItemList;
	}

	public static ArrayList<MenuItem> buildMenuItemList() {
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
		ArrayList<MenuItem> menuItemList = buildMenuItemList();
		ArrayList<BundleItem> bundleItemList = buildBundleItemList(menuItemList);
		ArrayList<DiscountVoucher> discountVoucherList = buildDiscountVoucherList();

		System.out.println(bundleItemList.get(0).getFinalPrice());
		System.out.println(bundleItemList.get(1).getFinalPrice());
		System.out.println(discountVoucherList.get(1).getName());
	}

	public static void main(String[] args) {
		buildMenu();
	}
}