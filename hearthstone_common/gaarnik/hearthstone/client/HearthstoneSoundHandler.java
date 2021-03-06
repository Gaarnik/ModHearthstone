package gaarnik.hearthstone.client;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HearthstoneSoundHandler {
	// *******************************************************************
	public static final String TELEPORT_SOUND = "gaarnik.hearthstone.resources.sound.teleport";
	
	private static final String TELEPORT_FILE = "gaarnik/hearthstone/resources/sound/teleport.ogg";

	// *******************************************************************
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event) {
		try {
			event.manager.soundPoolSounds.addSound(TELEPORT_FILE, this.getClass().getResource("/" + TELEPORT_FILE));
		} 
		catch (Exception e) {
			System.err.println("Failed to register one or more sounds.");
		}
	}

}
