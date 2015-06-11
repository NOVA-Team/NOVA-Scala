package nova.scala.modcontent

import java.util.Optional

import nova.core.item.Item
import nova.core.render.texture.ItemTexture

/**
 * Marks an item or block that needs to automatically register its texture based on its name.
 * @author Calclavia
 */
trait AutoItemTexture extends Item {
	var texture: ItemTexture = null

	override def getTexture: Optional[ItemTexture] = Optional.of(texture)
}
