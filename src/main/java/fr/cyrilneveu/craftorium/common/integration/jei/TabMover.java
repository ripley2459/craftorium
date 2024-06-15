package fr.cyrilneveu.craftorium.common.integration.jei;

import fr.cyrilneveu.craftorium.api.machine.MachineScreen;
import fr.cyrilneveu.craftorium.api.mui.ATabGroup;
import fr.cyrilneveu.craftorium.api.mui.AWidget;
import mezz.jei.api.gui.IAdvancedGuiHandler;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public final class TabMover implements IAdvancedGuiHandler<MachineScreen> {
    @Override
    public Class<MachineScreen> getGuiContainerClass() {
        return MachineScreen.class;
    }

    @Override
    public java.util.List<Rectangle> getGuiExtraAreas(MachineScreen gui) {
        List<Rectangle> rectangles = new ArrayList<>();

        for (AWidget widget : gui.getScreen().getWidgets()) {
            if (widget instanceof ATabGroup group) {
                for (AWidget tab : group.getChildren())
                    rectangles.add(new Rectangle(tab.getRealPosition().getPosX(), tab.getRealPosition().getPosY(), tab.getSize().getSizeX(), tab.getSize().getSizeY()));
            }
        }

        return rectangles;
    }

    @Nullable
    @Override
    public Object getIngredientUnderMouse(MachineScreen guiContainer, int mouseX, int mouseY) {
        return null;
    }
}
