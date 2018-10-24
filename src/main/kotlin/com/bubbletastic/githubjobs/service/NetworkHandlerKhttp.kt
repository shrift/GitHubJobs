package com.bubbletastic.githubjobs.service

import khttp.get
import java.io.InputStream

class NetworkHandlerKhttp : NetworkHandler {
    override fun get(url: String): InputStream {
        return get(url, stream = true).raw
    }
}