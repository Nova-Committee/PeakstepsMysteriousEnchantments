package committee.nova.pkstmystench.enchantment;

import committee.nova.pkstmystench.PeakstepsMysteriousEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EnchantmentInit {
    public static Enchantment ROCKCRASH = new RockcrashEnchantment();
    public static Enchantment PRE_SHARPENING = new PreSharpeningEnchantment();
    public static Enchantment CRYSTAL_WINGS = new CrystalWingsEnchantment();
    public static Enchantment PERFECT_TINKERING = new PerfectTinkeringEnchantment();
    public static Enchantment WEIGHT_OF_SURVIVAL = new WeightOfSurvivalEnchantment();
    public static Enchantment TIDE_FALLS_TIDE_RISES = new TideFallsTideRisesEnchantment();

    public static void registerAll() {
        Registry.register(Registry.ENCHANTMENT,
                new Identifier(PeakstepsMysteriousEnchantments.MODID, "rockcrash"),
                ROCKCRASH);
        Registry.register(Registry.ENCHANTMENT,
                new Identifier(PeakstepsMysteriousEnchantments.MODID, "pre_sharpening"),
                PRE_SHARPENING);
        Registry.register(Registry.ENCHANTMENT,
                new Identifier(PeakstepsMysteriousEnchantments.MODID, "crystal_wings"),
                CRYSTAL_WINGS);
        Registry.register(Registry.ENCHANTMENT,
                new Identifier(PeakstepsMysteriousEnchantments.MODID, "perfect_tinkering"),
                PERFECT_TINKERING);
        Registry.register(Registry.ENCHANTMENT,
                new Identifier(PeakstepsMysteriousEnchantments.MODID, "weight_of_survival"),
                WEIGHT_OF_SURVIVAL);
        Registry.register(Registry.ENCHANTMENT,
                new Identifier(PeakstepsMysteriousEnchantments.MODID, "tide_falls_tide_rises"),
                TIDE_FALLS_TIDE_RISES);
    }
}
