package com.bubbletastic.githubjobs.service

import org.junit.Before
import org.junit.Test

class JobsServiceTest {

    lateinit var jobsService: JobsService

    @Before
    fun setup() {
        jobsService = JobsService(NetworkHandlerMock())
    }

    @Test
    fun testAccumulatePages() {
        val accumulatePages = jobsService.accumulatePages("https://asdf.com/doesnotmatter?someparam=novalue")
        assert(accumulatePages.count() == 210)
        assert(accumulatePages.distinctBy { it.string("id") }.count() == 210)
    }
}