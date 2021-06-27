package safro.fabric.enchantments.mixin;


import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;

@Mixin(PlayerEntity.class)
    public abstract class TankMixin {

    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow public abstract void setAbsorptionAmount(float amount);

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)

    public void TankArmor(CallbackInfo ci) {

        if((EnchantmentHelper.getLevel(FabricEnchantments.TANK_ENCHANTMENT, this.getEquippedStack(EquipmentSlot.CHEST)) >= 1)) {
            this.setAbsorptionAmount(1.0F * (EnchantmentHelper.getLevel(FabricEnchantments.TANK_ENCHANTMENT, this.getEquippedStack(EquipmentSlot.CHEST))));
        }
    }
}
