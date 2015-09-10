#pragma once

#ifndef MAPSPICE_H
#define MAPSPICE_H

#include "object.h"
#include "LayerSpice.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Карта спайса - хранит в себе все слои спайса, находящегося на карте.
class MapSpice : public Object
{
	public:
		/// Контейнер указателей на слои спайса
		std::vector<LayerSpice *> MapLayerSpice;
	public:
		/// @name Конструкторы
		/// @{
		MapSpice();
		MapSpice(IVideoDriver *driver, ISceneManager *smgr);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~MapSpice();
		/// @}

		/// @name Функции
		/// @{
		void addLayerSpice(LayerSpice * LSpice);
		virtual void Update();
		/// @}
};

#endif // MAPSPICE_H
////////////////////////////////////////////////////////////////////////////////
