package deus.bttf.Recipes;

import net.minecraft.core.block.Block;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeRegistry;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryFurnace;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;

import static deus.bttf.BTTFMain.MOD_ID;
import static deus.bttf.Items.BTTFItems.*; // Asegúrate de que los ítems están importados correctamente

public class RecipeInitializer extends RecipeRegistry {
	public static final RecipeNamespace BTTF = new RecipeNamespace();

	public static void InitRecipes() {
		// Inicia las recetas de la sopa de pescado cocido
		RecipeBuilder.Shaped(MOD_ID)
			.setShape(" F ", "KFJ", " W ")
			.addInput('W', Item.bowl)
			.addInput('F', Item.foodFishCooked)
			.addInput('K', cooked_salmon)
			.addInput('J', cooked_cod) // Si no necesitas cocinados, simplemente elimina esta línea
			.create("CookedFishSoupCrafting", cooked_fish_soup.getDefaultStack());

		// Recetas de horno
		RecipeBuilder.Furnace(MOD_ID).setInput(salmon).create("CookedSalmonRecipe", cooked_salmon.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(gold_fish).create("CookedGoldFishRecipe", cooked_gold_fish.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(cod_fish).create("CookedCodFishRecipe", cooked_cod.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(mutton_raw).create("CookedMuttonRecipe", mutton_cooked.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(rabbit_raw).create("CookedRabbitRecipe", rabbit_cooked.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(beef_raw).create("CookedBeefRecipe", beef_cooked.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(chicken_raw).create("CookedChickenRecipe", chicken_cooked.getDefaultStack());
		RecipeBuilder.Furnace(MOD_ID).setInput(rotten_flesh).create("LeatherFromRottenFlesh", Item.leather.getDefaultStack());
	}

	public static void InitNameSpaces() {
		// Registro de grupos de recetas
		final RecipeGroup<RecipeEntryCrafting<?, ?>> FURNACE = new RecipeGroup<>(
			new RecipeSymbol(new ItemStack(Block.furnaceStoneActive))
		);

		// Registra el grupo de recetas de la mesa de trabajo

		BTTF.register("furnace", FURNACE);

		// Registro final de recetas
		Registries.RECIPES.register(MOD_ID, BTTF);
	}
}
