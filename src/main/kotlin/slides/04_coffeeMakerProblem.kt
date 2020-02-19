package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.ms
import kotlinx.css.properties.s
import kotlinx.css.properties.transition
import org.w3c.dom.HTMLElement
import org.w3c.dom.asList
import react.*
import react.dom.div
import react.dom.li
import react.dom.title
import styled.*
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.kpres.sourceCode
import ws.utils.*


private val infos = SlideInfos(
        stateCount = 5
)

private val CoffeeMakerProblemSlide by functionalComponent<SlideContentProps> { props ->
    slideTitle("The Coffee Maker problem")

    bulletList(props) {
        bulletCode(props.state, 1, "CoffeeMaker", "kotlin",
                """
                    class CoffeeMaker(
                            private val logger: CommonLogger,
                            private val heater: Heater,
                            private val pump: Pump,
                            private var ration: () -> Ration
                    )
                """.trimIndent()
        )
        bulletCode(props.state, 2, "ConsoleLogger", "kotlin",
                """
                    class ConsoleLogger: CommonLogger {
                        override fun log(msg: String) = println(msg)
                    } 
                """.trimIndent()
        )
        bulletCode(props.state, 3, "Heater", "kotlin",
                """
                    class ElectricHeater(private val log: CommonLogger) : Heater {
                        override fun on() { ... }
                        override fun off() { ... }
                        override val isHot: Boolean get() = ...
                    } 
                """.trimIndent()
        )
        bulletCode(props.state, 4, "Pump", "kotlin",
                """
                    class Thermosiphon(
                            private val log: CommonLogger,
                            private val heater: Heater
                    ) : Pump {
                        override fun pumpWater() { ... }
                    }
                """.trimIndent()
        )
    }
}

fun PresentationBuilder.coffeeMakerProblem() = slide(infos) { child(CoffeeMakerProblemSlide, it) }


