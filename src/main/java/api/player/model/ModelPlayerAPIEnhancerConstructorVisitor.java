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

package api.player.model;

import org.objectweb.asm.MethodVisitor;

public class ModelPlayerAPIEnhancerConstructorVisitor extends MethodVisitor
{
	private final boolean isObfuscated;

	public ModelPlayerAPIEnhancerConstructorVisitor(MethodVisitor paramMethodVisitor, boolean isObfuscated)
	{
		super(262144, paramMethodVisitor);
		this.isObfuscated = isObfuscated;
	}

	public void visitMethodInsn(int opcode, String owner, String name, String desc)
	{
		if(name.equals("<init>") && ((isObfuscated && owner.equals("bhm")) || owner.equals("net/minecraft/client/model/ModelBiped")))
			owner = owner.equals("bhm") ? "api/player/model/ModelPlayer" : "api/player/model/ModelPlayer";
		super.visitMethodInsn(opcode, owner, name, desc);
	}
}
