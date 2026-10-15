package com.example.model

data class ServiceItem(
    val title: String,
    val description: String,
    val badge: String,
    val features: List<String>
)

data class TestimonialItem(
    val name: String,
    val role: String,
    val comment: String,
    val rating: Int = 5,
    val location: String
)

data class FaqItem(
    val question: String,
    val answer: String
)

object BioSiteData {
    const val WHATSAPP_URL = "https://wa.link/0d3d10"
    const val INSTAGRAM_URL = "https://instagram.com/bruno_santospaz?mdxt=MWpqMjVuY2V4YWNhdA%3D%3D"
    const val INSTAGRAM_HANDLE = "@bruno_santospaz"
    const val PHONE_DISPLAY = "(WhatsApp Oficial)"
    const val OWNER_NAME = "Bruno Santos Paz"

    val services = listOf(
        ServiceItem(
            title = "Instalação de Portas de Madeira & Frisadas",
            description = "Instalação completa de portas de giro, de correr e pivotantes com alinhamento a laser e folgas milimétricas uniformes.",
            badge = "Alta Precisão",
            features = listOf(
                "Portas de Giro, Pivotantes e De Correr",
                "Alinhamento a laser sem folgas irregulares",
                "Instalação de dobradiças embutidas e fechaduras"
            )
        ),
        ServiceItem(
            title = "Rodapés de Poliestireno (Santa Luzia) e MDF",
            description = "Cortes em 45 graus (meia-esquadria) computadorizados com acabamento sem frestas e calafetação premium.",
            badge = "Acabamento Fino",
            features = listOf(
                "Especialista em Santa Luzia e Poliestireno",
                "Quinas e cantos em 45° perfeitos",
                "Fixação invisível e vedação impermeável"
            )
        ),
        ServiceItem(
            title = "Fechaduras Digitais, Biométricas & Magnéticas",
            description = "Furação e usinagem de alta precisão em portas nobres sem danificar a laca ou folha de madeira.",
            badge = "Tecnologia",
            features = listOf(
                "Fechaduras biométricas e por senha",
                "Fechos magnéticos silenciosos",
                "Usinagem limpa com gabarito profissional"
            )
        ),
        ServiceItem(
            title = "Guarnições, Alizares & Ajustes Finos",
            description = "Acabamento de portais, instalação de vistas e reparo de portas que raspam no piso.",
            badge = "Perfeição",
            features = listOf(
                "Encaixes sem emendas aparentes",
                "Ajuste e plainamento com proteção de borda",
                "Calafetação final na cor exata da pintura"
            )
        )
    )

    val testimonials = listOf(
        TestimonialItem(
            name = "Juliana Meneses",
            role = "Arquiteta de Interiores",
            comment = "O trabalho do Bruno na instalação dos rodapés de 15cm Santa Luzia e portas laqueadas foi impecável. Cortes de 45° perfeitos, sem sujeira no apartamento e pontualidade britânica.",
            rating = 5,
            location = "Obra Residencial Alphaville"
        ),
        TestimonialItem(
            name = "Rodrigo Camargo",
            role = "Proprietário",
            comment = "Já tive experiências ruins com outros instaladores, mas a REHM superou tudo. As 8 portas do meu apartamento fecham com um toque suave e as fechaduras magnéticas ficaram perfeitas.",
            rating = 5,
            location = "Apartamento Alto Padrão"
        ),
        TestimonialItem(
            name = "Carla Silveira",
            role = "Designer de Ambientes",
            comment = "Profissional extremamente educado, caprichoso e que cuida da obra como se fosse a dele. Aspiração de pó constante e acabamento de cinema.",
            rating = 5,
            location = "Condomínio Fechado"
        )
    )

    val faqs = listOf(
        FaqItem(
            question = "Como é feito o cálculo do orçamento?",
            answer = "Basta informar a quantidade de portas, metros lineares de rodapés e localização pelo nosso WhatsApp. Se tiver a planta ou fotos, o orçamento sai ainda mais rápido!"
        ),
        FaqItem(
            question = "Vocês fornecem o material ou apenas a mão de obra?",
            answer = "Focamos na mão de obra especializada de alta precisão, mas prestamos total consultoria sobre as melhores marcas (como Santa Luzia, Pormade) e calculamos a metragem exata para você não desperdiçar material."
        ),
        FaqItem(
            question = "Quanto tempo dura a instalação de uma porta?",
            answer = "Com nossas ferramentas de gabarito e corte a laser, uma porta completa com alizares e fechadura é finalizada com padrão de excelência em poucas horas, sem sujeira pela casa."
        ),
        FaqItem(
            question = "Como agendar um atendimento?",
            answer = "Clique diretamente no botão do WhatsApp nesta página para falar com o Bruno Santos e verificar a disponibilidade da agenda para a sua data."
        )
    )
}
