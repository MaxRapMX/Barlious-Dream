package com.mrmx.Barlious_Dream.event;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

@EventBusSubscriber(modid = "mrmx_dream", value = Dist.CLIENT)

public class ItemTooltip {

    @SubscribeEvent
    public static void VisualEffects(ItemTooltipEvent event) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(event.getItemStack().getItem());
        if (id.getNamespace().equals("mrmx_dream")) {
            switch (id.getPath()) {
                case "vanilla_atole":
                    Component vanilla_atole = Component.translatable(MobEffects.MOVEMENT_SPEED.value().getDescriptionId());
                    event.getToolTip().add(vanilla_atole.copy().withStyle(ChatFormatting.BLUE)
                            .append(Component.literal(" II (00:45)").withStyle(ChatFormatting.BLUE)));
                    if (ModList.get().isLoaded("farmersdelight")) {
                        var nourishment = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"));
                        if (nourishment.isPresent()) {
                            Component nourishmentEffect = Component.translatable(nourishment.get().value().getDescriptionId());
                            event.getToolTip().add(nourishmentEffect.copy().withStyle(ChatFormatting.BLUE)
                                    .append(Component.literal(" (00:45)").withStyle(ChatFormatting.BLUE)));
                        }
                    } break;
                case "chocolate_atole":
                    Component chocolate_atole = Component.translatable(MobEffects.DIG_SPEED.value().getDescriptionId());
                    event.getToolTip().add(chocolate_atole.copy().withStyle(ChatFormatting.BLUE)
                            .append(Component.literal(" II (01:25)").withStyle(ChatFormatting.BLUE)));
                    if (ModList.get().isLoaded("farmersdelight")) {
                        var nourishment = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"));
                        if (nourishment.isPresent()) {
                            Component nourishmentEffect = Component.translatable(nourishment.get().value().getDescriptionId());
                            event.getToolTip().add(nourishmentEffect.copy().withStyle(ChatFormatting.BLUE)
                                    .append(Component.literal(" (00:45)").withStyle(ChatFormatting.BLUE)));
                        }
                    } break;
                case "cherry_atole":
                    Component cherry_atole = Component.translatable(MobEffects.REGENERATION.value().getDescriptionId());
                    event.getToolTip().add(cherry_atole.copy().withStyle(ChatFormatting.BLUE)
                            .append(Component.literal(" II (00:10)").withStyle(ChatFormatting.BLUE)));
                    if (ModList.get().isLoaded("farmersdelight")) {
                        var nourishment = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"));
                        if (nourishment.isPresent()) {
                            Component nourishmentEffect = Component.translatable(nourishment.get().value().getDescriptionId());
                            event.getToolTip().add(nourishmentEffect.copy().withStyle(ChatFormatting.BLUE)
                                    .append(Component.literal(" (00:45)").withStyle(ChatFormatting.BLUE)));
                        }
                    } break;
                case "rice_puding_atole":
                    Component rice_puding_atole = Component.translatable(MobEffects.DAMAGE_RESISTANCE.value().getDescriptionId());
                    event.getToolTip().add(rice_puding_atole.copy().withStyle(ChatFormatting.BLUE)
                            .append(Component.literal(" (01:45)").withStyle(ChatFormatting.BLUE)));
                    if (ModList.get().isLoaded("farmersdelight")) {
                        var nourishment = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"));
                        if (nourishment.isPresent()) {
                            Component nourishmentEffect = Component.translatable(nourishment.get().value().getDescriptionId());
                            event.getToolTip().add(nourishmentEffect.copy().withStyle(ChatFormatting.BLUE)
                                    .append(Component.literal(" (00:45)").withStyle(ChatFormatting.BLUE)));
                        }
                    } break;
                case "champurrado":
                    Component champurrado = Component.translatable(MobEffects.NIGHT_VISION.value().getDescriptionId());
                    event.getToolTip().add(champurrado.copy().withStyle(ChatFormatting.BLUE)
                            .append(Component.literal(" (01:25)").withStyle(ChatFormatting.BLUE)));
                    if (ModList.get().isLoaded("farmersdelight")) {
                        var nourishment = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"));
                        if (nourishment.isPresent()) {
                            Component nourishmentEffect = Component.translatable(nourishment.get().value().getDescriptionId());
                            event.getToolTip().add(nourishmentEffect.copy().withStyle(ChatFormatting.BLUE)
                                    .append(Component.literal(" (00:45)").withStyle(ChatFormatting.BLUE)));
                        }
                    } break;
            }
        }
    }
}