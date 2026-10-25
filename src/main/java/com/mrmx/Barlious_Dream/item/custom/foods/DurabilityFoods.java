package com.mrmx.Barlious_Dream.item.custom.foods;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
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

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        FoodProperties food = stack.get(DataComponents.FOOD);
        if (food == null || food.effects().isEmpty()) {
            return;
        }
        PotionContents visualPotionContents = PotionContents.EMPTY;
        for (FoodProperties.PossibleEffect possibleEffect : food.effects()) {
            visualPotionContents = visualPotionContents.withEffectAdded(possibleEffect.effect());
        }
        Objects.requireNonNull(tooltipComponents);
        visualPotionContents.addPotionTooltip(tooltipComponents::add, 1.0F, context.tickRate());
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}