package gaarnik.hearthstone.common.achievement;

import gaarnik.hearthstone.common.item.HearthstoneItems;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class HearthstoneAchievementPage extends AchievementPage {
	// *******************************************************************
	private static final String NAME = "Hearthstone";

	// *******************************************************************
	ArrayList<Achievement> achievements;

	// *******************************************************************
	public HearthstoneAchievementPage() {
		super(NAME);
		
		this.initAchievements();
		
		AchievementPage.registerAchievementPage(this);
	}

	// *******************************************************************

	// *******************************************************************

	// *******************************************************************
	private void initAchievements() {
		this.achievements = new ArrayList<Achievement>();
		
		Achievement craft = new Achievement(2001, "Hearthstone.craft", 0, 0, new ItemStack(HearthstoneItems.hearthstoneItem, 1), null);
		LanguageRegistry.instance().addStringLocalization("achievement." + craft, "en_US", "A new Travel !");
		LanguageRegistry.instance().addStringLocalization("achievement." + craft + ".desc", "en_US", "Craft the Heathstone");
		this.achievements.add(craft);

		Achievement use = new Achievement(2002, "Hearthstone.use", -2, -2, new ItemStack(HearthstoneItems.hearthstoneItem, 1), craft);
		LanguageRegistry.instance().addStringLocalization("achievement." + use, "en_US", "Motion sickness !");
		LanguageRegistry.instance().addStringLocalization("achievement." + use + ".desc", "en_US", "Use the Heathstone");
		this.achievements.add(use);
	}

	// *******************************************************************
	@Override
	public List<Achievement> getAchievements() { return this.achievements; }

}
