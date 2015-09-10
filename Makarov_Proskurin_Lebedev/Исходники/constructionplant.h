#pragma once

#ifndef CONSTRUCTIONPLANT_H
#define CONSTRUCTIONPLANT_H

#include "structure.h"
#include "harvester.h"
#include "tank.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Строительный завод - строит здания.
class ConstructionPlant : public Structure
{
	public:
		/// @name Конструкторы
		/// @{
		ConstructionPlant();
		ConstructionPlant(irr::video::IVideoDriver    *   driver,
						  irr::scene::ISceneManager   *   smgr,
						  irr::core::vector3df position,
						  IDTeam IDTEAM,
						  Spice *SpiceTeam);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~ConstructionPlant();
		/// @}

		/// @name Функции
		/// @{
		virtual void setInfoModel (IVideoDriver *driver, ISceneManager *smgr, std::string texture, vector3df position);
		virtual Harvester *createHarv(IVideoDriver *driver, ISceneManager *smgr);
		virtual Tank *createTank(IVideoDriver *driver, ISceneManager *smgr);
		/// @}
};

#endif // CONSTRUCTIONPLANT_H
////////////////////////////////////////////////////////////////////////////////
