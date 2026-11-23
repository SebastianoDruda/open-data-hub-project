# ADR-0002: Java 21, Maven multi-module and JPMS

- **Status:** Proposed
- **Date:** 2026-11-23
- **Deciders:** Sebastiano Druda, Jacob Wieser, Tom Springer

## Context

The challenge requires good modularity and a pluggable architecture; the language is free.
The course examples use Java and LangChain4j, and the team already built a plugin system with Java
modules and `ServiceLoader` in a previous lab. The repository must be open source and easy to build
for anyone (and in CI).

## Options considered

1. **Java 21 + Maven multi-module + JPMS (`module-info.java`)** – module boundaries checked by the
   compiler, `provides`/`uses` for plugins, same tools as the course; JPMS can be strict with
   third-party libraries that are not modular (automatic modules).
2. Java + Maven multi-module without JPMS – simpler with libraries, but boundaries only by
   convention (`META-INF/services`).
3. TypeScript (frontend + Node backend) – one language for the web, but further from the course
   material and from LangChain4j.

## Decision

Option 1. Each component is a Maven module **and** a Java module. The Maven wrapper (`./mvnw`)
pins the build tool version. Java 21 (LTS) is the minimum version.

## Consequences

- Illegal dependencies (e.g. the app using a plugin class) fail at compile time.
- Instability/abstractness can be measured per module.
- If a library causes JPMS problems we may need `requires static`, automatic module names or,
  in the worst case, a new ADR that drops JPMS for that module.
