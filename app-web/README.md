# app-web

**Java module:** `it.unibz.odh.app`

## Responsibility

Composition root: HTTP server exposing the formatted table and the web page with the table component. Plugins are only on the runtime path and are discovered through ServiceLoader.

## Dependency rule

Compile-time dependency only on table-core. Plugins are `runtime` dependencies, so the code cannot reference their classes.

**Maven dependencies:** `table-core`, `formatters-basic` (runtime), `formatters-odh` (runtime), `source-tourism` (runtime), `source-mobility` (runtime), `llm-advisor` (runtime)
