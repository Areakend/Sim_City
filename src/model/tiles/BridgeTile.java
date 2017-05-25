package model.tiles;

import model.CityResources;

public class BridgeTile extends Tile implements Destroyable {
	
    // Constant
    /**
     * Default instance.
     */
    public final static BridgeTile INSTANCE = new BridgeTile();

    // Factory
    /**
     * @return Default river tile.
     */
    public static BridgeTile getDefault() {
        // Provide always the same instance since River is not changing.
        return BridgeTile.INSTANCE;
    }
	
	protected boolean isDestroyed;
	
	public BridgeTile(){
		super();
		this.isDestroyed=false;
	}
    public boolean isDestroyed() {
        return this.isDestroyed;
    }
	@Override
	public void disassemble(CityResources res) {
        if (!this.isDestroyed) {
            this.isDestroyed = true;
        }
		
	}
	@Override
	public void update(CityResources res) {
		//La route ne fait rien
	}

}
