package com.curryp0mmes.realistic.guns.customstuff.projectile;

import com.curryp0mmes.realistic.guns.ClientEnv;
import com.curryp0mmes.realistic.guns.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class GunProjectile extends ThrownItemEntity {

    public GunProjectile(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public GunProjectile(World world, LivingEntity owner) {
        super(ModItems.GunProjectileEntityType, owner, world);
    }

    public GunProjectile(World world, double x, double y, double z) {
        super(ModItems.GunProjectileEntityType, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.BULLET;
    }

    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, ClientEnv.PacketID);
    }

    protected void onEntityHit(EntityHitResult entityHitResult) { // called on entity hit.
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity(); // sets a new Entity instance as the EntityHitResult (victim)
        int i = 5;
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float)i); // deals damage

        if (entity instanceof LivingEntity) { // checks if entity is an instance of LivingEntity (meaning it is not a boat or minecart)
            //entity.playSound(SoundEvents.BLOCK_GLASS_BREAK, 2F, 1F); // plays a sound for the entity hit only
        }
    }

    protected void onCollision(HitResult hitResult) { // called on collision with a block
        super.onCollision(hitResult);
        if (!this.world.isClient) { // checks if the world is client
            this.world.sendEntityStatus(this, (byte)3); // particle?
            this.remove(); // kills the projectile
        }

    }


}
