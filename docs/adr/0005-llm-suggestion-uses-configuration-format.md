# ADR-0005: The LLM suggestion uses the configuration format

- **Status:** Proposed
- **Date:** 2026-11-23
- **Deciders:** Sebastiano Druda, Jacob Wieser, Tom Springer

## Context

When a column is not configured, an LLM suggests one or more formatters. LLM answers are
non-deterministic, can hallucinate (unknown formatters, wrong parameters) and cost tokens and
energy at every call.

## Options considered

1. **The LLM returns exactly the configuration structure** (`ColumnSpec` = column + list of
   `{id, params}`), validated against the registry and cached.
2. The LLM returns free text that is parsed – fragile, hard to validate.
3. The LLM formats the values itself – expensive (every value through the model), not reproducible.

## Decision

Option 1. Consequences on the design:

- the prompt contains a **closed catalogue** generated from the registry; unknown ids are rejected;
- **one batched call per table** with only the unconfigured columns and 3–5 truncated samples each;
- the validated answer is **cached** and can be exported and reviewed by a human, becoming explicit
  configuration (human in the loop);
- invalid steps are dropped; one retry with the validation error may be attempted (self-reflection).

## Consequences

- LLM calls drop to (almost) zero after the first run of a dataset.
- Configuration and suggestions share parsing, validation and tests.
- Quality of the suggestions depends on the catalogue descriptions – they must be evaluated (M7).
