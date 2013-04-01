package gaarnik.hearthstone.common;

import gaarnik.hearthstone.common.item.HearthstoneItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;

public class HearthstoneCraftingHandler implements ICraftingHandler {

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
		
		if(item.itemID == HearthstoneItems.hearthstoneItem.itemID)
			player.addStat(ModHearthstone.hearthstonePage.craft, 1);
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		
	}

}
