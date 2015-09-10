#pragma once

#ifndef MACHINEPLANT_H
#define MACHINEPLANT_H

#include "structure.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Машинный завод - производит юнитов.
class MachinePlant : public Structure
{
	public:
		/// @name Конструкторы
		/// @{
		MachinePlant();
		MachinePlant(IDTeam IDTEAM);
		/// @}

		/// @name Деструкторы
		/// @{
		virtual	~MachinePlant();
		/// @}
};

#endif // MACHINEPLANT_H
////////////////////////////////////////////////////////////////////////////////
