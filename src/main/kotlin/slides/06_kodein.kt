package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.child
import react.functionalComponent
import styled.*
import ws.comp.logo
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.utils.*

private val infos = SlideInfos(
        containerStyle = {
            ".inner-container" {
                backgroundColor = Color("#EB5A44")
                transition(::background, 1000.ms)
            }
        }
)

private fun PresentationBuilder.whatIsKodeinDI() = slide(infos) {
    logo(division = {
        styledSpan {
            css {
                verticalAlign = VerticalAlign.middle
            }
            +"DI"
        }
    }, zoom = 0.8) {
        styledP {
            css {
                padding(0.em)
                margin(0.em)
            }
            +"painless "
            styledImg(src = "images/kotlin-white.svg") {
                css { height = 0.65.em }
            }
            +" "
            styledSpan {
                css {
                    transition(::fontSize, 500.ms)
                    verticalAlign = VerticalAlign.middle
                }
                +"Dependency Injection"
            }
        }
    }
}

private val CustomFeature by functionalComponent<SlideContentProps> { props ->
    titledContent("Custom feature") {
        kotlinSourceCode("""
            fun Application.diModule() {    
                install(DIFeature) {
                    bind() from singleton { UserService() }    
                    bind() from singleton { BasicItemService() }    
                }
            }
        """.trimIndent())
    }
}
private val ClosestPattern by functionalComponent<SlideContentProps> { props ->
    titledContent("Closest DI pattern") {
        kotlinSourceCode("""
        fun Application.module() {    
            install(DIFeature) {        
                bind<ItemService>() with singleton { BasicItemService() }    
            }    
            
            routing {        
                get("/todolist") {
                    val itemService: ItemService by di().instance()
                    // logic here        
                }
            }
        }
        """.trimIndent())
    }
}
private val KtorScopes by functionalComponent<SlideContentProps> { props ->
    titledContent("Ktor Scopes") {
        bulletList(props) {
            bulletCode(props.state, 1, "Session Scope", "kotlin",
                """
                val itemService: SessionItemService 
                        by di().on(session).instance()
                """.trimIndent()
            )
            bulletCode(props.state, 2, "Request Scope", "kotlin",
                """
                val itemService: SessionItemService 
                        by di().on(applicationCall).instance()
                """.trimIndent()
            )
        }
    }
}

private val DIController by functionalComponent<SlideContentProps> { props ->
    titledContent("DI Aware Controller") {
        kotlinSourceCode("""
        class MyController(application: Application) : DIController {
            override val di by di { application }
            private val repository: DataRepository by instance("dao")    
            
            override fun Routing.installRoutes() {        
                get("/version") {            
                    val version: String by instance("version")            
                    call.respondText(version)
                }    
            }
        }
        """.trimIndent())
    }
}

fun PresentationBuilder.di() {
    whatIsKodeinDI()
    slide { slideTitle("DI extensions for Ktor") }
    slide { child(CustomFeature, it) }
    slide { child(ClosestPattern, it) }
    slide(SlideInfos(4)) { child(KtorScopes, it) }
    slide {
        slideTitle("Using DIController,")
        slideTitle(" a MVC-like architecture")
    }
    slide { child(DIController, it) }
}
