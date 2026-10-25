package com.mrmx.Barlious_Dream.event;

import com.mrmx.Barlious_Dream.item.ModItems;
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import com.mrmx.cnc_sa.item.SA_ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import uwu.llkc.cnc.common.init.ItemRegistry;
import static com.mrmx.Barlious_Dream.registry.Barlious_Dream.isModInVersionRange;

@EventBusSubscriber(modid = Barlious_Dream.MODID)
public class AddingItemsInCreativeTabs {

    @SubscribeEvent
    public static void AddingItemsInCreativeTabs(BuildCreativeModeTabContentsEvent event) {

        // Tools and Utilities
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.insertAfter(
                    new ItemStack(Items.IRON_PICKAXE), ModItems.IRON_HEAVY_PICKAXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack(Items.IRON_AXE), ModItems.IRON_MACHETE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack(Items.DIAMOND_PICKAXE), ModItems.DIAMOND_HEAVY_PICKAXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack(Items.DIAMOND_AXE), ModItems.DIAMOND_MACHETE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack(Items.NETHERITE_PICKAXE), ModItems.NETHERITE_HEAVY_PICKAXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack(Items.NETHERITE_AXE), ModItems.NETHERITE_MACHETE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            if (!(isModInVersionRange("vanillabackport", "[1.2.0,)")))
                event.insertAfter(
                    new ItemStack(Items.ELYTRA), ModItems.ENDER_BAG.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            if (isModInVersionRange("vanillabackport", "[1.2.0,)"))
                net.minecraft.core.registries.BuiltInRegistries.ITEM.getOptional(
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("minecraft", "bundle")
                ).ifPresent(bundle -> {
                    event.insertBefore(new ItemStack(bundle),
                            ModItems.ENDER_BAG.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                });
        }

        // Combat
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.insertAfter(
                    new ItemStack(Items.NETHERITE_AXE), ModItems.IRON_MACHETE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack((ItemLike) ModItems.IRON_MACHETE), ModItems.DIAMOND_MACHETE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack((ItemLike) ModItems.DIAMOND_MACHETE), ModItems.NETHERITE_MACHETE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        // Ingredients
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.insertBefore(
                    new ItemStack(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), ModItems.BARLIOUS_UPGRADE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            if (ModList.get().isLoaded("corn_delight"))
                event.insertBefore(
                        new ItemStack(Items.NETHER_WART), ModItems.ARTISANAL_CUP.get().getDefaultInstance(),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        // Food and Drinks
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.insertAfter(
                    new ItemStack(Items.CHORUS_FRUIT), ModItems.CRYING_CHORUS_FRUIT.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            // Atoles
            if (ModList.get().isLoaded("corn_delight")) {
                // without cnc sa
                if (!(ModList.get().isLoaded("cnc_sa"))) {
                    event.insertBefore(
                            new ItemStack(Items.HONEY_BOTTLE), ModItems.VANILLA_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) ModItems.VANILLA_ATOLE), ModItems.CHOCOLATE_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) ModItems.CHOCOLATE_ATOLE), ModItems.APPLE_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    if (ModList.get().isLoaded("cnc"))
                        event.insertBefore(
                                new ItemStack((ItemLike) ModItems.APPLE_ATOLE), ModItems.CHERRY_ATOLE.get().getDefaultInstance(),
                                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) ModItems.APPLE_ATOLE), ModItems.CARROT_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) ModItems.CARROT_ATOLE), ModItems.GLOW_BERRIES_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) ModItems.GLOW_BERRIES_ATOLE), ModItems.CHORUS_FRUIT_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) ModItems.CHORUS_FRUIT_ATOLE), ModItems.RICE_PUDING_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) ModItems.RICE_PUDING_ATOLE), ModItems.CHAMPURRADO.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    if (ModList.get().isLoaded("cnc")) {
                        event.insertBefore(
                                new ItemStack((ItemLike) ModItems.CHAMPURRADO), ModItems.WALNUT_ATOLE.get().getDefaultInstance(),
                                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(
                                new ItemStack((ItemLike) ModItems.WALNUT_ATOLE), ModItems.VANILLA_WITH_WALNUT_ATOLE.get().getDefaultInstance(),
                                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(
                                new ItemStack((ItemLike) ModItems.VANILLA_WITH_WALNUT_ATOLE), ModItems.VANILLA_WITH_CHERRIES_ATOLE.get().getDefaultInstance(),
                                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    }

                }
                // with cnc sa
                if (ModList.get().isLoaded("cnc_sa")) {
                    event.insertAfter(
                            new ItemStack((ItemLike) ItemRegistry.VANILLA_MILKSHAKE),
                            ModItems.VANILLA_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) ItemRegistry.CHOCOLATE_MILKSHAKE),
                            ModItems.CHOCOLATE_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) ItemRegistry.CHERRY_MILKSHAKE),
                            ModItems.CHERRY_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) SA_ModItems.APPLE_MILKSHAKE),
                            ModItems.APPLE_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) SA_ModItems.CARROT_MILKSHAKE),
                            ModItems.CARROT_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) SA_ModItems.GLOW_BERRIES_MILKSHAKE),
                            ModItems.GLOW_BERRIES_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) SA_ModItems.CHORUS_FRUIT_MILKSHAKE),
                            ModItems.CHORUS_FRUIT_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) SA_ModItems.RICE_PUDING_MILKSHAKE),
                            ModItems.RICE_PUDING_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) SA_ModItems.WALNUT_MILKSHAKE),
                            ModItems.WALNUT_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) SA_ModItems.VANILLA_WITH_WALNUT_MILKSHAKE),
                            ModItems.VANILLA_WITH_WALNUT_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) SA_ModItems.VANILLA_WITH_CHERRIES_MILKSHAKE),
                            ModItems.VANILLA_WITH_CHERRIES_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) SA_ModItems.THE_SECRET_MILKSHAKE),
                            ModItems.CHAMPURRADO.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            }
        }

        // CNC Tab
        if (ModList.get().isLoaded("cnc")) {
            ResourceKey<CreativeModeTab> cncTabKey = ResourceKey.create(
                    Registries.CREATIVE_MODE_TAB,
                    ResourceLocation.fromNamespaceAndPath("cnc", "cnc_tab")
            ); if (event.getTabKey() == cncTabKey) {
                event.insertBefore(
                        new ItemStack((ItemLike) ItemRegistry.PLANT_FOOD), ModItems.PLANT_WHISTLE.get().getDefaultInstance(),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

                // Atoles without cnc_sa
                if (ModList.get().isLoaded("corn_delight") && !(ModList.get().isLoaded("cnc_sa"))) {
                    event.insertAfter(
                            new ItemStack((ItemLike) ItemRegistry.CHERRY_MILKSHAKE), ModItems.CHERRY_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) ModItems.CHERRY_ATOLE), ModItems.WALNUT_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) ModItems.WALNUT_ATOLE), ModItems.VANILLA_WITH_WALNUT_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) ModItems.VANILLA_WITH_WALNUT_ATOLE), ModItems.VANILLA_WITH_CHERRIES_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }

                // Atoles with cnc_sa
                if (ModList.get().isLoaded("corn_delight") && ModList.get().isLoaded("cnc_sa")) {
                    event.insertBefore(
                            new ItemStack((ItemLike) SA_ModItems.APPLE_MILKSHAKE),
                            ModItems.CHERRY_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) SA_ModItems.WALNUT_MILKSHAKE),
                            ModItems.WALNUT_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) SA_ModItems.VANILLA_WITH_WALNUT_MILKSHAKE),
                            ModItems.VANILLA_WITH_WALNUT_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    event.insertAfter(
                            new ItemStack((ItemLike) SA_ModItems.VANILLA_WITH_CHERRIES_MILKSHAKE),
                            ModItems.VANILLA_WITH_CHERRIES_ATOLE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                }
            }
        }

        // VB Copper Tools
        if (isModInVersionRange("vanillabackport", "[1.2.0,)")) {
            if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
                net.minecraft.core.registries.BuiltInRegistries.ITEM.getOptional(
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("minecraft", "copper_pickaxe")
                ).ifPresent(copper_pickaxe -> {
                    event.insertAfter(new ItemStack(copper_pickaxe),
                            ModItems.VB_COPPER_HEAVY_PICKAXE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                });
                net.minecraft.core.registries.BuiltInRegistries.ITEM.getOptional(
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("minecraft", "copper_axe")
                ).ifPresent(copper_axe -> {
                    event.insertAfter(new ItemStack(copper_axe),
                            ModItems.VB_COPPER_MACHETE.get().getDefaultInstance(),
                            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                    );
                });
            } if (event.getTabKey() == CreativeModeTabs.COMBAT) {
                event.insertBefore(
                        new ItemStack((ItemLike) ModItems.IRON_MACHETE), ModItems.VB_COPPER_MACHETE.get().getDefaultInstance(),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }

    }
    //@SubscribeEvent
      //public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        //System.out.println("[DEBUG TAB IDENTIFIER] Tab ID: " + event.getTabKey().location().toString());
          //}
}
