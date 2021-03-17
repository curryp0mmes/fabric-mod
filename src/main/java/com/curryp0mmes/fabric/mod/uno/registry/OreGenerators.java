package com.curryp0mmes.fabric.mod.uno.registry;

import com.curryp0mmes.fabric.mod.uno.FabricMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class OreGenerators {

    private static final ConfiguredFeature<?, ?> CRYSTAL_ORE_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    ModBlocks.CRYSTAL_ORE.getDefaultState(),
                    5)) // vein size
            .decorate(Decorator.RANGE.configure(new  RangeDecoratorConfig(
                    0,
                    0,
                    20)))
            .spreadHorizontally()
            .repeat(1); // number of veins per chunk


    @SuppressWarnings({"deprecation"})
    public static void configureOres() {
        RegistryKey<ConfiguredFeature<?, ?>> crystalOreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(FabricMod.MOD_ID, "crystal_ore_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, crystalOreOverworld.getValue(), CRYSTAL_ORE_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, crystalOreOverworld);
    }
}
