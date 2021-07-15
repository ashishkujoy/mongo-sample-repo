package com.example.springmongo

import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface TemplateRepository : ReactiveCrudRepository<TemplateGroup, String> {
    @Cacheable("templates")
    fun findTemplateGroupByName(name: String): Mono<TemplateGroup>
}
