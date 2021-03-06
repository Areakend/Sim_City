package model.tiles;

import model.CityResources;

/**
 * State-less tile that represents river tiles.
 */
public final class RiverTile extends Tile {

    // Constant
    /**
     * Default instance.
     */
    private final static RiverTile INSTANCE = new RiverTile();

    // Factory
    /**
     * @return Default river tile.
     */
    public static RiverTile getDefault() {
        // Provide always the same instance since River is not changing.
        return RiverTile.INSTANCE;
    }

    // Creation
    /**
     * Prefer use {@link RiverTile#getDefault()} instead.
     */
    private RiverTile() {
    }

    // Access
    @Override
    public int hashCode() {
        return 0;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof RiverTile;
    }
    
    public void disassemble(CityResources res){
    }

    // Change
    @Override
    public void update(CityResources res) {
        // Do nothings.
    }

}
