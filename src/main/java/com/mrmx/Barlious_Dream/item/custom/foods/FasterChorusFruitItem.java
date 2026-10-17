package com.mrmx.Barlious_Dream.item.custom.foods;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ChorusFruitItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FasterChorusFruitItem extends ChorusFruitItem {

    public FasterChorusFruitItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);
        if (!level.isClientSide() && livingEntity instanceof Player player) {
            player.getCooldowns().addCooldown(this, 10);
        }

        return result;
    }
}