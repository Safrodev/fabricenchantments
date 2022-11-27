package safro.fabric.enchantments.mixin;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.enchantment.TankEnchantment;

@Mixin(PlayerEntity.class)
public abstract class TankMixin {

    @Shadow public abstract Iterable<ItemStack> getArmorItems();

    private final DefaultedList<ItemStack> cache = DefaultedList.ofSize(4, ItemStack.EMPTY);

    @Inject(method = "tick", at = @At("HEAD"))
    private void tickTank(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        int i = 0;
        for (ItemStack stack : getArmorItems()) {
            ItemStack cacheStack = cache.get(i);
            if (cacheStack.getItem() != stack.getItem()) {
                if (EnchantmentHelper.getLevel(FabricEnchantments.TANK, cacheStack) > 0 && player.getAttributes().hasModifierForAttribute(EntityAttributes.GENERIC_MAX_HEALTH, TankEnchantment.ATTRIBUTE_UUID)) {
                    player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).removeModifier(TankEnchantment.ATTRIBUTE_UUID);
                }
                cache.set(i, stack.copy());
            }
            ++i;
        }

        int lvl = EnchantmentHelper.getLevel(FabricEnchantments.TANK, player.getEquippedStack(EquipmentSlot.CHEST));
        if (lvl > 0 && !player.getAttributes().hasModifierForAttribute(EntityAttributes.GENERIC_MAX_HEALTH, TankEnchantment.ATTRIBUTE_UUID)) {
            EntityAttributeModifier modifier = new EntityAttributeModifier(TankEnchantment.ATTRIBUTE_UUID, "Tank Enchantment", 1.5D * lvl, EntityAttributeModifier.Operation.ADDITION);
            player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addTemporaryModifier(modifier);
        }
    }
}
