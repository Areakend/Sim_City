package model.tiles;

import model.CityResources;

/**
 * State-less tile that represents castle tiles.
 */
public final class CastleaTile extends Tile {

    // Constant
    /**
     * Default instance.
     */
    private final static CastleaTile INSTANCE = new CastleaTile();

    // Factory
    /**
     * @return Default castle tile.
     */
    public static CastleaTile getDefault() {
        // Provide always the same instance since Castle is not changing.
        return CastleaTile.INSTANCE;
    }

    // Creation
    /**
     * Prefer use {@link CastleaTile#getDefault()} instead.
     */
    private CastleaTile() {
    }

    // Access
    @Override
    public int hashCode() {
        return 0;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof CastleaTile;
    }

    // Change
    @Override
    public void update(CityResources res) {
        // Do nothings.
    }

}
