# llm-advisor

**Java module:** `it.unibz.odh.llm`

## Responsibility

Implementation of the formatting advisor with LangChain4j: builds the prompt from the formatter catalogue and column samples, validates the answer and supports several model providers.

## Dependency rule

The only module that depends on an LLM library (Common Reuse Principle: users who configure formatters by hand do not depend on LangChain4j). Prompts live in src/main/resources/prompts.

**Maven dependencies:** `table-api`
