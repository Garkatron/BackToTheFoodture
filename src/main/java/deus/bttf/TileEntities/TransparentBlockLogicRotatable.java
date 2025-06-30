package deus.bttf.TileEntities;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.material.Material;

public class TransparentBlockLogicRotatable extends BlockLogicRotatable {

	private boolean renderInside;

	public TransparentBlockLogicRotatable(Block<?> block, Material material) {
		super(block, material);
	}

	public void BlockTransparent(String key, int id, Material material, boolean renderInside) {
		this.renderInside = renderInside;
	}

	public boolean isSolidRender() {
		return false;
	}


}
