package committee.nova.pkstmystench.mixin;

import committee.nova.pkstmystench.enchantment.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public World world;

    @Shadow public abstract Iterable<ItemStack> getArmorItems();

    @Shadow public abstract boolean isLiving();

    @Shadow public float fallDistance;

    @Shadow public abstract Vec3d getPos();

    @Shadow protected abstract BlockPos getPosWithYOffset(float offset);

    @Shadow @Final protected Random random;

    @Inject(method = "onLanding", at = @At("HEAD"))
    public void onOnLanding(CallbackInfo ci) {
        if (!this.world.isClient()) {
            if (this.isLiving()) {
                for (ItemStack stack : getArmorItems()) {
                    if (stack.getItem() instanceof ArmorItem armor
                            && armor.getSlotType().equals(EquipmentSlot.FEET)) {
                        if (EnchantmentHelper.fromNbt(stack.getEnchantments()).containsKey(EnchantmentInit.ROCKCRASH)) {
                            float fall = this.fallDistance;
                            if (fall > 6) {
                                List<Entity> entitiesNearby = world.getOtherEntities(stack.getHolder(), Box.from(this.getPos()), source ->
                                        source.getPos().isInRange(this.getPos(),
                                                2 + (0.6d * EnchantmentHelper.getLevel(EnchantmentInit.ROCKCRASH, stack)))
                                );
                                for (Entity entity : entitiesNearby) {
                                    if (entity instanceof LivingEntity living) {
                                        living.damage(DamageSource.ANVIL,
                                                (float) ((6f + 0.1 * fall) + (1f * EnchantmentHelper.getLevel(EnchantmentInit.ROCKCRASH, stack))));
                                    }
                                }
                                stack.damage(1, this.random, null);
                                BlockPos downPos = this.getPosWithYOffset(0.2f);
                                if (EnchantmentHelper.getLevel(EnchantmentInit.ROCKCRASH, stack) >= 1) {
                                    breakBlockWithCheck(world, downPos, true);
                                    if (EnchantmentHelper.getLevel(EnchantmentInit.ROCKCRASH, stack) >= 2) {
                                        if (breakBlockWithCheck(world, downPos.west(), true))
                                            stack.damage(1, this.random, null);
                                        if (breakBlockWithCheck(world, downPos.east(), true))
                                            stack.damage(1, this.random, null);
                                        if (breakBlockWithCheck(world, downPos.north(), true))
                                            stack.damage(1, this.random, null);
                                        if (breakBlockWithCheck(world, downPos.south(), true))
                                            stack.damage(1, this.random, null);
                                        if (EnchantmentHelper.getLevel(EnchantmentInit.ROCKCRASH, stack) >= 3) {
                                            if (breakBlockWithCheck(world, downPos.west().north(), true))
                                                stack.damage(1, this.random, null);
                                            if (breakBlockWithCheck(world, downPos.east().north(), true))
                                                stack.damage(1, this.random, null);
                                            if (breakBlockWithCheck(world, downPos.west().south(), true))
                                                stack.damage(1, this.random, null);
                                            if (breakBlockWithCheck(world, downPos.east().south(), true))
                                                stack.damage(1, this.random, null);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isBreakable(World world, BlockPos pos) {
        return world.getBlockState(pos).getHardness(world, pos) > 0;
    }

    private static boolean breakBlockWithCheck(World world, BlockPos pos, boolean drop) {
        if (isBreakable(world, pos)) {
            world.breakBlock(pos, drop);
            return true;
        }
        return false;
    }
}
