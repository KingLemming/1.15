package cofh.core.client.gui.element;

import cofh.core.client.gui.IGuiAccess;
import cofh.core.util.GuiHelper;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.util.helpers.MathHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;

import static cofh.core.util.GuiHelper.SLOT_SIZE;
import static cofh.lib.util.constants.Constants.MAX_AUGMENTS;

public class ElementAugmentSlots extends ElementBase {

    private final IntSupplier numSlots;
    private final List<SlotCoFH> augmentSlots;
    private final List<ElementSlot> slots = new ArrayList<>(MAX_AUGMENTS);

    public ElementAugmentSlots(IGuiAccess gui, int posX, int posY, @Nonnull IntSupplier numSlots, @Nonnull List<SlotCoFH> augmentSlots) {

        super(gui, posX, posY);

        this.numSlots = numSlots;
        this.augmentSlots = augmentSlots;

        for (int i = 0; i < augmentSlots.size(); ++i) {
            int slotIndex = i;
            this.augmentSlots.get(i).setEnabled(() -> slotIndex < this.numSlots.getAsInt());
        }
        for (int i = 0; i < MAX_AUGMENTS; ++i) {
            int slotIndex = i;
            ElementSlot slot = GuiHelper.createSlot(gui, 18 * i, 0);
            slot.setVisible(() -> slotIndex < this.numSlots.getAsInt());
            slots.add(slot);
        }
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {

        for (ElementBase slot : slots) {
            if (slot.visible()) {
                slot.drawBackground(mouseX, mouseY);
            }
        }
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {

        for (ElementBase slot : slots) {
            if (slot.visible()) {
                slot.drawForeground(mouseX, mouseY);
            }
        }
    }

    @Override
    public void update(int mouseX, int mouseY) {

        int activeSlots = MathHelper.clamp(numSlots.getAsInt(), 0, MAX_AUGMENTS);

        switch (activeSlots) {
            case 1:
                augmentSlots.get(0).xPos = posX + SLOT_SIZE;
                augmentSlots.get(0).yPos = posY + SLOT_SIZE;
                break;
            case 2:
                for (int i = 0; i < activeSlots; ++i) {
                    augmentSlots.get(i).xPos = posX + 9 + SLOT_SIZE * (i % 2);
                    augmentSlots.get(i).yPos = posY + SLOT_SIZE;
                }
                break;
            case 3:
                for (int i = 0; i < 2; ++i) {
                    augmentSlots.get(i).xPos = posX + 9 + SLOT_SIZE * (i % 2);
                    augmentSlots.get(i).yPos = posY + 9;
                }
                augmentSlots.get(2).xPos = posX + SLOT_SIZE;
                augmentSlots.get(2).yPos = posY + 9 + SLOT_SIZE;
                break;
            case 4:
                for (int i = 0; i < activeSlots; ++i) {
                    augmentSlots.get(i).xPos = posX + 9 + SLOT_SIZE * (i % 2);
                    augmentSlots.get(i).yPos = posY + 9 + SLOT_SIZE * (i / 2);
                }
                break;
            case 5:
                for (int i = 0; i < activeSlots; ++i) {
                    augmentSlots.get(i).xPos = posX + SLOT_SIZE * (i % 3) + 9 * (i / 3);
                    augmentSlots.get(i).yPos = posY + 9 + SLOT_SIZE * (i / 3);
                }
                break;
            case 6:
                for (int i = 0; i < activeSlots; ++i) {
                    augmentSlots.get(i).xPos = posX + SLOT_SIZE * (i % 3);
                    augmentSlots.get(i).yPos = posY + 9 + SLOT_SIZE * (i / 3);
                }
                break;
            case 7:
                for (int i = 0; i < 2; ++i) {
                    augmentSlots.get(i).xPos = posX + 9 + SLOT_SIZE * (i);
                    augmentSlots.get(i).yPos = posY;
                }
                for (int i = 2; i < 5; ++i) {
                    augmentSlots.get(i).xPos = posX + SLOT_SIZE * (i - 2);
                    augmentSlots.get(i).yPos = posY + SLOT_SIZE;
                }
                for (int i = 5; i < activeSlots; ++i) {
                    augmentSlots.get(i).xPos = posX + 9 + SLOT_SIZE * (i - 5);
                    augmentSlots.get(i).yPos = posY + SLOT_SIZE * 2;
                }
                break;
            case 8:
                for (int i = 0; i < 3; ++i) {
                    augmentSlots.get(i).xPos = posX + SLOT_SIZE * i;
                    augmentSlots.get(i).yPos = posY;
                }
                for (int i = 3; i < 5; ++i) {
                    augmentSlots.get(i).xPos = posX + 9 + SLOT_SIZE * (i - 3);
                    augmentSlots.get(i).yPos = posY + SLOT_SIZE;
                }
                for (int i = 5; i < activeSlots; ++i) {
                    augmentSlots.get(i).xPos = posX + SLOT_SIZE * (i - 5);
                    augmentSlots.get(i).yPos = posY + SLOT_SIZE * 2;
                }
                break;
            case 9:
                for (int i = 0; i < activeSlots; ++i) {
                    augmentSlots.get(i).xPos = posX + SLOT_SIZE * (i % 3);
                    augmentSlots.get(i).yPos = posY + SLOT_SIZE * (i / 3);
                }
            default:
        }
        for (int i = 0; i < activeSlots; ++i) {
            slots.get(i).setPosition(augmentSlots.get(i).xPos - 1, augmentSlots.get(i).yPos - 1);
        }
    }

}
