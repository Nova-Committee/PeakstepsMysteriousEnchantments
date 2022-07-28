package committee.nova.pkstmystench.mixin;

import committee.nova.pkstmystench.enchantment.EnchantmentInit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Nullable public ClientPlayerEntity player;

    @Inject(
            method = "doAttack",
            cancellable = true,
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayerInteractionManager;attackEntity(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/entity/Entity;)V")
    )
    private void onDoAttack(CallbackInfoReturnable<Boolean> info) {
        if (player != null) {
            if (EnchantmentHelper.fromNbt(player.getStackInHand(player.getActiveHand()).getEnchantments())
                    .containsKey(EnchantmentInit.PRE_SHARPENING)) {
                if (!this.isCooldownFinished()) {
                    info.setReturnValue(false);
                }
            }
        }
    }

    @Unique
    private boolean isCooldownFinished() {
        return this.player.getAttackCooldownProgress(0f) >= 1f;
    }
}
