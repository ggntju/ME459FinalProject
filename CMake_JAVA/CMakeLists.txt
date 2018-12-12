cmake_minimum_required (VERSION 2.8)

find_package(Java REQUIRED)
include(UseJava)

enable_testing()

project (Main)

set(CMAKE_JAVA_COMPILE_FLAGS "-source" "1.8" "-target" "1.8")

add_jar(Main Main.java MANIFEST ./META-INF/MANIFEST.MF)

get_target_property(_jarFile Main JAR_FILE)
get_target_property(_classDir Main CLASSDIR)

message(STATUS "Jar file ${_jarFile}")
message(STATUS "Class compiled to ${_classDir}")