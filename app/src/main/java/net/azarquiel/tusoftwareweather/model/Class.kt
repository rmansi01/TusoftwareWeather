package net.azarquiel.tusoftwareweather.model

data class Currently(val summary : String, val icon : String, val precipProbability : Double, val temperature : Double)
data class Daily(val data:List<Data>)
data class Data(val time : Long, val summary : String, val icon : String, val precipProbability: Double, val temperatureMax : Double, val temperatureMin : Double)
data class Result(val currently: Currently, val daily: Daily)