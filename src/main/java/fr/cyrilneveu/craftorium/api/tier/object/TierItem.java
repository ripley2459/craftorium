package fr.cyrilneveu.craftorium.api.tier.object;

import fr.cyrilneveu.craftorium.api.config.Settings;
import fr.cyrilneveu.craftorium.api.inventory.ItemEnergyStorageBehaviour;
import fr.cyrilneveu.craftorium.api.item.CustomItem;
import fr.cyrilneveu.craftorium.api.property.Aestheticism;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TierItem extends CustomItem {
    protected final ATierObject reference;
    protected final Tier tier;

    public TierItem(ATierObject reference, Tier tier) {
        super(new Aestheticism.ObjectAestheticism(reference.getFaces(tier), () -> reference.getTooltips(tier), tier.getAestheticism().isGlint()));
        this.reference = reference;
        this.tier = tier;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return Utils.localise(String.join(".", getTranslationKey(), "name"), tier.getDisplayName());
    }

    public static final class Battery extends TierItem {
        public Battery(ATierObject reference, Tier tier) {
            super(reference, tier);
            this.maxStackSize = 1;
        }

        @Override
        public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt) {
            return new ItemEnergyStorageBehaviour(stack, (int) (tier.getStorage().getEnergyBuffer() * Settings.balancingSettings.batteryBaseStorage), (int) (tier.getStorage().getEnergyIO() * Settings.balancingSettings.batteryBaseTransfer));
        }

        @Override
        public void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltips, ITooltipFlag flag) {
            if (getMaxEnergy(itemStack) > 0)
                tooltips.add(Utils.localise("tooltip.craftorium.battery.energy", getEnergy(itemStack), getMaxEnergy(itemStack)));
            super.addInformation(itemStack, world, tooltips, flag);
        }

        @Override
        public boolean showDurabilityBar(@Nonnull ItemStack itemStack) {
            return getEnergy(itemStack) > 0;
        }

        @Override
        public double getDurabilityForDisplay(@Nonnull ItemStack itemStack) {
            double max = getMaxEnergy(itemStack);
            return (max - getEnergy(itemStack)) / max;
        }
    }
}
