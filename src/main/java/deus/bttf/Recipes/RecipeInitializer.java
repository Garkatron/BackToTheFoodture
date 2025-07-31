package deus.bttf.Recipes;

import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeRegistry;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.helper.RecipeBuilder;

import static deus.bttf.BTTFMain.MOD_ID;
import static deus.bttf.Items.BTTFItems.*;

public class RecipeInitializer extends RecipeRegistry {
	public static final RecipeNamespace BTTF = new RecipeNamespace();

	public static void InitRecipes() {
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(" F ", "KFJ", " W ")
			.addInput('W', Items.BOWL)
			.addInput('F', Items.FOOD_FISH_COOKED)
			.addInput('K', FOOD_COOKED_SALMON)
			.addInput('J', FOOD_COOKED_COD)
			.create("bttf:recipe/fish_soup", FOOD_COOKED_FISH_SOUP.getDefaultStack());

		RecipeBuilder.Furnace(MOD_ID).setInput(FOOD_SALMON).create("bttf:recipe/cooked_salmon", FOOD_COOKED_SALMON.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(FOOD_GOLD_FISH).create("bttf:recipe/cooked_gold_fish", FOOD_COOKED_GOLD_FISH.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(FOOD_COD).create("bttf:recipe/cooked_cod_fish", FOOD_COOKED_COD.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(FOOD_MUTTON).create("bttf:recipe/cooked_mutton", FOOD_COOKED_MUTTON.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(FOOD_RABBIT).create("bttf:recipe/cooked_rabbit", FOOD_COOKED_RABBIT.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(FOOD_BEEF).create("bttf:recipe/cooked_beef", FOOD_COOKED_BEEF.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(FOOD_CHICKEN).create("bttf:recipe/cooked_chicken", FOOD_COOKED_CHICKEN.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(ROTTEN_FLESH).create("bttf:recipe/leather_from_rotten_flesh", Items.LEATHER.getDefaultStack());

	}

	public static void InitNameSpaces() {
		final RecipeGroup<RecipeEntryCrafting<?, ?>> FURNACE = new RecipeGroup<>(
			new RecipeSymbol(new ItemStack(FOOD_GOLD_FISH))
		);

		BTTF.register("bttf", FURNACE);

		Registries.RECIPES.register(MOD_ID, BTTF);
	}
}
