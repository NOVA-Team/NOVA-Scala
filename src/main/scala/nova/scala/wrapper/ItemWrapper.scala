package nova.scala.wrapper

/**
 * @author Calclavia
 */
object ItemWrapper {

	implicit class AdvancedItemStack(underlying: ItemStack) {
		def areItemsEqual(checkStack: ItemStack): Boolean = {
			if (checkStack != null) {
				if (underlying.isItemEqual(checkStack)) {
					//Check NBT
					if (underlying.getTagCompound != null && checkStack.getTagCompound != null) {
						if (underlying.getTagCompound.equals(checkStack.getTagCompound)) {
							return true
						}
					}

					if (underlying.getTagCompound == null && checkStack.getTagCompound == null) {
						return true
					}
				}
			}

			return false
		}
	}

}
