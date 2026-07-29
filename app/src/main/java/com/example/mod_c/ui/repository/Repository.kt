package com.example.mod_c.ui.repository

import android.content.Context
import com.example.mod_c.ui.models.CarouselDto
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class Repository(val context: Context) {
    val json = Json {
        ignoreUnknownKeys = true
    }

    val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(json)
        }
        install(HttpTimeout){
            socketTimeoutMillis = 5000
            connectTimeoutMillis = 7000
            requestTimeoutMillis = 5000
        }
        install(HttpRequestRetry){
            retryIf(maxRetries = 2){request,response->
                !response.status.isSuccess()
            }
            exponentialDelay()
            retryOnException(maxRetries = 2)
        }
    }


    suspend fun loadJson() = withContext(Dispatchers.IO){
    val text = context.assets.open("artigos").bufferedReader().use { it.readText() }
    Json.decodeFromString<List<CarouselDto>>("\"app/src/main/assets/artigos.json\"")
    }

}