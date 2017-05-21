package model.tiles;

import model.CityResources;

public class RoadTile extends Tile implements Destroyable {
	
    // Constant
    /**
     * Default instance.
     */
    public final static RoadTile INSTANCE = new RoadTile();

    // Factory
    /**
     * @return Default river tile.
     */
    public static RoadTile getDefault() {
        // Provide always the same instance since River is not changing.
        return RoadTile.INSTANCE;
    }
	
	protected boolean isDestroyed;
	
	public RoadTile(){
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
