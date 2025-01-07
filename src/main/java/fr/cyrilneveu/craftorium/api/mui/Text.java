package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Size;
import fr.cyrilneveu.craftorium.api.utils.Utils;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

import static fr.cyrilneveu.craftorium.api.utils.Size.ZERO;

public class Text extends AWidget {
    protected final Supplier<String> textProvider;
    protected final int color;
    protected final boolean centered;

    public Text(Position position, Supplier<String> textProvider, boolean centered, int color) {
        super(position, ZERO);
        this.textProvider = textProvider;
        this.color = color;
        this.centered = centered;
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {
        if (!isActive())
            return;

        RenderUtils.renderText(Utils.localise(textProvider.get()), getRealPosition(), color);
    }

    @Override
    public Size getSize() {
        int width = Minecraft.getMinecraft().fontRenderer.getStringWidth(Utils.localise(textProvider.get()));
        return new Size(width, 9);
    }

    @Override
    public Position getRealPosition() {
        if (!centered)
            return getPosition().add(offset);//.add(parentPosition);

        int width = Minecraft.getMinecraft().fontRenderer.getStringWidth(Utils.localise(textProvider.get()));
        Position p1 = getPosition().add(offset);//.add(parentPosition);
        return new Position(p1.getPosX() - width / 2, p1.getPosY());
    }
}
