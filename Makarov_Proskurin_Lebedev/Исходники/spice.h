#pragma once

#ifndef SPICE_H
#define SPICE_H

#include "resource.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Спайс - ресурс. Используется для покупки юнитов и зданий.
class Spice : public Resource
{
	public:
		/// @name Конструкторы
		/// @{
		Spice();
		Spice(int NumSpice, int MaxNumSpice, IDTeam IDTEAM);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~Spice();
		/// @}
};

#endif // SPICE_H
////////////////////////////////////////////////////////////////////////////////
