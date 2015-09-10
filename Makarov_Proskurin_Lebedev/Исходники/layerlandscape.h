#pragma once

#ifndef LAYERLANDSCAPE_H
#define LAYERLANDSCAPE_H

#include "objectwithmodel.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Слой ландшафтного поля.
class LayerLandscape : public ObjectWithModel
{
	public:
		/// @name Конструкторы
		/// @{
		LayerLandscape();
		LayerLandscape(irr::video::IVideoDriver    *   driver, irr::scene::ISceneManager   *   smgr, irr::core::vector3df position, IDTeam IDTEAM);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~LayerLandscape();
		/// @}

		/// @name Функции
		/// @{
		virtual void setInfoModel (IVideoDriver *driver, ISceneManager *smgr, std::string texture, vector3df position);
		/// @}
};

#endif // LAYERLANDSCAPE_H
////////////////////////////////////////////////////////////////////////////////
