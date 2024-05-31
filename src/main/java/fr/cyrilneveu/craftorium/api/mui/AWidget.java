package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.Size;
import io.netty.buffer.ByteBuf;

import java.util.Collections;
import java.util.List;

import static fr.cyrilneveu.craftorium.api.utils.Position.ORIGIN;
import static fr.cyrilneveu.craftorium.api.utils.Size.ZERO;

public abstract class AWidget {
    protected Position position = ORIGIN;
    protected Size size = ZERO;
    protected Position parentPosition = ORIGIN;
    protected Size parentSize = ZERO;
    protected Position offset = ORIGIN;
    protected boolean isActive = true;

    public AWidget(Position position, Size size) {
        this.position = position;
        this.size = size;
    }

    public void init(Position parentPosition, Size parentSize) {
        this.parentPosition = parentPosition;
        this.parentSize = parentSize;
    }

    public void drawForeground(int mouseX, int mouseY) {

    }

    public void drawBackground(int mouseX, int mouseY, float partialTicks) {

    }

    public boolean onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        return false;
    }

    public List<String> getTooltips(int mouseX, int mouseY) {
        return Collections.emptyList();
    }

    public boolean isHovered(int mouseX, int mouseY) {
        return isActive() && mouseX >= getRealPosition().getPosX() && mouseX < getRealPosition().getPosX() + getSize().getSizeX() && mouseY >= getRealPosition().getPosY() && mouseY < getRealPosition().getPosY() + getSize().getSizeY();
    }

    public void fromBytes(ByteBuf buf) {

    }

    public void toBytes(ByteBuf buf) {

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getRealPosition() {
        return getPosition().add(offset).add(parentPosition);
    }

    public Size getSize() {
        return size;
    }

    public Position getOffset() {
        return offset;
    }

    public void setOffset(Position offset) {
        this.offset = offset;
    }

    public void addOffset(Position offset) {
        this.offset = this.offset.add(offset);
    }

    public void resetOffset() {
        this.offset = ORIGIN;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Position getParentPosition() {
        return parentPosition;
    }

    public Size getParentSize() {
        return parentSize;
    }


}
