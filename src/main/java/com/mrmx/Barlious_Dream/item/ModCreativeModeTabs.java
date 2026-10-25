package com.mrmx.Barlious_Dream.item;

import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.mrmx.Barlious_Dream.registry.Barlious_Dream.isModInVersionRange;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Barlious_Dream.MODID);

    public static final Supplier<CreativeModeTab> BARLIOUS_DREAM_CONTENT_TAB = CREATIVE_MODE_TAB.register("barlious_dream_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BARLIOUS_DREAM_LOGO.get()))
                    .title(Component.translatable("creativetab.mrmx_dream.content"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BARLIOUS_UPGRADE);
                        if (ModList.get().isLoaded("cnc")) output.accept(ModItems.PLANT_WHISTLE);
                        if (isModInVersionRange("vanillabackport", "[1.2.0,)")) output.accept(ModItems.VB_COPPER_MACHETE);
                        output.accept(ModItems.IRON_MACHETE);
                        output.accept(ModItems.DIAMOND_MACHETE);
                        output.accept(ModItems.NETHERITE_MACHETE);
                        output.accept(ModItems.ENDER_BAG);
                        if (isModInVersionRange("vanillabackport", "[1.2.0,)")) output.accept(ModItems.COPPER_HEAVY_HEAD);
                        if (isModInVersionRange("vanillabackport", "[1.2.0,)")) output.accept(ModItems.VB_COPPER_HEAVY_PICKAXE);
                        output.accept(ModItems.IRON_HEAVY_HEAD);
                        output.accept(ModItems.IRON_HEAVY_PICKAXE);
                        output.accept(ModItems.DIAMOND_HEAVY_HEAD);
                        output.accept(ModItems.DIAMOND_HEAVY_PICKAXE);
                        output.accept(ModItems.NETHERITE_HEAVY_HEAD);
                        output.accept(ModItems.NETHERITE_HEAVY_PICKAXE);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.ARTISANAL_CUP);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.VANILLA_ATOLE);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.CHOCOLATE_ATOLE);
                        if (ModList.get().isLoaded("corn_delight") && ModList.get().isLoaded("cnc")) output.accept(ModItems.CHERRY_ATOLE);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.APPLE_ATOLE);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.CARROT_ATOLE);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.GLOW_BERRIES_ATOLE);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.CHORUS_FRUIT_ATOLE);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.RICE_PUDING_ATOLE);
                        if (ModList.get().isLoaded("corn_delight") && ModList.get().isLoaded("cnc")) output.accept(ModItems.WALNUT_ATOLE);
                        if (ModList.get().isLoaded("corn_delight") && ModList.get().isLoaded("cnc")) output.accept(ModItems.VANILLA_WITH_WALNUT_ATOLE);
                        if (ModList.get().isLoaded("corn_delight") && ModList.get().isLoaded("cnc")) output.accept(ModItems.VANILLA_WITH_CHERRIES_ATOLE);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.CHAMPURRADO);
                        output.accept(ModItems.CRYING_CHORUS_FRUIT);
                    }).build());

    public static void register(IEventBus eventBus ) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
