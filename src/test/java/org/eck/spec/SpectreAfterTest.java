package org.eck.spec;

import static org.eck.spec.Spectre.context;
import static org.eck.spec.Spectre.after;
import static org.eck.spec.Spectre.afterEach;
import static org.eck.spec.Spectre.describe;
import static org.eck.spec.Spectre.it;

import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(SpectreRunner.class)
public class SpectreAfterTest {
    int afterCounter = 0;
    int afterEachCounter = 0;
    int afterInnerCounter = 0;
    int afterInnerEachCounter = 0;


    public void afterSpec() {
        describe("#after", () -> {

            after(() -> {
                afterCounter++;
            });

            afterEach(() -> {
                afterEachCounter++;
            });

            it("after runs only once per block", () -> {
                Assert.assertEquals(0, afterCounter);
                Assert.assertEquals(0, afterEachCounter);
            });

            it("afterEach runs only once per block", () -> {
                Assert.assertEquals(0, afterCounter);
                Assert.assertEquals(1, afterEachCounter);
            });

            context("#after each on inner context", () -> {
                after(() -> {
                    afterInnerCounter++;
                });

                afterEach(() -> {
                    afterInnerEachCounter++;
                });

                it("(inner context) after runs only once per block", () -> {
                    Assert.assertEquals(0, afterCounter);
                    Assert.assertEquals(2, afterEachCounter);
                    Assert.assertEquals(0, afterInnerCounter);
                    Assert.assertEquals(0, afterInnerEachCounter);
                });

                it("(inner context) afterEach runs only once per block", () -> {
                    Assert.assertEquals(0, afterCounter);
                    Assert.assertEquals(3, afterEachCounter);
                    Assert.assertEquals(0, afterInnerCounter);
                    Assert.assertEquals(1, afterInnerEachCounter);
                });
            });
        });
    }
}
