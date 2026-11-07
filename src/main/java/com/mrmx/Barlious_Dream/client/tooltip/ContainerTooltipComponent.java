package com.mrmx.Barlious_Dream.client.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import java.util.List;

public record ContainerTooltipComponent(List<ItemStack> items) implements TooltipComponent { }
