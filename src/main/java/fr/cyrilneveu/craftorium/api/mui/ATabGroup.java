package fr.cyrilneveu.craftorium.api.mui;

import fr.cyrilneveu.craftorium.api.utils.Position;
import fr.cyrilneveu.craftorium.api.utils.RenderUtils;
import fr.cyrilneveu.craftorium.api.utils.Size;
import net.minecraft.client.gui.Gui;

import static fr.cyrilneveu.craftorium.api.mui.Background.BACKGROUND;
import static fr.cyrilneveu.craftorium.api.mui.Background.RECT_OFFSET;
import static fr.cyrilneveu.craftorium.api.utils.Position.ORIGIN;
import static fr.cyrilneveu.craftorium.api.utils.Size.ZERO;

public abstract class ATabGroup extends AWidget {
    protected final Tab[] tabs;

    public ATabGroup(Tab[] tabs) {
        super(ORIGIN, ZERO);
        this.tabs = tabs;
    }

    protected abstract boolean isTabControlHovered(int mouseX, int mouseY, Tab tab);

    protected abstract void setTabsOffset();

    public Tab[] getChildren() {
        return tabs;
    }

    @Override
    public void init(Position parentPosition, Size parentSize) {
        for (Tab tab : tabs) {
            tab.init(parentPosition, parentSize);

            for (AWidget child : tab.getChildren())
                child.init(parentPosition, parentSize);
        }

        setTabsOffset();
    }

    @Override
    public boolean onMouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (Tab tab : tabs) {
            if (isTabControlHovered(mouseX, mouseY, tab))
                tab.setOpen(!tab.isOpen());
        }

        setTabsOffset();
        return false;
    }

    public static class LeftTabGroup extends ATabGroup {

        public LeftTabGroup(Tab[] tabs) {
            super(tabs);
        }

        @Override
        protected boolean isTabControlHovered(int mouseX, int mouseY, Tab tab) {
            Position p1 = new Position(tab.getParentPosition().getPosX() - tab.getNormalSize().getSizeX(), tab.getParentPosition().getPosY() + tab.getOffset().getPosY());
            return mouseX >= p1.getPosX() && mouseX < p1.getPosX() + tab.getNormalSize().getSizeX() && mouseY >= p1.getPosY() && mouseY < p1.getPosY() + tab.getNormalSize().getSizeY();
        }

        @Override
        protected void setTabsOffset() {
            int yOffset = 4;
            for (Tab tab : tabs) {
                tab.setOffset(new Position(-tab.getSize().getSizeX(), yOffset));
                yOffset += tab.getSize().getSizeY();

                for (AWidget child : tab.getChildren())
                    child.setOffset(tab.getOffset());
            }
        }

        @Override
        public void drawBackground(int mouseX, int mouseY, float partialTicks) {
            for (Tab tab : tabs) {
                RenderUtils.setRenderColor(tab.getColor());
                RenderUtils.bindTexture(BACKGROUND);

                // Corners - Top left > Bottom left
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX(), tab.getRealPosition().getPosY(), 0, 0, RECT_OFFSET.getSizeX(), RECT_OFFSET.getSizeY(), tab.getTextureSize().getSizeX(), tab.getTextureSize().getSizeY());
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX(), tab.getRealPosition().getPosY() + tab.getSize().getSizeY() - RECT_OFFSET.getSizeY(), 0, tab.getTextureSize().getSizeY() - RECT_OFFSET.getSizeY(), RECT_OFFSET.getSizeX(), RECT_OFFSET.getSizeY(), tab.getTextureSize().getSizeX(), tab.getTextureSize().getSizeY());

                // Borders - Top > Bottom >Left
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX() + RECT_OFFSET.getSizeX(), tab.getRealPosition().getPosY(), RECT_OFFSET.getSizeX(), 0, tab.getSize().getSizeX() - RECT_OFFSET.getSizeX(), RECT_OFFSET.getSizeY(), tab.getTextureSize().getSizeX(), tab.getTextureSize().getSizeY());
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX() + RECT_OFFSET.getSizeX(), tab.getRealPosition().getPosY() + tab.getSize().getSizeY() - RECT_OFFSET.getSizeY(), RECT_OFFSET.getSizeX(), tab.getTextureSize().getSizeY() - RECT_OFFSET.getSizeY(), tab.getSize().getSizeX() - RECT_OFFSET.getSizeX(), RECT_OFFSET.getSizeY(), tab.getTextureSize().getSizeX(), tab.getTextureSize().getSizeY());
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX(), tab.getRealPosition().getPosY() + RECT_OFFSET.getSizeY(), 0, RECT_OFFSET.getSizeY(), RECT_OFFSET.getSizeX(), tab.getSize().getSizeY() - 2 * RECT_OFFSET.getSizeY(), tab.getTextureSize().getSizeX(), tab.getTextureSize().getSizeY());

                // Center
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX() + RECT_OFFSET.getSizeX(), tab.getRealPosition().getPosY() + RECT_OFFSET.getSizeY(), RECT_OFFSET.getSizeX(), RECT_OFFSET.getSizeY(), tab.getSize().getSizeX() - RECT_OFFSET.getSizeX(), tab.getSize().getSizeY() - 2 * RECT_OFFSET.getSizeY(), tab.getTextureSize().getSizeX(), tab.getTextureSize().getSizeY());

                RenderUtils.resetRenderColor();

                Position iconPos = tab.getRealPosition().add(new Position(tab.getSize().getSizeX() - 17, 4));

                RenderUtils.bindTexture(tab.getTexture());
                Gui.drawModalRectWithCustomSizedTexture(iconPos.getPosX(), iconPos.getPosY(), 0, 0, 16, 16, 16, 16);
            }
        }
    }

    public static class RightTabGroup extends ATabGroup {
        public RightTabGroup(Tab[] tabs) {
            super(tabs);
        }

        @Override
        protected boolean isTabControlHovered(int mouseX, int mouseY, Tab tab) {
            return mouseX >= tab.getRealPosition().getPosX() &&
                    mouseX < tab.getRealPosition().getPosX() + tab.getNormalSize().getSizeX() &&
                    mouseY >= tab.getRealPosition().getPosY() &&
                    mouseY < tab.getRealPosition().getPosY() + tab.getNormalSize().getSizeY();
        }

        @Override
        protected void setTabsOffset() {
            int yOffset = 4;
            for (Tab tab : tabs) {
                tab.setOffset(new Position(tab.getParentSize().getSizeX(), yOffset));
                yOffset += tab.getSize().getSizeY();

                for (AWidget child : tab.getChildren())
                    child.setOffset(tab.getOffset());
            }
        }

        @Override
        public void drawBackground(int mouseX, int mouseY, float partialTicks) {
            for (Tab tab : tabs) {
                RenderUtils.setRenderColor(tab.getColor());
                RenderUtils.bindTexture(BACKGROUND);

                // Corners - Top left > Bottom left
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX() + tab.getSize().getSizeX() - RECT_OFFSET.getSizeX(), tab.getRealPosition().getPosY(),
                        tab.getTextureSize().getSizeX() - RECT_OFFSET.getSizeX(), 0,
                        RECT_OFFSET.getSizeX(), RECT_OFFSET.getSizeY(),
                        tab.getTextureSize().getSizeX(), tab.getTextureSize().getSizeY());
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX() + tab.getSize().getSizeX() - RECT_OFFSET.getSizeX(), tab.getRealPosition().getPosY() + tab.getSize().getSizeY() - RECT_OFFSET.getSizeY(),
                        tab.getTextureSize().getSizeX() - RECT_OFFSET.getSizeX(), tab.getTextureSize().getSizeY() - RECT_OFFSET.getSizeY(),
                        RECT_OFFSET.getSizeX(), RECT_OFFSET.getSizeY(),
                        tab.getTextureSize().getSizeX(), tab.getTextureSize().getSizeY());

                // Borders - Top > Bottom >Left
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX(), tab.getRealPosition().getPosY(),
                        RECT_OFFSET.getSizeX(), 0,
                        tab.getSize().getSizeX() - RECT_OFFSET.getSizeX(), RECT_OFFSET.getSizeY(),
                        tab.getTextureSize().getSizeX(), tab.getTextureSize().getSizeY());
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX() + tab.getSize().getSizeX() - RECT_OFFSET.getSizeX(), tab.getRealPosition().getPosY() + RECT_OFFSET.getSizeY(),
                        tab.getTextureSize().getSizeX() - RECT_OFFSET.getSizeX(), RECT_OFFSET.getSizeY(),
                        RECT_OFFSET.getSizeX(), tab.getSize().getSizeY() - 2 * RECT_OFFSET.getSizeY(),
                        tab.getTextureSize().getSizeX(), tab.getTextureSize().getSizeY());
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX(), tab.getRealPosition().getPosY() + tab.getSize().getSizeY() - RECT_OFFSET.getSizeY(),
                        RECT_OFFSET.getSizeX(), tab.getTextureSize().getSizeY() - RECT_OFFSET.getSizeY(),
                        tab.getSize().getSizeX() - RECT_OFFSET.getSizeX(), RECT_OFFSET.getSizeY(),
                        tab.getTextureSize().getSizeX(), tab.getTextureSize().getSizeY());

                // Center
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX(), tab.getRealPosition().getPosY() + RECT_OFFSET.getSizeY(),
                        RECT_OFFSET.getSizeX(), RECT_OFFSET.getSizeY(),
                        tab.getSize().getSizeX() - RECT_OFFSET.getSizeX(), tab.getSize().getSizeY() - 2 * RECT_OFFSET.getSizeY(),
                        tab.getTextureSize().getSizeX(), tab.getTextureSize().getSizeY());

                RenderUtils.resetRenderColor();

                RenderUtils.bindTexture(tab.getTexture());
                Gui.drawModalRectWithCustomSizedTexture(tab.getRealPosition().getPosX() + 1, tab.getRealPosition().getPosY() + 4, 0, 0, 16, 16, 16, 16);
            }
        }
    }
}
