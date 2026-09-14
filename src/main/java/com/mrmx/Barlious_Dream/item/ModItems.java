package com.mrmx.Barlious_Dream.item;

import com.mrmx.Barlious_Dream.item.custom.HeavyPickaxeItem;
import com.mrmx.Barlious_Dream.item.custom.MacheteItem;
import com.mrmx.Barlious_Dream.item.custom.PlantWhistleCNC;
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final boolean CNC_REQUIRED = ModList.get().isLoaded("cnc");
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Barlious_Dream.MODID);
    public static final DeferredRegister.Items TOOLS = DeferredRegister.createItems(Barlious_Dream.MODID);

    public static final DeferredItem<Item> BARLIOUS_DREAM_LOGO = ITEMS.register("logo",
            () -> new Item(new Item.Properties().stacksTo(1)));

    // Machetes; 1.3 mas de daño, pero 1.16 mas lento que las espadas
    public static final DeferredItem<MacheteItem> IRON_MACHETE = TOOLS.register("iron_machete",
            () -> new MacheteItem(ModToolTiers.IRON_MACHETE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.IRON_MACHETE, 4, -2.7f))));
    public static final DeferredItem<MacheteItem> DIAMOND_MACHETE = TOOLS.register("diamond_machete",
            () -> new MacheteItem(ModToolTiers.DIAMOND_MACHETE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.DIAMOND_MACHETE, 4, -2.7f))));
    public static final DeferredItem<MacheteItem> NETHERITE_MACHETE = TOOLS.register("netherite_machete",
            () -> new MacheteItem(ModToolTiers.NETHERITE_MACHETE, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(ModToolTiers.NETHERITE_MACHETE, 4, -2.7f))));

    // Picos reforzados; +1 de daño, pero +3 de coldoown
    public static final DeferredItem<HeavyPickaxeItem> IRON_HEAVY_PICKAXE = TOOLS.register("iron_heavy_pickaxe",
            () -> new HeavyPickaxeItem(ModToolTiers.IRON_HEAVY_PICKAXE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.IRON_HEAVY_PICKAXE, 2.0f, -3.1f))));
    public static final DeferredItem<HeavyPickaxeItem> DIAMOND_HEAVY_PICKAXE = TOOLS.register("diamond_heavy_pickaxe",
            () -> new HeavyPickaxeItem(ModToolTiers.DIAMOND_HEAVY_PICKAXE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.DIAMOND_HEAVY_PICKAXE, 2.0f, -3.1f))));
    public static final DeferredItem<HeavyPickaxeItem> NETHERITE_HEAVY_PICKAXE = TOOLS.register("netherite_heavy_pickaxe",
            () -> new HeavyPickaxeItem(ModToolTiers.NETHERITE_HEAVY_PICKAXE, new Item.Properties().fireResistant()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.NETHERITE_HEAVY_PICKAXE, 2.0f, -3.1f))));

    // Extra
    public static final DeferredItem<Item> PLANT_WHISTLE = CNC_REQUIRED
            ? ITEMS.register("plant_whistle",
            () -> new PlantWhistleCNC(new Item.Properties().stacksTo(1))): null;

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus); TOOLS.register(eventBus);
    }
}
