package deus.bttf.Recipes;

import deus.bttf.DevTools.Debug.Debug;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.RecipeBuilder;

import static deus.bttf.BTTFMain.MOD_ID;
import static deus.bttf.Items.BTTFItems.*;

public class RecipeInitializer {
	public static void Initialize() {

		Debug.println("INITIALIZING RECIPES");

		RecipeBuilder.Shaped(MOD_ID)
			.setShape(" F ", "KFJ", " W ")
			.addInput('W', Item.bowl)
			.addInput('F', Item.foodFishCooked)
			.addInput('K', cooked_salmon)
			.addInput('J', cooked_cod)

			.create("CookedFishSoupCrafting", cooked_fish_soup.getDefaultStack());

		// FURNACE
		RecipeBuilder.Furnace(MOD_ID).setInput(salmon).create("CookedSalmonRecipe",cooked_salmon.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(gold_fish).create("CookedGoldFishRecipe",cooked_gold_fish.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(cod_fish).create("CookedCodFishRecipe",cooked_cod.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(salmon).create("CookedSalmonRecipe", cooked_salmon.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(cod_fish).create("CookedCodFishRecipe", cooked_cod.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(gold_fish).create("CookedGoldfishRecipe", cooked_gold_fish.getDefaultStack());

		RecipeBuilder.Furnace(MOD_ID).setInput(mutton_raw).create("CookedMuttonRecipe",mutton_cooked.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(rabbit_raw).create("CookedRabbitRecipe",rabbit_cooked.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(beef_raw).create("CookedBeefRecipe",beef_cooked.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(chicken_raw).create("CookedChickenRecipe", chicken_cooked.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(rotten_flesh).create("LeatherFromRottenFlesh", Item.leather.getDefaultStack());

	}
}
