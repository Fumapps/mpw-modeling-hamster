include_directories(.)

add_executable(hamstersimulator_core_tests
        HamsterCommandTest.cpp
        InitHamsterCommandTest.cpp
        TerritoryBuilderTest.cpp
        UndoCommandTest.cpp
        view/HamsterViewModelTest.cpp
        util/GameStringifier.cpp util/GameStringifier.h
        util/TestUtils.cpp util/TestUtils.h
        util/DirectionTestHelper.cpp util/DirectionTestHelper.h
        view/HamsterViewTestBase.cpp view/HamsterViewTestBase.h
        view/HamsterViewModelStringifier.cpp view/HamsterViewModelStringifier.h view/UserInputInterfaceFake.cpp view/UserInputInterfaceFake.h)
target_link_libraries(hamstersimulator_core_tests hamstersimulator_core_lib gtest_main)
add_test(NAME hamstersimulator_core_tests COMMAND hamstersimulator_core_tests WORKING_DIRECTORY $<TARGET_FILE_DIR:hamstersimulator_core_tests>)

# Copy resources from binary root
add_custom_command(TARGET hamstersimulator_core_tests PRE_BUILD
        COMMAND ${CMAKE_COMMAND} -E copy_directory
        ${CMAKE_BINARY_DIR}/resources/ $<TARGET_FILE_DIR:hamstersimulator_core_tests>/resources)
