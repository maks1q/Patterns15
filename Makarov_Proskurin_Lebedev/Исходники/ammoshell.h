#pragma once

#ifndef AMMOSHELL_H
#define AMMOSHELL_H

#include "ammunition.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Боевой снаряд
class AmmoShell : public Ammunition
{
	public:
		/// @name Конструкторы
		/// @{
		AmmoShell();
		AmmoShell(irr::video::IVideoDriver    *	driver,
				  irr::scene::ISceneManager   *	smgr,
				  irr::core::vector3df			position,
				  IDTeam IDTEAM,
				  irr::core::vector3df targetPosition);
		/// @}

		/// @name Деструкторы
		/// @{
		~AmmoShell();
		/// @}

		/// @name Функции
		/// @{
		virtual void Update();
		/// @}
};

#endif // AMMOSHELL_H
////////////////////////////////////////////////////////////////////////////////
