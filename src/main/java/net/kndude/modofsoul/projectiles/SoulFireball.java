package net.kndude.modofsoul.projectiles;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class SoulFireball extends Fireball {
    public SoulFireball(EntityType<? extends net.minecraft.world.entity.projectile.SmallFireball> entityType, Level level) {
        super(entityType, level);
    }

    public SoulFireball(Level level, LivingEntity owner, Vec3 movement) {
        super(EntityType.SMALL_FIREBALL, owner, movement, level);
    }

    public SoulFireball(Level level, double x, double y, double z, Vec3 movement) {
        super(EntityType.SMALL_FIREBALL, x, y, z, movement, level);
    }

    /**
     * Called when the projectile hits an entity
     */
    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (this.level() instanceof ServerLevel serverlevel) {
            Entity entity1 = result.getEntity();
            Entity $$4 = this.getOwner();
            System.out.println($$4);
            System.out.println($$4 == entity1);
            int $$5 = entity1.getRemainingFireTicks();
            entity1.igniteForSeconds(2.0F);
            DamageSource $$6 = this.damageSources().fireball(this, $$4);
            if (!entity1.hurt($$6, 5.0F)) {
                entity1.setRemainingFireTicks($$5);
            } else {
                EnchantmentHelper.doPostAttackEffects(serverlevel, entity1, $$6);
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (!this.level().isClientSide) {
            Entity entity = this.getOwner();
            if (!(entity instanceof Mob) || net.neoforged.neoforge.event.EventHooks.canEntityGrief(this.level(), entity)) {
                BlockPos blockpos = result.getBlockPos().relative(result.getDirection());
            }
        }
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false;
    }
}

