package deus.bttf.mixin;

import deus.bttf.Items.BTTFItems;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.animal.MobChicken;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobChicken.class)
public class ChickenMixin {
	//dropFewItems
	@Inject(method = "<init>", at = @At("TAIL"), remap = false)
	private void modifyInit(CallbackInfo ci) {
		MobChicken entity = (MobChicken) (Object) this;
		entity.mobDrops.add(new WeightedRandomLootObject(BTTFItems.FOOD_CHICKEN.getDefaultStack(), 0, 1));
	}

}
