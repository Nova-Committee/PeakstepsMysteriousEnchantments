package committee.nova.pkstmystench.event;

import committee.nova.pkstmystench.enchantment.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerDeathEvent {
    public static boolean onDeath(ServerPlayerEntity player, DamageSource damageSource, float damageAmount) {
        if (!damageSource.isOutOfWorld()) {
            for (ItemStack stack : player.getArmorItems()) {
                if (stack.getItem() instanceof ArmorItem armor
                        && armor.getSlotType() == EquipmentSlot.CHEST) {
                    if (EnchantmentHelper.fromNbt(stack.getEnchantments())
                            .containsKey(EnchantmentInit.TIDE_FALLS_TIDE_RISES)) {
                        if (!stack.damage(stack.getMaxDamage() / (2 + EnchantmentHelper.getLevel(EnchantmentInit.TIDE_FALLS_TIDE_RISES, stack)),
                                player.getRandom(), player)) {
                            player.setHealth(Math.min(armor.getProtection() * 2, player.getMaxHealth()));
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 600, 3));
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
