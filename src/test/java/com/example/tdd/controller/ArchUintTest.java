package com.example.tdd.controller;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;


public class ArchUintTest {

    static JavaClasses classes;

    @BeforeAll
    static void beforeAll(){
        System.out.println("BeforeAll");
        classes = new ClassFileImporter().importPackages("com.example.tdd");
    }

    @Test
    void 패키지_참조범의_확인(){
        ArchRule myRule = classes()
                .that().resideInAPackage("..service..")
                .should().resideInAnyPackage("..configuration..", "..data..", "..service..");

        myRule.check(classes);

    }

    @Test
    void 패키지_잠조방향_확인(){
        ArchRule controllerRule = noClasses().that().resideInAPackage("..service..")
                .should().accessClassesThat().resideInAPackage("..controller");

        controllerRule.check(classes);
    }

    @Test
    void 순환참조_확인(){
        ArchRule freeOfCycles = slices().matching("..com.example.(*)..")
                .should().beFreeOfCycles();
        freeOfCycles.check(classes);

    }




}
