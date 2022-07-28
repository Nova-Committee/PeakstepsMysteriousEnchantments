package committee.nova.pkstmystench.event;

import committee.nova.pkstmystench.enchantment.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AttackEntityEvent {
    public static ActionResult onAttack(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!world.isClient() && !player.isSpectator()) {
            ItemStack stack = player.getStackInHand(hand);
            if (!stack.isEmpty() && stack.isDamageable() && stack.getItem() instanceof SwordItem sword) {
                if (EnchantmentHelper.fromNbt(stack.getEnchantments()).containsKey(EnchantmentInit.PERFECT_TINKERING)) {
                    if (entity.isLiving()) {
                        entity.damage(DamageSource.player(player),
                                (15f + ((sword.getMaterial().getMiningLevel() + 1) * 3f)) * ((Math.round(((float) stack.getDamage() / (float) stack.getMaxDamage()) * 100f)) / 100f));
                        stack.damage(calculateDamageForPerfectTinkering(stack), player.getRandom(), (ServerPlayerEntity) player);
                        return ActionResult.SUCCESS;
                    }
                }
            }
        }
        return ActionResult.PASS;
    }

    private static int calculateDamageForPerfectTinkering(ItemStack stack) {
        if ((float) stack.getDamage() / (float) stack.getMaxDamage() > 0) {
            if ((float) stack.getDamage() / (float) stack.getMaxDamage() < 0.25f) {
                return 2;
            } else if ((float) stack.getDamage() / (float) stack.getMaxDamage() < 0.5f) {
                return 3;
            } else if ((float) stack.getDamage() / (float) stack.getMaxDamage() < 0.75f) {
                return 5;
            } else {
                return 7;
            }
        } else {
            return 1;
        }
    }
}
