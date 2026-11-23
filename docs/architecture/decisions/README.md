# Architecture Decision Records

We record every significant architectural decision as a short ADR (see [ADR-0001](0001-record-architecture-decisions.md)).

| ADR | Title | Status |
|---|---|---|
| [0001](0001-record-architecture-decisions.md) | Record architecture decisions | Accepted |
| [0002](0002-java-maven-multi-module-jpms.md) | Java 21, Maven multi-module and JPMS | Proposed |
| [0003](0003-plugins-via-serviceloader-and-annotations.md) | Plugins via ServiceLoader, metadata via annotations | Proposed |
| [0004](0004-server-side-formatting.md) | Formatting on the server, thin web component | Proposed |
| [0005](0005-llm-suggestion-uses-configuration-format.md) | The LLM suggestion uses the configuration format | Proposed |

**Process:** copy [`template.md`](template.md), give it the next number, open a pull request.
A *Proposed* ADR becomes *Accepted* when the whole team has approved the pull request.
Accepted ADRs are not edited: a new ADR *supersedes* them.
