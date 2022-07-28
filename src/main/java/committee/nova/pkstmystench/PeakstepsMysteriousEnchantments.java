package committee.nova.pkstmystench;

import committee.nova.pkstmystench.enchantment.EnchantmentInit;
import committee.nova.pkstmystench.event.EventInit;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PeakstepsMysteriousEnchantments implements ModInitializer {
	public static final String MODID = "pkstmystench";
	public static final Logger LOGGER = LoggerFactory.getLogger("Peakstep's Mysterious Enchantments");

	@Override
	public void onInitialize() {
		EnchantmentInit.registerAll();
		EventInit.registerAll();
		LOGGER.info("Initialized.");
	}
}
