package nova.scala.modcontent

import java.util.Optional

import nova.core.block.Block
import nova.core.render.texture.Texture
import nova.core.util.Direction

/**
 * Marks an item or block that needs to automatically register its texture based on its name.
 * @author Calclavia
 */
trait AutoBlockTexture extends Block {
	var texture: Texture = null

	def getTexture(side: Direction): Optional[Texture] = Optional.of(texture)
}
