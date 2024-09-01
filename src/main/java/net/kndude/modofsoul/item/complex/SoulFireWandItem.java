package net.kndude.modofsoul.item.complex;

import net.kndude.modofsoul.projectiles.SoulFireball;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SoulFireWandItem extends Item {
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
            level.playSound(null, player.getOnPos(), SoundEvents.BLAZE_SHOOT, SoundSource.BLOCKS, 0.5f, 0.5f);
            if(!level.isClientSide()){
                // Get player look vector
                Vec3 look = player.getLookAngle();
                Vec3 position = player.getEyePosition();

                // Create and launch a small fireball
                SoulFireball fireball = new SoulFireball(level, look.x, look.y, look.z, look);
                fireball.setPos(position.x, position.y, position.z);
                level.addFreshEntity(fireball); // Add the fireball entity to the world

                itemstack.hurtAndBreak(1,((ServerLevel) level) , player, item -> player.onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
            }
            return InteractionResultHolder.consume(itemstack);
        }
    }
}