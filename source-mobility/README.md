# source-mobility

**Java module:** `it.unibz.odh.source.mobility`

## Responsibility

Adapter for the Open Data Hub Mobility API (https://mobility.api.opendatahub.com): fetches measurements and pivots them into the table model.

## Dependency rule

Depends only on table-api (plus an HTTP/JSON library). Unit tests use recorded JSON fixtures, never the live API.

**Maven dependencies:** `table-api`
