package deus.bttf.Items;


import deus.bttf.DevTools.Debug.Debug;
import deus.bttf.DevTools.Utils.ItemUtils;
import deus.bttf.Items.Tools.ItemCreativeFishingRod;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemFood;
import turniplabs.halplibe.helper.CreativeHelper;
import turniplabs.halplibe.helper.ItemBuilder;

import static deus.bttf.BTTFMain.MOD_ID;
import static deus.bttf.BTTFMain.config;


public class BTTFItems {

	public static final ItemBuilder GenericItemBuilder = new ItemBuilder(MOD_ID);

	// ### Pescado Pufferfish
	public static Item pufferfish;

	// ### Salmón
	public static Item salmon;
	public static Item cooked_salmon;

	// ### Pez Tropical
	public static Item tropical_fish;

	// ### Bacalao
	public static Item cod_fish;
	public static Item cooked_cod;

	// ### Pez Dorado (Goldfish)
	public static Item gold_fish;
	public static Item cooked_gold_fish;

	// ### Sopa de Pescado Cocido
	public static Item cooked_fish_soup;

	public static Item beef_raw;
	public static Item beef_cooked;
	public static Item mutton_raw;
	public static Item mutton_cooked;
	public static Item chicken_raw;
	public static Item chicken_cooked;
	public static Item rabbit_raw;
	public static Item rabbit_cooked;
	public static Item rotten_flesh;
	public static Item spider_eye;


	public void Initialize() {

		// ? ## Food

		// Pescados
		pufferfish = makeFood(config.newItemID(), "pufferfish", -1, 1, false, 1);
		salmon = makeFood(config.newItemID(), "salmon", 2, 1, false, 64);
		cooked_salmon = makeFood(config.newItemID(), "cooked_salmon", 6, 1, false, 64);
		tropical_fish = makeFood(config.newItemID(), "tropical_fish", 1, 1, false, 64);
		cod_fish = makeFood(config.newItemID(), "cod", 2, 1, false, 64);
		cooked_cod = makeFood(config.newItemID(), "cooked_cod", 5, 1, false, 64);
		gold_fish = makeFood(config.newItemID(), "gold_fish", 2, 1, false, 64);
		cooked_gold_fish = makeFood(config.newItemID(), "cooked_gold_fish", 6, 1, false, 64);
		cooked_fish_soup = makeFood(config.newItemID(), "cooked_fish_soup", 8, 1, false, 1);

		// Carne de Vaca
		beef_raw = makeFood(config.newItemID(), "beef_raw", 3, 1, true, 64);
		beef_cooked = makeFood(config.newItemID(), "beef_cooked", 8, 1, true, 64);

		// Carne de Oveja
		mutton_raw = makeFood(config.newItemID(), "mutton_raw", 2, 1, true, 64);
		mutton_cooked = makeFood(config.newItemID(), "mutton_cooked", 6, 1, true, 64);

		// Pollo
		chicken_raw = makeFood(config.newItemID(), "chicken_raw", 2, 1, true, 64);
		chicken_cooked = makeFood(config.newItemID(), "chicken_cooked", 6, 1, true, 64);

		// Conejo
		rabbit_raw = makeFood(config.newItemID(), "rabbit_raw", 3, 1, true, 64);
		rabbit_cooked = makeFood(config.newItemID(), "rabbit_cooked", 5, 1, true, 64);

		// Carne de Zombie
		rotten_flesh = makeFood(config.newItemID(), "rotten_flesh", 4, 1, true, 64);

		// Ojo de Araña
		spider_eye = makeFood(config.newItemID(), "spider_eye", 0, 1, false, 64); // 2 de curación, 1 tick, apilable hasta 64


		// Debug.debugExecuteIt(() -> {

//			Item creative_fishing_rod;
//			ItemBuilder generic_item_builder = new ItemBuilder(MOD_ID);
//
//			ItemCreativeFishingRod t = new ItemCreativeFishingRod("bttf:item/ItemCreativeFishingRod", config.newItemID());
//
//			creative_fishing_rod = generic_item_builder.build(t);
//			CreativeHelper.setPriority(creative_fishing_rod, 1000);
//
//			Debug.println("Creative: " + creative_fishing_rod.id);

		// });

		// Here are assigned the priorities of the items in the creative menu.
		ItemUtils.assignPriorities(this.getClass());

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

