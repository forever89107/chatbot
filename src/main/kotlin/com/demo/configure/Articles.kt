package com.demo.configure

import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.resources.*
import io.ktor.server.resources.Resources
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import kotlinx.serialization.*

@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new") {
    @Serializable
    @Resource("new")
    class New(val parent: Articles = Articles())

    @Serializable
    @Resource("{id}")
    class Id(val parent: Articles = Articles(), val id: Long) {
        @Serializable
        @Resource("edit")
        class Edit(val parent: Id)
    }
}

fun Application.configureResourceDemo() {
    install(Resources)
    routing {
        get<Articles> { article ->
            // Get all articles ...
            call.respondText("List of articles sorted starting from ${article.sort}")
        }
        get<Articles.New> {
            // Show a page with fields for creating a new article ...
            call.respondText("Create a new article")
        }
        post<Articles> {
            // Save an article ...
            call.respondText("An article is saved", status = HttpStatusCode.Created)
        }
        get<Articles.Id> { element ->
            // Show an article with id ${article.id} ...
            call.respondText("An article with id ${element.id}", status = HttpStatusCode.OK)
        }
        get<Articles.Id.Edit> { element ->
            // Show a page with fields for editing an article ...
            call.respondText("Edit an article with id ${element.parent.id}", status = HttpStatusCode.OK)
        }
        put<Articles.Id> { element ->
            // Update an article ...
            call.respondText("An article with id ${element.id} updated", status = HttpStatusCode.OK)
        }
        delete<Articles.Id> { element ->
            // Delete an article ...
            call.respondText("An article with id ${element.id} deleted", status = HttpStatusCode.OK)
        }
    }
}