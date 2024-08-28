package net.kndude.modofsoul.item.complex;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.Properties;

public class SoulFireWandItem extends Item implements ProjectileItem {
    public SoulFireWandItem(Properties properties){
        super(properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(Items.BLAZE_ROD) || super.isValidRepairItem(toRepair, repair);
    }

    private static boolean isTooDamagedToUse(ItemStack stack) {
        return stack.getDamageValue() >= stack.getMaxDamage() - 1;
    }


    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (isTooDamagedToUse(itemstack)) {
            return InteractionResultHolder.fail(itemstack);
        }
        else {
            player.startUsingItem(hand);
            if(!level.isClientSide()){
                level.playSound(player, player.getOnPos(), SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1f,1f);
                itemstack.hurtAndBreak(1,((ServerLevel) level) , player, item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            }
            return InteractionResultHolder.consume(itemstack);
        }
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        return null;
    }
}
