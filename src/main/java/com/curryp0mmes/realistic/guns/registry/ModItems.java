package com.curryp0mmes.realistic.guns.registry;

import com.curryp0mmes.realistic.guns.RealisticGunsMod;
import com.curryp0mmes.realistic.guns.customstuff.item.M4CarbineItem;
import com.curryp0mmes.realistic.guns.customstuff.item.SimplePistolItem;
import com.curryp0mmes.realistic.guns.customstuff.material.CrystalArmorMaterial;
import com.curryp0mmes.realistic.guns.customstuff.projectile.GunProjectile;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import software.bernie.example.registry.RegistryUtils;


public class ModItems {

    //Rifle
    public static final M4CarbineItem M4_CARBINE = RegistryUtils.registerItem(
            new M4CarbineItem(new Item.Settings().group(RealisticGunsMod.MOD_GROUP).maxCount(1)),
            new Identifier(RealisticGunsMod.MOD_ID, "m4_carbine")
    );

    //PISTOL
    public static final Item TINY_GUNPOWDER = new Item(new Item.Settings().group(RealisticGunsMod.MOD_GROUP));
    public static final Item SIMPLE_PISTOL = new SimplePistolItem(new Item.Settings().group(RealisticGunsMod.MOD_GROUP).maxCount(1));
    public static final EntityType<GunProjectile> GunProjectileEntityType = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(RealisticGunsMod.MOD_ID, "gun_projectile"),
            FabricEntityTypeBuilder.<GunProjectile>create(SpawnGroup.MISC, GunProjectile::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
                    .trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
                    .build() // VERY IMPORTANT DONT DELETE FOR THE LOVE OF GOD PSLSSSSSS
    );
    public static final Item BULLET = new Item(new Item.Settings().group(RealisticGunsMod.MOD_GROUP));
    public static final Item AMMO_SHELL = new Item(new Item.Settings().group(RealisticGunsMod.MOD_GROUP));
    public static final Item AMMO = new Item(new Item.Settings().group(RealisticGunsMod.MOD_GROUP));

    public static void registerItems() {

        //Gun Items
        Registry.register(Registry.ITEM, new Identifier(RealisticGunsMod.MOD_ID, "tiny_gunpowder"), TINY_GUNPOWDER);
        Registry.register(Registry.ITEM, new Identifier(RealisticGunsMod.MOD_ID, "pistol"), SIMPLE_PISTOL);
        Registry.register(Registry.ITEM, new Identifier(RealisticGunsMod.MOD_ID, "bullet"), BULLET);
        Registry.register(Registry.ITEM, new Identifier(RealisticGunsMod.MOD_ID, "ammo_shell"), AMMO_SHELL);
        Registry.register(Registry.ITEM, new Identifier(RealisticGunsMod.MOD_ID, "ammo"), AMMO);
        //GeoItemRenderer.registerItemRenderer(M4_CARBINE, new M4CarbineRenderer());
    }
}
