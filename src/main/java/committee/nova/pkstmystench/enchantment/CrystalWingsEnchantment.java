package committee.nova.pkstmystench.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class CrystalWingsEnchantment extends Enchantment {
    public CrystalWingsEnchantment() {
        super(
                Rarity.VERY_RARE,
                EnchantmentTarget.ARMOR_CHEST,
                new EquipmentSlot[] {EquipmentSlot.CHEST}
        );
    }

    @Override
    public int getMinPower(int level) {
        return 25 * level;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
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
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }
}
