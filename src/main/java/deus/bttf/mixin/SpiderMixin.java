package deus.bttf.mixin;

import deus.bttf.Items.BTTFItems;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.monster.MobSpider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static deus.bttf.Config.ModConfig.TOML_CONFIG;

@Mixin(MobSpider.class)
public class SpiderMixin {
	//dropFewItems
	@Inject(method = "<init>", at = @At("TAIL"), remap = false)
	private void modifyInit(CallbackInfo ci) {
		MobSpider entity = (MobSpider) (Object) this;
		entity.mobDrops.add(new WeightedRandomLootObject(BTTFItems.SPIDER_EYE.getDefaultStack(), TOML_CONFIG.getInt("Drops.spider_eye.min"), TOML_CONFIG.getInt("Drops.spider_eye.max")));
	}
}
