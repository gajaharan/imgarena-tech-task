package com.imgarena.techtask

object TestFixtures {
    val dataSourceOneBody =
        """
        {
            "tournamentId": "174638",
            "tournamentName": "Women's Open Championship",
            "forecast": "fair",
            "courseName": "Sunnydale Golf Course",
            "countryCode": "GB",
            "startDate": "09/07/21",
            "endDate": "13/07/21",
            "roundCount": "4"
        }
        """.trimIndent()

    val dataSourceTwoBody =
        """
        {
            "tournamentUUID":"87fc6650-e114-4179-9aef-6a9a13030f80",
            "golfCourse":"Happy Days Golf Club",
            "competitionName":"South West Invitational",
            "hostCountry":"United States Of America",
            "epochStart":"1638349200",
            "epochFinish":"1638468000",
            "rounds":"2",
            "playerCount":"35"
        }
        """.trimIndent()

}