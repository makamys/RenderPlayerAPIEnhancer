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

import java.util.Set;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ModelPlayerAPIEnhancerMethodScanner extends MethodVisitor
{
	private final String name;
	private final String desc;
	private final Set<String> scanned;

	public ModelPlayerAPIEnhancerMethodScanner(MethodVisitor paramMethodVisitor, String name, String desc,  Set<String> scanned)
	{
		super(262144, paramMethodVisitor);
		this.name = name;
		this.desc = desc;
		this.scanned = scanned;
	}

	public void visitMethodInsn(int opcode, String owner, String name, String desc)
	{
		if(opcode == Opcodes.INVOKESPECIAL && name.equals(this.name) && desc.equals(this.desc))
			scanned.add(name + "___" + desc);
		super.visitMethodInsn(opcode, owner, name, desc);
	}
}
