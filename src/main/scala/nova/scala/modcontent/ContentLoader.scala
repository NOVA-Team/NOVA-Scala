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

import java.util.function.{Function => JFunction}

import nova.core.block.{Block, BlockFactory}
import nova.core.entity.{Entity, EntityFactory}
import nova.core.item.{Item, ItemFactory}
import nova.core.loader.Loadable
import nova.core.render.model.ModelProvider
import nova.core.render.texture.{BlockTexture, EntityTexture, ItemTexture}
import nova.internal.core.Game
import nova.scala.wrapper.FunctionalWrapper._

/**
 * Automatic mffs.content registration for all Blocks, Items, Entities and Textures.
 *
 * Extend this trait from the main mod loading class and all fields will be registered. Elegantly.
 *
 * @author Calclavia
 */
trait ContentLoader extends Loadable {
	self =>

	def id: String

	override def preInit() = {
		//Automated handler for registering blocks & items vars
		for (field <- self.getClass.getDeclaredFields) {
			//Set it so we can access the field
			field.setAccessible(true)

			//Get contents for reference
			val obj = field.get(self)

			if (obj != null) {
				// Get type of AnyRef, then register it if supported
				obj match {
					case itemWrapper: ItemClassWrapper =>
						if (itemWrapper.wrapped.newInstance().isInstanceOf[AutoItemTexture]) {
							val texture = Game.render.registerTexture(new ItemTexture(id, itemWrapper.getID))
							field.set(self, Game.items.register(
								texture.getClass.getName,
								supplier(() => {
									val wrapped = itemWrapper.wrapped.newInstance()
									wrapped.asInstanceOf[AutoItemTexture].texture = texture
									wrapped
								})
							))
						}
						else {
							field.set(self, Game.items.register(itemWrapper.wrapped))
						}
					case itemConstructor: ItemConstructorWrapper =>
						if (itemConstructor.wrapped.apply().isInstanceOf[AutoItemTexture]) {
							val texture = Game.render.registerTexture(new ItemTexture(id, itemConstructor.wrapped.getID))
							field.set(self, Game.items.register(
								texture.getClass.getName,
								supplier(() => {
									val wrapped = itemConstructor.wrapped.apply()
									wrapped.asInstanceOf[AutoItemTexture].texture = texture
									wrapped
								})
							))
						}
						else {
							field.set(self, Game.items.register(itemConstructor))
						}

					case blockWrapper: BlockClassWrapper =>
						if (blockWrapper.wrapped.newInstance().isInstanceOf[AutoBlockTexture]) {
							val texture = Game.render.registerTexture(new BlockTexture(id, blockWrapper.getID))
							Game.render.registerTexture(new BlockTexture(id, blockWrapper.getID))
							field.set(self, Game.blocks.register(
								texture.getClass.getName,
								supplier(() => {
									val wrapped = blockWrapper.wrapped.newInstance()
									wrapped.asInstanceOf[AutoBlockTexture].texture = texture
									wrapped
								})
							))
						}
						else {
							field.set(self, Game.blocks.register(blockWrapper.wrapped))
						}
					case blockConstructor: BlockConstructorWrapper =>
						if (blockConstructor.wrapped.apply().isInstanceOf[AutoBlockTexture]) {
							val texture = Game.render.registerTexture(new BlockTexture(id, blockConstructor.getID))
							Game.render.registerTexture(new BlockTexture(id, blockConstructor.getID))
							field.set(self, Game.blocks.register(
								texture.getClass.getName,
								supplier(() => {
									val wrapped = blockConstructor.wrapped.apply()
									wrapped.asInstanceOf[AutoBlockTexture].texture = texture
									wrapped
								})
							))
						}
						else {
							field.set(self, Game.blocks.register(blockConstructor))
						}
					case factory: EntityClassWrapper => field.set(self, Game.entities.register(factory))
					case factory: EntityConstructorWrapper => field.set(self, Game.entities.register(factory))
					case itemTexture: ItemTexture => field.set(self, Game.render.registerTexture(itemTexture))
					case blockTexture: BlockTexture => field.set(self, Game.render.registerTexture(blockTexture))
					case entityTexture: EntityTexture => field.set(self, Game.render.registerTexture(entityTexture))
					case modelProvider: ModelProvider => field.set(self, Game.render.registerModel(modelProvider))
					//	case guiFactory: GuiConstructorWrapper => Game.gui.register(guiFactory)
					case _ =>
				}
			}
		}
	}

	/**
	 * Creates a dummy instances temporarily until the preInit stage has passed
	 */
	implicit protected class BlockClassWrapper(val wrapped: Class[_ <: Block]) extends BlockFactory(wrapped.getName, () => wrapped.newInstance())

	implicit protected class BlockConstructorWrapper(val wrapped: () => Block) extends BlockFactory(wrapped.getClass.getName, () => wrapped())

	implicit protected class ItemClassWrapper(val wrapped: Class[_ <: Item]) extends ItemFactory(wrapped.getName, () => wrapped.newInstance())

	implicit protected class ItemConstructorWrapper(val wrapped: () => Item) extends ItemFactory(wrapped.getClass.getName, () => wrapped())

	implicit protected class EntityClassWrapper(val wrapped: Class[_ <: Entity]) extends EntityFactory(wrapped.getName, () => wrapped.newInstance())

	implicit protected class EntityConstructorWrapper(val wrapped: () => Entity) extends EntityFactory(wrapped.getClass.getName, () => wrapped())

	//implicit protected class GuiConstructorWrapper(val wrapped: Class[_ <: Gui]) extends GuiFactory(() => wrapped.newInstance())
}
