# source-tourism

**Java module:** `it.unibz.odh.source.tourism`

## Responsibility

Adapter for the Open Data Hub Tourism API (https://tourism.api.opendatahub.com): fetches a dataset and converts the JSON into the table model.

## Dependency rule

Depends only on table-api (plus an HTTP/JSON library). Unit tests use recorded JSON fixtures, never the live API.

**Maven dependencies:** `table-api`
