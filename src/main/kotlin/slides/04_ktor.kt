package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.RBuilder
import react.child
import react.functionalComponent
import styled.*
import ws.kpres.Fade
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.*

private val WhatIsKtor by functionalComponent<SlideContentProps> { props ->
    titledContent("What is Ktor?") {
        bulletList(props, style = {
            "li" {
                "span" { margin(1.em) }
            }
        }) {
            val currentState = props.state
            bulletPoint(currentState, 1, "DSL Server / Client")
            bulletPoint(currentState, 2, "Multiplatform")
            bulletPoint(currentState, 3, "Asynchronous")
            bulletPoint(currentState, 4, "Pure Kotlin")
        }
    }
}
private val WhatIsKtorFor by functionalComponent<SlideContentProps> { props ->
    titledContent("What is Ktor for?") {
        bulletList(props, style = {
            "li" {
                "span" { margin(1.em) }
            }
        }) {
            val currentState = props.state
            bulletPoint(currentState, 1, "Building client connections")
            bulletPoint(currentState, 2, "Building server applications")
            bulletPoint(currentState, 3, "HTTP APIs", 2)
            bulletPoint(currentState, 4, "REST systems", 2)
            bulletPoint(currentState, 5, "(Web)Sockets", 2)
        }
    }
}
private val KtorExample by functionalComponent<SlideContentProps> { props ->
    ktorBackend {
        kotlinSourceCode("""
            «main«fun main() {«server«
                val server = embeddedServer(Netty, port = 8080) {«routing«
                    routing {«get«
                        get("/") {
                            call.respondText("Hello World!", 
                                            ContentType.Text.Plain) 
                        }
                    »} 
                »}»«start«
                server.start(wait = true) 
            »}»
            """.trimIndent()) {
            val currentState = props.state
            +"c-main" { blockEffectFrom(currentState, 1) }
            +"c-server" { blockEffectFrom(currentState, 2) }
            +"c-routing" { blockEffectFrom(currentState, 3) }
            +"c-get" { blockEffectFrom(currentState, 4) }
            +"c-start" { blockEffectFrom(currentState, 5) }
        }
    }
}

private fun RBuilder.ktorBackend(body: RBuilder.() -> Unit) {
    styledDiv {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
            alignItems = Align.center
            height = 100.pct
            width = 100.pct
        }

        styledSpan {
            css {
                height = 10.pct
                width = 100.pct
                fontSize = 2.0.em
            }
            +"Ktor"
            styledSpan {
                css {
                    fontSize = 0.8.em
                }
                +" on the backend"
            }
        }
        styledDiv {
            css {
                height = 90.pct
                width = 100.pct
                paddingTop = 1.em
                display = Display.flex
                flexDirection = FlexDirection.column
                alignContent = Align.center
                alignItems = Align.center
            }
            body()
        }
    }
}
private fun PresentationBuilder.ktorBackendArch(step: Int) {
    slide(SlideInfos(
            inTransitions = Fade,
            outTransitions = Fade,
            inTransitionDuration = 0
    )) {
        ktorBackend {
            styledImg(src = "images/ktor-$step.png") {
                css {
                    height = 14.em
                    transition(::opacity, 300.ms)
                }
            }
        }
    }
}

fun PresentationBuilder.ktor() {
    slide(SlideInfos(5)) { child(WhatIsKtor, it) }
    slide(SlideInfos(6)) { child(WhatIsKtorFor, it) }
    slide(SlideInfos(6)) { child(KtorExample, it) }
    for (i in 1..14) {
        ktorBackendArch(i)
    }
}

