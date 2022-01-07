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

public class ModelPlayerAPIEnhancerMethodVisitor extends MethodVisitor
{
	private final boolean isObfuscated;
	private final String enhancableClassName;
	private final String originalSuperClassName;
	private final String enhancerSuperClassName;
	private final String enhancedName;
	private final String enhancedDesc;
	private final Set<String> superCallingMethods;

	public ModelPlayerAPIEnhancerMethodVisitor(MethodVisitor paramMethodVisitor, boolean isObfuscated, String enhancableClassName, String originalSuperClassName, String enhancerSuperClassName, Set<String> superCallingMethods, String enhancedName, String enhancedDesc)
	{
		super(262144, paramMethodVisitor);
		this.isObfuscated = isObfuscated;
		this.enhancableClassName = enhancableClassName;
		this.originalSuperClassName = originalSuperClassName;
		this.enhancerSuperClassName = enhancerSuperClassName;
		this.enhancedName = enhancedName;
		this.enhancedDesc = enhancedDesc;
		this.superCallingMethods = superCallingMethods;
	}

	public void visitMethodInsn(int opcode, String owner, String name, String desc)
	{
		if(opcode == Opcodes.INVOKESPECIAL && owner.equals(originalSuperClassName))
		{
			String newName = getSuperMethodName(name, desc);
			if(!newName.equals(name))
			{
				name = newName;
				owner = enhancerSuperClassName;
			}
		}
		super.visitMethodInsn(opcode, owner, name, desc);
	}

	private String getSuperMethodName(String name, String desc)
	{
		if(isObfuscated)
		{
			if(name.equals("a") && desc.equals("(Ljava/util/Random;)Lbix;"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'getRandomModelBox' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localGetRandomModelBox";
				}
			}
			else if(name.equals("func_85181_a") && desc.equals("(Ljava/util/Random;)Lnet/minecraft/client/model/ModelRenderer;"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'getRandomModelBox' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localGetRandomModelBox";
				}
			}
		}
		else if(name.equals("getRandomModelBox") && desc.equals("(Ljava/util/Random;)Lnet/minecraft/client/model/ModelRenderer;"))
		{
			if(superCallingMethods.contains(name + "___" + desc))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return name;
			}
			else
			{
				ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return "localGetRandomModelBox";
			}
		}

		if(isObfuscated)
		{
			if(name.equals("a") && desc.equals("(Ljava/lang/String;)Lbiy;"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'getTextureOffset' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localGetTextureOffset";
				}
			}
			else if(name.equals("func_78084_a") && desc.equals("(Ljava/lang/String;)Lnet/minecraft/client/model/TextureOffset;"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'getTextureOffset' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localGetTextureOffset";
				}
			}
		}
		else if(name.equals("getTextureOffset") && desc.equals("(Ljava/lang/String;)Lnet/minecraft/client/model/TextureOffset;"))
		{
			if(superCallingMethods.contains(name + "___" + desc))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return name;
			}
			else
			{
				ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return "localGetTextureOffset";
			}
		}

		if(isObfuscated)
		{
			if(name.equals("a") && desc.equals("(Lsa;FFFFFF)V"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'render' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localRender";
				}
			}
			else if(name.equals("func_78088_a") && desc.equals("(Lnet/minecraft/entity/Entity;FFFFFF)V"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'render' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localRender";
				}
			}
		}
		else if(name.equals("render") && desc.equals("(Lnet/minecraft/entity/Entity;FFFFFF)V"))
		{
			if(superCallingMethods.contains(name + "___" + desc))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return name;
			}
			else
			{
				ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return "localRender";
			}
		}

		if(isObfuscated)
		{
			if(name.equals("c") && desc.equals("(F)V"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'renderCloak' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localRenderCloak";
				}
			}
			else if(name.equals("func_78111_c") && desc.equals("(F)V"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'renderCloak' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localRenderCloak";
				}
			}
		}
		else if(name.equals("renderCloak") && desc.equals("(F)V"))
		{
			if(superCallingMethods.contains(name + "___" + desc))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return name;
			}
			else
			{
				ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return "localRenderCloak";
			}
		}

		if(isObfuscated)
		{
			if(name.equals("b") && desc.equals("(F)V"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'renderEars' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localRenderEars";
				}
			}
			else if(name.equals("func_78110_b") && desc.equals("(F)V"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'renderEars' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localRenderEars";
				}
			}
		}
		else if(name.equals("renderEars") && desc.equals("(F)V"))
		{
			if(superCallingMethods.contains(name + "___" + desc))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return name;
			}
			else
			{
				ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return "localRenderEars";
			}
		}

		if(isObfuscated)
		{
			if(name.equals("a") && desc.equals("(Lsv;FFF)V"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'setLivingAnimations' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localSetLivingAnimations";
				}
			}
			else if(name.equals("func_78086_a") && desc.equals("(Lnet/minecraft/entity/EntityLivingBase;FFF)V"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'setLivingAnimations' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localSetLivingAnimations";
				}
			}
		}
		else if(name.equals("setLivingAnimations") && desc.equals("(Lnet/minecraft/entity/EntityLivingBase;FFF)V"))
		{
			if(superCallingMethods.contains(name + "___" + desc))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return name;
			}
			else
			{
				ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return "localSetLivingAnimations";
			}
		}

		if(isObfuscated)
		{
			if(name.equals("a") && desc.equals("(FFFFFFLsa;)V"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'setRotationAngles' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localSetRotationAngles";
				}
			}
			else if(name.equals("func_78087_a") && desc.equals("(FFFFFFLnet/minecraft/entity/Entity;)V"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'setRotationAngles' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localSetRotationAngles";
				}
			}
		}
		else if(name.equals("setRotationAngles") && desc.equals("(FFFFFFLnet/minecraft/entity/Entity;)V"))
		{
			if(superCallingMethods.contains(name + "___" + desc))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return name;
			}
			else
			{
				ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return "localSetRotationAngles";
			}
		}

		if(isObfuscated)
		{
			if(name.equals("a") && desc.equals("(Ljava/lang/String;II)V"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'setTextureOffset' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localSetTextureOffset";
				}
			}
			else if(name.equals("func_78085_a") && desc.equals("(Ljava/lang/String;II)V"))
			{
				if(superCallingMethods.contains(name + "___" + desc))
				{
					ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return name;
				}
				else
				{
					ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because it actually is 'setTextureOffset' and its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
					return "localSetTextureOffset";
				}
			}
		}
		else if(name.equals("setTextureOffset") && desc.equals("(Ljava/lang/String;II)V"))
		{
			if(superCallingMethods.contains(name + "___" + desc))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("leaves super method reference '%s%s' in '%s.%s%s' untouched because its local implementation does call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return name;
			}
			else
			{
				ModelPlayerAPIEnhancerClassVisitor.info("renames super method reference '%s%s' in '%s.%s%s' because its local implementation doesn't call its super method", name, desc, enhancableClassName, enhancedName, enhancedDesc);
				return "localSetTextureOffset";
			}
		}

		return name;
	}
}
