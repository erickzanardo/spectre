package org.eck.spec;

import static org.eck.spec.Spectre.context;
import static org.eck.spec.Spectre.before;
import static org.eck.spec.Spectre.beforeEach;
import static org.eck.spec.Spectre.describe;
import static org.eck.spec.Spectre.it;

import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(SpectreRunner.class)
public class SpectreBeforeTest {
    int beforeCounter = 0;
    int beforeEachCounter = 0;
    int beforeInnerCounter = 0;
    int beforeInnerEachCounter = 0;


    public void beforeSpec() {
        describe("#before", () -> {

            before(() -> {
                beforeCounter++;
            });

            beforeEach(() -> {
                beforeEachCounter++;
            });

            it("before runs only once per block", () -> {
                Assert.assertEquals(1, beforeCounter);
                Assert.assertEquals(1, beforeEachCounter);
            });

            it("beforeEach runs only once per block", () -> {
                Assert.assertEquals(1, beforeCounter);
                Assert.assertEquals(2, beforeEachCounter);
            });

            context("#before each on inner context", () -> {
                before(() -> {
                    beforeInnerCounter++;
                });

                beforeEach(() -> {
                    beforeInnerEachCounter++;
                });

                it("(inner context) before runs only once per block", () -> {
                    Assert.assertEquals(1, beforeCounter);
                    Assert.assertEquals(3, beforeEachCounter);
                    Assert.assertEquals(1, beforeInnerCounter);
                    Assert.assertEquals(1, beforeInnerEachCounter);
                });

                it("(inner context) beforeEach runs only once per block", () -> {
                    Assert.assertEquals(1, beforeCounter);
                    Assert.assertEquals(4, beforeEachCounter);
                    Assert.assertEquals(1, beforeInnerCounter);
                    Assert.assertEquals(2, beforeInnerEachCounter);
                });
            });
        });
    }
}
