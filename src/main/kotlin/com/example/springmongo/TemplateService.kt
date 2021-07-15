package com.example.springmongo

import org.slf4j.LoggerFactory
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Component
class TemplateService(private val templateRepository: TemplateRepository) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun getDocument(templateGroupName: String): Mono<TemplateGroup> {
        return templateRepository
            .findTemplateGroupByName(templateGroupName)
            .switchIfEmpty { Mono.error(Throwable("Template not found in db for $templateGroupName")) }
            .doOnSuccess {
                log.info("Successfully fetched template from db")
            }
            .doOnError {
                log.error("Failed to fetch template from db", it)
            }
    }
}


@Document(collection = "template")
data class TemplateGroup(val name: String, val templates: List<Template>)

data class Template(
    val name: String,
    val content: String
)
