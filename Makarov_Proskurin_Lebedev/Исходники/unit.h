#pragma once

#ifndef UNIT_H
#define UNIT_H

#include "objectwithmodel.h"
#include "Map.h"
#include "LayerSpice.h"
#include "mapspice.h"
#include "spice.h"


////////////////////////////////////////////////////////////////////////////////
/// \brief Общий класс для юнитов: Харвестер, Легкий танк, Средний танк и т.д.
class Unit : public ObjectWithModel
{
	protected:
		/// Броня
		int Armor;
		/// Коэффициент защиты брони
		f32 RatioArmor;
		/// Скорость движения юнита
		f32 Speed;
		/// Контейнер точек назначения
		std::vector <vector3df> vecPoint;
	public:
		/// @name Конструкторы
		/// @{
		Unit();
		Unit(int Armor, f32 RatioArmor);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~Unit();
		/// @}

		/// @name Sets
		/// @{
		void setArmor		(int Armor);
		void setRatioArmor	(f32 RatioArmor);
		void setSpeed		(f32 Speed);
		void setInfoUnit (int Armor, f32 RatioArmor, f32 Speed);
		/// @}

		/// @name Gets
		/// @{
		int getArmor();
		f32 getRatioArmor();
		f32 getSpeed();
		f32 getRotateAngle(vector3df base, vector3df target);
		/// @}

		/// @name Функции
		/// @{
		virtual void toShowInfo ();
		virtual void toMove();
		virtual void toTurn(vector3df Point);
		virtual void toMoveToPoint(vector3df Point);
		virtual void toMoveToNode(irr::scene::IMeshSceneNode * targetNode);
		void addPoint(vector3df Point);
		virtual void Update();
		virtual void Update(Map * Maps);
		/// @}

		/// @name Проверки
		/// @{
		bool isIArrived(vector3df Point);
		virtual bool isTargetInRadius(vector3df Point, f32 Radius);
		virtual bool isCheckTheIndex();
		virtual bool isCheckTheIndex(std::vector<Unit*> Units);
		virtual bool isCheckTheIndex(std::vector<LayerSpice*> MSpice);
		/// @}
};

#endif // UNIT_H
////////////////////////////////////////////////////////////////////////////////
