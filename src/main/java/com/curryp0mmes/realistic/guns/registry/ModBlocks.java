package com.curryp0mmes.realistic.guns.registry;

import com.curryp0mmes.realistic.guns.RealisticGunsMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block CRYSTAL_ORE = new Block(FabricBlockSettings
            .of(Material.STONE)
            .breakByTool(FabricToolTags.PICKAXES,2)
            .requiresTool()
            .strength(2.f,20.f)
            .sounds(BlockSoundGroup.STONE));

    public static final Block CRYSTAL_BLOCK = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES)
            .requiresTool()
            .strength(2.f,20.f)
            .sounds(BlockSoundGroup.METAL));

    public static final Block CRYSTAL_BRICKS = new Block(FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES)
            .requiresTool()
            .strength(2.f,20.f)
            .sounds(BlockSoundGroup.METAL));

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(RealisticGunsMod.MOD_ID, "crystal_ore"), CRYSTAL_ORE);
        Registry.register(Registry.BLOCK, new Identifier(RealisticGunsMod.MOD_ID, "crystal_block"), CRYSTAL_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(RealisticGunsMod.MOD_ID, "crystal_bricks"), CRYSTAL_BRICKS);
    }
}
