// Copyright (C) 2009-2010 Josiah Hartzell (Skyreign Software)
// This file is part of the "irrWeatherManager" weather management library for the Irrlicht rendering engine.
// For conditions of distribution and use, see copyright notice in irrWeatherManager.h

#include "irrWeatherManager.h"

namespace irr
{

irrWeatherManager::irrWeatherManager(scene::ISceneManager* mgr)
{
	if( !mgr )
		return;

	smgr = mgr;

	f32 timeInSeconds = 120.f;
	f32 correctionOfTheVSync = (20.f / 34.f); // I get this value when i calculate the diffTime in onAnimate fonction.

	Atmosphere = new IWeatherManagerAtmosphere((timeInSeconds * correctionOfTheVSync), 0.f, 0.f, 0.f, smgr->getRootSceneNode(), smgr, 0);

	if( !Atmosphere )
	{
		printf("-- irrWeatherManager: ERROR: could not create atmosphere --\n");
		return;
	}

	else
		printf("irrWeatherManager %i.%i.%i\n", IRR_WEATHER_MANAGER_VER_MAJOR, IRR_WEATHER_MANAGER_VER_MINOR, IRR_WEATHER_MANAGER_VER_MICRO);
}

irrWeatherManager::~irrWeatherManager()
{
	if ( Atmosphere)
		Atmosphere->remove();
}

} // namespace irr
