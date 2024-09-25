package net.kndude.modofsoul.item;

import net.kndude.modofsoul.ModOfSoul;
import net.kndude.modofsoul.item.complex.SoulFireWandItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {

    // Kind of like a List of items
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModOfSoul.MODID);

    // The First Soul item details
    public static final DeferredItem<Item> FIRSTSOUL = ITEMS.register("firstsoul",() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SOUL = ITEMS.register("soul",() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SOULINGOT = ITEMS.register("soulingot",() -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SOULWAND = ITEMS.register("soulwand",() -> new SoulFireWandItem(
            new Item.Properties().durability(100)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final Tier SOULTIER = new SimpleTier(
            // The tag that determines what blocks this tool cannot break.
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            // Determines the durability of the tier.
            350,
            // Determines the mining speed of the tier. Unused by swords.
            // Stone uses 4, iron uses 6.
            7f,
            // Determines the attack damage bonus. Different tools use this differently. For example, swords do (getAttackDamageBonus() + 4) damage.
            3.5f,
            // Determines the enchantability of the tier. This represents how good the enchantments on this tool will be.
            20,
            // Determines the repair ingredient of the tier. Use a supplier for lazy initializing.
            () -> Ingredient.of(ModItems.SOULINGOT)
    );

    public static final Supplier<AxeItem> SOULAXE = ITEMS.register("soulaxe", () -> new AxeItem(SOULTIER,new Item.Properties().attributes(
    AxeItem.createAttributes(
            SOULTIER, 6,-2
            )
        )
    ));
    public static final Supplier<PickaxeItem> SOULPICKAXE = ITEMS.register("soulpickaxe", () -> new PickaxeItem(SOULTIER,new Item.Properties().attributes(
            PickaxeItem.createAttributes(
                    SOULTIER, 1,-2.8f
            )
    )
    ));
    public static final Supplier<ShovelItem> SOULSHOVEL = ITEMS.register("soulshovel", () -> new ShovelItem(SOULTIER,new Item.Properties().attributes(
            ShovelItem.createAttributes(
                    SOULTIER, 1.5f,-3f
            )
    )
    ));
    public static final Supplier<HoeItem> SOULHOE = ITEMS.register("soulhoe", () -> new HoeItem(SOULTIER,new Item.Properties().attributes(
            HoeItem.createAttributes(
                    SOULTIER, -4f,0f
            )
    )
    ));

}
