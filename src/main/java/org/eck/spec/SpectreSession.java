package org.eck.spec;

import java.util.List;
import java.util.Stack;

import org.junit.runner.Description;

@SuppressWarnings("rawtypes")
public class SpectreSession {
    private Stack<Description> specStack = new Stack<>();
    private static SpectreSession instance;
    private Class currentClass;
    private Description currentDescription;
    private List<SpectreTestEntry> specs;

    public static SpectreSession instance() {
        if (instance == null)
            instance = new SpectreSession();
        return instance;
    }

    public void setCurrentDescription(Description desc) {
        this.currentDescription = desc;
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

    public Description createNewTestDescription(String name) {
        Description spec = Description.createTestDescription(currentClass, name);
        currentDescription.addChild(spec);
        setCurrentDescription(spec);
        return spec;
    }

    public Description createNewTestGroupDescription(String name) {
        Description spec = Description.createSuiteDescription(name, currentClass.getAnnotations());
        currentDescription.addChild(spec);
        setCurrentDescription(spec);
        specStack.push(spec);
        return spec;
    }

    public void blockDone() {
        if (specStack.size() > 1) {
            specStack.pop();
            setCurrentDescription(specStack.peek());
        }
    }

}
