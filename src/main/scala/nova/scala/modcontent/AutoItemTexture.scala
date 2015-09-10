/*
 * Copyright (c) 2015 NOVA, All rights reserved.
 * This library is free software, licensed under GNU Lesser General Public License version 3
 *
 * This file is part of NOVA.
 *
 * NOVA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NOVA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NOVA.  If not, see <http://www.gnu.org/licenses/>.
 */

package nova.scala.modcontent

import nova.core.item.Item
import nova.core.render.texture.ItemTexture

/**
 * Marks an item or block that needs to automatically register its texture based on its name.
 * @author Calclavia
 */
trait AutoItemTexture extends Item {
	var texture: ItemTexture = null

	//	override def getTexture: Optional[ItemTexture] = Optional.of(texture)
}
