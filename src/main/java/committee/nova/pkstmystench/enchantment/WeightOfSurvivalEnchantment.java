package committee.nova.pkstmystench.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;

public class WeightOfSurvivalEnchantment extends Enchantment {
    public WeightOfSurvivalEnchantment() {
        super(
                Rarity.VERY_RARE,
                EnchantmentTarget.WEAPON,
                new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}
        );
    }

    @Override
    public int getMinPower(int level) {
        return 28 * level;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public boolean isAvailableForRandomSelection() {
        return false;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return group == EntityGroup.AQUATIC ? 3.0f : 0;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        super.onTargetDamaged(user, target, level);
        if (target instanceof LivingEntity living) {
            float userHealthPercent = user.getHealth() / user.getMaxHealth();
            float targetHealthPercent = living.getHealth() / living.getMaxHealth();
            if (userHealthPercent < targetHealthPercent) {
                living.damage(DamageSource.mob(user), (user.getHealth() + living.getHealth()) / 4);
            } else {
                user.damage(DamageSource.mob(user), user.getHealth() / 3);
            }
        }
    }
}
