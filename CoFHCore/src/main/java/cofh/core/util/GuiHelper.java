package cofh.core.util;

import cofh.core.gui.IGuiAccess;
import cofh.core.gui.element.ElementEnergyStorage;
import cofh.core.gui.element.ElementFluidStorage;
import cofh.core.gui.element.ElementSimple;
import cofh.lib.energy.EnergyStorageCoFH;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.util.control.IReconfigurable;

import static cofh.lib.util.constants.Constants.PATH_ELEMENTS;

public class GuiHelper {

    private GuiHelper() {

    }

    public static ElementEnergyStorage createDefaultEnergyStorage(IGuiAccess gui, int posX, int posY, EnergyStorageCoFH storage) {

        return createDefaultEnergyStorage(gui, posX, posY, storage, 16, 42, PATH_ELEMENTS + "storage_energy.png", 32, 64);
    }

    public static ElementEnergyStorage createDefaultEnergyStorage(IGuiAccess gui, int posX, int posY, EnergyStorageCoFH storage, int width, int height, String texture, int texW, int texH) {

        return (ElementEnergyStorage) new ElementEnergyStorage(gui, posX, posY, storage).setSize(width, height).setTexture(texture, texW, texH);
    }

    public static ElementFluidStorage createLargeFluidStorage(IGuiAccess gui, int posX, int posY, FluidStorageCoFH storage) {

        return createDefaultFluidStorage(gui, posX, posY, storage, 18, 62, PATH_ELEMENTS + "storage_fluid_large.png", PATH_ELEMENTS + "overlay_fluid_large.png", 32, 64);
    }

    public static ElementFluidStorage createMediumFluidStorage(IGuiAccess gui, int posX, int posY, FluidStorageCoFH storage) {

        return createDefaultFluidStorage(gui, posX, posY, storage, 18, 42, PATH_ELEMENTS + "storage_fluid_medium.png", PATH_ELEMENTS + "overlay_fluid_medium.png", 32, 64);
    }

    public static ElementFluidStorage createSmallFluidStorage(IGuiAccess gui, int posX, int posY, FluidStorageCoFH storage) {

        return createDefaultFluidStorage(gui, posX, posY, storage, 18, 34, PATH_ELEMENTS + "storage_fluid_small.png", PATH_ELEMENTS + "overlay_fluid_small.png", 32, 64);
    }

    public static ElementFluidStorage createDefaultFluidStorage(IGuiAccess gui, int posX, int posY, FluidStorageCoFH storage, int width, int height, String texture, String overlayTexture, int texW, int texH) {

        return (ElementFluidStorage) new ElementFluidStorage(gui, posX, posY, storage).setOverlayTexture(overlayTexture).setSize(width, height).setTexture(texture, texW, texH);
    }

    // TODO: Fix/Finish
    public static ElementSimple createSlotOverlay(IGuiAccess gui, int posX, int posY, IReconfigurable reconfig) {

        return (ElementSimple) new ElementSimple(gui, posX, posY).setVisible(reconfig::hasInputSide);
    }

    public static final int DURATION = 16;
    public static final int PROGRESS = 24;
    public static final int SPEED = 16;

    // region ELEMENTS
    public static final String PROG_ARROW_LEFT = PATH_ELEMENTS + "progress_arrow_left.png";
    public static final String PROG_ARROW_RIGHT = PATH_ELEMENTS + "progress_arrow_right.png";
    public static final String PROG_ARROW_FLUID_LEFT = PATH_ELEMENTS + "progress_arrow_fluid_left.png";
    public static final String PROG_ARROW_FLUID_RIGHT = PATH_ELEMENTS + "progress_arrow_fluid_right.png";
    public static final String PROG_DROP_LEFT = PATH_ELEMENTS + "progress_fluid_left.png";
    public static final String PROG_DROP_RIGHT = PATH_ELEMENTS + "progress_fluid_right.png";

    public static final String SCALE_ALCHEMY = PATH_ELEMENTS + "scale_alchemy.png";
    public static final String SCALE_BOOK = PATH_ELEMENTS + "scale_book.png";
    public static final String SCALE_BUBBLE = PATH_ELEMENTS + "scale_bubble.png";
    public static final String SCALE_COMPACT = PATH_ELEMENTS + "scale_compact.png";
    public static final String SCALE_CRUSH = PATH_ELEMENTS + "scale_crush.png";
    public static final String SCALE_FLAME = PATH_ELEMENTS + "scale_flame.png";
    public static final String SCALE_FLAME_GREEN = PATH_ELEMENTS + "scale_flame_green.png";
    public static final String SCALE_FLUX = PATH_ELEMENTS + "scale_flux.png";
    public static final String SCALE_SAW = PATH_ELEMENTS + "scale_saw.png";
    public static final String SCALE_SPIN = PATH_ELEMENTS + "scale_spin.png";
    public static final String SCALE_SUN = PATH_ELEMENTS + "scale_sun.png";
    public static final String SCALE_SNOWFLAKE = PATH_ELEMENTS + "scale_snowflake.png";
    // endregion
}
