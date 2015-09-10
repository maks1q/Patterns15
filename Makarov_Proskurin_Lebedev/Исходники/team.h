#pragma once

#ifndef TEAM_H
#define TEAM_H

#include "object.h"
#include "unit.h"
#include "harvester.h"
#include "structure.h"
#include "spice.h"
#include "electricpower.h"
#include "constructionplant.h"
#include "combatunit.h"
#include "extractingunit.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Команда - военная структура, состоящая из юнитов, зданий, ресурсов.
class Team : public Object
{
	public:
		/// Название команды
		std::string NameTeam;
		/// Контейнер указателей на боевых юнитов
		std::vector<CombatUnit *> TeamCombat;
		/// Контейнер указателей на добывающих юнитов
		std::vector<ExtractingUnit *> TeamExtract;
		/// Контейнер указателей на здания
		std::vector<Structure *> TeamStructure;

		ConstructionPlant *MyBase = NULL;
		/// Указатель на спайсовый склад
		Spice * SpiceTeam = NULL;
		/// Указатель на склад электроэнергии
		ElectricPower * EPowerTeam = NULL;
		/// \todo Создать фабричный метод создания объектов типа: юнит и здание.
	public:
		/// @name Конструкторы
		/// @{
		Team();
		Team(irr::video::IVideoDriver *driver,
			 irr::scene::ISceneManager *smgr,
			 irr::core::vector3df position,
			 IDTeam IDTEAM);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~Team();
		/// @}

		/// @name Sets
		/// @{
		void setNameTeam(std::string NameTeam);
		/// @}
		/// @name Gets
		/// @{
		std::string getNameTeam();
		/// @}

		/// @name Функции
		/// @{
		void addUnit(Unit * UnitTeam);
		void addStructure(Structure * StructureTeam);
		virtual void Update(IVideoDriver *driver,
							ISceneManager *smgr,
							Map *Maps,
							std::vector<Unit *> Units,
							std::vector<Ammunition *> * Ammo);
		/// @}
};

#endif // TEAM_H
////////////////////////////////////////////////////////////////////////////////
