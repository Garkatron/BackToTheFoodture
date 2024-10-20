package deus.bttf;


import deus.bttf.Config.ModConfig;
import deus.bttf.Items.BTTFItems;
import deus.bttf.Recipes.RecipeInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import static deus.bttf.DevTools.Debug.Debug.isDebug;

public class BTTFMain implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {

	public static ModConfig config = new ModConfig();
	public static final String MOD_ID = "bttf";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
			getInputArguments().toString().contains("-agentlib:jdwp");
		LOGGER.info("Fishing Additions initialized. Debug: "+ isDebug);


		new BTTFItems().Initialize();

	}

	@Override
	public void beforeGameStart() {


	}

	@Override
	public void afterGameStart() {
	}

	@Override
	public void onRecipesReady() {
		RecipeInitializer.InitRecipes();
	}

	@Override
	public void initNamespaces() {
		RecipeInitializer.InitNameSpaces();
	}
}
