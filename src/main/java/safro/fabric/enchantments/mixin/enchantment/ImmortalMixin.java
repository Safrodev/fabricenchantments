package safro.fabric.enchantments.mixin.enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;

@Mixin(ItemEntity.class)
public abstract class ImmortalMixin {

    @Shadow public abstract ItemStack getStack();

    @Shadow private int itemAge;

    @Inject(method = "tick", at = @At("HEAD"))
    private void stopDespawn(CallbackInfo ci) {
        if (this.getStack().hasEnchantments() && EnchantmentHelper.getLevel(FabricEnchantments.BEHEADING, this.getStack()) >= 1) {
            this.itemAge = 1;
        }
    }
}
