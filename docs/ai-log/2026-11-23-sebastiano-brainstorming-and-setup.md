# Session 01 – Assignment analysis and repository setup

| | |
|---|---|
| **Date** | 2026-11-23 |
| **Author** | Sebastiano Druda |
| **Tool / model** | Claude Code (Claude Opus 5.5) |
| **Goal** | Understand the assignment, relate it to the course material and set up the project repository. |

## 1. Requests to the assistant

1. **Assignment analysis** – read the Design Challenge 1 description (Table Column Formatters) and
   start a brainstorming on possible solutions.
2. **Alignment with the course** – study the assignment in depth using the course slides
   (lectures 0–7: software architecture, clean code and SOLID, software components, GenAI
   applications, prompt engineering, AI agents, external sources) as context, so that the solution
   applies the concepts of the course.
3. **Repository setup** – create the public repository `open-data-hub-project`, add the team members,
   organise it according to software architecture best practices and write an implementation plan.
   The setup had to be delivered in small, incremental commits.

## 2. Outcome

### Analysis
- Summary of the functional requirements (table generated from an Open Data Hub API, per-column
  formatters, configuration or LLM suggestion, extensibility, at least 5 formatters) and of the
  cross-cutting requirements (open source, pluggable architecture, AI usage shared, LLM strategy
  justified in terms of prompting, performance and sustainability, tests on several models).
- Mapping of each lecture to concrete design choices: Open-Closed Principle and polymorphism,
  component principles (REP, CCP, CRP, ADP, SDP, SAP) and their metrics, model choice and energy
  consumption, prompt patterns (templates, few-shot, self-consistency, self-reflection),
  LangChain4j AI services with typed return values.
- Inspection of real Open Data Hub responses: the Mobility API is flat and already provides the unit
  of measure (`tunit`), the Tourism API is nested and multilingual.

### Architecture proposal
- Star-shaped module structure around a stable, abstract `table-api` module.
- Formatters and data sources as plugins discovered with `ServiceLoader`; formatter metadata in
  annotations, also used to generate the catalogue sent to the LLM.
- Resolution chain: explicit configuration → cached suggestion → LLM advisor → plain-text default.
- The LLM answers in the same format as the configuration, so the answer can be validated, cached
  and reviewed.

### Repository
- Maven multi-module build with JPMS descriptors, Maven wrapper and enforcer rules.
- Continuous integration with GitHub Actions.
- Documentation: architecture overview, Architecture Decision Records (ADR-0001 to ADR-0005),
  implementation plan (milestones M0–M8), contribution guidelines, this log.
- GitHub milestones and issues that mirror the implementation plan.

## 3. Decisions and verification by the team

- ADR-0002 to ADR-0005 are left as *Proposed*: the whole team reviews and accepts them in
  milestone M1 (issue #4) before the implementation starts.
- RAG / vector databases and MCP are not adopted for now: the formatter catalogue is small enough to
  fit in the prompt, so they would be speculative generality. Tool calling is kept as an optional
  experiment for the LLM evaluation (M7).
- The build was verified locally (`./mvnw verify`) and in CI.

## 4. Open points

- Review of the ADRs and assignment of the work areas within the team (issue #4).
- Choice of the datasets for the demo (milestone M3).
