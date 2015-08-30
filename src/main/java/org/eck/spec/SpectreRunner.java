package org.eck.spec;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.ComparisonFailure;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

@SuppressWarnings("rawtypes")
public class SpectreRunner extends Runner {

    private List<SpectreTestEntry> specs = new ArrayList<SpectreTestEntry>();
    private Class aClass;
    private Object instance;
    private Description spec;

    public SpectreRunner(Class aClass) {
        this.aClass = aClass;
        try {
            this.instance = this.aClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        SpectreSession.instance().setSpecs(specs);
        SpectreSession.instance().setCurrentClass(aClass);
        spec = Description.createSuiteDescription(this.aClass.getName(), this.aClass.getAnnotations());

        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            if (method.getName().toLowerCase().endsWith("spec")) {
                Description methodSpec = Description.createTestDescription(method.getName(), method.getName());
                spec.addChild(methodSpec);

                SpectreSession.instance().setCurrentContext(new SpecContext(methodSpec));

                try {
                    method.invoke(instance);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Description getDescription() {
        return spec;
    }

    @Override
    public void run(RunNotifier runNotifier) {

        for (SpectreTestEntry entry : specs) {
            runNotifier.fireTestStarted(entry.getSpec());
            try {
                entry.getIt().execute();
            } catch (ComparisonFailure e) {
                Failure failure = new Failure(entry.getSpec(), e);
                runNotifier.fireTestFailure(failure);
            }
            runNotifier.fireTestFinished(entry.getSpec());
        }
    }

}
