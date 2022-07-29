package committee.nova.pkstmystench.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class TideFallsTideRisesEnchantment extends Enchantment {
    public TideFallsTideRisesEnchantment() {
        super(
                Rarity.VERY_RARE,
                EnchantmentTarget.ARMOR_CHEST,
                new EquipmentSlot[] {EquipmentSlot.CHEST}
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
        return 3;
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
}
