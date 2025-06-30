package deus.bttf.Items.Tools;


//import static dev.deus.fishing_additions.LootTables.LootTables.IronFishingRodLootTable;

import deus.bttf.Items.CustomClasses.CustomItemFishingRod;
import net.minecraft.core.util.collection.NamespaceID;

public class ItemCreativeFishingRod extends CustomItemFishingRod {

	public ItemCreativeFishingRod(NamespaceID namespace, int id) {
		super(namespace, id);
		this.setMaxDamage(1000000000);
	}
}
