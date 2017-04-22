package model.tiles;

import model.CityResources;

/**
 * State-less tile that represents castle tiles.
 */
public final class CastlebTile extends Tile {

    // Constant
    /**
     * Default instance.
     */
    private final static CastlebTile INSTANCE = new CastlebTile();

    // Factory
    /**
     * @return Default castle tile.
     */
    public static CastlebTile getDefault() {
        // Provide always the same instance since Castle is not changing.
        return CastlebTile.INSTANCE;
    }

    // Creation
    /**
     * Prefer use {@link CastlebTile#getDefault()} instead.
     */
    private CastlebTile() {
    }

    // Access
    @Override
    public int hashCode() {
        return 0;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof CastlebTile;
    }

    // Change
    @Override
    public void update(CityResources res) {
        // Do nothings.
    }

}
