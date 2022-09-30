package com.example.ksp_java_nested_class;

import com.example.annotations.ExampleAnnotation;

@ExampleAnnotation("exampleValue")
public class ExampleParentClass {

    @ExampleAnnotation("exampleValueInStatic")
    static class ExampleChildStaticClass {}
}
