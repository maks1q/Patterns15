#pragma once

#ifndef GAME_H
#define GAME_H

#include "stdafx.h"
#include "all_h.h"
#include <time.h>
////////////////////////////////////////////////////////////////////////////////
/// \brief Основной класс, управляющий всеми элементами игры.
class Game
{
	public:
		/// Контейнер указателей на всех юнитов в игре
		std::vector<Unit *> Units;
		/// Контейнер указателей на все команды в игре
		std::vector<Team *> Teams;
		/// Контейнер указателей на всех боевые снаряды в игре
		std::vector<Ammunition *> Ammo;
		/// Указатель на глобальную карту
		Map * Maps = NULL;
	public:
		/// @name Конструкторы
		/// @{
		Game();
		Game(IVideoDriver *driver, ISceneManager *smgr);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual	~Game();
		/// @}

		/// @name Функции
		/// @{
		virtual void Update(IVideoDriver *driver,
							ISceneManager *smgr,
							irr::gui::IGUIEnvironment *guienv,
							core::dimension2d<u32> deskres);
		virtual void UpdateUnits();
		virtual void addTeam(Team *TeamGame);
		/// @}
};

#endif // GAME_H
////////////////////////////////////////////////////////////////////////////////
