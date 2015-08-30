package org.eck.spec;

import static org.eck.spec.Spectre.context;
import static org.eck.spec.Spectre.describe;
import static org.eck.spec.Spectre.it;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;

@RunWith(SpectreRunner.class)
public class SpectreStructureTest {
    public void spec() {
        describe("describe", () -> {
            String someValue = "someValue";
            context("some context", () -> {
                it("test something", () -> {
                    assertEquals("someValue", someValue);
                });
            });
            context("another context", () -> {
                it("test other things", () -> {
                    assertEquals("someValue", someValue);
                });
            });
        });
    }
}
