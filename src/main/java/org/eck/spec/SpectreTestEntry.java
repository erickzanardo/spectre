package org.eck.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    public void resolveContext() {
        List<Block> before = new ArrayList<>();
        List<Block> beforeEach = new ArrayList<>();

        Stack<SpecContext> contexts = new Stack<>();

        SpecContext current = context;
        while(current != null) {
            contexts.push(current);
            current = current.getParent();
        }

        while(!contexts.isEmpty()) {
            before.addAll(contexts.peek().getBefore());
            // Befores are executed only once, so we remove then from the context
            contexts.peek().getBefore().clear();
            beforeEach.addAll(contexts.peek().getBeforeEach());
            contexts.pop();
        }

        for (Block block : before) {
            block.execute();
        }

        for (Block block : beforeEach) {
            block.execute();
        }
    }
}
