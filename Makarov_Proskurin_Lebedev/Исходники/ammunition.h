#pragma once

#ifndef AMMUNITION_H
#define AMMUNITION_H

#include "objectwithmodel.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Класс Боеприпасы
class Ammunition : public ObjectWithModel
{
	public:
		int Damage;
		f32	RadiusOfDefeat;
		irr::core::vector3df TargetPosition;
	public:
		/// @name Конструкторы
		/// @{
		Ammunition();
		Ammunition(irr::video::IVideoDriver    *   driver,
				   irr::scene::ISceneManager   *   smgr,
				   irr::core::vector3df position,
				   IDTeam IDTEAM,
				   irr::core::vector3df targetPosition);
		/// @}

		/// @name Деструкторы
		/// @{
		~Ammunition();

		/// @}

		/// @name Sets
		/// @{
		void setDamage(int Damage);
		void setRadiusOfDefeat(f32 RadiusOfDefeat);
		void setTargetPosition(irr::core::vector3df TargetPosition);
		/// @}

		/// @name Gets
		/// @{
		int getDamage();
		f32 getRadiusOfDefeat();
		irr::core::vector3df getTargetPosition();
		/// @}

		/// @name Функции
		/// @{
		void setInfoAmmo(int Damage, f32 RadiusOfDefeat);
		void toMoveToTarget();
		virtual void Update();
		/// @}
};

#endif // AMMUNITION_H
////////////////////////////////////////////////////////////////////////////////
