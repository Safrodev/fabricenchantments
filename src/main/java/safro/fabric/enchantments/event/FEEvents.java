package safro.fabric.enchantments.event;

import net.fabricmc.fabric.api.item.v1.ModifyItemAttributeModifiersCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import safro.fabric.enchantments.FabricEnchantments;

public class FEEvents {

    public static void init() {
        ModifyItemAttributeModifiersCallback.EVENT.register((stack, slot, attributeModifiers) -> {
            if (slot == EquipmentSlot.CHEST) {
                int i = EnchantmentHelper.getLevel(FabricEnchantments.TANK, stack);
                if (i > 0) {
                    EntityAttributeModifier modifier = new EntityAttributeModifier("5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 1.5D * i, EntityAttributeModifier.Operation.ADDITION);
                    stack.addAttributeModifier(EntityAttributes.GENERIC_MAX_HEALTH, modifier, slot);
                }
            }
        });
    }
}
