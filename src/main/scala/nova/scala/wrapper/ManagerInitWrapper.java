/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nova.scala.wrapper;

import nova.core.block.BlockManager;
import nova.core.entity.EntityManager;
import nova.core.item.ItemManager;
import nova.core.language.LanguageManager;
import nova.core.recipes.RecipeManager;
import nova.core.render.RenderManager;

/**
 * Created because Scala's {@code }
 *
 * @author ExE Boss
 */
public final class ManagerInitWrapper {

    private ManagerInitWrapper() {}

    public static final Class<BlockManager.Init> BLOCK_MANAGER_INIT       = BlockManager.Init.class;
    public static final Class<ItemManager.Init> ITEM_MANAGER_INIT         = ItemManager.Init.class;
    public static final Class<LanguageManager.Init> LANGUAGE_MANAGER_INIT = LanguageManager.Init.class;
    public static final Class<RenderManager.Init> RENDER_MANAGER_INIT     = RenderManager.Init.class;
    public static final Class<RecipeManager.Init> RECIPE_MANAGER_INIT     = RecipeManager.Init.class;
    public static final Class<EntityManager.Init> ENTITY_MANAGER_INIT     = EntityManager.Init.class;
}
