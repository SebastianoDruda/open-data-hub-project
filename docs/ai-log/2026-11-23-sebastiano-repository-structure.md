# Session 02 – Repository structure and workflow

| | |
|---|---|
| **Date** | 2026-11-23 |
| **Author** | Sebastiano Druda |
| **Tool / model** | Claude Code (Claude Opus 5.5) |
| **Goal** | Simplify the repository layout and the Git workflow before the implementation starts. |

## 1. Requests to the assistant

1. **Single-branch workflow** – work only on `main`, without feature branches, and integrate the
   pending dependency updates directly into `main`.
2. **Folder organisation** – group modules and documentation into folders by type and remove
   scattered single files from the repository.

## 2. Outcome

### Folder structure
- Modules grouped by type: `api/`, `core/`, `formatters/{basic,odh}/`, `sources/{tourism,mobility}/`,
  `llm/`, `app/` (artifact ids and Java module names unchanged).
- The responsibility of each module is documented in its `module-info.java` Javadoc and POM
  description instead of separate README files; empty placeholders were removed.
- Documentation grouped by type: `docs/architecture/` (overview and `decisions/`),
  `docs/planning/`, `docs/ai-log/`; `CONTRIBUTING.md` moved to `.github/`.

### Workflow and build
- Dependency updates applied on `main`: Maven 3.10.2, Maven plugins, JUnit 6.1.3, GitHub Actions.
- Single-branch workflow described in `.github/CONTRIBUTING.md`: small Conventional Commits with the
  issue number, `./mvnw verify` before every push, no force push on `main`.
- Tooling that only makes sense with pull requests (Dependabot, pull request template, CODEOWNERS)
  removed; CI runs on every push to `main`.

## 3. Decisions and verification by the team

- Folder-by-type layout chosen for readability, consistent with the structure used in the
  Software Components lab.
- All internal documentation links checked; build verified locally and in CI after every step.

## 4. Open points

- Team members to confirm the workflow in the kick-off meeting (issue #4).
