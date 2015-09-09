package org.eck.spec;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;

public class SpecContext {
    private Description spec;
    private List<Block> before = new ArrayList<>();
    private List<Block> beforeEach = new ArrayList<>();
    private List<Block> after = new ArrayList<>();
    private List<Block> afterEach = new ArrayList<>();
    private SpecContext parent;
    private int tests = 0;

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

    public List<Block> getAfter() {
        return after;
    }

    public List<Block> getAfterEach() {
        return afterEach;
    }

    public SpecContext getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return spec.toString();
    }

    public int testCount() {
        return this.tests;
    }

    public void newTestOnThisContext() {
        this.tests++;
        if(this.parent != null) {
            this.parent.newTestOnThisContext();
        }
    }

    public void testExecuted() {
        this.tests--;
        if(this.parent != null) {
            this.parent.testExecuted();
        }
    }
}
