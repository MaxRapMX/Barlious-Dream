package com.mrmx.Barlious_Dream.event;

import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import top.theillusivec4.curios.api.CuriosApi;

public class CuriosCompat {
    public static ItemStack findChorusTotemInCurios(ServerPlayer player) {
        final ItemStack[] found = {ItemStack.EMPTY};
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            handler.findFirstCurio(stack -> {
                if (stack.isEmpty()) return false;
                String registryName = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
                return registryName.equals("artifacts:chorus_totem");
            }).ifPresent(slotResult -> found[0] = slotResult.stack());
        });
        return found[0];
    }
}