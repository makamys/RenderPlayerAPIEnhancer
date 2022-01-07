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

import net.minecraft.launchwrapper.*;

import api.player.model.*;

public class RenderPlayerAPIEnhancerTransformer implements IClassTransformer
{
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		for(String canonicalClassNamePattern : RenderPlayerAPIEnhancerPlugin.canonicalClassNamePatterns)
			if(transformedName.matches(canonicalClassNamePattern))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("detects class '%s' that might be a player armor class because it matches the configured canonical class name pattern '%s'", transformedName, canonicalClassNamePattern);
				return ModelPlayerAPIEnhancerClassVisitor.transform(bytes, RenderPlayerAPIEnhancerPlugin.isObfuscated);
			}
		for(String canonicalClassName : RenderPlayerAPIEnhancerPlugin.canonicalClassNames)
			if(transformedName.equals(canonicalClassName))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("detects class '%s' that might be a player armor class because it equals the configured canonical class name'%s'", transformedName, canonicalClassName);
				return ModelPlayerAPIEnhancerClassVisitor.transform(bytes, RenderPlayerAPIEnhancerPlugin.isObfuscated);
			}
		return bytes;
	}
}