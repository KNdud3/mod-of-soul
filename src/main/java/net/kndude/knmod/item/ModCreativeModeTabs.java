package net.kndude.knmod.item;

import net.kndude.knmod.ModOfSoul;
import net.kndude.knmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModOfSoul.MODID);

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }

    public static final Supplier<CreativeModeTab> MOD_OF_SOUL_ITEMS_TAB =
            CREATIVE_MODE_TAB.register("mod_of_soul_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.FIRSTSOUL.get()))
                    .title(Component.translatable("creativetab.modofsoul.mod_of_soul"))
                    .displayItems((itemDisplayParameters, output) -> { //Add items in here
                        output.accept(ModItems.FIRSTSOUL);
                        output.accept(ModBlocks.COMPACTSOUL);
                    })
                    .build());

}
