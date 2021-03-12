package com.curryp0mmes.fabric.mod.uno.registry;

import com.curryp0mmes.fabric.mod.uno.customstuff.CrystalArmorMaterial;
import com.curryp0mmes.fabric.mod.uno.FabricMod;
import com.curryp0mmes.fabric.mod.uno.customstuff.GunItem;
import com.curryp0mmes.fabric.mod.uno.customstuff.GunProjectile;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModItems {

    //Items
    public static final Item COMMUNISM_ITEM = new Item(new Item.Settings().group(FabricMod.MOD_GROUP)); //
    public static final Item CRYSTAL = new Item(new Item.Settings().group(com.curryp0mmes.fabric.mod.uno.FabricMod.CRYSTAL_GROUP));
    public static final Item CRYSTAL_SHARD = new Item(new Item.Settings().group(com.curryp0mmes.fabric.mod.uno.FabricMod.CRYSTAL_GROUP));



    //PISTOL
    public static final Item PISTOL = new GunItem(new Item.Settings().group(FabricMod.MOD_GROUP).maxCount(1));
    public static final EntityType<GunProjectile> GunProjectileEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(FabricMod.MOD_ID, "gun_projectile"),
            FabricEntityTypeBuilder.<GunProjectile>create(SpawnGroup.MISC, GunProjectile::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
                    .trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
                    .build() // VERY IMPORTANT DONT DELETE FOR THE LOVE OF GOD PSLSSSSSS
    );
    public static final Item BULLET = new Item(new Item.Settings().group(FabricMod.MOD_GROUP));


    //Armor
    public static final ArmorMaterial CRYSTAL_ARMOR_MATERIAL = new CrystalArmorMaterial();
    public static final Item CRYSTAL_HELMET = new ArmorItem(CRYSTAL_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(FabricMod.CRYSTAL_GROUP));
    public static final Item CRYSTAL_CHESTPLATE = new ArmorItem(CRYSTAL_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(FabricMod.CRYSTAL_GROUP));
    public static final Item CRYSTAL_LEGGINGS = new ArmorItem(CRYSTAL_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(FabricMod.CRYSTAL_GROUP));
    public static final Item CRYSTAL_BOOTS = new ArmorItem(CRYSTAL_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(FabricMod.CRYSTAL_GROUP));

    //BlockItems
    public static final BlockItem STALIN_BLOCK = new BlockItem(ModBlocks.STALIN_BLOCK, new Item.Settings().group(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_GROUP));
    public static final BlockItem CRYSTAL_ORE = new BlockItem(ModBlocks.CRYSTAL_ORE, new Item.Settings().group(com.curryp0mmes.fabric.mod.uno.FabricMod.CRYSTAL_GROUP));
    public static final BlockItem CRYSTAL_BLOCK = new BlockItem(ModBlocks.CRYSTAL_BLOCK, new Item.Settings().group(com.curryp0mmes.fabric.mod.uno.FabricMod.CRYSTAL_GROUP));
    public static final BlockItem CRYSTAL_BRICKS = new BlockItem(ModBlocks.CRYSTAL_BRICKS, new Item.Settings().group(com.curryp0mmes.fabric.mod.uno.FabricMod.CRYSTAL_GROUP));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "communism_item"), COMMUNISM_ITEM);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "pistol"), PISTOL);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "bullet"), BULLET);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "crystal"), CRYSTAL);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "crystal_shard"), CRYSTAL_SHARD);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "stalin_block"), STALIN_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "crystal_ore"), CRYSTAL_ORE);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "crystal_block"), CRYSTAL_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "crystal_bricks"), CRYSTAL_BRICKS);

        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "crystal_helmet"), CRYSTAL_HELMET);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "crystal_chestplate"), CRYSTAL_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "crystal_leggings"), CRYSTAL_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(com.curryp0mmes.fabric.mod.uno.FabricMod.MOD_ID, "crystal_boots"), CRYSTAL_BOOTS);
    }
}
