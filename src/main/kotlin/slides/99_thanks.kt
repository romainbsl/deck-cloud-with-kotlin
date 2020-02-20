package ws.slides

import kotlinx.css.Color
import kotlinx.css.FontWeight
import kotlinx.css.color
import kotlinx.css.fontWeight
import react.child
import react.functionalComponent
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.blockEffectFrom
import ws.utils.getValue
import ws.utils.kotlinSourceCode
import ws.utils.slideTitle

fun PresentationBuilder.thanks() {
    slide {child(ThankYou, it)}
    slide { slideTitle("One more thing!") }
    slide(SlideInfos(2)) {child(OneMoreThing, it)}
}
private val ThankYou by functionalComponent<SlideContentProps> { props ->
    slideTitle("Thank you!")
}
private val OneMoreThing by functionalComponent<SlideContentProps> { props ->
    slideTitle("Win an IntelliJ IDEA licence!")

    kotlinSourceCode("""
        fun main() {
            val s: String? = null
            if (s?.isEmpty()) println("is empty")«correct-msg«
            Type mismatch: inferred type «correct-msg«is» Boolean? 
            but Boolean was expected...
            »if (s.isNullOrEmpty()) println("is null or empty")
        }
        
        // What will it display? Some possibilities:
        a) is empty is null or empty
        b) is null or empty
        c) prints nothing
        «correct«d) doesn’t compile»
        """.trimIndent()) {
        + "c-correct" {
            if (props.state == 1) {
                color = Color.green
                fontWeight = FontWeight.w500
            }
        }
        + "c-correct-msg" {
            blockEffectFrom(props.state, 1)
            if (props.state == 1) {
                color = Color.darkRed
                fontWeight = FontWeight.w500
            }
        }
    }
}