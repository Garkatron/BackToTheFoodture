package deus.bttf.Items.CustomClasses;

import deus.bttf.Entities.CustomBobberEntity;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class CustomItemFishingRod extends Item {


	public CustomItemFishingRod(String name, int id) {
		super(name, id);
		this.setMaxDamage(64);
		this.setMaxStackSize(1);
	}


	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (entityplayer.bobberEntity != null) {
			int damage = entityplayer.bobberEntity.yoink();
			itemstack.damageItem(damage, entityplayer);
		} else {
			world.playSoundAtEntity(entityplayer, entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isClientSide) {
				world.entityJoinedWorld(
					new CustomBobberEntity(
						world,
						entityplayer,
						this
					)
				);
			}
		}

		entityplayer.swingItem();
		return itemstack;
	}
}
