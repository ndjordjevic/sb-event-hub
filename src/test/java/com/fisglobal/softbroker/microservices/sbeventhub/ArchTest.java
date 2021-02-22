package com.fisglobal.softbroker.microservices.sbeventhub;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.fisglobal.softbroker.microservices.sbeventhub");

        noClasses()
            .that()
                .resideInAnyPackage("com.fisglobal.softbroker.microservices.sbeventhub.service..")
            .or()
                .resideInAnyPackage("com.fisglobal.softbroker.microservices.sbeventhub.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.fisglobal.softbroker.microservices.sbeventhub.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
