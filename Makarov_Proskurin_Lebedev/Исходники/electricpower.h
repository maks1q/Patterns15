#pragma once

#ifndef ELECTRICPOWER_H
#define ELECTRICPOWER_H

#include "resource.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Электроэнергия - ресурс, дающий энергию зданиям.
class ElectricPower : public Resource
{
	public:
		/// @name Конструкторы
		/// @{
		ElectricPower();
		ElectricPower(int NumPower, int MaxNumPower,IDTeam IDTEAM);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~ElectricPower();
		/// @}
};

#endif // ELECTRICPOWER_H
////////////////////////////////////////////////////////////////////////////////
