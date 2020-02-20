package ws.slides

import kotlinx.css.*
import kotlinx.css.properties.ms
import kotlinx.css.properties.transition
import react.*
import styled.*
import ws.kpres.*
import ws.utils.*


private val WhyMppInfos = SlideInfos(
        stateCount = 2,
        outTransitions = Fade
)
private val WhyMppSlide by functionalComponent<SlideContentProps> { props ->
    styledH1 {
        css {
            fontWeight = FontWeight.normal
            if (props.state > 0) {
                transition(::fontWeight, 300.ms)
                fontWeight = FontWeight.w200
                transition(::fontSize, 300.ms)
                fontSize = 1.5.em
                transition(::opacity, 300.ms)
                opacity = 0.8
            }
            margin(0.em, 0.em, 0.3.em, 0.em)
        }
        +" Dependency Injection for"
    }
    styledH1 {
        css {
            fontWeight = FontWeight.normal
            margin(0.em, 0.em, 0.3.em, 0.em)
        }
        +" Kotlin/Multiplatform"
    }

    styledDiv {
        css {
            display = Display.flex
            alignItems = Align.center
            height = 100.pct
        }

        styledImg(src = "images/thinking-face.png") {
            css {
                height = 5.em
                transition(::opacity, 300.ms)
                if (props.state != 1) opacity = 0
            }
        }
    }
}
private val MppArgsInfos = SlideInfos(
        stateCount = 9,
        inTransitions = Fade,
        inTransitionDuration = 0
)
private val MppArgsSlide by functionalComponent<SlideContentProps> { props ->
    styledH1 {
        css {
            fontWeight = FontWeight.w200
            fontSize = 1.5.em
            opacity = 0.8
            margin(0.em, 0.em, 0.3.em, 0.em)
        }
        +" Dependency Injection for"
    }
    styledH1 {
        css {
            fontWeight = FontWeight.normal
            margin(0.em, 0.em, 0.3.em, 0.em)
        }
        +" Kotlin/Multiplatform"
    }

    bulletList(props) {
        styledDiv {
            css {
                display = Display.flex
                flexDirection = FlexDirection.row
                alignSelf = Align.center
            }

            styledDiv {
                val currentState = props.state
                bulletPoint(currentState, 1, "Args 1")
                bulletPoint(currentState, 2, "Args 2")
                bulletPoint(currentState, 3, "Args 3")
                bulletPoint(currentState, 4, "Args 4")
            }
            styledDiv {
                val currentState = props.state
                bulletPoint(currentState, 5, "Args 5")
                bulletPoint(currentState, 6, "Args 6")
                bulletPoint(currentState, 7, "Args 7")
                bulletPoint(currentState, 8, "Args 8")
            }
        }
    }
}
private val MppConfigurationInfos = SlideInfos(
        stateCount = 8
)
private val MppConfigurationSlide by functionalComponent<SlideContentProps> { props ->
    slideTitle("KMP Configuration")

    bulletList(props) {
        bulletCode(props.state, 1, "Gradle Plugin", "kotlin",
                """
                plugins {
                  id("org.jetbrains.kotlin.multiplatform") version "1.3.61"
                }    
                """.trimIndent()
        )
        bulletCode(props.state, 2, "Targets", "kotlin",
                """
                kotlin {
                    jvm()             // JVM & Android
                    js { browser() }  // Browser JS
                    iosX64()          // iOS simulator
                    macosX64()        // Native host (linuxX64 / mingwX64) 
                    //...
                }
                """.trimIndent()
        )
        bulletCode(props.state, 3, "Common dependencies - Main", "kotlin",
                """
                kotlin {
                    val commonMain by getting {
                        dependencies {
                            implementation(kotlin("stdlib-common"))
                            implementation("org.kodein.di:kodein-di:7.0.0")
                        }
                    }
                }
                """.trimIndent()
        )
        bulletCode(props.state, 4, "Common dependencies - Test", "kotlin",
                """
                kotlin {
                    //...
                    val commonTest by getting {
                        dependencies {
                            implementation(kotlin("test-common"))
                            implementation(kotlin("test-annotations-common"))
                        }
                    }
                }
                """.trimIndent()
        )
        bulletCode(props.state, 5, "Platform specific dependencies - Main", "kotlin",
                """
                val jvmMain by getting {
                    dependencies {
                        implementation(kotlin("stdlib"))
                    }
                }
                """.trimIndent()
        )
        bulletCode(props.state, 6, "Platform specific dependencies - Test", "kotlin",
                """
                val jvmTest by getting {
                    dependencies {
                        implementation(kotlin("test"))
                        implementation(kotlin("test-junit"))
                    }
                }
                """.trimIndent()
        )
    }
}
private val MppHierarchyInfos = SlideInfos(
        stateCount = 2
)
private val MppHierarchySlide by functionalComponent<SlideContentProps> { props ->
    slideTitle("KMP Hierarchy")

    styledDiv {
        css {
            display = Display.flex
            alignItems = Align.center
            height = 100.pct
        }

        styledImg(src = "images/mpp_hierarchy.png") {
            css {
                borderRadius = 1.em
                height = 14.em
                transition(::opacity, 300.ms)
                if (props.state != 1) opacity = 0
            }
        }
    }
}

fun PresentationBuilder.mpp() {
    slide(WhyMppInfos) { child(WhyMppSlide, it) }
    slide(MppArgsInfos) { child(MppArgsSlide, it) }
    slide(MppConfigurationInfos) { child(MppConfigurationSlide, it) }
    slide(MppHierarchyInfos) { child(MppHierarchySlide, it) }
}


