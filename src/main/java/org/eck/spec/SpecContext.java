package org.eck.spec;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;

public class SpecContext {
    private Description spec;
    private List<Block> before = new ArrayList<>();
    private List<Block> beforeEach = new ArrayList<>();
    private SpecContext parent;

    public SpecContext(Description spec) {
        super();
        this.spec = spec;
    }

    public Description getSpec() {
        return spec;
    }

    public List<Block> getBefore() {
        return before;
    }

    public List<Block> getBeforeEach() {
        return beforeEach;
    }

    public void setParent(SpecContext parent) {
        this.parent = parent;
    }

    public SpecContext getParent() {
        return parent;
    }
}
