#pragma once

#ifndef MAP_H
#define MAP_H

#include "object.h"
#include "mapspice.h"
#include "maplandscape.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Класс карта. Хранит в себе все подкарты и слои карты.
class Map : public Object
{
	public:
		/// Указатель на карту спайса
		MapSpice		* IMapSpice			=	NULL;
		/// Указатель на карту ландшафта
		MapLandscape	* IMapLandscape		=	NULL;
	public:
		/// @name Конструкторы
		/// @{
		Map();
		Map(IVideoDriver *driver, ISceneManager *smgr);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~Map();
		/// @}

		/// @name Функции
		/// @{
		void createMap(IVideoDriver *driver, ISceneManager *smgr);
		virtual void Update();
		/// @}
};

#endif // MAP_H
////////////////////////////////////////////////////////////////////////////////
