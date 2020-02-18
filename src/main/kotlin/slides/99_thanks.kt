package ws.slides

import react.dom.h1
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos


private val infos = SlideInfos(
)

fun PresentationBuilder.thanks() = slide(infos) { props ->
    h1 { +"Thank you!" }
}
