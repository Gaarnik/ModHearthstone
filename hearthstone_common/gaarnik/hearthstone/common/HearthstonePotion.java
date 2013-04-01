package gaarnik.hearthstone.common;

import gaarnik.hearthstone.client.HearthstoneClientProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;

public class HearthstonePotion extends Potion {
	// *******************************************************************
	public static final int ID = 32;

	// *******************************************************************

	// *******************************************************************
	protected HearthstonePotion() {
		super(ID, true, 0);

		this.setIconIndex(2, 2);
		this.setPotionName("Motion sickness");
	}

	// *******************************************************************

	// *******************************************************************

	// *******************************************************************

	// *******************************************************************
	@SideOnly(Side.CLIENT)
	public boolean hasStatusIcon() { return true; }

	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex() {
		int texture = Minecraft.getMinecraft().renderEngine.getTexture(HearthstoneClientProxy.INV_TEXTURE);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
		return super.getStatusIconIndex();
	}

	@SideOnly(Side.CLIENT)
	public boolean isBadEffect() { return true; }

}
