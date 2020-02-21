package ws.slides

import ws.kpres.PresentationBuilder
import ws.utils.slideTitle

fun PresentationBuilder.setBindings() {
    slide {
        slideTitle("[Advanced Mode]")
        slideTitle("_ set bindings _")
    }
    slide {
        slideTitle("I can see you...")
        slideTitle("Coffee haters!")
    }
    slide { slideTitle("Coffee vs Tea ration") }
    slide { slideTitle("Pair<String, () -> Ration>") }
    slide { slideTitle("Retrieving...") }
    slide { slideTitle("Multiton usage!") }
    slide { slideTitle("Lets brew #whatever") }
}