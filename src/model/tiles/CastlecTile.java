package model.tiles;

import model.CityResources;

/**
 * State-less tile that represents castle tiles.
 */
public final class CastlecTile extends Tile {

    // Constant
    /**
     * Default instance.
     */
    private final static CastlecTile INSTANCE = new CastlecTile();

    // Factory
    /**
     * @return Default castle tile.
     */
    public static CastlecTile getDefault() {
        // Provide always the same instance since Castle is not changing.
        return CastlecTile.INSTANCE;
    }

    // Creation
    /**
     * Prefer use {@link CastlecTile#getDefault()} instead.
     */
    private CastlecTile() {
    }

    // Access
    @Override
    public int hashCode() {
        return 0;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof CastlecTile;
    }

    // Change
    @Override
    public void update(CityResources res) {
        // Do nothings.
    }

}
