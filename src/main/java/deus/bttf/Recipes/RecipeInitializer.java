package deus.bttf.Recipes;

import deus.bttf.Items.BTTFItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeRegistry;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryFurnace;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.helper.RecipeBuilder;

import static deus.bttf.BTTFMain.MOD_ID;
import static deus.bttf.Items.BTTFItems.*; // Asegúrate de que los ítems están importados correctamente

public class RecipeInitializer extends RecipeRegistry {
	public static final RecipeNamespace BTTF = new RecipeNamespace();

	public static void InitRecipes() {
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(" F ", "KFJ", " W ")
			.addInput('W', Items.BOWL)
			.addInput('F', Items.FOOD_FISH_COOKED)
			.addInput('K', cooked_salmon)
			.addInput('J', cooked_cod)
			.create("bttf:recipe/fish_soup", cooked_fish_soup.getDefaultStack());

		RecipeBuilder.Furnace(MOD_ID).setInput(salmon).create("bttf:recipe/cooked_salmon", cooked_salmon.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(gold_fish).create("bttf:recipe/cooked_gold_fish", cooked_gold_fish.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(cod_fish).create("bttf:recipe/cooked_cod_fish", cooked_cod.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(mutton_raw).create("bttf:recipe/cooked_mutton", mutton_cooked.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(rabbit_raw).create("bttf:recipe/cooked_rabbit", rabbit_cooked.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(beef_raw).create("bttf:recipe/cooked_beef", beef_cooked.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(chicken_raw).create("bttf:recipe/cooked_chicken", chicken_cooked.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(rotten_flesh).create("bttf:recipe/leather_from_rotten_flesh", Items.LEATHER.getDefaultStack());

	}

	public static void InitNameSpaces() {
		final RecipeGroup<RecipeEntryCrafting<?, ?>> FURNACE = new RecipeGroup<>(
			new RecipeSymbol(new ItemStack(gold_fish))
		);

		BTTF.register("bttf", FURNACE);

		Registries.RECIPES.register(MOD_ID, BTTF);
	}
}
