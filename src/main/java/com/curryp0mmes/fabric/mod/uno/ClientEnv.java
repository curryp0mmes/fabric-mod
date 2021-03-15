package com.curryp0mmes.fabric.mod.uno;

import com.curryp0mmes.fabric.mod.uno.customstuff.projectile.EntitySpawnPacket;
import com.curryp0mmes.fabric.mod.uno.customstuff.render.M4CarbineRenderer;
import com.curryp0mmes.fabric.mod.uno.registry.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import org.lwjgl.glfw.GLFW;
import software.bernie.geckolib3.renderer.geo.GeoItemRenderer;

import java.util.UUID;

@Environment(EnvType.CLIENT)
public class ClientEnv implements ClientModInitializer {
    public static final String KEY_CATEGORY = "category.curry.general";
    private static final KeyBinding reloadKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.curry.reload", // The translation key of the keybinding's name
            InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
            GLFW.GLFW_KEY_R, // The keycode of the key
            KEY_CATEGORY // The translation key of the keybinding's category.
    ));


    public static final Identifier PacketID = new Identifier(FabricMod.MOD_ID, "spawn_packet");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(ModItems.GunProjectileEntityType, (dispatcher, context) ->
                new FlyingItemEntityRenderer(dispatcher, context.getItemRenderer()));
        receiveEntityPacket();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (reloadKeyBinding.wasPressed()) {
                ClientPlayNetworking.send(ModNetworkingConstants.RELOAD_PACKET_ID, PacketByteBufs.empty());
            }
        });
        GeoItemRenderer.registerItemRenderer(ModItems.M4_CARBINE, new M4CarbineRenderer());

    }


    @SuppressWarnings("deprecation")
    public void receiveEntityPacket() {
        ClientSidePacketRegistry.INSTANCE.register(PacketID, (ctx, byteBuf) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUuid();
            int entityId = byteBuf.readVarInt();
            Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            ctx.getTaskQueue().execute(() -> {
                if (MinecraftClient.getInstance().world == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity e = et.create(MinecraftClient.getInstance().world);
                if (e == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                e.updateTrackedPosition(pos);
                e.setPos(pos.x, pos.y, pos.z);
                e.pitch = pitch;
                e.yaw = yaw;
                e.setEntityId(entityId);
                e.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, e);
            });
        });
    }
}
