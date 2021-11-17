package io.github.gaming32.sdl4j;

import io.github.gaming32.sdl4j.LowLevel.SDL2Library;
import io.github.gaming32.sdl4j.modules.DisplayModule;

public final class SDL4J {
    public static interface Module {
        public void init();
    }

    private static int wasInit;

    public static ImportSuccess init() {
        SDL2Library lib = LowLevel.getInstance();

        int i = 0, success = 0, fail = 0;

        final Module[] modules = new Module[] {
            DisplayModule.getInstance()
        };

        wasInit = lib.SDL_Init(SDL2Library.SDL_INIT_TIMER);

        for (i = 0; i < modules.length; i++) {
            try {
                modules[i].init();
                success++;
            } catch (Exception e) {
                fail++;
            }
        }

        wasInit = 1;
        return new ImportSuccess(success, fail);
    }
}
