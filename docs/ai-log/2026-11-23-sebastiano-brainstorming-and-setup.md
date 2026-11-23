# 2026-11-23 – Brainstorming and repository setup

- **Author:** Sebastiano Druda
- **Tool / model:** Claude Code (Claude Opus 5.5)
- **Goal:** understand the assignment, connect it with the course material and set up the repository.

## Prompts

1. > ciao leggi il pdf nella cartella aperta e iniziamo a brainstormare e a studiare questo progetto.
2. > ok studialo molto bene e dai anche un occhio a queste slides per contesto dalle slide del professore:
   > 0-Course Introduction 2026.pdf, 1-What is software architecture.pdf, 2-Clean Code and Refactoring.pdf,
   > 3-Software Components.pdf, 4-Introduction to Applications with GenAI.pdf,
   > 5-Prompt Engineering for AI Agents.pdf, 6. Generative AI Agents.pdf, 7. Integrating External Sources.pdf
3. > ok ottimo, io inizierei con il creare la repository e invitare sia jacob wieser sia tom springer,
   > abbiamo già i loro username in altre repository dovresti poterlo fare, fai anche setup completo della
   > repository in modo che sia organizzata molto bene seconto tutte le best practices di software
   > architecture. mi raccomando, e fai un piano di implementazione non tutto in 1 commit. intanto fai
   > questo per me grazie.. la repo chiamala "open-data-hub-project" grazie.

## What the AI produced

- Summary of the assignment (requirements, cross-cutting requirements on AI usage and sustainability).
- Mapping of the course slides to the project: Open-Closed Principle and polymorphism (slides 2),
  component principles REP/CCP/CRP/ADP/SDP/SAP and metrics (3), model choice, energy, reliability and
  prompt injection (4), prompt patterns – templates, few-shot, self-consistency, meta-prompting (5),
  LangChain4j AI services with typed returns and self-reflection (6), RAG / tool calling / MCP (7).
- Inspection of real Open Data Hub responses (Mobility `MeteoStation/air-temperature/latest`,
  Tourism `Accommodation` with `fields=`): the Mobility API already carries the unit (`tunit: "°C"`),
  Tourism data is nested and multilingual.
- Proposed architecture: star-shaped modules around `table-api`, formatters and data sources as
  `ServiceLoader` plugins, formatter catalogue for the LLM generated from annotations, resolution
  chain configuration → cache → LLM → default, LLM answer in the configuration format.
- Repository setup in separate commits: base files, Maven multi-module + JPMS skeleton, CI,
  architecture overview + ADRs (as *Proposed*), implementation plan, contribution guidelines and
  templates, this log; GitHub repository, collaborators, labels, milestones and issues.

## What we changed, rejected or verified ourselves

- ADR-0002..0005 are intentionally left as *Proposed*: the whole team must review and accept them
  in milestone M1 before implementation starts.
- Suggestions rejected for now as speculative generality: RAG / vector database and MCP
  (the formatter catalogue is small enough to fit in the prompt). Tool calling is kept as an
  optional experiment for the evaluation (M7).
- _To be completed by the team after review._
