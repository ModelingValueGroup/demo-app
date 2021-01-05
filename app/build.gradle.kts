//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// (C) Copyright 2018-2021 Modeling Value Group B.V. (http://modelingvalue.org)                                        ~
//                                                                                                                     ~
// Licensed under the GNU Lesser General Public License v3.0 (the 'License'). You may not use this file except in      ~
// compliance with the License. You may obtain a copy of the License at: https://choosealicense.com/licenses/lgpl-3.0  ~
// Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on ~
// an 'AS IS' BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the  ~
// specific language governing permissions and limitations under the License.                                          ~
//                                                                                                                     ~
// Maintainers:                                                                                                        ~
//     Wim Bast, Tom Brus, Ronald Krijgsheld                                                                           ~
// Contributors:                                                                                                       ~
//     Arjan Kok, Carel Bast                                                                                           ~
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

val VERSION: String by project
val GROUP: String by project
val TOKEN: String = System.getenv("TOKEN") ?: "DRY"

group = GROUP
version = VERSION

plugins {
    application
    `maven-publish`
}

repositories {
    jcenter()
    mavenLocal()
    maven {
        url = uri("https://maven.pkg.github.com/ModelingValueGroup/packages")
        credentials {
            username = "" // can be anything but plugin requires it
            password = TOKEN
        }
    }
    maven {
        url = uri("https://maven.pkg.github.com/ModelingValueGroup/packages-snapshots")
        credentials {
            username = "" // can be anything but plugin requires it
            password = TOKEN
        }
    }
}

dependencies {
    gradleApi()
    implementation("demo-lib:lib:3.1.0-BRANCH")
    implementation("com.google.guava:guava:29.0-jre")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

application {
    mainClass.set("demo.app.App")
}

tasks.test {
    useJUnitPlatform()
}
