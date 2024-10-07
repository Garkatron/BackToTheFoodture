package deus.bttf.Items.Tools;


//import static dev.deus.fishing_additions.LootTables.LootTables.IronFishingRodLootTable;

import deus.bttf.Items.CustomClasses.CustomItemFishingRod;

public class ItemCreativeFishingRod extends CustomItemFishingRod {
	public ItemCreativeFishingRod(String name, int id) {
		super(name, id);
		this.setMaxDamage(1000000000);
	}
}
