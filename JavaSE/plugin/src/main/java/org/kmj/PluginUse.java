package org.kmj;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PluginUse implements IPluginPhase {
    @Override
    public void first() {
        log.info("first");
    }

    @Override
    public void second() {
        log.info("second");
    }
}
