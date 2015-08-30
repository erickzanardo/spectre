package org.eck.spec;

import org.junit.runner.Description;

public class SpectreTestEntry {
    private Description spec;
    private Block it;

    public SpectreTestEntry(Description spec, Block it) {
        super();
        this.spec = spec;
        this.it = it;
    }

    public Description getSpec() {
        return spec;
    }

    public Block getIt() {
        return it;
    }

}
