package committee.nova.pkstmystench.event;

import committee.nova.pkstmystench.enchantment.EnchantmentInit;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class ElytraLikeEvent {
    public static boolean onTryToUseElytraLike(LivingEntity entity, boolean tickElytra) {
        for (ItemStack stack : entity.getArmorItems()) {
            if (stack.getItem() instanceof ArmorItem armor
                    && armor.getSlotType().equals(EquipmentSlot.CHEST)) {
                if (EnchantmentHelper.fromNbt(stack.getEnchantments()).containsKey(EnchantmentInit.CRYSTAL_WINGS)) {
                    if (tickElytra) {
                        Random random = new Random();
                        if (random.nextInt(600) == 1) {
                            if (!(entity instanceof PlayerEntity player)) {
                                stack.damage(1, entity.getRandom(), null);
                            } else {
                                if (!player.isCreative()) {
                                    stack.damage(1, entity.getRandom(), null);
                                }
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
