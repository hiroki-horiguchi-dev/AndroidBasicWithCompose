fun main() {
    val events = mutableListOf(
        Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0),
        Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15),
        Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30),
        Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60),
        Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10),
        Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)
    )

    // filter 正解
    val eventsWithinOneHour = events.filter { it.durationInMinutes < 60 }
    // println("短いイベントが ${eventsWithinOneHour.size}件あります")

    // groupBy returns Map<Daypart, List<Event>>
    val eventByDaypart = events.groupBy { it.daypart }
    // 惜しい
    // val morningEvent = eventByDaypart[Daypart.MORNING] ?: null
    // val afternoonEvent = eventByDaypart[Daypart.AFTERNOON] ?: null
    // val eveningEvent = eventByDaypart[Daypart.EVENING] ?: null
    // println("Morning: ${morningEvent?.size} events")
    // println("Afternoon: ${afternoonEvent?.size} events")
    // println("Evening: ${eveningEvent?.size} events")
    eventByDaypart.forEach {
        (daypart, events) -> println("$daypart: ${events.size} events")
    }

    // タスク6
    // こういうコレクション操作方法もあるよってことかな
    // first, firstOrNull とかも該当しそうだね
    println("Last event of the day: ${events.last().title}")

    // 拡張プロパティ
    // TODO 復習必須
    println("Duration of first event of the day: ${events[0].durationOfEvent}")
}



data class Event(
    val title: String,
    val description: String? = null,
    val daypart: Daypart,
    val durationInMinutes: Int
)
// 正解

enum class Daypart {
    MORNING,
    AFTERNOON,
    EVENING
}
// 正解

// 拡張プロパティ
val Event.durationOfEvent: String
    get() = if (this.durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }