package ws.utils

import kotlinx.css.*
import react.RBuilder
import styled.css
import styled.styledH1
import ws.kpres.PresentationBuilder

fun RBuilder.slideTitle(title: String) {
    styledH1 {
        css {
            fontWeight = FontWeight.normal
            margin(0.em, 0.em, 0.3.em, 0.em)
        }
        +" $title"
    }
}

fun PresentationBuilder.titleSlide(title: String) = slide {
    styledH1 {
        css {
            fontWeight = FontWeight.normal
            margin(LinearDimension.auto)
        }
        +" $title"
    }
}
