package com.example.theshowhub

enum class SortOption(val label: Int) {
    BestVoted(R.string.best_voted_label),
    WorstVoted(R.string.worst_voted_label),
    TitleAZ(R.string.a_to_z_label),
    TitleZA(R.string.z_to_a_label),
    AirDateNewest(R.string.air_date_newest_label),
    AirDateOldest(R.string.air_date_oldest_label)
}
