package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.inventory.ASlotData;
import fr.cyrilneveu.craftorium.api.utils.Size;

public abstract class ASlot extends AWidget implements ITextured {
    public static final Size SLOT_SIZE = new Size(16, 16);
    public static final Size SLOT_TEXTURE_SIZE = new Size(18, 18);
    protected final ASlotData slotData;

    public ASlot(ASlotData slotData) {
        super(slotData.getPosition(), SLOT_SIZE);
        this.slotData = slotData;
    }

    @Override
    public Size getTextureSize() {
        return SLOT_TEXTURE_SIZE;
    }
}
