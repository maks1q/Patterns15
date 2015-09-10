#pragma once

#ifndef STRUCTURE_H
#define STRUCTURE_H

#include "objectwithmodel.h"
#include "spice.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Общий класс для зданий: Спайсовый завод, Силос, Машинный завод и т.д.
class Structure : public ObjectWithModel
{
	public:
		/// Прочность здания
		unsigned int Strength;
		Spice * SpiceTeam   = NULL;
	public:
		/// @name Конструкторы
		/// @{
		Structure();
		/// @}

		/// @name Деструкторы
		/// @{
		~Structure();
		/// @}

		/// @name Sets
		/// @{
		void setStrength(unsigned int Strength);
		/// @}

		/// @name Gets
		/// @{
		unsigned int getStrength();
		/// @}
};

#endif // STRUCTURE_H
////////////////////////////////////////////////////////////////////////////////
