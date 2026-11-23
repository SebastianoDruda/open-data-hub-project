# ADR-0003: Plugins via ServiceLoader, metadata via annotations

- **Status:** Proposed
- **Date:** 2026-11-23
- **Deciders:** Sebastiano Druda, Jacob Wieser, Tom Springer

## Context

New formatters (and data sources) must be addable without modifying existing code (Open-Closed
Principle). The LLM must know which formatters exist and which parameters they accept, and this
list must not drift from the code.

## Options considered

1. **`ServiceLoader` + `@Formatter(id, description, params)` annotation** – standard JDK mechanism,
   no framework; one source of truth for code and LLM catalogue.
2. Manual registration in a map in the core – simple but every new formatter changes the core.
3. Dependency injection framework (Spring) – powerful, but heavy for this need and hides the
   plugin mechanism we want to show.

## Decision

Option 1. A formatter plugin implements `ColumnFormatter`, is annotated with `@Formatter`, and is
published with `provides ... with ...` (plus `META-INF/services` for the classpath, e.g. in tests).
The `FormatterRegistry` in `table-core` discovers them and **generates the catalogue sent to the
LLM from the annotations**.

## Consequences

- Adding a formatter = one class + one `provides` line; the LLM sees it automatically.
- Descriptions in annotations become part of the prompt, so they must be clear and short
  (they affect token cost and answer quality).
- Duplicate ids must be detected by the registry at startup.
