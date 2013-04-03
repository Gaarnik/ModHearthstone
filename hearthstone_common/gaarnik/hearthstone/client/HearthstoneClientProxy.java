package gaarnik.hearthstone.client;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import gaarnik.hearthstone.common.HearthstoneCommonProxy;


public class HearthstoneClientProxy extends HearthstoneCommonProxy {
	// *******************************************************************
	public static final String ITEMS_TEXTURE = "/gaarnik/hearthstone/resources/item/items.png";
	
	public static final String INV_TEXTURE = "/gaarnik/hearthstone/resources/inv/inventory.png";

	// *******************************************************************
	@Override
	public void initTextures() {
		MinecraftForgeClient.preloadTexture(ITEMS_TEXTURE);
		MinecraftForgeClient.preloadTexture(INV_TEXTURE);
	}

	// *******************************************************************
	@Override
	public void registerSoundHandler() {
		MinecraftForge.EVENT_BUS.register(new HearthstoneSoundHandler());
	}

}
