package com.demo.configure

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*
import kotlinx.html.*
fun Application.ConfiguretionReverseRouting(){
    install(Resources)
    routing {
        // Building links from resources
        get {
            call.respondHtml {
                body {
                    this@ConfiguretionReverseRouting.apply {
                        p {
                            val link: String = href(Articles())
                            a(link) { +"Get all articles" }
                        }
                        p {
                            val link: String = href(Articles.New())
                            a(link) { +"Create a new article" }
                        }
                        p {
                            val link: String = href(Articles.Id.Edit(Articles.Id(id = 123)))
                            a(link) { +"Edit an exising article" }
                        }
                        p {
                            val urlBuilder =
                                URLBuilder(URLProtocol.HTTPS, "ktor.io", parameters = parametersOf("token", "123"))
                            href(Articles(sort = null), urlBuilder)
                            val link: String = urlBuilder.buildString()
                            i { a(link) { +link } }
                        }
                    }
                }
            }
        }
    }
}