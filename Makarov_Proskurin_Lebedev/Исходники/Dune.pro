#-------------------------------------------------
#
# Project created by QtCreator 2014-05-14T12:48:14
#
#-------------------------------------------------

QT       += core

QT       -= gui

TARGET = Dune
CONFIG   += console
CONFIG   -= app_bundle

TEMPLATE = app


SOURCES += main.cpp \
    object.cpp \
    resource.cpp \
    unit.cpp \
    team.cpp \
    ai.cpp \
    division.cpp \
    spice.cpp \
    electricpower.cpp \
    machineplant.cpp \
    constructionplant.cpp \
    structure.cpp \
    silo.cpp \
    windturbine.cpp \
    spiceplant.cpp \
    price.cpp \
    objectwithmodel.cpp \
    rtscamera.cpp \
    extractingunit.cpp \
    harvester.cpp \
    game.cpp \
    combatunit.cpp \
    mapspice.cpp \
    maplandscape.cpp \
    layerspice.cpp \
    layerlandscape.cpp \
    map.cpp \
    state.cpp \
    tank.cpp \
    ammunition.cpp \
    ammoshell.cpp \
    IAtmosphereSkySceneNode.cpp \
    IAtmosphereStarSceneNode.cpp \
    irrWeatherManager.cpp \
    IWeatherManagerAtmosphere.cpp
LIBS += -LC:\Qt\Qt5.2.1\5.2.1\mingw48_32\include\Irrlicht\lib\Win32-gcc -lIrrlicht
INCLUDEPATH += C:\Qt\Qt5.2.1\5.2.1\mingw48_32\include\Irrlicht\include

HEADERS += \
    object.h \
    resource.h \
    unit.h \
    team.h \
    ai.h \
    division.h \
    stdafx.h \
    spice.h \
    electricpower.h \
    machineplant.h \
    constructionplant.h \
    structure.h \
    silo.h \
    windturbine.h \
    spiceplant.h \
    price.h \
    all_h.h \
    objectwithmodel.h \
    rtscamera.h \
    extractingunit.h \
    game.h \
    combatunit.h \
    mapspice.h \
    maplandscape.h \
    map.h \
    layerspice.h \
    layerlandscape.h \
    harvester.h \
    state.h \
    idteam.h \
    tank.h \
    ammunition.h \
    ammoshell.h \
    IAtmosphereSkySceneNode.h \
    IAtmosphereStarSceneNode.h \
    irrWeatherManager.h \
    IWeatherManagerAtmosphere.h
