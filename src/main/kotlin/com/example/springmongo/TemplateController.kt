package com.example.springmongo

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/template-service")
class TemplateController(private val templateService: TemplateService) {

    @GetMapping("/document/{templateGroupName}")
    fun getDocument(@PathVariable templateGroupName: String): Mono<TemplateGroup> {
        return templateService.getDocument(templateGroupName)
    }
}