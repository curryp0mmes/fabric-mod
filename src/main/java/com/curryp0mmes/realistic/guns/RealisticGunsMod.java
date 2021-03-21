package com.curryp0mmes.realistic.guns;

import com.curryp0mmes.realistic.guns.customstuff.PlayerLayDown;
import com.curryp0mmes.realistic.guns.registry.ModBlocks;
import com.curryp0mmes.realistic.guns.registry.ModItems;
import com.curryp0mmes.realistic.guns.registry.ModParticles;
import com.curryp0mmes.realistic.guns.registry.OreGenerators;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;

public class RealisticGunsMod implements ModInitializer {

    public static final String MOD_ID = "realistic_guns";


    public static final ItemGroup MOD_GROUP = FabricItemGroupBuilder
            .build(new Identifier(MOD_ID, "general"),
            () -> new ItemStack(ModItems.M4_CARBINE));
    public static final ItemGroup CRYSTAL_GROUP = FabricItemGroupBuilder
            .build(new Identifier(MOD_ID, "crystal"),
            () -> new ItemStack(ModItems.CRYSTAL));

    @Override
    public void onInitialize() {
        GeckoLib.initialize();
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        OreGenerators.configureOres();
        ModParticles.registerParticles();
        PlayerLayDown.registerEvents();
    }
}
