rootProject.name = "Midic"

include(
    "depconstraints",
    ":app",
    ":core:theme",
    ":core:di",
    ":core:ui-components",
    ":core:ui-components:bottom-sheet",
    ":core:utils:arch-base",
    ":core:utils:compose",
    ":common",
    ":features:term-search",
    ":features:term-details",
)
