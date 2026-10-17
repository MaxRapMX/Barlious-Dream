package com.mrmx.Barlious_Dream.item.custom.foods;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

// This Class use code adapted from "Crops ´n´ Corpses" mod, by KaiCoyote and Itskillerluc
// used under the MIT License.

public class DurabilityFoods extends Item {
    private final Supplier<? extends ItemLike> remnantItem;

    public DurabilityFoods(Properties properties, Supplier<? extends ItemLike> remnantItem) {
        super(properties);
        this.remnantItem = remnantItem;
    } public DurabilityFoods(Properties properties) {
        super(properties);
        this.remnantItem = null;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack copyStack = stack.copy();
        EquipmentSlot slot = livingEntity.getEquipmentSlotForItem(stack);
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);

        if (this.remnantItem != null && this.remnantItem.get() != null) {
            if (copyStack.isDamageableItem()) {
                return copyStack.hurtAndConvertOnBreak(1, this.remnantItem.get().asItem(), livingEntity, slot);
            } if (livingEntity instanceof Player player) {
                return ItemUtils.createFilledResult(stack, player, new ItemStack(this.remnantItem.get()));
            } else {
                return new ItemStack(this.remnantItem.get());
            }
        }

        if (copyStack.isDamageableItem()) {
            return copyStack.hurtAndConvertOnBreak(1, net.minecraft.world.item.Items.AIR, livingEntity, slot);
        } return result;
    }
}