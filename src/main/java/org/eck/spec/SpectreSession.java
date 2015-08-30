package org.eck.spec;

import java.util.List;
import java.util.Stack;

import org.junit.runner.Description;

@SuppressWarnings("rawtypes")
public class SpectreSession {
    private Stack<SpecContext> specStack = new Stack<>();
    private static SpectreSession instance;
    private Class currentClass;
    private SpecContext currentContext;
    private List<SpectreTestEntry> specs;

    public static SpectreSession instance() {
        if (instance == null)
            instance = new SpectreSession();
        return instance;
    }

    public void setCurrentContext(SpecContext context) {
        this.currentContext = context;
        ;
    }

    public void setCurrentClass(Class aClass) {
        this.currentClass = aClass;
    }

    public void setSpecs(List<SpectreTestEntry> specs) {
        this.specs = specs;
    }

    public void addSpec(SpectreTestEntry spec) {
        this.specs.add(spec);
    }

    public Description createNewTestDescription(String name, Block block) {
        Description spec = Description.createTestDescription(currentClass, name);
        currentContext.getSpec().addChild(spec);

        SpectreTestEntry specEntry = new SpectreTestEntry(spec, block, currentContext);
        addSpec(specEntry);

        return spec;
    }

    public Description createNewTestGroupDescription(String name) {
        Description spec = Description.createSuiteDescription(name, currentClass.getAnnotations());
        SpecContext context = new SpecContext(spec);

        if (currentContext != null) {
            context.setParent(context);
        }

        currentContext.getSpec().addChild(spec);
        specStack.push(new SpecContext(spec));

        setCurrentContext(context);
        return spec;
    }

    public void addBefore(Block block) {
        currentContext.getBefore().add(block);
    }

    public void addBeforeEach(Block block) {
        currentContext.getBeforeEach().add(block);
    }

    public void blockDone() {
        if (specStack.size() > 1) {
            specStack.pop();
            setCurrentContext(specStack.peek());
        }
    }

}
