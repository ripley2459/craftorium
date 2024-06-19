package fr.cyrilneveu.craftorium.api.machine;

import fr.cyrilneveu.craftorium.api.mui.AWidget;
import fr.cyrilneveu.craftorium.api.mui.Screen;
import fr.cyrilneveu.craftorium.api.net.CMachinePacket;
import fr.cyrilneveu.craftorium.api.net.NetManager;
import fr.cyrilneveu.craftorium.api.utils.Position;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public final class MachineScreen extends GuiContainer {
    private final MachineTile owner;
    private final Screen screen;

    public MachineScreen(MachineTile owner, Container inventorySlotsIn) {
        super(inventorySlotsIn);
        this.owner = owner;
        this.screen = owner.getScreen();
        this.xSize = screen.getSize().getSizeX();
        this.ySize = screen.getSize().getSizeY();
    }

    @Override
    public void initGui() {
        super.initGui();

        Position offset = new Position(getGuiLeft(), getGuiTop());
        for (AWidget widget : screen.getWidgets())
            widget.init(offset, screen.getSize());
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        for (AWidget widget : screen.getWidgets())
            widget.drawForeground(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        for (AWidget widget : screen.getWidgets())
            widget.drawBackground(mouseX, mouseY, partialTicks);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);

        List<String> tooltips = new LinkedList<>();
        for (AWidget widget : screen.getWidgets())
            tooltips.addAll(widget.getTooltips(mouseX, mouseY));

        drawHoveringText(tooltips, mouseX, mouseY);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        boolean flag = false;
        for (AWidget widget : screen.getWidgets()) {
            if (widget.onMouseClicked(mouseX, mouseY, mouseButton))
                flag = true;
        }

        if (flag)
            NetManager.sendToServer(new CMachinePacket(owner));
    }

    public MachineTile getOwner() {
        return owner;
    }

    public Screen getScreen() {
        return screen;
    }
}
