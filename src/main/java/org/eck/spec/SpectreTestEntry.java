package org.eck.spec;

import org.junit.runner.Description;

public class SpectreTestEntry {
    private Description spec;
    private Block it;
    private SpecContext context;

    public SpectreTestEntry(Description spec, Block it, SpecContext currentContext) {
        super();
        this.spec = spec;
        this.it = it;
        this.context = currentContext;
    }

    public Description getSpec() {
        return spec;
    }

    public Block getIt() {
        return it;
    }

    public SpecContext getContext() {
        return context;
    }
}
