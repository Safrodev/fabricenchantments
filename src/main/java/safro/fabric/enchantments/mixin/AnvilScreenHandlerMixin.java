package safro.fabric.enchantments.mixin;

import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import safro.fabric.enchantments.enchantment.AutoSmeltEnchantment;
import safro.fabric.enchantments.enchantment.DoubleSwingEnchantment;
import safro.fabric.enchantments.enchantment.GodOfThunderEnchantment;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {

    @Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "net/minecraft/enchantment/Enchantment.isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z", ordinal = 0))
    private boolean specialEnchantmentTargets(Enchantment enchant, ItemStack is) {
        if (enchant instanceof AutoSmeltEnchantment || enchant instanceof DoubleSwingEnchantment || enchant instanceof GodOfThunderEnchantment) {
            if (is.isIn(FabricToolTags.AXES)) {
                return true;
            } else return is.isIn(FabricToolTags.PICKAXES) || is.isIn(FabricToolTags.SHOVELS) && enchant instanceof AutoSmeltEnchantment;
        } else {
            return enchant.isAcceptableItem(is);
        }
    }
}

