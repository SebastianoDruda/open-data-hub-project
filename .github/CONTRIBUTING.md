# Contributing

## Workflow

We work on a **single branch: `main`**. No feature branches, no pull requests.

1. **Pick an issue** from a milestone (see [docs/planning/implementation-plan.md](../docs/planning/implementation-plan.md))
   and assign it to yourself, so nobody works on the same thing.
2. **Update before you start:** `git pull --rebase`.
3. **Commit small and often**, using [Conventional Commits](https://www.conventionalcommits.org/) and
   the issue number: `feat(formatters): add unit formatter (#12)`, `test(core): cover duplicate ids (#7)`.
   One logical change per commit. Close an issue from the commit that finishes it: `Closes #7`.
4. **Before pushing:** `./mvnw verify` must pass, then `git pull --rebase` and `git push`.
   Never use `git push --force` on `main`.
5. **CI runs on every push.** If `main` becomes red, fixing it is the top priority of whoever broke it.
6. **Review:** changes to `api/` (the shared contract) and to the ADRs are discussed with the team
   in the issue *before* pushing. Everything else is reviewed by reading the commits on `main`.

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
  needs an **ADR** in `docs/architecture/decisions` in the same push.
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
- mention AI assistance in the commit message body;
- never push code you cannot explain to the rest of the team.
