package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.model.BioSiteData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("REHM Instalações", appName)
    }

    @Test
    fun `verify bio site contact links`() {
        assertEquals("https://wa.link/0d3d10", BioSiteData.WHATSAPP_URL)
        assertTrue(BioSiteData.INSTAGRAM_URL.contains("instagram.com/bruno_santospaz"))
        assertTrue(BioSiteData.services.isNotEmpty())
    }
}
