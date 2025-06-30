package deus.bttf.mixin;

import deus.bttf.Items.BTTFItems;
import net.minecraft.core.WeightedRandomLootObject;

import net.minecraft.core.entity.animal.MobCow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(MobCow.class)
public class CowMixin {
	//dropFewItems
	@Inject(method = "<init>", at = @At("TAIL"), remap = false)
	private void modifyInit(CallbackInfo ci) {
		MobCow entity = (MobCow) (Object) this;
		entity.mobDrops.add(new WeightedRandomLootObject(BTTFItems.beef_raw.getDefaultStack(), 0, 2));
	}
}
