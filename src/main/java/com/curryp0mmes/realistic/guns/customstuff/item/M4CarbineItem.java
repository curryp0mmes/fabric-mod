package com.curryp0mmes.realistic.guns.customstuff.item;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import software.bernie.example.registry.SoundRegistry;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.ParticleKeyFrameEvent;
import software.bernie.geckolib3.core.event.SoundKeyframeEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class M4CarbineItem extends GunItem implements IAnimatable {

    public AnimationFactory factory = new AnimationFactory(this);
    private String controllerName = "controller";

    public M4CarbineItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxAmmunition() {
        return 30;
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event)
    {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.m4carbine.fire", false));
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData animationData) {
        AnimationController controller = new AnimationController(this, controllerName, 1, this::predicate);
        controller.registerSoundListener(this::soundListener);
        controller.registerParticleListener(this::particleListener);
        animationData.addAnimationController(controller);
    }
    private <ENTITY extends IAnimatable> void particleListener(ParticleKeyFrameEvent<ENTITY> event) {

        World world = MinecraftClient.getInstance().world;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        //world.addParticle(new ParticleEffect(ModParticles.GUNFIRE_PARTICLE), player.getX(), player.getY());
    }

    private <ENTITY extends IAnimatable> void soundListener(SoundKeyframeEvent<ENTITY> event) {
        // The animation for the jackinthebox has a sound keyframe at time 0:00.
        // As soon as that keyframe gets hit this method fires and it starts playing the
        // sound to the current player.
        // The music is synced with the animation so the box opens as soon as the music
        // plays the box opening sound
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        player.playSound(SoundRegistry.JACK_MUSIC, 1, 1);
    }

    @Override
    public void reloadAnimationCallback(PlayerEntity user, ItemStack item) {
        AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, item, controllerName);
        if (controller.getAnimationState() == AnimationState.Stopped) {
            // If you don't do this, the popup animation will only play once because the
            // animation will be cached.
            controller.markNeedsReload();
            // Set the animation to open the jackinthebox which will start playing music and
            // eventually do the actual animation. Also sets it to not loop
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.m4carbine.reload", false));
        }

        return;
    }

    @Override
    public TypedActionResult<ItemStack> fireAnimationCallback(World world, PlayerEntity user, Hand hand) {
        System.out.println("TEST");
        if(!world.isClient) return TypedActionResult.pass(user.getStackInHand(hand));
        AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, user.getStackInHand(hand), controllerName);

        if (controller.getAnimationState() == AnimationState.Stopped) {
            // If you don't do this, the popup animation will only play once because the
            // animation will be cached.
            controller.markNeedsReload();
            // Set the animation to open the jackinthebox which will start playing music and
            // eventually do the actual animation. Also sets it to not loop
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.m4carbine.fire", false));
        }
        //user.getItemCooldownManager().set(this, 5); //cooldown to use it again
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public boolean canShoot(World world, PlayerEntity user, Hand hand) {
        AnimationController controller = GeckoLibUtil.getControllerForStack(this.factory, user.getStackInHand(hand), controllerName);
        return controller.getAnimationState() == AnimationState.Stopped;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
