#pragma once

#ifndef EXTRACTINGUNIT_H
#define EXTRACTINGUNIT_H

#include "unit.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Класс юнитов, добывающих ресурсы.
class ExtractingUnit : public Unit
{
	public:
		/// Скорость добычи ресурса
		unsigned int ProductionSpeed;
		/// Указатель на текущее спайсовое поле, с которого ведется добыча ресурса
		LayerSpice * ThisSpice = NULL;
		/// Указательно на спайсовый склад, содержащийся внутри юнита
		Spice * SpiceBunker = NULL;
		/// Указатель на спайсовый склад команды
		Spice * SpiceTeam   = NULL;
		/// Координаты местонахождения базы
		vector3df Home;
	public:
		/// @name Конструкторы
		/// @{
		ExtractingUnit();
		/// @}

		/// @name Деструкторы
		/// @{
		virtual	~ExtractingUnit();
		/// @}

		/// @name Sets
		/// @{
		void setProductionSpeed	(unsigned int ProductionSpeed);
		void setInfoExUnit(unsigned int ProductionSpeed, int Res, int MaxRes, Spice *SpiceTeam);
		/// @}

		/// @name Gets
		/// @{
		unsigned int getProductionSpeed()	const;
		/// @}

		/// @name Функции
		/// @{
		virtual void toSearchOfResources(std::vector<LayerSpice *> MSpice);
		virtual void toExtractSpice(LayerSpice * ThisLayerSpice);
		virtual void Update ();
		virtual void Update(Map *Maps);
		/// @}

		/// @name Проверки
		/// @{
		virtual bool isCheckTheIndex(std::vector<LayerSpice *> MSpice);
		/// @}
};

#endif // EXTRACTINGUNIT_H
////////////////////////////////////////////////////////////////////////////////
