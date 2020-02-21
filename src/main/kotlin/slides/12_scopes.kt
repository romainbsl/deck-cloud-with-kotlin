package ws.slides

import ws.kpres.PresentationBuilder
import ws.utils.slideTitle

fun PresentationBuilder.scopes() {
    slide {
        slideTitle("[Advanced Mode]")
        slideTitle("_ scopes _")
    }
    slide { slideTitle("RationScope?") }
    slide { slideTitle("CoffeeMaker singleton on RationScope") }
    slide { slideTitle("Ration bindings") }
    slide { slideTitle("Retrieving...") }
    slide { slideTitle("Brew me that #whatever") }
}