package deus.bttf.mixin.plugin;


import deus.bttf.BTTFMain;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class BttfMixinConfigPlugin implements IMixinConfigPlugin {

	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		if (mixinClassName.equals("deus.bttf.mixin.FAFishingRodMixin")) {
			BTTFMain.LOGGER.warn("BackToTheFoodture Disabled Fishing rod Mixins to avoid Stardew Farming incompatibility");
			return !FabricLoader.getInstance().isModLoaded("stardew");
		}
		return true;
	}

	@Override public void onLoad(String mixinPackage) {}
	@Override public String getRefMapperConfig() { return null; }
	@Override public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

	@Override
	public List<String> getMixins() {
		List<String> strings = new java.util.ArrayList<>();
		return strings;
	}

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

	}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

	}
}
