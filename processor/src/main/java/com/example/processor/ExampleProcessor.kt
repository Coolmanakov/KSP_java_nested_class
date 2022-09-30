package com.example.processor

import com.example.annotations.ExampleAnnotation
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate

class ExampleProcessor(
    private val logger: KSPLogger
) : SymbolProcessor {

    private val annotation = ExampleAnnotation::class.qualifiedName
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(annotation!!)
            .filterIsInstance<KSClassDeclaration>()

        if (!symbols.iterator().hasNext()) {
            return emptyList()
        }
        val result = symbols.filterNot { it.validate() }.toList()

        return result.ifEmpty {
            logger.error("Everything is good, validate all")
            emptyList()
        }
    }
}