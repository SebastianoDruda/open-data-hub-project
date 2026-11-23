# Contributing

## Workflow

1. **Pick or open an issue.** Every piece of work starts from an issue in a milestone
   (see [docs/planning/implementation-plan.md](../docs/planning/implementation-plan.md)). Assign it to yourself.
2. **Create a branch** from an up-to-date `main`:
   `feat/<issue-number>-short-name`, `fix/...`, `docs/...`, `refactor/...`, `test/...`, `chore/...`.
3. **Commit small and often**, using [Conventional Commits](https://www.conventionalcommits.org/):
   `feat(formatters-basic): add unit formatter`, `test(core): cover duplicate formatter ids`.
   One logical change per commit; the build must pass at every commit.
4. **Open a pull request** early (draft is fine), link the issue (`Closes #12`), fill in the template.
5. **Merge** when CI is green and at least one other team member has approved.
   `main` is protected: no direct pushes, no force pushes.

## Build

```bash
./mvnw verify          # compile, run all tests, enforce build rules
./mvnw -pl table-core -am verify   # one module and what it needs
```

Requirements: JDK 21+. No API key is needed to build or test.

## Design rules

- Respect the module dependency rules in [docs/architecture/overview.md](../docs/architecture/overview.md):
  everything depends on `table-api`; plugins never depend on each other; only `llm-advisor`
  depends on an LLM library.
- A change that affects the structure (new module, new dependency, new pattern, changed contract)
  needs an **ADR** in `docs/architecture/decisions` in the same pull request.
- Clean code: meaningful names, small functions, guard clauses, no `if` chains on types
  (use polymorphism / the registry), no duplicated logic.
- Changes to `table-api` affect every module: discuss them with the team first.

## Tests

- Every new class has unit tests (JUnit 5) in the same package.
- Unit tests must not use the network or an LLM: use JSON fixtures in `src/test/resources` and
  fake implementations of `FormattingAdvisor`.
- Live API / LLM calls belong to the evaluation runs (milestone M7), not to `./mvnw verify`.

## Secrets

Never commit API keys. Use environment variables (e.g. `OPENAI_API_KEY`); see `.env.example`
once it exists. `.env` and `*.key` files are ignored by git.

## Use of AI assistants

The assignment requires that **all prompts and interactions with AI are shared** and that the team
understands everything that is committed. Therefore:

- every AI-assisted working session gets an entry in [`docs/ai-log`](../docs/ai-log/README.md);
- mention AI assistance in the pull request description;
- never merge code you cannot explain to the rest of the team.
