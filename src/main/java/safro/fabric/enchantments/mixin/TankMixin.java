package safro.fabric.enchantments.mixin;


import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;

@Mixin(LivingEntity.class)
    public abstract class TankMixin {

    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow public abstract void setAbsorptionAmount(float amount);


    @Shadow public abstract float getMaxHealth();

    @Shadow public abstract float getHealth();

    @Shadow public abstract void setHealth(float health);

    @Shadow public abstract AttributeContainer getAttributes();

    @Shadow @Final private AttributeContainer attributes;

    @Shadow @Nullable public abstract EntityAttributeInstance getAttributeInstance(EntityAttribute attribute);

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)

    public void TankArmor(CallbackInfo ci) {

        if ((EnchantmentHelper.getLevel(FabricEnchantments.TANK, this.getEquippedStack(EquipmentSlot.CHEST)) >= 1)) {

            this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(20 + (EnchantmentHelper.getLevel(FabricEnchantments.TANK, this.getEquippedStack(EquipmentSlot.CHEST))));
        }
    }
}
