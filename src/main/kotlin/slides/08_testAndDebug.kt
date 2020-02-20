package ws.slides

import react.child
import react.functionalComponent
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.utils.getValue
import ws.utils.slideTitle

private val CommonTestTitleSlide by functionalComponent<SlideContentProps> {
    slideTitle("Common Test")
}

fun PresentationBuilder.commonTest() {
    slide { child(CommonTestTitleSlide, it) }
}

