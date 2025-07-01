package deus.bttf.Items;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemFood;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.world.World;

public class ItemHurt extends ItemFood {
	public ItemHurt(String name, String namespaceId, int id, int healAmount, int ticksPerHeal, boolean favouriteWolfMeat, int maxStackSize) {
		super(name, namespaceId, id, healAmount, ticksPerHeal, favouriteWolfMeat, maxStackSize);
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, Player entityplayer) {
		ItemStack i = super.onUseItem(itemstack, world, entityplayer);
		entityplayer.sendMessage("Ouch!");
		entityplayer.hurt(entityplayer, 2, DamageType.GENERIC);
		return itemstack;
	}
}
