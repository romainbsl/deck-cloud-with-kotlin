package ws.slides

import kotlinx.css.*
import react.RBuilder
import react.child
import react.dom.p
import react.functionalComponent
import styled.css
import styled.styledDiv
import ws.kpres.PresentationBuilder
import ws.kpres.SlideContentProps
import ws.kpres.SlideInfos
import ws.kpres.notes
import ws.utils.*


private val infos = SlideInfos(
        stateCount = 9,
        notes = notes("""
            [0,1]Línjection de dependances, est une des solution pour repondre au pattern d'inversion des controles.
            [2,3]Sans IoC, le code de votre application est couplé, nous devons savoir qu'elle implementation de nos interfaces ou classes abstraites nous devons appeler
            [4]et enfin, notre le flot des instruction doit être connu et maitrisé par l'application
            Tout ceci engendre également une forte contrainte pour la réalisation des tests.
            [5]Avec l'IoC notre code est découplé, donc isolé, et donc plus facile a tester
            [6,7,8]l'execution est dynamique, le framework qui a la responsabilité de gérer les dépendances sait ou et quand il doit instancier et appeler les objets de notre application.
            Il maitraise donc le flot des instructions a exécuter.
        """.trimIndent())
)

private val IoCSlide by functionalComponent<SlideContentProps> { props ->
    slideTitle(" IoC Pattern")

    bulletList(props = props) {
        styledDiv {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                    alignSelf = Align.center
                }

            fun RBuilder.versusBulletPoint(currentState: Int, stateRef: Int, value: String) =
                    bulletPoint(currentState, stateRef, value, ruleSet = versusBulletRule(4))

            styledDiv {
                css { margin(1.em) }
                versusBulletPoint(props.state, 1, "Strictly coupled")
                versusBulletPoint(props.state, 2, "Static declaration")
                versusBulletPoint(props.state, 3, "On site call...")
                versusBulletPoint(props.state, 4, "App responsibility")
            }

            styledDiv {
                css { margin(1.em) }
                versusBulletPoint(props.state, 5, "Decouple your code")
                versusBulletPoint(props.state, 6, "Dynamic execution")
                versusBulletPoint(props.state, 7, "Managed call!")
                versusBulletPoint(props.state, 8, "Framework responsibility")
            }
        }
    }
}

fun PresentationBuilder.inversionOfControl() = slide(infos) { child(IoCSlide, it) }


