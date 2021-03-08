package com.curryp0mmes.fabric.mod.uno.registry;

import com.curryp0mmes.fabric.mod.uno.FabricMod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModItems {

    //Items
    public static final Item COMMUNISM_ITEM = new Item(new Item.Settings().group(FabricMod.MOD_GROUP)); //
    public static final Item CRYSTAL = new Item(new Item.Settings().group(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_GROUP));
    public static final Item CRYSTAL_SHARD = new Item(new Item.Settings().group(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_GROUP));

    //BlockItems
    public static final BlockItem STALIN_BLOCK = new BlockItem(ModBlocks.STALIN_BLOCK, new Item.Settings().group(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_GROUP));
    public static final BlockItem CRYSTAL_ORE = new BlockItem(ModBlocks.CRYSTAL_ORE, new Item.Settings().group(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_GROUP));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "communism_item"), COMMUNISM_ITEM);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "crystal"), CRYSTAL);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "crystal_shard"), CRYSTAL_SHARD);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "stalin_block"), STALIN_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "crystal_ore"), CRYSTAL_ORE);
    }
}
