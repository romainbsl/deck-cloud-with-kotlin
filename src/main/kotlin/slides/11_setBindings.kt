package ws.slides

import react.child
import react.functionalComponent
import ws.kpres.Fade
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.*

private val CoffeeVsTea by functionalComponent<SlideContentProps> { props ->
    slideTitle("Coffee vs Tea ration")

    kotlinSourceCode("""
    abstract class Ration(val logger: CommonLogger)«dot« { ... }»«ration-out« {
        abstract fun name(): String «companion«
        
        companion object {
            const val coffee = "coffee"
            const val tea = "tea"
        }»
    }»«coffee«
    class Coffee(logger: CommonLogger) : Ration(logger) {
        init {
            logger.log("<Creating CoffeeRation>")
        }
        override fun name(): String = coffee
    }»«tea«
    class Tea(logger: CommonLogger) : Ration(logger) {
        init {
            logger.log("<Creating TeaRation>")
        }
        override fun name(): String = tea
    }»
    """.trimIndent()) {
        val currentState = props.state
        +"c-companion" { blockEffect(currentState, 1..2) }
        +"c-coffee" { blockEffectFrom(currentState, 2) }
        +"c-dot" { lineEffectFrom(currentState, 2) }
        +"c-ration-out" { lineEffectTo(currentState, 2) }
        +"c-tea" { blockEffectFrom(currentState, 3) }
    }
}
// TODO coloration?
private val RationModule by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    «typealias«typealias RationEntry = Pair<String, () -> Ration>
    typealias RationEntries = Set<RationEntry>
    
    »«module«val rationModule = DI.Module("Ration Module") {«bind«
        bind() from setBinding<RationEntry>()»«inset«
    
        bind<RationEntry>().inSet() with provider { 
            Ration.coffee to { Coffee(instance()) } 
        }
        bind<RationEntry>().inSet() with provider { 
            Ration.tea to { Tea(instance()) } 
        }
    »}»
    """.trimIndent()) {
        val currentState = props.state
        +"c-typealias" { blockEffectFrom(currentState, 1) }
        +"c-module" { blockEffectFrom(currentState, 2) }
        +"c-bind" { lineEffectFrom(currentState, 3) }
        +"c-inset" { lineEffectFrom(currentState, 4) }
    }
}

// TODO transition from singleton to multiton
private val Multiton by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    «import«import(rationModule)
    
    »«maker«bind<CoffeeMaker>() with multiton { ration: () -> Ration -> 
        CoffeeMaker(instance(), instance(), instance(), ration) 
    }»
    """.trimIndent()) {
        val currentState = props.state
        +"c-import" { blockEffectFrom(currentState, 1) }
        +"c-maker" { blockEffectFrom(currentState, 2) }
    }
}
// TODO From singleton (1) to multiton (2) with parameter (3)
private val Retrieving1 by functionalComponent<SlideContentProps> { props ->
    kotlinSourceCode("""
    «ration«val rationMap by instance<RationEntries>().toMap()
    
    »«maker«val coffeeMaker: CoffeeMaker by instance«arg-out«()»»«arg-in«(
            arg = «arg-line-out«?»«arg-line-in«rationMap[Ration.coffee]»
    )»«brew«
    
    // ...
    coffeeMaker.brew()
    »""".trimIndent()) {
        val currentState = props.state
        +"c-maker" { lineEffectFrom(currentState, 1) }
        +"c-arg-in" { blockEffectFrom(currentState, 2) }
        +"c-arg-out" { lineEffectTo(currentState, 2) }
        +"c-ration" { blockEffectFrom(currentState, 3) }
        +"c-arg-line-in" { lineEffectFrom(currentState, 4) }
        +"c-arg-line-out" {lineEffectTo(currentState, 4)}
        +"c-brew" { blockEffectFrom(currentState, 5) }
    }
}

fun PresentationBuilder.setBindings() {
    slide {
        slideTitle("[Advanced Mode]")
        slideTitle("_ set bindings _")
    }
    slide {
        slideTitle("I can see you...")
        slideTitle("Coffee haters!")
    }
    slide(SlideInfos(4)) { child(CoffeeVsTea, it) }
    slide(SlideInfos(5)) { child(RationModule, it) }
    slide(SlideInfos(3)) { child(Multiton, it) }
    slide(SlideInfos(6)) { child(Retrieving1, it) }
}