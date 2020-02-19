package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.*
import ws.kpres.*
import ws.utils.*

private val Slide1 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    class CoffeeMaker(«main« ... )»«out«
            private val logger: CommonLogger,
            private val heater: Heater,
            private val pump: Pump,
            private var ration: () -> Ration
    ) {
        fun brew() {
            heater.on()
    
            val ration = ration()
            pump.pumpWater()
            logger.log("[_]P /$/{ration.name()} [_]P")
    
            heater.off()
        }
    }»
    «main«
    fun main() {
        val coffeeMaker = 
                CoffeeMaker(${compileError(1)})
        
        coffeeMaker.brew()
    }»
    """.trimIndent())
    {
        val currentState = props.state
        +"c-out" { lineEffect(currentState, 0..0) }
        +"c-main" { lineEffectFrom(currentState, 1) }
        +"c-err1" { orangeHighlight(currentState, 2) }
    }
}
private val Slide2 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    «out«class CoffeeMaker( ... )
    
    »fun main() {
        val coffeeMaker = 
                CoffeeMaker(ConsoleLogger(), 
                            ElectricHeater(${compileError(1)}), 
                            Thermosiphon(${compileError(1)}), 
                            { Coffee(${compileError(1)}) })
                                        
        coffeeMaker.brew()
    }
    """.trimIndent())
    {
        +"c-out" { lineEffect(props.state, 0..0) }
        +"c-err1" { orangeHighlight(props.state, 1) }
    }
}
private val Slide3 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    fun main() {
        val logger = ConsoleLogger()
        val heater = ElectricHeater(${compileError(2)})
        val pump = Thermosiphon(${compileError(2)})
        val coffee = { Coffee(${compileError(2)})}
        
        val coffeeMaker = 
                CoffeeMaker(«out«ConsoleLogger()»«in«logger,heater»,
                            «out«ElectricHeater(${compileError(1)}),»«in«pump, coffee») 
                            «out«Thermosiphon(${compileError(1)}),»
                            «out«{ Coffee(${compileError(1)}) })»
                            
        coffeeMaker.brew()
    }
    """.trimIndent())
    {
        +"c-err1" { orangeHighlight(props.state, 0) }
        +"c-out" { lineEffectTo(props.state, 1) }
        +"c-in" {
            lineEffectFrom(props.state, 1)
            color = Palette.orange.color
        }
        +"c-err2" { orangeHighlight(props.state, 1) }
    }
}
private val Slide4 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    fun main() {
        val logger = ConsoleLogger()
        val heater = ElectricHeater(«logger«logger»)
        val pump = Thermosiphon(«logger«logger, heater»)
        val coffee = { Coffee(«logger«logger») }
    
        val coffeeMaker = 
                CoffeeMaker(logger, heater, 
                            pump, coffee)
        
        «brew«// MAKE ME MY DAMN COFFEE!!!»
        coffeeMaker.brew()
    }
    """.trimIndent()) {
        +"c-logger" {
            color = if (props.state == 0) Palette.orange.color else Color.black
        }
        +"c-brew" { lineEffectFrom(props.state, 1) }
    }
}
private val Slide5 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    «bind«@Injectable »class CoffeeMaker(
            «njct«@Inject »private val logger: CommonLogger,
            «njct«@Inject »private val heater: Heater,
            «njct«@Inject »private val pump: Pump,
            «njct«@Inject »private var ration: () -> Ration
    )
    
    «bind«@Injectable »class ConsoleLogger: CommonLogger { ... }
    
    «bind«@Injectable »class ElectricHeater(
            «njct«@Inject »private val log: CommonLogger
    ) : Heater { ... }
    
    «bind«@Injectable »class Thermosiphon(
            «njct«@Inject »private val log: CommonLogger,
            «njct«@Inject »private val heater: Heater
    ) : Pump { ... }
    """.trimIndent()) {
        + "c-bind" { lineEffectFrom(props.state, 1) }
        + "c-njct" { lineEffectFrom(props.state, 2) }
    }
}
private val Slide6 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    fun main() {
        @Inject val coffeeMaker: CoffeeMaker
        
        // Ah! That smells good!
        coffeeMaker.brew()
    }
    """.trimIndent())
}

fun PresentationBuilder.coupledCode() {
    fun fastTransition(i: Int) = SlideInfos(
            stateCount = i,
            inTransitionDuration = 0
    )

    titleSlide("Coupled architecture")
    slide(fastTransition(3)) { child(Slide1, it) }
    slide(fastTransition(2)) { child(Slide2, it) }
    slide(fastTransition(2)) { child(Slide3, it) }
    slide(fastTransition(2)) { child(Slide4, it) }
    slide(SlideInfos(3)) { child(Slide5, it) }
    slide { child(Slide6, it) }
}

private fun RBuilder.kotlinSourceCode(code: String, style: RuleSet = {}) = sourceCode("kotlin", code) {
    "code" {
        overflow = Overflow.hidden
    }
    "span.c-marker" {
        opacity = 1.0
        transition(::opacity, 300.ms)
        transition(::fontSize, 300.ms)
        transition(::lineHeight, 300.ms)
        transition(::color, 300.ms)
        style()
    }
}