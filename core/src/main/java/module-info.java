/**
 * Formatter registry (ServiceLoader discovery), column format resolution chain (configuration
 * -> cache -> advisor -> default), formatting pipeline and table rendering model.
 */
module it.unibz.odh.table.core {
    requires transitive it.unibz.odh.table.api;

    // M1: add 'exports it.unibz.odh.table.core;' together with the first public types
    // (javac rejects exporting a package that contains no classes yet).
}
