package model.tiles;

import model.CityResources;

/**
 * State-less tile that represents castle tiles.
 */
public final class CastledTile extends Tile {

    // Constant
    /**
     * Default instance.
     */
    private final static CastledTile INSTANCE = new CastledTile();

    // Factory
    /**
     * @return Default castle tile.
     */
    public static CastledTile getDefault() {
        // Provide always the same instance since Castle is not changing.
        return CastledTile.INSTANCE;
    }

    // Creation
    /**
     * Prefer use {@link CastledTile#getDefault()} instead.
     */
    private CastledTile() {
    }

    // Access
    @Override
    public int hashCode() {
        return 0;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof CastledTile;
    }

    // Change
    @Override
    public void update(CityResources res) {
        // Do nothings.
    }

}
