# Implementation plan

The work is split into milestones. Each milestone is a set of small issues; each issue is one
branch and one pull request, with several small commits (see [CONTRIBUTING](../../.github/CONTRIBUTING.md)).
The milestones and issues are mirrored on GitHub (Issues → Milestones).

Principles for the whole plan:

- **Walking skeleton first:** get a thin end-to-end path working early (M1), then widen it.
- **Design for current needs** and refactor when needed – avoid speculative generality.
- **Tests with every change.** Unit tests never call the network or an LLM: they use recorded
  JSON fixtures and fake advisors. Live calls are only in the evaluation (M7).
- **Every architectural decision → ADR.** Every AI-assisted session → entry in `docs/ai-log`.

Suggested ownership (to be confirmed by the team), so that every member owns one area end to end:

| Area | Milestones | Owner |
|---|---|---|
| Core & API (contract, registry, resolver, configuration) | M1, M4 | _tbd_ |
| Formatters & web (formatter plugins, renderer, web component) | M2, M6 | _tbd_ |
| Data sources & LLM (Open Data Hub adapters, advisor, evaluation) | M3, M5, M7 | _tbd_ |

Everyone reviews the others' pull requests, and M8 is shared.

---

## M0 – Repository setup ✅

- [x] Repository, license, editor settings, `.gitignore` (API keys excluded)
- [x] Maven multi-module skeleton with JPMS descriptors and Maven wrapper
- [x] CI on GitHub Actions, Dependabot
- [x] Architecture overview, ADR process, initial ADRs (Proposed)
- [x] Implementation plan, contribution guidelines, issue/PR templates, AI log

## M1 – Walking skeleton (contract + core)

Goal: a hard-coded table formatted by one real formatter, end to end in a unit test.

1. Review and accept ADR-0002..0005 (team meeting, merge or amend).
2. `table-api`: `ColumnFormatter`, `@Formatter`/`@Param` annotations, `FormatterStep`, `ColumnSpec`,
   table model (`Column`, `Row`, `Table`), `DataSource`, `FormattingAdvisor`; export the package.
3. `table-core`: `FormatterRegistry` with `ServiceLoader` discovery, duplicate-id check, step
   validation and catalogue export.
4. `table-core`: formatting pipeline (apply a list of steps to a value, error → fallback to raw text).
5. First formatter (`number`) in `formatters-basic` with `provides` and tests.
6. Architecture test that fails if a module breaks the dependency rules (e.g. ArchUnit or a test
   over the module descriptors).

## M2 – Formatter plugins (≥ 5 general formatters)

1. `number` (decimals, locale) – done in M1, extend if needed
2. `unit` (prefix / suffix)
3. `datetime` (ISO 8601 / epoch input, output pattern, time zone)
4. `boolean` (true/false labels, `null` handling)
5. `text` (truncate, case) and `null-placeholder`
6. `link` (URL → anchor, declared as markup)
7. `enum-map` / `percent` (optional)
8. `formatters-odh`: `lang-pick` (`{de,it,en}` → one language), `coordinates`, `array-summary`

Each formatter: class + annotation with a clear description + `provides` + unit tests (normal
values, `null`, wrong type).

## M3 – Open Data Hub data sources

1. Choose and document the datasets (e.g. Tourism `Accommodation`/`ODHActivityPoi`, Mobility `MeteoStation`).
2. Record JSON fixtures from the real APIs into `src/test/resources`.
3. `source-tourism`: HTTP client, `fields=` selection, paging, flattening of nested JSON into columns.
4. `source-mobility`: latest measurements, pivot (one row per station, one column per data type),
   unit (`tunit`) exposed as column metadata.
5. Column sampling (N non-null, distinct, truncated values) used by the resolver and the advisor.

## M4 – Configuration and resolution chain

1. Configuration file format (decide JSON vs YAML → ADR) and loader.
2. `ColumnFormatResolver` as a chain: explicit configuration → cache → advisor → default.
3. Validation errors reported clearly (unknown formatter id, wrong parameter type).
4. Example configuration for the chosen datasets.

## M5 – LLM advisor

1. LangChain4j dependency in `llm-advisor` only; model and provider selected by configuration,
   API key from environment variable.
2. AI service interface returning a typed `TableFormatting`; prompts in `src/main/resources/prompts`
   (role, context, constraints, closed catalogue, few-shot examples).
3. One batched call per table, only unconfigured columns, 3–5 truncated samples each.
4. Validation of the answer against the registry; one retry with the error message (self-reflection).
5. Persistent cache of validated suggestions + export as configuration file (human in the loop).
6. Prompt-injection defences: delimiters around data, truncation, no trust in the output.
7. Logging of tokens and latency for every call.

## M6 – Web application

1. Web framework decision (→ ADR) and HTTP endpoint `GET /api/tables/{dataset}`.
2. Response `{columns, rows: [{raw, formatted}]}`, HTML escaping in the renderer.
3. Web component / page that renders the table (sorting on raw values is a nice extra).
4. Show in the UI where a column format came from (configuration / cache / LLM / default).

## M7 – LLM evaluation and sustainability

1. Hand-written "gold" configuration for the columns of 2 datasets.
2. Evaluation runner: accuracy vs gold, tokens, latency, number of calls, cache hit ratio.
3. Compare at least 3 models (e.g. a small and a large cloud model, one local model via Ollama).
4. Experiments: zero-shot vs few-shot, with/without self-consistency, (optional) tool calling.
5. Results table and charts for the report.

## M8 – Delivery

1. README with screenshots and how to run; module metrics (instability/abstractness).
2. Final report: architecture, ADRs, LLM strategy and evaluation, AI usage.
3. Consolidated AI interaction log (all prompts, as required by the assignment).
4. Poster and presentation.
5. Tag release `v1.0.0`.
