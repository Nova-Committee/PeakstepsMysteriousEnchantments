package committee.nova.pkstmystench.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;

public class PerfectTinkeringEnchantment extends Enchantment {
    public PerfectTinkeringEnchantment() {
        super(
                Rarity.RARE,
                EnchantmentTarget.WEAPON,
                new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}
        );
    }

    @Override
    public int getMinPower(int level) {
        return 20 * level;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return -1.5f;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }
}
