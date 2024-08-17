package net.kndude.knmod.item;

import net.kndude.knmod.ModOfSoul;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    // Kind of like a List of items
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModOfSoul.MODID);

    // The First Soul item details
    public static final DeferredItem<Item> FIRSTSOUL = ITEMS.register("firstsoul",() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SOUL = ITEMS.register("soul",() -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
