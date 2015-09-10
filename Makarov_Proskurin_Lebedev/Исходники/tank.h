#pragma once

#ifndef TANK_H
#define TANK_H

#include "combatunit.h"
#include "ammoshell.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Танк
class Tank : public CombatUnit
{
	public:
		/// @name Конструкторы
		/// @{
		Tank();
		Tank(irr::video::IVideoDriver *driver,
			 irr::scene::ISceneManager *smgr,
			 irr::core::vector3df position,
			 IDTeam IDTEAM);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~Tank();
		/// @}

		/// @name Функции
		/// @{
		virtual void setInfoModel(irr::video::IVideoDriver    *   driver,
						 irr::scene::ISceneManager   *   smgr,
						 std::string texture,
						 irr::core::vector3df position );
		virtual void toAttack (IVideoDriver *driver,
							   ISceneManager *smgr,
							   IMeshSceneNode *targetNode,
							   std::vector<Ammunition *> *Ammo);
		virtual void Update(IVideoDriver *driver,
							ISceneManager *smgr,
							Map *Maps,
							std::vector<Unit*> Units,
							std::vector<Ammunition *> *Ammo);
		/// @}
};

#endif // TANK_H
