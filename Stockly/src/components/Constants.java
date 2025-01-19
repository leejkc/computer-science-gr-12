package Stockly.src.components;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Constants {
    private Constants() {}

    public static final int BUTTONS_PER_PAGE = 30;
    public static final Path INVENTORY_PATH = Paths.get("Stockly/src/docs/inventory.txt");
    public static final Path CHANGES_PATH = Paths.get("Stockly/src/docs/changes.txt");
}
