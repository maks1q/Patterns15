#pragma once

#ifndef HARVESTER_H
#define HARVESTER_H

#include "extractingunit.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Юнит, добывающий ресурс Спайс.
class Harvester : public ExtractingUnit
{
	public:
		/// @name Конструкторы
		/// @{
		Harvester();
		Harvester(irr::video::IVideoDriver *driver,
				  irr::scene::ISceneManager *smgr,
				  irr::core::vector3df position,
				  IDTeam IDTEAM,
				  Spice * SpiceTeam);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~Harvester();
		/// @}

		/// @name Функции
		/// @{
		virtual void setInfoModel(irr::video::IVideoDriver    *   driver,
								 irr::scene::ISceneManager   *   smgr,
								 std::string texture,
								 irr::core::vector3df position);

		virtual void Update();
		virtual void Update(Map *Maps);
		/// @}

};

#endif // HARVESTER_H
////////////////////////////////////////////////////////////////////////////////
