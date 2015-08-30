package org.eck.spec;

import org.junit.runner.Description;

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

    public static void before(String decription, Block block) {
        // TODO
    }

    public static void it(String decription, Block block) {
        Description description = SpectreSession.instance().createNewTestDescription(decription);
        SpectreTestEntry specEntry = new SpectreTestEntry(description, block);
        SpectreSession.instance().addSpec(specEntry);
    }

}
