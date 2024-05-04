package fr.cyrilneveu.craftorium.api.utils;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

/**
 * <a href="https://github.com/GregTechCEu/GregTech/blob/master/src/main/java/gregtech/api/capability/impl/CombinedCapabilityProvider.java">GregTech CEu's original.</a>
 */
public final class CombinedCapabilityProvider implements ICapabilityProvider {
    private final ICapabilityProvider[] providers;

    public CombinedCapabilityProvider(ICapabilityProvider[] providers) {
        this.providers = providers;
    }

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, @Nullable EnumFacing facing) {
        return Utils.atLeastOne(providers, p -> p.hasCapability(capability, facing));
    }

    @Nullable
    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, @Nullable EnumFacing facing) {
        for (ICapabilityProvider provider : providers) {
            T capability1 = provider.getCapability(capability, facing);
            if (capability1 != null)
                return capability1;
        }

        return null;
    }
}
