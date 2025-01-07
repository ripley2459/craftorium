package fr.cyrilneveu.craftorium.api.utils;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class CombinedCapabilityProvider implements ICapabilityProvider {
    private final ICapabilityProvider[] providers;

    public CombinedCapabilityProvider(ICapabilityProvider[] providers) {
        this.providers = providers;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return Utils.atLeastOne(providers, p -> p.hasCapability(capability, facing));
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        for (ICapabilityProvider provider : providers) {
            T capability1 = provider.getCapability(capability, facing);
            if (capability1 != null)
                return capability1;
        }

        return null;
    }
}
