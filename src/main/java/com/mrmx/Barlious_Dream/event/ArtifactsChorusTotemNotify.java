package com.mrmx.Barlious_Dream.event;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = "mrmx_dream", bus = EventBusSubscriber.Bus.GAME)
public class ArtifactsChorusTotemNotify {

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerDamage(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (player.getHealth() <= event.getAmount()) {
                ItemStack totemStack = findChorusTotem(player);
                if (!totemStack.isEmpty()) {
                    TotemPoppedGlobalNotify.triggerBasicPopMessage(player, totemStack);
                }
            }
        }
    }

    private static ItemStack findChorusTotem(ServerPlayer player) {
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = player.getItemInHand(hand);
            if (isArtifactsChorusTotem(stack)) {
                return stack;
            }
        }
        if (ModList.get().isLoaded("curios")) {
            return CuriosCompat.findChorusTotemInCurios(player);
        }

        return ItemStack.EMPTY;
    }

    private static boolean isArtifactsChorusTotem(ItemStack stack) {
        if (stack.isEmpty()) return false;
        String registryName = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
        return registryName.equals("artifacts:chorus_totem");
    }
}