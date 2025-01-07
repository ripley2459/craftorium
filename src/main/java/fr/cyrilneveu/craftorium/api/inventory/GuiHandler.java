package fr.cyrilneveu.craftorium.api.inventory;

import fr.cyrilneveu.craftorium.api.machine.MachineTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public final class GuiHandler implements IGuiHandler {
    public static final int MACHINE_GUI_ID = 0;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case MACHINE_GUI_ID:
                BlockPos pos = new BlockPos(x, y, z);
                TileEntity tileEntity = world.getTileEntity(pos);
                return tileEntity instanceof MachineTile tile ? tile.createContainer(player) : null;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case MACHINE_GUI_ID:
                BlockPos pos = new BlockPos(x, y, z);
                TileEntity tileEntity = world.getTileEntity(pos);
                return tileEntity instanceof MachineTile tile ? tile.createGui(player) : null;
            default:
                return null;
        }
    }
}
