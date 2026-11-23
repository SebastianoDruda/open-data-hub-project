/**
 * Composition root: HTTP server exposing the formatted table and the web page with the table
 * component. Plugins are only on the runtime path and are discovered through ServiceLoader.
 */
module it.unibz.odh.app {
    requires it.unibz.odh.table.core;
}
