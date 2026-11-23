# table-core

**Java module:** `it.unibz.odh.table.core`

## Responsibility

Formatter registry (ServiceLoader discovery), column format resolution chain (configuration -> cache -> advisor -> default), formatting pipeline and table rendering model.

## Dependency rule

Depends only on table-api. Never depends on a concrete plugin or on an LLM library.

**Maven dependencies:** `table-api`
