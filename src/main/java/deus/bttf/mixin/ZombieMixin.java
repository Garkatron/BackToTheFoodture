package deus.bttf.mixin;

import deus.bttf.Items.BTTFItems;
import net.minecraft.core.WeightedRandomLootObject;

import net.minecraft.core.entity.monster.MobZombie;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static deus.bttf.Config.ModConfig.TOML_CONFIG;


@Mixin(MobZombie.class)
public class ZombieMixin {
	@Inject(method = "<init>", at = @At("TAIL"), remap = false)
	private void modifyInit(CallbackInfo ci) {
		MobZombie entity = (MobZombie) (Object) this;
		entity.mobDrops.add(new WeightedRandomLootObject(BTTFItems.ROTTEN_FLESH.getDefaultStack(), TOML_CONFIG.getInt("Drops.zombie_rotten_flesh.min"), TOML_CONFIG.getInt("Drops.zombie_rotten_flesh.max")));
	}
}
