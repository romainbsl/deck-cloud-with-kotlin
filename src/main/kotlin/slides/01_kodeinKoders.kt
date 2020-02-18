package ws.slides

import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.backgroundImage
import ws.comp.logo
import ws.kpres.Fade
import ws.kpres.Flip
import ws.kpres.PresentationBuilder
import ws.kpres.SlideInfos
import ws.utils.Background


private val infos = SlideInfos(
        containerStyle = {
            ".inner-container" {
                backgroundImage = Background.kodein
            }
        }
)

fun PresentationBuilder.kodeinKoders() = slide(infos) {
    logo(division = "Koders", href = "https://kodein.net", zoom = 1.0 ) {
        +"painless technology"
    }
}

