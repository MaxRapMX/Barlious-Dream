package com.mrmx.Barlious_Dream.client.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class ClientContainerTooltipComponent implements ClientTooltipComponent {
    private final List<ItemStack> items;
    private static final int MAX_COLUMNS = 12;

    public ClientContainerTooltipComponent(TooltipComponent component) {
        if (component instanceof ContainerTooltipComponent EnderBagComponent) {
            List<ItemStack> mergedList = new ArrayList<>();
            for (ItemStack stack : EnderBagComponent.items()) {
                if (stack.isEmpty()) continue;
                boolean found = false;
                for (ItemStack existing : mergedList) {
                    if (ItemStack.isSameItemSameComponents(existing, stack)) {
                        existing.setCount(existing.getCount() + stack.getCount());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    mergedList.add(stack.copy());
                }
            }
            this.items = new ArrayList<>();
            for (ItemStack stack : mergedList) {
                int maxStack = stack.getMaxStackSize();
                int totalCount = stack.getCount();

                while (totalCount > 0) {
                    int countToAdd = Math.min(totalCount, maxStack);
                    ItemStack splitStack = stack.copy();
                    splitStack.setCount(countToAdd);
                    this.items.add(splitStack);
                    totalCount -= countToAdd;
                }
            }
        } else {
            this.items = List.of();
        }
    }



    @Override
    public int getWidth(Font font) {
        if (items.isEmpty()) return 0;
        int columns = Math.min(items.size(), MAX_COLUMNS);
        return columns * 18;
    }

    @Override
    public int getHeight() {
        if (items.isEmpty()) return 0;
        int rows = (int) Math.ceil((double) items.size() / MAX_COLUMNS);
        return (rows * 18) + 2;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        int index = 0;
        for (ItemStack stack : items) {
            int row = index / MAX_COLUMNS;
            int col = index % MAX_COLUMNS;
            int slotX = x + col * 18;
            int slotY = y + row * 18;

            guiGraphics.renderItem(stack, slotX, slotY);
            guiGraphics.renderItemDecorations(font, stack, slotX, slotY);
            index++;
        }
    }
}