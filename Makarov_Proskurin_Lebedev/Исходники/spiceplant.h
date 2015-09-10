#pragma once

#ifndef SPICEPLANT_H
#define SPICEPLANT_H

#include "structure.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Спайсовый завод - ведет переработку Спайса.
class SpicePlant : public Structure
{
	public:
		/// @name Конструкторы
		/// @{
		SpicePlant();
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~SpicePlant();
		/// @}
};

#endif // SPICEPLANT_H
////////////////////////////////////////////////////////////////////////////////
