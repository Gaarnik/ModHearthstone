package gaarnik.hearthstone.client;

import net.minecraftforge.client.MinecraftForgeClient;
import gaarnik.hearthstone.common.HearthstoneCommonProxy;


public class HearthstoneClientProxy extends HearthstoneCommonProxy {
	// *******************************************************************
	public static final String ITEMS_TEXTURE = "/gaarnik/hearthstone/resources/item/items.png";

	// *******************************************************************
	@Override
	public void initTextures() {
		MinecraftForgeClient.preloadTexture(ITEMS_TEXTURE);
	}

}
