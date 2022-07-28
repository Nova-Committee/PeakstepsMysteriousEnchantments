package committee.nova.pkstmystench.event;

import committee.nova.pkstmystench.enchantment.EnchantmentInit;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;

public class EventInit {
    public static void registerAll() {
        EntityElytraEvents.CUSTOM.register(ElytraLikeEvent::onTryToUseElytraLike);
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(Items.ENCHANTED_BOOK).apply(
                                EnchantRandomlyLootFunction.create().add(EnchantmentInit.CRYSTAL_WINGS)
                        ).weight(4));
                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
