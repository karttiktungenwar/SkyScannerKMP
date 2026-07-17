package com.app.skyscanner.core.common.data.models

import com.app.skyscanner.core.common.domain.enitity.Destination
import com.app.skyscanner.core.common.domain.enitity.ItineraryActivity
import com.app.skyscanner.core.common.domain.enitity.ItineraryDay
import com.app.skyscanner.core.common.domain.enitity.Tour
import kotlinx.datetime.LocalDate

object ModelData {
    val categories: List<String> = listOf(
        "Asia",
        "Europe",
        "South America",
        "North America",
    )

    val destinations: List<Destination> = listOf(
        // ASIA
        Destination(
            id = "kyoto",
            city = "Kyoto",
            country = "Japan",
            imageUrl = "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?w=900&q=80",
            rating = 4.9,
            reviewCount = 210,
            continent = "Asia",
            description = "Kyoto, once the imperial capital of Japan, is home to thousands of temples, classical gardens, and traditional wooden houses.",
        ),
        Destination(
            id = "bali",
            city = "Ubud",
            country = "Indonesia",
            imageUrl = "https://images.unsplash.com/photo-1537996194471-e657df975ab4?w=900&q=80",
            rating = 4.8,
            reviewCount = 185,
            continent = "Asia",
            description = "Ubud is the cultural heart of Bali, surrounded by lush rainforests, iconic terraced rice paddies, and historic Hindu temples.",
        ),
        Destination(
            id = "seoul",
            city = "Seoul",
            country = "South Korea",
            imageUrl = "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?w=900&q=80",
            rating = 4.7,
            reviewCount = 310,
            continent = "Asia",
            description = "Seoul, the capital of South Korea, is a vibrant metropolis known for its modern skyscrapers, ancient palaces, and bustling street markets.",
        ),
        Destination(
            id = "bangkok",
            city = "Bangkok",
            country = "Thailand",
            imageUrl = "https://images.unsplash.com/photo-1517760444937-f6397edcbbcd?w=900&q=80",
            rating = 4.6,
            reviewCount = 280,
            continent = "Asia",
            description = "Bangkok, Thailand's capital, is a large city known for its ornate shrines, vibrant street life, and bustling markets.",
        ),
        Destination(
            id = "singapore",
            city = "Singapore",
            country = "Singapore",
            imageUrl = "https://images.unsplash.com/photo-1541055967364-7c53e6887c54?w=900&q=80",
            rating = 4.8,
            reviewCount = 240,
            continent = "Asia",
            description = "Singapore is a global financial center and a city-state known for its cleanliness, modern architecture, and diverse cultural heritage.",
        ),

        // EUROPE
        Destination(
            id = "paris",
            city = "Paris",
            country = "France",
            imageUrl = "https://images.unsplash.com/photo-1502602898536-47ad22581b52?w=900&q=80",
            rating = 4.9,
            reviewCount = 450,
            continent = "Europe",
            description = "Paris, the capital of France, is known for its art, fashion, gastronomy, and culture. It is home to iconic landmarks like the Eiffel Tower and Louvre Museum.",
        ),
        Destination(
            id = "rome",
            city = "Rome",
            country = "Italy",
            imageUrl = "https://images.unsplash.com/photo-1531168556467-80aace4d0144?w=900&q=80",
            rating = 4.8,
            reviewCount = 380,
            continent = "Europe",
            description = "Rome, the capital of Italy, is a sprawling, cosmopolitan city with nearly 3,000 years of globally influential art, architecture, and culture on display.",
        ),
        Destination(
            id = "barcelona",
            city = "Barcelona",
            country = "Spain",
            imageUrl = "https://images.unsplash.com/photo-1539037116277-4db20889f2d4?w=900&q=80",
            rating = 4.7,
            reviewCount = 320,
            continent = "Europe",
            description = "Barcelona, the capital of Catalonia, is known for its unique architecture, vibrant culture, and Mediterranean beaches.",
        ),
        Destination(
            id = "prague",
            city = "Prague",
            country = "Czech Republic",
            imageUrl = "https://images.unsplash.com/photo-1513778150807-0bf745834167?w=900&q=80",
            rating = 4.7,
            reviewCount = 290,
            continent = "Europe",
            description = "Prague, the capital of the Czech Republic, is known for its Old Town Square, the historic Charles Bridge, and Prague Castle.",
        ),
        Destination(
            id = "amsterdam",
            city = "Amsterdam",
            country = "Netherlands",
            imageUrl = "https://images.unsplash.com/photo-1534353436294-0dbd4bdac845?w=900&q=80",
            rating = 4.6,
            reviewCount = 260,
            continent = "Europe",
            description = "Amsterdam, the capital of the Netherlands, is famous for its artistic heritage, elaborate canal system, and narrow houses.",
        ),

        // SOUTH AMERICA
        Destination(
            id = "rio",
            city = "Rio de Janeiro",
            country = "Brazil",
            imageUrl = "https://images.unsplash.com/photo-1483729558449-99ef09a8c325?w=900&q=80",
            rating = 5.0,
            reviewCount = 143,
            continent = "South America",
            description = "Rio de Janeiro, often simply called Rio, is one of Brazil's most iconic cities, renowned for its dramatic mountains, golden beaches, and vibrant culture.",
        ),
        Destination(
            id = "patagonia",
            city = "Patagonia",
            country = "Argentina",
            imageUrl = "https://images.unsplash.com/photo-1531065208531-4036c0dba3ca?w=900&q=80",
            rating = 4.8,
            reviewCount = 98,
            continent = "South America",
            description = "Patagonia is a vast, sparsely populated region shared by Argentina and Chile, famous for its glaciers, jagged peaks, and endless windswept steppe.",
        ),
        Destination(
            id = "machu-picchu",
            city = "Machu Picchu",
            country = "Peru",
            imageUrl = "https://images.unsplash.com/photo-1533474215517-112b22298142?w=900&q=80",
            rating = 4.9,
            reviewCount = 210,
            continent = "South America",
            description = "Machu Picchu is a 15th-century Inca citadel located in the Eastern Cordillera of southern Peru on a mountain ridge.",
        ),
        Destination(
            id = "buenos-aires",
            city = "Buenos Aires",
            country = "Argentina",
            imageUrl = "https://images.unsplash.com/photo-1570197788417-0e82375c9371?w=900&q=80",
            rating = 4.7,
            reviewCount = 175,
            continent = "South America",
            description = "Buenos Aires is the capital and largest city of Argentina, known for its European-style architecture and rich cultural life.",
        ),
        Destination(
            id = "cartagena",
            city = "Cartagena",
            country = "Colombia",
            imageUrl = "https://images.unsplash.com/photo-1547189089-9349f491b488?w=900&q=80",
            rating = 4.8,
            reviewCount = 150,
            continent = "South America",
            description = "Cartagena is a port city on Colombia’s Caribbean coast, known for its colorful colonial architecture and vibrant culture.",
        ),

        // NORTH AMERICA
        Destination(
            id = "new-york",
            city = "New York City",
            country = "USA",
            imageUrl = "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9?w=900&q=80",
            rating = 4.8,
            reviewCount = 520,
            continent = "North America",
            description = "New York City is a global hub of finance, culture, and entertainment, known for its iconic skyline, Broadway shows, and diverse neighborhoods.",
        ),
        Destination(
            id = "san-francisco",
            city = "San Francisco",
            country = "USA",
            imageUrl = "https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=900&q=80",
            rating = 4.7,
            reviewCount = 380,
            continent = "North America",
            description = "San Francisco is known for its steep rolling hills, the Golden Gate Bridge, and its vibrant tech industry.",
        ),
        Destination(
            id = "toronto",
            city = "Toronto",
            country = "Canada",
            imageUrl = "https://images.unsplash.com/photo-1533928298208-27ff66555d8d?w=900&q=80",
            rating = 4.6,
            reviewCount = 240,
            continent = "North America",
            description = "Toronto, the capital of Ontario, is Canada's largest city and a major cultural and economic center.",
        ),
        Destination(
            id = "mexico-city",
            city = "Mexico City",
            country = "Mexico",
            imageUrl = "https://images.unsplash.com/photo-1544145945-f90425340c7e?w=900&q=80",
            rating = 4.7,
            reviewCount = 290,
            continent = "North America",
            description = "Mexico City is the capital of Mexico and one of the most important cultural and financial centers in the Americas.",
        ),
        Destination(
            id = "cancun",
            city = "Cancun",
            country = "Mexico",
            imageUrl = "https://images.unsplash.com/photo-1559827260-dc66d52bef19?w=900&q=80",
            rating = 4.5,
            reviewCount = 220,
            continent = "North America",
            description = "Cancun is a coastal city in Mexico known for its beautiful beaches, turquoise waters, and vibrant nightlife.",
        ),
    )

    val toursForRio: List<Tour> = listOf(
        Tour(
            id = "iconic-brazil",
            title = "Iconic Brazil",
            days = 8,
            priceFrom = 659,
            rating = 4.6,
            reviewCount = 56,
            imageUrl = "https://images.unsplash.com/photo-1531065208531-4036c0dba3ca?w=900&q=80",
            startDate = LocalDate(2026, 10, 21),
            endDate = LocalDate(2026, 11, 1),
            itinerary = listOf(
                ItineraryDay(
                    dayNumber = 1,
                    title = "Arrival to Rio de Janeiro",
                    imageUrl = "https://images.unsplash.com/photo-1436491865332-7a61a109cc05?w=400&q=80",
                    activities = listOf(
                        ItineraryActivity(
                            "Morning",
                            "Arrive in Rio de Janeiro and transfer to hotel"
                        ),
                        ItineraryActivity("Afternoon", "Free time to relax or explore nearby area"),
                        ItineraryActivity(
                            "Evening",
                            "Welcome dinner at traditional Brazilian restaurant"
                        )
                    )
                ),
                ItineraryDay(
                    dayNumber = 2,
                    title = "Rio de Janeiro Highlights",
                    imageUrl = "https://images.unsplash.com/photo-1483729558449-99ef09a8c325?w=400&q=80",
                    activities = listOf(
                        ItineraryActivity("Morning", "Visit Christ the Redeemer at Corcovado"),
                        ItineraryActivity("Afternoon", "Cable car up Sugarloaf Mountain"),
                        ItineraryActivity("Evening", "Stroll along Copacabana beach at sunset")
                    )
                )
            )
        )
    )

    val iconicBrazil: Tour
        get() = toursForRio.first()
}