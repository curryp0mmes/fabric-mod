package com.curryp0mmes.fabric.mod.uno;

import com.curryp0mmes.fabric.mod.uno.registry.ModBlocks;
import com.curryp0mmes.fabric.mod.uno.registry.ModItems;
import com.curryp0mmes.fabric.mod.uno.registry.OreGenerators;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Fabricmod implements ModInitializer {

    public static final String MOD_ID = "curry";

    public static final ItemGroup MOD_GROUP = FabricItemGroupBuilder
            .build(new Identifier(MOD_ID, "general"),
            () -> new ItemStack(ModItems.COMMUNISM_ITEM));

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        OreGenerators.configureOres();

        System.out.println("Yay this is working");

        int aCoolVariable = 10;

        aCoolVariable += 10;

        System.out.println("the cool variable is now " + aCoolVariable);

        System.out.println("Test function says: " + testFunction(aCoolVariable));

    }

    public int testFunction(int x) {
        return x+2;
    }
}
