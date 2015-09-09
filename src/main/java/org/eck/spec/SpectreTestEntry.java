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

    public void resolveBefore() {
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

    public void resolveAfter() {
        context.testExecuted();

        List<Block> after = new ArrayList<>();
        List<Block> afterEach = new ArrayList<>();

        Stack<SpecContext> contexts = new Stack<>();

        SpecContext current = context;
        while(current != null) {
            contexts.push(current);
            current = current.getParent();
        }

        while(!contexts.isEmpty()) {
            if(contexts.peek().testCount() == 0) {
                after.addAll(contexts.peek().getAfter());
                // Afters are executed only once, so we remove then from the context
                contexts.peek().getAfter().clear();
            }
            afterEach.addAll(contexts.peek().getAfterEach());
            contexts.pop();
        }

        for (Block block : after) {
            block.execute();
        }

        for (Block block : afterEach) {
            block.execute();
        }
    }
}
