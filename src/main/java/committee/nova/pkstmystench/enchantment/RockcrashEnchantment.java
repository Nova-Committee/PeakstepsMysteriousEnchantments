package committee.nova.pkstmystench.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class RockcrashEnchantment extends Enchantment {
    public RockcrashEnchantment() {
        super(
                Rarity.RARE,
                EnchantmentTarget.ARMOR_FEET,
                new EquipmentSlot[] {EquipmentSlot.FEET}
        );
    }

    @Override
    public int getMinPower(int level) {
        return 20 * level;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
