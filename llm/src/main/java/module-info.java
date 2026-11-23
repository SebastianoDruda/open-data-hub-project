/**
 * Implementation of the formatting advisor with LangChain4j: builds the prompt from the
 * formatter catalogue and column samples, validates the answer and supports several model
 * providers.
 */
module it.unibz.odh.llm {
    requires it.unibz.odh.table.api;
}
