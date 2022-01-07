// ==================================================================
// This file is part of Render Player API Enhancer.
//
// Render Player API Enhancer is free software: you can redistribute
// it and/or modify it under the terms of the GNU Lesser General
// Public License as published by the Free Software Foundation,
// either version 3 of the License, or (at your option) any later
// version.
//
// Render Player API Enhancer is distributed in the hope that it will
// be useful, but WITHOUT ANY WARRANTY; without even the implied
// warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
// See the GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License and the GNU General Public License along with Render
// Player API Enhancer. If not, see <http://www.gnu.org/licenses/>.
// ==================================================================

package api.player.forge;

import java.io.*;
import java.util.*;

import org.apache.logging.log4j.*;

import cpw.mods.fml.relauncher.*;
import api.player.model.*;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions("api.player.forge")
public class RenderPlayerAPIEnhancerPlugin implements IFMLLoadingPlugin
{
	public static String Version = "1.4";

	public static boolean isObfuscated;
	public static boolean hasRenderPlayerAPI = false;
	public static boolean hasValidRenderPlayerAPI = false;
	public static Set<String> canonicalClassNames;
	public static Set<String> canonicalClassNamePatterns;

	private static final String[] defaultClassNamePatterns = new String[]
	{
		"(?i).*armou?r[^\\.]*",
	};

	private static final String[] defaultClassNames = new String[]
	{
		"thaumic.tinkerer.client.model.kami.ModelWings",
		"thaumcraft.client.renderers.models.gear.ModelRobe",
		"thaumcraft.client.renderers.models.gear.ModelHoverHarness",
		"riskyken.armourersWorkshop.client.model.equipmet.AbstractModelCustomEquipment"
	};

	public RenderPlayerAPIEnhancerPlugin()
	{
		for(String fileName : CoreModManager.getLoadedCoremods())
			if(fileName.startsWith("RenderPlayerAPI-") && fileName.endsWith(".jar"))
			{
				hasRenderPlayerAPI = true;
				String[] versions = fileName.substring(16, fileName.length() - 4).split("-");
				String renderPlayerAPIVersion = versions[versions.length - 1];
				hasValidRenderPlayerAPI = !renderPlayerAPIVersion.equals("1.0");
				break;
			}

		if(hasRenderPlayerAPI)
			if (hasValidRenderPlayerAPI)
				log(Level.WARN,
					"========================================",
					"The mod \"Render Player API Enhancer\" version " + Version + " is active!",
					"----------------------------------------",
					"Render Player API Enhancer will violently force ALL classes that:",
					"* extend 'net.minecraft.client.model.ModelBiped' and",
					"* have canonical names that match at least one 'armor' pattern",
					"to use Render Player API.",
					"----------------------------------------",
					"This can fix various incompatibilities between:",
					"* player animation changing mods and ",
					"* player armor model adding mods.",
					"This can also screw everything up, so use with caution.",
					"========================================");
			else
				log(Level.ERROR,
					"========================================",
					"The mod \"Render Player API Enhancer\" version " + Version + " can not be created!",
					"----------------------------------------",
					"The version of the found \"Render Player API\" was not high enough so the mod \"Render Player API Enhancer\" will remain inert.",
					"To activate \"Render Player API Enhancer\" download and install the latest Render Player API Core for the Minecraft version you were trying to run.",
					"========================================");
		else
			log(Level.ERROR,
				"========================================",
				"The mod \"Render Player API Enhancer\" version " + Version + " can not be created!",
				"----------------------------------------",
				"The API \"Render Player API\" was not found so the mod \"Render Player API Enhancer\" will remain inert.",
				"To activate \"Render Player API Enhancer\" download and install the latest Render Player API Core for the Minecraft version you were trying to run.",
				"========================================");
	}

	public String[] getASMTransformerClass()
	{
		return hasRenderPlayerAPI ? new String[] { "api.player.forge.RenderPlayerAPIEnhancerTransformer" } : null;
	}

	public String getModContainerClass()
	{
		return hasRenderPlayerAPI ? "api.player.forge.RenderPlayerAPIEnhancerContainer" : null;
	}

	public String getSetupClass()
	{
		return null;
	}

	public void injectData(Map<String, Object> data)
	{
		isObfuscated = (Boolean)data.get("runtimeDeobfuscationEnabled");
		if(hasRenderPlayerAPI)
		{
			File mcLocation = (File)data.get("mcLocation");
			canonicalClassNames = HandleOptions(mcLocation, "render_player_api_Enhancer_classnames.txt", defaultClassNames, "canonical class name");
			canonicalClassNamePatterns = HandleOptions(mcLocation, "render_player_api_Enhancer_classnamepatterns.txt", defaultClassNamePatterns, "canonical class name pattern");
		}
	}

	public String getAccessTransformerClass()
	{
		return null;
	}

	private static void log(Level level, String... messageParts)
	{
		for(String messagePart :messageParts)
			ModelPlayerAPIEnhancerClassVisitor.log(level, messagePart);
	}

	private static Set<String> HandleOptions(File mcLocation, String fileName, String[] defaultStrings, String type)
	{
		try
		{
			File optionsFile = new File(mcLocation, fileName);
			if(!optionsFile.exists())
			{
				PrintWriter printer = new PrintWriter(new FileWriter(optionsFile));

				printer.println("######################################################");
				printer.println("# Render Player API Enhancer " + type + " configuration file");
				printer.println("######################################################");
				printer.println("# * one line per " + type);
				printer.println("# * for comments prepend '#'");
				printer.println("# * to disable default " + type + " prepend '-'");
				printer.println("# * delete this file to get newest defaults on startup");
				printer.println("######################################################");
				printer.println();
				for(String defaultPattern : defaultStrings)
				{
					printer.println("# uncomment the line below to disable this default " + type);
					printer.println("#-" + defaultPattern);
					printer.println();
				}
				printer.println();
				printer.println("# add your own " + type + "s below");
				printer.close();
			}

			Set<String> result = new HashSet<String>();
			for(String defaultString : defaultStrings)
				result.add(defaultString);

			BufferedReader reader = new BufferedReader(new FileReader(optionsFile));

			String line;
			while((line = reader.readLine()) != null)
			{
				line = line.trim();
				int commentIndex = line.indexOf('#');
				if(commentIndex >= 0)
					line = line.substring(0, commentIndex).trim();
				if(line.isEmpty())
					continue;

				boolean remove = line.startsWith("-"); 
				if(remove)
					result.remove(line.substring(1).trim());
				else
					result.add(line);
			}
			reader.close();

			return result;
		}
		catch(IOException ioe)
		{
			throw new RuntimeException(ioe);
		}
	}
}