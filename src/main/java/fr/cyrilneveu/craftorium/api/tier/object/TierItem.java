package fr.cyrilneveu.craftorium.api.tier.object;

import fr.cyrilneveu.craftorium.api.render.FaceProvider;
import fr.cyrilneveu.craftorium.api.tier.Tier;
import fr.cyrilneveu.craftorium.common.ACommonProxy;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;
import static fr.cyrilneveu.craftorium.api.utils.Utils.WHITE_COLOR;

public final class TierItem extends ATierObject {
    public TierItem(String name, ICreateObject provider, IGetFaces faces, IGetModelTemplate model) {
        super(name, provider, faces, model);
    }

    public static FaceProvider[] defaultFaces(ATierObject reference, Tier tier) {
        FaceProvider[] faces = new FaceProvider[2];
        faces[0] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "tiers", "items", reference.name)), tier.getAestheticism().getBaseColor());
        faces[1] = new FaceProvider(new ResourceLocation(MODID, String.join("/", "items", "tiers", "items", reference.name).concat("_overlay")), WHITE_COLOR);
        return faces;
    }

    public ItemStack asItemStack(Tier tier) {
        return asItemStack(tier, 1);
    }

    public ItemStack asItemStack(Tier tier, int amount) {
        return ACommonProxy.getItemStack(getName(tier), amount);
    }
}
