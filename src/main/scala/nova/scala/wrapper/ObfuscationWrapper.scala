package nova.scala.wrapper

/**
 * Wraps obfuscated names of some classes
 * @author Calclavia
 */
object ObfuscationWrapper {

	implicit class ItemBlockWrapper(itemBlock: ItemBlock) {
		def getBlock = itemBlock.field_150939_a
	}

}
