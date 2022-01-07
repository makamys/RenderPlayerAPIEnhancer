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

import java.io.*;
import java.util.*;

import org.objectweb.asm.*;

public class ModelPlayerAPIEnhancerClassScanner extends ClassVisitor
{
	public static Set<String> scan(byte[] bytes)
	{
		try
		{
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			ClassReader cr = new ClassReader(in);
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ModelPlayerAPIEnhancerClassScanner p = new ModelPlayerAPIEnhancerClassScanner(cw);

			cr.accept(p, 0);
			return p.scanned;
		}
		catch(IOException ioe)
		{
			throw new RuntimeException(ioe);
		}
	}

	public ModelPlayerAPIEnhancerClassScanner(ClassVisitor classVisitor)
	{
		super(262144, classVisitor);
		this.scanned = new HashSet<String>();
	}

	private final Set<String> scanned;

	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	{
		MethodVisitor visitor = super.visitMethod(access, name, desc, signature, exceptions);
		if((access & Opcodes.ACC_STATIC) == 0 && !name.equals("<init>"))
			visitor = new ModelPlayerAPIEnhancerMethodScanner(visitor, name, desc, scanned);
		return visitor;
	}
}
