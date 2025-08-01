package deus.bttf.Items;


import deus.bttf.DevTools.Utils.ItemUtils;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import turniplabs.halplibe.helper.ItemBuilder;

import static deus.bttf.BTTFMain.MOD_ID;
import static deus.bttf.BTTFMain.config;


public class BTTFItems {

	public static final ItemBuilder GenericItemBuilder = new ItemBuilder(MOD_ID);

	// ### Pescado Pufferfish
	public static Item FOOD_PUFFERFISH;

	// ### Salmón
	public static Item FOOD_SALMON;
	public static Item FOOD_COOKED_SALMON;

	// ### Pez Tropical
	public static Item FOOD_TROPICAL_FISH;

	// ### Bacalao
	public static Item FOOD_COD;
	public static Item FOOD_COOKED_COD;

	// ### Pez Dorado (Goldfish)
	public static Item FOOD_GOLD_FISH;
	public static Item FOOD_COOKED_GOLD_FISH;

	// ### Sopa de Pescado Cocido
	public static Item FOOD_COOKED_FISH_SOUP;

	public static Item FOOD_BEEF;
	public static Item FOOD_COOKED_BEEF;
	public static Item FOOD_MUTTON;
	public static Item FOOD_COOKED_MUTTON;
	public static Item FOOD_CHICKEN;
	public static Item FOOD_COOKED_CHICKEN;
	public static Item FOOD_RABBIT;
	public static Item FOOD_COOKED_RABBIT;
	public static Item ROTTEN_FLESH;
	public static Item SPIDER_EYE;


	public void Initialize() {

		// ? ## Food

		// Fishes
		FOOD_PUFFERFISH = GenericItemBuilder.build(new ItemHurt("pufferfish", "bttf:item/pufferfish", config.newItemID(), -1, 4, false, 1));
		FOOD_SALMON = makeFood(config.newItemID(), "salmon", 2, 10, false, 16);
		FOOD_COOKED_SALMON = makeFood(config.newItemID(), "cooked_salmon", 6, 10, false, 16);
		FOOD_TROPICAL_FISH = makeFood(config.newItemID(), "tropical_fish", 1, 5, false, 16);
		FOOD_COD = makeFood(config.newItemID(), "cod", 2, 10, false, 16);
		FOOD_COOKED_COD = makeFood(config.newItemID(), "cooked_cod", 5, 8, false, 16);
		FOOD_GOLD_FISH = makeFood(config.newItemID(), "gold_fish", 4, 2, false, 16);
		FOOD_COOKED_GOLD_FISH = makeFood(config.newItemID(), "cooked_gold_fish", 8, 1, false, 16);
		FOOD_COOKED_FISH_SOUP = makeFood(config.newItemID(), "cooked_fish_soup", 6, 15, false, 1);

		// BEEF
		FOOD_BEEF = makeFood(config.newItemID(), "beef", 4, 13, true, 4);
		FOOD_COOKED_BEEF = makeFood(config.newItemID(), "cooked_beef", 8, 10, true, 4);

		// MUTTON
		FOOD_MUTTON = makeFood(config.newItemID(), "mutton", 3, 8, true, 6);
		FOOD_COOKED_MUTTON = makeFood(config.newItemID(), "cooked_mutton", 7, 6, true, 6);

		// CHICKEN
		FOOD_CHICKEN = makeFood(config.newItemID(), "chicken", 4, 15, true, 3);
		FOOD_COOKED_CHICKEN = makeFood(config.newItemID(), "cooked_chicken", 10, 14, true, 3);

		// RABBIT
		FOOD_RABBIT = makeFood(config.newItemID(), "rabbit", 3, 7, true, 3);
		FOOD_COOKED_RABBIT = makeFood(config.newItemID(), "cooked_rabbit", 6, 5, true, 3);

		// ROTTEN FLESH
		ROTTEN_FLESH = makeFood(config.newItemID(), "rotten_flesh", 4, 12, true, 16);

		// SPIDER EYE
		SPIDER_EYE = GenericItemBuilder.build(new ItemHurt("spider_eye", "bttf:item/spider_eye", config.newItemID(), -1, 4, false, 8));


	}
	public static ItemFood makeFood(int id, String name, int healAmount, int ticksPerHeal, boolean favouriteWolfMeat, int maxStackSize) {
		return GenericItemBuilder
			.build(new ItemFood(
				name,
				"bttf:item/"+name,
				id,
				healAmount,
				ticksPerHeal,
				favouriteWolfMeat,
				maxStackSize
			));
	}

}

