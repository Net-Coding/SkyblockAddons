package codes.biscuit.skyblockaddons.mixins;

import codes.biscuit.skyblockaddons.SkyblockAddons;
import codes.biscuit.skyblockaddons.utils.Feature;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiFurnace.class)
public abstract class MixinGuiFurnace extends GuiContainer {

	public MixinGuiFurnace(Container inventorySlotsIn) {
		super(inventorySlotsIn);
	}
	@Override
	protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
		SkyblockAddons main = SkyblockAddons.getInstance();
		if (slotIn != null && main.getConfigValues().isEnabled(Feature.LOCK_SLOTS) &&
				main.getUtils().isOnSkyblock()) {
			int slotNum = slotIn.slotNumber;
			slotNum+=6;
			if (slotNum > 8 && main.getConfigValues().getLockedSlots().contains(slotNum)) {
				main.getUtils().playSound(SoundEvents.BLOCK_NOTE_BASS, 0.5);
				return;
			}
		}
		super.handleMouseClick(slotIn, slotId, mouseButton, type);
	}
}