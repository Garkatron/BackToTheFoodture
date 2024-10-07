package deus.bttf.mixin;

import deus.bttf.Items.BTTFItems;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.animal.EntitySheep;
import net.minecraft.core.entity.monster.EntityZombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(EntityZombie.class)
public class ZombieMixin {
	@Inject(method = "<init>", at = @At("TAIL"), remap = false)
	private void modifyInit(CallbackInfo ci) {
		EntityZombie entity = (EntityZombie) (Object) this;
		entity.mobDrops.add(new WeightedRandomLootObject(BTTFItems.rotten_flesh.getDefaultStack(), 0, 2));
	}
}
