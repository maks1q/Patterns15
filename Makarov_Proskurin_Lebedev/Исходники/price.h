#pragma once

#ifndef PRICE_H
#define PRICE_H

#include "resource.h"

////////////////////////////////////////////////////////////////////////////////
/// \brief Цена - стоимость какого-либо объекта.
class Price : public Resource
{
	public:
		/// @name Конструкторы
		/// @{
		Price();
		/// @}

		/// @name Деструкторы
		/// @{
		virtual ~Price();
		/// @}
};

#endif // PRICE_H
////////////////////////////////////////////////////////////////////////////////
