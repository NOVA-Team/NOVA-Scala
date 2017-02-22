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

import nova.core.block.{Block, BlockFactory, BlockManager}
import nova.core.entity.{Entity, EntityFactory, EntityManager}
import nova.core.item.{Item, ItemFactory, ItemManager}
import nova.core.language.LanguageManager
import nova.core.recipes.RecipeManager
import nova.core.render.RenderManager
import nova.core.render.model.ModelProvider
import nova.core.render.texture.{BlockTexture, EntityTexture, ItemTexture}
import nova.internal.core.Game
import nova.scala.wrapper.ManagerInitWrapper

/**
 * Automatic content registration for all Blocks, Items, Entities and Textures.
 *
 * Extend this trait from the main mod loading class and all fields will be registered. Elegantly.
 * It is recommended to override the [[registerLanguage]] method to register translations.
 *
 * @author Calclavia, ExE Boss
 */
trait ContentLoader {
	self =>

	Game.events.on(ManagerInitWrapper.LANGUAGE_MANAGER_INIT).bind(evt => registerLanguage(evt.manager))
	Game.events.on(ManagerInitWrapper.RENDER_MANAGER_INIT).bind(evt => registerRenderer(evt.manager))
	Game.events.on(ManagerInitWrapper.BLOCK_MANAGER_INIT).bind(evt => registerBlocks(evt.manager))
	Game.events.on(ManagerInitWrapper.ITEM_MANAGER_INIT).bind(evt => registerItems(evt.manager))
	Game.events.on(ManagerInitWrapper.ENTITY_MANAGER_INIT).bind(evt => registerEntities(evt.manager))
	Game.events.on(ManagerInitWrapper.RECIPE_MANAGER_INIT).bind(evt => registerRecipes(evt.manager))

	def id: String

	/**
	 * Register translations
	 *
	 * @param languageManager The langauge manager to register the translations to
	 */
	protected def registerLanguage(languageManager: LanguageManager) {}

	private def registerRenderer(renderManager: RenderManager) {
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
						if (itemWrapper.wrapped.newInstance().isInstanceOf[AutoItemTexture])
							renderManager.registerTexture(new ItemTexture(id, itemWrapper.getID.substring(id.length + 1)))
					case itemConstructor: ItemConstructorWrapper =>
						if (itemConstructor.wrapped.apply().isInstanceOf[AutoItemTexture])
							renderManager.registerTexture(new ItemTexture(id, itemConstructor.getID.substring(id.length + 1)))

					case blockWrapper: BlockClassWrapper =>
						if (blockWrapper.wrapped.newInstance().isInstanceOf[AutoBlockTexture])
							renderManager.registerTexture(new BlockTexture(id, blockWrapper.getID.substring(id.length + 1)))
					case blockConstructor: BlockConstructorWrapper =>
						if (blockConstructor.wrapped.apply().isInstanceOf[AutoBlockTexture])
							renderManager.registerTexture(new BlockTexture(id, blockConstructor.getID.substring(id.length + 1)))

					case itemTexture: ItemTexture     => field.set(self, renderManager.registerTexture(itemTexture))
					case blockTexture: BlockTexture   => field.set(self, renderManager.registerTexture(blockTexture))
					case entityTexture: EntityTexture => field.set(self, renderManager.registerTexture(entityTexture))
					case modelProvider: ModelProvider => field.set(self, renderManager.registerModel(modelProvider))
					case _ =>
				}
			}
		}
	}

	private def registerBlocks(blockManager: BlockManager) {
		//Automated handler for registering items
		for (field <- self.getClass.getDeclaredFields) {
			//Set it so we can access the field
			field.setAccessible(true)

			//Get contents for reference
			val obj = field.get(self)

			if (obj != null) {
				// Get type of AnyRef, then register it if supported
				obj match {
					case blockWrapper: BlockClassWrapper =>
						if (blockWrapper.wrapped.newInstance().isInstanceOf[AutoBlockTexture]) {
							val texture = Game.render.blockTextures.get(blockWrapper.getID).get
							field.set(self, Game.blocks.register(
								blockWrapper.getID, () => {
									val wrapped = blockWrapper.wrapped.newInstance()
									wrapped.asInstanceOf[AutoBlockTexture].texture = texture
									wrapped
								}
							))
						}
						else {
							field.set(self, Game.blocks.register(blockWrapper.wrapped))
						}
					case blockConstructor: BlockConstructorWrapper =>
						if (blockConstructor.wrapped.apply().isInstanceOf[AutoBlockTexture]) {
							val texture = Game.render.blockTextures.get(blockConstructor.getID).get
							field.set(self, Game.blocks.register(
								blockConstructor.getID, () => {
									val wrapped = blockConstructor.wrapped.apply()
									wrapped.asInstanceOf[AutoBlockTexture].texture = texture
									wrapped
								}
							))
						}
						else {
							field.set(self, Game.blocks.register(blockConstructor))
						}
					case _ =>
				}
			}
			field.setAccessible(false)
		}
	}

	private def registerItems(itemManager: ItemManager) {
		//Automated handler for registering items
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
							val texture = Game.render.itemTextures.get(itemWrapper.getID).get
							field.set(self, Game.items.register(
								itemWrapper.getID, () => {
									val wrapped = itemWrapper.wrapped.newInstance()
									wrapped.asInstanceOf[AutoItemTexture].texture = texture
									wrapped
								}
							))
						}
						else {
							field.set(self, Game.items.register(itemWrapper.wrapped))
						}
					case itemConstructor: ItemConstructorWrapper =>
						if (itemConstructor.wrapped.apply().isInstanceOf[AutoItemTexture]) {
							val texture = Game.render.itemTextures.get(itemConstructor.getID).get
							field.set(self, Game.items.register(
								itemConstructor.getID, () => {
									val wrapped = itemConstructor.wrapped.apply()
									wrapped.asInstanceOf[AutoItemTexture].texture = texture
									wrapped
								}
							))
						}
						else {
							field.set(self, Game.items.register(itemConstructor))
						}
					case _ =>
				}
			}
			field.setAccessible(false)
		}
	}

	private def registerEntities(entityManager: EntityManager) {
		//Automated handler for registering blocks & items vars
		for (field <- self.getClass.getDeclaredFields) {
			//Set it so we can access the field
			field.setAccessible(true)

			//Get contents for reference
			val obj = field.get(self)

			if (obj != null) {
				// Get type of AnyRef, then register it if supported
				obj match {
					case factory: EntityClassWrapper => field.set(self, Game.entities.register(factory))
					case factory: EntityConstructorWrapper => field.set(self, Game.entities.register(factory))
					case _ =>
				}
			}
			field.setAccessible(false)
		}
	}

	private def registerRecipes(recipeManager: RecipeManager) {
		// TODO: Recipes
	}

	/**
	 * Creates a dummy instances temporarily until the preInit stage has passed
	 */
	implicit protected class BlockClassWrapper(val wrapped: Class[_ <: Block]) extends BlockFactory(id + ':' + wrapped.getName, () => wrapped.newInstance())

	implicit protected class BlockConstructorWrapper(val wrapped: () => Block) extends BlockFactory(id + ':' + wrapped.getClass.getName, () => wrapped())

	implicit protected class ItemClassWrapper(val wrapped: Class[_ <: Item]) extends ItemFactory(id + ':' + wrapped.getName, () => wrapped.newInstance())

	implicit protected class ItemConstructorWrapper(val wrapped: () => Item) extends ItemFactory(id + ':' + wrapped.getClass.getName, () => wrapped())

	implicit protected class EntityClassWrapper(val wrapped: Class[_ <: Entity]) extends EntityFactory(id + ':' + wrapped.getName, () => wrapped.newInstance())

	implicit protected class EntityConstructorWrapper(val wrapped: () => Entity) extends EntityFactory(id + ':' + wrapped.getClass.getName, () => wrapped())

	//implicit protected class GuiConstructorWrapper(val wrapped: Class[_ <: Gui]) extends GuiFactory(() => wrapped.newInstance())
}
