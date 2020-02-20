package ws.slides

import ws.kpres.PresentationBuilder
import ws.utils.slideTitle

fun PresentationBuilder.mvp() {
    slide { slideTitle("MVP pattern for Kotlin/Multiplatform") }
    slide { slideTitle("MVP pattern introduction?") }
    slide { slideTitle("Presenter / View interface") }
    slide { slideTitle("BrewPresenterImpl 1") }
    slide { slideTitle("ScreenLogger") }
    slide { slideTitle("BrewPresenterImpl 2") }
    slide { slideTitle("bindings") }
    slide { slideTitle("iOS") }
    slide { slideTitle("Android") }
    slide { slideTitle("Stupid Simple, Right?") }
}