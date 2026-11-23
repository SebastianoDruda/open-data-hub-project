# ADR-0001: Record architecture decisions

- **Status:** Accepted
- **Date:** 2026-11-23
- **Deciders:** Sebastiano Druda, Jacob Wieser, Tom Springer

## Context

The project is evaluated on how the team applies software architecture concepts, and the design
must be driven and understood by the students (also when AI assistants are used). Decisions
discussed only in chat are lost and cannot be presented or reviewed.

## Decision

We keep Architecture Decision Records (lightweight, one Markdown file per decision) in `docs/adr`,
following the template in `template.md`. Every ADR is reviewed through a pull request.

## Consequences

- The report and the poster can reference the ADRs to explain the trade-offs.
- New decisions that contradict an accepted ADR must supersede it explicitly.
