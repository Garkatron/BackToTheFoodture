package deus.bttf.mixin;

import deus.bttf.Items.BTTFItems;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.animal.MobSheep;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static deus.bttf.Config.ModConfig.TOML_CONFIG;


@Mixin(MobSheep.class)
public class SheepMixin {
	//dropFewItems
	@Inject(method = "<init>", at = @At("TAIL"), remap = false)
	private void modifyInit(CallbackInfo ci) {
		MobSheep entity = (MobSheep) (Object) this;
		entity.mobDrops.add(new WeightedRandomLootObject(BTTFItems.FOOD_MUTTON.getDefaultStack(), TOML_CONFIG.getInt("Drops.sheep_mutton.min"), TOML_CONFIG.getInt("Drops.sheep_mutton.max")));
	}



}
