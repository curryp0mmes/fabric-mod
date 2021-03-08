package com.curryp0mmes.fabric.mod.uno.registry;

import com.curryp0mmes.fabric.mod.uno.Fabricmod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModItems {

    //Items
    public static final Item COMMUNISM_ITEM = new Item(new Item.Settings().group(Fabricmod.MOD_GROUP).maxCount(100)); //

    //BlockItems
    public static final BlockItem STALIN_BLOCK = new BlockItem(ModBlocks.STALIN_BLOCK, new Item.Settings().group(Fabricmod.MOD_GROUP));
    public static final BlockItem OUR_ORE = new BlockItem(ModBlocks.OUR_ORE, new Item.Settings().group(Fabricmod.MOD_GROUP));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(Fabricmod.MOD_ID, "communism_item"), COMMUNISM_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Fabricmod.MOD_ID, "stalin_block"), STALIN_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(Fabricmod.MOD_ID, "our_ore"), OUR_ORE);
    }
}
