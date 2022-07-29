package committee.nova.pkstmystench.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;

public class PreSharpeningEnchantment extends Enchantment {
    public PreSharpeningEnchantment() {
        super(
                Rarity.RARE,
                EnchantmentTarget.WEAPON,
                new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}
        );
    }

    @Override
    public int getMinPower(int level) {
        return 15 * level;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        return 1.8f * level;
    }
}
