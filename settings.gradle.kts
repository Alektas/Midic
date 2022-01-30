rootProject.name = "Midic"

include(
    "depconstraints",
    ":app",
    ":core:theme",
    ":core:ui-components",
    ":core:ui-components:bottom-sheet",
    ":core:utils:arch-base",
    ":common",
    ":features:term-search",
)
