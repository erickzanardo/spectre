package org.eck.spec;

import static org.eck.spec.Spectre.context;
import static org.eck.spec.Spectre.describe;
import static org.eck.spec.Spectre.before;
import static org.eck.spec.Spectre.after;
import static org.eck.spec.Spectre.it;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(SpectreRunner.class)
public class SampleTest {
    private List<Integer> list = new ArrayList<Integer>();

    public void listSpec() {
        describe("List", () -> {
            before(() -> {
                list.add(1);
                list.add(2);
            });

            describe("#contains", () -> {
                it("contains the value 1", () -> {
                    Assert.assertTrue(list.contains(1));
                });
                it("doesn't contains the value 3", () -> {
                    Assert.assertFalse(list.contains(3));
                });
            });

            after(() -> {
                list.clear();
            });
        });
    }
}
