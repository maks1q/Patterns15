#pragma once

#ifndef COMBATUNIT_H
#define COMBATUNIT_H

#include "unit.h"
#include "ammunition.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Класс - боевая единица.
class CombatUnit : public Unit
{
	public:
		/// Дистанция для атаки
		float AttackDistance;
		int RechargeTime;
		Unit * TargetUnit = NULL;
	public:
		/// @name Конструкторы
		/// @{
		CombatUnit();
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~CombatUnit();
		/// @}

		/// @name Sets
		/// @{
		void setAttackDistance(float AttackDistance);
		void setRechargeTime(int RechargeTime);
		void setInfoCombUnit(float AttackDistance, int RechargeTime);
		/// @}

		/// @name Gets
		/// @{
		float getAttackDistance();
		int getRechargeTime();
		/// @}

		/// @name Функции
		/// @{
		//virtual void Attack(ISceneManager *smgr, IVideoDriver *driver, IMeshSceneNode * sceneNode);
		virtual void toAttack(IVideoDriver *driver, ISceneManager *smgr, IMeshSceneNode * sceneNode, std::vector<Ammunition *> *Ammo);

		virtual void toSearchOfEnemy(std::vector<Unit *> Units);
		virtual void Update(IVideoDriver *driver, ISceneManager *smgr,
							Map *Maps, std::vector<Unit *> Units,
							std::vector<Ammunition *> *Ammo);
		/// @}

		/// @name Проверки
		/// @{
		virtual bool isCheckTheIndex(std::vector<Unit*> Units);
		/// @}
};

#endif // COMBATUNIT_H
////////////////////////////////////////////////////////////////////////////////
