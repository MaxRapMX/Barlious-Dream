package com.mrmx.Barlious_Dream.item.custom.tools;

import com.mrmx.Barlious_Dream.client.ModKeyMappings;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;
import java.util.List;

public class Ender_Bag extends Item {
    public Ender_Bag(Properties properties) { super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            openEnderBagMenu(serverPlayer);
        } return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    public static void openEnderBagMenu(ServerPlayer serverPlayer) {
        Level level = serverPlayer.level();
        level.playSound(null, serverPlayer.blockPosition(),
                SoundEvents.ENDER_CHEST_OPEN, SoundSource.PLAYERS, 0.75f, 1.0f);
        level.playSound(null, serverPlayer.blockPosition(),
                SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 0.5f, 1.0f);

        serverPlayer.openMenu(new SimpleMenuProvider(
                (id, inventory, p) -> new ChestMenu(MenuType.GENERIC_9x3, id, inventory, p.getEnderChestInventory(), 3) {
                    @Override
                    public void removed(Player player) {
                        super.removed(player);
                        if (!player.level().isClientSide()) {
                            player.level().playSound(null, player.blockPosition(),
                                    SoundEvents.ENDER_CHEST_CLOSE, SoundSource.PLAYERS, 0.75f, 1.0f);
                        }
                    }
                }, Component.translatable("container.mrmx_dream.ender_bag")
        ));
    }

    public static boolean isEquippedInCurios(Player player) {
        if (!ModList.get().isLoaded("curios")) return false;
        return top.theillusivec4.curios.api.CuriosApi.getCuriosInventory(player)
                .map(handler -> handler.isEquipped(stack -> stack.getItem() instanceof Ender_Bag))
                .orElse(false);
    }

    @Override
    public boolean canFitInsideContainerItems(ItemStack stack) {
        return false;
    } @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip_1.mrmx_dream.ender_bag").withStyle(ChatFormatting.GRAY)
                .append(Component.translatable("tooltip_2.mrmx_dream.ender_bag").withStyle(ChatFormatting.LIGHT_PURPLE)));
        if (Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.keybinds.left-click")
                    .withStyle(ChatFormatting.GOLD).append(Component.translatable("tooltip.mrmx_dream.ender_bag.left-click").withStyle(ChatFormatting.YELLOW)));
            if (ModList.get().isLoaded("curios")) {
                Component keyName = ModKeyMappings.OPEN_ENDER_BAG.getTranslatedKeyMessage();
                tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.keybinds.custom", keyName)
                        .withStyle(ChatFormatting.GOLD).append(Component.translatable("tooltip.mrmx_dream.ender_bag.custom_key").withStyle(ChatFormatting.YELLOW)));
            }
        } else {
            tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.keybinds.shift")
                    .withStyle(ChatFormatting.AQUA).append(Component.translatable("tooltip.mrmx_dream.keybinds.shift.description").withStyle(ChatFormatting.DARK_AQUA)));
        } super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}



