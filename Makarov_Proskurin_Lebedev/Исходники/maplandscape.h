#pragma once

#ifndef MAPLANDSCAPE_H
#define MAPLANDSCAPE_H

#include "object.h"
#include "LayerLandscape.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Карта ландшафта - хранит все слои ландшафта.
class MapLandscape : public Object
{
	public:
		/// Контейнер указателей на слои ландшафта
		std::vector<LayerLandscape *> MapLayerLandscape;
	public:
		/// @name Конструкторы
		/// @{
		MapLandscape();
		MapLandscape(IVideoDriver *driver, ISceneManager *smgr);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~MapLandscape();
		/// @}

		/// @name Функции
		/// @{
		void addLayerLandscape(LayerLandscape * LLand);
		/// @}
};

#endif // MAPLANDSCAPE_H
////////////////////////////////////////////////////////////////////////////////
