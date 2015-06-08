package nova.scala.util

import java.util.{List => JList, Set => JSet}

/**
 * A recipe handler used to add, remove, get and replace recipes that are already added in the existing recipe pool for
 * crafting and smelting. All recipe functions take account of the ForgeOreDictionary.
 *
 * @author Calclavia
 */
object Recipes {
	def +=(in: ItemStack, out: ItemStack, xp: Float) = if (in != null) GameRegistry.addSmelting(in, out, xp)

	def get(output: ItemStack): Set[IRecipe] = get filter (check => ItemStack.areItemStacksEqual(check.getRecipeOutput, output))

	def get: Set[IRecipe] = (CraftingManager.getInstance.getRecipeList filter (_.isInstanceOf[IRecipe]) map (_.asInstanceOf[IRecipe])).toSet

	/**
	 * Replaces a recipe with a new IRecipe.
	 * @return True if successful
	 */
	def replace(recipe: IRecipe, newRecipe: IRecipe): Boolean = {
		if (this -= recipe) {
			this += newRecipe
			return true
		}

		return false
	}

	/**
	 * Removes a recipe by its IRecipe class.
	 * @return True if successful
	 */
	def -=(recipe: IRecipe): Boolean = {
		val matches = get filter (rec => rec == recipe || recipe.equals(rec))
		CraftingManager.getInstance.getRecipeList.removeAll(matches)
		return matches.size > 0
	}

	def +=(crafting: IRecipe) = if (crafting != null) GameRegistry.addRecipe(crafting)

	/**
	 * Replaces a recipe with the resulting ItemStack with a new IRecipe.
	 * @return True if successful
	 */
	def replace(recipe: ItemStack, newRecipe: IRecipe): Boolean = {
		if (this -= recipe) {
			this += newRecipe
			return true
		}

		return false
	}

	/**
	 * Removes all recipes that have a given output.
	 * @return True if successful
	 */
	def -=(stack: ItemStack): Boolean = {
		val matches = get filter (rec => rec.getRecipeOutput.isItemEqual(stack))
		CraftingManager.getInstance.getRecipeList.removeAll(matches)
		return matches.size > 0
	}

	def getRecipesByOutput(output: ItemStack): JList[IRecipe] = {
		return CraftingManager.getInstance.getRecipeList
			.filter(_.isInstanceOf[IRecipe])
			.map(_.asInstanceOf[IRecipe])
			.filter(r => ItemStack.areItemStacksEqual(r.getRecipeOutput, output))
	}

}