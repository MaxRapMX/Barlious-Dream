package com.mrmx.Barlious_Dream.network;

import net.minecraft.world.item.ItemStack;
import java.util.List;

public class ClientEnderCache {
    private static List<ItemStack> contents = List.of();
    public static void set(List<ItemStack> items) { contents = items; }
    public static List<ItemStack> get() { return contents; }
}