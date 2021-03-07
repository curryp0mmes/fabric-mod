package com.curryp0mmes.fabric.mod.uno;

import com.curryp0mmes.fabric.mod.uno.registry.ModBlocks;
import com.curryp0mmes.fabric.mod.uno.registry.ModItems;
import net.fabricmc.api.ModInitializer;

public class Fabricmod implements ModInitializer {

    public static final String MOD_ID = "curry";

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();

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
