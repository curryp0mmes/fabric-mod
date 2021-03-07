package com.curryp0mmes.fabric.mod.uno.registry;

import com.curryp0mmes.fabric.mod.uno.Fabricmod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModItems {

    //items
    public static final Item COMMUNISM_ITEM = new Item(new Item.Settings().group(ItemGroup.MISC).maxCount(100));

    //moditems
    public static final BlockItem STALIN_BLOCK = new BlockItem(ModBlocks.STALIN_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(Fabricmod.MOD_ID, "communism_item"), COMMUNISM_ITEM);
        Registry.register(Registry.ITEM, new Identifier(Fabricmod.MOD_ID, "stalin_block"), STALIN_BLOCK);
    }
}
