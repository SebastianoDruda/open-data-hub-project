# formatters-basic

**Java module:** `it.unibz.odh.formatters.basic`

## Responsibility

Plugin with the general purpose formatters (number, unit, date/time, boolean, text, link, ...).

## Dependency rule

Depends only on table-api. Formatters are published with `provides ... with ...` and are never referenced directly by other modules.

**Maven dependencies:** `table-api`
