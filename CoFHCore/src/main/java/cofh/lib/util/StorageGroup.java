package cofh.lib.util;

public enum StorageGroup {

    ALL,            // Added ONLY to Inventory, no sub-category
    INPUT,          // Process Input
    OUTPUT,         // Process Output
    ACCESSIBLE,     // Input + Output
    CATALYST,       // Used in Process; accessible on Input Sides. Non-recipe.
    INTERNAL        // Inaccessible externally
}
