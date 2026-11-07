package com.mrmx.Barlious_Dream.item.custom.tools;

import com.mrmx.Barlious_Dream.config.ArrowHitConfig;
import com.mrmx.Barlious_Dream.registry.ModAttachments;
import com.mrmx.Barlious_Dream.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import uwu.llkc.cnc.common.entities.plants.CNCPlant;

import java.util.List;

public class PlantWhistleCNC extends Item {
    public PlantWhistleCNC(Properties properties) {
        super(properties);
    }
    int cooldown = 20;
    int successfull_cooldown = cooldown / 2;

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            // Evaluamos si el barrido en área tuvo éxito o falló
            boolean success = blowWhistleForNearbyPlants(level, player);
            if (success) {
                player.getCooldowns().addCooldown(this, successfull_cooldown);
            } else {
                player.getCooldowns().addCooldown(this, cooldown); // Cooldown si falla / no hay plantas
            }
        } return InteractionResultHolder.success(itemstack);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!(target instanceof CNCPlant plant)) return InteractionResult.PASS;
        if (player.getCooldowns().isOnCooldown(player.getMainHandItem().getItem())) return InteractionResult.PASS;
        if (plant.getOwnerUUID() == null) {
            if (!player.level().isClientSide()) {
                player.level().playSound(null, player.blockPosition(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 0.5f, 1.0f);
                player.displayClientMessage(Component.translatable("message.mrmx_dream.plant_whistle.wild_plant"), true);
                player.getCooldowns().addCooldown(this, cooldown);
            } return InteractionResult.SUCCESS;
        }
        if (!plant.getOwnerUUID().equals(player.getUUID())) {
            if (!player.level().isClientSide()) {
                player.level().playSound(null, player.blockPosition(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 0.5f, 1.0f);
                player.displayClientMessage(Component.translatable("message.mrmx_dream.plant_whistle.not_your_plant"), true);
                player.getCooldowns().addCooldown(this, cooldown);
            } return InteractionResult.SUCCESS;
        }

        if (player.level().isClientSide()) { return InteractionResult.SUCCESS; }
        if (player.isShiftKeyDown()) {
            int newMode = cycleMode(plant);
            plant.setData(ModAttachments.PLANT_DEFENSE_MODE.get(), newMode);
            plant.setTarget(null);
            playModeSound(player.level(), player.blockPosition(), newMode);
            player.displayClientMessage(modeMessage(newMode), true);
        } else { boolean success = blowWhistleForNearbyPlants(player.level(), player);
            if (success) {
                player.getCooldowns().addCooldown(this, successfull_cooldown);
            } else {
                player.getCooldowns().addCooldown(this, cooldown);
            }
        } return InteractionResult.SUCCESS;
    }

    private static boolean blowWhistleForNearbyPlants(Level level, Player player) {
        AABB area = player.getBoundingBox().inflate(10.0D);
        List<CNCPlant> nearbyPlants = level.getEntitiesOfClass(CNCPlant.class, area, e -> isOwnedCncPlant(e, player));

        if (nearbyPlants.isEmpty()) {
            player.playNotifySound(ModSounds.PVZ_EWW.get(), SoundSource.PLAYERS, 0.5f, 1.0f);
            player.displayClientMessage(Component.translatable("message.mrmx_dream.plant_whistle.none_nearby"), true);
            return false;
        } int newMode = cycleMode(nearbyPlants.get(0));
        for (CNCPlant plant : nearbyPlants) {
            plant.setData(ModAttachments.PLANT_DEFENSE_MODE.get(), newMode);
            plant.setTarget(null);
        } playModeSound(player.level(), player.blockPosition(), newMode);
        player.displayClientMessage(modeMessage(newMode), true);
        return true;
    }

    private static boolean isOwnedCncPlant(LivingEntity entity, Player player) {
        return entity instanceof CNCPlant plant
                && plant.getOwnerUUID() != null && plant.getOwnerUUID().equals(player.getUUID());
    }

    private static int cycleMode(CNCPlant reference) {
        int current = reference.getData(ModAttachments.PLANT_DEFENSE_MODE.get());
        return (current + 1) % 3;
    }

    public static Component modeMessage(int mode) {
        return switch (mode) {
            case 1 -> Component.translatable("message.mrmx_dream.plant_whistle.only_plant_target_mode");
            case 2 -> Component.translatable("message.mrmx_dream.plant_whistle.no_mercy_mode");
            default -> Component.translatable("message.mrmx_dream.plant_whistle.default_mode");
        };
    }

    private static void playModeSound(Level level, net.minecraft.core.BlockPos pos, int mode) {
        net.minecraft.sounds.SoundEvent sound = switch (mode) {
            case 1 -> ModSounds.WHISTLE_ONLY_PLANT_TARGET.get();
            case 2 -> ModSounds.WHISTLE_NO_MERCY.get();
            default -> ModSounds.WHISTLE_DEFAULT.get();
        };

        float volume = (mode == 2) ? 0.5f : 1.0f;
        level.playSound(null, pos, sound, SoundSource.PLAYERS, volume, 1.0f);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.plant_whistle").withStyle(ChatFormatting.GRAY));
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.keybinds.left-click")
                    .withStyle(ChatFormatting.GREEN).append(Component.translatable("tooltip.mrmx_dream.plant_whistle.left-click").withStyle(ChatFormatting.DARK_GREEN)));
            tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.keybinds.right-click")
                    .withStyle(ChatFormatting.GREEN).append(Component.translatable("tooltip.mrmx_dream.plant_whistle.right-click").withStyle(ChatFormatting.DARK_GREEN)));
            tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.keybinds.shift_right-click")
                    .withStyle(ChatFormatting.GREEN).append(Component.translatable("tooltip.mrmx_dream.plant_whistle.shift_right-click").withStyle(ChatFormatting.DARK_GREEN)));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.keybinds.shift")
                    .withStyle(ChatFormatting.AQUA).append(Component.translatable("tooltip.mrmx_dream.keybinds.shift.description").withStyle(ChatFormatting.DARK_AQUA)));
        } super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
