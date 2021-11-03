package safro.fabric.enchantments.mixin.enchantment;


import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Maps;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;

import java.util.Map;
import java.util.UUID;

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

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow public float bodyYaw;
    private final Map<EntityAttribute, EntityAttributeModifier> attributeModifiers = Maps.newHashMap();

    @Inject(at = @At("TAIL"), method = "tick", cancellable = true)

    public void TankArmor(CallbackInfo ci) {


        if ((EnchantmentHelper.getLevel(FabricEnchantments.TANK, this.getEquippedStack(EquipmentSlot.CHEST)) >= 1)) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 40, EnchantmentHelper.getLevel(FabricEnchantments.TANK, this.getEquippedStack(EquipmentSlot.CHEST)), true, false));
        }
    }
}
