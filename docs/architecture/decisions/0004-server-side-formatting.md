# ADR-0004: Formatting on the server, thin web component

- **Status:** Proposed
- **Date:** 2026-11-23
- **Deciders:** Sebastiano Druda, Jacob Wieser, Tom Springer

## Context

The result is a table in a web page. Formatting could run in the browser (JavaScript) or on the
server (Java). The LLM API key must never reach the browser.

## Options considered

1. **Server-side formatting** – the backend returns `{columns, rows: [{raw, formatted}]}` and a small
   web component only renders it. One language for all the logic, testable with JUnit; the key stays
   on the server.
2. Client-side formatting – richer interactivity, but the formatter catalogue must exist in two
   languages and stay in sync; the backend is still needed for the LLM.

## Decision

Option 1. The web component is deliberately "dumb". HTML escaping happens in **one** place (the
renderer); formatters that produce markup (links, images) declare it explicitly.

## Consequences

- Single source of truth for formatters, easy to test.
- Sorting/filtering on the client must use the `raw` value, not the formatted string.
- Every table request goes through the server (acceptable for this project).
