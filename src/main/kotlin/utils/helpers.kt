package ws.utils

import kotlinx.css.FontWeight
import kotlinx.css.em
import kotlinx.css.fontWeight
import kotlinx.css.margin
import react.RBuilder
import styled.css
import styled.styledH1

fun RBuilder.slideTitle(title: String) {
    styledH1 {
        css {
            fontWeight = FontWeight.normal
            margin(0.em, 0.em, 0.3.em, 0.em)
        }
        +" $title"
    }
}