package safro.fabric.enchantments.asm;


import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

public class AxeTarget extends AxeTargetMixin {

    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof AxeItem;
    }

}

@Mixin(EnchantmentTarget.class)
abstract class AxeTargetMixin {

    @Shadow
    abstract boolean isAcceptableItem(Item item);

}
