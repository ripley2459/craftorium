package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Size;
import io.netty.buffer.ByteBuf;

import java.util.Collections;
import java.util.List;

import static fr.cyrilneveu.craftorium.api.utils.Position.ORIGIN;

public abstract class AWidget {
    protected Position position = ORIGIN;
    protected Size size = Size.ZERO;
    protected Position offset = ORIGIN;
    protected boolean isVisible = true;
    protected boolean isActive = true;

    public AWidget(Position position, Size size) {
        this.position = position;
        this.size = size;
    }

    public List<String> getTooltips(int mouseX, int mouseY) {
        return Collections.emptyList();
    }

    public void drawForeground(int mouseX, int mouseY) {

    }

    public void drawBackground(int mouseX, int mouseY, float partialTicks) {

    }

    public void fromBytes(ByteBuf buf) {

    }

    public void toBytes(ByteBuf buf) {

    }

    public Position getPosition() {
        return position;
    }

    public Position getRealPosition() {
        return getPosition().add(offset);
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= getRealPosition().getPosX() && mouseX < getRealPosition().getPosX() + getSize().getSizeX() && mouseY >= getRealPosition().getPosY() && mouseY < getRealPosition().getPosY() + getSize().getSizeY();
    }

    public void onMouseClicked(int mouseX, int mouseY, int mouseButton) {

    }

    public Size getSize() {
        return size;
    }

    public Position getOffset() {
        return offset;
    }

    public void addOffset(Position position) {
        offset = offset.add(position);
    }

    public void resetOffset() {
        offset = ORIGIN;
    }

    public void removeOffset(Position position) {
        offset = offset.subtract(position);
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
