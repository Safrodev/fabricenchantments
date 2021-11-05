package safro.fabric.enchantments.mixin.enchantment;


import com.google.common.collect.Maps;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
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
    private final Map<EntityAttribute, EntityAttributeModifier> attributeModifiers = Maps.newHashMap();

    @Shadow public abstract AttributeContainer getAttributes();

    @Shadow @Final private AttributeContainer attributes;
    private boolean enabled = false;

    @Inject(method = "onEquipStack", at = @At("TAIL"), cancellable = true)
    private void tankEnchantment(ItemStack stack, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (EnchantmentHelper.getLevel(FabricEnchantments.TANK, entity.getEquippedStack(EquipmentSlot.CHEST)) >= 1) {
            EntityAttributeModifier entityAttributeModifier = new EntityAttributeModifier(UUID.fromString("5D6F0BA2-1186-46AC-B896-C61C5CEE99CC"), "Health Boost", (double)EnchantmentHelper.getLevel(FabricEnchantments.TANK, entity.getEquippedStack(EquipmentSlot.CHEST)), EntityAttributeModifier.Operation.ADDITION);
            entity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addTemporaryModifier(entityAttributeModifier);
        }
    }
}
