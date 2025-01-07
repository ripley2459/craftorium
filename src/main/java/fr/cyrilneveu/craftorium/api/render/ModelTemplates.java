package fr.cyrilneveu.craftorium.api.render;

import static fr.cyrilneveu.craftorium.CraftoriumTags.MODID;

public final class ModelTemplates {
    public static final ModelTemplate BLOCK = new ModelTemplate(MODID, "block/block");
    public static final ModelTemplate BLOCK_TINTED = new ModelTemplate(MODID, "block/block_tinted");
    public static final ModelTemplate BLOCK_OVERLAY = new ModelTemplate(MODID, "block/block_overlay");
    public static final ModelTemplate BLOCK_TINTED_OVERLAY = new ModelTemplate(MODID, "block/block_tinted_overlay");
    public static final ModelTemplate BLOCK_OVERLAY_TINTED = new ModelTemplate(MODID, "block/block_overlay_tinted");
    public static final ModelTemplate BLOCK_TINTED_OVERLAY_TINTED = new ModelTemplate(MODID, "block/block_tinted_overlay_tinted");
    public static final ModelTemplate BLOCK_TINTED_OVERLAY_ORIENTED = new ModelTemplate(MODID, "block/block_tinted_overlay_oriented");
    public static final ModelTemplate ITEM = new ModelTemplate("minecraft", "item/generated");
    public static final ModelTemplate ITEM_HANDHELD = new ModelTemplate("minecraft", "item/handheld");
}
