package model.tiles;

import model.CityResources;

public class RoadTile extends Tile implements Destroyable {
	
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
