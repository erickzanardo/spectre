package org.eck.spec;

public class Spectre {
    public static void describe(String decription, Block block) {
        SpectreSession.instance().createNewTestGroupDescription(decription);
        block.execute();
        SpectreSession.instance().blockDone();
    }

    public static void context(String decription, Block block) {
        SpectreSession.instance().createNewTestGroupDescription(decription);
        block.execute();
        SpectreSession.instance().blockDone();
    }

    public static void before(Block block) {
        SpectreSession.instance().addBefore(block);
    }

    public static void beforeEach(Block block) {
        SpectreSession.instance().addBeforeEach(block);
    }

    public static void it(String decription, Block block) {
        SpectreSession.instance().createNewTestDescription(decription, block);
    }
}
