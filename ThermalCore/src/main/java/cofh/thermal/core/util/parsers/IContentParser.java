package cofh.thermal.core.util.parsers;

import com.google.gson.JsonElement;

public interface IContentParser {

    /**
     * Called before JSON files are parsed.
     */
    void preProcess();

    /**
     * Parses a JSON element (file).
     *
     * @return TRUE if the format is valid, FALSE if not.
     */
    boolean process(JsonElement element);

    /**
     * Called after JSON files are parsed.
     */
    void postProcess();

}
