package com.bubbletastic.githubjobs.service

import java.io.InputStream

interface NetworkHandler {
    fun get(url: String): InputStream
}